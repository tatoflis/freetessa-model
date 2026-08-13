package es.cic.tessa.excel.organizer.model;


import com.fasterxml.jackson.annotation.JsonValue;


public enum SystemTemplateHiddenTypes
{

 HIDDEN_TEMPLATE("-"),
 SYNTHETIC_ASSETS_EN("Synthetic Assets"),
 SYNTHETIC_ASSETS_ES("Activos sintéticos");

    @JsonValue
    public String code;

    private SystemTemplateHiddenTypes(String code)
    {

	this.code = code;
    }


    public static SystemTemplateHiddenTypes fromString(String code)
    {

	if(code != null)
	{
	    for (SystemTemplateHiddenTypes systemTemplateHiddenType : SystemTemplateHiddenTypes.values())
	    {
		if(code.equalsIgnoreCase(systemTemplateHiddenType.code))
		{
		    return systemTemplateHiddenType;
		}
	    }
	}
	return null;
    }


    public static String fromStringToString(String code)
    {

	if(code != null)
	{
	    for (SystemTemplateHiddenTypes systemTemplateHiddenType : SystemTemplateHiddenTypes.values())
	    {
		if(code.equalsIgnoreCase(systemTemplateHiddenType.code))
		{
		    return systemTemplateHiddenType.getCode();
		}
	    }
	}
	return null;
    }


    public String getCode()
    {

	return code;
    }
}
