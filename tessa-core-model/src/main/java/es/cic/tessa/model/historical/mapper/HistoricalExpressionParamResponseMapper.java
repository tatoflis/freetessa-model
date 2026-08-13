package es.cic.tessa.model.historical.mapper;


import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.HistoricalExpressionParam;
import es.cic.tessa.model.historical.dto.HistoricalChangeResponse;
import es.cic.tessa.model.historical.dto.HistoricalExpressionParamResponse;


@Component
public class HistoricalExpressionParamResponseMapper
{

    private HistoricalDefaultValueResponseMapper defaultValueResponseMapper;

    private HistoricalChangeResponseMapper historicalChangeResponseMapper;

    public HistoricalExpressionParamResponseMapper(HistoricalDefaultValueResponseMapper defaultValueResponseMapper, HistoricalChangeResponseMapper historicalChangeResponseMapper)
    {

	this.defaultValueResponseMapper = defaultValueResponseMapper;
	this.historicalChangeResponseMapper = historicalChangeResponseMapper;

    }


    public HistoricalExpressionParamResponse expressionParamToExpressionParamResponse(HistoricalExpressionParam expressionParam)
    {

	HistoricalExpressionParamResponse expressionParamResponse = new HistoricalExpressionParamResponse();

	expressionParamResponse.setId(expressionParam.getCustomId());
	expressionParamResponse.setName(expressionParam.getName());
	expressionParamResponse.setType(expressionParam.getType());
	expressionParamResponse.setRequired(expressionParam.getRequired());

	if(expressionParam.getHistoricalDefaultValueAssetValue() != null)
	{
	    expressionParamResponse.setDefaultValueAssetValueResponse(defaultValueResponseMapper.defaultValueToDefaultValueRespose(expressionParam.getHistoricalDefaultValueAssetValue()));
	}
	expressionParamResponse.setPosition(expressionParam.getPosition());
	expressionParamResponse.setVersion(expressionParam.getVersion());

	HistoricalChangeResponse historicalChangeResponse;

	if(expressionParam.getHistoricalChange() != null)
	{
	    historicalChangeResponse = historicalChangeResponseMapper.historicalChangeToHistoricalChangeRespose(expressionParam.getHistoricalChange());
	}
	else
	{
	    // Nodo vivo ya borrado (DETACH DELETE destruyó la relación HISTORICAL_CHANGE):
	    // la fecha se recupera desde las propiedades denormalizadas del propio nodo sombra.
	    historicalChangeResponse = new HistoricalChangeResponse();

	    if(expressionParam.getStartChange() != null)
	    {
		historicalChangeResponse.setStartChange(expressionParam.getStartChange().toInstant(ZoneOffset.UTC));
	    }

	    if(expressionParam.getEndChange() != null)
	    {
		historicalChangeResponse.setEndChange(expressionParam.getEndChange().toInstant(ZoneOffset.UTC));
	    }
	}

	historicalChangeResponse.setOperation(expressionParam.getHistoricalChangeOperation());
	expressionParamResponse.setHistoricalChange(historicalChangeResponse);

	return expressionParamResponse;
    }


    public HistoricalExpressionParam expressionParamResponseToExpressionParam(HistoricalExpressionParamResponse expressionParamResponse)
    {

	HistoricalExpressionParam expressionParam = new HistoricalExpressionParam();

	expressionParam.setCustomId(expressionParamResponse.getId());
	expressionParam.setName(expressionParamResponse.getName());
	expressionParam.setType(expressionParamResponse.getType());
	expressionParam.setRequired(expressionParamResponse.getRequired());

	if(expressionParamResponse.getDefaultValueAssetValueResponse() != null)
	{
	    expressionParam.setHistoricalDefaultValueAssetValue(defaultValueResponseMapper.defaultValueAssetValueResponseToDefaultValueAssetValue(expressionParamResponse.getDefaultValueAssetValueResponse()));
	}
	expressionParam.setPosition(expressionParamResponse.getPosition());
	expressionParam.setVersion(expressionParamResponse.getVersion());

	return expressionParam;
    }


    public Set<HistoricalExpressionParam> expressionParamCollectionResponseToExpressionParamCollection(Collection<HistoricalExpressionParamResponse> expressionParamsResponse)
    {

	Set<HistoricalExpressionParam> expressionParams = new HashSet<>();

	for (HistoricalExpressionParamResponse expressionParamResponse : expressionParamsResponse)
	{
	    expressionParams.add(expressionParamResponseToExpressionParam(expressionParamResponse));
	}

	return expressionParams;

    }


    public Collection<HistoricalExpressionParamResponse> expressionParamsToExpressionParamResponseCollection(Collection<HistoricalExpressionParam> expressionParams)
    {

	Collection<HistoricalExpressionParamResponse> expressionParamsResponse = new ArrayList<>();

	for (HistoricalExpressionParam expressionParam : expressionParams)
	{
	    expressionParamsResponse.add(expressionParamToExpressionParamResponse(expressionParam));
	}

	return expressionParamsResponse;
    }


    public ResponsePage<HistoricalExpressionParamResponse> expressionParamToExpressionParamResponsePage(ResponsePage<HistoricalExpressionParam> expressionParams)
    {

	List<HistoricalExpressionParamResponse> expressionParamResponses = new ArrayList<>();

	for (HistoricalExpressionParam expressionParam : expressionParams)
	{
	    expressionParamResponses.add(expressionParamToExpressionParamResponse(expressionParam));
	}

	return new ResponsePage<>(expressionParamResponses, expressionParams.getPageable(), expressionParams.getTotalElements());
    }


    public ResponsePage<HistoricalExpressionParamResponse> expresionParamsToExpressionParamsResponsePage(Collection<HistoricalExpressionParam> expressionParams)
    {

	List<HistoricalExpressionParamResponse> expressionParamResponseList = new ArrayList<>();

	for (HistoricalExpressionParam expressionParam : expressionParams)
	{
	    expressionParamResponseList.add(expressionParamToExpressionParamResponse(expressionParam));
	}

	return new ResponsePage<>(expressionParamResponseList);
    }

}
