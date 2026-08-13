package es.cic.tessa.lookup.exceptions;


public class EventException extends RuntimeException
{

    private static final long serialVersionUID = 1L;

    public EventException(String message)
    {

	super(message);
    }


    public EventException(String message, Throwable e)
    {

	super(message, e);
    }

}
