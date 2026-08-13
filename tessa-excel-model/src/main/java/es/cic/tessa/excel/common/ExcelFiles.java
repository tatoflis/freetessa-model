package es.cic.tessa.excel.common;


public enum ExcelFiles
{

 CSV_DELETE_ORGANIZERS_FILE("csv.delete.organizer.filename"),
     CSV_ORGANIZERS_FILE("csv.organizer.filename"),
     CSV_ASSETS_FILE("csv.asset.filename"),
     CSV_DELETE_ASSETS_FILE("csv.delete.asset.filename"),
     CSV_UPDATE_ASSETS_FILE("csv.update.asset.filename"),
     CSV_TEMPLATES_FILE("csv.template.filename"),
     ASSET_ORGANIZERS_FILE("excel.organizer.assets.filename"),
     TEMPLATE_ORGANIZERS_FILE("excel.organizer.templates.filename"),
     ASSET_FILE("excel.asset.filename"),
     TEMPLATE_FILE("excel.template.filename");

    private String excelFile;

    ExcelFiles(String excelFile)
    {

	this.excelFile = excelFile;
    }


    public String getExcelFile()
    {

	return this.excelFile;
    }
}
