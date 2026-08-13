package es.cic.tessa.common.exceptions;


public class RedisException extends RuntimeException
{

    private static final long serialVersionUID = 1L;

    private final String code;
    private final String[] args;

    public RedisException()
    {

	super();
	this.code = null;
	this.args = null;
    }


    public RedisException(String message)
    {

	super(message);
	this.code = null;
	this.args = null;
    }


    public RedisException(String message, Throwable cause)
    {

	super(message, cause);
	this.code = null;
	this.args = null;
    }


    public RedisException(Throwable cause)
    {

	super(cause);
	this.code = null;
	this.args = null;
    }


    public RedisException(String code, String message, String... args)
    {

	super(message);
	this.code = code;
	this.args = args;
    }


    public String getCode()
    {

	return code;
    }


    public String[] getArgs()
    {

	return args;
    }
}
