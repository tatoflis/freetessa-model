package es.cic.tessa.model.historical.mapper;


import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import es.cic.tessa.common.model.TessaConstants;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.HistoricalTemplateAttribute;
import es.cic.tessa.model.HistoricalTemplateAttributeCollectionMapping;
import es.cic.tessa.model.historical.dto.HistoricalChangeResponse;
import es.cic.tessa.model.historical.dto.HistoricalExpressionParamResponse;
import es.cic.tessa.model.historical.dto.HistoricalTemplateAttributeCollectionMappingResponse;
import es.cic.tessa.model.historical.dto.HistoricalTemplateAttributeResponse;
import es.cic.tessa.model.mappers.FunctionResponseMapper;


@Component
public class HistoricalTemplateAttributeResponseMapper
{

    @Autowired
    private HistoricalTemplateResponseMapper historicalTemplateResponseMapper;

    @Autowired
    private HistoricalTemplateReferenceResponseMapper historicalTemplateReferenceResponseMapper;

    @Autowired
    private HistoricalChangeResponseMapper historicalChangeResponseMapper;

    @Autowired
    private FunctionResponseMapper functionResponseMapper;

    @Autowired
    @Lazy
    private HistoricalExpressionParamResponseMapper historicalExpressionParamResponseMapper;

    @Autowired
    private HistoricalHashtagResponseMapper historicalHashtagResponseMapper;

    @Autowired
    @Lazy
    private HistoricalTemplateAttributeCollectionMappingResponseMapper historicalTemplateAttributeCollectionMappingResponseMapper;

    public HistoricalTemplateAttributeResponse historicalTemplateAttributeToHistoricalTemplateAttributeRespose(HistoricalTemplateAttribute historicalTemplateAttribute)
    {

	return historicalTemplateAttributeToHistoricalTemplateAttributeRespose(historicalTemplateAttribute, true);
    }


