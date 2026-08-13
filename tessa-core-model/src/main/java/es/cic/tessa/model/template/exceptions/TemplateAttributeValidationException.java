package es.cic.tessa.model.template.exceptions;

import es.cic.tessa.common.exceptions.TessaBaseException;

public class TemplateAttributeValidationException extends TessaBaseException
{

    private static final long serialVersionUID = 1L;

    public TemplateAttributeValidationException(String message)
    {

	super(message);
    }


    public TemplateAttributeValidationException(String code, String message)
    {

	super(message);
	setCode(code);
    }


    public TemplateAttributeValidationException(String message, Throwable e)
    {

	super(message, e);
    }


    public TemplateAttributeValidationException(String code, String message, Throwable e)
    {

	super(message, e);
	setCode(code);
    }

}
