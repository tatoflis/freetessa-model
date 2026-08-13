package es.cic.tessa.model.types;


import com.fasterxml.jackson.annotation.JsonValue;


public enum OrganizerType
{

 ASSET_ORGANIZER("Asset Organizer"),
     TEMPLATE_ORGANIZER("Template Organizer");

    @JsonValue
    public String code;

    private OrganizerType(String code)
    {

	this.code = code;
    }


    public static OrganizerType fromString(String code)
    {

	if(code != null)
	{
	    for (OrganizerType organizerType : OrganizerType.values())
	    {
		if(code.equalsIgnoreCase(organizerType.code))
		{
		    return organizerType;
		}
	    }
	}
	return null;
    }


    public static String fromStringToString(String code)
    {

	if(code != null)
	{
	    for (OrganizerType organizerType : OrganizerType.values())
	    {
		if(code.equalsIgnoreCase(organizerType.code))
		{
		    return organizerType.getCode();
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
