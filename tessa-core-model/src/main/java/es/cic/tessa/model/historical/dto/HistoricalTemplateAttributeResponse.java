package es.cic.tessa.model.historical.dto;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import es.cic.tessa.model.dto.FunctionResponse;
import es.cic.tessa.model.dto.support.AbstractEntityResponse;


public class HistoricalTemplateAttributeResponse extends AbstractEntityResponse
{

    private static final long serialVersionUID = 1L;

    private String type;

    private Integer minLength;

    private Integer maxLength;

    private Boolean required;

    private Boolean hidden;

    private Boolean hasDefaultValue;

    private String defaultValue;

    private Boolean hasCalculatedValue;

    private String calculatedValue;

    private FunctionResponse expressionProperties;

    private String pattern;

    private Boolean unique;

    private Boolean externalSource;

    private Boolean collection;

    private Boolean withcapacity;

    private Integer capacity;

    private Boolean mapping;

    private Boolean finalAttribute;

    private Boolean passwordAttribute;

    private Boolean identificator;

    private Boolean alias;

    private HistoricalTemplateResponse historicalTemplate;

    private HistoricalTemplateReferenceResponse historicalTemplateReference;

    private HistoricalChangeResponse historicalChange;

    private Integer position;

    private String contentType;

    private Collection<String> enums = new ArrayList<>();

    private Collection<HistoricalExpressionParamResponse> expressionParams = new ArrayList<>();

    private Collection<HistoricalHashtagResponse> hashtags = new ArrayList<>();

    private Collection<HistoricalTemplateAttributeCollectionMappingResponse> templateAttributeCollectionMapping = new ArrayList<>();

    private Long nodeId;

    public String getType()
    {

	return type;
    }


    public void setType(String type)
    {

	this.type = type;
    }


    public Integer getMinLength()
    {

	return minLength;
    }


    public void setMinLength(Integer minLength)
    {

	this.minLength = minLength;
    }


    public Integer getMaxLength()
    {

	return maxLength;
    }


    public void setMaxLength(Integer maxLength)
    {

	this.maxLength = maxLength;
    }


    public Boolean getRequired()
    {

	return required;
    }


    public void setRequired(Boolean required)
    {

	this.required = required;
    }


    public Boolean getHidden()
    {

	return hidden;
    }


    public void setHidden(Boolean hidden)
    {

	this.hidden = hidden;
    }


    public Boolean getHasDefaultValue()
    {

	return hasDefaultValue;
    }


    public void setHasDefaultValue(Boolean hasDefaultValue)
    {

	this.hasDefaultValue = hasDefaultValue;
    }


    public String getDefaultValue()
    {

	return defaultValue;
    }


    public void setDefaultValue(String defaultValue)
    {

	this.defaultValue = defaultValue;
    }


    public Boolean getHasCalculatedValue()
    {

	return hasCalculatedValue;
    }


    public void setHasCalculatedValue(Boolean hasCalculatedValue)
    {

	this.hasCalculatedValue = hasCalculatedValue;
    }


    public String getCalculatedValue()
    {

	return calculatedValue;
    }


    public void setCalculatedValue(String calculatedValue)
    {

	this.calculatedValue = calculatedValue;
    }


    public String getPattern()
    {

	return pattern;
    }


    public void setPattern(String pattern)
    {

	this.pattern = pattern;
    }


    public Boolean getUnique()
    {

	return unique;
    }


    public void setUnique(Boolean unique)
    {

	this.unique = unique;
    }


    public Boolean getExternalSource()
    {

	return externalSource;
    }


    public void setExternalSource(Boolean externalSource)
    {

	this.externalSource = externalSource;
    }


    public Boolean getCollection()
    {

	return collection;
    }


    public void setCollection(Boolean collection)
    {

	this.collection = collection;
    }


    public HistoricalTemplateResponse getHistoricalTemplate()
    {

	return historicalTemplate;
    }


    public void setHistoricalTemplate(HistoricalTemplateResponse historicalTemplate)
    {

	this.historicalTemplate = historicalTemplate;
    }


    public HistoricalTemplateReferenceResponse getHistoricalTemplateReference()
    {

	return historicalTemplateReference;
    }


    public void setHistoricalTemplateReference(HistoricalTemplateReferenceResponse historicalTemplateReference)
    {

	this.historicalTemplateReference = historicalTemplateReference;
    }


    public HistoricalChangeResponse getHistoricalChange()
    {

	return historicalChange;
    }


    public void setHistoricalChange(HistoricalChangeResponse historicalChange)
    {

	this.historicalChange = historicalChange;
    }


    public Boolean getWithcapacity()
    {

	return withcapacity;
    }


