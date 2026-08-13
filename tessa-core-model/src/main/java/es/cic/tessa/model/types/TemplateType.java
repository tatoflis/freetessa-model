package es.cic.tessa.model.types;


import com.fasterxml.jackson.annotation.JsonValue;


public enum TemplateType
{

 COMPLEX("Complex"),
     SIMPLE("Simple"),
     LOOKUP("Lookup"),
     METADATA("Metadata");

    @JsonValue
    public String code;

    private TemplateType(String code)
    {

	this.code = code;
    }


    public static TemplateType fromString(String code)
    {

	if(code != null)
	{
	    for (TemplateType assetTemplateType : TemplateType.values())
	    {
		if(code.equalsIgnoreCase(assetTemplateType.code))
		{
		    return assetTemplateType;
		}
	    }
	}
	return null;
    }


    public static String fromStringToString(String code)
    {

	if(code != null)
	{
	    for (TemplateType assetTemplateType : TemplateType.values())
	    {
		if(code.equalsIgnoreCase(assetTemplateType.code))
		{
		    return assetTemplateType.getCode();
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
