package es.cic.tessa.lookup.expression.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import es.cic.tessa.model.ExpressionParam;


public class ExpressionSuscription implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Long assetValueId;
    private Long assetId;
    private List<ExpressionParam> expressionParams = new ArrayList<ExpressionParam>();
    private Set<String> groups = new HashSet<>();
    private String topicSuscription;

    public Long getAssetValueId()
    {

	return assetValueId;
    }


    public void setAssetValueId(Long assetValueId)
    {

	this.assetValueId = assetValueId;
    }


    public Long getAssetId()
    {

	return assetId;
    }


    public void setAssetId(Long assetId)
    {

	this.assetId = assetId;
    }


    public List<ExpressionParam> getExpressionParams()
    {

	return expressionParams;
    }


    public void setExpressionParams(List<ExpressionParam> expressionParams)
    {

	this.expressionParams = expressionParams;
    }


    public Set<String> getGroups()
    {

	return groups;
    }


    public void setGroups(Set<String> groups)
    {

	this.groups = groups;
    }


    public String getTopicSuscription()
    {

	return topicSuscription;
    }


    public void setTopicSuscription(String topicSuscription)
    {

	this.topicSuscription = topicSuscription;
    }


    @Override
    public String toString()
    {

	return "ExpressionSuscription [assetValueId=" + assetValueId + ", assetId=" + assetId + ", expressionParams=" + expressionParams + ", groups=" + groups + ", topicSuscription=" + topicSuscription + "]";
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(assetValueId, groups, assetId, topicSuscription);
    }


    @Override
    public boolean equals(Object obj)
    {

	if(this == obj)
	    return true;
	if(obj == null)
	    return false;
	if(getClass() != obj.getClass())
	    return false;
	ExpressionSuscription other = (ExpressionSuscription) obj;
	return Objects.equals(assetValueId, other.assetValueId) && Objects.equals(groups, other.groups) && Objects.equals(assetId, other.assetId) && Objects.equals(topicSuscription, other.topicSuscription);
    }

}
