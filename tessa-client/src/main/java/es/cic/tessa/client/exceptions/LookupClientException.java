package es.cic.tessa.client.exceptions;


public class LookupClientException extends RuntimeException
{

    private static final long serialVersionUID = 1L;

    private String code;

    public LookupClientException(String message)
    {

	super(message);
    }


    public LookupClientException(String code, String message)
    {

	super(message);
	this.code = code;
    }


    public LookupClientException(String message, Throwable e)
    {

	super(message, e);
    }


    public LookupClientException(String code, String message, Throwable e)
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
