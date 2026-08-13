package es.cic.tessa.rest.conf;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.cic.tessa.common.support.PageConfig;

class RestClientConfigTest
{

    private RestClientConfig config;

    private static final String PROTOCOL = "http";
    private static final String HOST     = "localhost";
    private static final String PORT     = "8080";
    private static final String CONTEXT  = "tessa-core";

    private Set<String> groups;

    @BeforeEach
    void setUp()
    {

	config = new RestClientConfig();
	config.setProtocol(PROTOCOL);
	config.setHost(HOST);
	config.setPort(PORT);
	config.setContext(CONTEXT);

	groups = new LinkedHashSet<>();
	groups.add("System");
    }

    private String baseUrl()
    {

	return PROTOCOL + "://" + HOST + ":" + PORT + "/" + CONTEXT + "/";
    }

    // ── Asset ────────────────────────────────────────────────────────────────

    @Test
    void buildUriAssetGet_contieneBaseUrlEId()
    {

	String uri = config.buildUriAssetGet(1L, groups);

	assertTrue(uri.startsWith(baseUrl()));
	assertTrue(uri.contains("asset"));
	assertTrue(uri.contains("1"));
	assertTrue(uri.contains("groups=System"));
    }

    @Test
    void buildUriAssetSearch_sinPageConfig_contieneRutaBusqueda()
    {

	String uri = config.buildUriAssetSearch(null, groups);

	assertTrue(uri.startsWith(baseUrl()));
	assertTrue(uri.contains("asset/search"));
	assertTrue(uri.contains("groups=System"));
    }

    @Test
    void buildUriAssetSearch_conPageConfig_contieneParametrosPaginacion()
    {

	PageConfig pageConfig = new PageConfig(2, 10);

	String uri = config.buildUriAssetSearch(pageConfig, groups);

	assertTrue(uri.contains("numPage=2"));
	assertTrue(uri.contains("sizePage=10"));
    }

    @Test
    void buildUriAssetFullGet_contieneRutaFull()
    {

	String uri = config.buildUriAssetFullGet(5L, groups);

	assertTrue(uri.startsWith(baseUrl()));
	assertTrue(uri.contains("asset/full"));
	assertTrue(uri.contains("5"));
    }

    @Test
    void buildUriAssetFullSearch_contieneRutaSearchgrid()
    {

	String uri = config.buildUriAssetFullSearch(null, groups);

	assertTrue(uri.contains("searchgrid"));
    }

    @Test
    void buildUriAssetCreateUpdate_contieneRutaAsset()
    {

	String uri = config.buildUriAssetCreateUpdate(groups);

	assertTrue(uri.startsWith(baseUrl()));
	assertTrue(uri.contains("asset"));
	assertTrue(uri.contains("groups=System"));
    }

    // ── AssetValue ───────────────────────────────────────────────────────────

    @Test
    void buildUriAssetValueGetDelete_contieneIdYRuta()
    {

	String uri = config.buildUriAssetValueGetDelete(10L, groups);

	assertTrue(uri.contains("assetvalue"));
	assertTrue(uri.contains("10"));
	assertTrue(uri.contains("groups=System"));
    }

    @Test
    void buildUriAssetValueSearch_sinPageConfig_contieneRutaBusqueda()
    {

	String uri = config.buildUriAssetValueSearch(null, groups);

	assertTrue(uri.contains("assetvalue/search"));
    }

    @Test
    void buildUriAssetValueSearch_conPageConfig_contieneParametros()
    {

	PageConfig pageConfig = new PageConfig(1, 5);

	String uri = config.buildUriAssetValueSearch(pageConfig, groups);

	assertTrue(uri.contains("numPage=1"));
	assertTrue(uri.contains("sizePage=5"));
    }

    @Test
    void buildUriAssetValueCreateUpdate_contieneRutaAssetValue()
    {

	String uri = config.buildUriAssetValueCreateUpdate(groups);

	assertTrue(uri.contains("assetvalue"));
    }

