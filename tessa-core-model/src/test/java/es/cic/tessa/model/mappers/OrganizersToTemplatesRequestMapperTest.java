package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.dto.AssignOrganizersToTemplatesRequest;
import es.cic.tessa.model.filter.TemplateFilter;

class OrganizersToTemplatesRequestMapperTest
{

    private OrganizersToTemplatesRequestMapper mapper;

    @BeforeEach
    void setUp()
    {

	mapper = new OrganizersToTemplatesRequestMapper();
    }

    @Test
    void getOrganizersFromRequest_devuelveIds()
    {

	AssignOrganizersToTemplatesRequest request = new AssignOrganizersToTemplatesRequest();
	request.setOrganizerIds(List.of(1L, 2L));

	List<Long> result = mapper.getOrganizersFromRequest(request);

	assertEquals(2, result.size());
    }

    @Test
    void getTemplatesFromRequest_devuelveFilter()
    {

	TemplateFilter filter = new TemplateFilter();
	AssignOrganizersToTemplatesRequest request = new AssignOrganizersToTemplatesRequest();
	request.setTemplateFilter(filter);

	TemplateFilter result = mapper.getTemplatesFromRequest(request);

	assertSame(filter, result);
    }
}
