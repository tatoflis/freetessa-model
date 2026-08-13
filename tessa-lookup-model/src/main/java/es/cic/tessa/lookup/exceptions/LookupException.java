package es.cic.tessa.lookup.exceptions;


public class LookupException extends RuntimeException
{

    private static final long serialVersionUID = 1L;

    private String code;

    public LookupException(String message)
    {

	super(message);
    }


    public LookupException(Throwable throwable)
    {

	super(throwable);
    }


    public LookupException(String message, String code)
    {

	super(message);
	this.code = code;
    }


    public LookupException(String message, Throwable e)
    {

	super(message, e);
    }


    public LookupException(String code, String message, Throwable e)
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
