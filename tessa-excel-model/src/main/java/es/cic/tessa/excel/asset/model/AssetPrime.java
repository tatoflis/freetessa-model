package es.cic.tessa.excel.asset.model;


import es.cic.tessa.model.Asset;


public class AssetPrime
{

    private Asset assetBase;
    private String name;
    private String alias;

    public AssetPrime(Asset assetBase, String name, String alias)
    {

	super();
	this.assetBase = assetBase;
	this.name = name;
	this.alias = alias;

    }


    public Asset getAssetBase()
    {

	return assetBase;
    }


    public String getAlias()
    {

	return alias;
    }


    public String getName()
    {

	return name;
    }

}
