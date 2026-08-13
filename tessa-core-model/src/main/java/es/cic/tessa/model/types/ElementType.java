package es.cic.tessa.model.types;


import com.fasterxml.jackson.annotation.JsonValue;


public enum ElementType
{

 ASSET("Asset"),
     TEMPLATE("Template"),
     ORGANIZER("Organizer"),
     HASHTAG("Hashtag"),
     ASSET_VALUE("AssetValue"),
     TEMPLATE_ATTRIBUTE("TemplateAttribute"),
     TEMPLATE_ATTRIBUTE_COLLECTION_MAPPING("TemplateAttributeCollectionMapping");

    @JsonValue
    public String code;

    private ElementType(String code)
    {

	this.code = code;
    }


    public static ElementType fromString(String code)
    {

	if(code != null)
	{
	    for (ElementType elementType : ElementType.values())
	    {
		if(code.equalsIgnoreCase(elementType.code))
		{
		    return elementType;
		}
	    }
	}
	return null;
    }


    public static String fromStringToString(String code)
    {

	if(code != null)
	{
	    for (ElementType elementType : ElementType.values())
	    {
		if(code.equalsIgnoreCase(elementType.code))
		{
		    return elementType.getCode();
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
