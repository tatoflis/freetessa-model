package es.cic.tessa.model.mappers;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.Asset;
import es.cic.tessa.model.Template;
import es.cic.tessa.model.dto.AssetRequest;
import es.cic.tessa.model.dto.AssignOrganizersToAssetsRequest;
import es.cic.tessa.model.filter.AssetFilter;


@Component
public class AssetRequestMapper
{

    public Asset assetRequestToAsset(AssetRequest assetRequest, Set<String> groups)
    {

	Asset asset = new Asset();

	asset.setCustomId(assetRequest.getId());

	if(assetRequest.getName() != null)
	{
	    asset.setName(assetRequest.getName());
	    asset.setNameLower(assetRequest.getName().toLowerCase());
	}

	if(assetRequest.getIdentificator() != null)
	{
	    asset.setIdentificator(assetRequest.getIdentificator());
	    asset.setIdentificatorLower(assetRequest.getIdentificator().toLowerCase());
	}

	asset.setDescription(assetRequest.getDescription());
	asset.setIcon(assetRequest.getIcon());
	asset.setGroups(groups);

	if(assetRequest.getActive() == null)
	{
	    asset.setActive(true);
	}
	else
	{
	    asset.setActive(assetRequest.getActive());
	}

	if(assetRequest.getVersion() != null)
	{
	    asset.setVersion(assetRequest.getVersion());
	}

	if(assetRequest.getIdTemplate() != null)
	{
	    Template template = new Template();
	    template.setCustomId(assetRequest.getIdTemplate());

	    asset.setTemplate(template);
	}

	if(assetRequest.getIdAssetBase() != null)
	{
	    Asset assetBase = new Asset();
	    assetBase.setCustomId(assetRequest.getIdAssetBase());

	    asset.setDependsAsset(assetBase);
	}

	if(assetRequest.getInsertDate() != null)
	{
	    asset.setInsertDate(assetRequest.getInsertDate());
	}

	return asset;
    }


    public AssetRequest assetToAssetRequest(Asset asset)
    {

	AssetRequest assetRequest = new AssetRequest();

	assetRequest.setId(asset.getCustomId());
	assetRequest.setName(asset.getName());
	assetRequest.setIdentificator(asset.getIdentificator());
	assetRequest.setDescription(asset.getDescription());
	assetRequest.setIcon(asset.getIcon());
	assetRequest.setActive(asset.getActive());

	if(asset.getVersion() != null)
	{
	    assetRequest.setVersion(asset.getVersion());
	}

	if(asset.getTemplate() != null)
	{
	    assetRequest.setIdTemplate(asset.getTemplate().getCustomId());
	}

	if(asset.getDependsAsset() != null)
	{
	    assetRequest.setIdAssetBase(asset.getDependsAsset().getCustomId());
	}

	return assetRequest;
    }


    public AssignOrganizersToAssetsRequest assignOrganizerToAssetRequest(AssetFilter assetFilter, Collection<Long> idsOrganizers, Set<String> groups)
    {

	AssignOrganizersToAssetsRequest assignOrganizerToAssetRequest = new AssignOrganizersToAssetsRequest();

	assignOrganizerToAssetRequest.setAssetFilter(assetFilter);
	assignOrganizerToAssetRequest.setOrganizerIds(idsOrganizers);
	return assignOrganizerToAssetRequest;
    }


    public ResponsePage<Asset> assetRequestPageToAssetPage(ResponsePage<AssetRequest> assetsRequest, Set<String> groups)
    {

	Collection<Asset> assets = new ArrayList<>();

	for (AssetRequest assetRequest : assetsRequest)
	{
	    assets.add(assetRequestToAsset(assetRequest, groups));
	}

	return new ResponsePage<Asset>(assets, assetsRequest.getPageable(), assetsRequest.getTotalElements());

    }


    public Collection<Asset> assetRequestCollectionToAssetCollection(Collection<AssetRequest> assetsRequest, Set<String> groups)
    {

	Collection<Asset> assets = new HashSet<>();

	for (AssetRequest assetRequest : assetsRequest)
	{
	    assets.add(assetRequestToAsset(assetRequest, groups));
	}

	return assets;

    }

}
