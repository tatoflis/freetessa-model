package es.cic.tessa.model.mappers;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import org.springframework.stereotype.Component;
import es.cic.tessa.model.TemplateAttributeCollectionMapping;
import es.cic.tessa.model.dto.TemplateAttributeCollectionMappingRequest;


@Component
public class TemplateAttributeCollectionMappingRequestMapper
{

    public TemplateAttributeCollectionMapping templateAttributeCollectionMappingRequestToTemplateAttributeCollectionMapping(TemplateAttributeCollectionMappingRequest templateAttributeCollectionMappingRequest, Set<String> groups)
    {

	TemplateAttributeCollectionMapping templateAttributeCollectionMapping = new TemplateAttributeCollectionMapping();
	templateAttributeCollectionMapping.setCustomId(templateAttributeCollectionMappingRequest.getId());
	templateAttributeCollectionMapping.setName(templateAttributeCollectionMappingRequest.getName());
	templateAttributeCollectionMapping.setNameLower(templateAttributeCollectionMappingRequest.getName().toLowerCase());
	templateAttributeCollectionMapping.setDescription(templateAttributeCollectionMappingRequest.getDescription());
	templateAttributeCollectionMapping.setIcon(templateAttributeCollectionMappingRequest.getIcon());
	templateAttributeCollectionMapping.setCalculatedValue(templateAttributeCollectionMappingRequest.getCalculatedValue());
	templateAttributeCollectionMapping.setTemplateAttribute(null);
	templateAttributeCollectionMapping.setPosition(templateAttributeCollectionMappingRequest.getPosition());
	templateAttributeCollectionMapping.setGroups(groups);

	if(templateAttributeCollectionMappingRequest.getVersion() != null)
	{
	    templateAttributeCollectionMapping.setVersion(templateAttributeCollectionMappingRequest.getVersion());
	}

	return templateAttributeCollectionMapping;
    }


    public Collection<TemplateAttributeCollectionMapping> templateAttributeCollectionMappingRequestCollectionToTemplateAttributeCollectionMappingCollection(Collection<TemplateAttributeCollectionMappingRequest> templateAttributeCollectionMappingsRequest, Set<String> groups)
    {

	Collection<TemplateAttributeCollectionMapping> templateAttributeCollectionMappingsRequests = new ArrayList<TemplateAttributeCollectionMapping>();

	for (TemplateAttributeCollectionMappingRequest templateAttributeCollectionMapping : templateAttributeCollectionMappingsRequest)
	{
	    templateAttributeCollectionMappingsRequests.add(templateAttributeCollectionMappingRequestToTemplateAttributeCollectionMapping(templateAttributeCollectionMapping, groups));
	}

	return templateAttributeCollectionMappingsRequests;

    }

}
