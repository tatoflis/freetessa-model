package es.cic.tessa.lookup.exceptions;


public class RedisCacheException extends RuntimeException
{

    private static final long serialVersionUID = 1L;

    private String code;

    public RedisCacheException(String message)
    {

	super(message);
    }


    public RedisCacheException(Throwable throwable)
    {

	super(throwable);
    }


    public RedisCacheException(String message, String code)
    {

	super(message);
	this.code = code;
    }


    public RedisCacheException(String message, Throwable e)
    {

	super(message, e);
    }


    public RedisCacheException(String code, String message, Throwable e)
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
