package es.cic.tessa.model.dto;


import java.util.Objects;
import es.cic.tessa.model.dto.support.AbstractIdentificableEntityResponse;
import es.cic.tessa.model.types.ReferenceType;
import es.cic.tessa.model.types.RelationType;


public class TemplateReferenceResponse extends AbstractIdentificableEntityResponse
{

    private static final long serialVersionUID = 1L;

    private ReferenceType referenceType;

    private RelationType relationType;

    private TemplateResponse templateResponse;

    public ReferenceType getReferenceType()
    {

	return referenceType;
    }


    public void setReferenceType(ReferenceType referenceType)
    {

	this.referenceType = referenceType;
    }


    public RelationType getRelationType()
    {

	return relationType;
    }


    public void setRelationType(RelationType relationType)
    {

	this.relationType = relationType;
    }


    public TemplateResponse getTemplateResponse()
    {

	return templateResponse;
    }


    public void setTemplateResponse(TemplateResponse templateResponse)
    {

	this.templateResponse = templateResponse;
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(id, referenceType, relationType, templateResponse);
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
	TemplateReferenceResponse other = (TemplateReferenceResponse) obj;
	return Objects.equals(id, other.id) && referenceType == other.referenceType && relationType == other.relationType && Objects.equals(templateResponse, other.templateResponse);
    }

}
