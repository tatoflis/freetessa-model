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


import java.io.Serializable;


public class CellValue implements Serializable
{

    private static final long serialVersionUID = 1L;

    private String sheetName;
    private String cellValue;
    private String refCell;

    public String getSheetName()
    {

	return sheetName;
    }


    public void setSheetName(String sheetName)
    {

	this.sheetName = sheetName;
    }


    public String getCellValue()
    {

	return cellValue;
    }


    public void setCellValue(String cellValue)
    {

	this.cellValue = cellValue;
    }


    public String getRefCell()
    {

	return refCell;
    }


    public void setRefCell(String refCell)
    {

	this.refCell = refCell;
    }


    public static long getSerialversionuid()
    {

	return serialVersionUID;
    }


    @Override
    public String toString()
    {

	return "CellValue [sheetName=" + sheetName + ", cellValue=" + cellValue + "]";
    }

}
