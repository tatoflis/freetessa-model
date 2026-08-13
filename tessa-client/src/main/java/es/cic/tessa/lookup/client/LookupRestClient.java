package es.cic.tessa.lookup.client;


import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;
import es.cic.tessa.client.exceptions.LookupClientException;
import es.cic.tessa.lookup.expression.model.ResultExpressionFunction;
import es.cic.tessa.lookup.expression.model.Expression;
import es.cic.tessa.rest.conf.LookupClient;
import es.cic.tessa.rest.conf.LookupRestClientConfig;


@Component
public class LookupRestClient extends LookupClient
{

    private static final Logger LOGGER = LoggerFactory.getLogger(LookupRestClient.class);

    @Value("${tessa.security.username:#{null}}")
    protected String username;

    @Value("${tessa.security.password:#{null}}")
    protected String password;

    public LookupRestClient()
    {

    }


    public LookupRestClient(LookupRestClientConfig lookupRestClientConfig)
    {

	super(lookupRestClientConfig);
    }


    public LookupRestClient(RestTemplate restTemplate, ObjectMapper objectMapper, LookupRestClientConfig lookupRestClientConfig, String username, String password)
    {

	super(restTemplate, objectMapper, lookupRestClientConfig);

	this.username = username;
	this.password = password;
    }


    public String resolveAssetValue(Long assetValueId, Long synteticAssetId, Boolean stringExpression, Boolean async, Set<String> groups)
    {

	try
	{
	    String lookupUriSearch = getLookupRestClientConfig().buildUriLookupResolveAssetValue(assetValueId, synteticAssetId, stringExpression, async, groups);

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    if(username != null && password != null)
	    {
		headers.setBasicAuth(username, password);
	    }

	    HttpEntity<String> entity = new HttpEntity<>("", headers);

	    ResponseEntity<ResultExpressionFunction> responseEntity = restTemplate.exchange(lookupUriSearch, HttpMethod.POST, entity, new ParameterizedTypeReference<ResultExpressionFunction>()
	    {
	    });

	    if(responseEntity.getBody() != null)
	    {

		return responseEntity.getBody().getRows().get(0).getRow().get(0).getValue();
	    }

	    return null;

	}
	catch (Exception e)
	{
	    LOGGER.error("Error executing client call to resolve value, {}", e.getMessage());
	    throw new LookupClientException("Error executing client call to resolve value ", e);
	}
    }


    public String resolveExpression(Long assetValueId, Long synteticAssetId, Expression expression, Set<String> groups)
    {

	try
	{
	    String lookupUriSearch = getLookupRestClientConfig().buildUriLookupResolveExpression(assetValueId, synteticAssetId, groups);

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    if(username != null && password != null)
	    {
		headers.setBasicAuth(username, password);
	    }

	    String expressionJson = objectMapper.writeValueAsString(expression);

	    HttpEntity<String> entity = new HttpEntity<String>(expressionJson, headers);

	    ResponseEntity<ResultExpressionFunction> responseEntity = restTemplate.exchange(lookupUriSearch, HttpMethod.POST, entity, new ParameterizedTypeReference<ResultExpressionFunction>()
	    {
	    });

	    if(responseEntity.getBody() != null)
	    {

		return responseEntity.getBody().getRows().get(0).getRow().get(0).getValue();
	    }

	    return null;

	}
	catch (Exception e)
	{
	    LOGGER.error("Error executing client call to resolve value, {}", e.getMessage());
	    throw new LookupClientException("Error executing client call to resolve value ", e);
	}
    }

}
