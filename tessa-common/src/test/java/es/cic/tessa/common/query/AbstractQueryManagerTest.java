package es.cic.tessa.common.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.cic.tessa.common.exceptions.TessaException;
import es.cic.tessa.common.filter.PropertyFilter;
import es.cic.tessa.common.model.types.ArithmeticOperatorType;

class AbstractQueryManagerTest
{

    /**
     * Subclase concreta mínima para probar los métodos protegidos.
     */
    private static class ConcreteQueryManager extends AbstractQueryManager
    {

	public Map<String, Object> buildPropertiesPublic(Collection<PropertyFilter> filters)
	{

	    return buildProperties(filters);
	}

	public void addFilterIfNotNullOrEmptyPublic(Collection<?> propertyFilters, String filterName,
		List<String> orderedFilters)
	{

	    addFilterIfNotNullOrEmpty(propertyFilters, filterName, orderedFilters);
	}

	public void addFilterIfNotNullPublic(Object object, String filterName, List<String> orderedFilters)
	{

	    addFilterIfNotNull(object, filterName, orderedFilters);
	}

	public void addFilterIfNotEmptyAndPropertyNotNullPublic(Collection<?> propertyFilters, String filterName,
		List<String> orderedFilters)
	{

	    addFilterIfNotEmptyAndPropertyNotNull(propertyFilters, filterName, orderedFilters);
	}

	public boolean isEqualsPublic(Collection<?> propertyFilters)
	{

	    return isEquals(propertyFilters);
	}

	public boolean isNotNullOrEmptyFilterPublic(Collection<?> propertyFilters)
	{

	    return isNotNullOrEmptyFilter(propertyFilters);
	}
    }

    private ConcreteQueryManager manager;

    @BeforeEach
    void setUp()
    {

	manager = new ConcreteQueryManager();
    }

    // ── buildProperties ──────────────────────────────────────────────────────

    @Test
    void buildProperties_propiedadName_mapeaANameLower()
    {

	List<PropertyFilter> filters = List.of(new PropertyFilter("name", "MiValor"));

	Map<String, Object> resultado = manager.buildPropertiesPublic(filters);

	assertTrue(resultado.containsKey("nameLower"), "Debe mapear 'name' a 'nameLower'");
	assertEquals("mivalor", resultado.get("nameLower"));
    }

    @Test
    void buildProperties_propiedadValue_mapeaAValueLower()
    {

	List<PropertyFilter> filters = List.of(new PropertyFilter("value", "Texto"));

	Map<String, Object> resultado = manager.buildPropertiesPublic(filters);

	assertTrue(resultado.containsKey("valueLower"));
	assertEquals("texto", resultado.get("valueLower"));
    }

    @Test
    void buildProperties_otraPropiedadString_convierteAMinusculas()
    {

	List<PropertyFilter> filters = List.of(new PropertyFilter("nemonic", "CODIGO"));

	Map<String, Object> resultado = manager.buildPropertiesPublic(filters);

	assertTrue(resultado.containsKey("nemonic"));
	assertEquals("codigo", resultado.get("nemonic"));
    }

    @Test
    void buildProperties_valorBoolean_conviertACadena()
    {

	List<PropertyFilter> filters = List.of(new PropertyFilter("abstract", Boolean.TRUE));

	Map<String, Object> resultado = manager.buildPropertiesPublic(filters);

	assertEquals("true", resultado.get("abstract"));
    }

    @Test
    void buildProperties_valorNumerico_conviertACadena()
    {

	List<PropertyFilter> filters = List.of(new PropertyFilter("id", 42L));

	Map<String, Object> resultado = manager.buildPropertiesPublic(filters);

	assertEquals("42", resultado.get("id"));
    }

    @Test
    void buildProperties_valorInteger_conviertACadena()
    {

	List<PropertyFilter> filters = List.of(new PropertyFilter("position", 7));

	Map<String, Object> resultado = manager.buildPropertiesPublic(filters);

	assertEquals("7", resultado.get("position"));
    }

    @Test
    void buildProperties_tipoDesconocido_lanzaTessaException()
    {

	// Un objeto que no es Boolean, Number ni String
	List<PropertyFilter> filters = List.of(new PropertyFilter("campo", List.of("a", "b")));

	assertThrows(TessaException.class, () -> manager.buildPropertiesPublic(filters));
    }

    @Test
    void buildProperties_variosFilters_devuelveTodosLosMapeados()
    {

	List<PropertyFilter> filters = List.of(
		new PropertyFilter("name", "Template"),
		new PropertyFilter("type", "ASSET"));

	Map<String, Object> resultado = manager.buildPropertiesPublic(filters);

	assertEquals(2, resultado.size());
	assertTrue(resultado.containsKey("nameLower"));
	assertTrue(resultado.containsKey("type"));
    }

    // ── addFilterIfNotNullOrEmpty ─────────────────────────────────────────────

    @Test
    void addFilterIfNotNullOrEmpty_coleccionNoVacia_agregaFiltro()
    {

	List<String> orderedFilters = new ArrayList<>();

	manager.addFilterIfNotNullOrEmptyPublic(List.of("elemento"), "MiFiltro", orderedFilters);

	assertTrue(orderedFilters.contains("MiFiltro"));
    }

    @Test
    void addFilterIfNotNullOrEmpty_coleccionVacia_noAgregaFiltro()
    {

	List<String> orderedFilters = new ArrayList<>();

	manager.addFilterIfNotNullOrEmptyPublic(List.of(), "MiFiltro", orderedFilters);

	assertFalse(orderedFilters.contains("MiFiltro"));
    }

