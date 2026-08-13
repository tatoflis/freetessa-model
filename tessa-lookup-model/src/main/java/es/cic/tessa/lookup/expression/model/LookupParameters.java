package es.cic.tessa.lookup.expression.model;


import java.io.Serializable;


public class LookupParameters implements Serializable
{

    private static final long serialVersionUID = 1L;
    private Long assetValueId;
    private String expressionParams;
    private Long lookupId;

    public Long getAssetValueId()
    {

	return assetValueId;
    }


    public void setAssetValueId(Long assetValueId)
    {

	this.assetValueId = assetValueId;
    }


    public String getExpressionParams()
    {

	return expressionParams;
    }


    public void setExpressionParams(String expressionParams)
    {

	this.expressionParams = expressionParams;
    }


    public Long getLookupId()
    {

	return lookupId;
    }


    public void setLookupId(Long lookupId)
    {

	this.lookupId = lookupId;
    }

}
