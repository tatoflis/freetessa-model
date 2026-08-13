package es.cic.tessa.model.mappers;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.Template;
import es.cic.tessa.model.dto.TemplateRequest;


@Component
public class TemplateRequestMapper
{

    public Template templateRequestToTemplate(TemplateRequest templateRequest, Set<String> groups)
    {

	Template template = new Template();
	template.setCustomId(templateRequest.getId());
	template.setName(templateRequest.getName());
	template.setNameLower(templateRequest.getName().toLowerCase());
	template.setDescription(templateRequest.getDescription());
	template.setIcon(templateRequest.getIcon());
	template.setType(templateRequest.getType());
	template.setAbstractTemplate(templateRequest.isAbstractTemplate());
	template.setFinalTemplate(templateRequest.isFinalTemplate());
	template.setAssetOrganized(templateRequest.isAssetOrganized());
	template.setTemplateOrganized(templateRequest.isTemplateOrganized());
	template.setGroups(groups);
	if(templateRequest.getVersion() != null)
	{
	    template.setVersion(templateRequest.getVersion());
	}

	if(templateRequest.getIdExtendsTemplate() != null)
	{
	    Template extendsTemplate = new Template();
	    extendsTemplate.setCustomId(templateRequest.getIdExtendsTemplate());

	    template.setExtendsTemplate(extendsTemplate);
	}

	if(templateRequest.getModifDate() != null)
	{
	    template.setModifDate(templateRequest.getModifDate());
	}

	return template;

    }


    public ResponsePage<Template> templateRequestPageToTemplatePage(ResponsePage<TemplateRequest> templatesRequest, Set<String> groups)
    {

	List<Template> templates = new ArrayList<>();

	for (TemplateRequest templateRequest : templatesRequest)
	{
	    templates.add(templateRequestToTemplate(templateRequest, groups));
	}

	return new ResponsePage<>(templates, templatesRequest.getPageable(), templatesRequest.getTotalElements());

    }

}
