package es.cic.tessa.model.organizer.exceptions;


import es.cic.tessa.common.exceptions.TessaBaseException;


public class OrganizerException extends TessaBaseException
{

    private static final long serialVersionUID = 1L;

    public OrganizerException(String message)
    {

	super(message);
    }


    public OrganizerException(String code, String message)
    {

	super(message);
	setCode(code);
    }


    public OrganizerException(String message, Throwable e)
    {

	super(message, e);
    }


    public OrganizerException(String code, String message, Throwable e)
    {

	super(message, e);
	setCode(code);
    }

}
