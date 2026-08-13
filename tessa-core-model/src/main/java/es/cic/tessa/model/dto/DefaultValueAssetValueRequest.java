package es.cic.tessa.model.dto;


import es.cic.tessa.model.dto.support.AbstractEntityRequest;


public class DefaultValueAssetValueRequest extends AbstractEntityRequest
{

    private static final long serialVersionUID = 1L;

    private int order;
    private String value;

    public int getOrder()
    {

	return order;
    }


    public void setOrder(int order)
    {

	this.order = order;
    }


    public String getValue()
    {

	return value;
    }


    public void setValue(String value)
    {

	this.value = value;
    }

}
