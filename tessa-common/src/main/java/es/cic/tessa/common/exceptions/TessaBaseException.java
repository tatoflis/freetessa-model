package es.cic.tessa.common.exceptions;


public abstract class TessaBaseException extends RuntimeException
{

    private static final long serialVersionUID = 1L;

    private String code;

    protected TessaBaseException(String message)
    {

	super(message);
    }


    protected TessaBaseException(String code, String message)
    {

	super(message);
	this.code = code;
    }


    protected TessaBaseException(String message, Throwable e)
    {

	super(message, e);
    }


    protected TessaBaseException(String code, String message, Throwable e)
    {

	super(message, e);
	this.code = code;
    }


    public String getCode()
    {

	return code;
    }


    protected void setCode(String code)
    {

	this.code = code;
    }
}