    public void setWithcapacity(Boolean withcapacity)
    {

	this.withcapacity = withcapacity;
    }


    public Integer getCapacity()
    {

	return capacity;
    }


    public void setCapacity(Integer capacity)
    {

	this.capacity = capacity;
    }


    public Boolean getMapping()
    {

	return mapping;
    }


    public void setMapping(Boolean mapping)
    {

	this.mapping = mapping;
    }


    public Boolean getFinalAttribute()
    {

	return finalAttribute;
    }


    public void setFinalAttribute(Boolean finalAttribute)
    {

	this.finalAttribute = finalAttribute;
    }


    public Boolean getPasswordAttribute()
    {

	return passwordAttribute;
    }


    public void setPasswordAttribute(Boolean passwordAttribute)
    {

	this.passwordAttribute = passwordAttribute;
    }


    public Boolean getIdentificator()
    {

	return identificator;
    }


    public void setIdentificator(Boolean identificator)
    {

	this.identificator = identificator;
    }


    public Boolean getAlias()
    {

	return alias;
    }


    public void setAlias(Boolean alias)
    {

	this.alias = alias;
    }


    public Integer getPosition()
    {

	return position;
    }


    public void setPosition(Integer position)
    {

	this.position = position;
    }


    public String getContentType()
    {

	return contentType;
    }


    public void setContentType(String contentType)
    {

	this.contentType = contentType;
    }


    public Collection<String> getEnums()
    {

	return enums;
    }


    public void setEnums(Collection<String> enums)
    {

	this.enums = enums;
    }


    public Collection<HistoricalExpressionParamResponse> getExpressionParams()
    {

	return expressionParams;
    }


    public void setExpressionParams(Collection<HistoricalExpressionParamResponse> expressionParams)
    {

	this.expressionParams = expressionParams;
    }


    public Collection<HistoricalHashtagResponse> getHashtags()
    {

	return hashtags;
    }


    public void setHashtags(Collection<HistoricalHashtagResponse> hashtags)
    {

	this.hashtags = hashtags;
    }


    public Collection<HistoricalTemplateAttributeCollectionMappingResponse> getTemplateAttributeCollectionMapping()
    {

	return templateAttributeCollectionMapping;
    }


    public void setTemplateAttributeCollectionMapping(Collection<HistoricalTemplateAttributeCollectionMappingResponse> templateAttributeCollectionMapping)
    {

	this.templateAttributeCollectionMapping = templateAttributeCollectionMapping;
    }


    public Long getNodeId()
    {

	return nodeId;
    }


    public void setNodeId(Long nodeId)
    {

	this.nodeId = nodeId;
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(alias, calculatedValue, capacity, collection, contentType, defaultValue, externalSource, finalAttribute, hasCalculatedValue, hasDefaultValue, hidden, historicalChange, identificator, mapping, maxLength, minLength, nodeId, passwordAttribute, pattern, position, required, historicalTemplate, historicalTemplateReference, type, unique, withcapacity);
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
	HistoricalTemplateAttributeResponse other = (HistoricalTemplateAttributeResponse) obj;
	return Objects.equals(alias, other.alias) && Objects.equals(calculatedValue, other.calculatedValue) && Objects.equals(capacity, other.capacity) && Objects.equals(collection, other.collection) && Objects.equals(contentType, other.contentType) && Objects.equals(defaultValue, other.defaultValue) && Objects.equals(externalSource, other.externalSource) && Objects.equals(finalAttribute, other.finalAttribute) && Objects.equals(hasCalculatedValue, other.hasCalculatedValue) && Objects.equals(hasDefaultValue, other.hasDefaultValue) && Objects.equals(hidden, other.hidden) && Objects.equals(historicalChange, other.historicalChange) && Objects.equals(identificator, other.identificator) && Objects.equals(mapping, other.mapping) && Objects.equals(maxLength, other.maxLength) && Objects.equals(minLength, other.minLength) && Objects.equals(nodeId, other.nodeId) && Objects.equals(passwordAttribute, other.passwordAttribute) && Objects.equals(pattern, other.pattern) && Objects.equals(position, other.position) && Objects.equals(required, other.required) && Objects.equals(historicalTemplate, other.historicalTemplate) && Objects.equals(historicalTemplateReference, other.historicalTemplateReference) && Objects.equals(type, other.type) && Objects.equals(unique, other.unique) && Objects.equals(withcapacity, other.withcapacity);
    }


    public FunctionResponse getExpressionProperties()
    {

	return expressionProperties;
    }


    public void setExpressionProperties(FunctionResponse expressionProperties)
    {

	this.expressionProperties = expressionProperties;
    }

}
