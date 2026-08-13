package es.cic.tessa.model.historical.mapper;


import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import es.cic.tessa.common.model.TessaConstants;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.HistoricalTemplate;
import es.cic.tessa.model.HistoricalTemplateAttribute;
import es.cic.tessa.model.historical.dto.HistoricalChangeResponse;
import es.cic.tessa.model.historical.dto.HistoricalTemplateAttributeResponse;
import es.cic.tessa.model.historical.dto.HistoricalTemplateResponse;


@Component
public class HistoricalTemplateResponseMapper
{

    @Autowired
    private HistoricalChangeResponseMapper historicalChangeResponseMapper;

    @Autowired
    private HistoricalOrganizerResponseMapper historicalOrganizerResponseMapper;

    @Autowired
    @Lazy
    private HistoricalTemplateAttributeResponseMapper historicalTemplateAttributeResponseMapper;

    public HistoricalTemplateResponse historicalTemplateTohistoricalTemplateRespose(HistoricalTemplate historicalTemplate)
    {

	HistoricalTemplateResponse historicalTemplateResponse = new HistoricalTemplateResponse();
	historicalTemplateResponse.setId(historicalTemplate.getCustomId());
	historicalTemplateResponse.setNodeId(historicalTemplate.getId());
	historicalTemplateResponse.setName(historicalTemplate.getName());
	historicalTemplateResponse.setDescription(historicalTemplate.getDescription());
	historicalTemplateResponse.setIcon(historicalTemplate.getIcon());
	historicalTemplateResponse.setInsertDate(historicalTemplate.getInsertDate().toInstant(ZoneOffset.UTC));

	if(historicalTemplate.getModifDate() != null)
	{
	    historicalTemplateResponse.setModifDate(historicalTemplate.getModifDate().toInstant(ZoneOffset.UTC));
	}

	historicalTemplateResponse.setFinalTemplate(historicalTemplate.getFinalTemplate());
	historicalTemplateResponse.setAbstractTemplate(historicalTemplate.getAbstractTemplate());
	historicalTemplateResponse.setNumComplexAttributes(historicalTemplate.getNumComplexAttributes());
	historicalTemplateResponse.setType(historicalTemplate.getType());
	historicalTemplateResponse.setVersion(historicalTemplate.getVersion());
	historicalTemplateResponse.setAssetOrganized(historicalTemplate.getAssetOrganized());
	historicalTemplateResponse.setTemplateOrganized(historicalTemplate.getTemplateOrganized());
	historicalTemplateResponse.setNemonic(historicalTemplate.getNemonic());
	historicalTemplateResponse.setVersion(historicalTemplate.getVersion());

	if(historicalTemplate.getHistoricalExtendsTemplate() != null)
	{
	    historicalTemplateResponse.setHistoricalExtendsTemplate(historicalTemplateTohistoricalTemplateRespose(historicalTemplate.getHistoricalExtendsTemplate()));
	}

	if(historicalTemplate.getHistoricalOrganizers() != null)
	{
	    historicalTemplateResponse.setHistoricalOrganizers(historicalOrganizerResponseMapper.historicalOrganizerCollectionToHistoricalOrganizerResposeCollection(historicalTemplate.getHistoricalOrganizers()));
	}

	if(!CollectionUtils.isEmpty(historicalTemplate.getHistoricalTemplateAttributes()))
	{
	    List<HistoricalTemplateAttributeResponse> attributes = new ArrayList<>();

	    for (HistoricalTemplateAttribute historicalTemplateAttribute : historicalTemplate.getHistoricalTemplateAttributes())
	    {
		attributes.add(historicalTemplateAttributeResponseMapper.historicalTemplateAttributeToHistoricalTemplateAttributeResposeSinTemplate(historicalTemplateAttribute));
	    }

	    historicalTemplateResponse.setHistoricalTemplateAttributes(attributes);
	}

	if(historicalTemplate.getGroups().contains(TessaConstants.SYSTEM))
	{
	    historicalTemplateResponse.setGroups(TessaConstants.SYSTEM_GROUP);
	}

	HistoricalChangeResponse historicalChangeResponse;

	if(historicalTemplate.getHistoricalChange() != null)
	{
	    historicalChangeResponse = historicalChangeResponseMapper.historicalChangeToHistoricalChangeRespose(historicalTemplate.getHistoricalChange());
	}
	else
	{
	    // Nodo vivo ya borrado (DETACH DELETE destruyó la relación HISTORICAL_CHANGE):
	    // la fecha se recupera desde las propiedades denormalizadas del propio nodo sombra.
	    historicalChangeResponse = new HistoricalChangeResponse();

	    if(historicalTemplate.getStartChange() != null)
	    {
		historicalChangeResponse.setStartChange(historicalTemplate.getStartChange().toInstant(ZoneOffset.UTC));
	    }

	    if(historicalTemplate.getEndChange() != null)
	    {
		historicalChangeResponse.setEndChange(historicalTemplate.getEndChange().toInstant(ZoneOffset.UTC));
	    }
	}

	historicalChangeResponse.setOperation(historicalTemplate.getHistoricalChangeOperation());
	historicalTemplateResponse.setHistoricalChange(historicalChangeResponse);

	return historicalTemplateResponse;
    }


    public ResponsePage<HistoricalTemplateResponse> historicalTemplatesTohistoricalTemplatesRespose(ResponsePage<HistoricalTemplate> historicalTemplates)
    {

	List<HistoricalTemplateResponse> response = new ArrayList<>();

	for (HistoricalTemplate historicalTemplate : historicalTemplates)
	{
	    response.add(historicalTemplateTohistoricalTemplateRespose(historicalTemplate));
	}

	return new ResponsePage<>(new ArrayList<>(response), historicalTemplates.getPageable(), historicalTemplates.getTotalElements());
    }
}
