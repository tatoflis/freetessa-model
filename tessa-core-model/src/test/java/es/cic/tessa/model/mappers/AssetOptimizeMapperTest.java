package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.Asset;
import es.cic.tessa.model.Template;
import es.cic.tessa.model.optimize.AssetOptimize;

class AssetOptimizeMapperTest
{

    @Test
    void toOptimize_conNull_devuelveNull()
    {

	assertNull(AssetOptimizeMapper.toOptimize(null));
    }

    @Test
    void toOptimize_conCamposBasicos_mapeaCorrectamente()
    {

	Asset asset = new Asset();
	asset.setCustomId(1L);
	asset.setName("asset-name");
	asset.setGroups(Set.of("group1", "group2"));
	asset.setIdentificator("ASSET-001");
	asset.setActive(true);

	AssetOptimize result = AssetOptimizeMapper.toOptimize(asset);

	assertNotNull(result);
	assertEquals(1L, result.getId());
	assertEquals("asset-name", result.getName());
	assertTrue(result.getGroups().containsAll(Set.of("group1", "group2")));
	assertEquals("ASSET-001", result.getIdentificator());
	assertTrue(result.isActive());
    }

    @Test
    void toOptimize_conTemplate_mapeaTemplateOptimize()
    {

	Template template = new Template();
	template.setCustomId(10L);
	template.setName("template-name");
	template.setGroups(Set.of("grp"));
	template.setType("COMPLEX");

	Asset asset = new Asset();
	asset.setCustomId(1L);
	asset.setName("asset");
	asset.setTemplate(template);

	AssetOptimize result = AssetOptimizeMapper.toOptimize(asset);

	assertNotNull(result.getTemplateOptimize());
	assertEquals(10L, result.getTemplateOptimize().getId());
	assertEquals("template-name", result.getTemplateOptimize().getName());
	assertTrue(result.getTemplateOptimize().getGroups().contains("grp"));
	assertEquals("COMPLEX", result.getTemplateOptimize().getType());
    }

    @Test
    void toOptimize_sinTemplate_templateOptimizeEsNull()
    {

	Asset asset = new Asset();
	asset.setCustomId(1L);
	asset.setName("asset");

	AssetOptimize result = AssetOptimizeMapper.toOptimize(asset);

	assertNull(result.getTemplateOptimize());
    }

    @Test
    void toOptimize_conDependsAsset_mapeaDependsOptimize()
    {

	Asset dependsAsset = new Asset();
	dependsAsset.setCustomId(5L);
	dependsAsset.setName("parent-asset");
	dependsAsset.setGroups(Set.of("g1"));

	Asset asset = new Asset();
	asset.setCustomId(1L);
	asset.setName("child-asset");
	asset.setDependsAsset(dependsAsset);

	AssetOptimize result = AssetOptimizeMapper.toOptimize(asset);

	assertNotNull(result.getAssetDependsOptimize());
	assertEquals(5L, result.getAssetDependsOptimize().getId());
	assertEquals("parent-asset", result.getAssetDependsOptimize().getName());
	assertTrue(result.getAssetDependsOptimize().getGroups().contains("g1"));
    }

    @Test
    void toOptimize_sinDependsAsset_dependsOptimizeEsNull()
    {

	Asset asset = new Asset();
	asset.setCustomId(1L);
	asset.setName("asset");

	AssetOptimize result = AssetOptimizeMapper.toOptimize(asset);

	assertNull(result.getAssetDependsOptimize());
    }
}
