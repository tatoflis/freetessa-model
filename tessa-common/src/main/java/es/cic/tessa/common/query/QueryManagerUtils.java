package es.cic.tessa.common.query;


import es.cic.tessa.common.model.TessaConstants;


public class QueryManagerUtils
{

    public static String propertyOrderName(String propertyName)
    {

	String propertyOrder;

	switch (propertyName)
	{
	case TessaConstants.NAME:
	{

	    propertyOrder = TessaConstants.NAME_LOWER;
	    break;

	}
	case TessaConstants.VALUE:
	{

	    propertyOrder = TessaConstants.VALUE_LOWER;
	    break;

	}
	case "insertDate":
	{

	    propertyOrder = TessaConstants.INSERT;
	    break;

	}
	case "modificationDate":
	{

	    propertyOrder = TessaConstants.MODIFICATION;
	    break;

	}
	case "finalTemplate":
	{

	    propertyOrder = TessaConstants.FINAL;
	    break;

	}
	case "abstractTemplate":
	{

	    propertyOrder = TessaConstants.ABSTRACT;
	    break;

	}
	default:
	    propertyOrder = propertyName;
	    break;
	}

	return propertyOrder;
    }


    private QueryManagerUtils()
    {

	super();
    }
}
