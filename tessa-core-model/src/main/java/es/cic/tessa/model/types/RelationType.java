package es.cic.tessa.model.types;


import com.fasterxml.jackson.annotation.JsonValue;


public enum RelationType
{

 ASSOCIATION("Association"),
     AGGREGATION("Aggregation"),
     COMPOSITION("Composition");

    @JsonValue
    public String code;

    private RelationType(String code)
    {

	this.code = code;
    }


    public static RelationType fromString(String code)
    {

	if(code != null)
	{
	    for (RelationType relationType : RelationType.values())
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
	    for (RelationType relationType : RelationType.values())
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
