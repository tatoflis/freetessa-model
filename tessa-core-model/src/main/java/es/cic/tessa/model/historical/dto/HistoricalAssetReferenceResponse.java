package es.cic.tessa.model.historical.dto;


import java.util.Objects;
import es.cic.tessa.model.dto.support.AbstractIdentificableEntityResponse;


public class HistoricalAssetReferenceResponse extends AbstractIdentificableEntityResponse
{

    private static final long serialVersionUID = 1L;

    private HistoricalAssetResponse historicalAssetResponse;

    private String referenceType;

    private String relationType;

    public HistoricalAssetResponse getHistoricalAssetResponse()
    {

	return historicalAssetResponse;
    }


    public void setHistoricalAssetResponse(HistoricalAssetResponse historicalAssetResponse)
    {

	this.historicalAssetResponse = historicalAssetResponse;
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


    @Override
    public int hashCode()
    {

	return Objects.hash(historicalAssetResponse, referenceType, relationType);
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
	HistoricalAssetReferenceResponse other = (HistoricalAssetReferenceResponse) obj;
	return Objects.equals(historicalAssetResponse, other.historicalAssetResponse) && Objects.equals(referenceType, other.referenceType) && Objects.equals(relationType, other.relationType);
    }


    @Override
    public String toString()
    {

	return "HistoricalAssetReferenceResponse [historicalAssetResponse=" + historicalAssetResponse + ", referenceType=" + referenceType + ", relationType=" + relationType + "]";
    }

}
