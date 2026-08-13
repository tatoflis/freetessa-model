package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.dto.GroupsRequest;

class GroupsMapperTest
{

    private GroupsMapper mapper;

    @BeforeEach
    void setUp()
    {

	mapper = new GroupsMapper();
    }

    @Test
    void getIds_devuelveIdsDelRequest()
    {

	GroupsRequest request = new GroupsRequest();
	request.setIds(Set.of(1L, 2L, 3L));

	Set<Long> result = mapper.getIds(request);

	assertEquals(3, result.size());
	assertTrue(result.containsAll(Set.of(1L, 2L, 3L)));
    }

    @Test
    void getGroups_devuelveGroupsDelRequest()
    {

	GroupsRequest request = new GroupsRequest();
	request.setGroups(Set.of("g1", "g2"));

	Set<String> result = mapper.getGroups(request);

	assertEquals(2, result.size());
	assertTrue(result.containsAll(Set.of("g1", "g2")));
    }
}
