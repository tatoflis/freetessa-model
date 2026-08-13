package es.cic.tessa.model.mappers;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.Asset;
import es.cic.tessa.model.dto.AssetResponse;
import es.cic.tessa.model.dto.json.ResponsePageJson;


@Component
public class AssetResponseMapper
{

    @Autowired
    @Lazy
    private TemplateResponseMapper templateResponseMapper;

    public AssetResponse assetToAssetRespose(Asset asset)
    {

	AssetResponse assetResponse = new AssetResponse();

	assetResponse.setId(asset.getCustomId());
	assetResponse.setActive(asset.getActive());
	assetResponse.setName(asset.getName());
	assetResponse.setIdentificator(asset.getIdentificator());
	assetResponse.setPhysicalPath(asset.getPhysicalPath());
	assetResponse.setLogicalPath(asset.getLogicalPath());
	assetResponse.setDescription(asset.getDescription());
	assetResponse.setIcon(asset.getIcon());
	if(asset.getInsertDate() != null)
	{
	    assetResponse.setInsertDate(asset.getInsertDate().toInstant(ZoneOffset.UTC));
	}

	if(asset.getModifDate() != null)
	{
	    assetResponse.setModifDate(asset.getModifDate().toInstant(ZoneOffset.UTC));
	}

	assetResponse.setVersion(asset.getVersion());
	assetResponse.setNemonic(asset.getNemonic());

	assetResponse.setNumComplexAssets(asset.getNumComplexAssets());

	if(asset.getTemplate() != null)
	{
	    assetResponse.setTemplate(templateResponseMapper.templateToFullTemplateRespose(asset.getTemplate()));
	}

	if(asset.getDependsAsset() != null)
	{
	    assetResponse.setAssetDependsResponse(assetToAssetRespose(asset.getDependsAsset()));
	}

	return assetResponse;
    }


    public ResponsePage<AssetResponse> assetPageToAssetResposePage(ResponsePage<Asset> assets)
    {

	LinkedHashSet<AssetResponse> assetsResponse = new LinkedHashSet<>();

	for (Asset asset : assets)
	{
	    assetsResponse.add(assetToAssetRespose(asset));

	}

	return new ResponsePage<>(new ArrayList<>(assetsResponse), assets.getPageable(), assets.getTotalElements());

    }


    public Asset assetResponseToAsset(AssetResponse assetResponse)
    {

	Asset asset = new Asset();

	asset.setCustomId(assetResponse.getId());
	asset.setName(assetResponse.getName());
	asset.setIdentificator(assetResponse.getIdentificator());
	asset.setIcon(assetResponse.getIcon());
	asset.setActive(assetResponse.getActive());

	asset.setDescription(assetResponse.getDescription());
	asset.setInsertDate(LocalDateTime.ofInstant(assetResponse.getInsertDate(), ZoneOffset.UTC));

	if(assetResponse.getModifDate() != null)
	{
	    asset.setModifDate(LocalDateTime.ofInstant(assetResponse.getModifDate(), ZoneOffset.UTC));
	}

	asset.setVersion(assetResponse.getVersion());
	asset.setNemonic(assetResponse.getNemonic());

	if(assetResponse.getTemplate() != null)
	{
	    asset.setTemplate(templateResponseMapper.templateResponseToTemplate(assetResponse.getTemplate()));
	}

	if(assetResponse.getAssetDependsResponse() != null)
	{
	    asset.setDependsAsset(assetResponseToAsset(assetResponse.getAssetDependsResponse()));
	}

	return asset;
    }


    public ResponsePage<Asset> assetResponsePageToAssetPage(ResponsePage<AssetResponse> assetsResponse)
    {

	Set<Asset> assets = new HashSet<>();

	for (AssetResponse assetResponse : assetsResponse)
	{
	    assets.add(assetResponseToAsset(assetResponse));
	}

	return new ResponsePage<>(assets, assetsResponse.getPageable(), assetsResponse.getTotalElements());

    }


    public ResponsePage<Asset> assetCollectionToAssetResposePage(ResponsePageJson<AssetResponse> assetsResponse)
    {

	List<Asset> assets = new ArrayList<>();

	PageRequest pageRequest = PageRequest.of(assetsResponse.getPageable().getPageNumber(), assetsResponse.getPageable().getPageSize());

	for (AssetResponse assetResponse : assetsResponse.getContent())
	{

	    Asset asset = assetResponseToAsset(assetResponse);

	    assets.add(asset);
	}

	return new ResponsePage<>(assets, pageRequest, assetsResponse.getTotalElements());

    }

}
