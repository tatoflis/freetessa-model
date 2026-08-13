package es.cic.tessa.common.model.types;


public enum HierarchyType
{

 UP("Up"),
     DOWN("Down"),
     BOTH("Both"),
     NONE("None");

    public String code;

    private HierarchyType(String code)
    {

	this.code = code;
    }


    public static HierarchyType fromString(String code)
    {

	if(code != null)
	{
	    for (HierarchyType hierarchyType : HierarchyType.values())
	    {
		if(code.equalsIgnoreCase(hierarchyType.code))
		{
		    return hierarchyType;
		}
	    }
	}
	return null;
    }


    public static String fromStringToString(String code)
    {

	if(code != null)
	{
	    for (HierarchyType hierarchyType : HierarchyType.values())
	    {
		if(code.equalsIgnoreCase(hierarchyType.code))
		{
		    return hierarchyType.getCode();
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
