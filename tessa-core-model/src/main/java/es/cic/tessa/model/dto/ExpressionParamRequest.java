package es.cic.tessa.model.dto;


import java.util.Objects;
import es.cic.tessa.model.dto.support.AbstractEntityRequest;


public class ExpressionParamRequest extends AbstractEntityRequest
{

    private static final long serialVersionUID = 1L;

    private String type;
    private boolean required;
    private int position;
    private Long idTemplateAttribute;
    private DefaultValueAssetValueRequest defaultValueAssetValueRequest;

    public String getType()
    {

	return type;
    }


    public void setType(String type)
    {

	this.type = type;
    }


    public boolean isRequired()
    {

	return required;
    }


    public void setRequired(boolean required)
    {

	this.required = required;
    }


    public int getPosition()
    {

	return position;
    }


    public void setPosition(int position)
    {

	this.position = position;
    }


    public Long getIdTemplateAttribute()
    {

	return idTemplateAttribute;
    }


    public void setIdTemplateAttribute(Long idTemplateAttribute)
    {

	this.idTemplateAttribute = idTemplateAttribute;
    }


    public DefaultValueAssetValueRequest getDefaultValueAssetValueRequest()
    {

	return defaultValueAssetValueRequest;
    }


    public void setDefaultValueAssetValueRequest(DefaultValueAssetValueRequest defaultValueAssetValueRequest)
    {

	this.defaultValueAssetValueRequest = defaultValueAssetValueRequest;
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(defaultValueAssetValueRequest, idTemplateAttribute, position, required, type);
    }


    @Override
    public boolean equals(Object obj)
    {

	if(this == obj)
	    return true;
	if(obj == null)
	    return false;
	if(getClass() != obj.getClass())
	    return false;
	ExpressionParamRequest other = (ExpressionParamRequest) obj;
	return Objects.equals(defaultValueAssetValueRequest, other.defaultValueAssetValueRequest) && Objects.equals(idTemplateAttribute, other.idTemplateAttribute) && position == other.position && required == other.required && Objects.equals(type, other.type);
    }


    @Override
    public String toString()
    {

	return "ExpressionParamRequest [type=" + type + ", required=" + required + ", position=" + position + ", idTemplateAttribute=" + idTemplateAttribute + ", defaultValueAssetValueRequest=" + defaultValueAssetValueRequest + "]";
    }

}
