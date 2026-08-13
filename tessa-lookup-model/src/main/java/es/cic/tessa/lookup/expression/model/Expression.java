package es.cic.tessa.lookup.expression.model;


import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import tools.jackson.databind.annotation.JsonSerialize;
import es.cic.tessa.lookup.model.AbstractLookupData;


@JsonInclude(Include.NON_NULL)
@JsonSerialize
public class Expression implements Serializable
{

    private static final long serialVersionUID = 1L;
    @JsonProperty("ET")
    private ExpressionType expressionType;

    @JsonProperty("EL")
    private Map<Long, AbstractLookupData> assetsLookupData = new LinkedHashMap<>();

    @JsonProperty("E_IND")
    private Boolean ignoreNoData;

    @JsonProperty("EP")
    private String propertyName;

    @JsonProperty("ER")
    private String expressionToResolve;

    @JsonProperty("CC")
    private String aggregateExpression;

    @JsonProperty("ECE")
    private Map<String, Long> configExpression = new LinkedHashMap<String, Long>();

    public Expression()
    {

    }


    public Expression(ExpressionType expressionType, String expressionToResolve, Map<String, Long> configExpression)
    {

	this.expressionType = expressionType;
	this.expressionToResolve = expressionToResolve;
	this.configExpression = configExpression;
    }


    public String getExpressionToResolve()
    {

	return expressionToResolve;
    }


    public void setExpressionToResolve(String expressionToResolve)
    {

	this.expressionToResolve = expressionToResolve;
    }


    public Map<String, Long> getConfigExpression()
    {

	return configExpression;
    }


    public void setConfigExpression(Map<String, Long> configExpression)
    {

	this.configExpression = configExpression;
    }


    public ExpressionType getExpressionType()
    {

	return expressionType;
    }


    public void setExpressionType(ExpressionType expressionType)
    {

	this.expressionType = expressionType;
    }


    public String getPropertyName()
    {

	return propertyName;
    }


    public void setPropertyName(String propertyName)
    {

	this.propertyName = propertyName;
    }


    public Map<Long, AbstractLookupData> getAssetsLookupData()
    {

	return assetsLookupData;
    }


    public void setAssetsLookupData(Map<Long, AbstractLookupData> assetsLookupData)
    {

	this.assetsLookupData = assetsLookupData;
    }


    public Boolean getIgnoreNoData()
    {

	return ignoreNoData;
    }


    public void setIgnoreNoData(Boolean ignoreNoData)
    {

	this.ignoreNoData = ignoreNoData;
    }


    @Override
    public String toString()
    {

	return "Expression [expressionType=" + expressionType + ", assetsLookupData=" + assetsLookupData + ", propertyName=" + propertyName + ", expressionToResolve=" + expressionToResolve + ", configExpression=" + configExpression + "]";
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(assetsLookupData, configExpression, expressionToResolve, expressionType, ignoreNoData, propertyName);
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
	Expression other = (Expression) obj;
	return Objects.equals(assetsLookupData, other.assetsLookupData) && Objects.equals(configExpression, other.configExpression) && Objects.equals(expressionToResolve, other.expressionToResolve) && expressionType == other.expressionType && Objects.equals(ignoreNoData, other.ignoreNoData) && Objects.equals(propertyName, other.propertyName);
    }


    public String getAggregateExpression()
    {

	return aggregateExpression;
    }


    public void setAggregateExpression(String aggregateExpression)
    {

	this.aggregateExpression = aggregateExpression;
    }

}
