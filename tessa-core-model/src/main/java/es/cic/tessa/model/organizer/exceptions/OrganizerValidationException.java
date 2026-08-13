package es.cic.tessa.model.organizer.exceptions;


import es.cic.tessa.common.exceptions.TessaBaseException;


public class OrganizerValidationException extends TessaBaseException
{

    private static final long serialVersionUID = 1L;

    public OrganizerValidationException(String message)
    {

	super(message);
    }


    public OrganizerValidationException(String code, String message)
    {

	super(message);
	setCode(code);
    }


    public OrganizerValidationException(String message, Throwable e)
    {

	super(message, e);
    }


    public OrganizerValidationException(String code, String message, Throwable e)
    {

	super(message, e);
	setCode(code);
    }

}
