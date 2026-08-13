package es.cic.tessa.model.jsog;


import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.dto.AssetResponse;
import es.cic.tessa.model.dto.AssetValueResponse;
import es.cic.tessa.model.dto.TemplateResponse;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;


/**
 * Verifica que la serializacion JSOG con JSOGGenerator produce: - Primera
 * aparicion: {"@id": "1", ...campos...} - Aparicion repetida: {"@ref": "1"}
 */
class JSOGSerializationTest
{

    @Test
    void serializaReferenciasCircularesConFormatoJSOG() throws Exception
    {

	// --- Arrange ---------------------------------------------------------
	// Template compartido por dos assets
	TemplateResponse template = new TemplateResponse();
	template.setId(10L);
	template.setName("PlantillaMotor");

	// Asset 1 apunta al template
	AssetResponse asset1 = new AssetResponse();
	asset1.setId(1L);
	asset1.setName("Motor A");
	asset1.setTemplate(template);

	// AssetValue que tiene back-reference al mismo asset1
	AssetValueResponse val1 = new AssetValueResponse();
	val1.setId(100L);
	val1.setAsset(asset1); // referencia circular: valor → asset → valor…
	val1.setValue("3000 RPM");

	List<AssetValueResponse> values = new ArrayList<>();
	values.add(val1);
	asset1.setValues(values);

	// Asset 2 reutiliza el mismo template → debe aparecer como @ref
	AssetResponse asset2 = new AssetResponse();
	asset2.setId(2L);
	asset2.setName("Motor B");
	asset2.setTemplate(template);

	List<AssetResponse> assets = new ArrayList<>();
	assets.add(asset1);
	assets.add(asset2);

	// --- Act -------------------------------------------------------------
	// JSOGModule debe estar registrado — sin el, Jackson serializa JSOGRef
	// como POJO
	ObjectMapper mapper = JsonMapper.builder().addModule(new JSOGModule()).build();
	String json = mapper.writeValueAsString(assets);

	// --- Assert ----------------------------------------------------------
	System.out.println("JSON JSOG producido:");
	System.out.println(json);

	// El template debe aparecer con @id la primera vez
	assertTrue(json.contains("\"@id\""), "Debe existir la clave @id");
	// El template debe aparecer como referencia la segunda vez
	assertTrue(json.contains("\"@ref\""), "Debe existir la clave @ref para la referencia repetida");
	// El valor escalar de @id debe ser una cadena numerica (no objeto
	// anidado)
	// Por ejemplo: "@id":"1" (no "@id":{"@ref":"..."})
	assertTrue(json.matches("(?s).*\"@id\"\\s*:\\s*\"\\d+\".*"), "El valor de @id debe ser una cadena numerica simple, no un objeto");
	// El @ref debe ser un objeto con clave @ref y valor string numerico
	assertTrue(json.matches("(?s).*\\{\\s*\"@ref\"\\s*:\\s*\"\\d+\"\\s*}.*"), "La referencia debe tener la forma {\"@ref\":\"<numero>\"}");
    }
}
