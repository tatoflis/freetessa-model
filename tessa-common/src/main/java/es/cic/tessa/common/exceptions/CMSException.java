package es.cic.tessa.common.exceptions;


public class CMSException extends RuntimeException
{

    private static final long serialVersionUID = 1L;

    private String code;
    private String[] args;

    public CMSException(String message)
    {

	super(message);
    }


    public CMSException(String code, String message, String... args)
    {

	super(message);
	this.code = code;
	this.args = args;
    }


    public CMSException(String message, Throwable e)
    {

	super(message, e);
    }


    public CMSException(String code, String message, Throwable e)
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


    public String[] getArgs()
    {

	return args;
    }


    public void setArgs(String[] args)
    {

	this.args = args;
    }

}
