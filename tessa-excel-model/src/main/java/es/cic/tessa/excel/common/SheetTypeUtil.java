package es.cic.tessa.excel.common;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import es.cic.tessa.common.exceptions.TessaException;


public class SheetTypeUtil
{

    private static final Logger LOGGER = LoggerFactory.getLogger(SheetTypeUtil.class);

    public static final boolean isSheetType(final Sheet sheet, final SheetTypes type)
    {

	boolean isType = false;

	final Row currentRow = sheet.getRow(0);
	final Cell currentCell = currentRow.getCell(0);

	if(type.getCode().equalsIgnoreCase(currentCell.getStringCellValue()))
	{
	    isType = true;
	}
	else
	{
	    isType = false;
	}

	return isType;
    }


    public static String[] resolveLabels(final String labels)
    {

	if(labels == null || labels.isEmpty())
	{
	    return null;
	}
	final StringTokenizer tokenizer = new StringTokenizer(labels, ",");
	final List<String> labelsAssetTemplate = new ArrayList<String>();
	while (tokenizer.hasMoreTokens())
	{
	    labelsAssetTemplate.add(StringUtils.stripStart(tokenizer.nextToken(), " "));
	}
	final String[] arrayLabels = new String[labelsAssetTemplate.size()];
	for (int i = 0; i < labelsAssetTemplate.size(); ++i)
	{
	    arrayLabels[i] = labelsAssetTemplate.get(i);
	}
	return arrayLabels;
    }


    public static String convertToStringLabels(final Set<String> labels)
    {

	if(labels == null || labels.isEmpty())
	{
	    return "";
	}
	final StringBuilder labelsGenerated = new StringBuilder();
	for (final String label : labels)
	{
	    labelsGenerated.append(label);
	    labelsGenerated.append(", ");
	}
	return labelsGenerated.subSequence(0, labelsGenerated.length() - 2).toString().trim();
    }


    public static String checkMappingHeader(final String nameAttributeHeader)
    {

	String nameHeader = null;
	if(nameAttributeHeader.contains("(Alias)"))
	{
	    nameHeader = nameAttributeHeader.subSequence(0, nameAttributeHeader.length() - 8).toString();
	}
	else if(nameAttributeHeader.contains("(Values)"))
	{
	    nameHeader = nameAttributeHeader.subSequence(0, nameAttributeHeader.length() - 9).toString();
	}
	else
	{
	    nameHeader = nameAttributeHeader;
	}
	return nameHeader;
    }


    public static Sheet getSheet(final Workbook workbook, final String sheetAssetName)
    {

	final Sheet sheet = workbook.getSheet(sheetAssetName);
	if(sheet == null)
	{
	    SheetTypeUtil.LOGGER.error("No sheet found with name {}", (Object) sheetAssetName);
	    throw new TessaException("No sheet found with name " + sheetAssetName);
	}
	return sheet;
    }


    public static boolean isMergedRegion(final Sheet sheet, final int row, final int column)
    {

	for (int sheetMergeCount = sheet.getNumMergedRegions(), i = 0; i < sheetMergeCount; ++i)
	{
	    final CellRangeAddress range = sheet.getMergedRegion(i);
	    final int firstColumn = range.getFirstColumn();
	    final int lastColumn = range.getLastColumn();
	    final int firstRow = range.getFirstRow();
	    final int lastRow = range.getLastRow();
	    if(row >= firstRow && row < lastRow && column >= firstColumn && column <= lastColumn)
	    {
		return true;
	    }
	}
	return false;
    }


    public static String getCellByRowAndColumn(final Sheet sheet, final int rowIndex, final int colIndex)
    {

	try
	{
	    final Row row = sheet.getRow(rowIndex);
	    final Cell assetTemplateCell = row.getCell(colIndex);

	    if(assetTemplateCell != null)
	    {
		switch (assetTemplateCell.getCellType())
		{
		case STRING:
		{

		    if(!StringUtils.isAllBlank(assetTemplateCell.getStringCellValue()))
		    {

			return assetTemplateCell.getStringCellValue();
		    }

		    break;

		}
		case BOOLEAN:
		{
		    final boolean booleanCellValue = assetTemplateCell.getBooleanCellValue();
		    return Boolean.toString(booleanCellValue);

		}
		case FORMULA:
		{
		    if(!StringUtils.isAllBlank(assetTemplateCell.getStringCellValue()))
		    {
			return assetTemplateCell.getStringCellValue();
		    }

		    break;
		}

		case NUMERIC:
		{
		    if(DateUtil.isCellDateFormatted(assetTemplateCell))
		    {
			Date dateCellValue = assetTemplateCell.getDateCellValue();

			return String.valueOf(dateCellValue.getTime());

		    }
		    else
		    {
			final double numericDoubleCellValue = (double) assetTemplateCell.getNumericCellValue();
			int numericIntCellValue = 0;
			if(numericDoubleCellValue % 1 == 0)
			{
			    numericIntCellValue = (int) assetTemplateCell.getNumericCellValue();
			    return String.valueOf(numericIntCellValue);
			}
			else
			{
			    return String.valueOf(numericDoubleCellValue);
			}

		    }
		}

		default:
		{
		    throw new TessaException("Cell format not recognized. " + assetTemplateCell.getCellType());

		}
		}
	    }

	}
	catch (Exception e)
	{
	    SheetTypeUtil.LOGGER.trace("No format allowed in cell row {} col {} found data in cell !!", (Object) rowIndex, (Object) colIndex);
	}

	return null;
    }


    public static CellValue getCellValue(final String content)
    {

	StringTokenizer st = null;
	final CellValue cellValue = new CellValue();
	if(content.contains("'!"))
	{
	    st = new StringTokenizer(content.substring(1), "'!");
	    cellValue.setSheetName(st.nextToken());
	    cellValue.setRefCell(st.nextToken());
	}
	else
	{
	    cellValue.setCellValue(content);
	}
	return cellValue;
    }


    public static int getMergerCellRegionRow(final Sheet sheet, final int cellRow, final int cellCol)
    {

	int retVal = 0;
	for (int sheetMergerCount = sheet.getNumMergedRegions(), i = 0; i < sheetMergerCount; ++i)
	{
	    final CellRangeAddress cra = sheet.getMergedRegion(i);
	    final int firstRow = cra.getFirstRow();
	    final int firstCol = cra.getFirstColumn();
	    final int lastRow = cra.getLastRow();
	    final int lastCol = cra.getLastColumn();
	    if(cellRow >= firstRow && cellRow <= lastRow && cellCol >= firstCol && cellCol <= lastCol)
	    {
		retVal = lastRow - firstRow + 1;
		break;
	    }
	}
	return retVal;
    }


    public static String getAttributeName(String columnValue)
    {

	Pattern pattern = Pattern.compile("\\([^()]*\\)");

	Matcher matcher = pattern.matcher(columnValue);

	return matcher.replaceAll("").trim();
    }


    private SheetTypeUtil()
    {

    }
}
