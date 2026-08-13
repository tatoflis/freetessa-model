package es.cic.tessa.rest.conf;


import java.util.Set;


public class AbtractClientConfig
{

    protected String buildGroups(Set<String> groups)
    {

	if(groups == null || groups.isEmpty())
	{
	    return "";
	}

	StringBuilder groupsAsString = new StringBuilder();

	for (String group : groups)
	{
	    groupsAsString.append("groups=").append(group).append("&");
	}

	return groupsAsString.substring(0, groupsAsString.length() - 1);
    }
}
