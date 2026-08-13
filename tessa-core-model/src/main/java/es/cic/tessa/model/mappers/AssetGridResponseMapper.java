package es.cic.tessa.model.mappers;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.Asset;
import es.cic.tessa.model.dto.AssetResponse;
import es.cic.tessa.model.dto.json.ResponsePageJson;


@Component
public class AssetGridResponseMapper
{

    @Autowired
    @Lazy
    private TemplateResponseMapper templateResponseMapper;

    @Autowired
    @Lazy
    private AssetResponseMapper assetResponseMapper;

    @Autowired
    private AssetValueGridResponseMapper assetValueGridResposeMapper;

    @Autowired
    private OrganizerResponseMapper organizerResposeMapper;

    public AssetResponse assetToAssetGridRespose(Asset asset)
    {

	AssetResponse assetGridResponse = new AssetResponse();

	assetGridResponse.setId(asset.getCustomId());
	assetGridResponse.setActive(asset.getActive());
	assetGridResponse.setName(asset.getName());
	assetGridResponse.setIdentificator(asset.getIdentificator());
	assetGridResponse.setPhysicalPath(asset.getPhysicalPath());
	assetGridResponse.setLogicalPath(asset.getLogicalPath());
	assetGridResponse.setDescription(asset.getDescription());
	assetGridResponse.setIcon(asset.getIcon());
	if(asset.getInsertDate() != null)
	{
	    assetGridResponse.setInsertDate(asset.getInsertDate().toInstant(ZoneOffset.UTC));
	}
	if(asset.getModifDate() != null)
	{
	    assetGridResponse.setModifDate(asset.getModifDate().toInstant(ZoneOffset.UTC));
	}
	assetGridResponse.setVersion(asset.getVersion());
	assetGridResponse.setNemonic(asset.getNemonic());
	assetGridResponse.setNumComplexAssets(asset.getNumComplexAssets());

	if(asset.getTemplate() != null)
	{
	    assetGridResponse.setTemplate(templateResponseMapper.templateToFullTemplateRespose(asset.getTemplate()));
	}

	if(asset.getDependsAsset() != null)
	{
	    assetGridResponse.setAssetDependsResponse(assetToAssetGridRespose(asset.getDependsAsset()));
	}

	if(asset.getValues() != null)
	{
	    assetGridResponse.setValues(assetValueGridResposeMapper.assetValueCollectionToAssetValueResposeCollection(asset.getValues()));
	}

	if(asset.getOrganizers() != null)
	{
	    assetGridResponse.setOrganizers(organizerResposeMapper.organizerCollectionToOrganizerResposeCollection(asset.getOrganizers()));
	}

	return assetGridResponse;
    }


    public Asset assetGridResponseToAsset(AssetResponse assetGridResponse)
    {

	Asset asset = new Asset();

	asset.setCustomId(assetGridResponse.getId());
	asset.setName(assetGridResponse.getName());
	asset.setIdentificator(assetGridResponse.getIdentificator());
	if(assetGridResponse.getName() != null)
	{
	    asset.setNameLower(assetGridResponse.getName().toLowerCase());
	}
	asset.setDescription(assetGridResponse.getDescription());
	asset.setIcon(assetGridResponse.getIcon());
	asset.setInsertDate(LocalDateTime.ofInstant(assetGridResponse.getInsertDate(), ZoneOffset.UTC));

	if(assetGridResponse.getModifDate() != null)
	{
	    asset.setModifDate(LocalDateTime.ofInstant(assetGridResponse.getModifDate(), ZoneOffset.UTC));
	}

	asset.setVersion(assetGridResponse.getVersion());

	asset.setNemonic(assetGridResponse.getNemonic());

	if(assetGridResponse.getTemplate() != null)
	{
	    asset.setTemplate(templateResponseMapper.templateResponseToTemplate(assetGridResponse.getTemplate()));
	}

	if(assetGridResponse.getAssetDependsResponse() != null)
	{
	    asset.setDependsAsset(assetResponseMapper.assetResponseToAsset(assetGridResponse.getAssetDependsResponse()));
	}

	asset.setValues(assetValueGridResposeMapper.assetValueResponseCollectionToAssetValueCollection(assetGridResponse.getValues()));
	asset.setOrganizers(organizerResposeMapper.organizerResponseCollectionToOrganizerList(assetGridResponse.getOrganizers()));

	return asset;
    }


    public ResponsePage<AssetResponse> assetPageToAssetGridResposePage(ResponsePage<Asset> assets)
    {

	LinkedHashSet<AssetResponse> assetsGridResponse = new LinkedHashSet<>();

	for (Asset asset : assets)
	{
	    assetsGridResponse.add(assetToAssetGridRespose(asset));

	}

	return new ResponsePage<>(assetsGridResponse, assets.getPageable(), assets.getTotalElements());

    }


    public ResponsePage<Asset> assetGridCollectionToAssetResposePage(ResponsePageJson<AssetResponse> assetsResponse)
    {

	List<Asset> assets = new ArrayList<>();

	PageRequest pageRequest = PageRequest.of(assetsResponse.getPageable().getPageNumber(), assetsResponse.getPageable().getPageSize());

	for (AssetResponse assetGridResponse : assetsResponse.getContent())
	{

	    Asset asset = assetGridResponseToAsset(assetGridResponse);

	    assets.add(asset);
	}

	return new ResponsePage<>(assets, pageRequest, assetsResponse.getTotalElements());

    }

}
