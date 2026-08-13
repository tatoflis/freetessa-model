package es.cic.tessa.model.mappers;


import es.cic.tessa.model.Asset;
import es.cic.tessa.model.optimize.AssetOptimize;
import es.cic.tessa.model.optimize.TemplateOptimize;


public final class AssetOptimizeMapper
{

    private AssetOptimizeMapper()
    {

    }


    public static AssetOptimize toOptimize(Asset asset)
    {

	if(asset == null)
	{
	    return null;
	}

	AssetOptimize optimize = new AssetOptimize();
	optimize.setId(asset.getCustomId());
	optimize.setName(asset.getName());
	optimize.setGroups(asset.getGroups());
	optimize.setIdentificator(asset.getIdentificator());
	optimize.setActive(asset.getActive());

	if(asset.getTemplate() != null)
	{
	    TemplateOptimize tOptimize = new TemplateOptimize();
	    tOptimize.setId(asset.getTemplate().getCustomId());
	    tOptimize.setName(asset.getTemplate().getName());
	    tOptimize.setGroups(asset.getTemplate().getGroups());
	    tOptimize.setType(asset.getTemplate().getType());
	    optimize.setTemplateOptimize(tOptimize);
	}

	if(asset.getDependsAsset() != null)
	{
	    AssetOptimize dependsOptimize = new AssetOptimize();
	    dependsOptimize.setId(asset.getDependsAsset().getCustomId());
	    dependsOptimize.setName(asset.getDependsAsset().getName());
	    dependsOptimize.setGroups(asset.getDependsAsset().getGroups());
	    optimize.setAssetDependsOptimize(dependsOptimize);
	}

	return optimize;
    }
}
