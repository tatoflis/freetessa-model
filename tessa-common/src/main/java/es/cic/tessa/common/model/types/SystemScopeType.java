package es.cic.tessa.common.model.types;


public enum SystemScopeType
{

 ALL("All"),
     ONLY_SYSTEM("OnlySystem"),
     EXCLUDE_SYSTEM("ExcludeSystem");

    public String code;

    private SystemScopeType(String code)
    {

	this.code = code;
    }


    public static SystemScopeType fromString(String code)
    {

	if(code != null)
	{
	    for (SystemScopeType systemScopeType : SystemScopeType.values())
	    {
		if(code.equalsIgnoreCase(systemScopeType.code))
		{
		    return systemScopeType;
		}
	    }
	}
	return null;
    }


    public static String fromStringToString(String code)
    {

	if(code != null)
	{
	    for (SystemScopeType systemScopeType : SystemScopeType.values())
	    {
		if(code.equalsIgnoreCase(systemScopeType.code))
		{
		    return systemScopeType.getCode();
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
