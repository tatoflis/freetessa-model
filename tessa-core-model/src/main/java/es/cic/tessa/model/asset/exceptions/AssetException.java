package es.cic.tessa.model.asset.exceptions;


import es.cic.tessa.common.exceptions.TessaBaseException;


public class AssetException extends TessaBaseException
{

    private static final long serialVersionUID = 1L;

    private String[] args;

    public AssetException(String message)
    {

	super(message);
    }


    public AssetException(String code, String message, String... args)
    {

	super(message);
	setCode(code);
	this.args = args;
    }


    public AssetException(String message, Throwable e)
    {

	super(message, e);
    }


    public AssetException(String code, String message, Throwable e)
    {

	super(message, e);
	setCode(code);
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
