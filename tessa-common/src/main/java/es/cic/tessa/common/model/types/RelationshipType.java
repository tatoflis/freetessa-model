package es.cic.tessa.common.model.types;


public enum RelationshipType
{

 TO("To"),
     FROM("From");

    private String type;

    RelationshipType(String relationshipType)
    {

	this.type = relationshipType;
    }


    public static RelationshipType fromString(String relationshipType)
    {

	if(relationshipType != null)
	{
	    for (RelationshipType relationshipDirection : RelationshipType.values())
	    {
		if(relationshipType.equalsIgnoreCase(relationshipDirection.type))
		{
		    return relationshipDirection;
		}
	    }
	}
	return null;
    }


    public static String fromStringToString(String relationshipType)
    {

	if(relationshipType != null)
	{
	    for (RelationshipType relationshipDirection : RelationshipType.values())
	    {
		if(relationshipType.equalsIgnoreCase(relationshipDirection.type))
		{
		    return relationshipDirection.getRelationshipType();
		}
	    }
	}
	return null;
    }


    public String getRelationshipType()
    {

	return type;
    }
}
