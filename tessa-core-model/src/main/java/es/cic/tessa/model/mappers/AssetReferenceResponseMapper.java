package es.cic.tessa.model.mappers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import es.cic.tessa.model.AssetReference;
import es.cic.tessa.model.dto.AssetReferenceResponse;


@Component
public class AssetReferenceResponseMapper
{

    @Autowired
    private AssetResponseMapper assetResponseMapper;

    public AssetReferenceResponse assetReferenceToAssetReferenceRespose(AssetReference assetReference)
    {

	AssetReferenceResponse assetReferenceResponse = new AssetReferenceResponse();

	assetReferenceResponse.setId(assetReference.getId());
	assetReferenceResponse.setReferenceType(assetReference.getReferenceType());
	assetReferenceResponse.setRelationType(assetReference.getRelationType());

	assetReferenceResponse.setAssetResponse(assetResponseMapper.assetToAssetRespose(assetReference.getAsset()));

	return assetReferenceResponse;
    }


    public AssetReference assetReferenceResponseToAssetReference(AssetReferenceResponse assetReferenceResponse)
    {

	AssetReference assetReference = new AssetReference();

	assetReference.setId(assetReferenceResponse.getId());
	assetReference.setRelationType(assetReferenceResponse.getRelationType());
	assetReference.setReferenceType(assetReferenceResponse.getReferenceType());

	assetReference.setAsset(assetResponseMapper.assetResponseToAsset(assetReferenceResponse.getAssetResponse()));

	return assetReference;

    }

}
