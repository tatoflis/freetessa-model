package es.cic.tessa.hashtag.client;


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
import es.cic.tessa.client.exceptions.RestClientException;
import es.cic.tessa.common.support.PageConfig;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.Hashtag;
import es.cic.tessa.model.dto.HashtagResponse;
import es.cic.tessa.model.dto.json.ResponsePageJson;
import es.cic.tessa.model.filter.HashtagFilter;
import es.cic.tessa.model.mappers.HashtagResponseMapper;
import es.cic.tessa.rest.conf.RestClient;


@Component
public class HashtagRestClient extends RestClient
{

    private final static Logger LOGGER = LoggerFactory.getLogger(HashtagRestClient.class);

    @Autowired
    private HashtagResponseMapper hashtagResponseMapper;

    public ResponsePage<Hashtag> searchHashtag(HashtagFilter filter, Set<String> groups)
    {

	return searchHashtag(filter, null, groups);
    }


    public ResponsePage<Hashtag> searchHashtag(HashtagFilter filter, PageConfig pageConfig, Set<String> groups)
    {

	try
	{

	    String hashtagUriSearch = getRestClientConfig().buildUriHashtagSearch(pageConfig, groups);

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    String filterHashtag = objectMapper.writeValueAsString(filter);

	    HttpEntity<String> entity = new HttpEntity<String>(filterHashtag, headers);

	    ResponseEntity<ResponsePageJson<HashtagResponse>> responseEntity = restTemplate.exchange(hashtagUriSearch, HttpMethod.POST, entity, new ParameterizedTypeReference<ResponsePageJson<HashtagResponse>>()
	    {
	    });

	    if(responseEntity.getBody() != null)
	    {
		ResponsePageJson<HashtagResponse> hashtagsResponse = responseEntity.getBody();
		return hashtagResponseMapper.hashtagCollectionToHashtagResposePage(hashtagsResponse);
	    }
	    return null;
	}
	catch (Exception e)
	{
	    LOGGER.error("Error executing client call to search hashtag ", e);
	    throw new RestClientException("Error executing client call to search hashtag", e);
	}
    }

}
