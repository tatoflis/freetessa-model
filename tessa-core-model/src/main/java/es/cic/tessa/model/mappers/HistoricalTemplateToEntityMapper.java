package es.cic.tessa.model.mappers;


import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import es.cic.tessa.model.HistoricalTemplate;
import es.cic.tessa.model.Template;


@Component
public class HistoricalTemplateToEntityMapper
{

    @Lazy
    @Autowired
    private HistoricalTemplateAttributeToEntityMapper historicalTemplateAttributeToEntityMapper;

    @Lazy
    @Autowired
    private HistoricalOrganizerToEntityMapper historicalOrganizerToEntityMapper;


    public Template historicalTemplateToTemplate(HistoricalTemplate historicalTemplate)
    {

	if(historicalTemplate == null)
	{
	    return null;
	}

	Template template = new Template();

	// Campos compartidos via TessaElement/IdentificableElement/LockableElement/DateTimeElement
	template.setCustomId(historicalTemplate.getCustomId());
	template.setId(historicalTemplate.getId());
	template.setName(historicalTemplate.getName());
	template.setNameLower(historicalTemplate.getNameLower());
	template.setDescription(historicalTemplate.getDescription());
	template.setNemonic(historicalTemplate.getNemonic());
	template.setGroups(historicalTemplate.getGroups());
	template.setIcon(historicalTemplate.getIcon());
	template.setVersion(historicalTemplate.getVersion());
	template.setInsertDate(historicalTemplate.getInsertDate());
	template.setModifDate(historicalTemplate.getModifDate());

	// Campos propios de Template
	template.setFinalTemplate(historicalTemplate.getFinalTemplate());
	template.setAbstractTemplate(historicalTemplate.getAbstractTemplate());
	template.setAssetOrganized(historicalTemplate.getAssetOrganized());
	template.setTemplateOrganized(historicalTemplate.getTemplateOrganized());
	template.setType(historicalTemplate.getType());
	template.setNumComplexAttributes(historicalTemplate.getNumComplexAttributes());

	// endDate: solo existe en HistoricalTemplate, sin destino en Template, se ignora

	// Relaciones anidadas
	template.setExtendsTemplate(historicalTemplateToTemplate(historicalTemplate.getHistoricalExtendsTemplate()));

	if(historicalTemplate.getHistoricalTemplateAttributes() != null)
	{
	    template.setTemplateAttributes(historicalTemplateAttributeToEntityMapper.historicalTemplateAttributeListToTemplateAttributeList(historicalTemplate.getHistoricalTemplateAttributes()));
	}

	if(historicalTemplate.getHistoricalOrganizers() != null)
	{
	    template.setOrganizers(historicalOrganizerToEntityMapper.historicalOrganizerListToOrganizerList(historicalTemplate.getHistoricalOrganizers()));
	}

	return template;
    }


    public List<Template> historicalTemplateListToTemplateList(List<HistoricalTemplate> historicalTemplates)
    {

	if(historicalTemplates == null)
	{
	    return List.of();
	}

	return historicalTemplates.stream()
		.map(this::historicalTemplateToTemplate)
		.collect(Collectors.toList());
    }

}
