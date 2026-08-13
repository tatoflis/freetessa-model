package es.cic.tessa.model.assembly;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Value;
import org.neo4j.driver.internal.value.NullValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import es.cic.tessa.common.model.Labels;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.asset.exceptions.AssetException;
import es.cic.tessa.model.optimize.AssetOptimize;
import es.cic.tessa.model.optimize.OrganizerOptimize;


public class AssemblyOrganizerOptimize
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AssemblyOrganizerOptimize.class);

    public static ResponsePage<OrganizerOptimize> buildOrganizerOptimize(Result result)
    {

	List<OrganizerOptimize> organizers = new ArrayList<>();

	try
	{

	    while (result.hasNext())
	    {
		Record row = result.next();

		OrganizerOptimize organizerOptimize = new OrganizerOptimize();
		organizerOptimize.setId(row.get("id").asLong());
		organizerOptimize.setName(row.get("name").asString());

		Set<String> nodeGroups = new HashSet<>();

		for (Object label : row.get("groups").asList())
		{
		    if(!label.equals(Labels.ORGANIZER))
		    {
			nodeGroups.add(label.toString());
		    }

		}

		organizerOptimize.setGroups(nodeGroups);

		Value parentNode = row.get("parentOrganizerOptimize");

		OrganizerOptimize parentOrganizerOptimize = new OrganizerOptimize();
		parentOrganizerOptimize.setId(parentNode.get("id").asLong());
		parentOrganizerOptimize.setName(parentNode.get("name").asString());

		Set<String> parentGroups = new HashSet<>();

		for (Object label : parentNode.get("groups").asList())
		{
		    if(!label.equals(Labels.ORGANIZER))
		    {
			parentGroups.add(label.toString());
		    }

		}

		parentOrganizerOptimize.setGroups(parentGroups);

		organizerOptimize.setParentOrganizerOptimize(parentOrganizerOptimize);

		Value metadataNode = row.get("metadataOptimize");

		if(!metadataNode.equals(NullValue.NULL))
		{
		    AssetOptimize assetMetadataOptimize = new AssetOptimize();

		    assetMetadataOptimize.setId(metadataNode.get("id").asLong());
		    assetMetadataOptimize.setName(metadataNode.get("name").asString());
		    assetMetadataOptimize.setIdentificator(metadataNode.get("identificator").asString());

		    Set<String> metadataGroups = new HashSet<>();

		    for (Object label : metadataNode.get("groups").asList())
		    {
			if(!label.equals(Labels.ASSET))
			{
			    metadataGroups.add(label.toString());
			}

		    }

		    assetMetadataOptimize.setGroups(metadataGroups);

		    organizerOptimize.setMetadataOptimize(assetMetadataOptimize);
		}

		organizers.add(organizerOptimize);
	    }
	}
	catch (Exception e)
	{
	    LOGGER.error("Error assembling optimize organizers!", e);
	    throw new AssetException("Error assembling optimize organizers! ", e);
	}

	return new ResponsePage<>(organizers);
    }

}
