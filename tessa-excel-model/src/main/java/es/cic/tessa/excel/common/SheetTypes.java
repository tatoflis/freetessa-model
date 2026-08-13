package es.cic.tessa.excel.common;


public enum SheetTypes
{

 TEMPLATE("Template"),
     ASSET("Asset"),
     TEMPLATE_ORGANIZER("Template Organizer"),
     ASSET_ORGANIZER("Asset Organizer"),
     HIDDEN_SHEET("-");

    private String code;

    private SheetTypes(final String code)
    {

	this.code = code;
    }


    public String getCode()
    {

	return this.code;
    }
}