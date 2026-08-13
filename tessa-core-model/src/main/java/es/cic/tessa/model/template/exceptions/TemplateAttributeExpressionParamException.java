package es.cic.tessa.model.template.exceptions;

import es.cic.tessa.common.exceptions.TessaBaseException;

public class TemplateAttributeExpressionParamException extends TessaBaseException
{

    private static final long serialVersionUID = 1L;

    public TemplateAttributeExpressionParamException(String message)
    {

	super(message);
    }


    public TemplateAttributeExpressionParamException(String code, String message)
    {

	super(message);
	setCode(code);
    }


    public TemplateAttributeExpressionParamException(String message, Throwable e)
    {

	super(message, e);
    }


    public TemplateAttributeExpressionParamException(String code, String message, Throwable e)
    {

	super(message, e);
	setCode(code);
    }

}
