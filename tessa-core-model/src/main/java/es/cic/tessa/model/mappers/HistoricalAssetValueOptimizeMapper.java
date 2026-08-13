package es.cic.tessa.model.mappers;


import java.util.ArrayList;
import java.util.Collection;
import es.cic.tessa.model.Function;
import es.cic.tessa.model.HistoricalAssetValue;
import es.cic.tessa.model.HistoricalExpressionParam;
import es.cic.tessa.model.HistoricalTemplateAttribute;
import es.cic.tessa.model.optimize.AssetOptimize;
import es.cic.tessa.model.optimize.AssetValueOptimize;
import es.cic.tessa.model.optimize.ExpressionParamOptimize;
import es.cic.tessa.model.optimize.TemplateAttributeOptimize;
import es.cic.tessa.model.optimize.TemplateOptimize;


/**
 * Mapea un {@link HistoricalAssetValue} (snapshot as-of ya resuelto, p.ej. por
 * {@code HistoricalAssetService.findAssetAsOf}) directamente a {@link AssetValueOptimize}, sin
 * pasar por la entidad viva. Evita el doble salto Historical→vivo→Optimize y las pérdidas de campo
 * que introduce {@code HistoricalAssetValueToEntityMapper} (p.ej. no mapea
 * {@code historicalTemplateAttributeCollectionMapping} — comentario explícito en esa clase).
 * <p>
 * Mismo patrón estático que {@link AssetValueOptimizeMapper}: {@code final class} +
 * {@code toOptimize(...)}. A diferencia de la entidad viva, aquí {@code Function
 * expressionProperties} ya llega deserializado como POJO (no como JSON), así que el desglose a los
 * 7 campos planos de {@code AssetValueOptimize} se aplica directamente sin volver a parsear.
 * <p>
 * {@code idBinary} no tiene origen en {@code HistoricalAssetValue} (no es una propiedad
 * historizada) y queda {@code null}, igual que hoy hace {@link AssetValueOptimizeMapper} cuando el
 * valor no es numérico.
 */
public final class HistoricalAssetValueOptimizeMapper
{

    private HistoricalAssetValueOptimizeMapper()
    {

    }


    public static AssetValueOptimize toOptimize(HistoricalAssetValue historicalAssetValue)
    {

	if(historicalAssetValue == null)
	{
	    return null;
	}

	AssetValueOptimize optimize = new AssetValueOptimize();
	optimize.setId(historicalAssetValue.getCustomId());
	optimize.setName(historicalAssetValue.getName());
	optimize.setGroups(historicalAssetValue.getGroups());
	optimize.setValue(historicalAssetValue.getValue());

	mapFunctionFields(historicalAssetValue.getExpressionProperties(), optimize);
	mapAsset(historicalAssetValue, optimize);
	mapAssetReference(historicalAssetValue, optimize);
	mapTemplateAttribute(historicalAssetValue, optimize);
	mapExpressionParams(historicalAssetValue, optimize);

	return optimize;
    }


    private static void mapFunctionFields(Function function, AssetValueOptimize optimize)
    {

	if(function == null)
	{
	    return;
	}

	optimize.setExpressionFunction(function.getExpressionFunction());
	optimize.setCronExpression(function.getCronExpression());
	optimize.setExpressionEvent(function.getExpressionEvent());
	optimize.setCronDelay(function.getCronDelay());
	optimize.setCronTimeZone(function.getCronTimeZone());
	optimize.setIgnoreNoData(function.getIgnoreNoData());
	optimize.setRefillingCalculation(function.getRefillingCalculation());
    }


