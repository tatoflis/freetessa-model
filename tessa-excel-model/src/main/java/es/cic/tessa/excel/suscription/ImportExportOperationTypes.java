package es.cic.tessa.excel.suscription;


import com.fasterxml.jackson.annotation.JsonValue;


public enum ImportExportOperationTypes
{

 IMPORT_EXCEL_ORGANIZER("excel.organizer.import"),
     IMPORT_EXCEL_TEMPLATES("excel.template.import"),
     IMPORT_EXCEL_ASSETS("excel.asset.import"),
     EXPORT_EXCEL_TEMPLATE_ORGANIZERS("excel.organizer.template.export"),
     EXPORT_EXCEL_ASSET_ORGANIZERS("excel.organizer.asset.export"),
     EXPORT_EXCEL_TEMPLATES("excel.template.export"),
     EXPORT_EXCEL_ASSETS("excel.asset.export"),
     IMPORT_CSV_ORGANIZERS("csv.organizer.import"),
     IMPORT_CSV_TEMPLATES("csv.template.import"),
     IMPORT_CSV_ASSETS("csv.asset.import"),
     EXPORT_CSV_ORGANIZERS("csv.organizer.export"),
     EXPORT_CSV_TEMPLATES("csv.template.export"),
     EXPORT_CSV_ASSETS("csv.asset.export");

    @JsonValue
    public String code;

    private ImportExportOperationTypes(String code)
    {

	this.code = code;
    }


    public static ImportExportOperationTypes fromString(String code)
    {

	if(code != null)
	{
	    for (ImportExportOperationTypes importExportOperationTypes : ImportExportOperationTypes.values())
	    {
		if(code.equalsIgnoreCase(importExportOperationTypes.code))
		{
		    return importExportOperationTypes;
		}
	    }
	}
	return null;
    }


    public static String fromStringToString(String code)
    {

	if(code != null)
	{
	    for (ImportExportOperationTypes importExportOperationTypes : ImportExportOperationTypes.values())
	    {
		if(code.equalsIgnoreCase(importExportOperationTypes.code))
		{
		    return importExportOperationTypes.getCode();
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
