package es.cic.tessa.model.historical.mapper;


import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.HistoricalTemplateAttributeCollectionMapping;
import es.cic.tessa.model.historical.dto.HistoricalChangeResponse;
import es.cic.tessa.model.historical.dto.HistoricalTemplateAttributeCollectionMappingResponse;


@Component
public class HistoricalTemplateAttributeCollectionMappingResponseMapper
{

    @Autowired
    private HistoricalTemplateAttributeResponseMapper historicalTemplateAttributeResponseMapper;

    @Autowired
    private HistoricalChangeResponseMapper historicalChangeResponseMapper;

    public HistoricalTemplateAttributeCollectionMappingResponse historicalTemplateAttributeCollectionMappingToHistoricalTemplateAttributeCollectionMappingResponse(HistoricalTemplateAttributeCollectionMapping historicalTemplateAttributeCollectionMapping)
    {

	return historicalTemplateAttributeCollectionMappingToHistoricalTemplateAttributeCollectionMappingResponse(historicalTemplateAttributeCollectionMapping, true);
    }


    /**
     * Igual que {@link #historicalTemplateAttributeCollectionMappingToHistoricalTemplateAttributeCollectionMappingResponse(HistoricalTemplateAttributeCollectionMapping)},
     * pero permite suprimir la back-reference {@code historicalTemplateAttribute}
     * ({@code includeTemplateAttribute=false}): la usa
     * {@link HistoricalTemplateAttributeResponseMapper} al poblar la colección de mappings del
     * propio atributo, donde repoblar el padre en cada hijo volvería a invocar
     * historicalTemplateAttributeToHistoricalTemplateAttributeRespose -> ...ToHistoricalTemplateAttributeCollectionMappingResponse
     * en bucle infinito (StackOverflowError) — mismo patrón que
     * {@code historicalTemplateAttributeToHistoricalTemplateAttributeResposeSinTemplate}.
     */
    public HistoricalTemplateAttributeCollectionMappingResponse historicalTemplateAttributeCollectionMappingToHistoricalTemplateAttributeCollectionMappingResponseSinTemplateAttribute(HistoricalTemplateAttributeCollectionMapping historicalTemplateAttributeCollectionMapping)
    {

	return historicalTemplateAttributeCollectionMappingToHistoricalTemplateAttributeCollectionMappingResponse(historicalTemplateAttributeCollectionMapping, false);
    }


    private HistoricalTemplateAttributeCollectionMappingResponse historicalTemplateAttributeCollectionMappingToHistoricalTemplateAttributeCollectionMappingResponse(HistoricalTemplateAttributeCollectionMapping historicalTemplateAttributeCollectionMapping, boolean includeTemplateAttribute)
    {

	HistoricalTemplateAttributeCollectionMappingResponse historicalTemplateAttributeCollectionMappingResponse = new HistoricalTemplateAttributeCollectionMappingResponse();
	historicalTemplateAttributeCollectionMappingResponse.setId(historicalTemplateAttributeCollectionMapping.getCustomId());
	historicalTemplateAttributeCollectionMappingResponse.setName(historicalTemplateAttributeCollectionMapping.getName());
	historicalTemplateAttributeCollectionMappingResponse.setDescription(historicalTemplateAttributeCollectionMapping.getDescription());
	historicalTemplateAttributeCollectionMappingResponse.setIcon(historicalTemplateAttributeCollectionMapping.getIcon());
	historicalTemplateAttributeCollectionMappingResponse.setInsertDate(historicalTemplateAttributeCollectionMapping.getInsertDate().toInstant(ZoneOffset.UTC));

	if(historicalTemplateAttributeCollectionMapping.getModifDate() != null)
	{
	    historicalTemplateAttributeCollectionMappingResponse.setModifDate(historicalTemplateAttributeCollectionMapping.getModifDate().toInstant(ZoneOffset.UTC));
	}

	historicalTemplateAttributeCollectionMappingResponse.setVersion(historicalTemplateAttributeCollectionMapping.getVersion());
	historicalTemplateAttributeCollectionMappingResponse.setNemonic(historicalTemplateAttributeCollectionMapping.getNemonic());
	historicalTemplateAttributeCollectionMappingResponse.setPosition(historicalTemplateAttributeCollectionMapping.getPosition());
	historicalTemplateAttributeCollectionMappingResponse.setHistoricalChangeOperation(historicalTemplateAttributeCollectionMapping.getHistoricalChangeOperation());

	if(includeTemplateAttribute && historicalTemplateAttributeCollectionMapping.getHistoricalTemplateAttribute() != null)
	{
	    historicalTemplateAttributeCollectionMappingResponse.setHistoricalTemplateAttribute(historicalTemplateAttributeResponseMapper.historicalTemplateAttributeToHistoricalTemplateAttributeResposeSinTemplate(historicalTemplateAttributeCollectionMapping.getHistoricalTemplateAttribute()));
	}

	HistoricalChangeResponse historicalChangeResponse;

	if(historicalTemplateAttributeCollectionMapping.getHistoricalChange() != null)
	{
	    historicalChangeResponse = historicalChangeResponseMapper.historicalChangeToHistoricalChangeRespose(historicalTemplateAttributeCollectionMapping.getHistoricalChange());
	}
	else
	{
	    // Nodo vivo ya borrado (DETACH DELETE destruyó la relación HISTORICAL_CHANGE):
	    // la fecha se recupera desde las propiedades denormalizadas del propio nodo sombra.
	    historicalChangeResponse = new HistoricalChangeResponse();

	    if(historicalTemplateAttributeCollectionMapping.getStartChange() != null)
	    {
		historicalChangeResponse.setStartChange(historicalTemplateAttributeCollectionMapping.getStartChange().toInstant(ZoneOffset.UTC));
	    }

	    if(historicalTemplateAttributeCollectionMapping.getEndChange() != null)
	    {
		historicalChangeResponse.setEndChange(historicalTemplateAttributeCollectionMapping.getEndChange().toInstant(ZoneOffset.UTC));
	    }
	}

	historicalChangeResponse.setOperation(historicalTemplateAttributeCollectionMapping.getHistoricalChangeOperation());
	historicalTemplateAttributeCollectionMappingResponse.setHistoricalChange(historicalChangeResponse);

	return historicalTemplateAttributeCollectionMappingResponse;
    }


    public ResponsePage<HistoricalTemplateAttributeCollectionMappingResponse> historicalTemplateAttributeCollectionMappingPageToHistoricalTemplateAttributeCollectionMappingResponsePage(ResponsePage<HistoricalTemplateAttributeCollectionMapping> historicalTemplateAttributeCollectionMappings)
    {

	List<HistoricalTemplateAttributeCollectionMappingResponse> historicalTemplateAttributeCollectionMappingsResponses = new ArrayList<>();

	for (HistoricalTemplateAttributeCollectionMapping historicalTemplateAttributeCollectionMapping : historicalTemplateAttributeCollectionMappings)
	{
	    historicalTemplateAttributeCollectionMappingsResponses.add(historicalTemplateAttributeCollectionMappingToHistoricalTemplateAttributeCollectionMappingResponse(historicalTemplateAttributeCollectionMapping));
	}

	return new ResponsePage<>(historicalTemplateAttributeCollectionMappingsResponses, historicalTemplateAttributeCollectionMappings.getPageable(), historicalTemplateAttributeCollectionMappings.getTotalElements());
    }
}
