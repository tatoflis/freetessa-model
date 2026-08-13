package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.AssetReference;
import es.cic.tessa.model.dto.AssetReferenceRequest;

class AssetReferenceRequestMapperTest
{

    private AssetReferenceRequestMapper mapper;

    @BeforeEach
    void setUp()
    {

	mapper = new AssetReferenceRequestMapper();
    }

    @Test
    void requestToAssetReference_mapeaCampos()
    {

	AssetReferenceRequest request = new AssetReferenceRequest();
	request.setId(10L);
	request.setIdAsset(20L);

	AssetReference result = mapper.assetReferenceRequestToAssetReference(request);

	assertEquals(10L, result.getId());
	assertNotNull(result.getAsset());
	assertEquals(20L, result.getAsset().getCustomId());
    }

    @Test
    void requestToAssetReference_sinIdAsset_assetConCustomIdNull()
    {

	AssetReferenceRequest request = new AssetReferenceRequest();
	request.setId(10L);

	AssetReference result = mapper.assetReferenceRequestToAssetReference(request);

	assertEquals(10L, result.getId());
	assertNotNull(result.getAsset());
	assertNull(result.getAsset().getCustomId());
    }
}
