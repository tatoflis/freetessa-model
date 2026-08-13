package es.cic.tessa.model.asset.exceptions;


import es.cic.tessa.common.exceptions.TessaBaseException;


public class AssetValueValidationException extends TessaBaseException
{

    private static final long serialVersionUID = 1L;

    public AssetValueValidationException(String message)
    {

	super(message);
    }


    public AssetValueValidationException(String code, String message)
    {

	super(message);
	setCode(code);
    }


    public AssetValueValidationException(String message, Throwable e)
    {

	super(message, e);
    }


    public AssetValueValidationException(String code, String message, Throwable e)
    {

	super(message, e);
	setCode(code);
    }

}
