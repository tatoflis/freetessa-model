package es.cic.tessa.model.mappers;


import es.cic.tessa.model.Organizer;
import es.cic.tessa.model.optimize.AssetOptimize;
import es.cic.tessa.model.optimize.OrganizerOptimize;


public final class OrganizerOptimizeMapper
{

    private OrganizerOptimizeMapper()
    {

    }


    public static OrganizerOptimize toOptimize(Organizer organizer)
    {

	if(organizer == null)
	{
	    return null;
	}

	OrganizerOptimize optimize = new OrganizerOptimize();
	optimize.setId(organizer.getCustomId());
	optimize.setName(organizer.getName());
	optimize.setGroups(organizer.getGroups());

	if(organizer.getParentOrganizer() != null)
	{
	    OrganizerOptimize parentOptimize = new OrganizerOptimize();
	    parentOptimize.setId(organizer.getParentOrganizer().getCustomId());
	    parentOptimize.setName(organizer.getParentOrganizer().getName());
	    parentOptimize.setGroups(organizer.getParentOrganizer().getGroups());
	    optimize.setParentOrganizerOptimize(parentOptimize);
	}

	if(organizer.getMetadata() != null)
	{
	    AssetOptimize metadataOptimize = new AssetOptimize();
	    metadataOptimize.setId(organizer.getMetadata().getCustomId());
	    metadataOptimize.setName(organizer.getMetadata().getName());
	    metadataOptimize.setGroups(organizer.getMetadata().getGroups());
	    optimize.setMetadataOptimize(metadataOptimize);
	}

	return optimize;
    }
}
