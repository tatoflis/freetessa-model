package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Instant;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import es.cic.tessa.model.Asset;
import es.cic.tessa.model.Organizer;
import es.cic.tessa.model.dto.AssetResponse;
import es.cic.tessa.model.dto.OrganizerResponse;

@ExtendWith(MockitoExtension.class)
class OrganizerResponseMapperTest
{

    @Mock
    private AssetResponseMapper assetResponseMapper;

    @InjectMocks
    private OrganizerResponseMapper mapper;

    @Test
    void organizerToResponse_camposBasicos()
    {

	Organizer org = new Organizer();
	org.setCustomId(1L);
	org.setName("org1");
	org.setDescription("desc");
	org.setIcon("icon.png");
	org.setOrganizerType("FOLDER");
	org.setNumElements(5);
	org.setNumOrganizers(3);
	org.setVersion(2L);
	org.setNemonic("root/child");
	org.setInsertDate(LocalDateTime.of(2024, 1, 15, 10, 30));
	org.setPath("root/child");

	OrganizerResponse result = mapper.organizerToOrganizerRespose(org);

	assertEquals(1L, result.getId());
	assertEquals("org1", result.getName());
	assertEquals("desc", result.getDescription());
	assertEquals("icon.png", result.getIcon());
	assertEquals("FOLDER", result.getType());
	assertEquals(5, result.getNumElements());
	assertEquals(3, result.getNumOrganizers());
	assertEquals(2L, result.getVersion());
	assertEquals("root/child", result.getPath());
    }

    @Test
    void organizerToResponse_conMetadata_invocaAssetMapper()
    {

	Asset metadata = new Asset();
	metadata.setCustomId(10L);

	Organizer org = new Organizer();
	org.setCustomId(1L);
	org.setName("org1");
	org.setNemonic("root");
	org.setInsertDate(LocalDateTime.of(2024, 1, 1, 0, 0));
	org.setMetadata(metadata);

	AssetResponse assetResp = new AssetResponse();
	assetResp.setId(10L);
	when(assetResponseMapper.assetToAssetRespose(metadata)).thenReturn(assetResp);

	OrganizerResponse result = mapper.organizerToOrganizerRespose(org);

	assertNotNull(result.getMetadata());
	assertEquals(10L, result.getMetadata().getId());
    }

    @Test
    void organizerToResponse_conParentOrganizer_recursivo()
    {

	Organizer parent = new Organizer();
	parent.setCustomId(50L);
	parent.setName("parent");
	parent.setNemonic("root");
	parent.setInsertDate(LocalDateTime.of(2024, 1, 1, 0, 0));

	Organizer org = new Organizer();
	org.setCustomId(1L);
	org.setName("child");
	org.setNemonic("root/child");
	org.setInsertDate(LocalDateTime.of(2024, 1, 1, 0, 0));
	org.setParentOrganizer(parent);

	OrganizerResponse result = mapper.organizerToOrganizerRespose(org);

	assertNotNull(result.getParentOrganizer());
	assertEquals(50L, result.getParentOrganizer().getId());
    }

    @Test
    void organizerResponseToOrganizer_camposBasicos()
    {

	OrganizerResponse resp = new OrganizerResponse();
	resp.setId(1L);
	resp.setName("org1");
	resp.setDescription("desc");
	resp.setIcon("icon.png");
	resp.setType("FOLDER");
	resp.setNumElements(5);
	resp.setNumOrganizers(3);
	resp.setVersion(2L);
	resp.setNemonic("nem");
	resp.setInsertDate(Instant.parse("2024-06-01T12:00:00Z"));

	Organizer result = mapper.organizerResponseToOrganizer(resp);

	assertEquals(1L, result.getCustomId());
	assertEquals("org1", result.getName());
	assertEquals("desc", result.getDescription());
	assertEquals("FOLDER", result.getOrganizerType());
	assertEquals(5, result.getNumElements());
	assertEquals(3, result.getNumOrganizers());
	assertEquals(2L, result.getVersion());
    }

    @Test
    void organizerResponseToOrganizer_conMetadata_invocaAssetMapper()
    {

	AssetResponse assetResp = new AssetResponse();
	assetResp.setId(10L);

	OrganizerResponse resp = new OrganizerResponse();
	resp.setId(1L);
	resp.setName("org1");
	resp.setNemonic("nem");
	resp.setInsertDate(Instant.parse("2024-01-01T00:00:00Z"));
	resp.setMetadata(assetResp);

	Asset asset = new Asset();
	asset.setCustomId(10L);
	when(assetResponseMapper.assetResponseToAsset(assetResp)).thenReturn(asset);

	Organizer result = mapper.organizerResponseToOrganizer(resp);

	assertNotNull(result.getMetadata());
	assertEquals(10L, result.getMetadata().getCustomId());
    }

}
