package es.cic.tessa.model.mappers;


import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.Asset;
import es.cic.tessa.model.AssetReference;
import es.cic.tessa.model.dto.AssetReferenceRequest;


@Component
public class AssetReferenceRequestMapper
{

    public AssetReference assetReferenceRequestToAssetReference(AssetReferenceRequest assetReferenceRequest)
    {

	AssetReference assetReference = new AssetReference();
	assetReference.setId(assetReferenceRequest.getId());

	Asset asset = new Asset();
	asset.setCustomId(assetReferenceRequest.getIdAsset());
	assetReference.setAsset(asset);

	return assetReference;

    }


    public ResponsePage<AssetReference> assetReferenceRequestPageToAssetReferencePage(ResponsePage<AssetReferenceRequest> assetReferencesRequest)
    {

	List<AssetReference> assetReferences = new ArrayList<AssetReference>();

	for (AssetReferenceRequest assetReferenceRequest : assetReferencesRequest)
	{
	    assetReferences.add(assetReferenceRequestToAssetReference(assetReferenceRequest));
	}

	return new ResponsePage<AssetReference>(assetReferences, assetReferencesRequest.getPageable(), assetReferencesRequest.getTotalElements());

    }

}
