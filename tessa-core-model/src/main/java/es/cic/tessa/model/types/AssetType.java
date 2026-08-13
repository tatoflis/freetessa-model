package es.cic.tessa.model.types;


import com.fasterxml.jackson.annotation.JsonValue;


public enum AssetType
{

 SYNTETIC_ASSET("SynteticAsset"),
     ASSET("Asset"),
     ASSET_BASE("AssetBase"),
     ASSET_AND_ASSET_BASE("AssetAndAssetBase"),
     SYNTETIC_ASSET_AND_ASSET("SynteticAssetAndAsset");

    @JsonValue
    public String code;

    private AssetType(String code)
    {

	this.code = code;
    }


    public static AssetType fromString(String code)
    {

	if(code != null)
	{
	    for (AssetType assetType : AssetType.values())
	    {
		if(code.equalsIgnoreCase(assetType.code))
		{
		    return assetType;
		}
	    }
	}
	return null;
    }


    public static String fromStringToString(String code)
    {

	if(code != null)
	{
	    for (AssetType assetType : AssetType.values())
	    {
		if(code.equalsIgnoreCase(assetType.code))
		{
		    return assetType.getCode();
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
