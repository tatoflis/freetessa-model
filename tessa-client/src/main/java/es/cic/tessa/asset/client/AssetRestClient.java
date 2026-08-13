package es.cic.tessa.asset.client;


import java.util.Collection;
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
import es.cic.tessa.client.exceptions.AssetClientException;
import es.cic.tessa.client.exceptions.OrganizerClientException;
import es.cic.tessa.common.filter.PropertyFilter;
import es.cic.tessa.common.model.TessaConstants;
import es.cic.tessa.common.support.PageConfig;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.Asset;
import es.cic.tessa.model.AssetValue;
import es.cic.tessa.model.dto.AssetRequest;
import es.cic.tessa.model.dto.AssetResponse;
import es.cic.tessa.model.dto.AssetValueRequest;
import es.cic.tessa.model.dto.AssetValueResponse;
import es.cic.tessa.model.dto.AssignOrganizersToAssetsRequest;
import es.cic.tessa.model.dto.json.ResponsePageJson;
import es.cic.tessa.model.filter.AssetFilter;
import es.cic.tessa.model.filter.AssetValueFilter;
import es.cic.tessa.model.mappers.AssetGridResponseMapper;
import es.cic.tessa.model.mappers.AssetRequestMapper;
import es.cic.tessa.model.mappers.AssetResponseMapper;
import es.cic.tessa.model.mappers.AssetValueRequestMapper;
import es.cic.tessa.model.mappers.AssetValueResponseMapper;
import es.cic.tessa.rest.conf.RestClient;


@Component
public class AssetRestClient extends RestClient
{

    private final static Logger LOGGER = LoggerFactory.getLogger(AssetRestClient.class);

    @Autowired
    private AssetRequestMapper assetRequestMapper;

    @Autowired
    private AssetResponseMapper assetResponseMapper;

    @Autowired
    private AssetGridResponseMapper assetGridResponseMapper;

    @Autowired
    private AssetValueRequestMapper assetValueRequestMapper;

    @Autowired
    private AssetValueResponseMapper assetValueResponseMapper;

    public Asset getAsset(Long idAsset, Set<String> groups)
    {

	Asset asset = null;

	String assetUriGet = getRestClientConfig().buildUriAssetGet(idAsset, groups);

	AssetResponse assetResponse = restTemplate.getForObject(assetUriGet, AssetResponse.class);

	if(assetResponse != null)
	{
	    asset = assetResponseMapper.assetResponseToAsset(assetResponse);
	}

	return asset;
    }


    public Asset getFullAsset(Long idAsset, Set<String> groups)
    {

	Asset asset = null;

	String assetUriGet = getRestClientConfig().buildUriAssetFullGet(idAsset, groups);

	AssetResponse assetResponse = restTemplate.getForObject(assetUriGet, AssetResponse.class);

	if(assetResponse != null)
	{
	    asset = assetGridResponseMapper.assetGridResponseToAsset(assetResponse);
	}
	return asset;
    }


    public ResponsePage<Asset> searchAsset(AssetFilter filter, Set<String> groups)
    {

	return searchAsset(filter, null, groups);
    }


    public ResponsePage<Asset> searchAsset(AssetFilter filter, PageConfig pageConfig, Set<String> groups)
    {

	ResponsePage<Asset> assetCollectionToAssetResposePage = new ResponsePage<>();
	try
	{

	    String assetUriSearch = getRestClientConfig().buildUriAssetSearch(pageConfig, groups);

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    String filterOrganizer = objectMapper.writeValueAsString(filter);

	    HttpEntity<String> entity = new HttpEntity<String>(filterOrganizer, headers);

	    ResponseEntity<ResponsePageJson<AssetResponse>> responseEntity = restTemplate.exchange(assetUriSearch, HttpMethod.POST, entity, new ParameterizedTypeReference<ResponsePageJson<AssetResponse>>()
	    {
	    });

	    if(responseEntity.getBody() != null)
	    {
		ResponsePageJson<AssetResponse> assetsResponse = responseEntity.getBody();
		assetCollectionToAssetResposePage = assetResponseMapper.assetCollectionToAssetResposePage(assetsResponse);
	    }
	    return assetCollectionToAssetResposePage;
	}
	catch (Exception e)
	{
	    LOGGER.error("Error executing client call to search organizers ", e);
	    throw new OrganizerClientException("Error executing client call to search organizers", e);
	}
    }