    private HistoricalTemplateAttributeResponse historicalTemplateAttributeToHistoricalTemplateAttributeRespose(HistoricalTemplateAttribute historicalTemplateAttribute, boolean includeTemplate)
    {

	HistoricalTemplateAttributeResponse historicalTemplateAttributeResponse = new HistoricalTemplateAttributeResponse();
	historicalTemplateAttributeResponse.setId(historicalTemplateAttribute.getCustomId());
	historicalTemplateAttributeResponse.setNodeId(historicalTemplateAttribute.getId());
	historicalTemplateAttributeResponse.setName(historicalTemplateAttribute.getName());
	historicalTemplateAttributeResponse.setDescription(historicalTemplateAttribute.getDescription());
	historicalTemplateAttributeResponse.setIcon(historicalTemplateAttribute.getIcon());
	historicalTemplateAttributeResponse.setInsertDate(historicalTemplateAttribute.getInsertDate().toInstant(ZoneOffset.UTC));

	if(historicalTemplateAttribute.getModifDate() != null)
	{
	    historicalTemplateAttributeResponse.setModifDate(historicalTemplateAttribute.getModifDate().toInstant(ZoneOffset.UTC));
	}

	historicalTemplateAttributeResponse.setType(historicalTemplateAttribute.getType());
	historicalTemplateAttributeResponse.setMinLength(historicalTemplateAttribute.getMinLength());
	historicalTemplateAttributeResponse.setMaxLength(historicalTemplateAttribute.getMaxLength());
	historicalTemplateAttributeResponse.setRequired(historicalTemplateAttribute.getRequired());
	historicalTemplateAttributeResponse.setHidden(historicalTemplateAttribute.getHidden());
	historicalTemplateAttributeResponse.setHasDefaultValue(historicalTemplateAttribute.getHasDefaultValue());
	historicalTemplateAttributeResponse.setDefaultValue(historicalTemplateAttribute.getDefaultValue());
	historicalTemplateAttributeResponse.setHasCalculatedValue(historicalTemplateAttribute.getHasCalculatedValue());
	historicalTemplateAttributeResponse.setCalculatedValue(historicalTemplateAttribute.getCalculatedValue());

	if(historicalTemplateAttribute.getExpressionProperties() != null)
	{
	    historicalTemplateAttributeResponse.setExpressionProperties(functionResponseMapper.functionToFunctionResponse(historicalTemplateAttribute.getExpressionProperties()));
	}

	historicalTemplateAttributeResponse.setPattern(historicalTemplateAttribute.getPattern());
	historicalTemplateAttributeResponse.setUnique(historicalTemplateAttribute.getUnique());
	historicalTemplateAttributeResponse.setExternalSource(historicalTemplateAttribute.getExternalSource());
	historicalTemplateAttributeResponse.setCollection(historicalTemplateAttribute.getCollection());
	historicalTemplateAttributeResponse.setWithcapacity(historicalTemplateAttribute.getWithcapacity());
	historicalTemplateAttributeResponse.setCapacity(historicalTemplateAttribute.getCapacity());
	historicalTemplateAttributeResponse.setMapping(historicalTemplateAttribute.getMapping());
	historicalTemplateAttributeResponse.setVersion(historicalTemplateAttribute.getVersion());
	historicalTemplateAttributeResponse.setFinalAttribute(historicalTemplateAttribute.getFinalAttribute());
	historicalTemplateAttributeResponse.setPasswordAttribute(historicalTemplateAttribute.isPassword());
	historicalTemplateAttributeResponse.setNemonic(historicalTemplateAttribute.getNemonic());
	historicalTemplateAttributeResponse.setIdentificator(historicalTemplateAttribute.getIdentificable());
	historicalTemplateAttributeResponse.setAlias(historicalTemplateAttribute.getAlias());
	historicalTemplateAttributeResponse.setPosition(historicalTemplateAttribute.getPosition());
	historicalTemplateAttributeResponse.setVersion(historicalTemplateAttribute.getVersion());
	historicalTemplateAttributeResponse.setContentType(historicalTemplateAttribute.getContentType());

	if(!CollectionUtils.isEmpty(historicalTemplateAttribute.getEnumValues()))
	{
	    historicalTemplateAttributeResponse.setEnums(historicalTemplateAttribute.getEnumValues());
	}

	if(includeTemplate && historicalTemplateAttribute.getHistoricalTemplate() != null)
	{
	    historicalTemplateAttributeResponse.setHistoricalTemplate(historicalTemplateResponseMapper.historicalTemplateTohistoricalTemplateRespose(historicalTemplateAttribute.getHistoricalTemplate()));
	}

	if(historicalTemplateAttribute.getHistoricalTemplateReference() != null)
	{
	    historicalTemplateAttributeResponse.setHistoricalTemplateReference(historicalTemplateReferenceResponseMapper.historicalTemplateReferenceToHistoricalTemplateReferenceRespose(historicalTemplateAttribute.getHistoricalTemplateReference()));
	}

	if(!CollectionUtils.isEmpty(historicalTemplateAttribute.getHistoricalExpressionParams()))
	{
	    List<HistoricalExpressionParamResponse> expressionParams = new ArrayList<>(historicalExpressionParamResponseMapper.expressionParamsToExpressionParamResponseCollection(historicalTemplateAttribute.getHistoricalExpressionParams()));
	    expressionParams.sort(Comparator.comparing(HistoricalExpressionParamResponse::getPosition));
	    historicalTemplateAttributeResponse.setExpressionParams(expressionParams);
	}

	if(!CollectionUtils.isEmpty(historicalTemplateAttribute.getHashtags()))
	{
	    historicalTemplateAttributeResponse.setHashtags(historicalHashtagResponseMapper.hashtagsToHashtagResponseCollection(historicalTemplateAttribute.getHashtags()));
	}

	if(!CollectionUtils.isEmpty(historicalTemplateAttribute.getHistoricalTemplateAttributeCollectionMapping()))
	{
	    List<HistoricalTemplateAttributeCollectionMappingResponse> mappings = new ArrayList<>();
	    for (HistoricalTemplateAttributeCollectionMapping mapping : historicalTemplateAttribute.getHistoricalTemplateAttributeCollectionMapping())
	    {
		mappings.add(historicalTemplateAttributeCollectionMappingResponseMapper.historicalTemplateAttributeCollectionMappingToHistoricalTemplateAttributeCollectionMappingResponseSinTemplateAttribute(mapping));
	    }
	    mappings.sort(Comparator.comparing(HistoricalTemplateAttributeCollectionMappingResponse::getPosition));
	    historicalTemplateAttributeResponse.setTemplateAttributeCollectionMapping(mappings);
	}

	HistoricalChangeResponse historicalChangeResponse;

	if(historicalTemplateAttribute.getHistoricalChange() != null)
	{
	    historicalChangeResponse = historicalChangeResponseMapper.historicalChangeToHistoricalChangeRespose(historicalTemplateAttribute.getHistoricalChange());
	}
	else
	{
	    // Nodo vivo ya borrado (DETACH DELETE destruyó la relación HISTORICAL_CHANGE):
	    // la fecha se recupera desde las propiedades denormalizadas del propio nodo sombra.
	    historicalChangeResponse = new HistoricalChangeResponse();

	    if(historicalTemplateAttribute.getStartChange() != null)
	    {
		historicalChangeResponse.setStartChange(historicalTemplateAttribute.getStartChange().toInstant(ZoneOffset.UTC));
	    }

	    if(historicalTemplateAttribute.getEndChange() != null)
	    {
		historicalChangeResponse.setEndChange(historicalTemplateAttribute.getEndChange().toInstant(ZoneOffset.UTC));
	    }
	}

	historicalChangeResponse.setOperation(historicalTemplateAttribute.getHistoricalChangeOperation());
	historicalTemplateAttributeResponse.setHistoricalChange(historicalChangeResponse);

	if(historicalTemplateAttribute.getGroups().contains(TessaConstants.SYSTEM))
	{
	    historicalTemplateAttributeResponse.setGroups(historicalTemplateAttribute.getGroups());
	}

	return historicalTemplateAttributeResponse;
    }


