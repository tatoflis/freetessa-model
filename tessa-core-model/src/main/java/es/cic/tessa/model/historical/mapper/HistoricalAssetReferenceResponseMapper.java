package es.cic.tessa.model.historical.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import es.cic.tessa.model.HistoricalAssetReference;
import es.cic.tessa.model.historical.dto.HistoricalAssetReferenceResponse;


@Component
public class HistoricalAssetReferenceResponseMapper
{

    @Autowired
    private HistoricalAssetResponseMapper historicalAssetResponseMapper;

    public HistoricalAssetReferenceResponse historicalAssetReferenceToHistoricalAssetReferenceRespose(HistoricalAssetReference historicalAssetReference)
    {

	HistoricalAssetReferenceResponse historicalAssetReferenceResponse = new HistoricalAssetReferenceResponse();

	historicalAssetReferenceResponse.setId(historicalAssetReference.getId());
	historicalAssetReferenceResponse.setReferenceType(historicalAssetReference.getReferenceType());
	historicalAssetReferenceResponse.setRelationType(historicalAssetReference.getRelationType());

	historicalAssetReferenceResponse.setHistoricalAssetResponse(historicalAssetResponseMapper.historicalAssetToHistoricalAssetRespose(historicalAssetReference.getHistoricalAsset()));

	return historicalAssetReferenceResponse;
    }

}
