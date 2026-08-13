package es.cic.tessa.model.historical.dto;


import java.util.Collection;
import java.util.Objects;
import es.cic.tessa.model.dto.FunctionResponse;
import es.cic.tessa.model.dto.support.AbstractEntityResponse;


public class HistoricalAssetValueResponse extends AbstractEntityResponse
{

    private static final long serialVersionUID = 1L;

    private HistoricalAssetReferenceResponse historicalAssetReference;

    private HistoricalTemplateAttributeResponse historicalTemplateAttribute;

    private HistoricalAssetResponse historicalAsset;

    private HistoricalTemplateAttributeCollectionMappingResponse historicalTemplateAttributeCollectionMapping;

    private HistoricalChangeResponse historicalChange;

    private String value;

    private FunctionResponse function;

    private Collection<HistoricalExpressionParamResponse> expressionParams;

    public HistoricalAssetReferenceResponse getHistoricalAssetReference()
    {

	return historicalAssetReference;
    }


    public void setHistoricalAssetReference(HistoricalAssetReferenceResponse historicalAssetReference)
    {

	this.historicalAssetReference = historicalAssetReference;
    }


    public HistoricalTemplateAttributeResponse getHistoricalTemplateAttribute()
    {

	return historicalTemplateAttribute;
    }


    public void setHistoricalTemplateAttribute(HistoricalTemplateAttributeResponse historicalTemplateAttribute)
    {

	this.historicalTemplateAttribute = historicalTemplateAttribute;
    }


    public HistoricalAssetResponse getHistoricalAsset()
    {

	return historicalAsset;
    }


    public void setHistoricalAsset(HistoricalAssetResponse historicalAsset)
    {

	this.historicalAsset = historicalAsset;
    }


    public HistoricalTemplateAttributeCollectionMappingResponse getHistoricalTemplateAttributeCollectionMapping()
    {

	return historicalTemplateAttributeCollectionMapping;
    }


    public void setHistoricalTemplateAttributeCollectionMapping(HistoricalTemplateAttributeCollectionMappingResponse historicalTemplateAttributeCollectionMapping)
    {

	this.historicalTemplateAttributeCollectionMapping = historicalTemplateAttributeCollectionMapping;
    }


    public HistoricalChangeResponse getHistoricalChange()
    {

	return historicalChange;
    }


    public void setHistoricalChange(HistoricalChangeResponse historicalChange)
    {

	this.historicalChange = historicalChange;
    }


    public String getValue()
    {

	return value;
    }


    public void setValue(String value)
    {

	this.value = value;
    }


    public FunctionResponse getFunction()
    {

	return function;
    }


    public void setFunction(FunctionResponse function)
    {

	this.function = function;
    }


    public Collection<HistoricalExpressionParamResponse> getExpressionParams()
    {

	return expressionParams;
    }


    public void setExpressionParams(Collection<HistoricalExpressionParamResponse> expressionParams)
    {

	this.expressionParams = expressionParams;
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(expressionParams, function, historicalAsset, historicalAssetReference, historicalChange, historicalTemplateAttribute, historicalTemplateAttributeCollectionMapping, value);
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
	HistoricalAssetValueResponse other = (HistoricalAssetValueResponse) obj;
	return Objects.equals(expressionParams, other.expressionParams) && Objects.equals(function, other.function) && Objects.equals(historicalAsset, other.historicalAsset) && Objects.equals(historicalAssetReference, other.historicalAssetReference) && Objects.equals(historicalChange, other.historicalChange) && Objects.equals(historicalTemplateAttribute, other.historicalTemplateAttribute) && Objects.equals(historicalTemplateAttributeCollectionMapping, other.historicalTemplateAttributeCollectionMapping) && Objects.equals(value, other.value);
    }


    @Override
    public String toString()
    {

	return "HistoricalAssetValueResponse [historicalAssetReference=" + historicalAssetReference + ", historicalTemplateAttribute=" + historicalTemplateAttribute + ", historicalAsset=" + historicalAsset + ", historicalTemplateAttributeCollectionMapping=" + historicalTemplateAttributeCollectionMapping + ", historicalChange=" + historicalChange + ", value=" + value + ", function=" + function + ", expressionParams=" + expressionParams + "]";
    }

}
