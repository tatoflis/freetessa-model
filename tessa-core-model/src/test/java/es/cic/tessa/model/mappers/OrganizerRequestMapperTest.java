package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.Organizer;
import es.cic.tessa.model.dto.OrganizerRequest;

class OrganizerRequestMapperTest
{

    private OrganizerRequestMapper mapper;

    @BeforeEach
    void setUp()
    {

	mapper = new OrganizerRequestMapper();
    }

    @Test
    void organizerRequestToOrganizer_mapeaCamposBasicos()
    {

	OrganizerRequest request = new OrganizerRequest();
	request.setId(1L);
	request.setName("org-name");
	request.setDescription("desc");
	request.setIcon("icon.png");
	request.setType("ASSET_ORGANIZER");
	request.setVersion(2L);
	request.setModifDate(LocalDateTime.of(2025, 6, 1, 12, 0));

	Set<String> groups = Set.of("g1");
	Organizer result = mapper.organizerRequestToOrganizer(request, groups);

	assertEquals(1L, result.getCustomId());
	assertEquals("org-name", result.getName());
	assertEquals("org-name", result.getNameLower());
	assertEquals("desc", result.getDescription());
	assertEquals("icon.png", result.getIcon());
	assertEquals("ASSET_ORGANIZER", result.getOrganizerType());
	assertEquals(2L, result.getVersion());
	assertEquals(LocalDateTime.of(2025, 6, 1, 12, 0), result.getModifDate());
	assertTrue(result.getGroups().contains("g1"));
    }

    @Test
    void organizerRequestCollectionToOrganizerCollection_mapeaColeccion()
    {

	OrganizerRequest r1 = new OrganizerRequest();
	r1.setName("org1");

	OrganizerRequest r2 = new OrganizerRequest();
	r2.setName("org2");

	Collection<Organizer> result = mapper.organizerRequestCollectionToOrganizerCollection(List.of(r1, r2), Set.of("g"));

	assertEquals(2, result.size());
    }
}
