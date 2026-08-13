package es.cic.tessa.model.dto;


import java.util.Objects;
import es.cic.tessa.model.dto.support.AbstractEntityResponse;


public class ExpressionParamResponse extends AbstractEntityResponse
{

    private static final long serialVersionUID = 1L;

    private String type;
    private Boolean required;
    private Integer position;
    private TemplateAttributeResponse templateAttributeResponse;
    private DefaultValueAssetValueResponse defaultValueAssetValueResponse;

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


    public TemplateAttributeResponse getTemplateAttributeResponse()
    {

	return templateAttributeResponse;
    }


    public void setTemplateAttributeResponse(TemplateAttributeResponse templateAttributeResponse)
    {

	this.templateAttributeResponse = templateAttributeResponse;
    }


    public DefaultValueAssetValueResponse getDefaultValueAssetValueResponse()
    {

	return defaultValueAssetValueResponse;
    }


    public void setDefaultValueAssetValueResponse(DefaultValueAssetValueResponse defaultValueAssetValueResponse)
    {

	this.defaultValueAssetValueResponse = defaultValueAssetValueResponse;
    }


    @Override
    public String toString()
    {

	return "ExpressionParamResponse [type=" + type + ", required=" + required + ", position=" + position + ", templateAttributeResponse=" + templateAttributeResponse + ", defaultValueAssetValueResponse=" + defaultValueAssetValueResponse + "]";
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(defaultValueAssetValueResponse, position, required, templateAttributeResponse, type);
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
	ExpressionParamResponse other = (ExpressionParamResponse) obj;
	return Objects.equals(defaultValueAssetValueResponse, other.defaultValueAssetValueResponse) && Objects.equals(position, other.position) && Objects.equals(required, other.required) && Objects.equals(templateAttributeResponse, other.templateAttributeResponse) && Objects.equals(type, other.type);
    }

}
