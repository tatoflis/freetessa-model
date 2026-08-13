package es.cic.tessa.excel.asset.model;


import java.util.HashSet;
import java.util.Set;


public class AssetRows
{

    private Set<AssetColumns> assetRows = new HashSet<AssetColumns>();

    private AssetCells assetAttributes = new AssetCells();

    public Set<AssetColumns> getAssetRows()
    {

	return assetRows;
    }


    public void setAssetRows(Set<AssetColumns> assetRows)
    {

	this.assetRows = assetRows;
    }


    public AssetCells getAssetAttributes()
    {

	return assetAttributes;
    }


    public void setAssetAttributes(AssetCells assetAttributes)
    {

	this.assetAttributes = assetAttributes;
    }


    @Override
    public String toString()
    {

	return "[assetRows=" + assetRows + ", assetAttributes=" + assetAttributes + "]";
    }

}
