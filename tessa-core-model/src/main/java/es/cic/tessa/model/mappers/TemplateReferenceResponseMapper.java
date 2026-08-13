package es.cic.tessa.model.mappers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import es.cic.tessa.model.TemplateReference;
import es.cic.tessa.model.dto.TemplateReferenceResponse;
import es.cic.tessa.model.types.ReferenceType;
import es.cic.tessa.model.types.RelationType;


@Component
public class TemplateReferenceResponseMapper
{

    @Autowired
    private TemplateResponseMapper templateResponseMapper;

    public TemplateReferenceResponse templateReferenceToTemplateReferenceRespose(TemplateReference templateReference)
    {

	TemplateReferenceResponse templateReferenceResponse = new TemplateReferenceResponse();

	templateReferenceResponse.setId(templateReference.getId());
	templateReferenceResponse.setReferenceType(ReferenceType.fromString(templateReference.getTemplateReferenceType()));
	templateReferenceResponse.setRelationType(RelationType.fromString(templateReference.getTemplateRelationType()));
	templateReferenceResponse.setTemplateResponse(templateResponseMapper.templateToFullTemplateRespose(templateReference.getTemplate()));

	return templateReferenceResponse;
    }


    public TemplateReference templateReferenceResponseToTemplateReference(TemplateReferenceResponse templateReferenceResponse)
    {

	TemplateReference templateReference = new TemplateReference();
	templateReference.setId(templateReferenceResponse.getId());
	templateReference.setTemplateRelationType(templateReferenceResponse.getRelationType().getCode());
	templateReference.setTemplateReferenceType(templateReferenceResponse.getReferenceType().getCode());
	templateReference.setTemplate(templateResponseMapper.templateResponseToTemplate(templateReferenceResponse.getTemplateResponse()));

	return templateReference;

    }


    public TemplateReferenceResponse recursiveTemplateReferenceToTemplateReferenceRespose(TemplateReference templateReference)
    {

	TemplateReferenceResponse templateReferenceResponse = new TemplateReferenceResponse();

	templateReferenceResponse.setId(templateReference.getId());
	templateReferenceResponse.setReferenceType(ReferenceType.fromString(templateReference.getTemplateReferenceType()));
	templateReferenceResponse.setRelationType(RelationType.fromString(templateReference.getTemplateRelationType()));
	templateReferenceResponse.setTemplateResponse(templateResponseMapper.templateToTemplateRespose(templateReference.getTemplate()));;

	return templateReferenceResponse;
    }

}