    @Test
    void buildUriAssetValueAssignAsset_contieneIdAsset()
    {

	String uri = config.buildUriAssetValueAssignAsset(7L, groups);

	assertTrue(uri.contains("7"));
	assertTrue(uri.contains("assetvalues"));
    }

    // ── Organizer ────────────────────────────────────────────────────────────

    @Test
    void buildUriOrganizerSearch_contieneRutaOrganizer()
    {

	String uri = config.buildUriOrganizerSearch(null, groups);

	assertTrue(uri.contains("organizer"));
	assertTrue(uri.contains("groups=System"));
    }

    @Test
    void buildUriAssetAssignOrganizers_contieneRutaAssign()
    {

	String uri = config.buildUriAssetAssignOrganizers(groups);

	assertTrue(uri.contains("organizers"));
	assertTrue(uri.contains("groups=System"));
    }

    // ── Template ─────────────────────────────────────────────────────────────

    @Test
    void buildUriTemplateGet_contieneRutaTemplateEId()
    {

	String uri = config.buildUriTemplateGet(3L, groups);

	assertTrue(uri.startsWith(baseUrl()));
	assertTrue(uri.contains("template"));
	assertTrue(uri.contains("3"));
    }

    @Test
    void buildUriTemplateSearch_contieneRutaBusqueda()
    {

	String uri = config.buildUriTemplateSearch(null, groups);

	assertTrue(uri.contains("template/search"));
	assertTrue(uri.contains("groups=System"));
    }

    // ── TemplateAttribute ─────────────────────────────────────────────────────

    @Test
    void buildUriTemplateAttributeGet_contieneRutaEId()
    {

	String uri = config.buildUriTemplateAttributeGet(8L, groups);

	assertTrue(uri.contains("templateattribute"));
	assertTrue(uri.contains("8"));
    }

    @Test
    void buildUriTemplateAttributeSearch_contieneRutaBusqueda()
    {

	String uri = config.buildUriTemplateAttributeSearch(null, groups);

	assertTrue(uri.contains("templateattribute/search"));
    }

    @Test
    void buildUriTemplateAttributeCollectionMappingGet_contieneRuta()
    {

	String uri = config.buildUriTemplateAttributeCollectionMappingGet(2L, groups);

	assertTrue(uri.contains("templateattributecollectionmapping"));
	assertTrue(uri.contains("2"));
    }

    @Test
    void buildUriTemplateAttributeCollectionMappingSearch_contieneRutaBusqueda()
    {

	String uri = config.buildUriTemplateAttributeCollectionMappingSearch(null, groups);

	assertTrue(uri.contains("templateattributecollectionmapping/search"));
    }

    @Test
    void buildUriTemplateAttributeExpressionParamSearch_contieneRutaExpressionParam()
    {

	String uri = config.buildUriTemplateAttributeExpressionParamSearch(null, groups);

	assertTrue(uri.contains("expressionparam/search"));
    }

    // ── Hashtag ───────────────────────────────────────────────────────────────

    @Test
    void buildUriHashtagGet_contieneRutaEId()
    {

	String uri = config.buildUriHashtagGet(4L, groups);

	assertTrue(uri.contains("hashtag"));
	assertTrue(uri.contains("4"));
    }

    @Test
    void buildUriHashtagSearch_contieneRutaBusqueda()
    {

	String uri = config.buildUriHashtagSearch(null, groups);

	assertTrue(uri.contains("hashtag/search"));
	assertTrue(uri.contains("groups=System"));
    }

    // ── Getters y setters ────────────────────────────────────────────────────

    @Test
    void getters_devuelvenValoresEstablecidos()
    {

	assertTrue(PROTOCOL.equals(config.getProtocol()));
	assertTrue(HOST.equals(config.getHost()));
	assertTrue(PORT.equals(config.getPort()));
	assertTrue(CONTEXT.equals(config.getContext()));
    }
}
