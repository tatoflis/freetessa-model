package es.cic.tessa.model.template.exceptions;


import es.cic.tessa.common.exceptions.TessaBaseException;


public class TemplateAttributeCollectionMappingException extends TessaBaseException
{

    private static final long serialVersionUID = 1L;

    public TemplateAttributeCollectionMappingException(String message)
    {

	super(message);
    }


    public TemplateAttributeCollectionMappingException(String code, String message)
    {

	super(message);
	setCode(code);
    }


    public TemplateAttributeCollectionMappingException(String message, Throwable e)
    {

	super(message, e);
    }


    public TemplateAttributeCollectionMappingException(String code, String message, Throwable e)
    {

	super(message, e);
	setCode(code);
    }

}
