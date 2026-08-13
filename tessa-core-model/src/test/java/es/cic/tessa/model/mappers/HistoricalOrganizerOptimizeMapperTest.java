package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.HistoricalAsset;
import es.cic.tessa.model.HistoricalOrganizer;
import es.cic.tessa.model.optimize.OrganizerOptimize;

class HistoricalOrganizerOptimizeMapperTest
{

    @Test
    void toOptimize_conNull_devuelveNull()
    {

	assertNull(HistoricalOrganizerOptimizeMapper.toOptimize(null));
    }

    @Test
    void toOptimize_conCamposBasicos_mapeaCorrectamente()
    {

	HistoricalOrganizer organizer = new HistoricalOrganizer();
	organizer.setCustomId(1L);
	organizer.setName("org-name");
	organizer.setGroups(Set.of("g1"));

	OrganizerOptimize result = HistoricalOrganizerOptimizeMapper.toOptimize(organizer);

	assertNotNull(result);
	assertEquals(1L, result.getId());
	assertEquals("org-name", result.getName());
    }

    @Test
    void toOptimize_conParentOrganizer_mapeaParentOrganizerOptimize()
    {

	HistoricalOrganizer parent = new HistoricalOrganizer();
	parent.setCustomId(2L);
	parent.setName("parent-org");
	parent.setGroups(Set.of("g"));

	HistoricalOrganizer organizer = new HistoricalOrganizer();
	organizer.setCustomId(1L);
	organizer.setName("org-name");
	organizer.setHistoricalParentOrganizer(parent);

	OrganizerOptimize result = HistoricalOrganizerOptimizeMapper.toOptimize(organizer);

	assertNotNull(result.getParentOrganizerOptimize());
	assertEquals(2L, result.getParentOrganizerOptimize().getId());
	assertEquals("parent-org", result.getParentOrganizerOptimize().getName());
    }

    @Test
    void toOptimize_sinParentOrganizer_parentOrganizerOptimizeEsNull()
    {

	HistoricalOrganizer organizer = new HistoricalOrganizer();
	organizer.setCustomId(1L);
	organizer.setName("org-name");

	OrganizerOptimize result = HistoricalOrganizerOptimizeMapper.toOptimize(organizer);

	assertNull(result.getParentOrganizerOptimize());
    }

    @Test
    void toOptimize_conMetadata_mapeaMetadataOptimizeSinDescenderAOrganizers()
    {

	HistoricalAsset metadata = new HistoricalAsset();
	metadata.setCustomId(10L);
	metadata.setName("metadata-asset");
	metadata.setGroups(Set.of("g"));

	HistoricalOrganizer organizer = new HistoricalOrganizer();
	organizer.setCustomId(1L);
	organizer.setName("org-name");
	organizer.setHistoricalMetadata(metadata);

	OrganizerOptimize result = HistoricalOrganizerOptimizeMapper.toOptimize(organizer);

	assertNotNull(result.getMetadataOptimize());
	assertEquals(10L, result.getMetadataOptimize().getId());
	assertEquals("metadata-asset", result.getMetadataOptimize().getName());
    }

    @Test
    void toOptimize_sinMetadata_metadataOptimizeEsNull()
    {

	HistoricalOrganizer organizer = new HistoricalOrganizer();
	organizer.setCustomId(1L);
	organizer.setName("org-name");

	OrganizerOptimize result = HistoricalOrganizerOptimizeMapper.toOptimize(organizer);

	assertNull(result.getMetadataOptimize());
    }
}