    /**
     * Igual que {@link #historicalTemplateAttributeToHistoricalTemplateAttributeRespose}, pero sin
     * poblar la back-reference {@code historicalTemplate}: la usa
     * {@link HistoricalTemplateResponseMapper} para colgar la colección de atributos de la propia
     * template, donde repoblar el padre en cada hijo volvería a invocar
     * historicalTemplateTohistoricalTemplateRespose -> ...ToHistoricalTemplateAttributeRespose en
     * bucle infinito (StackOverflowError). No basta con limpiar el campo a posteriori: hay que
     * evitar la llamada recursiva desde el principio (parametro includeTemplate=false).
     */
    public HistoricalTemplateAttributeResponse historicalTemplateAttributeToHistoricalTemplateAttributeResposeSinTemplate(HistoricalTemplateAttribute historicalTemplateAttribute)
    {

	return historicalTemplateAttributeToHistoricalTemplateAttributeRespose(historicalTemplateAttribute, false);
    }


    public ResponsePage<HistoricalTemplateAttributeResponse> historicalTemplateAttributesTohistoricalTemplateAttributesRespose(ResponsePage<HistoricalTemplateAttribute> historicalTemplateAttributes)
    {

	List<HistoricalTemplateAttributeResponse> response = new ArrayList<>();

	for (HistoricalTemplateAttribute historicalTemplateAttribute : historicalTemplateAttributes)
	{
	    response.add(historicalTemplateAttributeToHistoricalTemplateAttributeRespose(historicalTemplateAttribute));
	}

	return new ResponsePage<>(new ArrayList<>(response), historicalTemplateAttributes.getPageable(), historicalTemplateAttributes.getTotalElements());
    }
}
