package es.cic.tessa.excel.asset.model;


import java.util.HashMap;
import java.util.Map;


public class AssetCells
{

    private Map<Integer, String> valueCells = new HashMap<Integer, String>();

    public Map<Integer, String> getValueCells()
    {

	return valueCells;
    }


    public void setValueCells(Map<Integer, String> valueCells)
    {

	this.valueCells = valueCells;
    }


    @Override
    public String toString()
    {

	return "[valueCells=" + valueCells + "]";
    }

}
