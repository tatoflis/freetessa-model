package es.cic.tessa.model.dto;


import es.cic.tessa.model.dto.support.AbstractEntityResponse;


public class TemplateAttributeResponse2 extends AbstractEntityResponse
{

    private static final long serialVersionUID = 1L;

    private String type;

    private Integer minLength;

    private Integer maxLength;

    private Boolean required;

    private Boolean hasDefaultValue;

    private String defaultValue;

    private String cronExpression;

    private String cronTimeZone;

    private Long cronDelay;

    private Boolean hasCalculatedValue;

    private String calculatedValue;

    private String expressionFunction;

    private boolean refillingCalculation;

    private boolean ignoreNoData;

    private String pattern;

    private Boolean unique;

    private Boolean externalSource;

    private Boolean collection;

    private Boolean withcapacity;

    private Integer capacity;

    private Boolean mapping;

    private Boolean finalAttribute;

    private Boolean password;

    private Boolean identificator;

    private TemplateReferenceResponse templateReference;

    private TemplateResponse template;

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


    public String getCronExpression()
    {

	return cronExpression;
    }


    public void setCronExpression(String cronExpression)
    {

	this.cronExpression = cronExpression;
    }


    public String getCalculatedValue()
    {

	return calculatedValue;
    }


    public void setCalculatedValue(String calculatedValue)
    {

	this.calculatedValue = calculatedValue;
    }


    public String getExpressionFunction()
    {

	return expressionFunction;
    }


    public void setExpressionFunction(String expressionFunction)
    {

	this.expressionFunction = expressionFunction;
    }


    public boolean isRefillingCalculation()
    {

	return refillingCalculation;
    }


    public void setRefillingCalculation(boolean refillingCalculation)
    {

	this.refillingCalculation = refillingCalculation;
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


    public boolean isIgnoreNoData()
    {

	return ignoreNoData;
    }


    public void setIgnoreNoData(boolean ignoreNoData)
    {

	this.ignoreNoData = ignoreNoData;
    }


    public Long getCronDelay()
    {

	return cronDelay;
    }


    public void setCronDelay(Long cronDelay)
    {

	this.cronDelay = cronDelay;
    }


    public String getCronTimeZone()
    {

	return cronTimeZone;
    }


    public void setCronTimeZone(String cronTimeZone)
    {

	this.cronTimeZone = cronTimeZone;
    }

}
