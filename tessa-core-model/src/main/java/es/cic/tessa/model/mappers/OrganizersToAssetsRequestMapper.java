package es.cic.tessa.model.mappers;


import java.util.Collection;
import org.springframework.stereotype.Component;
import es.cic.tessa.model.dto.OrganizerRequest;
import es.cic.tessa.model.dto.OrganizersToAssetsRequest;


@Component
public class OrganizersToAssetsRequestMapper
{

    public Collection<OrganizerRequest> getOrganizersFromRequest(OrganizersToAssetsRequest organizersToAssetsRequest)
    {

	return organizersToAssetsRequest.getOrganizers();
    }


    public Collection<Long> getAssetsFromRequest(OrganizersToAssetsRequest organizersToAssetsRequest)
    {

	return organizersToAssetsRequest.getAssetsIds();
    }
}
