package es.cic.tessa.model.mappers;


import java.util.Set;
import org.springframework.stereotype.Component;
import es.cic.tessa.model.dto.GroupsRequest;


@Component
public class GroupsMapper
{

    public Set<Long> getIds(GroupsRequest groupsRequest)
    {

	return groupsRequest.getIds();
    }


    public Set<String> getGroups(GroupsRequest groupsRequest)
    {

	return groupsRequest.getGroups();
    }

}
