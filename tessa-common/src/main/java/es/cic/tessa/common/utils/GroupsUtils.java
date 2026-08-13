package es.cic.tessa.common.utils;


import java.util.Set;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import es.cic.tessa.common.exceptions.TessaException;
import es.cic.tessa.common.model.TessaConstants;


public class GroupsUtils
{

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupsUtils.class);

    private static final Pattern VALID_LABEL_PATTERN = Pattern.compile("^[a-zA-Z0-9_]+$");

    private GroupsUtils()
    {

    }


    public static String buildGroupsNotSystemAsCypherLabels(Set<String> groups)
    {

	if(groups.stream().anyMatch(group -> group.equalsIgnoreCase(TessaConstants.SYSTEM)))
	{
	    LOGGER.error("Not allowed to unassign System group from template");
	    throw new TessaException("Not allowed to unassign System group from template");
	}

	StringBuilder groupsCypher = new StringBuilder();

	for (String group : groups)
	{
	    validateLabel(group);
	    groupsCypher.append(":");
	    groupsCypher.append(group);
	}

	return groupsCypher.toString();
    }


    public static String buildGroupsAsCypherLabels(Set<String> groups)
    {

	StringBuilder groupsCypher = new StringBuilder();

	for (String group : groups)
	{
	    validateLabel(group);
	    groupsCypher.append(":");
	    groupsCypher.append(group);
	}

	return groupsCypher.toString();
    }


    private static void validateLabel(String label)
    {

	if(label == null || label.isBlank())
	{
	    throw new TessaException("Group label cannot be null or blank");
	}

	if(!VALID_LABEL_PATTERN.matcher(label).matches())
	{
	    LOGGER.error("Invalid group label detected: contains disallowed characters");
	    throw new TessaException("Invalid group label: only alphanumeric characters and underscores are allowed");
	}
    }
}
