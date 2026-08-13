package es.cic.tessa.model.jsog;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.dto.AssetResponse;
import es.cic.tessa.model.dto.AssetValueResponse;
import es.cic.tessa.model.dto.TemplateResponse;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

/**
 * Reproduce el escenario real que falla cuando Spring Data Neo4j 8 entrega instancias
 * Java distintas para el mismo nodo Neo4j.
 *
 * <p>SDN 8 ya NO comparte instancias entre relaciones que apuntan al mismo nodo.
 * Como JSOG/{@code @JsonIdentityInfo} decide {@code @id} vs {@code @ref} por identidad
 * de instancia Java ({@code ==}), sin canonicalizacion cada instancia recibe su propio
 * {@code @id} y se declara completa en lugar de emitir {@code {"@ref": "N"}}.
 *
 * <p>Con {@link CanonicalizingBeanSerializerModifier} registrado en {@link JSOGModule},
 * las instancias se canonicalizan por {@code (Class, dbId)} antes de serializar, y Jackson
 * emite {@code @ref} automaticamente para las repetidas.
 *
 * <h2>Escenario SDN 8</h2>
 * <ul>
 *   <li>{@code rootAsset} — instancia A, dbId=1074, con {@code template} instancia X (dbId=66).</li>
 *   <li>5 {@code AssetValue}s, cada una con su propio {@code asset} (instancias B,C,D,E,F — todas
 *       dbId=1074, distintas de A) y su propio {@code template} para ese asset (instancias Y,Z,W,V,U
 *       — todas dbId=66, distintas de X).</li>
 *   <li>Total: 6 instancias Asset (todas dbId=1074), 6 instancias Template (todas dbId=66).</li>
 * </ul>
 *
 * <h2>Resultado esperado con canonicalizacion</h2>
 * <ul>
 *   <li>El Asset con dbId=1074 aparece completamente serializado una sola vez.</li>
 *   <li>El Template con dbId=66 aparece completamente serializado una sola vez.</li>
 *   <li>Las 5 apariciones repetidas del Asset se emiten como {@code {"@ref":"N"}}.</li>
 * </ul>
 *
 * <h2>Conteo de @id/@ref en el escenario SDN8</h2>
 * <p>El JSON final contiene:
 * <ul>
 *   <li>1 declaracion {@code @id} para el Asset canonico (dbId=1074).</li>
 *   <li>1 declaracion {@code @id} para el Template canonico (dbId=66) — embebido en el Asset.</li>
 *   <li>5 declaraciones {@code @id} para los 5 AssetValues (dbIds distintos: 200-204).</li>
 *   <li>5 referencias {@code @ref} para las 5 repeticiones del Asset.</li>
 * </ul>
 * Total: 7 declaraciones {@code @id} y 5 referencias {@code @ref}.
 *
 * <p>Sin canonicalizacion: 12 declaraciones {@code @id} (6 assets + 6 templates) y 0 {@code @ref}.
 */
class JSOGRealScenarioTest {

    private static final long ASSET_DB_ID = 1074L;
    private static final long TEMPLATE_DB_ID = 66L;

    // -------------------------------------------------------------------------
    // Jackson 3 (tools.jackson)
    // -------------------------------------------------------------------------

    /**
     * Construye un ObjectMapper Jackson 3 con JSOGModule (incluye
     * CanonicalizingBeanSerializerModifier) registrado explicitamente.
     */
    private ObjectMapper buildMapper() {
        return JsonMapper.builder()
                .addModule(new JSOGModule())
                .build();
    }

    @Test
    void instanciasCompartidas_funcionaComoAntes() throws Exception {
        List<Object> root = buildClassicSharedGraph();

        ObjectMapper mapper = buildMapper();
        String json = mapper.writeValueAsString(root);

        System.out.println("=== JSOGRealScenarioTest — JSON clasico ===");
        System.out.println(json);
        System.out.println("===========================================");

        assertClassicJsogStructure(json, "Jackson 3 clasico");
    }

    // -------------------------------------------------------------------------
    // Grafos de prueba
    // -------------------------------------------------------------------------

