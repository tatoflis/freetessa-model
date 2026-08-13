package es.cic.tessa.model.asset.exceptions;


import es.cic.tessa.common.exceptions.TessaBaseException;


public class AssetValidationException extends TessaBaseException
{

    private static final long serialVersionUID = 1L;

    public AssetValidationException(String message)
    {

	super(message);
    }


    public AssetValidationException(String code, String message)
    {

	super(message);
	setCode(code);
    }


    public AssetValidationException(String message, Throwable e)
    {

	super(message, e);
    }


    public AssetValidationException(String code, String message, Throwable e)
    {

	super(message, e);
	setCode(code);
    }

}
