package es.cic.tessa.model.mappers;


import java.util.ArrayList;
import java.util.Collection;
import es.cic.tessa.model.Function;
import es.cic.tessa.model.HistoricalExpressionParam;
import es.cic.tessa.model.HistoricalTemplateAttribute;
import es.cic.tessa.model.optimize.ExpressionParamOptimize;
import es.cic.tessa.model.optimize.TemplateAttributeOptimize;
import es.cic.tessa.model.optimize.TemplateOptimize;


/**
 * Mapea un {@link HistoricalTemplateAttribute} (snapshot as-of ya resuelto, p.ej. por
 * {@code HistoricalTemplateService.findTemplateAsOf} / {@code buildTemplateAttributeIndexAsOf})
 * directamente a {@link TemplateAttributeOptimize}, sin pasar por la entidad viva. Mismo motivo y
 * patrón que {@link HistoricalAssetValueOptimizeMapper}: evita el doble salto Historical→vivo→Optimize
 * y sus pérdidas de campo.
 * <p>
 * {@code Function expressionProperties} ya llega deserializado como POJO en el snapshot histórico
 * (a diferencia de la entidad viva, donde {@code AssemblyTemplateAttributeOptimize} lo parsea desde
 * JSON), así que el desglose a los 7 campos planos se aplica directamente.
 */
public final class HistoricalTemplateAttributeOptimizeMapper
{

    private HistoricalTemplateAttributeOptimizeMapper()
    {

    }


    public static TemplateAttributeOptimize toOptimize(HistoricalTemplateAttribute historicalTemplateAttribute)
    {

	if(historicalTemplateAttribute == null)
	{
	    return null;
	}

	TemplateAttributeOptimize optimize = new TemplateAttributeOptimize();
	optimize.setId(historicalTemplateAttribute.getCustomId());
	optimize.setName(historicalTemplateAttribute.getName());
	optimize.setGroups(historicalTemplateAttribute.getGroups());
	optimize.setType(historicalTemplateAttribute.getType());
	optimize.setCollection(historicalTemplateAttribute.getCollection());
	optimize.setCalculatedValue(historicalTemplateAttribute.getCalculatedValue());

	mapExpressionProperties(historicalTemplateAttribute.getExpressionProperties(), optimize);
	mapTemplate(historicalTemplateAttribute, optimize);
	mapTemplateReference(historicalTemplateAttribute, optimize);
	mapExpressionParams(historicalTemplateAttribute, optimize);

	return optimize;
    }


    private static void mapExpressionProperties(Function function, TemplateAttributeOptimize optimize)
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


    private static void mapTemplate(HistoricalTemplateAttribute historicalTemplateAttribute, TemplateAttributeOptimize optimize)
    {

	if(historicalTemplateAttribute.getHistoricalTemplate() == null)
	{
	    return;
	}

	TemplateOptimize tOptimize = new TemplateOptimize();
	tOptimize.setId(historicalTemplateAttribute.getHistoricalTemplate().getCustomId());
	tOptimize.setName(historicalTemplateAttribute.getHistoricalTemplate().getName());
	tOptimize.setGroups(historicalTemplateAttribute.getHistoricalTemplate().getGroups());
	optimize.setTemplateOptimize(tOptimize);
    }


    private static void mapTemplateReference(HistoricalTemplateAttribute historicalTemplateAttribute, TemplateAttributeOptimize optimize)
    {

	if(historicalTemplateAttribute.getHistoricalTemplateReference() == null || historicalTemplateAttribute.getHistoricalTemplateReference().getHistoricalTemplate() == null)
	{
	    return;
	}

	TemplateOptimize refOptimize = new TemplateOptimize();
	refOptimize.setId(historicalTemplateAttribute.getHistoricalTemplateReference().getHistoricalTemplate().getCustomId());
	refOptimize.setName(historicalTemplateAttribute.getHistoricalTemplateReference().getHistoricalTemplate().getName());
	refOptimize.setGroups(historicalTemplateAttribute.getHistoricalTemplateReference().getHistoricalTemplate().getGroups());
	optimize.setTemplateReferenceOptimize(refOptimize);
    }


    private static void mapExpressionParams(HistoricalTemplateAttribute historicalTemplateAttribute, TemplateAttributeOptimize optimize)
    {

	if(historicalTemplateAttribute.getHistoricalExpressionParams() == null || historicalTemplateAttribute.getHistoricalExpressionParams().isEmpty())
	{
	    return;
	}

	Collection<ExpressionParamOptimize> params = new ArrayList<>();

	for (HistoricalExpressionParam param : historicalTemplateAttribute.getHistoricalExpressionParams())
	{
	    params.add(HistoricalExpressionParamOptimizeMapper.toOptimize(param));
	}

	optimize.setExpressionParams(params);
    }
}
