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


import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;


public class ExcelFonts
{

    public static Font getTitleAssetFont(Workbook workbook)
    {

	Font font = workbook.createFont();
	font.setFontHeightInPoints((short) 18);
	font.setColor(IndexedColors.BLACK.getIndex());
	font.setBold(true);

	return font;
    }


    public static Font getHeaderFont(Workbook workbook)
    {

	Font font = workbook.createFont();
	font.setFontHeightInPoints((short) 11);
	font.setItalic(true);
	font.setBold(true);
	font.setColor(IndexedColors.BLACK.getIndex());
	return font;
    }


    public static Font getDataFont(Workbook workbook)
    {

	Font font = workbook.createFont();
	font.setFontHeightInPoints((short) 11);
	font.setColor(IndexedColors.BLACK.getIndex());
	return font;
    }
}
