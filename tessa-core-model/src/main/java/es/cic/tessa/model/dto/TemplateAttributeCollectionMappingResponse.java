package es.cic.tessa.model.dto;


import es.cic.tessa.model.dto.support.AbstractEntityResponse;


public class TemplateAttributeCollectionMappingResponse extends AbstractEntityResponse
{

    private static final long serialVersionUID = 1L;

    private String calculatedValue;

    // WORKAROUND swagger bug
    private TemplateAttributeResponse2 templateAttribute;

    private Integer position;

    public String getCalculatedValue()
    {

	return calculatedValue;
    }


    public void setCalculatedValue(String calculatedValue)
    {

	this.calculatedValue = calculatedValue;
    }


    public TemplateAttributeResponse2 getTemplateAttribute()
    {

	return templateAttribute;
    }


    public void setTemplateAttribute(TemplateAttributeResponse2 templateAttribute)
    {

	this.templateAttribute = templateAttribute;
    }


    public Integer getPosition()
    {

	return position;
    }


    public void setPosition(Integer position)
    {

	this.position = position;
    }

}
