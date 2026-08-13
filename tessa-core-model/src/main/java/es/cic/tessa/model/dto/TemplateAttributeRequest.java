package es.cic.tessa.model.dto;


import java.util.Collection;
import java.util.List;
import es.cic.tessa.model.dto.support.AbstractEntityRequest;


public class TemplateAttributeRequest extends AbstractEntityRequest
{

    private static final long serialVersionUID = 1L;

    private String type;

    private Integer minLength;

    private Integer maxLength;

    private boolean required;

    private boolean hidden;

    private boolean hasDefaultValue;

    private String defaultValue;

    private boolean hasCalculatedValue;

    private String calculatedValue;

    private FunctionRequest expressionProperties;

    private String pattern;

    private boolean unique;

    private boolean externalSource;

    private boolean collection;

    private boolean withcapacity;

    private Integer capacity;

    private boolean mapping;

    private boolean finalAttribute;

    private boolean password;

    private boolean identificator;

    private boolean alias;

    private Long idTemplateReference;

    private Long idTemplate;

    private Integer position;

    private String relationType;

    private String referenceType;

    private String contentType;

    private List<String> enums;

    private Collection<ExpressionParamRequest> expressionParams;

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


    public boolean isRequired()
    {

	return required;
    }


    public void setRequired(boolean required)
    {

	this.required = required;
    }


    public boolean isHidden()
    {

	return hidden;
    }


    public void setHidden(boolean hidden)
    {

	this.hidden = hidden;
    }


    public boolean isHasDefaultValue()
    {

	return hasDefaultValue;
    }


    public void setHasDefaultValue(boolean hasDefaultValue)
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


    public FunctionRequest getExpressionProperties()
    {

	return expressionProperties;
    }


    public void setExpressionProperties(FunctionRequest expressionProperties)
    {

	this.expressionProperties = expressionProperties;
    }


    public String getPattern()
    {

	return pattern;
    }


    public void setPattern(String pattern)
    {

	this.pattern = pattern;
    }


    public boolean isUnique()
    {

	return unique;
    }


    public void setUnique(boolean unique)
    {

	this.unique = unique;
    }


    public boolean isExternalSource()
    {

	return externalSource;
    }


    public void setExternalSource(boolean externalSource)
    {

	this.externalSource = externalSource;
    }


    public boolean isCollection()
    {

	return collection;
    }


    public void setCollection(boolean collection)
    {

	this.collection = collection;
    }


    public boolean isWithcapacity()
    {

	return withcapacity;
    }


    public void setWithcapacity(boolean withcapacity)
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


    public boolean isMapping()
    {

	return mapping;
    }


    public void setMapping(boolean mapping)
    {

	this.mapping = mapping;
    }


    public boolean isFinalAttribute()
    {

	return finalAttribute;
    }


    public void setFinalAttribute(boolean finalAttribute)
    {

	this.finalAttribute = finalAttribute;
    }


    public boolean isIdentificator()
    {

	return identificator;
    }


    public void setIdentificator(boolean identificator)
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


    public String getRelationType()
    {

	return relationType;
    }


    public void setRelationType(String relationType)
    {

	this.relationType = relationType;
    }


    public String getReferenceType()
    {

	return referenceType;
    }


    public void setReferenceType(String referenceType)
    {

	this.referenceType = referenceType;
    }


    public boolean isHasCalculatedValue()
    {

	return hasCalculatedValue;
    }


    public void setHasCalculatedValue(boolean hasCalculatedValue)
    {

	this.hasCalculatedValue = hasCalculatedValue;
    }


    public boolean isPassword()
    {

	return password;
    }


    public void setPassword(boolean password)
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


    public List<String> getEnums()
    {

	return enums;
    }


    public void setEnums(List<String> enums)
    {

	this.enums = enums;
    }


    public Long getIdTemplateReference()
    {

	return idTemplateReference;
    }


    public void setIdTemplateReference(Long idTemplateReference)
    {

	this.idTemplateReference = idTemplateReference;
    }


    public Long getIdTemplate()
    {

	return idTemplate;
    }


    public void setIdTemplate(Long idTemplate)
    {

	this.idTemplate = idTemplate;
    }


    public boolean isAlias()
    {

	return alias;
    }


    public void setAlias(boolean alias)
    {

	this.alias = alias;
    }


    public Collection<ExpressionParamRequest> getExpressionParams()
    {

	return expressionParams;
    }


    public void setExpressionParams(Collection<ExpressionParamRequest> expressionParams)
    {

	this.expressionParams = expressionParams;
    }

}
