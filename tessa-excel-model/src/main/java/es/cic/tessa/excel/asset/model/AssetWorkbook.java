package es.cic.tessa.excel.asset.model;


import java.util.HashMap;
import java.util.Map;


public class AssetWorkbook
{

    private Map<String, AssetRows> assetSheet = new HashMap<String, AssetRows>();

    public Map<String, AssetRows> getAssetSheet()
    {

	return assetSheet;
    }


    public void setAssetSheet(Map<String, AssetRows> assetSheet)
    {

	this.assetSheet = assetSheet;
    }


    @Override
    public String toString()
    {

	return "[assetSheet=" + assetSheet + "]";
    }

}
