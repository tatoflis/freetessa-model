package es.cic.tessa.lookup.expression.model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class ExpressionPayload implements Serializable
{

    private static final long serialVersionUID = 1L;
    private Long templateAttributeId;
    private Long assetValueId;
    private byte[] expressionFunctionJsonCompress;
    private String expressionFunctionJson;
    private Set<String> groups = new HashSet<>();

    public Long getTemplateAttributeId()
    {

	return templateAttributeId;
    }


    public void setTemplateAttributeId(Long templateAttributeId)
    {

	this.templateAttributeId = templateAttributeId;
    }


    public Long getAssetValueId()
    {

	return assetValueId;
    }


    public void setAssetValueId(Long assetValueId)
    {

	this.assetValueId = assetValueId;
    }


    public byte[] getExpressionFunctionJsonCompress()
    {

	return expressionFunctionJsonCompress;
    }


    public void setExpressionFunctionJsonCompress(byte[] expressionFunctionJsonCompress)
    {

	this.expressionFunctionJsonCompress = expressionFunctionJsonCompress;
    }


    public String getExpressionFunctionJson()
    {

	return expressionFunctionJson;
    }


    public void setExpressionFunctionJson(String expressionFunctionJson)
    {

	this.expressionFunctionJson = expressionFunctionJson;
    }


    public Set<String> getGroups()
    {

	return groups;
    }


    public void setGroups(Set<String> groups)
    {

	this.groups = groups;
    }

}
