package es.cic.tessa.model.dto;


import java.util.HashSet;
import java.util.Set;


public class GroupsRequest
{

    private Set<Long> ids = new HashSet<>();

    private Set<String> groups = new HashSet<>();

    public Set<Long> getIds()
    {

	return ids;
    }


    public void setIds(Set<Long> ids)
    {

	this.ids = ids;
    }


    public Set<String> getGroups()
    {

	return groups;
    }


    public void setGroups(Set<String> groups)
    {

	this.groups = groups;
    }
}
