package es.cic.tessa.model.types;


import com.fasterxml.jackson.annotation.JsonValue;


public enum ReferenceType
{

 COMPLEX("Complex"),
     SIMPLE("Simple");

    @JsonValue
    public String code;

    private ReferenceType(String code)
    {

	this.code = code;
    }


    public static ReferenceType fromString(String code)
    {

	if(code != null)
	{
	    for (ReferenceType TemplateReferenceType : ReferenceType.values())
	    {
		if(code.equalsIgnoreCase(TemplateReferenceType.code))
		{
		    return TemplateReferenceType;
		}
	    }
	}
	return null;
    }


    public static String fromStringToString(String code)
    {

	if(code != null)
	{
	    for (ReferenceType templateReferenceType : ReferenceType.values())
	    {
		if(code.equalsIgnoreCase(templateReferenceType.code))
		{
		    return templateReferenceType.getCode();
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
