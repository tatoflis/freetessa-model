package es.cic.tessa.model.template.exceptions;


import es.cic.tessa.common.exceptions.TessaBaseException;


public class HashtagValidationException extends TessaBaseException
{

    private static final long serialVersionUID = 1L;

    public HashtagValidationException(String message)
    {

	super(message);
    }


    public HashtagValidationException(String code, String message)
    {

	super(message);
	setCode(code);
    }


    public HashtagValidationException(String message, Throwable e)
    {

	super(message, e);
    }


    public HashtagValidationException(String code, String message, Throwable e)
    {

	super(message, e);
	setCode(code);
    }

}
