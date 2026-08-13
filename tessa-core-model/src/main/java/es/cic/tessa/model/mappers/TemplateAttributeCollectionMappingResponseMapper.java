package es.cic.tessa.model.mappers;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.TemplateAttributeCollectionMapping;
import es.cic.tessa.model.dto.TemplateAttributeCollectionMappingResponse;
import es.cic.tessa.model.dto.json.ResponsePageJson;


@Component
public class TemplateAttributeCollectionMappingResponseMapper
{

    @Autowired
    private TemplateAttributeResponseMapper templateAttributeResponseMapper;

    public TemplateAttributeCollectionMappingResponse templateAttributeCollectionMappingToTemplateAttributeCollectionMappingResponse(TemplateAttributeCollectionMapping templateAttributeCollectionMapping)
    {

	TemplateAttributeCollectionMappingResponse templateAttributeCollectionMappingResponse = new TemplateAttributeCollectionMappingResponse();
	templateAttributeCollectionMappingResponse.setId(templateAttributeCollectionMapping.getCustomId());
	templateAttributeCollectionMappingResponse.setName(templateAttributeCollectionMapping.getName());
	templateAttributeCollectionMappingResponse.setDescription(templateAttributeCollectionMapping.getDescription());
	templateAttributeCollectionMappingResponse.setIcon(templateAttributeCollectionMapping.getIcon());
	templateAttributeCollectionMappingResponse.setInsertDate(templateAttributeCollectionMapping.getInsertDate().toInstant(ZoneOffset.UTC));

	if(templateAttributeCollectionMapping.getModifDate() != null)
	{
	    templateAttributeCollectionMappingResponse.setModifDate(templateAttributeCollectionMapping.getModifDate().toInstant(ZoneOffset.UTC));
	}

	templateAttributeCollectionMappingResponse.setVersion(templateAttributeCollectionMapping.getVersion());
	templateAttributeCollectionMappingResponse.setNemonic(templateAttributeCollectionMapping.getNemonic());
	templateAttributeCollectionMappingResponse.setCalculatedValue(templateAttributeCollectionMapping.getCalculatedValue());
	templateAttributeCollectionMappingResponse.setPosition(templateAttributeCollectionMapping.getPosition());

	if(templateAttributeCollectionMapping.getTemplateAttribute() != null)
	{
	    templateAttributeCollectionMappingResponse.setTemplateAttribute(templateAttributeResponseMapper.templateAttributeToTemplateAttributeRespose2(templateAttributeCollectionMapping.getTemplateAttribute()));
	}

	return templateAttributeCollectionMappingResponse;
    }


    public TemplateAttributeCollectionMapping templateAttributeCollectionMappingResponseToTemplateAttributeCollectionMapping(TemplateAttributeCollectionMappingResponse templateAttributeCollectionMappingResponse)
    {

	TemplateAttributeCollectionMapping templateAttributeCollectionMapping = new TemplateAttributeCollectionMapping();
	templateAttributeCollectionMapping.setId(templateAttributeCollectionMappingResponse.getId());
	templateAttributeCollectionMapping.setName(templateAttributeCollectionMappingResponse.getName());
	templateAttributeCollectionMapping.setDescription(templateAttributeCollectionMappingResponse.getDescription());
	templateAttributeCollectionMapping.setIcon(templateAttributeCollectionMappingResponse.getIcon());
	templateAttributeCollectionMapping.setCalculatedValue(templateAttributeCollectionMappingResponse.getCalculatedValue());
	templateAttributeCollectionMapping.setInsertDate(LocalDateTime.ofInstant(templateAttributeCollectionMappingResponse.getInsertDate(), ZoneOffset.UTC));

	if(templateAttributeCollectionMapping.getModifDate() != null)
	{
	    templateAttributeCollectionMapping.setModifDate(LocalDateTime.ofInstant(templateAttributeCollectionMappingResponse.getModifDate(), ZoneOffset.UTC));
	}

	templateAttributeCollectionMapping.setVersion(templateAttributeCollectionMappingResponse.getVersion());
	templateAttributeCollectionMapping.setNemonic(templateAttributeCollectionMappingResponse.getNemonic());
	templateAttributeCollectionMapping.setPosition(templateAttributeCollectionMappingResponse.getPosition());
	templateAttributeCollectionMapping.setVersion(templateAttributeCollectionMappingResponse.getVersion());

	if(templateAttributeCollectionMappingResponse.getTemplateAttribute() != null)
	{
	    templateAttributeCollectionMapping.setTemplateAttribute(templateAttributeResponseMapper.templateAttributeResponseToTemplateAttribute(templateAttributeCollectionMappingResponse.getTemplateAttribute()));
	}

	return templateAttributeCollectionMapping;
    }


