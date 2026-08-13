package es.cic.tessa.model.mappers;


import java.util.List;
import org.springframework.stereotype.Component;
import es.cic.tessa.model.dto.AssignOrganizersToTemplatesRequest;
import es.cic.tessa.model.filter.TemplateFilter;


@Component
public class OrganizersToTemplatesRequestMapper
{

    public List<Long> getOrganizersFromRequest(AssignOrganizersToTemplatesRequest organizersToTemplatesRequest)
    {

	return organizersToTemplatesRequest.getOrganizerIds();
    }


    public TemplateFilter getTemplatesFromRequest(AssignOrganizersToTemplatesRequest organizersToTemplatesRequest)
    {

	return organizersToTemplatesRequest.getTemplateFilter();
    }
}