    public ResponsePage<Asset> searchFullAsset(AssetFilter filter, PageConfig pageConfig, Set<String> groups)
    {

	ResponsePage<Asset> assetGridCollectionToAssetResposePage = new ResponsePage<>();
	try
	{

	    String assetUriSearch = getRestClientConfig().buildUriAssetFullSearch(pageConfig, groups);

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    String filterOrganizer = objectMapper.writeValueAsString(filter);

	    HttpEntity<String> entity = new HttpEntity<String>(filterOrganizer, headers);
	    ResponseEntity<ResponsePageJson<AssetResponse>> assetsResponseEntity = restTemplate.exchange(assetUriSearch, HttpMethod.POST, entity, new ParameterizedTypeReference<ResponsePageJson<AssetResponse>>()
	    {
	    });

	    if(assetsResponseEntity.getBody() != null)
	    {
		ResponsePageJson<AssetResponse> assetsResponse = assetsResponseEntity.getBody();

		if(assetsResponse != null)
		{
		    assetGridCollectionToAssetResposePage = assetGridResponseMapper.assetGridCollectionToAssetResposePage(assetsResponse);
		}
	    }

	    return assetGridCollectionToAssetResposePage;

	}
	catch (Exception e)
	{
	    LOGGER.error("Error executing client call to search organizers ", e);
	    throw new AssetClientException("Error executing client call to search assets", e);
	}
    }


    public ResponsePage<Asset> searchFullAsset(AssetFilter filter, Set<String> groups)
    {

	return searchFullAsset(filter, null, groups);
    }


    public Asset createAsset(Asset asset, Set<String> groups)
    {

	try
	{

	    String assetUriCreate = getRestClientConfig().buildUriAssetCreateUpdate(groups);

	    AssetRequest assetRequest = assetRequestMapper.assetToAssetRequest(asset);

	    HttpEntity<AssetRequest> request = new HttpEntity<>(assetRequest);
	    ResponseEntity<AssetResponse> assetResponse = restTemplate.exchange(assetUriCreate, HttpMethod.POST, request, AssetResponse.class);

	    if(assetResponse != null)
	    {
		return assetResponseMapper.assetResponseToAsset(assetResponse.getBody());
	    }

	    return null;

	}
	catch (Exception e)
	{
	    LOGGER.error("Error executing client call to create asset", e);
	    throw new AssetClientException("Error executing client call to create asset", e);
	}
    }


    public Asset updateAsset(Asset asset, Set<String> groups)
    {

	try
	{

	    String assetUriUpdate = getRestClientConfig().buildUriAssetCreateUpdate(groups);

	    AssetRequest assetRequest = assetRequestMapper.assetToAssetRequest(asset);

	    HttpEntity<AssetRequest> request = new HttpEntity<>(assetRequest);
	    ResponseEntity<AssetResponse> assetResponse = restTemplate.exchange(assetUriUpdate, HttpMethod.PUT, request, AssetResponse.class);

	    if(assetResponse != null)
	    {
		return assetResponseMapper.assetResponseToAsset(assetResponse.getBody());
	    }

	    return null;

	}
	catch (Exception e)
	{
	    LOGGER.error("Error executing client call to update asset", e);
	    throw new AssetClientException("Error executing client call to update asset", e);
	}
    }


    public AssetValue getAssetValue(Long idAssetValue, Set<String> groups)
    {

	AssetValueFilter assetValueFilter = new AssetValueFilter();
	assetValueFilter.addPropertyFilter(new PropertyFilter(TessaConstants.ID, idAssetValue));

	ResponsePage<AssetValue> assetValues = searchAssetValues(assetValueFilter, groups);

	if(assetValues != null && assetValues.getNumberOfElements() == 1)
	{
	    return assetValues.iterator().next();
	}
	else
	{
	    return null;
	}

    }


    public ResponsePage<AssetValue> searchAssetValues(AssetValueFilter filter, Set<String> groups)
    {

	return searchAssetValues(filter, null, groups);
    }


    public ResponsePage<AssetValue> searchAssetValues(AssetValueFilter filter, PageConfig pageConfig, Set<String> groups)
    {

	ResponsePage<AssetValue> assetValueCollectionToAssetValueResposePage = new ResponsePage<>();

	try
	{

	    String assetValueUriSearch = getRestClientConfig().buildUriAssetValueSearch(pageConfig, groups);

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    String filterAssetValue = objectMapper.writeValueAsString(filter);

	    HttpEntity<String> entity = new HttpEntity<String>(filterAssetValue, headers);

	    ResponseEntity<ResponsePageJson<AssetValueResponse>> responseEntity = restTemplate.exchange(assetValueUriSearch, HttpMethod.POST, entity, new ParameterizedTypeReference<ResponsePageJson<AssetValueResponse>>()
	    {
	    });

	    if(responseEntity.getBody() != null)
	    {
		ResponsePageJson<AssetValueResponse> assetValuesResponse = responseEntity.getBody();
		assetValueCollectionToAssetValueResposePage = assetValueResponseMapper.assetValueCollectionToAssetValueResposePage(assetValuesResponse);
	    }

	    return assetValueCollectionToAssetValueResposePage;
	}
	catch (Exception e)
	{
	    LOGGER.error("Error executing client call to search asset values ", e);
	    throw new AssetClientException("Error executing client call to search asset values", e);
	}
    }


