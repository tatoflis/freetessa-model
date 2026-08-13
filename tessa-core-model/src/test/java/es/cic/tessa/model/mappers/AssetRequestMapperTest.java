package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.Asset;
import es.cic.tessa.model.Template;
import es.cic.tessa.model.dto.AssetRequest;

class AssetRequestMapperTest
{

    private AssetRequestMapper mapper;

    @BeforeEach
    void setUp()
    {

	mapper = new AssetRequestMapper();
    }

    @Test
    void assetRequestToAsset_mapeaCamposBasicos()
    {

	AssetRequest request = new AssetRequest();
	request.setId(1L);
	request.setName("asset-name");
	request.setIdentificator("ASSET-001");
	request.setDescription("desc");
	request.setIcon("icon.png");
	request.setActive(true);
	request.setVersion(2L);
	request.setIdTemplate(10L);
	request.setIdAssetBase(20L);
	request.setInsertDate(LocalDateTime.of(2025, 1, 1, 0, 0));

	Set<String> groups = Set.of("g1");
	Asset result = mapper.assetRequestToAsset(request, groups);

	assertEquals(1L, result.getCustomId());
	assertEquals("asset-name", result.getName());
	assertEquals("asset-name", result.getNameLower());
	assertEquals("ASSET-001", result.getIdentificator());
	assertEquals("asset-001", result.getIdentificatorLower());
	assertEquals("desc", result.getDescription());
	assertEquals("icon.png", result.getIcon());
	assertTrue(result.getActive());
	assertEquals(2L, result.getVersion());
	assertNotNull(result.getTemplate());
	assertEquals(10L, result.getTemplate().getCustomId());
	assertNotNull(result.getDependsAsset());
	assertEquals(20L, result.getDependsAsset().getCustomId());
	assertEquals(LocalDateTime.of(2025, 1, 1, 0, 0), result.getInsertDate());
	assertTrue(result.getGroups().contains("g1"));
    }

    @Test
    void assetRequestToAsset_sinActive_defaultTrue()
    {

	AssetRequest request = new AssetRequest();
	request.setName("asset");
	request.setActive(null);

	Asset result = mapper.assetRequestToAsset(request, Set.of("g"));

	assertTrue(result.getActive());
    }

    @Test
    void assetRequestToAsset_sinTemplate_templateEsNull()
    {

	AssetRequest request = new AssetRequest();
	request.setName("asset");

	Asset result = mapper.assetRequestToAsset(request, Set.of("g"));

	assertNull(result.getTemplate());
    }

    @Test
    void assetToAssetRequest_mapeaCamposCorrectamente()
    {

	Template template = new Template();
	template.setCustomId(10L);

	Asset dependsAsset = new Asset();
	dependsAsset.setCustomId(20L);

	Asset asset = new Asset();
	asset.setCustomId(1L);
	asset.setName("asset-name");
	asset.setIdentificator("ASSET-001");
	asset.setDescription("desc");
	asset.setIcon("icon.png");
	asset.setActive(true);
	asset.setVersion(2L);
	asset.setTemplate(template);
	asset.setDependsAsset(dependsAsset);

	AssetRequest result = mapper.assetToAssetRequest(asset);

	assertEquals(1L, result.getId());
	assertEquals("asset-name", result.getName());
	assertEquals("ASSET-001", result.getIdentificator());
	assertEquals("desc", result.getDescription());
	assertEquals("icon.png", result.getIcon());
	assertTrue(result.getActive());
	assertEquals(2L, result.getVersion());
	assertEquals(10L, result.getIdTemplate());
	assertEquals(20L, result.getIdAssetBase());
    }

    @Test
    void assetToAssetRequest_sinRelaciones_idsNull()
    {

	Asset asset = new Asset();
	asset.setCustomId(1L);
	asset.setName("asset");

	AssetRequest result = mapper.assetToAssetRequest(asset);

	assertNull(result.getIdTemplate());
	assertNull(result.getIdAssetBase());
    }
}
