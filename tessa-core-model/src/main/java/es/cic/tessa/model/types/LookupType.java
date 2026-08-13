package es.cic.tessa.model.types;


import com.fasterxml.jackson.annotation.JsonValue;


public enum LookupType
{

 LOOKUP_DB("LookupDB"),
     LOOKUP_REST("LookupRest"),
     LOOKUP_ID("LookupID"),
     LOOKUP_PROMETHEUS_REST("LookupPrometheusRest"),
     LOOKUP_WEBSERVICE("LookupWebservice");

    @JsonValue
    public String code;

    private LookupType(String code)
    {

	this.code = code;
    }


    public static LookupType fromString(String code)
    {

	if(code != null)
	{
	    for (LookupType lookupType : LookupType.values())
	    {
		if(code.equalsIgnoreCase(lookupType.code))
		{
		    return lookupType;
		}
	    }
	}
	return null;
    }


    public static String fromStringToString(String code)
    {

	if(code != null)
	{
	    for (LookupType lookupType : LookupType.values())
	    {
		if(code.equalsIgnoreCase(lookupType.code))
		{
		    return lookupType.getCode();
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