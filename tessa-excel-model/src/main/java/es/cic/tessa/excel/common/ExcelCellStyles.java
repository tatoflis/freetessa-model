/*
 * Copyright [2019] "CIC Consulting"
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 *
 */
package es.cic.tessa.excel.common;


import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;


public class ExcelCellStyles
{

    public static CellStyle getStyleTitleSheet(Workbook workbook)
    {

	CellStyle titleAssetStyle = workbook.createCellStyle();
	titleAssetStyle.setFont(ExcelFonts.getTitleAssetFont(workbook));
	titleAssetStyle.setAlignment(HorizontalAlignment.CENTER);
	//
	titleAssetStyle.setFillBackgroundColor(IndexedColors.SKY_BLUE.getIndex());
	byte[] azul =
	{ (byte) 142, (byte) 169, (byte) 219 };
	XSSFCellStyle blueColorStyle = (XSSFCellStyle) titleAssetStyle;
	blueColorStyle.setFillForegroundColor(new XSSFColor(azul, null));
	titleAssetStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	titleAssetStyle.setBorderBottom(BorderStyle.THICK);
	titleAssetStyle.setBorderTop(BorderStyle.THICK);
	titleAssetStyle.setBorderLeft(BorderStyle.THICK);
	titleAssetStyle.setBorderRight(BorderStyle.THICK);

	return titleAssetStyle;
    }


    public static CellStyle getCellStyleHeader(Workbook workbook)
    {

	CellStyle cellStyle = workbook.createCellStyle();

	cellStyle.setFont(ExcelFonts.getHeaderFont(workbook));

	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	cellStyle.setBorderBottom(BorderStyle.THICK);
	cellStyle.setBorderTop(BorderStyle.THICK);
	cellStyle.setBorderLeft(BorderStyle.THICK);
	cellStyle.setBorderRight(BorderStyle.THICK);

	cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	return cellStyle;

    }


    public static CellStyle getCellStyleHeaderData(Workbook workbook)
    {

	CellStyle cellStyle = workbook.createCellStyle();

	cellStyle.setFont(ExcelFonts.getHeaderFont(workbook));

	cellStyle.setAlignment(HorizontalAlignment.CENTER);
	cellStyle.setBorderBottom(BorderStyle.THICK);
	cellStyle.setBorderTop(BorderStyle.THICK);
	cellStyle.setBorderLeft(BorderStyle.THICK);
	cellStyle.setBorderRight(BorderStyle.THICK);

	return cellStyle;

    }


    public static CellStyle getCellStyleData(Workbook workbook)
    {

	CellStyle cellStyle = workbook.createCellStyle();
	cellStyle.setFont(ExcelFonts.getDataFont(workbook));
	cellStyle.setAlignment(HorizontalAlignment.CENTER);

	return cellStyle;

    }


    public static void getCellStyleFunctionHeader(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol)
    {

	CellRangeAddress cellRangeAddress = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);

	RegionUtil.setBorderTop(BorderStyle.THICK, cellRangeAddress, sheet);
	RegionUtil.setBorderBottom(BorderStyle.THICK, cellRangeAddress, sheet);
	RegionUtil.setBorderLeft(BorderStyle.THICK, cellRangeAddress, sheet);
	RegionUtil.setBorderRight(BorderStyle.THICK, cellRangeAddress, sheet);

	sheet.addMergedRegion(cellRangeAddress);

    }


    private ExcelCellStyles()
    {

	super();
    }
}
