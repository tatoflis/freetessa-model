package es.cic.tessa.organizer.client;


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
import es.cic.tessa.client.exceptions.OrganizerClientException;
import es.cic.tessa.common.support.PageConfig;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.Organizer;
import es.cic.tessa.model.dto.OrganizerResponse;
import es.cic.tessa.model.dto.json.ResponsePageJson;
import es.cic.tessa.model.filter.OrganizerFilter;
import es.cic.tessa.model.mappers.OrganizerResponseMapper;
import es.cic.tessa.rest.conf.RestClient;


@Component
public class OrganizerRestClient extends RestClient
{

    private final static Logger LOGGER = LoggerFactory.getLogger(OrganizerRestClient.class);

    @Autowired
    private OrganizerResponseMapper organizerResponseMapper;

    public Organizer getOrganizer(Long idOrganizer, Set<String> groups)
    {

	String organizerUriGet = getRestClientConfig().buildUriAssetGet(idOrganizer, groups);

	Organizer organizer = restTemplate.getForObject(organizerUriGet, Organizer.class);
	return organizer;
    }


    public ResponsePage<Organizer> searchOrganizer(OrganizerFilter filter, Set<String> groups)
    {

	return searchOrganizer(filter, null, groups);
    }


    public ResponsePage<Organizer> searchOrganizer(OrganizerFilter filter, PageConfig pageConfig, Set<String> groups)
    {

	try
	{

	    String organizerUriSearch = getRestClientConfig().buildUriOrganizerSearch(pageConfig, groups);

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    String filterOrganizer = objectMapper.writeValueAsString(filter);

	    HttpEntity<String> entity = new HttpEntity<String>(filterOrganizer, headers);

	    ResponseEntity<ResponsePageJson<OrganizerResponse>> responseEntity = restTemplate.exchange(organizerUriSearch, HttpMethod.POST, entity, new ParameterizedTypeReference<ResponsePageJson<OrganizerResponse>>()
	    {
	    });

	    if(responseEntity.getBody() != null)
	    {
		ResponsePageJson<OrganizerResponse> organizerResponse = responseEntity.getBody();
		return organizerResponseMapper.organizerCollectionToOrganizerResposePage(organizerResponse);

	    }
	    return null;
	}
	catch (Exception e)
	{
	    LOGGER.error("Error executing client call to search organizers ", e);
	    throw new OrganizerClientException("Error executing client call to search organizers", e);
	}
    }

}
