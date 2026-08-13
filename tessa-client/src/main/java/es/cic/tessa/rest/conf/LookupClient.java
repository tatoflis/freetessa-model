package es.cic.tessa.rest.conf;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;
import es.cic.tessa.client.exceptions.RestClientException;


@Component
public class LookupClient
{

    private static final Logger LOGGER = LoggerFactory.getLogger(LookupClient.class);

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected LookupRestClientConfig lookupRestClientConfig;

    public LookupClient()
    {

    }


    public LookupClient(LookupRestClientConfig lookupRestClientConfig)
    {

	this.lookupRestClientConfig = lookupRestClientConfig;
    }


    public LookupClient(RestTemplate restTemplate, ObjectMapper objectMapper, LookupRestClientConfig lookupRestClientConfig)
    {

	this.restTemplate = restTemplate;
	this.objectMapper = objectMapper;
	this.lookupRestClientConfig = lookupRestClientConfig;

    }


    public LookupRestClientConfig getLookupRestClientConfig()
    {

	if(lookupRestClientConfig == null)
	{
	    LOGGER.error("The lookup client is not initialized");
	    throw new RestClientException("The lookup client is not initialized");
	}

	return lookupRestClientConfig;
    }

}
