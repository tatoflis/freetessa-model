package es.cic.tessa.model.dto;


import es.cic.tessa.model.dto.support.AbstractEntityRequest;


public class TemplateAttributeCollectionMappingRequest extends AbstractEntityRequest
{

    private static final long serialVersionUID = 1L;

    private String calculatedValue;

    private Integer position;

    public String getCalculatedValue()
    {

	return calculatedValue;
    }


    public void setCalculatedValue(String calculatedValue)
    {

	this.calculatedValue = calculatedValue;
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
