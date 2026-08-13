package es.cic.tessa.model.historical.mapper;


import java.time.ZoneOffset;
import java.util.LinkedHashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.HistoricalAsset;
import es.cic.tessa.model.historical.dto.HistoricalAssetResponse;
import es.cic.tessa.model.historical.dto.HistoricalChangeResponse;


@Component
public class HistoricalAssetResponseMapper
{

    @Autowired
    private HistoricalTemplateResponseMapper historicalTemplateResponseMapper;

    @Autowired
    private HistoricalAssetValueResponseMapper historicalAssetValueResponseMapper;

    @Autowired
    private HistoricalOrganizerResponseMapper historicalOrganizerResponseMapper;

    @Autowired
    private HistoricalChangeResponseMapper historicalChangeResponseMapper;

    public HistoricalAssetResponse historicalAssetToHistoricalAssetRespose(HistoricalAsset historicalAsset)
    {

	HistoricalAssetResponse historicalAssetResponse = new HistoricalAssetResponse();

	historicalAssetResponse.setId(historicalAsset.getCustomId());
	historicalAssetResponse.setActive(historicalAsset.getActive());
	historicalAssetResponse.setName(historicalAsset.getName());
	historicalAssetResponse.setIdentificator(historicalAsset.getIdentificator());
	historicalAssetResponse.setPhysicalPath(historicalAsset.getPhysicalPath());
	historicalAssetResponse.setLogicalPath(historicalAsset.getLogicalPath());
	historicalAssetResponse.setDescription(historicalAsset.getDescription());
	historicalAssetResponse.setIcon(historicalAsset.getIcon());
	historicalAssetResponse.setInsertDate(historicalAsset.getInsertDate().toInstant(ZoneOffset.UTC));
	historicalAssetResponse.setNumComplexAssets(historicalAsset.getNumComplexAssets());

	if(historicalAsset.getModifDate() != null)
	{
	    historicalAssetResponse.setModifDate(historicalAsset.getModifDate().toInstant(ZoneOffset.UTC));
	}

	if(historicalAsset.getHistoricalTemplate() != null)
	{
	    historicalAssetResponse.setHistoricalTemplate(historicalTemplateResponseMapper.historicalTemplateTohistoricalTemplateRespose(historicalAsset.getHistoricalTemplate()));
	}

	if(historicalAsset.getHistoricalDependsAsset() != null)
	{
	    historicalAssetResponse.setHistoricalAssetDependsResponse(historicalAssetToHistoricalAssetRespose(historicalAsset.getHistoricalDependsAsset()));
	}

	if(historicalAsset.getHistoricalValues() != null)
	{
	    historicalAssetResponse.setHistoricalValues(historicalAssetValueResponseMapper.historicalAssetValueCollectionToHistoricalAssetValueResposeCollection(historicalAsset.getHistoricalValues()));
	}

	if(historicalAsset.getHistoricalOrganizers() != null)
	{
	    historicalAssetResponse.setHistoricalOrganizers(historicalOrganizerResponseMapper.historicalOrganizerCollectionToHistoricalOrganizerResposeCollection(historicalAsset.getHistoricalOrganizers()));
	}

	HistoricalChangeResponse historicalChangeResponse;

	if(historicalAsset.getHistoricalChange() != null)
	{
	    historicalChangeResponse = historicalChangeResponseMapper.historicalChangeToHistoricalChangeRespose(historicalAsset.getHistoricalChange());
	}
	else
	{
	    // Nodo vivo ya borrado (DETACH DELETE destruyó la relación HISTORICAL_CHANGE):
	    // la fecha se recupera desde las propiedades denormalizadas del propio nodo sombra.
	    historicalChangeResponse = new HistoricalChangeResponse();

	    if(historicalAsset.getStartChange() != null)
	    {
		historicalChangeResponse.setStartChange(historicalAsset.getStartChange().toInstant(ZoneOffset.UTC));
	    }

	    if(historicalAsset.getEndChange() != null)
	    {
		historicalChangeResponse.setEndChange(historicalAsset.getEndChange().toInstant(ZoneOffset.UTC));
	    }
	}

	historicalChangeResponse.setOperation(historicalAsset.getHistoricalChangeOperation());
	historicalAssetResponse.setHistoricalChange(historicalChangeResponse);

	return historicalAssetResponse;
    }


    public ResponsePage<HistoricalAssetResponse> historicalAssetPageToHistoricalAssetResposePage(ResponsePage<HistoricalAsset> historicalAssets)
    {

	LinkedHashSet<HistoricalAssetResponse> historicalAssetsResponse = new LinkedHashSet<>();

	for (HistoricalAsset historicalAsset : historicalAssets)
	{
	    historicalAssetsResponse.add(historicalAssetToHistoricalAssetRespose(historicalAsset));

	}

	return new ResponsePage<>(historicalAssetsResponse, historicalAssets.getPageable(), historicalAssets.getTotalElements());

    }
}
