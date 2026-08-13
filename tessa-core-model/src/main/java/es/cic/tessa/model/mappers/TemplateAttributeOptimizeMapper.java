package es.cic.tessa.model.mappers;


import java.util.ArrayList;
import java.util.Collection;
import es.cic.tessa.model.ExpressionParam;
import es.cic.tessa.model.Function;
import es.cic.tessa.model.TemplateAttribute;
import es.cic.tessa.model.optimize.ExpressionParamOptimize;
import es.cic.tessa.model.optimize.TemplateAttributeOptimize;
import es.cic.tessa.model.optimize.TemplateOptimize;


public final class TemplateAttributeOptimizeMapper
{

    private TemplateAttributeOptimizeMapper()
    {

    }


    public static TemplateAttributeOptimize toOptimize(TemplateAttribute templateAttribute)
    {

	if(templateAttribute == null)
	{
	    return null;
	}

	TemplateAttributeOptimize optimize = new TemplateAttributeOptimize();
	optimize.setId(templateAttribute.getCustomId());
	optimize.setName(templateAttribute.getName());
	optimize.setGroups(templateAttribute.getGroups());
	optimize.setType(templateAttribute.getType());
	optimize.setCollection(templateAttribute.getCollection());
	optimize.setCalculatedValue(templateAttribute.getCalculatedValue());

	mapExpressionProperties(templateAttribute.getExpressionProperties(), optimize);
	mapTemplate(templateAttribute, optimize);
	mapTemplateReference(templateAttribute, optimize);
	mapExpressionParams(templateAttribute, optimize);

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


    private static void mapTemplate(TemplateAttribute templateAttribute, TemplateAttributeOptimize optimize)
    {

	if(templateAttribute.getTemplate() == null)
	{
	    return;
	}

	TemplateOptimize tOptimize = new TemplateOptimize();
	tOptimize.setId(templateAttribute.getTemplate().getCustomId());
	tOptimize.setName(templateAttribute.getTemplate().getName());
	tOptimize.setGroups(templateAttribute.getTemplate().getGroups());
	optimize.setTemplateOptimize(tOptimize);
    }


    private static void mapTemplateReference(TemplateAttribute templateAttribute, TemplateAttributeOptimize optimize)
    {

	if(templateAttribute.getTemplateReference() == null || templateAttribute.getTemplateReference().getTemplate() == null)
	{
	    return;
	}

	TemplateOptimize refOptimize = new TemplateOptimize();
	refOptimize.setId(templateAttribute.getTemplateReference().getTemplate().getCustomId());
	refOptimize.setName(templateAttribute.getTemplateReference().getTemplate().getName());
	refOptimize.setGroups(templateAttribute.getTemplateReference().getTemplate().getGroups());
	optimize.setTemplateReferenceOptimize(refOptimize);
    }


    private static void mapExpressionParams(TemplateAttribute templateAttribute, TemplateAttributeOptimize optimize)
    {

	if(templateAttribute.getExpressionParams() == null || templateAttribute.getExpressionParams().isEmpty())
	{
	    return;
	}

	Collection<ExpressionParamOptimize> params = new ArrayList<>();

	for (ExpressionParam param : templateAttribute.getExpressionParams())
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
