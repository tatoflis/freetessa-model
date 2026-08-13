package es.cic.tessa.model.mappers;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import java.time.Instant;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import es.cic.tessa.model.Asset;
import es.cic.tessa.model.Template;
import es.cic.tessa.model.dto.AssetResponse;
import es.cic.tessa.model.dto.TemplateResponse;


@ExtendWith(MockitoExtension.class)
class AssetResponseMapperTest
{

    @Mock
    private TemplateResponseMapper templateResponseMapper;

    @InjectMocks
    private AssetResponseMapper mapper;

    @Test
    void assetToResponse_camposBasicos()
    {

	Asset asset = new Asset();
	asset.setCustomId(1L);
	asset.setName("asset1");
	asset.setDescription("desc");
	asset.setIcon("icon.png");
	asset.setIdentificator("ID-001");
	asset.setActive(true);
	asset.setVersion(5L);
	asset.setNemonic("nem");
	asset.setInsertDate(LocalDateTime.of(2024, 1, 15, 10, 30));

	AssetResponse result = mapper.assetToAssetRespose(asset);

	assertEquals(1L, result.getId());
	assertEquals("asset1", result.getName());
	assertEquals("desc", result.getDescription());
	assertEquals("icon.png", result.getIcon());
	assertEquals("ID-001", result.getIdentificator());
	assertTrue(result.getActive());
	assertEquals(5L, result.getVersion());
	assertEquals("nem", result.getNemonic());
	assertNotNull(result.getInsertDate());
    }


    @Test
    void assetToResponse_conTemplate_invocaTemplateMapper()
    {

	Template tmpl = new Template();
	tmpl.setCustomId(10L);

	Asset asset = new Asset();
	asset.setCustomId(1L);
	asset.setName("a");
	asset.setInsertDate(LocalDateTime.of(2024, 1, 1, 0, 0));
	asset.setTemplate(tmpl);

	TemplateResponse tmplResp = new TemplateResponse();
	tmplResp.setId(10L);
	when(templateResponseMapper.templateToFullTemplateRespose(tmpl)).thenReturn(tmplResp);

	AssetResponse result = mapper.assetToAssetRespose(asset);

	assertNotNull(result.getTemplate());
	assertEquals(10L, result.getTemplate().getId());
	verify(templateResponseMapper).templateToFullTemplateRespose(tmpl);
    }


    @Test
    void assetToResponse_sinTemplate_noInvocaMapper()
    {

	Asset asset = new Asset();
	asset.setCustomId(1L);
	asset.setName("a");
	asset.setInsertDate(LocalDateTime.of(2024, 1, 1, 0, 0));

	AssetResponse result = mapper.assetToAssetRespose(asset);

	assertNull(result.getTemplate());
	verifyNoInteractions(templateResponseMapper);
    }


    @Test
    void assetToResponse_conDependsAsset_recursivo()
    {

	Asset parent = new Asset();
	parent.setCustomId(50L);
	parent.setName("parent");
	parent.setInsertDate(LocalDateTime.of(2024, 1, 1, 0, 0));

	Asset asset = new Asset();
	asset.setCustomId(1L);
	asset.setName("child");
	asset.setInsertDate(LocalDateTime.of(2024, 1, 1, 0, 0));
	asset.setDependsAsset(parent);

	AssetResponse result = mapper.assetToAssetRespose(asset);

	assertNotNull(result.getAssetDependsResponse());
	assertEquals(50L, result.getAssetDependsResponse().getId());
    }


    @Test
    void assetResponseToAsset_camposBasicos()
    {

	AssetResponse resp = new AssetResponse();
	resp.setId(1L);
	resp.setName("asset1");
	resp.setDescription("desc");
	resp.setIcon("icon.png");
	resp.setIdentificator("ID-001");
	resp.setActive(true);
	resp.setVersion(5L);
	resp.setNemonic("nem");
	resp.setInsertDate(Instant.parse("2024-06-01T12:00:00Z"));

	Asset result = mapper.assetResponseToAsset(resp);

	assertEquals(1L, result.getCustomId());
	assertEquals("asset1", result.getName());
	assertEquals("desc", result.getDescription());
	assertEquals("icon.png", result.getIcon());
	assertEquals("ID-001", result.getIdentificator());
	assertTrue(result.getActive());
	assertEquals(5L, result.getVersion());
    }


    @Test
    void assetResponseToAsset_conTemplate_invocaReverseMapper()
    {

	TemplateResponse tmplResp = new TemplateResponse();
	tmplResp.setId(10L);

	AssetResponse resp = new AssetResponse();
	resp.setId(1L);
	resp.setName("a");
	resp.setInsertDate(Instant.parse("2024-01-01T00:00:00Z"));
	resp.setTemplate(tmplResp);

	Template tmpl = new Template();
	tmpl.setCustomId(10L);
	when(templateResponseMapper.templateResponseToTemplate(tmplResp)).thenReturn(tmpl);

	Asset result = mapper.assetResponseToAsset(resp);

	assertNotNull(result.getTemplate());
	assertEquals(10L, result.getTemplate().getCustomId());
    }
}
