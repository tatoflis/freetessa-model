package es.cic.tessa.rest.conf;


import java.util.Set;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import es.cic.tessa.common.support.PageConfig;


@Component
public class RestClientConfig extends AbtractClientConfig
{

    private static String API_URI = "api/v1";

    public static String TEMPLATE_GET_URI = API_URI + "/template";

    public static String TEMPLATE_SEARCH_URI = API_URI + "/template/search?";

    public static String ASSET_GET_URI = API_URI + "/asset";

    public static String ASSET_SEARCH_URI = API_URI + "/asset/search?";

    public static String ASSET_FULL_GET_URI = API_URI + "/asset/full";

    public static String ASSET_FULL_SEARCH_URI = API_URI + "/asset/searchgrid?";

    public static String ASSETVALUE_GET_DELETE_URI = API_URI + "/assetvalue";

    public static String ASSETVALUE_SEARCH_URI = API_URI + "/assetvalue/search?";

    public static String ASSET_ASSIGN_VALUES_URI = API_URI + "/asset/assign/{id}/assetvalues";

    public static String ORGNANIZER_GET_URI = API_URI + "/organizer";

    public static String ORGNANIZER_SEARCH_URI = API_URI + "/organizer/search?";

    public static String ASSET_ASSIGN_ORGANIZERS_URI = API_URI + "/asset/assign/organizers";

    public static String TEMPLATEATTRIBUTE_GET_URI = API_URI + "/templateattribute";

    public static String TEMPLATEATTRIBUTE_SEARCH_URI = API_URI + "/templateattribute/search?";

    public static String TEMPLATEATTRIBUTECOLLECTIONMAPPING_GET_URI = API_URI + "/templateattributecollectionmapping";

    public static String TEMPLATEATTRIBUTECOLLECTIONMAPPING_SEARCH_URI = API_URI + "/templateattributecollectionmapping/search?";

    public static String TEMPLATEATTRIBUTEEXPRESSIONPARAM_SEARCH_URI = API_URI + "/expressionparam/search?";

    public static String HASHTAG_GET_URI = API_URI + "/hashtag";

    public static String HASHTAG_SEARCH_URI = API_URI + "/hashtag/search?";

    @Value("${tessa.rest.client.protocol:#{null}}")
    private String protocol;

    @Value("${tessa.rest.client.host:#{null}}")
    private String host;

    @Value("${tessa.rest.client.port:#{null}}")
    private String port;

    @Value("${tessa.rest.client.context:#{null}}")
    private String context;

    public String buildUriTemplateGet(Long id, Set<String> groups)
    {

	String groupsAsString = buildGroups(groups);

	StringBuilder uri = new StringBuilder();

	return uri.append(protocol).append("://").append(host).append(":").append(port).append("/").append(context).append("/").append(RestClientConfig.TEMPLATE_GET_URI).append(id + "?" + groupsAsString).toString();

    }


    public String buildUriTemplateSearch(PageConfig pageConfig, Set<String> groups)
    {

	String groupsAsString = buildGroups(groups);

	StringBuilder uri = new StringBuilder();

	return uri.append(protocol).append("://").append(host).append(":").append(port).append("/").append(context).append("/").append(RestClientConfig.TEMPLATE_SEARCH_URI).append(groupsAsString).toString();

    }


    public String buildUriHashtagGet(Long id, Set<String> groups)
    {

	String groupsAsString = buildGroups(groups);

	StringBuilder uri = new StringBuilder();

	return uri.append(protocol).append("://").append(host).append(":").append(port).append("/").append(context).append("/").append(RestClientConfig.HASHTAG_GET_URI).append(id + "?" + groupsAsString).toString();

    }


    public String buildUriHashtagSearch(PageConfig pageConfig, Set<String> groups)
    {

	String groupsAsString = buildGroups(groups);

	StringBuilder uri = new StringBuilder();

	return uri.append(protocol).append("://").append(host).append(":").append(port).append("/").append(context).append("/").append(RestClientConfig.HASHTAG_SEARCH_URI).append(groupsAsString).toString();

    }


