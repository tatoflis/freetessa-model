package es.cic.tessa.client.exceptions;


public class OrganizerClientException extends RuntimeException
{

    private static final long serialVersionUID = 1L;

    private String code;

    public OrganizerClientException(String message)
    {

	super(message);
    }


    public OrganizerClientException(String code, String message)
    {

	super(message);
	this.code = code;
    }


    public OrganizerClientException(String message, Throwable e)
    {

	super(message, e);
    }


    public OrganizerClientException(String code, String message, Throwable e)
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

}
