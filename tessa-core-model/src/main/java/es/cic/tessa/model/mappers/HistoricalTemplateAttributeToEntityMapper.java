package es.cic.tessa.model.mappers;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import es.cic.tessa.model.ExpressionParam;
import es.cic.tessa.model.Hashtag;
import es.cic.tessa.model.HistoricalExpressionParam;
import es.cic.tessa.model.HistoricalHashtag;
import es.cic.tessa.model.HistoricalTemplateAttribute;
import es.cic.tessa.model.TemplateAttribute;


@Component
public class HistoricalTemplateAttributeToEntityMapper
{

    @Lazy
    @Autowired
    private HistoricalTemplateToEntityMapper historicalTemplateToEntityMapper;


    public TemplateAttribute historicalTemplateAttributeToTemplateAttribute(HistoricalTemplateAttribute historicalTemplateAttribute)
    {

	if(historicalTemplateAttribute == null)
	{
	    return null;
	}

	TemplateAttribute templateAttribute = new TemplateAttribute();

	// Campos compartidos via TessaElement/IdentificableElement/LockableElement/DateTimeElement
	templateAttribute.setCustomId(historicalTemplateAttribute.getCustomId());
	templateAttribute.setId(historicalTemplateAttribute.getId());
	templateAttribute.setName(historicalTemplateAttribute.getName());
	templateAttribute.setNameLower(historicalTemplateAttribute.getNameLower());
	templateAttribute.setDescription(historicalTemplateAttribute.getDescription());
	templateAttribute.setNemonic(historicalTemplateAttribute.getNemonic());
	templateAttribute.setGroups(historicalTemplateAttribute.getGroups());
	templateAttribute.setIcon(historicalTemplateAttribute.getIcon());
	templateAttribute.setVersion(historicalTemplateAttribute.getVersion());
	templateAttribute.setInsertDate(historicalTemplateAttribute.getInsertDate());
	templateAttribute.setModifDate(historicalTemplateAttribute.getModifDate());

	// Campos propios de TemplateAttribute
	templateAttribute.setIdentificable(historicalTemplateAttribute.getIdentificable());
	templateAttribute.setAlias(historicalTemplateAttribute.getAlias());
	templateAttribute.setType(historicalTemplateAttribute.getType());
	templateAttribute.setMinLength(historicalTemplateAttribute.getMinLength());
	templateAttribute.setMaxLength(historicalTemplateAttribute.getMaxLength());
	templateAttribute.setRequired(historicalTemplateAttribute.getRequired());
	templateAttribute.setHasDefaultValue(historicalTemplateAttribute.getHasDefaultValue());
	templateAttribute.setDefaultValue(historicalTemplateAttribute.getDefaultValue());
	templateAttribute.setHasCalculatedValue(historicalTemplateAttribute.getHasCalculatedValue());
	templateAttribute.setCalculatedValue(historicalTemplateAttribute.getCalculatedValue());
	templateAttribute.setExpressionProperties(historicalTemplateAttribute.getExpressionProperties());
	templateAttribute.setPattern(historicalTemplateAttribute.getPattern());
	templateAttribute.setUnique(historicalTemplateAttribute.getUnique());
	templateAttribute.setExternalSource(historicalTemplateAttribute.getExternalSource());
	templateAttribute.setCollection(historicalTemplateAttribute.getCollection());
	templateAttribute.setWithcapacity(historicalTemplateAttribute.getWithcapacity());
	templateAttribute.setCapacity(historicalTemplateAttribute.getCapacity());
	templateAttribute.setMapping(historicalTemplateAttribute.getMapping());
	templateAttribute.setFinalAttribute(historicalTemplateAttribute.getFinalAttribute());
	templateAttribute.setPassword(historicalTemplateAttribute.getPassword());
	templateAttribute.setContentType(historicalTemplateAttribute.getContentType());
	templateAttribute.setPosition(historicalTemplateAttribute.getPosition());

	// historicalTemplateAttributeCollectionMapping: el tipo es Historical*; sin equivalente vivo directo, no se mapea

	// Relacion con Template padre (desde el atributo hacia su template)
	templateAttribute.setTemplate(historicalTemplateToEntityMapper.historicalTemplateToTemplate(historicalTemplateAttribute.getHistoricalTemplate()));

	// ExpressionParams: mapeo inline Historical→vivo
	if(historicalTemplateAttribute.getHistoricalExpressionParams() != null)
	{
	    templateAttribute.setExpressionParams(historicalExpressionParamsToExpressionParams(historicalTemplateAttribute.getHistoricalExpressionParams()));
	}

	// Hashtags: mapeo inline Historical→vivo
	if(historicalTemplateAttribute.getHashtags() != null)
	{
	    templateAttribute.setHashtags(historicalHashtagsToHashtags(historicalTemplateAttribute.getHashtags()));
	}

	return templateAttribute;
    }


    public List<TemplateAttribute> historicalTemplateAttributeListToTemplateAttributeList(List<HistoricalTemplateAttribute> historicalTemplateAttributes)
    {

	if(historicalTemplateAttributes == null)
	{
	    return List.of();
	}

	return historicalTemplateAttributes.stream()
		.map(this::historicalTemplateAttributeToTemplateAttribute)
		.collect(Collectors.toList());
    }


    private Set<ExpressionParam> historicalExpressionParamsToExpressionParams(Collection<HistoricalExpressionParam> historicalParams)
    {

	Set<ExpressionParam> result = new HashSet<>();

	for(HistoricalExpressionParam hp : historicalParams)
	{
	    ExpressionParam ep = new ExpressionParam();
	    ep.setCustomId(hp.getCustomId());
	    ep.setId(hp.getId());
	    ep.setName(hp.getName());
	    ep.setNameLower(hp.getNameLower());
	    ep.setDescription(hp.getDescription());
	    ep.setNemonic(hp.getNemonic());
	    ep.setGroups(hp.getGroups());
	    ep.setIcon(hp.getIcon());
	    ep.setVersion(hp.getVersion());
	    ep.setInsertDate(hp.getInsertDate());
	    ep.setModifDate(hp.getModifDate());
	    ep.setType(hp.getType());
	    ep.setRequired(hp.getRequired());
	    ep.setPosition(hp.getPosition());
	    // templateAttribute e historialDefaultValueAssetValue: no se mapean para evitar ciclos
	    result.add(ep);
	}

	return result;
    }


    private List<Hashtag> historicalHashtagsToHashtags(Collection<HistoricalHashtag> historicalHashtags)
    {

	List<Hashtag> result = new ArrayList<>();

	for(HistoricalHashtag hh : historicalHashtags)
	{
	    Hashtag hashtag = new Hashtag();
	    hashtag.setCustomId(hh.getCustomId());
	    hashtag.setId(hh.getId());
	    hashtag.setName(hh.getName());
	    hashtag.setNameLower(hh.getNameLower());
	    hashtag.setDescription(hh.getDescription());
	    hashtag.setNemonic(hh.getNemonic());
	    hashtag.setGroups(hh.getGroups());
	    hashtag.setIcon(hh.getIcon());
	    hashtag.setVersion(hh.getVersion());
	    hashtag.setInsertDate(hh.getInsertDate());
	    hashtag.setModifDate(hh.getModifDate());
	    hashtag.setValue(hh.getValue());
	    // historicalTemplateAttribute: no se mapea para evitar ciclos
	    result.add(hashtag);
	}

	return result;
    }

}
