package es.cic.tessa.lookup.utils;


import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import es.cic.tessa.lookup.expression.model.ExpressionConstants;
import es.cic.tessa.lookup.expression.model.LookupParameters;


public class ExpressionProcessUtils
{

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpressionProcessUtils.class);

    private static Collection<String> extractExpressions(String expression)
    {

	Set<String> expressionList = new LinkedHashSet<>();

	Pattern patronExp = Pattern.compile("\\{\\{(?<exp>.*?)\\}\\}");
	Matcher matcheExrExp = patronExp.matcher(expression);

	while (matcheExrExp.find())
	{
	    String exp = matcheExrExp.group("exp");

	    if(exp != null)
	    {
		expressionList.add(ExpressionConstants.PREFIX_GROUPAL_EXPRESSION + exp + ExpressionConstants.SUFIX_GROUPAL_EXPRESSION);
	    }
	}

	Pattern patronCustom = Pattern.compile("(?<=\\p{L})\\((?<cust>\\d+)[,)](?!=)");
	Matcher matcherCustom = patronCustom.matcher(expression);

	while (matcherCustom.find())
	{
	    String custom = matcherCustom.group("cust");

	    if(custom != null && !custom.isEmpty())
	    {
		expressionList.add(ExpressionConstants.PARENTHESIS_OPEN + custom + ExpressionConstants.PARENTHESIS_CLOSE);
	    }
	}

	LOGGER.debug("The complex expression {} result is {}", expression, expressionList);

	return expressionList;
    }


    // TODO review method
    public static Collection<LookupParameters> splitExpression(String expressionToPrepare)
    {

	Collection<LookupParameters> expressionsMap = new LinkedList<>();

	expressionToPrepare = expressionToPrepare.strip();

	try
	{

	    Collection<String> expressionList = extractExpressions(expressionToPrepare);

	    for (String expression : expressionList)
	    {

		if(expression.isEmpty())
		{
		    continue;
		}

		Long lookupId = null;
		Long assetValueId = null;
		LookupParameters lookupParams = new LookupParameters();

		if(expression.contains(ExpressionConstants.PARENTHESIS_OPEN) && !expression.contains(ExpressionConstants.FUNCTION_SEPARATOR))
		{
		    lookupId = ExpressionConstants.WILDCARD_LOOKUP_ID;
		    assetValueId = Long.valueOf(expression.replace(ExpressionConstants.PARENTHESIS_OPEN, "").replace(ExpressionConstants.PARENTHESIS_CLOSE, ""));
		}
		else
		{

		    String expressionNoKeys = expression.substring(2, expression.length() - 2);
		    int assetValueIdEndIndex = expressionNoKeys.indexOf(ExpressionConstants.ASSET_VALUE_ID_SEPARATOR);
		    lookupId = Long.valueOf(expressionNoKeys.substring(0, assetValueIdEndIndex));

		    int expressionStartIndex = expressionNoKeys.indexOf(ExpressionConstants.EXPRESSION_START_DELIMITER) + 2;
		    int expressionEndIndex = 0;

		    String expressionsToResolve = null;

		    if(expressionNoKeys.contains(ExpressionConstants.PREFIX_EXPRESSION_PARAM))
		    {
			expressionEndIndex = expressionNoKeys.indexOf(ExpressionConstants.EXPRESSION_END_DELIMITER) + 1;
			expressionsToResolve = expressionNoKeys.substring(expressionStartIndex, expressionEndIndex);

		    }
		    else
		    {
			expressionEndIndex = expressionNoKeys.indexOf(ExpressionConstants.EXPRESSION_END_DELIMITER);
			expressionsToResolve = expressionNoKeys.substring(expressionStartIndex, expressionEndIndex);

		    }

		    if(!expressionsToResolve.contains(ExpressionConstants.ASSET_VALUE_ID_SEPARATOR))
		    {
			assetValueId = Long.parseLong(expressionsToResolve);
		    }
		    else
		    {

			assetValueId = Long.valueOf(expressionsToResolve.substring(0, expressionsToResolve.indexOf("_")));
			String expressionParemeters = expressionsToResolve.substring(expressionsToResolve.indexOf("_"), expressionsToResolve.length());

			if(!StringUtils.containsOnly(expressionParemeters, "_"))
			{
			    lookupParams.setExpressionParams(expressionParemeters);
			}
		    }
		}

		lookupParams.setAssetValueId(assetValueId);

		lookupParams.setLookupId(lookupId);

		expressionsMap.add(lookupParams);

	    }

	}
	catch (Exception e)
	{
	    LOGGER.error("Error splitting expression {}", expressionToPrepare, e);
	}

	return expressionsMap;
    }

}
