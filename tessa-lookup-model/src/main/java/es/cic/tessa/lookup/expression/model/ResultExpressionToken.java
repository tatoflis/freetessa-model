package es.cic.tessa.lookup.expression.model;


public class ResultExpressionToken
{

    private String result;

    private String expression;

    private String aggregateExpression;

    public ResultExpressionToken(String expression, String result, String aggregateExpression)
    {

	this.result = result;
	this.expression = expression;
	this.aggregateExpression = aggregateExpression;
    }


    public String getResult()
    {

	return result;
    }


    public void setResult(String result)
    {

	this.result = result;
    }


    public String getExpression()
    {

	return expression;
    }


    public void setExpression(String expression)
    {

	this.expression = expression;
    }


    public String getAggregateExpression()
    {

	return aggregateExpression;
    }


    public void setAggregateExpression(String aggregateExpression)
    {

	this.aggregateExpression = aggregateExpression;
    }


    @Override
    public String toString()
    {

	return "ResultExpressionToken [result=" + result + ", expression=" + expression + ", aggregateExpression=" + aggregateExpression + "]";
    }

}
