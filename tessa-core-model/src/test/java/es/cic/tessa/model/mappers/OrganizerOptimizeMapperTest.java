package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.Asset;
import es.cic.tessa.model.Organizer;
import es.cic.tessa.model.optimize.OrganizerOptimize;

class OrganizerOptimizeMapperTest
{

    @Test
    void toOptimize_conNull_devuelveNull()
    {

	assertNull(OrganizerOptimizeMapper.toOptimize(null));
    }

    @Test
    void toOptimize_conCamposBasicos_mapeaCorrectamente()
    {

	Organizer org = new Organizer();
	org.setCustomId(1L);
	org.setName("org-name");
	org.setGroups(Set.of("g1"));

	OrganizerOptimize result = OrganizerOptimizeMapper.toOptimize(org);

	assertNotNull(result);
	assertEquals(1L, result.getId());
	assertEquals("org-name", result.getName());
	assertTrue(result.getGroups().contains("g1"));
    }

    @Test
    void toOptimize_conParentOrganizer_mapeaParentOptimize()
    {

	Organizer parent = new Organizer();
	parent.setCustomId(10L);
	parent.setName("parent-org");
	parent.setGroups(Set.of("pg"));

	Organizer org = new Organizer();
	org.setCustomId(1L);
	org.setName("child-org");
	org.setParentOrganizer(parent);

	OrganizerOptimize result = OrganizerOptimizeMapper.toOptimize(org);

	assertNotNull(result.getParentOrganizerOptimize());
	assertEquals(10L, result.getParentOrganizerOptimize().getId());
	assertEquals("parent-org", result.getParentOrganizerOptimize().getName());
    }

    @Test
    void toOptimize_sinParentOrganizer_parentOptimizeEsNull()
    {

	Organizer org = new Organizer();
	org.setCustomId(1L);
	org.setName("org");

	OrganizerOptimize result = OrganizerOptimizeMapper.toOptimize(org);

	assertNull(result.getParentOrganizerOptimize());
    }

    @Test
    void toOptimize_conMetadata_mapeaMetadataOptimize()
    {

	Asset metadata = new Asset();
	metadata.setCustomId(20L);
	metadata.setName("meta-asset");
	metadata.setGroups(Set.of("mg"));

	Organizer org = new Organizer();
	org.setCustomId(1L);
	org.setName("org");
	org.setMetadata(metadata);

	OrganizerOptimize result = OrganizerOptimizeMapper.toOptimize(org);

	assertNotNull(result.getMetadataOptimize());
	assertEquals(20L, result.getMetadataOptimize().getId());
	assertEquals("meta-asset", result.getMetadataOptimize().getName());
    }

    @Test
    void toOptimize_sinMetadata_metadataOptimizeEsNull()
    {

	Organizer org = new Organizer();
	org.setCustomId(1L);
	org.setName("org");

	OrganizerOptimize result = OrganizerOptimizeMapper.toOptimize(org);

	assertNull(result.getMetadataOptimize());
    }
}
