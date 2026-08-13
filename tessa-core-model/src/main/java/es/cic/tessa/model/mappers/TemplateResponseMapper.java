package es.cic.tessa.model.mappers;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import es.cic.tessa.common.model.TessaConstants;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.Template;
import es.cic.tessa.model.dto.TemplateResponse;
import es.cic.tessa.model.dto.json.ResponsePageJson;


@Component
public class TemplateResponseMapper
{

    private TemplateAttributeResponseMapper templateAttributeResponseMapper;

    public TemplateResponseMapper(@Lazy TemplateAttributeResponseMapper templateAttributeResponseMapper)
    {

	this.templateAttributeResponseMapper = templateAttributeResponseMapper;
    }


    public TemplateResponse templateToTemplateRespose(Template template)
    {

	TemplateResponse templateResponse = new TemplateResponse();
	templateResponse.setId(template.getCustomId());
	templateResponse.setName(template.getName());
	templateResponse.setDescription(template.getDescription());
	templateResponse.setIcon(template.getIcon());
	if(template.getInsertDate() != null)
	{
	    templateResponse.setInsertDate(template.getInsertDate().toInstant(ZoneOffset.UTC));
	}

	if(template.getModifDate() != null)
	{
	    templateResponse.setModifDate(template.getModifDate().toInstant(ZoneOffset.UTC));
	}

	templateResponse.setFinalTemplate(template.getFinalTemplate());
	templateResponse.setAbstractTemplate(template.getAbstractTemplate());
	templateResponse.setNumComplexAttributes(template.getNumComplexAttributes());
	templateResponse.setType(template.getType());
	templateResponse.setVersion(template.getVersion());
	templateResponse.setAssetOrganized(template.getAssetOrganized());
	templateResponse.setTemplateOrganized(template.getTemplateOrganized());
	templateResponse.setNemonic(template.getNemonic());

	if(template.getGroups().contains(TessaConstants.SYSTEM))
	{
	    templateResponse.setGroups(TessaConstants.SYSTEM_GROUP);
	}

	return templateResponse;
    }


    public TemplateResponse templateToFullTemplateRespose(Template template)
    {

	TemplateResponse templateResponse = templateToTemplateRespose(template);

	if(template.getExtendsTemplate() != null)
	{
	    templateResponse.setExtendsTemplate(templateToFullTemplateRespose(template.getExtendsTemplate()));
	}

	if(template.getTemplateAttributes() != null && !template.getTemplateAttributes().isEmpty())
	{
	    templateResponse.setTemplateAttributes(templateAttributeResponseMapper.templateAttributeCollectionToTemplateAttributeResposeCollection(true, template.getTemplateAttributes()));
	}

	return templateResponse;
    }


    public ResponsePage<TemplateResponse> templatePageToTemplateResposePage(ResponsePage<Template> templates)
    {

	LinkedHashSet<TemplateResponse> templatesResponse = new LinkedHashSet<>();

	for (Template template : templates)
	{
	    templatesResponse.add(templateToFullTemplateRespose(template));

	}

	return new ResponsePage<>(templatesResponse, templates.getPageable(), templates.getTotalElements());

    }


    public ResponsePage<Template> templateResponsePageToTemplatePage(ResponsePage<TemplateResponse> templatesResponse)
    {

	List<Template> templates = new ArrayList<>();

	for (TemplateResponse templateResponse : templatesResponse)
	{
	    templates.add(templateResponseToTemplate(templateResponse));
	}

	return new ResponsePage<>(templates, templatesResponse.getPageable(), templatesResponse.getTotalElements());

    }


    public ResponsePage<Template> templateCollectionToTemplateResposePage(ResponsePageJson<TemplateResponse> templatesResponse)
    {

	List<Template> templates = new ArrayList<>();

	PageRequest pageRequest = PageRequest.of(templatesResponse.getPageable().getPageNumber(), templatesResponse.getPageable().getPageSize());

	for (TemplateResponse templateResponse : templatesResponse.getContent())
	{

	    Template template = templateResponseToTemplate(templateResponse);

	    templates.add(template);
	}

	return new ResponsePage<>(templates, pageRequest, templatesResponse.getTotalElements());

    }


    public Template templateResponseToTemplate(TemplateResponse templateResponse)
    {

	Template template = new Template();
	template.setCustomId(templateResponse.getId());
	template.setName(templateResponse.getName());
	template.setDescription(templateResponse.getDescription());
	template.setIcon(templateResponse.getIcon());
	template.setVersion(templateResponse.getVersion());
	template.setFinalTemplate(templateResponse.getFinalTemplate());
	template.setAbstractTemplate(templateResponse.getAbstractTemplate());
	template.setNumComplexAttributes(templateResponse.getNumComplexAttributes());
	template.setType(templateResponse.getType());
	template.setInsertDate(LocalDateTime.ofInstant(templateResponse.getInsertDate(), ZoneOffset.UTC));
	if(templateResponse.getModifDate() != null)
	{
	    template.setModifDate(LocalDateTime.ofInstant(templateResponse.getModifDate(), ZoneOffset.UTC));
	}
	template.setAssetOrganized(templateResponse.getAssetOrganized());
	template.setTemplateOrganized(templateResponse.getTemplateOrganized());
	template.setNemonic(templateResponse.getNemonic());

	if(templateResponse.getExtendsTemplate() != null)
	{
	    template.setExtendsTemplate(templateResponseToTemplate(templateResponse.getExtendsTemplate()));
	}

	if(templateResponse.getGroups() != null)
	{
	    template.setGroups(templateResponse.getGroups());
	}

	return template;

    }

}
