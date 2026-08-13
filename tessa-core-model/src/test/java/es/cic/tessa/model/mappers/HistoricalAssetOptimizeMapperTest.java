package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.HistoricalAsset;
import es.cic.tessa.model.HistoricalTemplate;
import es.cic.tessa.model.optimize.AssetOptimize;

class HistoricalAssetOptimizeMapperTest
{

    @Test
    void toOptimize_conNull_devuelveNull()
    {

	assertNull(HistoricalAssetOptimizeMapper.toOptimize(null));
    }

    @Test
    void toOptimize_conCamposBasicos_mapeaCorrectamente()
    {

	HistoricalAsset asset = new HistoricalAsset();
	asset.setCustomId(1L);
	asset.setName("asset-name");
	asset.setGroups(Set.of("g1"));
	asset.setIdentificator("IDENT-1");
	asset.setActive(true);

	AssetOptimize result = HistoricalAssetOptimizeMapper.toOptimize(asset);

	assertNotNull(result);
	assertEquals(1L, result.getId());
	assertEquals("asset-name", result.getName());
	assertEquals("IDENT-1", result.getIdentificator());
	assertTrue(result.isActive());
    }

    @Test
    void toOptimize_conTemplate_mapeaTemplateOptimizeSinExtends()
    {

	HistoricalTemplate ancestro = new HistoricalTemplate();
	ancestro.setCustomId(200L);
	ancestro.setName("ancestro");

	HistoricalTemplate template = new HistoricalTemplate();
	template.setCustomId(100L);
	template.setName("tmpl");
	template.setGroups(Set.of("tg"));
	template.setType("Complex");
	template.setHistoricalExtendsTemplate(ancestro);

	HistoricalAsset asset = new HistoricalAsset();
	asset.setCustomId(1L);
	asset.setName("asset-name");
	asset.setHistoricalTemplate(template);

	AssetOptimize result = HistoricalAssetOptimizeMapper.toOptimize(asset);

	assertNotNull(result.getTemplateOptimize());
	assertEquals(100L, result.getTemplateOptimize().getId());
	assertEquals("Complex", result.getTemplateOptimize().getType());
    }

    @Test
    void toOptimize_conDependsAsset_mapeaAssetDependsOptimize()
    {

	HistoricalAsset padre = new HistoricalAsset();
	padre.setCustomId(2L);
	padre.setName("padre");
	padre.setGroups(Set.of("g"));

	HistoricalAsset asset = new HistoricalAsset();
	asset.setCustomId(1L);
	asset.setName("hijo");
	asset.setHistoricalDependsAsset(padre);

	AssetOptimize result = HistoricalAssetOptimizeMapper.toOptimize(asset);

	assertNotNull(result.getAssetDependsOptimize());
	assertEquals(2L, result.getAssetDependsOptimize().getId());
	assertEquals("padre", result.getAssetDependsOptimize().getName());
    }

    @Test
    void toOptimize_sinDependsAsset_assetDependsOptimizeEsNull()
    {

	HistoricalAsset asset = new HistoricalAsset();
	asset.setCustomId(1L);
	asset.setName("asset-name");

	AssetOptimize result = HistoricalAssetOptimizeMapper.toOptimize(asset);

	assertNull(result.getAssetDependsOptimize());
    }
}
