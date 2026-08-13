package es.cic.tessa.lookup.expression.model;


import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import es.cic.tessa.model.AssetValue;
import es.cic.tessa.model.ExpressionParam;
import es.cic.tessa.model.Function;
import es.cic.tessa.model.TemplateAttribute;
import es.cic.tessa.model.optimize.AssetValueOptimize;
import es.cic.tessa.model.optimize.ExpressionParamOptimize;
import es.cic.tessa.model.optimize.TemplateAttributeOptimize;


public class ExpressionFunctionDataOptimize implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Function expressionProperties = new Function();
    private Set<ExpressionParamOptimize> expressionParams = new LinkedHashSet<>();
    private Set<ExpressionParamOptimize> expressionParamsAccumulated = new LinkedHashSet<>();
    private boolean nameCalculated;
    private Instant actualMoment;
    private Boolean overriddenExpression;

    public static boolean resolveIgnoreNoData(AssetValueOptimize assetValueOptimize, TemplateAttributeOptimize templateAttributeOptimize)
    {

	if(assetValueOptimize != null && assetValueOptimize.getIgnoreNoData() != null)
	{
	    return assetValueOptimize.getIgnoreNoData();
	}

	if(templateAttributeOptimize != null && templateAttributeOptimize.getIgnoreNoData() != null)
	{
	    return templateAttributeOptimize.getIgnoreNoData();
	}

	return false;
    }


    public static boolean resolveIgnoreNoData(AssetValue assetValue, TemplateAttribute templateAttribute)
    {

	if(assetValue != null && assetValue.getExpressionProperties() != null && assetValue.getExpressionProperties().getIgnoreNoData() != null)
	{
	    return assetValue.getExpressionProperties().getIgnoreNoData();
	}

	if(templateAttribute != null && templateAttribute.getExpressionProperties() != null && templateAttribute.getExpressionProperties().getIgnoreNoData() != null)
	{
	    return templateAttribute.getExpressionProperties().getIgnoreNoData();
	}

	return false;
    }


    public static ExpressionFunctionDataOptimize buildExpressionFunctionData(TemplateAttributeOptimize templateAttributeOptimize, AssetValueOptimize assetValueOptimize)
    {

	ExpressionFunctionDataOptimize expressionFunctionData = new ExpressionFunctionDataOptimize();

	if(assetValueOptimize.getExpressionFunction() != null)
	{
	    expressionFunctionData.getExpressionProperties().setExpressionFunction(assetValueOptimize.getExpressionFunction());
	}
	else
	{
	    expressionFunctionData.getExpressionProperties().setExpressionFunction(templateAttributeOptimize.getExpressionFunction());
	}

	if(assetValueOptimize.getRefillingCalculation() != null)
	{
	    expressionFunctionData.getExpressionProperties().setRefillingCalculation(assetValueOptimize.getRefillingCalculation());
	}
	else
	{
	    expressionFunctionData.getExpressionProperties().setRefillingCalculation(templateAttributeOptimize.getRefillingCalculation());
	}

	expressionFunctionData.getExpressionProperties().setIgnoreNoData(resolveIgnoreNoData(assetValueOptimize, templateAttributeOptimize));

	if(assetValueOptimize.getCronExpression() != null)
	{
	    expressionFunctionData.getExpressionProperties().setCronExpression(assetValueOptimize.getCronExpression());
	}
	else
	{
	    expressionFunctionData.getExpressionProperties().setCronExpression(templateAttributeOptimize.getCronExpression());
	}

	if(assetValueOptimize.getExpressionEvent() != null)
	{
	    expressionFunctionData.getExpressionProperties().setExpressionEvent(assetValueOptimize.getExpressionEvent());
	    expressionFunctionData.setOverriddenExpression(true);
	}
	else
	{
	    expressionFunctionData.getExpressionProperties().setExpressionEvent(templateAttributeOptimize.getExpressionEvent());
	}

	if(assetValueOptimize.getCronTimeZone() != null)
	{
	    expressionFunctionData.getExpressionProperties().setCronTimeZone(assetValueOptimize.getCronTimeZone());
	}
	else
	{
	    expressionFunctionData.getExpressionProperties().setCronTimeZone(templateAttributeOptimize.getCronTimeZone());
	}

	if(assetValueOptimize.getCronDelay() != null)
	{
	    expressionFunctionData.getExpressionProperties().setCronDelay(assetValueOptimize.getCronDelay());
	}
	else
	{
	    expressionFunctionData.getExpressionProperties().setCronDelay(templateAttributeOptimize.getCronDelay());
	}

	if(assetValueOptimize.getExpressionParams().size() > 0)
	{
	    expressionFunctionData.getExpressionParams().addAll(assetValueOptimize.getExpressionParams());
	}
	else
	{
	    expressionFunctionData.getExpressionParams().addAll(templateAttributeOptimize.getExpressionParams());
	}

	return expressionFunctionData;
    }


    public static ExpressionFunctionDataOptimize buildExpressionFunctionData(TemplateAttribute templateAttibute, AssetValue assetValue)
    {

	ExpressionFunctionDataOptimize expressionFunctionData = new ExpressionFunctionDataOptimize();

	if(assetValue.getExpressionProperties().getExpressionFunction() != null)
	{
	    expressionFunctionData.getExpressionProperties().setExpressionFunction(assetValue.getExpressionProperties().getExpressionFunction());
	}
	else
	{
	    expressionFunctionData.getExpressionProperties().setExpressionFunction(templateAttibute.getExpressionProperties().getExpressionFunction());
	}

	if(assetValue.getExpressionProperties().getRefillingCalculation() != null)
	{
	    expressionFunctionData.getExpressionProperties().setRefillingCalculation(assetValue.getExpressionProperties().getRefillingCalculation());
	}
	else
	{
	    expressionFunctionData.getExpressionProperties().setRefillingCalculation(templateAttibute.getExpressionProperties().getRefillingCalculation());
	}

	expressionFunctionData.getExpressionProperties().setIgnoreNoData(resolveIgnoreNoData(assetValue, templateAttibute));

	if(assetValue.getExpressionProperties().getCronExpression() != null)
	{
	    expressionFunctionData.getExpressionProperties().setCronExpression(assetValue.getExpressionProperties().getCronExpression());
	}
	else
	{
	    expressionFunctionData.getExpressionProperties().setCronExpression(templateAttibute.getExpressionProperties().getCronExpression());
	}

	if(assetValue.getExpressionProperties().getExpressionEvent() != null)
	{
	    expressionFunctionData.getExpressionProperties().setExpressionEvent(assetValue.getExpressionProperties().getExpressionEvent());
	    expressionFunctionData.setOverriddenExpression(true);
	}
	else
	{
	    expressionFunctionData.getExpressionProperties().setExpressionEvent(templateAttibute.getExpressionProperties().getExpressionEvent());
	}

	if(assetValue.getExpressionProperties().getCronTimeZone() != null)
	{
	    expressionFunctionData.getExpressionProperties().setCronTimeZone(assetValue.getExpressionProperties().getCronTimeZone());
	}
	else
	{
	    expressionFunctionData.getExpressionProperties().setCronTimeZone(templateAttibute.getExpressionProperties().getCronTimeZone());
	}

	if(assetValue.getExpressionProperties().getCronDelay() != null)
	{
	    expressionFunctionData.getExpressionProperties().setCronDelay(assetValue.getExpressionProperties().getCronDelay());
	}
	else
	{
	    expressionFunctionData.getExpressionProperties().setCronDelay(templateAttibute.getExpressionProperties().getCronDelay());
	}

	if(assetValue.getExpressionParams().size() == 0)
	{
	    for (ExpressionParam expressionParam : templateAttibute.getExpressionParams())
	    {
		ExpressionParamOptimize expressionParamOptimize = new ExpressionParamOptimize();
		expressionParamOptimize.setId(expressionParam.getCustomId());
		expressionParamOptimize.setName(expressionParam.getName());
		expressionParamOptimize.setType(expressionParam.getType());
		expressionParamOptimize.setRequired(expressionParam.getRequired());
		expressionParamOptimize.setPosition(expressionParam.getPosition());
		expressionParamOptimize.setDefaultValue(expressionParam.getDefaultValueAssetValue().getValue());
		expressionParamOptimize.setGroups(expressionParam.getGroups());

		expressionFunctionData.getExpressionParams().add(expressionParamOptimize);

	    }

	}
	else
	{
	    for (ExpressionParam expressionParam : assetValue.getExpressionParams())
	    {
		ExpressionParamOptimize expressionParamOptimize = new ExpressionParamOptimize();
		expressionParamOptimize.setId(expressionParam.getCustomId());
		expressionParamOptimize.setName(expressionParam.getName());
		expressionParamOptimize.setType(expressionParam.getType());
		expressionParamOptimize.setRequired(expressionParam.getRequired());
		expressionParamOptimize.setPosition(expressionParam.getPosition());
		expressionParamOptimize.setDefaultValue(expressionParam.getDefaultValueAssetValue().getValue());
		expressionParamOptimize.setGroups(expressionParam.getGroups());

		expressionFunctionData.getExpressionParams().add(expressionParamOptimize);

	    }

	}

	return expressionFunctionData;
    }


    public Function getExpressionProperties()
    {

	return expressionProperties;
    }


    public void setExpressionProperties(Function expressionProperties)
    {

	this.expressionProperties = expressionProperties;
    }


    public Set<ExpressionParamOptimize> getExpressionParams()
    {

	return expressionParams;
    }


    public void setExpressionParams(Set<ExpressionParamOptimize> expressionParams)
    {

	this.expressionParams = expressionParams;
    }


    public boolean isNameCalculated()
    {

	return nameCalculated;
    }


    public void setNameCalculated(boolean nameCalculated)
    {

	this.nameCalculated = nameCalculated;
    }


    public Instant getActualMoment()
    {

	return actualMoment;
    }


    public void setActualMoment(Instant actualMoment)
    {

	this.actualMoment = actualMoment;
    }


    public Boolean isOverriddenExpression()
    {

	return overriddenExpression;
    }


    public void setOverriddenExpression(boolean overriddenExpression)
    {

	this.overriddenExpression = overriddenExpression;
    }


    @Override
    public String toString()
    {

	return "[expressionProperties=" + expressionProperties + ", expressionParams=" + expressionParams + ", nameCalculated=" + nameCalculated + ", actualMoment=" + actualMoment + "]";
    }


    public Set<ExpressionParamOptimize> getExpressionParamsAccumulated()
    {

	return expressionParamsAccumulated;
    }


    public void setExpressionParamsAccumulated(Set<ExpressionParamOptimize> expressionParamsAccumulated)
    {

	this.expressionParamsAccumulated = expressionParamsAccumulated;
    }

}
