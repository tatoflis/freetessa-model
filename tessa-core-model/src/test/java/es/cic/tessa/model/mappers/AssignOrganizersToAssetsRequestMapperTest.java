package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.dto.AssignOrganizersToAssetsRequest;
import es.cic.tessa.model.filter.AssetFilter;

class AssignOrganizersToAssetsRequestMapperTest
{

    private AssignOrganizersToAssetsRequestMapper mapper;

    @BeforeEach
    void setUp()
    {

	mapper = new AssignOrganizersToAssetsRequestMapper();
    }

    @Test
    void getOrganizersFromRequest_devuelveIds()
    {

	AssignOrganizersToAssetsRequest request = new AssignOrganizersToAssetsRequest();
	request.setOrganizerIds(List.of(1L, 2L, 3L));

	Collection<Long> result = mapper.getOrganizersFromRequest(request);

	assertEquals(3, result.size());
	assertTrue(result.contains(1L));
    }

    @Test
    void getAssetsFromRequest_devuelveAssetFilter()
    {

	AssetFilter filter = new AssetFilter();
	AssignOrganizersToAssetsRequest request = new AssignOrganizersToAssetsRequest();
	request.setAssetFilter(filter);

	AssetFilter result = mapper.getAssetsFromRequest(request);

	assertSame(filter, result);
    }
}
