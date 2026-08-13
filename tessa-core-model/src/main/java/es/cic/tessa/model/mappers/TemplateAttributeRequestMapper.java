package es.cic.tessa.model.mappers;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import es.cic.tessa.model.Template;
import es.cic.tessa.model.TemplateAttribute;
import es.cic.tessa.model.TemplateReference;
import es.cic.tessa.model.dto.TemplateAttributeRequest;


@Component
public class TemplateAttributeRequestMapper
{

    @Autowired
    private FunctionRequestMapper functionRequestMapper;

    public TemplateAttribute templateAttributeRequestToTemplateAttribute(TemplateAttributeRequest templateAttributeRequest, Set<String> groups)
    {

	TemplateAttribute templateAttribute = new TemplateAttribute();
	templateAttribute.setCustomId(templateAttributeRequest.getId());
	templateAttribute.setName(templateAttributeRequest.getName());
	templateAttribute.setNameLower(templateAttributeRequest.getName().toLowerCase());
	templateAttribute.setDescription(templateAttributeRequest.getDescription());
	templateAttribute.setIcon(templateAttributeRequest.getIcon());
	templateAttribute.setType(templateAttributeRequest.getType());
	templateAttribute.setCalculatedValue(templateAttributeRequest.getCalculatedValue());
	templateAttribute.setCapacity(templateAttributeRequest.getCapacity());
	templateAttribute.setCollection(Boolean.valueOf(templateAttributeRequest.isCollection()));
	templateAttribute.setDefaultValue(templateAttributeRequest.getDefaultValue());
	templateAttribute.setExternalSource(Boolean.valueOf(templateAttributeRequest.isExternalSource()));
	templateAttribute.setHasDefaultValue(Boolean.valueOf(templateAttributeRequest.isHasDefaultValue()));
	templateAttribute.setMapping(Boolean.valueOf(templateAttributeRequest.isMapping()));
	templateAttribute.setMaxLength(templateAttributeRequest.getMaxLength());
	templateAttribute.setMinLength(templateAttributeRequest.getMinLength());
	templateAttribute.setPattern(templateAttributeRequest.getPattern());
	templateAttribute.setRequired(Boolean.valueOf(templateAttributeRequest.isRequired()));
	templateAttribute.setHidden(Boolean.valueOf(templateAttributeRequest.isHidden()));
	templateAttribute.setUnique(Boolean.valueOf(templateAttributeRequest.isUnique()));
	templateAttribute.setWithcapacity(Boolean.valueOf(templateAttributeRequest.isWithcapacity()));
	templateAttribute.setFinalAttribute(Boolean.valueOf(templateAttributeRequest.isFinalAttribute()));
	templateAttribute.setPassword(Boolean.valueOf(templateAttributeRequest.isPassword()));
	templateAttribute.setIdentificable(Boolean.valueOf(templateAttributeRequest.isIdentificator()));
	templateAttribute.setAlias(Boolean.valueOf(templateAttributeRequest.isAlias()));
	templateAttribute.setPosition(templateAttributeRequest.getPosition());
	templateAttribute.setHasCalculatedValue(Boolean.valueOf(templateAttributeRequest.isHasCalculatedValue()));
	templateAttribute.setContentType(templateAttributeRequest.getContentType());
	templateAttribute.setGroups(groups);

	if(templateAttributeRequest.getExpressionProperties() != null)
	{
	    templateAttribute.setExpressionProperties(functionRequestMapper.functionRequestToFunction(templateAttributeRequest.getExpressionProperties()));
	}

	if(templateAttributeRequest.getVersion() != null)
	{
	    templateAttribute.setVersion(templateAttributeRequest.getVersion());
	}

	if(templateAttributeRequest.getIdTemplate() != null)
	{
	    Template template = new Template();
	    template.setCustomId(templateAttributeRequest.getIdTemplate());

	    templateAttribute.setTemplate(template);
	}

	if(templateAttributeRequest.getIdTemplateReference() != null)
	{
	    Template template = new Template();
	    template.setCustomId(templateAttributeRequest.getIdTemplateReference());

	    TemplateReference templateReference = new TemplateReference();
	    templateReference.setTemplate(template);
	    templateReference.setTemplateReferenceType(templateAttributeRequest.getReferenceType());
	    templateReference.setTemplateRelationType(templateAttributeRequest.getRelationType());

	    templateAttribute.setTemplateReference(templateReference);
	}

	if(!CollectionUtils.isEmpty(templateAttributeRequest.getEnums()))
	{
	    templateAttribute.setEnumValues(templateAttributeRequest.getEnums());
	}

	if(templateAttributeRequest.getModifDate() != null)
	{
	    templateAttribute.setModifDate(templateAttributeRequest.getModifDate());
	}

	return templateAttribute;
    }


    public Collection<TemplateAttribute> templateAttributeRequestCollectionToTemplateAttributeCollection(Collection<TemplateAttributeRequest> templateAttributesRequest, Set<String> groups)
    {

	Collection<TemplateAttribute> templateAttributes = new ArrayList<>();

	for (TemplateAttributeRequest templateAttributeRequest : templateAttributesRequest)
	{
	    templateAttributes.add(templateAttributeRequestToTemplateAttribute(templateAttributeRequest, groups));
	}

	return templateAttributes;

    }

}
