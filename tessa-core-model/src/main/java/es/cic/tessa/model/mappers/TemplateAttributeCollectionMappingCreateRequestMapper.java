package es.cic.tessa.model.mappers;


import java.util.HashSet;
import org.springframework.stereotype.Component;
import es.cic.tessa.model.TemplateAttributeCollectionMapping;
import es.cic.tessa.model.dto.TemplateAttributeCollectionMappingCreateRequest;


@Component
public class TemplateAttributeCollectionMappingCreateRequestMapper
{

    public TemplateAttributeCollectionMapping templateAttributeCollectionMappingCreateRequestToTemplateAttributeCollectionMapping(TemplateAttributeCollectionMappingCreateRequest templateAttributeCollectionMappingCreateRequest)
    {

	TemplateAttributeCollectionMapping templateAttributeCollectionMapping = new TemplateAttributeCollectionMapping();

	templateAttributeCollectionMapping.setCustomId(templateAttributeCollectionMappingCreateRequest.getId());
	templateAttributeCollectionMapping.setName(templateAttributeCollectionMappingCreateRequest.getName());
	templateAttributeCollectionMapping.setNameLower(templateAttributeCollectionMappingCreateRequest.getName().toLowerCase());
	templateAttributeCollectionMapping.setDescription(templateAttributeCollectionMappingCreateRequest.getDescription());
	templateAttributeCollectionMapping.setIcon(templateAttributeCollectionMappingCreateRequest.getIcon());
	templateAttributeCollectionMapping.setCalculatedValue(templateAttributeCollectionMappingCreateRequest.getCalculatedValue());
	templateAttributeCollectionMapping.setTemplateAttribute(null);
	templateAttributeCollectionMapping.setPosition(templateAttributeCollectionMappingCreateRequest.getPosition());
	templateAttributeCollectionMapping.setGroups(new HashSet<>(templateAttributeCollectionMappingCreateRequest.getGroups()));

	if(templateAttributeCollectionMappingCreateRequest.getVersion() != null)
	{
	    templateAttributeCollectionMapping.setVersion(templateAttributeCollectionMappingCreateRequest.getVersion());
	}

	return templateAttributeCollectionMapping;
    }

}