    private static void mapAsset(HistoricalAssetValue historicalAssetValue, AssetValueOptimize optimize)
    {

	if(historicalAssetValue.getHistoricalAsset() == null)
	{
	    return;
	}

	AssetOptimize assetOptimize = new AssetOptimize();
	assetOptimize.setId(historicalAssetValue.getHistoricalAsset().getCustomId());
	assetOptimize.setName(historicalAssetValue.getHistoricalAsset().getName());
	assetOptimize.setGroups(historicalAssetValue.getHistoricalAsset().getGroups());
	assetOptimize.setIdentificator(historicalAssetValue.getHistoricalAsset().getIdentificator());
	assetOptimize.setActive(historicalAssetValue.getHistoricalAsset().getActive());

	if(historicalAssetValue.getHistoricalAsset().getHistoricalTemplate() != null)
	{
	    TemplateOptimize tOptimize = new TemplateOptimize();
	    tOptimize.setId(historicalAssetValue.getHistoricalAsset().getHistoricalTemplate().getCustomId());
	    tOptimize.setName(historicalAssetValue.getHistoricalAsset().getHistoricalTemplate().getName());
	    tOptimize.setGroups(historicalAssetValue.getHistoricalAsset().getHistoricalTemplate().getGroups());
	    tOptimize.setType(historicalAssetValue.getHistoricalAsset().getHistoricalTemplate().getType());
	    assetOptimize.setTemplateOptimize(tOptimize);
	}

	optimize.setAssetOptimize(assetOptimize);
    }


    private static void mapAssetReference(HistoricalAssetValue historicalAssetValue, AssetValueOptimize optimize)
    {

	if(historicalAssetValue.getHistoricalAssetReference() == null || historicalAssetValue.getHistoricalAssetReference().getHistoricalAsset() == null)
	{
	    return;
	}

	AssetOptimize refOptimize = new AssetOptimize();
	refOptimize.setId(historicalAssetValue.getHistoricalAssetReference().getHistoricalAsset().getCustomId());
	refOptimize.setName(historicalAssetValue.getHistoricalAssetReference().getHistoricalAsset().getName());
	refOptimize.setGroups(historicalAssetValue.getHistoricalAssetReference().getHistoricalAsset().getGroups());

	if(historicalAssetValue.getHistoricalAssetReference().getHistoricalAsset().getHistoricalTemplate() != null)
	{
	    TemplateOptimize tOptimize = new TemplateOptimize();
	    tOptimize.setId(historicalAssetValue.getHistoricalAssetReference().getHistoricalAsset().getHistoricalTemplate().getCustomId());
	    tOptimize.setName(historicalAssetValue.getHistoricalAssetReference().getHistoricalAsset().getHistoricalTemplate().getName());
	    tOptimize.setGroups(historicalAssetValue.getHistoricalAssetReference().getHistoricalAsset().getHistoricalTemplate().getGroups());
	    tOptimize.setType(historicalAssetValue.getHistoricalAssetReference().getHistoricalAsset().getHistoricalTemplate().getType());
	    refOptimize.setTemplateOptimize(tOptimize);
	}

	optimize.setAssetReferenceOptimize(refOptimize);
    }


    private static void mapTemplateAttribute(HistoricalAssetValue historicalAssetValue, AssetValueOptimize optimize)
    {

	HistoricalTemplateAttribute ta = historicalAssetValue.getHistoricalTemplateAttribute();

	if(ta == null)
	{
	    return;
	}

	optimize.setTemplateAttributeOptimize(HistoricalTemplateAttributeOptimizeMapper.toOptimize(ta));
    }


    private static void mapExpressionParams(HistoricalAssetValue historicalAssetValue, AssetValueOptimize optimize)
    {

	if(historicalAssetValue.getExpressionParams() == null || historicalAssetValue.getExpressionParams().isEmpty())
	{
	    return;
	}

	Collection<ExpressionParamOptimize> params = new ArrayList<>();

	for (HistoricalExpressionParam param : historicalAssetValue.getExpressionParams())
	{
	    params.add(HistoricalExpressionParamOptimizeMapper.toOptimize(param));
	}

	optimize.setExpressionParams(params);
    }
}
