package es.cic.tessa.model.historical.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import es.cic.tessa.model.HistoricalTemplateReference;
import es.cic.tessa.model.historical.dto.HistoricalTemplateReferenceResponse;


@Component
public class HistoricalTemplateReferenceResponseMapper
{

    @Autowired
    private HistoricalTemplateResponseMapper historicalTemplateResponseMapper;

    public HistoricalTemplateReferenceResponse historicalTemplateReferenceToHistoricalTemplateReferenceRespose(HistoricalTemplateReference historicalTemplateReference)
    {

	HistoricalTemplateReferenceResponse historicalTemplateReferenceResponse = new HistoricalTemplateReferenceResponse();

	historicalTemplateReferenceResponse.setNodeId(historicalTemplateReference.getId());
	historicalTemplateReferenceResponse.setReferenceType(historicalTemplateReference.getReferenceType());
	historicalTemplateReferenceResponse.setRelationType(historicalTemplateReference.getRelationType());

	if(historicalTemplateReference.getHistoricalTemplate() != null)
	{
	    historicalTemplateReferenceResponse.setHistoricalTemplate(historicalTemplateResponseMapper.historicalTemplateTohistoricalTemplateRespose(historicalTemplateReference.getHistoricalTemplate()));
	}

	return historicalTemplateReferenceResponse;
    }

}
