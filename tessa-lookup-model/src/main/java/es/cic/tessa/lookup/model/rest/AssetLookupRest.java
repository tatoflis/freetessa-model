package es.cic.tessa.lookup.model.rest;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import tools.jackson.databind.annotation.JsonSerialize;
import es.cic.tessa.lookup.model.AbstractConnectionData;


@JsonInclude(Include.NON_NULL)
@JsonSerialize
public class AssetLookupRest extends AbstractConnectionData
{

    private static final long serialVersionUID = 1L;

    public static final String URI = "Uri";
    public static final String URI_SERVICE = "Uriservice";
    public static final String URI_LOGIN = "Urilogin";
    public static final String OPERATION = "Operation";

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("uriservice")
    private String uriservice;

    @JsonProperty("urilogin")
    private String urilogin;

    @JsonProperty("operation")
    private String operation;

    public String getOperation()
    {

	return operation;
    }


    public void setOperation(String operation)
    {

	this.operation = operation;
    }


    public String getUri()
    {

	return uri;
    }


    public void setUri(String uri)
    {

	this.uri = uri;
    }


    public String getUriservice()
    {

	return uriservice;
    }


    public void setUriservice(String uriservice)
    {

	this.uriservice = uriservice;
    }


    public String getUrilogin()
    {

	return urilogin;
    }


    public void setUrilogin(String urilogin)
    {

	this.urilogin = urilogin;
    }


    @Override
    public String toString()
    {

	return "AssetLookupRest [uri=" + uri + ", uriservice=" + uriservice + ", urilogin=" + urilogin + ", operation=" + operation + ", protocol=" + protocol + ", host=" + host + ", port=" + port + ", user=" + user + ", password=*****, id=" + id + "]";
    }

}
