package es.cic.tessa.model.optimize;


import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;


public class TemplateAttributeOptimize extends TessaElementOptimize
{

    private static final long serialVersionUID = 1L;

    private String type;
    private Boolean collection;

    private String calculatedValue;
    private String expressionFunction;
    private String cronExpression;
    private String expressionEvent;
    private Long cronDelay;
    private String cronTimeZone;
    private Boolean ignoreNoData;
    private Boolean refillingCalculation;

    private TemplateOptimize templateOptimize;

    private TemplateOptimize templateReferenceOptimize;

    private Collection<ExpressionParamOptimize> expressionParams = new LinkedHashSet<>();

    public TemplateAttributeOptimize()
    {

    }


    public TemplateAttributeOptimize(String name, String type, Set<String> groups)
    {

	super(name, groups);
	this.type = type;
    }


    public String getCronTimeZone()
    {

	return cronTimeZone;
    }


    public void setCronTimeZone(String cronTimeZone)
    {

	this.cronTimeZone = cronTimeZone;
    }


    public String getType()
    {

	return type;
    }


    public void setType(String type)
    {

	this.type = type;
    }


    public String getExpressionFunction()
    {

	return expressionFunction;
    }


    public void setExpressionFunction(String expressionFunction)
    {

	this.expressionFunction = expressionFunction;
    }


    public Boolean getIgnoreNoData()
    {

	return ignoreNoData;
    }


    public void setIgnoreNoData(Boolean ignoreNoData)
    {

	this.ignoreNoData = ignoreNoData;
    }


    public String getCalculatedValue()
    {

	return calculatedValue;
    }


    public void setCalculatedValue(String calculatedValue)
    {

	this.calculatedValue = calculatedValue;
    }


    public Boolean getCollection()
    {

	return collection;
    }


    public void setCollection(Boolean collection)
    {

	this.collection = collection;
    }


    public Long getCronDelay()
    {

	return cronDelay;
    }


    public void setCronDelay(Long cronDelay)
    {

	this.cronDelay = cronDelay;
    }


    public TemplateOptimize getTemplateOptimize()
    {

	return templateOptimize;
    }


    public void setTemplateOptimize(TemplateOptimize templateOptimize)
    {

	this.templateOptimize = templateOptimize;
    }


    public TemplateOptimize getTemplateReferenceOptimize()
    {

	return templateReferenceOptimize;
    }


    public void setTemplateReferenceOptimize(TemplateOptimize templateReferenceOptimize)
    {

	this.templateReferenceOptimize = templateReferenceOptimize;
    }


    public Boolean getRefillingCalculation()
    {

	return refillingCalculation;
    }


    public void setRefillingCalculation(Boolean refillingCalculation)
    {

	this.refillingCalculation = refillingCalculation;
    }


    public String getCronExpression()
    {

	return cronExpression;
    }


    public void setCronExpression(String cronExpression)
    {

	this.cronExpression = cronExpression;
    }


    public String getExpressionEvent()
    {

	return expressionEvent;
    }


    public void setExpressionEvent(String expressionEvent)
    {

	this.expressionEvent = expressionEvent;
    }


    public Collection<ExpressionParamOptimize> getExpressionParams()
    {

	return expressionParams;
    }


    public void setExpressionParams(Collection<ExpressionParamOptimize> expressionParams)
    {

	this.expressionParams = expressionParams;
    }


    @Override
    public String toString()
    {

	return "[name:" + getName() + ", templateOptimize=" + templateOptimize + "]";
    }

}
