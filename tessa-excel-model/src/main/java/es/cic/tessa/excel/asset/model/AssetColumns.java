package es.cic.tessa.excel.asset.model;


import java.util.HashMap;
import java.util.Map;


public class AssetColumns
{

    private Map<Integer, AssetCells> assetColumns = new HashMap<Integer, AssetCells>();

    public Map<Integer, AssetCells> getAssetColumns()
    {

	return assetColumns;
    }


    public void setAssetColumns(Map<Integer, AssetCells> assetColumns)
    {

	this.assetColumns = assetColumns;
    }


    @Override
    public String toString()
    {

	return "[assetColumns=" + assetColumns + "]";
    }

}
