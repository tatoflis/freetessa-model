package es.cic.tessa.model.historical.mapper;


import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.HistoricalOrganizer;
import es.cic.tessa.model.historical.dto.HistoricalChangeResponse;
import es.cic.tessa.model.historical.dto.HistoricalOrganizerResponse;


@Component
public class HistoricalOrganizerResponseMapper
{

    @Autowired
    private HistoricalAssetResponseMapper historicalAssetResponseMapper;

    @Autowired
    private HistoricalChangeResponseMapper historicalChangeResponseMapper;

    public HistoricalOrganizerResponse historicalOrganizerToHistoricalOrganizerRespose(HistoricalOrganizer historicalOrganizer)
    {

	HistoricalOrganizerResponse historicalOrganizerResponse = new HistoricalOrganizerResponse();
	historicalOrganizerResponse.setId(historicalOrganizer.getCustomId());
	historicalOrganizerResponse.setName(historicalOrganizer.getName());
	historicalOrganizerResponse.setNemonic(historicalOrganizer.getNemonic());
	historicalOrganizerResponse.setDescription(historicalOrganizer.getDescription());
	historicalOrganizerResponse.setIcon(historicalOrganizer.getIcon());
	historicalOrganizerResponse.setType(historicalOrganizer.getOrganizerType());
	historicalOrganizerResponse.setPath(historicalOrganizer.getPath());
	historicalOrganizerResponse.setNumElements(historicalOrganizer.getNumElements());
	historicalOrganizerResponse.setNumOrganizers(historicalOrganizer.getNumOrganizers());
	historicalOrganizerResponse.setVersion(historicalOrganizer.getVersion());
	historicalOrganizerResponse.setNemonic(historicalOrganizer.getNemonic());
	historicalOrganizerResponse.setInsertDate(historicalOrganizer.getInsertDate().toInstant(ZoneOffset.UTC));

	if(historicalOrganizer.getModifDate() != null)
	{
	    historicalOrganizerResponse.setModifDate(historicalOrganizer.getModifDate().toInstant(ZoneOffset.UTC));
	}

	if(historicalOrganizer.getHistoricalMetadata() != null)
	{
	    historicalOrganizerResponse.setHistoricalMetadata(historicalAssetResponseMapper.historicalAssetToHistoricalAssetRespose(historicalOrganizer.getHistoricalMetadata()));
	}

	if(historicalOrganizer.getHistoricalParentOrganizer() != null)
	{

	    historicalOrganizerResponse.setHistoricalParentOrganizer(historicalOrganizerToHistoricalOrganizerRespose(historicalOrganizer.getHistoricalParentOrganizer()));

	}

	HistoricalChangeResponse historicalChangeResponse = historicalOrganizer.getHistoricalChange() != null
		? historicalChangeResponseMapper.historicalChangeToHistoricalChangeRespose(historicalOrganizer.getHistoricalChange())
		: new HistoricalChangeResponse();

	historicalChangeResponse.setOperation(historicalOrganizer.getHistoricalChangeOperation());
	historicalOrganizerResponse.setHistoricalChange(historicalChangeResponse);

	return historicalOrganizerResponse;
    }


    public Collection<HistoricalOrganizerResponse> historicalOrganizerCollectionToHistoricalOrganizerResposeCollection(Collection<HistoricalOrganizer> historicalOrganizers)
    {

	Collection<HistoricalOrganizerResponse> historicalOrganizersResponse = new ArrayList<>();

	for (HistoricalOrganizer historicalOrganizer : historicalOrganizers)
	{
	    historicalOrganizersResponse.add(historicalOrganizerToHistoricalOrganizerRespose(historicalOrganizer));

	}

	return historicalOrganizersResponse;

    }


    public ResponsePage<HistoricalOrganizerResponse> historicalOrganizerPageToHistoricalOrganizerResposePage(ResponsePage<HistoricalOrganizer> historicalOrganizers)
    {

	List<HistoricalOrganizerResponse> historicalOrganizerResponse = new ArrayList<>();

	for (HistoricalOrganizer historicalOrganizer : historicalOrganizers)
	{
	    historicalOrganizerResponse.add(historicalOrganizerToHistoricalOrganizerRespose(historicalOrganizer));

	}

	return new ResponsePage<>(historicalOrganizerResponse, historicalOrganizers.getPageable(), historicalOrganizers.getTotalElements());

    }
}
