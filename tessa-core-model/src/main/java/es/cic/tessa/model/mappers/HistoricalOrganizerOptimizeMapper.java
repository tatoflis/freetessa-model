package es.cic.tessa.model.mappers;


import es.cic.tessa.model.HistoricalOrganizer;
import es.cic.tessa.model.optimize.OrganizerOptimize;


/**
 * Mapea un {@link HistoricalOrganizer} (snapshot as-of ya resuelto, p.ej. por
 * {@code HistoricalOrganizerService.findOrganizerAsOf}) directamente a {@link OrganizerOptimize},
 * sin pasar por la entidad viva. Mismo alcance de campos que {@link OrganizerOptimizeMapper} con
 * la entidad viva: {@code historicalMetadata} se mapea vía {@link HistoricalAssetOptimizeMapper},
 * que no desciende a {@code historicalOrganizers} del asset — evita así el ciclo
 * {@code Organizer → metadata(Asset) → organizers} sin guarda de recursión adicional.
 */
public final class HistoricalOrganizerOptimizeMapper
{

    private HistoricalOrganizerOptimizeMapper()
    {

    }


    public static OrganizerOptimize toOptimize(HistoricalOrganizer historicalOrganizer)
    {

	if(historicalOrganizer == null)
	{
	    return null;
	}

	OrganizerOptimize optimize = new OrganizerOptimize();
	optimize.setId(historicalOrganizer.getCustomId());
	optimize.setName(historicalOrganizer.getName());
	optimize.setGroups(historicalOrganizer.getGroups());

	if(historicalOrganizer.getHistoricalParentOrganizer() != null)
	{
	    OrganizerOptimize parentOptimize = new OrganizerOptimize();
	    parentOptimize.setId(historicalOrganizer.getHistoricalParentOrganizer().getCustomId());
	    parentOptimize.setName(historicalOrganizer.getHistoricalParentOrganizer().getName());
	    parentOptimize.setGroups(historicalOrganizer.getHistoricalParentOrganizer().getGroups());
	    optimize.setParentOrganizerOptimize(parentOptimize);
	}

	if(historicalOrganizer.getHistoricalMetadata() != null)
	{
	    optimize.setMetadataOptimize(HistoricalAssetOptimizeMapper.toOptimize(historicalOrganizer.getHistoricalMetadata()));
	}

	return optimize;
    }
}
