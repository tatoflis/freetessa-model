package es.cic.tessa.model.csv;


import java.util.List;
import es.cic.tessa.model.Asset;
import es.cic.tessa.model.Organizer;


public class AssetCSV
{

    private Asset asset;
    private List<Organizer> organizersWithMetadata;

    public AssetCSV(Asset asset, List<Organizer> organizersWithMetadata)
    {

	this.asset = asset;
	this.organizersWithMetadata = organizersWithMetadata;

    }


    public AssetCSV(Asset asset)
    {

	this.asset = asset;
    }


    public Asset getAsset()
    {

	return asset;
    }


    public void setAsset(Asset asset)
    {

	this.asset = asset;
    }


    public List<Organizer> getOrganizersWithMetadata()
    {

	return organizersWithMetadata;
    }


    public void setOrganizersWithMetadata(List<Organizer> organizersWithMetadata)
    {

	this.organizersWithMetadata = organizersWithMetadata;
    }


    @Override
    public String toString()
    {

	return "AssetCSV [getAsset()=" + (getAsset() != null ? getAsset().getName() : "null") + "]";
    }

}
