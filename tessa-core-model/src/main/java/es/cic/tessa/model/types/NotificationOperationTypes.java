package es.cic.tessa.model.types;


import com.fasterxml.jackson.annotation.JsonValue;


public enum NotificationOperationTypes
{

 CREATED("created"),
     UPDATED("updated"),
     DELETED("deleted");

    @JsonValue
    public String code;

    private NotificationOperationTypes(String code)
    {

	this.code = code;
    }


    public static NotificationOperationTypes fromString(String code)
    {

	if(code != null)
	{
	    for (NotificationOperationTypes operationType : NotificationOperationTypes.values())
	    {
		if(code.equalsIgnoreCase(operationType.code))
		{
		    return operationType;
		}
	    }
	}
	return null;
    }


    public static String fromStringToString(String code)
    {

	if(code != null)
	{
	    for (NotificationOperationTypes operationType : NotificationOperationTypes.values())
	    {
		if(code.equalsIgnoreCase(operationType.code))
		{
		    return operationType.getCode();
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