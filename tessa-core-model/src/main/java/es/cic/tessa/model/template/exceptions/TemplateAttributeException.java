package es.cic.tessa.model.template.exceptions;

import es.cic.tessa.common.exceptions.TessaBaseException;

public class TemplateAttributeException extends TessaBaseException
{

    private static final long serialVersionUID = 1L;

    public TemplateAttributeException(String message)
    {

	super(message);
    }


    public TemplateAttributeException(String code, String message)
    {

	super(message);
	setCode(code);
    }


    public TemplateAttributeException(String message, Throwable e)
    {

	super(message, e);
    }


    public TemplateAttributeException(String code, String message, Throwable e)
    {

	super(message, e);
	setCode(code);
    }

}
