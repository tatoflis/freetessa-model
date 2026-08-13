package es.cic.tessa.rest.conf;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;
import es.cic.tessa.client.exceptions.RestClientException;


@Component
public class RestClient
{

    private static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected RestClientConfig restClientConfig;

    public void init(RestClientConfig restClientConfig)
    {

	this.restClientConfig = restClientConfig;
    }


    public RestClientConfig getRestClientConfig()
    {

	if(restClientConfig == null)
	{
	    LOGGER.error("The client is not initialized");
	    throw new RestClientException("The client is not initialized");
	}

	return restClientConfig;
    }

}
