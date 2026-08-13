package es.cic.tessa.model.optimize;


import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;


public class AssetValueOptimize extends TessaElementOptimize
{

    private static final long serialVersionUID = 1L;

    private Long idBinary;
    private String value;

    private String expressionFunction;
    private String cronExpression;
    private String expressionEvent;
    private Long cronDelay;
    private String cronTimeZone;
    private Boolean ignoreNoData;
    private Boolean refillingCalculation;

    private AssetOptimize assetOptimize;
    private AssetOptimize assetReferenceOptimize;

    private TemplateAttributeOptimize templateAttributeOptimize;

    private Collection<ExpressionParamOptimize> expressionParams = new LinkedHashSet<>();

    public AssetValueOptimize()
    {

    }


    public AssetValueOptimize(String name, Set<String> groups)
    {

	super(name, groups);

    }


    public String getValue()
    {

	return value;
    }


    public void setValue(String value)
    {

	this.value = value;
    }


    public String getExpressionFunction()
    {

	return expressionFunction;
    }


    public void setExpressionFunction(String expressionFunction)
    {

	this.expressionFunction = expressionFunction;
    }


    public AssetOptimize getAssetOptimize()
    {

	return assetOptimize;
    }


    public void setAssetOptimize(AssetOptimize assetOptimize)
    {

	this.assetOptimize = assetOptimize;
    }


    public AssetOptimize getAssetReferenceOptimize()
    {

	return assetReferenceOptimize;
    }


    public void setAssetReferenceOptimize(AssetOptimize assetReferenceOptimize)
    {

	this.assetReferenceOptimize = assetReferenceOptimize;
    }


    public TemplateAttributeOptimize getTemplateAttributeOptimize()
    {

	return templateAttributeOptimize;
    }


    public void setTemplateAttributeOptimize(TemplateAttributeOptimize templateAttributeOptimize)
    {

	this.templateAttributeOptimize = templateAttributeOptimize;
    }


    public Long getIdBinary()
    {

	return idBinary;
    }


    public void setIdBinary(Long idBinary)
    {

	this.idBinary = idBinary;
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


    public Long getCronDelay()
    {

	return cronDelay;
    }


    public void setCronDelay(Long cronDelay)
    {

	this.cronDelay = cronDelay;
    }


    public String getCronTimeZone()
    {

	return cronTimeZone;
    }


    public void setCronTimeZone(String cronTimeZone)
    {

	this.cronTimeZone = cronTimeZone;
    }


    public Boolean getIgnoreNoData()
    {

	return ignoreNoData;
    }


    public void setIgnoreNoData(Boolean ignoreNoData)
    {

	this.ignoreNoData = ignoreNoData;
    }


    public Boolean getRefillingCalculation()
    {

	return refillingCalculation;
    }


    public void setRefillingCalculation(Boolean refillingCalculation)
    {

	this.refillingCalculation = refillingCalculation;
    }


    public Collection<ExpressionParamOptimize> getExpressionParams()
    {

	return expressionParams;
    }


    public void setExpressionParams(Collection<ExpressionParamOptimize> expressionParams)
    {

	this.expressionParams = expressionParams;
    }


    public String toString()
    {

	return "[name=" + name + ", id=" + getId() + ", templateAttribute=" + templateAttributeOptimize + "]";
    }

}
