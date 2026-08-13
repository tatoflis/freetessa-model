package es.cic.tessa.model.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.core.JacksonException;
import es.cic.tessa.model.AssetValue;
import es.cic.tessa.model.TemplateAttribute;
import es.cic.tessa.model.asset.exceptions.AssetValueException;
import es.cic.tessa.model.support.TessaElement;


public class FunctionUtils
{

    private static final Logger LOGGER = LoggerFactory.getLogger(FunctionUtils.class);

    private FunctionUtils()
    {

    }


    public static String retrieveCypherExpressionProperties(TessaElement tessaElement)
    {

	if(tessaElement instanceof TemplateAttribute templateAttribute && templateAttribute.getExpressionProperties() != null && (templateAttribute.getExpressionProperties().getExpressionFunction() != null || templateAttribute.getExpressionProperties().getExpressionEvent() != null))
	{
	    try
	    {
		return templateAttribute.getExpressionProperties().getCypherExpressionProperties();
	    }
	    catch (JacksonException e)
	    {
		LOGGER.error("Error processing expression properties", e);
		throw new AssetValueException("Error processing expression properties", e);
	    }
	}
	else if(tessaElement instanceof AssetValue assetValue && assetValue.getExpressionProperties() != null && (assetValue.getExpressionProperties().getExpressionFunction() != null || assetValue.getExpressionProperties().getExpressionEvent() != null || assetValue.getExpressionProperties().getCronExpression() != null || assetValue.getExpressionProperties().getCronDelay() != null || assetValue.getExpressionProperties().getCronTimeZone() != null || assetValue.getExpressionProperties().getRefillingCalculation() != null || assetValue.getExpressionProperties().getIgnoreNoData() != null))
	{
	    try
	    {
		return assetValue.getExpressionProperties().getCypherExpressionProperties();
	    }
	    catch (JacksonException e)
	    {
		LOGGER.error("Error processing expression properties", e);
		throw new AssetValueException("Error processing expression properties", e);
	    }
	}
	else
	{

	    return null;
	}

    }
}
