package es.cic.tessa.template.client;


import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import es.cic.tessa.client.exceptions.TemplateClientException;
import es.cic.tessa.common.support.PageConfig;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.ExpressionParam;
import es.cic.tessa.model.Template;
import es.cic.tessa.model.TemplateAttribute;
import es.cic.tessa.model.TemplateAttributeCollectionMapping;
import es.cic.tessa.model.dto.ExpressionParamResponse;
import es.cic.tessa.model.dto.TemplateAttributeCollectionMappingResponse;
import es.cic.tessa.model.dto.TemplateAttributeResponse;
import es.cic.tessa.model.dto.TemplateResponse;
import es.cic.tessa.model.dto.json.ResponsePageJson;
import es.cic.tessa.model.filter.TemplateAttributeCollectionMappingFilter;
import es.cic.tessa.model.filter.TemplateAttributeExpressionParamFilter;
import es.cic.tessa.model.filter.TemplateAttributeFilter;
import es.cic.tessa.model.filter.TemplateFilter;
import es.cic.tessa.model.mappers.TemplateAttributeCollectionMappingResponseMapper;
import es.cic.tessa.model.mappers.ExpressionParamResponseMapper;
import es.cic.tessa.model.mappers.TemplateAttributeResponseMapper;
import es.cic.tessa.model.mappers.TemplateResponseMapper;
import es.cic.tessa.rest.conf.RestClient;


@Component
public class TemplateRestClient extends RestClient
{

    private final static Logger LOGGER = LoggerFactory.getLogger(TemplateRestClient.class);

    @Autowired
    private TemplateResponseMapper templateResponseMapper;

    @Autowired
    private TemplateAttributeResponseMapper templateAttributeResponseMapper;

    @Autowired
    private TemplateAttributeCollectionMappingResponseMapper templateAttributeCollectionMappingResponseMapper;

    @Autowired
    private ExpressionParamResponseMapper templateAttributeExpressionParamResponseMapper;

    public ResponsePage<Template> searchTemplate(TemplateFilter filter, Set<String> groups)
    {

	return searchTemplate(filter, null, groups);
    }


    public ResponsePage<Template> searchTemplate(TemplateFilter filter, PageConfig pageConfig, Set<String> groups)
    {

	try
	{

	    String templateUriSearch = getRestClientConfig().buildUriTemplateSearch(pageConfig, groups);

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    String filterTemplate = objectMapper.writeValueAsString(filter);

	    HttpEntity<String> entity = new HttpEntity<String>(filterTemplate, headers);

	    ResponseEntity<ResponsePageJson<TemplateResponse>> responseEntity = restTemplate.exchange(templateUriSearch, HttpMethod.POST, entity, new ParameterizedTypeReference<ResponsePageJson<TemplateResponse>>()
	    {
	    });

	    if(responseEntity.getBody() != null)
	    {
		ResponsePageJson<TemplateResponse> templatesResponse = responseEntity.getBody();
		return templateResponseMapper.templateCollectionToTemplateResposePage(templatesResponse);
	    }
	    return null;
	}
	catch (Exception e)
	{
	    LOGGER.error("Error executing client call to search templates ", e);
	    throw new TemplateClientException("Error executing client call to search templates", e);
	}
    }


    public ResponsePage<TemplateAttribute> searchTemplateAttribute(TemplateAttributeFilter filter, Set<String> groups)
    {

	return searchTemplateAttribute(filter, null, groups);
    }


    public ResponsePage<TemplateAttribute> searchTemplateAttribute(TemplateAttributeFilter filter, PageConfig pageConfig, Set<String> groups)
    {

	if(filter == null)
	{
	    filter = new TemplateAttributeFilter();
	}

	try
	{

	    String templateAttributeUriSearch = getRestClientConfig().buildUriTemplateAttributeSearch(pageConfig, groups);

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    String filterTemplateAttribute = objectMapper.writeValueAsString(filter);

	    HttpEntity<String> entity = new HttpEntity<String>(filterTemplateAttribute, headers);

	    ResponseEntity<ResponsePageJson<TemplateAttributeResponse>> responseEntity = restTemplate.exchange(templateAttributeUriSearch, HttpMethod.POST, entity, new ParameterizedTypeReference<ResponsePageJson<TemplateAttributeResponse>>()
	    {
	    });

	    if(responseEntity.getBody() != null)
	    {
		ResponsePageJson<TemplateAttributeResponse> templateAttributesResponse = responseEntity.getBody();
		return templateAttributeResponseMapper.templateAttributeCollectionToTemplateAttributeResposePage(templateAttributesResponse);
	    }
	    return null;
	}
	catch (Exception e)
	{
	    LOGGER.error("Error executing client call to search template attributes ", e);
	    throw new TemplateClientException("Error executing client call to search template attributes", e);
	}
    }


