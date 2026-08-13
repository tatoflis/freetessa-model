package es.cic.tessa.model.utils;


import es.cic.tessa.model.optimize.TemplateAttributeOptimize;
import es.cic.tessa.model.types.LookupType;


public class AssetUtils
{

    private AssetUtils()
    {

    }


    public static boolean isComplexAsset(TemplateAttributeOptimize templateAttribute)
    {

	boolean result = false;

	if(templateAttribute.getTemplateReferenceOptimize() != null)
	{

	    if(templateAttribute.getTemplateReferenceOptimize().getName().equals(LookupType.LOOKUP_DB.getCode()) || templateAttribute.getTemplateReferenceOptimize().getName().equals(LookupType.LOOKUP_REST.getCode()) || templateAttribute.getTemplateReferenceOptimize().getName().equals(LookupType.LOOKUP_PROMETHEUS_REST.getCode()))
	    {
		result = false;
	    }
	    else
	    {
		result = true;
	    }
	}

	return result;
    }
}
