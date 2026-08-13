package es.cic.tessa.client.exceptions;


public class HashtagClientException extends RuntimeException
{

    private static final long serialVersionUID = 1L;

    private String code;

    public HashtagClientException(String message)
    {

	super(message);
    }


    public HashtagClientException(String code, String message)
    {

	super(message);
	this.code = code;
    }


    public HashtagClientException(String message, Throwable e)
    {

	super(message, e);
    }


    public HashtagClientException(String code, String message, Throwable e)
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
