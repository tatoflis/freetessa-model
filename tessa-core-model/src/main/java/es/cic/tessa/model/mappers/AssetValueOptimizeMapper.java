package es.cic.tessa.model.mappers;


import java.util.ArrayList;
import java.util.Collection;
import es.cic.tessa.model.AssetValue;
import es.cic.tessa.model.ExpressionParam;
import es.cic.tessa.model.Function;
import es.cic.tessa.model.TemplateAttribute;
import es.cic.tessa.model.optimize.AssetOptimize;
import es.cic.tessa.model.optimize.AssetValueOptimize;
import es.cic.tessa.model.optimize.ExpressionParamOptimize;
import es.cic.tessa.model.optimize.TemplateAttributeOptimize;
import es.cic.tessa.model.optimize.TemplateOptimize;


public final class AssetValueOptimizeMapper
{

    private AssetValueOptimizeMapper()
    {

    }


    public static AssetValueOptimize toOptimize(AssetValue assetValue)
    {

	if(assetValue == null)
	{
	    return null;
	}

	AssetValueOptimize optimize = new AssetValueOptimize();
	optimize.setId(assetValue.getCustomId());
	optimize.setName(assetValue.getName());
	optimize.setGroups(assetValue.getGroups());
	optimize.setValue(assetValue.getValue());

	if(assetValue.getIdBinary() != null && !assetValue.getIdBinary().isEmpty())
	{
	    try
	    {
		optimize.setIdBinary(Long.parseLong(assetValue.getIdBinary()));
	    }
	    catch (NumberFormatException e)
	    {
		// idBinary no es numérico, se omite
	    }
	}

	mapFunctionFields(assetValue.getExpressionProperties(), optimize);
	mapAsset(assetValue, optimize);
	mapAssetReference(assetValue, optimize);
	mapTemplateAttribute(assetValue, optimize);
	mapExpressionParams(assetValue, optimize);

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


    private static void mapAsset(AssetValue assetValue, AssetValueOptimize optimize)
    {

	if(assetValue.getAsset() == null)
	{
	    return;
	}

	AssetOptimize assetOptimize = new AssetOptimize();
	assetOptimize.setId(assetValue.getAsset().getCustomId());
	assetOptimize.setName(assetValue.getAsset().getName());
	assetOptimize.setGroups(assetValue.getAsset().getGroups());
	assetOptimize.setIdentificator(assetValue.getAsset().getIdentificator());
	assetOptimize.setActive(assetValue.getAsset().getActive());

	if(assetValue.getAsset().getTemplate() != null)
	{
	    TemplateOptimize tOptimize = new TemplateOptimize();
	    tOptimize.setId(assetValue.getAsset().getTemplate().getCustomId());
	    tOptimize.setName(assetValue.getAsset().getTemplate().getName());
	    tOptimize.setGroups(assetValue.getAsset().getTemplate().getGroups());
	    tOptimize.setType(assetValue.getAsset().getTemplate().getType());
	    assetOptimize.setTemplateOptimize(tOptimize);
	}

	optimize.setAssetOptimize(assetOptimize);
    }


    private static void mapAssetReference(AssetValue assetValue, AssetValueOptimize optimize)
    {

	if(assetValue.getAssetReference() == null || assetValue.getAssetReference().getAsset() == null)
	{
	    return;
	}

	AssetOptimize refOptimize = new AssetOptimize();
	refOptimize.setId(assetValue.getAssetReference().getAsset().getCustomId());
	refOptimize.setName(assetValue.getAssetReference().getAsset().getName());
	refOptimize.setGroups(assetValue.getAssetReference().getAsset().getGroups());
	refOptimize.setIdentificator(assetValue.getAssetReference().getAsset().getIdentificator());
	refOptimize.setActive(assetValue.getAssetReference().getAsset().getActive());

	if(assetValue.getAssetReference().getAsset().getTemplate() != null)
	{
	    TemplateOptimize tOptimize = new TemplateOptimize();
	    tOptimize.setId(assetValue.getAssetReference().getAsset().getTemplate().getCustomId());
	    tOptimize.setName(assetValue.getAssetReference().getAsset().getTemplate().getName());
	    tOptimize.setGroups(assetValue.getAssetReference().getAsset().getTemplate().getGroups());
	    tOptimize.setType(assetValue.getAssetReference().getAsset().getTemplate().getType());
	    refOptimize.setTemplateOptimize(tOptimize);
	}

	optimize.setAssetReferenceOptimize(refOptimize);
    }


    private static void mapTemplateAttribute(AssetValue assetValue, AssetValueOptimize optimize)
    {

	TemplateAttribute ta = assetValue.getTemplateAttribute();

	if(ta == null)
	{
	    return;
	}

	TemplateAttributeOptimize taOptimize = new TemplateAttributeOptimize();
	taOptimize.setId(ta.getCustomId());
	taOptimize.setName(ta.getName());
	taOptimize.setGroups(ta.getGroups());
	taOptimize.setType(ta.getType());
	taOptimize.setCollection(ta.getCollection());

	if(ta.getExpressionProperties() != null)
	{
	    Function fn = ta.getExpressionProperties();
	    taOptimize.setExpressionFunction(fn.getExpressionFunction());
	    taOptimize.setCronExpression(fn.getCronExpression());
	    taOptimize.setExpressionEvent(fn.getExpressionEvent());
	    taOptimize.setCronDelay(fn.getCronDelay());
	    taOptimize.setCronTimeZone(fn.getCronTimeZone());
	    taOptimize.setIgnoreNoData(fn.getIgnoreNoData());
	    taOptimize.setRefillingCalculation(fn.getRefillingCalculation());
	}

	if(ta.getCalculatedValue() != null)
	{
	    taOptimize.setCalculatedValue(ta.getCalculatedValue());
	}

	if(ta.getTemplate() != null)
	{
	    TemplateOptimize tOptimize = new TemplateOptimize();
	    tOptimize.setId(ta.getTemplate().getCustomId());
	    tOptimize.setName(ta.getTemplate().getName());
	    tOptimize.setGroups(ta.getTemplate().getGroups());
	    taOptimize.setTemplateOptimize(tOptimize);
	}

	if(ta.getTemplateReference() != null && ta.getTemplateReference().getTemplate() != null)
	{
	    TemplateOptimize refOptimize = new TemplateOptimize();
	    refOptimize.setId(ta.getTemplateReference().getTemplate().getCustomId());
	    refOptimize.setName(ta.getTemplateReference().getTemplate().getName());
	    refOptimize.setGroups(ta.getTemplateReference().getTemplate().getGroups());
	    taOptimize.setTemplateReferenceOptimize(refOptimize);
	}

	optimize.setTemplateAttributeOptimize(taOptimize);
    }


    private static void mapExpressionParams(AssetValue assetValue, AssetValueOptimize optimize)
    {

	if(assetValue.getExpressionParams() == null || assetValue.getExpressionParams().isEmpty())
	{
	    return;
	}

	Collection<ExpressionParamOptimize> params = new ArrayList<>();

	for (ExpressionParam param : assetValue.getExpressionParams())
	{
	    ExpressionParamOptimize paramOptimize = new ExpressionParamOptimize();
	    paramOptimize.setId(param.getCustomId());
	    paramOptimize.setName(param.getName());
	    paramOptimize.setGroups(param.getGroups());
	    params.add(paramOptimize);
	}

	optimize.setExpressionParams(params);
    }
}
