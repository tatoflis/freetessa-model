package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.dto.OrganizerRequest;
import es.cic.tessa.model.dto.OrganizersToAssetsRequest;

class OrganizersToAssetsRequestMapperTest
{

    private OrganizersToAssetsRequestMapper mapper;

    @BeforeEach
    void setUp()
    {

	mapper = new OrganizersToAssetsRequestMapper();
    }

    @Test
    void getOrganizersFromRequest_devuelveOrganizers()
    {

	OrganizerRequest org1 = new OrganizerRequest();
	org1.setName("org1");

	OrganizersToAssetsRequest request = new OrganizersToAssetsRequest();
	request.setOrganizers(List.of(org1));

	Collection<OrganizerRequest> result = mapper.getOrganizersFromRequest(request);

	assertEquals(1, result.size());
    }

    @Test
    void getAssetsFromRequest_devuelveIds()
    {

	OrganizersToAssetsRequest request = new OrganizersToAssetsRequest();
	request.setAssetsIds(List.of(10L, 20L));

	Collection<Long> result = mapper.getAssetsFromRequest(request);

	assertEquals(2, result.size());
	assertTrue(result.contains(10L));
    }
}