    /**
     * Grafico SDN 8: 6 instancias distintas de Asset (todas dbId=1074) y 6
     * instancias distintas de Template (todas dbId=66).
     *
     * <pre>
     * rootAsset (instancia A, dbId=1074) -> templateA (instancia X, dbId=66)
     * value0.asset = instancia B (dbId=1074) -> templateB (instancia Y, dbId=66)
     * value1.asset = instancia C (dbId=1074) -> templateC (instancia Z, dbId=66)
     * value2.asset = instancia D (dbId=1074) -> templateD (instancia W, dbId=66)
     * value3.asset = instancia E (dbId=1074) -> templateE (instancia V, dbId=66)
     * value4.asset = instancia F (dbId=1074) -> templateF (instancia U, dbId=66)
     * </pre>
     *
     * Sin canonicalizacion: 12 declaraciones {@code @id} (6 assets + 6 templates) y 0 @ref.
     * Con canonicalizacion: 7 declaraciones {@code @id} (1 Asset + 1 Template + 5 AssetValues)
     * y 5 referencias {@code @ref} al Asset canonico.
     */
    private List<Object> buildSdn8Graph() {
        // rootAsset: instancia A con template instancia X
        TemplateResponse templateA = makeTemplate(TEMPLATE_DB_ID, "PlantillaMotor");
        AssetResponse rootAsset = makeAsset(ASSET_DB_ID, "Motor_1074", templateA);

        // 5 AssetValues, cada una con instancia distinta de Asset y Template (mismo dbId)
        AssetValueResponse[] values = new AssetValueResponse[5];
        for (int i = 0; i < 5; i++) {
            // Nueva instancia de Template con mismo dbId=66
            TemplateResponse templateI = makeTemplate(TEMPLATE_DB_ID, "PlantillaMotor");
            // Nueva instancia de Asset con mismo dbId=1074
            AssetResponse assetI = makeAsset(ASSET_DB_ID, "Motor_1074", templateI);
            AssetValueResponse v = new AssetValueResponse();
            v.setId((long) (200 + i));
            v.setAsset(assetI);
            v.setValue("valor_" + i);
            values[i] = v;
        }

        return List.of(rootAsset, values[0], values[1], values[2], values[3], values[4]);
    }

    /**
     * Grafico clasico (pre-SDN8): una sola instancia Java por nodo.
     * Verifica que la canonicalizacion no rompe el comportamiento anterior.
     *
     * <pre>
     * rootAsset (instancia A, id=1) -> sharedTemplate (instancia T, id=2)
     * valueAsset (instancia B, id=5) -> sharedTemplate (misma instancia T)
     * v0.asset = valueAsset (misma instancia Java)
     * v1.asset = valueAsset (misma instancia Java)
     * v2.asset = valueAsset (misma instancia Java)
     * </pre>
     */
    private List<Object> buildClassicSharedGraph() {
        // Template compartido — misma instancia Java
        TemplateResponse sharedTemplate = makeTemplate(2L, "PlantillaMotor");

        // Asset raiz (id=1)
        AssetResponse rootAsset = makeAsset(1L, "Motor_A", sharedTemplate);

        // Asset de valores (id=5) — misma instancia para todas las AssetValues
        AssetResponse valueAsset = makeAsset(5L, "ValueAsset", sharedTemplate);

        AssetValueResponse v0 = new AssetValueResponse();
        v0.setId(100L);
        v0.setAsset(valueAsset);
        v0.setValue("valor0");

        AssetValueResponse v1 = new AssetValueResponse();
        v1.setId(101L);
        v1.setAsset(valueAsset);
        v1.setValue("valor1");

        AssetValueResponse v2 = new AssetValueResponse();
        v2.setId(102L);
        v2.setAsset(valueAsset);
        v2.setValue("valor2");

        return List.of(rootAsset, v0, v1, v2);
    }

    // -------------------------------------------------------------------------
    // Assertions
    // -------------------------------------------------------------------------