    public AssetValue createAssetValue(AssetValue assetValue, Set<String> groups)
    {

	try
	{

	    AssetValueRequest assetValueRequest = assetValueRequestMapper.assetValueToAssetValueRequest(assetValue);

	    String assetValueUriCreate = getRestClientConfig().buildUriAssetValueCreateUpdate(groups);

	    HttpEntity<AssetValueRequest> request = new HttpEntity<>(assetValueRequest);
	    ResponseEntity<AssetValueResponse> assetValueResponse = restTemplate.exchange(assetValueUriCreate, HttpMethod.POST, request, AssetValueResponse.class);

	    if(assetValueResponse != null)
	    {
		return assetValueResponseMapper.assetValueResponseToAssetValue(assetValueResponse.getBody());
	    }

	    return null;

	}
	catch (Exception e)
	{
	    LOGGER.error("Error executing client call to create asset value", e);
	    throw new AssetClientException("Error executing client call to create asset value", e);
	}
    }


    public AssetValue updateAssetValue(AssetValue assetValue, Set<String> groups)
    {

	try
	{

	    AssetValueRequest assetValueRequest = assetValueRequestMapper.assetValueToAssetValueRequest(assetValue);

	    String assetValueUriUpdate = getRestClientConfig().buildUriAssetCreateUpdate(groups);

	    HttpEntity<AssetValueRequest> request = new HttpEntity<>(assetValueRequest);
	    ResponseEntity<AssetValueResponse> assetValueResponse = restTemplate.exchange(assetValueUriUpdate, HttpMethod.PUT, request, AssetValueResponse.class);

	    if(assetValueResponse != null)
	    {
		return assetValueResponseMapper.assetValueResponseToAssetValue(assetValueResponse.getBody());
	    }

	    return null;

	}
	catch (Exception e)
	{
	    LOGGER.error("Error executing client call to update asset value", e);
	    throw new AssetClientException("Error executing client call to update asset value", e);
	}
    }


    public void assignAssetValuesToAsset(Long idAsset, Collection<AssetValue> assetValues, Set<String> groups)
    {

	try
	{

	    String assetValueUriAssign = getRestClientConfig().buildUriAssetValueAssignAsset(idAsset, groups);

	    Collection<AssetValueRequest> assetValuesRequest = assetValueRequestMapper.assetValueCollectionToAssetValueRequestCollection(assetValues);

	    HttpEntity<Collection<AssetValueRequest>> request = new HttpEntity<>(assetValuesRequest);
	    restTemplate.exchange(assetValueUriAssign, HttpMethod.POST, request, Void.class);

	}
	catch (Exception e)
	{
	    LOGGER.error("Error executing client call to assign asset values to asset", e);
	    throw new AssetClientException("Error executing client call to assing asset values to asset", e);
	}
    }


    public void assignOrganizersToAsset(AssetFilter assetFilter, Collection<Long> idOrganizers, Set<String> groups)
    {

	try
	{

	    String assetOrganizersUriAssign = getRestClientConfig().buildUriAssetAssignOrganizers(groups);

	    AssignOrganizersToAssetsRequest assignOrganizerToAssetRequest = assetRequestMapper.assignOrganizerToAssetRequest(assetFilter, idOrganizers, groups);

	    HttpEntity<AssignOrganizersToAssetsRequest> request = new HttpEntity<>(assignOrganizerToAssetRequest);
	    restTemplate.exchange(assetOrganizersUriAssign, HttpMethod.POST, request, Void.class);

	}
	catch (Exception e)
	{
	    LOGGER.error("Error executing client call to assign asset values to asset", e);
	    throw new AssetClientException("Error executing client call to assing asset values to asset", e);
	}
    }


    public void deleteAssetValue(Long idAssetValue, Set<String> groups)
    {

	try
	{

	    String assetUriDelete = getRestClientConfig().buildUriAssetValueGetDelete(idAssetValue, groups);

	    restTemplate.delete(assetUriDelete, AssetValue.class);

	}
	catch (Exception e)
	{
	    LOGGER.error("Error executing client call to delete asset value", e);
	    throw new AssetClientException("Error executing client call to delete asset value", e);
	}
    }
}
