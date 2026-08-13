package es.cic.tessa.common.model.types;

import es.cic.tessa.common.model.types.OperationType;

public enum OperationType
{

 NONE("None"),
     CREATE("Create"),
     UPDATE("Update"),
     DELETE("Delete");

    public String code;

    private OperationType(String code)
    {

	this.code = code;
    }


    public static OperationType fromString(String code)
    {

	if(code != null)
	{
	    for (OperationType operationType : OperationType.values())
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
	    for (OperationType operationType : OperationType.values())
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
