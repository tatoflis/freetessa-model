package es.cic.tessa.model.types;


import com.fasterxml.jackson.annotation.JsonValue;


public enum ContentType
{

 PDF("PDF"),
     PNG("PNG");

    @JsonValue
    public String code;

    private ContentType(String code)
    {

	this.code = code;
    }


    public static ContentType fromString(String code)
    {

	if(code != null)
	{
	    for (ContentType relationType : ContentType.values())
	    {
		if(code.equalsIgnoreCase(relationType.code))
		{
		    return relationType;
		}
	    }
	}
	return null;
    }


    public static String fromStringToString(String code)
    {

	if(code != null)
	{
	    for (ContentType relationType : ContentType.values())
	    {
		if(code.equalsIgnoreCase(relationType.code))
		{
		    return relationType.getCode();
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
