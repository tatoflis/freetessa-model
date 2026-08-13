package es.cic.tessa.model.template.exceptions;

import es.cic.tessa.common.exceptions.TessaBaseException;

public class TemplateValidationException extends TessaBaseException
{

    private static final long serialVersionUID = 1L;

    public TemplateValidationException(String message)
    {

	super(message);
    }


    public TemplateValidationException(String code, String message)
    {

	super(message);
	setCode(code);
    }


    public TemplateValidationException(String message, Throwable e)
    {

	super(message, e);
    }


    public TemplateValidationException(String code, String message, Throwable e)
    {

	super(message, e);
	setCode(code);
    }

}
