package es.cic.tessa.model.dto;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import es.cic.tessa.model.dto.support.AbstractEntityResponse;


public class TemplateAttributeResponse extends AbstractEntityResponse
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

    private Collection<String> enums = new ArrayList<>();

    private Boolean withcapacity;

    private Integer capacity;

    private Boolean mapping;

    private Boolean finalAttribute;

    private Boolean password;

    private Boolean identificator;

    private Boolean alias;

    private TemplateResponse template;

    private TemplateReferenceResponse templateReference;

    private Collection<ExpressionParamResponse> expressionParams = new HashSet<>();

    private Integer position;

    private String contentType;

    public TemplateResponse getTemplate()
    {

	return template;
    }


    public void setTemplate(TemplateResponse template)
    {

	this.template = template;
    }


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


    public Collection<String> getEnums()
    {

	return enums;
    }


    public void setEnums(Collection<String> enums)
    {

	this.enums = enums;
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


    public TemplateReferenceResponse getTemplateReference()
    {

	return templateReference;
    }


    public void setTemplateReference(TemplateReferenceResponse templateReference)
    {

	this.templateReference = templateReference;
    }


    public Boolean getIdentificator()
    {

	return identificator;
    }


    public void setIdentificator(Boolean identificator)
    {

	this.identificator = identificator;
    }


    public Integer getPosition()
    {

	return position;
    }


    public void setPosition(Integer position)
    {

	this.position = position;
    }


    public Boolean getHasCalculatedValue()
    {

	return hasCalculatedValue;
    }


    public void setHasCalculatedValue(Boolean hasCalculatedValue)
    {

	this.hasCalculatedValue = hasCalculatedValue;
    }


    public Boolean getPassword()
    {

	return password;
    }


    public void setPassword(Boolean password)
    {

	this.password = password;
    }


    public String getContentType()
    {

	return contentType;
    }


    public void setContentType(String contentType)
    {

	this.contentType = contentType;
    }


    public Boolean getAlias()
    {

	return alias;
    }


    public void setAlias(Boolean alias)
    {

	this.alias = alias;
    }


    public Collection<ExpressionParamResponse> getExpressionParams()
    {

	return expressionParams;
    }


    public void setExpressionParams(Collection<ExpressionParamResponse> expressionParams)
    {

	this.expressionParams = expressionParams;
    }


    public FunctionResponse getExpressionProperties()
    {

	return expressionProperties;
    }


    public void setExpressionProperties(FunctionResponse expressionProperties)
    {

	this.expressionProperties = expressionProperties;
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(alias, calculatedValue, capacity, collection, contentType, defaultValue, expressionParams, expressionProperties, externalSource, finalAttribute, hasCalculatedValue, hasDefaultValue, hidden, identificator, mapping, maxLength, minLength, password, pattern, position, required, template, templateReference, type, unique, withcapacity);
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
	TemplateAttributeResponse other = (TemplateAttributeResponse) obj;
	return Objects.equals(alias, other.alias) && Objects.equals(calculatedValue, other.calculatedValue) && Objects.equals(capacity, other.capacity) && Objects.equals(collection, other.collection) && Objects.equals(contentType, other.contentType) && Objects.equals(defaultValue, other.defaultValue) && Objects.equals(expressionParams, other.expressionParams) && Objects.equals(expressionProperties, other.expressionProperties) && Objects.equals(externalSource, other.externalSource) && Objects.equals(finalAttribute, other.finalAttribute) && Objects.equals(hasCalculatedValue, other.hasCalculatedValue) && Objects.equals(hasDefaultValue, other.hasDefaultValue) && Objects.equals(hidden, other.hidden) && Objects.equals(identificator, other.identificator) && Objects.equals(mapping, other.mapping) && Objects.equals(maxLength, other.maxLength) && Objects.equals(minLength, other.minLength) && Objects.equals(password, other.password) && Objects.equals(pattern, other.pattern) && Objects.equals(position, other.position) && Objects.equals(required, other.required) && Objects.equals(template, other.template) && Objects.equals(templateReference, other.templateReference) && Objects.equals(type, other.type) && Objects.equals(unique, other.unique) && Objects.equals(withcapacity, other.withcapacity);
    }

}
