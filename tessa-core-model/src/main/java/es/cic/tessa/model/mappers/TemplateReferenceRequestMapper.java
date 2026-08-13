package es.cic.tessa.model.mappers;


import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.Template;
import es.cic.tessa.model.TemplateReference;
import es.cic.tessa.model.dto.TemplateReferenceRequest;


@Component
public class TemplateReferenceRequestMapper
{

    public TemplateReference templateReferenceRequestToTemplateReference(TemplateReferenceRequest templateReferenceRequest)
    {

	TemplateReference templateReference = new TemplateReference();
	templateReference.setId(templateReferenceRequest.getId());

	Template template = new Template();
	template.setCustomId(templateReferenceRequest.getIdTemplate());
	templateReference.setTemplate(template);
	templateReference.setTemplateRelationType(templateReferenceRequest.getTemplateRelationType().getCode());
	templateReference.setTemplateReferenceType(templateReferenceRequest.getTemplateReferenceType().getCode());

	return templateReference;

    }


    public ResponsePage<TemplateReference> templateReferenceRequestPageToTemplateReferencePage(ResponsePage<TemplateReferenceRequest> templateReferencesRequest)
    {

	List<TemplateReference> templateReferences = new ArrayList<TemplateReference>();

	for (TemplateReferenceRequest templateReferenceRequest : templateReferencesRequest)
	{
	    templateReferences.add(templateReferenceRequestToTemplateReference(templateReferenceRequest));
	}

	return new ResponsePage<TemplateReference>(templateReferences, templateReferencesRequest.getPageable(), templateReferencesRequest.getTotalElements());

    }

}
