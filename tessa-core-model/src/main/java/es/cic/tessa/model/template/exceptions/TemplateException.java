package es.cic.tessa.model.template.exceptions;

import es.cic.tessa.common.exceptions.TessaBaseException;

public class TemplateException extends TessaBaseException
{

    private static final long serialVersionUID = 1L;

    public TemplateException(String message)
    {

	super(message);
    }


    public TemplateException(String code, String message)
    {

	super(message);
	setCode(code);
    }


    public TemplateException(String message, Throwable e)
    {

	super(message, e);
    }


    public TemplateException(String code, String message, Throwable e)
    {

	super(message, e);
	setCode(code);
    }

}
