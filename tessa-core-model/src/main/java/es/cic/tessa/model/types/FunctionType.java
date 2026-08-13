package es.cic.tessa.model.types;


import com.fasterxml.jackson.annotation.JsonValue;


public enum FunctionType
{

 STRING("String"),
     INTEGER("Integer"),
     DECIMAL("Decimal"),
     BOOLEAN("Boolean"),
     DATE("Date"),
     DATETIME("Datetime"),
     FUNCTION("Function"),
     CRON("Cron");

    @JsonValue
    public String code;

    private FunctionType(String code)
    {

	this.code = code;
    }


    public static FunctionType fromString(String code)
    {

	if(code != null)
	{
	    for (FunctionType functionType : FunctionType.values())
	    {
		if(code.equalsIgnoreCase(functionType.code))
		{
		    return functionType;
		}
	    }
	}
	return null;
    }


    public static String fromStringToString(String code)
    {

	if(code != null)
	{
	    for (FunctionType functionType : FunctionType.values())
	    {
		if(code.equalsIgnoreCase(functionType.code))
		{
		    return functionType.getCode();
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
