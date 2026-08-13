package es.cic.tessa.client.exceptions;


public class AssetClientException extends RuntimeException
{

    private static final long serialVersionUID = 1L;

    private String code;

    public AssetClientException(String message)
    {

	super(message);
    }


    public AssetClientException(String code, String message)
    {

	super(message);
	this.code = code;
    }


    public AssetClientException(String message, Throwable e)
    {

	super(message, e);
    }


    public AssetClientException(String code, String message, Throwable e)
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
