package es.cic.tessa.model.historical.dto;


import java.util.Objects;
import es.cic.tessa.model.dto.support.AbstractEntityResponse;


public class HistoricalExpressionParamResponse extends AbstractEntityResponse
{

    private static final long serialVersionUID = 1L;

    private String type;
    private Boolean required;
    private Integer position;
    private HistoricalTemplateAttributeResponse templateAttributeResponse;
    private HistoricalDefaultValueResponse defaultValueAssetValueResponse;
    private HistoricalChangeResponse historicalChange;

    public String getType()
    {

	return type;
    }


    public void setType(String type)
    {

	this.type = type;
    }


    public Boolean getRequired()
    {

	return required;
    }


    public void setRequired(Boolean required)
    {

	this.required = required;
    }


    public Integer getPosition()
    {

	return position;
    }


    public void setPosition(Integer position)
    {

	this.position = position;
    }


    public HistoricalTemplateAttributeResponse getTemplateAttributeResponse()
    {

	return templateAttributeResponse;
    }


    public void setTemplateAttributeResponse(HistoricalTemplateAttributeResponse templateAttributeResponse)
    {

	this.templateAttributeResponse = templateAttributeResponse;
    }


    public HistoricalDefaultValueResponse getDefaultValueAssetValueResponse()
    {

	return defaultValueAssetValueResponse;
    }


    public void setDefaultValueAssetValueResponse(HistoricalDefaultValueResponse defaultValueAssetValueResponse)
    {

	this.defaultValueAssetValueResponse = defaultValueAssetValueResponse;
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
	result = prime * result + Objects.hash(defaultValueAssetValueResponse, position, required, templateAttributeResponse, type, historicalChange);
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
	HistoricalExpressionParamResponse other = (HistoricalExpressionParamResponse) obj;
	return Objects.equals(defaultValueAssetValueResponse, other.defaultValueAssetValueResponse) && Objects.equals(position, other.position) && Objects.equals(required, other.required) && Objects.equals(templateAttributeResponse, other.templateAttributeResponse) && Objects.equals(type, other.type) && Objects.equals(historicalChange, other.historicalChange);
    }


    @Override
    public String toString()
    {

	return "HistoricalExpressionParamResponse [type=" + type + ", required=" + required + ", position=" + position + ", templateAttributeResponse=" + templateAttributeResponse + ", defaultValueAssetValueResponse=" + defaultValueAssetValueResponse + ", historicalChange=" + historicalChange + "]";
    }

}