    public String buildUriTemplateAttributeGet(Long id, Set<String> groups)
    {

	String groupsAsString = buildGroups(groups);

	StringBuilder uri = new StringBuilder();

	return uri.append(protocol).append("://").append(host).append(":").append(port).append("/").append(context).append("/").append(RestClientConfig.TEMPLATEATTRIBUTE_GET_URI).append(id + "?" + groupsAsString).toString();

    }


    public String buildUriTemplateAttributeSearch(PageConfig pageConfig, Set<String> groups)
    {

	String groupsAsString = buildGroups(groups);

	StringBuilder uri = new StringBuilder();

	return uri.append(protocol).append("://").append(host).append(":").append(port).append("/").append(context).append("/").append(RestClientConfig.TEMPLATEATTRIBUTE_SEARCH_URI).append(groupsAsString).toString();

    }


    public String buildUriTemplateAttributeCollectionMappingGet(Long id, Set<String> groups)
    {

	String groupsAsString = buildGroups(groups);

	StringBuilder uri = new StringBuilder();

	return uri.append(protocol).append("://").append(host).append(":").append(port).append("/").append(context).append("/").append(RestClientConfig.TEMPLATEATTRIBUTECOLLECTIONMAPPING_GET_URI).append(id + "?" + groupsAsString).toString();

    }


    public String buildUriTemplateAttributeCollectionMappingSearch(PageConfig pageConfig, Set<String> groups)
    {

	String groupsAsString = buildGroups(groups);

	StringBuilder uri = new StringBuilder();

	return uri.append(protocol).append("://").append(host).append(":").append(port).append("/").append(context).append("/").append(RestClientConfig.TEMPLATEATTRIBUTECOLLECTIONMAPPING_SEARCH_URI).append(groupsAsString).toString();

    }


    public String buildUriAssetGet(Long id, Set<String> groups)
    {

	String groupsAsString = buildGroups(groups);

	StringBuilder uri = new StringBuilder();

	return uri.append(protocol).append("://").append(host).append(":").append(port).append("/").append(context).append("/").append(RestClientConfig.ASSET_GET_URI).append("/" + id + "?" + groupsAsString).toString();

    }


    public String buildUriAssetSearch(PageConfig pageConfig, Set<String> groups)
    {

	String groupsAsString = buildGroups(groups);

	String pageConfigAsString = Strings.EMPTY;

	if(pageConfig != null)
	{
	    pageConfigAsString = buildPageConfig(pageConfig);
	}

	StringBuilder uri = new StringBuilder();

	return uri.append(protocol).append("://").append(host).append(":").append(port).append("/").append(context).append("/").append(RestClientConfig.ASSET_SEARCH_URI).append(pageConfigAsString).append(groupsAsString).toString();

    }


    public String buildUriAssetFullGet(Long id, Set<String> groups)
    {

	String groupsAsString = buildGroups(groups);

	StringBuilder uri = new StringBuilder();

	return uri.append(protocol).append("://").append(host).append(":").append(port).append("/").append(context).append("/").append(RestClientConfig.ASSET_FULL_GET_URI).append("/" + id + "?" + groupsAsString).toString();

    }


    public String buildUriAssetFullSearch(PageConfig pageConfig, Set<String> groups)
    {

	String groupsAsString = buildGroups(groups);

	String pageConfigAsString = Strings.EMPTY;

	if(pageConfig != null)
	{
	    pageConfigAsString = buildPageConfig(pageConfig);
	}

	StringBuilder uri = new StringBuilder();

	return uri.append(protocol).append("://").append(host).append(":").append(port).append("/").append(context).append("/").append(RestClientConfig.ASSET_FULL_SEARCH_URI).append(pageConfigAsString).append(groupsAsString).toString();

    }


    public String buildUriAssetCreateUpdate(Set<String> groups)
    {

	String groupsAsString = buildGroups(groups);

	StringBuilder uri = new StringBuilder();

	return uri.append(protocol).append("://").append(host).append(":").append(port).append("/").append(context).append("/").append(RestClientConfig.ASSET_GET_URI).append("?" + groupsAsString).toString();

    }


