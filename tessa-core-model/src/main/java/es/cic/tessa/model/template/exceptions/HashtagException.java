package es.cic.tessa.model.template.exceptions;


import es.cic.tessa.common.exceptions.TessaBaseException;


public class HashtagException extends TessaBaseException
{

    private static final long serialVersionUID = 1L;

    public HashtagException(String message)
    {

	super(message);
    }


    public HashtagException(String code, String message)
    {

	super(message);
	setCode(code);
    }


    public HashtagException(String message, Throwable e)
    {

	super(message, e);
    }


    public HashtagException(String code, String message, Throwable e)
    {

	super(message, e);
	setCode(code);
    }

}