    public ResponsePage<TemplateAttributeCollectionMapping> searchTemplateAttributeCollectionMapping(TemplateAttributeCollectionMappingFilter filter, Set<String> groups)
    {

	return searchTemplateAttributeCollectionMapping(filter, null, groups);

    }


    public ResponsePage<TemplateAttributeCollectionMapping> searchTemplateAttributeCollectionMapping(TemplateAttributeCollectionMappingFilter filter, PageConfig pageConfig, Set<String> groups)
    {

	try
	{

	    String templateAttributeCollectionMappingUriSearch = getRestClientConfig().buildUriTemplateAttributeCollectionMappingSearch(pageConfig, groups);

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    String filterTemplateAttributeCollectionMapping = objectMapper.writeValueAsString(filter);

	    HttpEntity<String> entity = new HttpEntity<String>(filterTemplateAttributeCollectionMapping, headers);

	    ResponseEntity<ResponsePageJson<TemplateAttributeCollectionMappingResponse>> responseEntity = restTemplate.exchange(templateAttributeCollectionMappingUriSearch, HttpMethod.POST, entity, new ParameterizedTypeReference<ResponsePageJson<TemplateAttributeCollectionMappingResponse>>()
	    {
	    });

	    if(responseEntity.getBody() != null)
	    {
		ResponsePageJson<TemplateAttributeCollectionMappingResponse> templateAttributeCollectionMappingsResponse = responseEntity.getBody();
		return templateAttributeCollectionMappingResponseMapper.templateAttributeCollectionMappingCollectionToTemplateAttributeCollectionMappingResposePage(templateAttributeCollectionMappingsResponse);
	    }
	    return null;
	}
	catch (Exception e)
	{
	    LOGGER.error("Error executing client call to search template attribute collection mappings ", e);
	    throw new TemplateClientException("Error executing client call to search template attribute collection mappings", e);
	}
    }


    public ResponsePage<ExpressionParam> searchTemplateAttributeExpressionParam(TemplateAttributeExpressionParamFilter filter, Set<String> groups)
    {

	return searchTemplateAttributeExpressionParam(filter, null, groups);

    }


    public ResponsePage<ExpressionParam> searchTemplateAttributeExpressionParam(TemplateAttributeExpressionParamFilter filter, PageConfig pageConfig, Set<String> groups)
    {

	try
	{

	    String templateAttributeExperssionParamUriSearch = getRestClientConfig().buildUriTemplateAttributeExpressionParamSearch(pageConfig, groups);

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    String filterTemplateAttributeExpressionParam = objectMapper.writeValueAsString(filter);

	    HttpEntity<String> entity = new HttpEntity<>(filterTemplateAttributeExpressionParam, headers);

	    ResponseEntity<ResponsePageJson<ExpressionParamResponse>> responseEntity = restTemplate.exchange(templateAttributeExperssionParamUriSearch, HttpMethod.POST, entity, new ParameterizedTypeReference<ResponsePageJson<ExpressionParamResponse>>()
	    {
	    });

	    if(responseEntity.getBody() != null)
	    {
		ResponsePageJson<ExpressionParamResponse> templateAttributeExpressionParamResponse = responseEntity.getBody();
		return templateAttributeExpressionParamResponseMapper.expressionParamResponseToExpressionParamResponsePage(templateAttributeExpressionParamResponse);
	    }
	    return null;
	}
	catch (Exception e)
	{
	    LOGGER.error("Error executing client call to search template attribute expression params ", e);
	    throw new TemplateClientException("Error executing client call to search template attribute expression params", e);
	}
    }

}
