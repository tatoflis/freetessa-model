package es.cic.tessa.model.types;


import com.fasterxml.jackson.annotation.JsonValue;


public enum TemplateAttributeType
{

 STRING("String"),
     LONGTEXT("Longtext"),
     INTEGER("Integer"),
     DECIMAL("Decimal"),
     DATE("Date"),
     DATETIME("Datetime"),
     BOOLEAN("Boolean"),
     BINARY("Binary"),
     COMPLEX("Complex"),
     COMPLEX_DEPENDS("ComplexDepends"),
     SIMPLE("Simple"),
     FUNCTION("Function");

    @JsonValue
    public String code;

    private TemplateAttributeType(String code)
    {

	this.code = code;
    }


    public static TemplateAttributeType fromString(String code)
    {

	if(code != null)
	{
	    for (TemplateAttributeType assetTemplateAttributeType : TemplateAttributeType.values())
	    {
		if(code.equalsIgnoreCase(assetTemplateAttributeType.code))
		{
		    return assetTemplateAttributeType;
		}
	    }
	}
	return null;
    }


    public static String fromStringToString(String code)
    {

	if(code != null)
	{
	    for (TemplateAttributeType assetTemplateAttributeType : TemplateAttributeType.values())
	    {
		if(code.equalsIgnoreCase(assetTemplateAttributeType.code))
		{
		    return assetTemplateAttributeType.getCode();
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
