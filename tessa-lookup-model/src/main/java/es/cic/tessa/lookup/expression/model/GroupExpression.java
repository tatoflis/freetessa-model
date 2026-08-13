package es.cic.tessa.lookup.expression.model;


import java.io.Serializable;


public class GroupExpression implements Serializable
{

    private static final long serialVersionUID = 1L;

    private String resolvedExpression;
    private String originalExpression;
    private Long lookupId;

    public String getResolvedExpression()
    {

	return resolvedExpression;
    }


    public void setResolvedExpression(String resolvedExpression)
    {

	this.resolvedExpression = resolvedExpression;
    }


    public String getOriginalExpression()
    {

	return originalExpression;
    }


    public void setOriginalExpression(String originalExpression)
    {

	this.originalExpression = originalExpression;
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
