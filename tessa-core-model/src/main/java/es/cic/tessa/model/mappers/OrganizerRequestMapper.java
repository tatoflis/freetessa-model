package es.cic.tessa.model.mappers;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import es.cic.tessa.model.Organizer;
import es.cic.tessa.model.dto.OrganizerRequest;


@Component
public class OrganizerRequestMapper
{

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizerRequestMapper.class);

    public Organizer organizerRequestToOrganizer(OrganizerRequest organizerRequest, Set<String> groups)
    {

	LOGGER.trace("Organizer request incoming ... {}", organizerRequest);

	Organizer organizer = new Organizer();
	organizer.setCustomId(organizerRequest.getId());
	organizer.setName(organizerRequest.getName());
	organizer.setNameLower(organizerRequest.getName().toLowerCase());
	organizer.setDescription(organizerRequest.getDescription());
	organizer.setIcon(organizerRequest.getIcon());
	organizer.setOrganizerType(organizerRequest.getType());
	organizer.setGroups(groups);

	if(organizerRequest.getVersion() != null)
	{
	    organizer.setVersion(organizerRequest.getVersion());
	}

	if(organizerRequest.getModifDate() != null)
	{
	    organizer.setModifDate(organizerRequest.getModifDate());
	}

	return organizer;
    }


    public Collection<Organizer> organizerRequestCollectionToOrganizerCollection(Collection<OrganizerRequest> organizersRequest, Set<String> groups)
    {

	Collection<Organizer> organizers = new HashSet<>();

	for (OrganizerRequest organizerRequest : organizersRequest)
	{
	    organizers.add(organizerRequestToOrganizer(organizerRequest, groups));
	}

	return organizers;

    }

}
