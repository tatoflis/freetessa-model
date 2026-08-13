package es.cic.tessa.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import es.cic.tessa.model.dto.support.AbstractEntityRequest;
import es.cic.tessa.model.types.TemplateType;


public class TemplateRequest extends AbstractEntityRequest
{

    private static final long serialVersionUID = 1L;

    @JsonProperty("final")
    private boolean finalTemplate;

    @JsonProperty("abstract")
    private boolean abstractTemplate;

    private boolean assetOrganized;

    private boolean templateOrganized;

    private String type = TemplateType.COMPLEX.getCode();

    private Long idExtendsTemplate;

    public boolean isFinalTemplate()
    {

	return finalTemplate;
    }


    public void setFinalTemplate(boolean finalTemplate)
    {

	this.finalTemplate = finalTemplate;
    }


    public boolean isAbstractTemplate()
    {

	return abstractTemplate;
    }


    public void setAbstractTemplate(boolean abstractTemplate)
    {

	this.abstractTemplate = abstractTemplate;
    }


    public boolean isAssetOrganized()
    {

	return assetOrganized;
    }


    public void setAssetOrganized(boolean assetOrganized)
    {

	this.assetOrganized = assetOrganized;
    }


    public boolean isTemplateOrganized()
    {

	return templateOrganized;
    }


    public void setTemplateOrganized(boolean templateOrganized)
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


    public Long getIdExtendsTemplate()
    {

	return idExtendsTemplate;
    }


    public void setIdExtendsTemplate(Long idExtendsTemplate)
    {

	this.idExtendsTemplate = idExtendsTemplate;
    }

}
