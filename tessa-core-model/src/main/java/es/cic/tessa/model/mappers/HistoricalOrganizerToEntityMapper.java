package es.cic.tessa.model.mappers;


import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import es.cic.tessa.model.HistoricalOrganizer;
import es.cic.tessa.model.Organizer;


@Component
public class HistoricalOrganizerToEntityMapper
{

    @Lazy
    @Autowired
    private HistoricalAssetToEntityMapper historicalAssetToEntityMapper;


    public Organizer historicalOrganizerToOrganizer(HistoricalOrganizer historicalOrganizer)
    {

	if(historicalOrganizer == null)
	{
	    return null;
	}

	Organizer organizer = new Organizer();

	// Campos compartidos via TessaElement/IdentificableElement/LockableElement/DateTimeElement
	organizer.setCustomId(historicalOrganizer.getCustomId());
	organizer.setId(historicalOrganizer.getId());
	organizer.setName(historicalOrganizer.getName());
	organizer.setNameLower(historicalOrganizer.getNameLower());
	organizer.setDescription(historicalOrganizer.getDescription());
	organizer.setNemonic(historicalOrganizer.getNemonic());
	organizer.setGroups(historicalOrganizer.getGroups());
	organizer.setIcon(historicalOrganizer.getIcon());
	organizer.setVersion(historicalOrganizer.getVersion());
	organizer.setInsertDate(historicalOrganizer.getInsertDate());
	organizer.setModifDate(historicalOrganizer.getModifDate());

	// Campos propios de Organizer
	organizer.setOrganizerType(historicalOrganizer.getOrganizerType());
	organizer.setNumElements(historicalOrganizer.getNumElements());
	organizer.setNumOrganizers(historicalOrganizer.getNumOrganizers());

	organizer.setPath(historicalOrganizer.getPath());

	// Relaciones anidadas
	organizer.setMetadata(historicalAssetToEntityMapper.historicalAssetToAsset(historicalOrganizer.getHistoricalMetadata()));
	organizer.setParentOrganizer(historicalOrganizerToOrganizer(historicalOrganizer.getHistoricalParentOrganizer()));

	return organizer;
    }


    public List<Organizer> historicalOrganizerListToOrganizerList(List<HistoricalOrganizer> historicalOrganizers)
    {

	if(historicalOrganizers == null)
	{
	    return List.of();
	}

	return historicalOrganizers.stream()
		.map(this::historicalOrganizerToOrganizer)
		.collect(Collectors.toList());
    }

}
