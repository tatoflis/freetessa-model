package es.cic.tessa.model.mappers;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.Organizer;
import es.cic.tessa.model.dto.OrganizerResponse;
import es.cic.tessa.model.dto.json.ResponsePageJson;


@Component
public class OrganizerResponseMapper
{

    @Autowired
    private AssetResponseMapper assetResponseMapper;

    public OrganizerResponse organizerToOrganizerRespose(Organizer organizer)
    {

	OrganizerResponse organizerResponse = new OrganizerResponse();
	organizerResponse.setId(organizer.getCustomId());
	organizerResponse.setName(organizer.getName());
	organizerResponse.setNemonic(organizer.getNemonic());
	organizerResponse.setDescription(organizer.getDescription());
	organizerResponse.setIcon(organizer.getIcon());
	organizerResponse.setType(organizer.getOrganizerType());
	organizerResponse.setNumElements(organizer.getNumElements());
	organizerResponse.setNumOrganizers(organizer.getNumOrganizers());
	organizerResponse.setVersion(organizer.getVersion());
	organizerResponse.setNemonic(organizer.getNemonic());
	organizerResponse.setInsertDate(organizer.getInsertDate().toInstant(ZoneOffset.UTC));

	if(organizerResponse.getModifDate() != null)
	{
	    organizerResponse.setModifDate(organizer.getModifDate().toInstant(ZoneOffset.UTC));
	}

	organizerResponse.setVersion(organizer.getVersion());

	if(organizer.getMetadata() != null)
	{
	    organizerResponse.setMetadata(assetResponseMapper.assetToAssetRespose(organizer.getMetadata()));
	}

	if(organizer.getParentOrganizer() != null)
	{
	    OrganizerResponse parentOrganizer = organizerToOrganizerRespose(organizer.getParentOrganizer());

	    organizerResponse.setParentOrganizer(parentOrganizer);

	}

	organizerResponse.setPath(organizer.getPath());

	return organizerResponse;
    }


    public ResponsePage<OrganizerResponse> organizerPageToOrganizerResposePage(ResponsePage<Organizer> organizers)
    {

	LinkedHashSet<OrganizerResponse> organizersResponse = new LinkedHashSet<>();

	for (Organizer organizer : organizers)
	{
	    organizersResponse.add(organizerToOrganizerRespose(organizer));

	}

	return new ResponsePage<>(organizersResponse, organizers.getPageable(), organizers.getTotalElements());

    }


    public Collection<OrganizerResponse> organizerCollectionToOrganizerResposeCollection(Collection<Organizer> organizers)
    {

	Collection<OrganizerResponse> organizersResponse = new ArrayList<>();

	for (Organizer organizer : organizers)
	{
	    organizersResponse.add(organizerToOrganizerRespose(organizer));

	}

	return organizersResponse;

    }


    public ResponsePage<Organizer> organizerCollectionToOrganizerResposePage(ResponsePageJson<OrganizerResponse> organizersResponse)
    {

	List<Organizer> organizers = new ArrayList<>();

	PageRequest pageRequest = PageRequest.of(organizersResponse.getPageable().getPageNumber(), organizersResponse.getPageable().getPageSize());

	for (OrganizerResponse organizerResponse : organizersResponse.getContent())
	{

	    Organizer organizer = organizerResponseToOrganizer(organizerResponse);

	    organizers.add(organizer);
	}

	return new ResponsePage<>(organizers, pageRequest, organizersResponse.getTotalElements());

    }


    public List<Organizer> organizerResponseCollectionToOrganizerList(Collection<OrganizerResponse> organizersResponse)
    {

	List<Organizer> organizers = new ArrayList<>();

	for (OrganizerResponse organizerResponse : organizersResponse)
	{
	    organizers.add(organizerResponseToOrganizer(organizerResponse));

	}

	return organizers;

    }


    public Organizer organizerResponseToOrganizer(OrganizerResponse organizerResponse)
    {

	Organizer organizer = new Organizer();
	organizer.setCustomId(organizerResponse.getId());
	organizer.setOrganizerType(organizerResponse.getType());
	organizer.setIcon(organizerResponse.getIcon());
	organizer.setName(organizerResponse.getName());
	organizer.setNemonic(organizerResponse.getNemonic());
	organizer.setDescription(organizerResponse.getDescription());
	organizer.setNumElements(organizerResponse.getNumElements());
	organizer.setNumOrganizers(organizerResponse.getNumOrganizers());
	organizer.setVersion(organizerResponse.getVersion());
	organizer.setNemonic(organizerResponse.getNemonic());
	organizer.setVersion(organizerResponse.getVersion());
	organizer.setInsertDate(LocalDateTime.ofInstant(organizerResponse.getInsertDate(), ZoneOffset.UTC));

	if(organizerResponse.getModifDate() != null)
	{
	    organizer.setModifDate(LocalDateTime.ofInstant(organizerResponse.getModifDate(), ZoneOffset.UTC));

	}

	if(organizerResponse.getParentOrganizer() != null)
	{
	    organizer.setParentOrganizer(organizerResponseToOrganizer(organizerResponse.getParentOrganizer()));
	}

	if(organizerResponse.getMetadata() != null)
	{
	    organizer.setMetadata(assetResponseMapper.assetResponseToAsset(organizerResponse.getMetadata()));
	}

	return organizer;
    }


    public ResponsePage<Organizer> organizerResponsePageToOrganizerPage(ResponsePage<OrganizerResponse> organizerResponsePage)
    {

	List<Organizer> organizers = new ArrayList<>();

	for (OrganizerResponse organizerResponse : organizerResponsePage)
	{
	    organizers.add(organizerResponseToOrganizer(organizerResponse));

	}

	return new ResponsePage<>(organizers, organizerResponsePage.getPageable(), organizerResponsePage.getTotalElements());

    }


}
