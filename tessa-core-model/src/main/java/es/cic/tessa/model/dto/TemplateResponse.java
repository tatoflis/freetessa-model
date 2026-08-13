package es.cic.tessa.model.dto;


import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonProperty;
import es.cic.tessa.model.dto.support.AbstractEntityResponse;
import es.cic.tessa.model.types.TemplateType;


public class TemplateResponse extends AbstractEntityResponse
{

    private static final long serialVersionUID = 1L;

    @JsonProperty("final")
    private Boolean finalTemplate = Boolean.FALSE;

    @JsonProperty("abstract")
    private Boolean abstractTemplate = Boolean.FALSE;

    private Boolean assetOrganized = Boolean.FALSE;

    private Boolean templateOrganized = Boolean.FALSE;

    private String type = TemplateType.COMPLEX.getCode();

    private TemplateResponse extendsTemplate;

    private Collection<TemplateAttributeResponse> templateAttributes;

    private Integer numComplexAttributes = Integer.valueOf(0);

    public Boolean getFinalTemplate()
    {

	return finalTemplate;
    }


    public void setFinalTemplate(Boolean finalTemplate)
    {

	this.finalTemplate = finalTemplate;
    }


    public Boolean getAbstractTemplate()
    {

	return abstractTemplate;
    }


    public void setAbstractTemplate(Boolean abstractTemplate)
    {

	this.abstractTemplate = abstractTemplate;
    }


    public Boolean getAssetOrganized()
    {

	return assetOrganized;
    }


    public void setAssetOrganized(Boolean assetOrganized)
    {

	this.assetOrganized = assetOrganized;
    }


    public Boolean getTemplateOrganized()
    {

	return templateOrganized;
    }


    public void setTemplateOrganized(Boolean templateOrganized)
    {

	this.templateOrganized = templateOrganized;
    }


    public String getType()
    {

	return type;
    }


    public void setType(String type)
    {

	this.type = type;
    }


    public TemplateResponse getExtendsTemplate()
    {

	return extendsTemplate;
    }


    public void setExtendsTemplate(TemplateResponse extendsTemplate)
    {

	this.extendsTemplate = extendsTemplate;
    }


    public Collection<TemplateAttributeResponse> getTemplateAttributes()
    {

	return templateAttributes;
    }


    public void setTemplateAttributes(Collection<TemplateAttributeResponse> templateAttributes)
    {

	this.templateAttributes = templateAttributes;
    }


    public Integer getNumComplexAttributes()
    {

	return numComplexAttributes;
    }


    public void setNumComplexAttributes(Integer numComplexAttributes)
    {

	this.numComplexAttributes = numComplexAttributes;
    }


}
