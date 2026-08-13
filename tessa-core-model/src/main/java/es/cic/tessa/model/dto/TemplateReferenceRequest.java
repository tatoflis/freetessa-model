package es.cic.tessa.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import es.cic.tessa.model.types.ReferenceType;
import es.cic.tessa.model.types.RelationType;


public class TemplateReferenceRequest
{

    @JsonProperty("id")
    private Long id;

    @JsonProperty("template")
    private Long idTemplate;

    private ReferenceType templateReferenceType = ReferenceType.COMPLEX;

    private RelationType templateRelationType = RelationType.AGGREGATION;

    public Long getId()
    {

	return id;
    }


    public void setId(Long id)
    {

	this.id = id;
    }


    public Long getIdTemplate()
    {

	return idTemplate;
    }


    public void setIdTemplate(Long idTemplate)
    {

	this.idTemplate = idTemplate;
    }


    public ReferenceType getTemplateReferenceType()
    {

	return templateReferenceType;
    }


    public void setTemplateReferenceType(ReferenceType templateReferenceType)
    {

	this.templateReferenceType = templateReferenceType;
    }


    public RelationType getTemplateRelationType()
    {

	return templateRelationType;
    }


    public void setTemplateRelationType(RelationType templateRelationType)
    {

	this.templateRelationType = templateRelationType;
    }

}
