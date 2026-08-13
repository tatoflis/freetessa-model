package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import es.cic.tessa.model.Asset;
import es.cic.tessa.model.AssetReference;
import es.cic.tessa.model.dto.AssetReferenceResponse;
import es.cic.tessa.model.dto.AssetResponse;

@ExtendWith(MockitoExtension.class)
class AssetReferenceResponseMapperTest
{

    @Mock
    private AssetResponseMapper assetResponseMapper;

    @InjectMocks
    private AssetReferenceResponseMapper mapper;

    @Test
    void assetReferenceToResponse_mapeaCampos()
    {

	Asset asset = new Asset();
	asset.setCustomId(20L);
	asset.setName("referenced");

	AssetReference ref = new AssetReference();
	ref.setId(10L);
	ref.setReferenceType("PARENT");
	ref.setRelationType("HAS_VALUE");
	ref.setAsset(asset);

	AssetResponse assetResp = new AssetResponse();
	assetResp.setId(20L);
	when(assetResponseMapper.assetToAssetRespose(asset)).thenReturn(assetResp);

	AssetReferenceResponse result = mapper.assetReferenceToAssetReferenceRespose(ref);

	assertEquals(10L, result.getId());
	assertEquals("PARENT", result.getReferenceType());
	assertEquals("HAS_VALUE", result.getRelationType());
	assertNotNull(result.getAssetResponse());
	assertEquals(20L, result.getAssetResponse().getId());
    }

    @Test
    void responseToAssetReference_mapeaCampos()
    {

	AssetResponse assetResp = new AssetResponse();
	assetResp.setId(20L);

	AssetReferenceResponse resp = new AssetReferenceResponse();
	resp.setId(10L);
	resp.setReferenceType("PARENT");
	resp.setRelationType("HAS_VALUE");
	resp.setAssetResponse(assetResp);

	Asset asset = new Asset();
	asset.setCustomId(20L);
	when(assetResponseMapper.assetResponseToAsset(assetResp)).thenReturn(asset);

	AssetReference result = mapper.assetReferenceResponseToAssetReference(resp);

	assertEquals(10L, result.getId());
	assertEquals("PARENT", result.getReferenceType());
	assertEquals("HAS_VALUE", result.getRelationType());
	assertNotNull(result.getAsset());
	assertEquals(20L, result.getAsset().getCustomId());
    }
}
