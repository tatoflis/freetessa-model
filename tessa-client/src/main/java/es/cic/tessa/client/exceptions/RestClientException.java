package es.cic.tessa.client.exceptions;


public class RestClientException extends RuntimeException
{

    private static final long serialVersionUID = 1L;

    private String code;

    public RestClientException(String message)
    {

	super(message);
    }


    public RestClientException(String code, String message)
    {

	super(message);
	this.code = code;
    }


    public RestClientException(String message, Throwable e)
    {

	super(message, e);
    }


    public RestClientException(String code, String message, Throwable e)
    {

	super(message, e);
	this.code = code;
    }


    public String getCode()
    {

	return code;
    }


    public void setCode(String code)
    {

	this.code = code;
    }

}
