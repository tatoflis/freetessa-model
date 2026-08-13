package es.cic.tessa.rest.conf;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LookupRestClientConfigTest
{

    private LookupRestClientConfig config;

    private Set<String> groups;

    @BeforeEach
    void setUp()
    {

	config = new LookupRestClientConfig();
	config.setLookupProtocol("http");
	config.setLookupHost("lookup-host");
	config.setLookupPort(9090);
	config.setLookupContext("tessa-lookup");

	groups = new LinkedHashSet<>();
	groups.add("System");
    }

    private String baseUrl()
    {

	return "http://lookup-host:9090/tessa-lookup/";
    }

    // ── buildUriLookupResolveAssetValue ──────────────────────────────────────

    @Test
    void buildUriLookupResolveAssetValue_sinSynteticAssetId_contieneIdYParams()
    {

	String uri = config.buildUriLookupResolveAssetValue(42L, null, true, false, groups);

	assertTrue(uri.startsWith(baseUrl()));
	assertTrue(uri.contains("lookup"));
	assertTrue(uri.contains("42"));
	assertTrue(uri.contains("stringExpression=true"));
	assertTrue(uri.contains("async=false"));
	assertTrue(uri.contains("groups=System"));
	assertFalse(uri.contains("synteticAssetId"), "No debe incluir synteticAssetId si es nulo");
    }

    @Test
    void buildUriLookupResolveAssetValue_conSynteticAssetId_contieneIdSintetico()
    {

	String uri = config.buildUriLookupResolveAssetValue(42L, 99L, false, true, groups);

	assertTrue(uri.contains("42"));
	assertTrue(uri.contains("synteticAssetId=99"));
	assertTrue(uri.contains("stringExpression=false"));
	assertTrue(uri.contains("async=true"));
    }

    @Test
    void buildUriLookupResolveAssetValue_async_paramEsCorrecto()
    {

	String uri = config.buildUriLookupResolveAssetValue(1L, null, true, true, groups);

	assertTrue(uri.contains("async=true"));
    }

    // ── buildUriLookupResolveExpression ──────────────────────────────────────

    @Test
    void buildUriLookupResolveExpression_sinSynteticAssetId_contieneRutaExpression()
    {

	String uri = config.buildUriLookupResolveExpression(10L, null, groups);

	assertTrue(uri.startsWith(baseUrl()));
	assertTrue(uri.contains("lookup/expression"));
	assertTrue(uri.contains("10"));
	assertTrue(uri.contains("groups=System"));
	assertFalse(uri.contains("synteticAssetId"), "No debe incluir synteticAssetId si es nulo");
    }

    @Test
    void buildUriLookupResolveExpression_conSynteticAssetId_contieneIdSintetico()
    {

	String uri = config.buildUriLookupResolveExpression(10L, 77L, groups);

	assertTrue(uri.contains("lookup/expression"));
	assertTrue(uri.contains("10"));
	assertTrue(uri.contains("synteticAssetId=77"));
    }

    // ── Getters y setters ─────────────────────────────────────────────────────

    @Test
    void getters_devuelvenValoresEstablecidos()
    {

	assertTrue("http".equals(config.getLookupProtocol()));
	assertTrue("lookup-host".equals(config.getLookupHost()));
	assertTrue(9090 == config.getLookupPort());
	assertTrue("tessa-lookup".equals(config.getLookupContext()));
    }

    @Test
    void setters_actualizanValores()
    {

	config.setLookupProtocol("https");
	config.setLookupHost("nuevo-host");
	config.setLookupPort(8443);
	config.setLookupContext("nuevo-contexto");

	assertTrue("https".equals(config.getLookupProtocol()));
	assertTrue("nuevo-host".equals(config.getLookupHost()));
	assertTrue(8443 == config.getLookupPort());
	assertTrue("nuevo-contexto".equals(config.getLookupContext()));
    }
}