    public Collection<TemplateAttributeCollectionMapping> templateAttributeCollectionMappingResponseCollectionToTemplateAttributeCollectionMappingCollection(Collection<TemplateAttributeCollectionMappingResponse> templateAttributeCollectionMappingsResponse)
    {

	SortedSet<TemplateAttributeCollectionMapping> templateAttributeCollectionMappingsResponses = new TreeSet<TemplateAttributeCollectionMapping>();

	for (TemplateAttributeCollectionMappingResponse templateAttributeCollectionMapping : templateAttributeCollectionMappingsResponse)
	{
	    templateAttributeCollectionMappingsResponses.add(templateAttributeCollectionMappingResponseToTemplateAttributeCollectionMapping(templateAttributeCollectionMapping));
	}

	return templateAttributeCollectionMappingsResponses;

    }


    public ResponsePage<TemplateAttributeCollectionMappingResponse> templateAttributeCollectionMappingPageToTemplateAttributeCollectionMappingResponsePage(ResponsePage<TemplateAttributeCollectionMapping> templateAttributeCollectionMappings)
    {

	List<TemplateAttributeCollectionMappingResponse> templateAttributeCollectionMappingsResponses = new ArrayList<TemplateAttributeCollectionMappingResponse>();

	for (TemplateAttributeCollectionMapping templateAttributeCollectionMapping : templateAttributeCollectionMappings)
	{
	    templateAttributeCollectionMappingsResponses.add(templateAttributeCollectionMappingToTemplateAttributeCollectionMappingResponse(templateAttributeCollectionMapping));
	}

	return new ResponsePage<TemplateAttributeCollectionMappingResponse>(templateAttributeCollectionMappingsResponses, templateAttributeCollectionMappings.getPageable(), templateAttributeCollectionMappings.getTotalElements());

    }


    public Collection<TemplateAttributeCollectionMappingResponse> templateAttributeCollectionMappingCollectionToTemplateAttributeCollectionMappingResponseCollection(Collection<TemplateAttributeCollectionMapping> templateAttributeCollectionMappings)
    {

	Collection<TemplateAttributeCollectionMappingResponse> templateAttributeCollectionMappingsResponses = new ArrayList<TemplateAttributeCollectionMappingResponse>();

	for (TemplateAttributeCollectionMapping templateAttributeCollectionMapping : templateAttributeCollectionMappings)
	{
	    templateAttributeCollectionMappingsResponses.add(templateAttributeCollectionMappingToTemplateAttributeCollectionMappingResponse(templateAttributeCollectionMapping));
	}

	return templateAttributeCollectionMappingsResponses;

    }


    public ResponsePage<TemplateAttributeCollectionMapping> templateAttritebuteCollectionMappingResponsePageTotemplateAttritebuteCollectionMappingPage(ResponsePage<TemplateAttributeCollectionMappingResponse> templateAttributeCollectionMappingsResponse)
    {

	List<TemplateAttributeCollectionMapping> templateAttributeCollectionMappings = new ArrayList<TemplateAttributeCollectionMapping>();

	for (TemplateAttributeCollectionMappingResponse templateAttritebuteCollectionMappingResponse : templateAttributeCollectionMappingsResponse)
	{
	    templateAttributeCollectionMappings.add(templateAttributeCollectionMappingResponseToTemplateAttributeCollectionMapping(templateAttritebuteCollectionMappingResponse));
	}

	return new ResponsePage<TemplateAttributeCollectionMapping>(templateAttributeCollectionMappings, templateAttributeCollectionMappingsResponse.getPageable(), templateAttributeCollectionMappingsResponse.getTotalElements());

    }


    public ResponsePage<TemplateAttributeCollectionMapping> templateAttributeCollectionMappingCollectionToTemplateAttributeCollectionMappingResposePage(ResponsePageJson<TemplateAttributeCollectionMappingResponse> templateAttributeCollectionMappingsResponse)
    {

	List<TemplateAttributeCollectionMapping> templateAttributeCollectionMappings = new ArrayList<TemplateAttributeCollectionMapping>();

	PageRequest pageRequest = PageRequest.of(templateAttributeCollectionMappingsResponse.getPageable().getPageNumber(), templateAttributeCollectionMappingsResponse.getPageable().getPageSize());

	for (TemplateAttributeCollectionMappingResponse templateAttributeCollectionMappingResponse : templateAttributeCollectionMappingsResponse.getContent())
	{

	    TemplateAttributeCollectionMapping templateAttributeCollectionMapping = templateAttributeCollectionMappingResponseToTemplateAttributeCollectionMapping(templateAttributeCollectionMappingResponse);

	    templateAttributeCollectionMappings.add(templateAttributeCollectionMapping);
	}

	return new ResponsePage<TemplateAttributeCollectionMapping>(templateAttributeCollectionMappings, pageRequest, templateAttributeCollectionMappingsResponse.getTotalElements());

    }

}
