package es.cic.tessa.model.historical.dto;


import java.util.Objects;
import es.cic.tessa.model.dto.support.AbstractEntityResponse;


public class HistoricalTemplateAttributeCollectionMappingResponse extends AbstractEntityResponse
{

    private static final long serialVersionUID = 1L;

    private Integer position;

    private HistoricalTemplateAttributeResponse historicalTemplateAttribute;

    private String historicalChangeOperation;

    private HistoricalChangeResponse historicalChange;

    public Integer getPosition()
    {

	return position;
    }


    public void setPosition(Integer position)
    {

	this.position = position;
    }


    public HistoricalTemplateAttributeResponse getHistoricalTemplateAttribute()
    {

	return historicalTemplateAttribute;
    }


    public void setHistoricalTemplateAttribute(HistoricalTemplateAttributeResponse historicalTemplateAttribute)
    {

	this.historicalTemplateAttribute = historicalTemplateAttribute;
    }


    public String getHistoricalChangeOperation()
    {

	return historicalChangeOperation;
    }


    public void setHistoricalChangeOperation(String historicalChangeOperation)
    {

	this.historicalChangeOperation = historicalChangeOperation;
    }


    public HistoricalChangeResponse getHistoricalChange()
    {

	return historicalChange;
    }


    public void setHistoricalChange(HistoricalChangeResponse historicalChange)
    {

	this.historicalChange = historicalChange;
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(historicalTemplateAttribute, position, historicalChangeOperation, historicalChange);
	return result;
    }


    @Override
    public boolean equals(Object obj)
    {

	if(this == obj)
	    return true;
	if(!super.equals(obj))
	    return false;
	if(getClass() != obj.getClass())
	    return false;
	HistoricalTemplateAttributeCollectionMappingResponse other = (HistoricalTemplateAttributeCollectionMappingResponse) obj;
	return Objects.equals(historicalTemplateAttribute, other.historicalTemplateAttribute) && Objects.equals(position, other.position) && Objects.equals(historicalChangeOperation, other.historicalChangeOperation) && Objects.equals(historicalChange, other.historicalChange);
    }

}
