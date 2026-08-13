package es.cic.tessa.model.mappers;


import es.cic.tessa.model.HistoricalAsset;
import es.cic.tessa.model.optimize.AssetOptimize;


/**
 * Mapea un {@link HistoricalAsset} (snapshot as-of ya resuelto, p.ej. por
 * {@code HistoricalAssetService.findAssetAsOf}) directamente a {@link AssetOptimize}, sin pasar
 * por la entidad viva. Mismo alcance de campos que {@link AssetOptimizeMapper} con la entidad
 * viva: template y dependsAsset, sin descender a {@code historicalValues}/{@code historicalOrganizers}
 * — evita así el ciclo {@code Asset ↔ AssetValue} (cada value referencia de vuelta a su
 * {@code historicalAsset}) sin necesidad de guarda de recursión.
 */
public final class HistoricalAssetOptimizeMapper
{

    private HistoricalAssetOptimizeMapper()
    {

    }


    public static AssetOptimize toOptimize(HistoricalAsset historicalAsset)
    {

	if(historicalAsset == null)
	{
	    return null;
	}

	AssetOptimize optimize = new AssetOptimize();
	optimize.setId(historicalAsset.getCustomId());
	optimize.setName(historicalAsset.getName());
	optimize.setGroups(historicalAsset.getGroups());
	optimize.setIdentificator(historicalAsset.getIdentificator());
	optimize.setActive(historicalAsset.getActive());

	if(historicalAsset.getHistoricalTemplate() != null)
	{
	    optimize.setTemplateOptimize(HistoricalTemplateOptimizeMapper.toOptimizeThin(historicalAsset.getHistoricalTemplate()));
	}

	if(historicalAsset.getHistoricalDependsAsset() != null)
	{
	    optimize.setAssetDependsOptimize(toOptimize(historicalAsset.getHistoricalDependsAsset()));
	}

	return optimize;
    }
}
