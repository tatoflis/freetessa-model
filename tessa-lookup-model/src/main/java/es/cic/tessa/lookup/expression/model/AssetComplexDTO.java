package es.cic.tessa.lookup.expression.model;


import java.io.Serializable;
import es.cic.tessa.model.optimize.AssetOptimize;
import es.cic.tessa.model.optimize.AssetValueOptimize;
import es.cic.tessa.model.optimize.TemplateAttributeOptimize;


public class AssetComplexDTO implements Serializable
{

    private static final long serialVersionUID = 1L;
    private boolean complex;
    private ExpressionFunctionDataOptimize expressionFunctionData;
    private AssetValueOptimize assetValue;
    private AssetOptimize asset;
    private TemplateAttributeOptimize templateAttribute;

    public boolean isComplex()
    {

	return complex;
    }


    public void setComplex(boolean complex)
    {

	this.complex = complex;
    }


    public AssetValueOptimize getAssetValue()
    {

	return assetValue;
    }


    public void setAssetValue(AssetValueOptimize assetValue)
    {

	this.assetValue = assetValue;
    }


    public AssetOptimize getAsset()
    {

	return asset;
    }


    public void setAsset(AssetOptimize asset)
    {

	this.asset = asset;
    }


    public TemplateAttributeOptimize getTemplateAttribute()
    {

	return templateAttribute;
    }


    public void setTemplateAttribute(TemplateAttributeOptimize templateAttribute)
    {

	this.templateAttribute = templateAttribute;
    }


    public ExpressionFunctionDataOptimize getExpressionFunctionDataOptimize()
    {

	return expressionFunctionData;
    }


    public void setExpressionFunctionDataOptimize(ExpressionFunctionDataOptimize expressionFunctionData)
    {

	this.expressionFunctionData = expressionFunctionData;
    }

}
