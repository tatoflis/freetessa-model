package es.cic.tessa.rest.conf;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class AbtractClientConfigTest
{

    /**
     * Subclase concreta para exponer el método protegido buildGroups.
     */
    private static class TestableClientConfig extends AbtractClientConfig
    {

	public String buildGroupsPublic(Set<String> groups)
	{

	    return buildGroups(groups);
	}
    }

    private TestableClientConfig config;

    @BeforeEach
    void setUp()
    {

	config = new TestableClientConfig();
    }


    @Test
    void buildGroups_unGrupo_devuelveParametroCorrecto()
    {

	String resultado = config.buildGroupsPublic(Set.of("System"));

	assertEquals("groups=System", resultado);
    }


    @Test
    void buildGroups_dosGrupos_devuelveParametrosSeparados()
    {

	// LinkedHashSet para orden de inserción determinista
	Set<String> grupos = new LinkedHashSet<>();
	grupos.add("GrupoA");
	grupos.add("GrupoB");

	String resultado = config.buildGroupsPublic(grupos);

	assertEquals("groups=GrupoA&groups=GrupoB", resultado);
    }


    @Test
    void buildGroups_tresGrupos_concatenaCorrectamente()
    {

	Set<String> grupos = new LinkedHashSet<>();
	grupos.add("G1");
	grupos.add("G2");
	grupos.add("G3");

	String resultado = config.buildGroupsPublic(grupos);

	assertEquals("groups=G1&groups=G2&groups=G3", resultado);
    }


    @Test
    void buildGroups_unGrupo_noAcabaConAmpersand()
    {

	String resultado = config.buildGroupsPublic(Set.of("MiGrupo"));

	assertTrue(!resultado.endsWith("&"), "El resultado no debe terminar con '&'");
    }


    @Test
    void buildGroups_variosGrupos_noAcabaConAmpersand()
    {

	Set<String> grupos = new LinkedHashSet<>();
	grupos.add("G1");
	grupos.add("G2");

	String resultado = config.buildGroupsPublic(grupos);

	assertTrue(!resultado.endsWith("&"), "El resultado no debe terminar con '&'");
    }


    @Test
    void buildGroups_conjuntoVacio_devuelveCadenaVacia()
    {

	String resultado = config.buildGroupsPublic(Set.of());

	assertEquals("", resultado);
    }
}