    @Test
    void addFilterIfNotNullOrEmpty_coleccionNula_noAgregaFiltro()
    {

	List<String> orderedFilters = new ArrayList<>();

	manager.addFilterIfNotNullOrEmptyPublic(null, "MiFiltro", orderedFilters);

	assertFalse(orderedFilters.contains("MiFiltro"));
    }

    @Test
    void addFilterIfNotNullOrEmpty_filtroYaPresente_noLosDuplica()
    {

	List<String> orderedFilters = new ArrayList<>();
	orderedFilters.add("MiFiltro");

	manager.addFilterIfNotNullOrEmptyPublic(List.of("elemento"), "MiFiltro", orderedFilters);

	assertEquals(1, orderedFilters.stream().filter("MiFiltro"::equals).count(),
		"No debe duplicar un filtro ya presente");
    }

    // ── addFilterIfNotNull ────────────────────────────────────────────────────

    @Test
    void addFilterIfNotNull_objetoNoNulo_agregaFiltro()
    {

	List<String> orderedFilters = new ArrayList<>();

	manager.addFilterIfNotNullPublic("valor", "MiFiltro", orderedFilters);

	assertTrue(orderedFilters.contains("MiFiltro"));
    }

    @Test
    void addFilterIfNotNull_objetoNulo_noAgregaFiltro()
    {

	List<String> orderedFilters = new ArrayList<>();

	manager.addFilterIfNotNullPublic(null, "MiFiltro", orderedFilters);

	assertFalse(orderedFilters.contains("MiFiltro"));
    }

    @Test
    void addFilterIfNotNull_filtroYaPresente_noLosDuplica()
    {

	List<String> orderedFilters = new ArrayList<>();
	orderedFilters.add("MiFiltro");

	manager.addFilterIfNotNullPublic("valor", "MiFiltro", orderedFilters);

	assertEquals(1, orderedFilters.stream().filter("MiFiltro"::equals).count());
    }

    // ── addFilterIfNotEmptyAndPropertyNotNull ────────────────────────────────

    @Test
    void addFilterIfNotEmptyAndPropertyNotNull_propertyFilterValido_agregaFiltro()
    {

	List<String> orderedFilters = new ArrayList<>();
	List<PropertyFilter> filters = List.of(new PropertyFilter("name", "Valor"));

	manager.addFilterIfNotEmptyAndPropertyNotNullPublic(filters, "MiFiltro", orderedFilters);

	assertTrue(orderedFilters.contains("MiFiltro"));
    }

    @Test
    void addFilterIfNotEmptyAndPropertyNotNull_propertyFilterConNombreNulo_noAgregaFiltro()
    {

	List<String> orderedFilters = new ArrayList<>();
	PropertyFilter pf = new PropertyFilter();
	pf.setPropertyName(null);
	pf.setPropertyValue("valor");

	manager.addFilterIfNotEmptyAndPropertyNotNullPublic(List.of(pf), "MiFiltro", orderedFilters);

	assertFalse(orderedFilters.contains("MiFiltro"));
    }

    @Test
    void addFilterIfNotEmptyAndPropertyNotNull_propertyFilterConValorNulo_noAgregaFiltro()
    {

	List<String> orderedFilters = new ArrayList<>();
	PropertyFilter pf = new PropertyFilter();
	pf.setPropertyName("name");
	pf.setPropertyValue(null);

	manager.addFilterIfNotEmptyAndPropertyNotNullPublic(List.of(pf), "MiFiltro", orderedFilters);

	assertFalse(orderedFilters.contains("MiFiltro"));
    }

    @Test
    void addFilterIfNotEmptyAndPropertyNotNull_coleccionVacia_noAgregaFiltro()
    {

	List<String> orderedFilters = new ArrayList<>();

	manager.addFilterIfNotEmptyAndPropertyNotNullPublic(List.of(), "MiFiltro", orderedFilters);

	assertFalse(orderedFilters.contains("MiFiltro"));
    }

    // ── isEquals ─────────────────────────────────────────────────────────────

    @Test
    void isEquals_conOperadorEquals_devuelveTrue()
    {

	PropertyFilter pf = new PropertyFilter("name", "Valor", ArithmeticOperatorType.EQUALS);
	List<PropertyFilter> filters = List.of(pf);

	assertTrue(manager.isEqualsPublic(filters));
    }

    @Test
    void isEquals_sinOperadorEquals_devuelveFalse()
    {

	PropertyFilter pf = new PropertyFilter("name", "Valor", ArithmeticOperatorType.CONTAINS);
	List<PropertyFilter> filters = List.of(pf);

	assertFalse(manager.isEqualsPublic(filters));
    }

    @Test
    void isEquals_operadorNulo_devuelveFalse()
    {

	PropertyFilter pf = new PropertyFilter();
	pf.setPropertyName("name");
	pf.setArithmeticOperatorType(null);
	List<PropertyFilter> filters = List.of(pf);

	assertFalse(manager.isEqualsPublic(filters));
    }

    // ── isNotNullOrEmptyFilter ────────────────────────────────────────────────

    @Test
    void isNotNullOrEmptyFilter_coleccionConElementos_devuelveTrue()
    {

	assertTrue(manager.isNotNullOrEmptyFilterPublic(List.of("elemento")));
    }

    @Test
    void isNotNullOrEmptyFilter_coleccionVacia_devuelveFalse()
    {

	assertFalse(manager.isNotNullOrEmptyFilterPublic(List.of()));
    }

    @Test
    void isNotNullOrEmptyFilter_coleccionNula_devuelveFalse()
    {

	assertFalse(manager.isNotNullOrEmptyFilterPublic(null));
    }
}
