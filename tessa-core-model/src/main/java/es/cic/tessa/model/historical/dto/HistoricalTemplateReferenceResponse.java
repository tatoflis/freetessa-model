package es.cic.tessa.model.historical.dto;


import java.util.Objects;
import es.cic.tessa.model.dto.support.AbstractIdentificableEntityResponse;


public class HistoricalTemplateReferenceResponse extends AbstractIdentificableEntityResponse
{

    private static final long serialVersionUID = 1L;

    private Long nodeId;

    private String referenceType;

    private String relationType;

    private HistoricalTemplateResponse historicalTemplate;

    public Long getNodeId()
    {

	return nodeId;
    }


    public void setNodeId(Long nodeId)
    {

	this.nodeId = nodeId;
    }


    public String getReferenceType()
    {

	return referenceType;
    }


    public void setReferenceType(String referenceType)
    {

	this.referenceType = referenceType;
    }


    public String getRelationType()
    {

	return relationType;
    }


    public void setRelationType(String relationType)
    {

	this.relationType = relationType;
    }


    public HistoricalTemplateResponse getHistoricalTemplate()
    {

	return historicalTemplate;
    }


    public void setHistoricalTemplate(HistoricalTemplateResponse historicalTemplate)
    {

	this.historicalTemplate = historicalTemplate;
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(historicalTemplate, nodeId, referenceType, relationType);
    }


    @Override
    public boolean equals(Object obj)
    {

	if(this == obj)
	    return true;
	if(obj == null)
	    return false;
	if(getClass() != obj.getClass())
	    return false;
	HistoricalTemplateReferenceResponse other = (HistoricalTemplateReferenceResponse) obj;
	return Objects.equals(historicalTemplate, other.historicalTemplate) && Objects.equals(nodeId, other.nodeId) && Objects.equals(referenceType, other.referenceType) && Objects.equals(relationType, other.relationType);
    }


    @Override
    public String toString()
    {

	return "HistoricalTemplateReferenceResponse [nodeId=" + nodeId + ", referenceType=" + referenceType + ", relationType=" + relationType + ", historicalTemplate=" + historicalTemplate + "]";
    }

}
