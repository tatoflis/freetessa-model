package es.cic.tessa.lookup.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import es.cic.tessa.common.utils.CronUtils;
import es.cic.tessa.lookup.exceptions.LookupException;
import es.cic.tessa.lookup.expression.model.ExpressionConstants;
import es.cic.tessa.lookup.expression.model.ExpressionFunctionDataOptimize;
import es.cic.tessa.model.optimize.ExpressionParamOptimize;


public class ExpressionParameterUtils
{

    private final static Logger LOGGER = LoggerFactory.getLogger(ExpressionParameterUtils.class);

    public static String changeExpressionFromExpressionParams(ExpressionFunctionDataOptimize expressionFunctionData, boolean isAggregationCalc)
    {

	String resultExpression = expressionFunctionData.getExpressionProperties().getExpressionFunction();

	int indexOf;
	while ((indexOf = resultExpression.indexOf(ExpressionConstants.PREFIX_EXPRESSION_PARAM)) != -1)
	{
	    int endIndex = resultExpression.indexOf(ExpressionConstants.SUFIX_EXPRESSION_PARAM, indexOf);
	    if(endIndex == -1)
	    {
		throw new IllegalArgumentException("Expresión mal formada: falta el sufijo del parámetro.");
	    }

	    String tokenExpression = resultExpression.substring(indexOf, endIndex + 1);
	    String paramName = tokenExpression.substring(ExpressionConstants.PREFIX_EXPRESSION_PARAM.length(), tokenExpression.length() - ExpressionConstants.SUFIX_EXPRESSION_PARAM.length());

	    ExpressionParamOptimize param = expressionFunctionData.getExpressionParams().stream().filter(p -> p.getName().equals(paramName)).findFirst().orElse(null);

	    if(param != null)
	    {
		String replacementValue = getReplacementValue(param, expressionFunctionData.getExpressionProperties().getExpressionFunction(), tokenExpression);
		resultExpression = resultExpression.replace(tokenExpression, replacementValue);

		if(!isAggregationCalc)
		{
		    expressionFunctionData.getExpressionParams().remove(param);
		}
		else
		{
		    expressionFunctionData.getExpressionParamsAccumulated().add(param);
		}
	    }
	    else
	    {
		resultExpression = resultExpression.replace(tokenExpression, "");
	    }
	}

	if(isAggregationCalc)
	{
	    LOGGER.debug("No removed parameters {} in expression function {}", expressionFunctionData.getExpressionParams(), expressionFunctionData.getExpressionProperties().getExpressionFunction());
	}

	return resultExpression;
    }


    public static void cleanAccumulatedParams(ExpressionFunctionDataOptimize expressionFunctionData)
    {

	for (ExpressionParamOptimize expressionParamOptimize : expressionFunctionData.getExpressionParamsAccumulated())
	{
	    expressionFunctionData.getExpressionParams().remove(expressionParamOptimize);
	}
    }


    private static String getReplacementValue(ExpressionParamOptimize param, String originalExpression, String tokenExpression)
    {

	if(param.getDefaultValue() != null)
	{
	    String defaultValue = param.getDefaultValue();
	    if(!defaultValue.contains("now") && !CronUtils.isCronFormatValid(defaultValue))
	    {
		return defaultValue;
	    }
	    else
	    {
		return ExpressionConstants.FUNCTION_SEPARATOR + defaultValue + ExpressionConstants.FUNCTION_SEPARATOR;
	    }
	}
	else if(param.isRequired())
	{
	    LOGGER.error("Default value for {} variable missing in expression {}", param.getName(), originalExpression);
	    throw new LookupException("Default value for " + param.getName() + " variable missing in expression " + originalExpression);
	}
	return "";
    }

}