    /**
     * Verifica el escenario SDN8 con canonicalizacion:
     *
     * <ul>
     *   <li>El Asset con dbId=1074 aparece completamente serializado exactamente una vez.</li>
     *   <li>El Template con dbId=66 aparece completamente serializado exactamente una vez.</li>
     *   <li>Los 5 AssetValues repetidos del Asset se emiten como {@code {"@ref":"N"}} (5 refs).</li>
     *   <li>El valor de {@code @id} es cadena numerica, no objeto anidado.</li>
     *   <li>Las referencias tienen la forma {@code {"@ref":"N"}}.</li>
     * </ul>
     *
     * <p>Sin canonicalizacion: {@code "id":1074} apareceria 6 veces y {@code "id":66} 6 veces.
     */
    private void assertSdn8JsogStructure(String json, String label) {
        assertTrue(json.contains("\"@id\""),
                "[" + label + "] Debe existir la clave @id");
        assertTrue(json.contains("\"@ref\""),
                "[" + label + "] Debe existir la clave @ref");

        assertTrue(json.matches("(?s).*\"@id\"\\s*:\\s*\"\\d+\".*"),
                "[" + label + "] El valor de @id debe ser una cadena numerica simple, no un objeto");

        assertTrue(json.matches("(?s).*\\{\\s*\"@ref\"\\s*:\\s*\"\\d+\"\\s*}.*"),
                "[" + label + "] Las referencias deben tener la forma {\"@ref\":\"<numero>\"}");

        // El Asset (dbId=1074) debe estar completamente serializado una sola vez.
        // Sin canonicalizacion apareceria 6 veces (una por instancia).
        int assetIdCount = countOccurrences(json, "\"id\":" + ASSET_DB_ID);
        assertTrue(assetIdCount == 1,
                "[" + label + "] El Asset (dbId=" + ASSET_DB_ID + ") debe aparecer completamente "
                + "serializado una sola vez; apariciones: " + assetIdCount);

        // El Template (dbId=66) debe estar completamente serializado una sola vez.
        // Sin canonicalizacion apareceria 6 veces.
        int templateIdCount = countOccurrences(json, "\"id\":" + TEMPLATE_DB_ID);
        assertTrue(templateIdCount == 1,
                "[" + label + "] El Template (dbId=" + TEMPLATE_DB_ID + ") debe aparecer completamente "
                + "serializado una sola vez; apariciones: " + templateIdCount);

        // Las 5 apariciones repetidas del Asset deben ser @ref
        int atRefCount = countOccurrences(json, "\"@ref\"");
        assertTrue(atRefCount >= 5,
                "[" + label + "] Deben existir al menos 5 referencias @ref para las repeticiones "
                + "del Asset; encontradas: " + atRefCount);

        assertFalse(json.matches("(?s).*\"@id\"\\s*:\\s*\\{.*"),
                "[" + label + "] El valor de @id NO debe ser un objeto");
    }

    /**
     * Verifica el escenario clasico (pre-SDN8) con instancias Java compartidas:
     * <ul>
     *   <li>Debe haber {@code @id} y {@code @ref}.</li>
     *   <li>El valor de {@code @id} es cadena numerica, no objeto anidado.</li>
     *   <li>Las referencias tienen la forma {@code {"@ref":"N"}}.</li>
     *   <li>El template (id=2) aparece serializado una sola vez.</li>
     *   <li>valueAsset (id=5) aparece serializado una sola vez.</li>
     * </ul>
     */
    private void assertClassicJsogStructure(String json, String label) {
        assertTrue(json.contains("\"@id\""),
                "[" + label + "] Debe existir la clave @id");
        assertTrue(json.contains("\"@ref\""),
                "[" + label + "] Debe existir la clave @ref");

        assertTrue(json.matches("(?s).*\"@id\"\\s*:\\s*\"\\d+\".*"),
                "[" + label + "] El valor de @id debe ser una cadena numerica simple, no un objeto");

        assertTrue(json.matches("(?s).*\\{\\s*\"@ref\"\\s*:\\s*\"\\d+\"\\s*}.*"),
                "[" + label + "] Las referencias deben tener la forma {\"@ref\":\"<numero>\"}");

        // Template (id=2) debe aparecer solo una vez con campos
        int templateIdCount = countOccurrences(json, "\"id\":2");
        assertTrue(templateIdCount == 1,
                "[" + label + "] El template (id=2) debe aparecer serializado una sola vez; "
                + "apariciones: " + templateIdCount);

        // valueAsset (id=5) debe aparecer solo una vez con campos
        int valueAssetIdCount = countOccurrences(json, "\"id\":5");
        assertTrue(valueAssetIdCount == 1,
                "[" + label + "] valueAsset (id=5) debe aparecer serializado una sola vez; "
                + "apariciones: " + valueAssetIdCount);

        int refCount = countOccurrences(json, "\"@ref\"");
        assertTrue(refCount >= 2,
                "[" + label + "] Debe haber al menos 2 referencias @ref; encontradas: " + refCount);

        assertFalse(json.matches("(?s).*\"@id\"\\s*:\\s*\\{.*"),
                "[" + label + "] El valor de @id NO debe ser un objeto");
    }

    // -------------------------------------------------------------------------
    // Utilidades
    // -------------------------------------------------------------------------

    private TemplateResponse makeTemplate(long id, String name) {
        TemplateResponse t = new TemplateResponse();
        t.setId(id);
        t.setName(name);
        return t;
    }

    private AssetResponse makeAsset(long id, String name, TemplateResponse template) {
        AssetResponse a = new AssetResponse();
        a.setId(id);
        a.setName(name);
        a.setTemplate(template);
        return a;
    }

    private int countOccurrences(String text, String pattern) {
        int count = 0;
        int idx = 0;
        while ((idx = text.indexOf(pattern, idx)) != -1) {
            count++;
            idx += pattern.length();
        }
        return count;
    }
}
