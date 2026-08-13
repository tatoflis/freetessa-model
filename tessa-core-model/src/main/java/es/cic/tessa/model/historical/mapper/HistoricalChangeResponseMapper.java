package es.cic.tessa.model.historical.mapper;


import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import es.cic.tessa.model.HistoricalAssetChange;
import es.cic.tessa.model.HistoricalAssetValueChange;
import es.cic.tessa.model.HistoricalExpressionParamChange;
import es.cic.tessa.model.HistoricalOrganizerChange;
import es.cic.tessa.model.HistoricalTemplateAttributeChange;
import es.cic.tessa.model.HistoricalTemplateAttributeCollectionMappingChange;
import es.cic.tessa.model.HistoricalTemplateChange;
import es.cic.tessa.model.historical.dto.HistoricalChangeResponse;
import es.cic.tessa.model.mappers.AssetResponseMapper;


@Component
public class HistoricalChangeResponseMapper
{

    @Autowired
    private AssetResponseMapper assetResponseMapper;

    public HistoricalChangeResponse historicalChangeToHistoricalChangeRespose(HistoricalAssetChange historicalAssetChange)
    {

	HistoricalChangeResponse historicalChangeResponse = new HistoricalChangeResponse();

	historicalChangeResponse.setId(historicalAssetChange.getId());
	historicalChangeResponse.setOperation(historicalAssetChange.getHistoricalChangeOperation());
	historicalChangeResponse.setStartChange(historicalAssetChange.getStartChange().toInstant(ZoneOffset.UTC));

	if(historicalAssetChange.getEndChange() != null)
	{
	    historicalChangeResponse.setEndChange(historicalAssetChange.getEndChange().toInstant(ZoneOffset.UTC));
	}

	historicalChangeResponse.setOriginalAsset(assetResponseMapper.assetToAssetRespose(historicalAssetChange.getAsset()));

	return historicalChangeResponse;
    }


    public HistoricalChangeResponse historicalChangeToHistoricalChangeRespose(HistoricalOrganizerChange historicalOrganizerChange)
    {

	HistoricalChangeResponse historicalChangeResponse = new HistoricalChangeResponse();

	historicalChangeResponse.setId(historicalOrganizerChange.getId());
	historicalChangeResponse.setOperation(historicalOrganizerChange.getHistoricalChangeOperation());
	historicalChangeResponse.setStartChange(historicalOrganizerChange.getStartChange().toInstant(ZoneOffset.UTC));

	if(historicalOrganizerChange.getEndChange() != null)
	{
	    historicalChangeResponse.setEndChange(historicalOrganizerChange.getEndChange().toInstant(ZoneOffset.UTC));
	}

	return historicalChangeResponse;
    }


    public HistoricalChangeResponse historicalChangeToHistoricalChangeRespose(HistoricalTemplateChange historicalTemplateChange)
    {

	HistoricalChangeResponse historicalChangeResponse = new HistoricalChangeResponse();

	historicalChangeResponse.setId(historicalTemplateChange.getId());
	historicalChangeResponse.setOperation(historicalTemplateChange.getHistoricalChangeOperation());
	historicalChangeResponse.setStartChange(historicalTemplateChange.getStartChange().toInstant(ZoneOffset.UTC));

	if(historicalTemplateChange.getEndChange() != null)
	{
	    historicalChangeResponse.setEndChange(historicalTemplateChange.getEndChange().toInstant(ZoneOffset.UTC));
	}

	return historicalChangeResponse;
    }


    public HistoricalChangeResponse historicalChangeToHistoricalChangeRespose(HistoricalTemplateAttributeChange historicalTemplateAttributeChange)
    {

	HistoricalChangeResponse historicalChangeResponse = new HistoricalChangeResponse();

	historicalChangeResponse.setId(historicalTemplateAttributeChange.getId());
	historicalChangeResponse.setOperation(historicalTemplateAttributeChange.getHistoricalChangeOperation());
	historicalChangeResponse.setStartChange(historicalTemplateAttributeChange.getStartChange().toInstant(ZoneOffset.UTC));

	if(historicalTemplateAttributeChange.getEndChange() != null)
	{
	    historicalChangeResponse.setEndChange(historicalTemplateAttributeChange.getEndChange().toInstant(ZoneOffset.UTC));
	}

	return historicalChangeResponse;
    }


    public HistoricalChangeResponse historicalChangeToHistoricalChangeRespose(HistoricalAssetValueChange historicalAssetValueChange)
    {

	HistoricalChangeResponse historicalChangeResponse = new HistoricalChangeResponse();

	historicalChangeResponse.setId(historicalAssetValueChange.getId());
	historicalChangeResponse.setOperation(historicalAssetValueChange.getHistoricalChangeOperation());
	historicalChangeResponse.setStartChange(historicalAssetValueChange.getStartChange().toInstant(ZoneOffset.UTC));

	if(historicalAssetValueChange.getEndChange() != null)
	{
	    historicalChangeResponse.setEndChange(historicalAssetValueChange.getEndChange().toInstant(ZoneOffset.UTC));
	}

	return historicalChangeResponse;
    }


    public HistoricalChangeResponse historicalChangeToHistoricalChangeRespose(HistoricalTemplateAttributeCollectionMappingChange historicalTemplateAttributeCollectionMappingChange)
    {

	HistoricalChangeResponse historicalChangeResponse = new HistoricalChangeResponse();

	historicalChangeResponse.setId(historicalTemplateAttributeCollectionMappingChange.getId());
	historicalChangeResponse.setOperation(historicalTemplateAttributeCollectionMappingChange.getHistoricalChangeOperation());
	historicalChangeResponse.setStartChange(historicalTemplateAttributeCollectionMappingChange.getStartChange().toInstant(ZoneOffset.UTC));

	if(historicalTemplateAttributeCollectionMappingChange.getEndChange() != null)
	{
	    historicalChangeResponse.setEndChange(historicalTemplateAttributeCollectionMappingChange.getEndChange().toInstant(ZoneOffset.UTC));
	}

	return historicalChangeResponse;
    }


    public HistoricalChangeResponse historicalChangeToHistoricalChangeRespose(HistoricalExpressionParamChange historicalExpressionParamChange)
    {

	HistoricalChangeResponse historicalChangeResponse = new HistoricalChangeResponse();

	historicalChangeResponse.setId(historicalExpressionParamChange.getId());
	historicalChangeResponse.setOperation(historicalExpressionParamChange.getHistoricalChangeOperation());
	historicalChangeResponse.setStartChange(historicalExpressionParamChange.getStartChange().toInstant(ZoneOffset.UTC));

	if(historicalExpressionParamChange.getEndChange() != null)
	{
	    historicalChangeResponse.setEndChange(historicalExpressionParamChange.getEndChange().toInstant(ZoneOffset.UTC));
	}

	return historicalChangeResponse;
    }
}
