package es.cic.tessa.rest.conf;


import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class LookupRestClientConfig extends AbtractClientConfig
{

    private static final String API_URI = "api/v1";

    public static final String LOOKUP_RESOLVE_ASSET_VALUE_URI = API_URI + "/lookup";
    public static final String LOOKUP_RESOLVE_EXPRESSION_URI = API_URI + "/lookup/expression";

    @Value("${tessa.rest.lookup.client.protocol:#{null}}")
    private String lookupProtocol;

    @Value("${tessa.rest.lookup.client.host:#{null}}")
    private String lookupHost;

    @Value("${tessa.rest.lookup.client.port:#{null}}")
    private Integer lookupPort;

    @Value("${tessa.rest.lookup.client.context:#{null}}")
    private String lookupContext;

    public LookupRestClientConfig()
    {

    }


    public LookupRestClientConfig(String protocol, String host, Integer port, String context)
    {

	this.lookupProtocol = protocol;
	this.lookupContext = host;
	this.lookupPort = port;
	this.lookupContext = context;
    }


    public String buildUriLookupResolveAssetValue(Long assetValueId, Long synteticAssetId, Boolean stringExpression, Boolean async, Set<String> groups)
    {

	String groupsAsString = buildGroups(groups);

	StringBuilder uri = new StringBuilder();

	if(synteticAssetId == null)
	{
	    return uri.append(lookupProtocol).append("://").append(lookupHost).append(":").append(lookupPort).append("/").append(lookupContext).append("/").append(LOOKUP_RESOLVE_ASSET_VALUE_URI).append("/" + assetValueId + "?stringExpression=" + stringExpression + "&async=" + async + "&" + groupsAsString).toString();

	}
	else
	{
	    return uri.append(lookupProtocol).append("://").append(lookupHost).append(":").append(lookupPort).append("/").append(lookupContext).append("/").append(LOOKUP_RESOLVE_ASSET_VALUE_URI).append("/" + assetValueId + "?synteticAssetId=" + synteticAssetId + "&stringExpression=" + stringExpression + "&async=" + async + "&" + groupsAsString).toString();

	}

    }


    public String buildUriLookupResolveExpression(Long assetValueId, Long synteticAssetId, Set<String> groups)
    {

	String groupsAsString = buildGroups(groups);

	StringBuilder uri = new StringBuilder();

	if(synteticAssetId == null)
	{
	    return uri.append(lookupProtocol).append("://").append(lookupHost).append(":").append(lookupPort).append("/").append(lookupContext).append("/").append(LOOKUP_RESOLVE_EXPRESSION_URI).append("/" + assetValueId + "?" + groupsAsString).toString();

	}
	else
	{
	    return uri.append(lookupProtocol).append("://").append(lookupHost).append(":").append(lookupPort).append("/").append(lookupContext).append("/").append(LOOKUP_RESOLVE_EXPRESSION_URI).append("/" + assetValueId + "?synteticAssetId=" + synteticAssetId + "&" + groupsAsString).toString();

	}

    }


    public String getLookupProtocol()
    {

	return lookupProtocol;
    }


    public void setLookupProtocol(String lookupProtocol)
    {

	this.lookupProtocol = lookupProtocol;
    }


    public String getLookupHost()
    {

	return lookupHost;
    }


    public void setLookupHost(String lookupHost)
    {

	this.lookupHost = lookupHost;
    }


    public Integer getLookupPort()
    {

	return lookupPort;
    }


    public void setLookupPort(Integer lookupPort)
    {

	this.lookupPort = lookupPort;
    }


    public String getLookupContext()
    {

	return lookupContext;
    }


    public void setLookupContext(String lookupContext)
    {

	this.lookupContext = lookupContext;
    }

}
