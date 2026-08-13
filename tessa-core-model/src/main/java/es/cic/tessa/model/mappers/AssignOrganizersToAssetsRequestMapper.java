package es.cic.tessa.model.mappers;


import java.util.Collection;
import org.springframework.stereotype.Component;
import es.cic.tessa.model.dto.AssignOrganizersToAssetsRequest;
import es.cic.tessa.model.filter.AssetFilter;


@Component
public class AssignOrganizersToAssetsRequestMapper
{

    public Collection<Long> getOrganizersFromRequest(AssignOrganizersToAssetsRequest organizersToAssetsRequest)
    {

	return organizersToAssetsRequest.getOrganizerIds();
    }


    public AssetFilter getAssetsFromRequest(AssignOrganizersToAssetsRequest organizersToAssetsRequest)
    {

	return organizersToAssetsRequest.getAssetFilter();
    }
}