    public String buildUriAssetValueGetDelete(Long id, Set<String> groups)
    {

	String groupsAsString = buildGroups(groups);

	StringBuilder uri = new StringBuilder();

	return uri.append(protocol).append("://").append(host).append(":").append(port).append("/").append(context).append("/").append(RestClientConfig.ASSETVALUE_GET_DELETE_URI).append("/" + id + "?" + groupsAsString).toString();

    }


    public String buildUriAssetValueSearch(PageConfig pageConfig, Set<String> groups)
    {

	String groupsAsString = buildGroups(groups);

	String pageConfigAsString = Strings.EMPTY;
	if(pageConfig != null)
	{
	    pageConfigAsString = buildPageConfig(pageConfig);
	}

	StringBuilder uri = new StringBuilder();

	return uri.append(protocol).append("://").append(host).append(":").append(port).append("/").append(context).append("/").append(RestClientConfig.ASSETVALUE_SEARCH_URI).append(pageConfigAsString).append(groupsAsString).toString();

    }


    public String buildUriAssetValueAssignAsset(Long idAsset, Set<String> groups)
    {

	String groupsAsString = buildGroups(groups);

	StringBuilder uri = new StringBuilder();

	String assing = ASSET_ASSIGN_VALUES_URI.replace("{id}", String.valueOf(idAsset));

	return uri.append(protocol).append("://").append(host).append(":").append(port).append("/").append(context).append("/").append(assing).append("?" + groupsAsString).toString();

    }


    public String buildUriOrganizerSearch(PageConfig pageConfig, Set<String> groups)
    {

	String groupsAsString = buildGroups(groups);

	StringBuilder uri = new StringBuilder();

	return uri.append(protocol).append("://").append(host).append(":").append(port).append("/").append(context).append("/").append(RestClientConfig.ORGNANIZER_SEARCH_URI).append(groupsAsString).toString();

    }


    public String buildUriAssetValueCreateUpdate(Set<String> groups)
    {

	String groupsAsString = buildGroups(groups);

	StringBuilder uri = new StringBuilder();

	return uri.append(protocol).append("://").append(host).append(":").append(port).append("/").append(context).append("/").append(RestClientConfig.ASSETVALUE_GET_DELETE_URI).append(groupsAsString).toString();

    }


    public String buildUriAssetAssignOrganizers(Set<String> groups)
    {

	String groupsAsString = buildGroups(groups);

	StringBuilder uri = new StringBuilder();

	return uri.append(protocol).append("://").append(host).append(":").append(port).append("/").append(context).append("/").append(RestClientConfig.ASSET_ASSIGN_ORGANIZERS_URI).append("?" + groupsAsString).toString();

    }


    public String buildUriTemplateAttributeExpressionParamSearch(PageConfig pageConfig, Set<String> groups)
    {

	String groupsAsString = buildGroups(groups);

	StringBuilder uri = new StringBuilder();

	return uri.append(protocol).append("://").append(host).append(":").append(port).append("/").append(context).append("/").append(RestClientConfig.TEMPLATEATTRIBUTEEXPRESSIONPARAM_SEARCH_URI).append(groupsAsString).toString();

    }


    private String buildPageConfig(PageConfig pageConfig)
    {

	StringBuilder pageConfigAsString = new StringBuilder();
	pageConfigAsString.append("numPage=");
	pageConfigAsString.append(pageConfig.getNumPage());
	pageConfigAsString.append("&sizePage=");
	pageConfigAsString.append(pageConfig.getSizePage());
	pageConfigAsString.append("&");

	return pageConfigAsString.toString();
    }


    public String getProtocol()
    {

	return protocol;
    }


    public void setProtocol(String protocol)
    {

	this.protocol = protocol;
    }


    public String getHost()
    {

	return host;
    }


    public void setHost(String host)
    {

	this.host = host;
    }


    public String getPort()
    {

	return port;
    }


    public void setPort(String port)
    {

	this.port = port;
    }


    public String getContext()
    {

	return context;
    }


    public void setContext(String context)
    {

	this.context = context;
    }

}
