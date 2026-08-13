package es.cic.tessa.model.historical.mapper;


import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.HistoricalAssetValue;
import es.cic.tessa.model.historical.dto.HistoricalAssetValueResponse;
import es.cic.tessa.model.historical.dto.HistoricalChangeResponse;
import es.cic.tessa.model.mappers.FunctionResponseMapper;


@Component
public class HistoricalAssetValueResponseMapper
{

    @Autowired
    private HistoricalTemplateAttributeResponseMapper historicalTemplateAttributeResponseMapper;

    @Autowired
    @Lazy
    private HistoricalAssetReferenceResponseMapper historicalAssetReferenceResponseMapper;

    @Autowired
    @Lazy
    private HistoricalAssetResponseMapper historicalAssetResponseMapper;

    @Autowired
    private HistoricalTemplateAttributeCollectionMappingResponseMapper historicalTemplateAttributeCollectionMappingResponseMapper;

    @Autowired
    private HistoricalChangeResponseMapper historicalChangeResponseMapper;

    private final FunctionResponseMapper functionResponseMapper;

    private final HistoricalExpressionParamResponseMapper expressionParamResponseMapper;

    public HistoricalAssetValueResponseMapper(FunctionResponseMapper functionResponseMapper, HistoricalExpressionParamResponseMapper expressionParamResponseMapper)
    {

	this.functionResponseMapper = functionResponseMapper;
	this.expressionParamResponseMapper = expressionParamResponseMapper;
    }


    public HistoricalAssetValueResponse historicalAssetValueToHistoricalAssetValueRespose(HistoricalAssetValue historicalAssetValue)
    {

	HistoricalAssetValueResponse historicalAssetValueResponse = new HistoricalAssetValueResponse();

	historicalAssetValueResponse.setId(historicalAssetValue.getCustomId());
	historicalAssetValueResponse.setName(historicalAssetValue.getName());
	historicalAssetValueResponse.setDescription(historicalAssetValue.getDescription());
	historicalAssetValueResponse.setIcon(historicalAssetValue.getIcon());
	historicalAssetValueResponse.setInsertDate(historicalAssetValue.getInsertDate().toInstant(ZoneOffset.UTC));

	if(historicalAssetValue.getModifDate() != null)
	{
	    historicalAssetValueResponse.setModifDate(historicalAssetValue.getModifDate().toInstant(ZoneOffset.UTC));
	}

	if(historicalAssetValue.getExpressionProperties() != null)
	{
	    historicalAssetValueResponse.setFunction(functionResponseMapper.functionToFunctionResponse(historicalAssetValue.getExpressionProperties()));

	}

	historicalAssetValueResponse.setValue(historicalAssetValue.getValue());

	if(historicalAssetValue.getHistoricalAssetReference() != null)
	{
	    historicalAssetValueResponse.setHistoricalAssetReference(historicalAssetReferenceResponseMapper.historicalAssetReferenceToHistoricalAssetReferenceRespose(historicalAssetValue.getHistoricalAssetReference()));
	}

	if(historicalAssetValue.getHistoricalTemplateAttribute() != null)
	{

	    historicalAssetValueResponse.setHistoricalTemplateAttribute(historicalTemplateAttributeResponseMapper.historicalTemplateAttributeToHistoricalTemplateAttributeRespose(historicalAssetValue.getHistoricalTemplateAttribute()));

	}
	if(historicalAssetValue.getHistoricalTemplateAttributeCollectionMapping() != null)
	{
	    historicalAssetValueResponse.setHistoricalTemplateAttributeCollectionMapping(historicalTemplateAttributeCollectionMappingResponseMapper.historicalTemplateAttributeCollectionMappingToHistoricalTemplateAttributeCollectionMappingResponse(historicalAssetValue.getHistoricalTemplateAttributeCollectionMapping()));
	}

	if(!CollectionUtils.isEmpty(historicalAssetValue.getExpressionParams()))
	{
	    historicalAssetValueResponse.setExpressionParams(expressionParamResponseMapper.expressionParamsToExpressionParamResponseCollection(historicalAssetValue.getExpressionParams()));
	}

	HistoricalChangeResponse historicalChangeResponse;

	if(historicalAssetValue.getHistoricalChange() != null)
	{
	    historicalChangeResponse = historicalChangeResponseMapper.historicalChangeToHistoricalChangeRespose(historicalAssetValue.getHistoricalChange());
	}
	else
	{
	    // Nodo vivo ya borrado (DETACH DELETE destruyó la relación HISTORICAL_CHANGE):
	    // la fecha se recupera desde las propiedades denormalizadas del propio nodo sombra.
	    historicalChangeResponse = new HistoricalChangeResponse();

	    if(historicalAssetValue.getStartChange() != null)
	    {
		historicalChangeResponse.setStartChange(historicalAssetValue.getStartChange().toInstant(ZoneOffset.UTC));
	    }

	    if(historicalAssetValue.getEndChange() != null)
	    {
		historicalChangeResponse.setEndChange(historicalAssetValue.getEndChange().toInstant(ZoneOffset.UTC));
	    }
	}

	historicalChangeResponse.setOperation(historicalAssetValue.getHistoricalChangeOperation());
	historicalAssetValueResponse.setHistoricalChange(historicalChangeResponse);

	return historicalAssetValueResponse;
    }


    public Collection<HistoricalAssetValueResponse> historicalAssetValueCollectionToHistoricalAssetValueResposeCollection(List<HistoricalAssetValue> historicalAssetValues)
    {

	Collection<HistoricalAssetValueResponse> historicalAssetValuesResponse = new HashSet<>();

	for (HistoricalAssetValue historicalAssetValue : historicalAssetValues)
	{
	    historicalAssetValuesResponse.add(historicalAssetValueToHistoricalAssetValueRespose(historicalAssetValue));

	}

	return historicalAssetValuesResponse;

    }


    public ResponsePage<HistoricalAssetValueResponse> historicalAssetValuePageToHistoricalAssetValueResposePage(ResponsePage<HistoricalAssetValue> historicalAssetValues)
    {

	Set<HistoricalAssetValueResponse> historicalAssetValuesResponse = new HashSet<>();

	for (HistoricalAssetValue historicalAssetValue : historicalAssetValues)
	{
	    historicalAssetValuesResponse.add(historicalAssetValueToHistoricalAssetValueRespose(historicalAssetValue));

	}

	return new ResponsePage<>(new ArrayList<>(historicalAssetValuesResponse), historicalAssetValues.getPageable(), historicalAssetValues.getTotalElements());

    }
}
