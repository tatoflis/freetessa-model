package es.cic.tessa.model.mappers;


import es.cic.tessa.model.HistoricalTemplate;
import es.cic.tessa.model.optimize.TemplateOptimize;


/**
 * Mapea un {@link HistoricalTemplate} (snapshot as-of ya resuelto, p.ej. por
 * {@code HistoricalTemplateService.findTemplateAsOf}) directamente a {@link TemplateOptimize}, sin
 * pasar por la entidad viva. Mismo alcance de campos que {@link TemplateOptimizeMapper} con la
 * entidad viva: {@code TemplateOptimize} no transporta la lista de atributos (solo la cadena
 * EXTENDS), así que este mapper tampoco la necesita.
 */
public final class HistoricalTemplateOptimizeMapper
{

    private HistoricalTemplateOptimizeMapper()
    {

    }


    public static TemplateOptimize toOptimize(HistoricalTemplate historicalTemplate)
    {

	if(historicalTemplate == null)
	{
	    return null;
	}

	TemplateOptimize optimize = toOptimizeThin(historicalTemplate);

	if(historicalTemplate.getHistoricalExtendsTemplate() != null)
	{
	    optimize.setTemplateExtendsOptimize(toOptimizeThin(historicalTemplate.getHistoricalExtendsTemplate()));
	}

	return optimize;
    }


    /**
     * Variante sin {@code templateExtendsOptimize}, para los llamantes (p.ej.
     * {@code HistoricalAssetOptimizeMapper}) que solo necesitan identificar la template sin
     * recorrer su cadena de herencia.
     */
    public static TemplateOptimize toOptimizeThin(HistoricalTemplate historicalTemplate)
    {

	if(historicalTemplate == null)
	{
	    return null;
	}

	TemplateOptimize optimize = new TemplateOptimize();
	optimize.setId(historicalTemplate.getCustomId());
	optimize.setName(historicalTemplate.getName());
	optimize.setGroups(historicalTemplate.getGroups());
	optimize.setType(historicalTemplate.getType());

	return optimize;
    }
}
