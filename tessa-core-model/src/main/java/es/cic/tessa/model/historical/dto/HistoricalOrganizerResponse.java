package es.cic.tessa.model.historical.dto;


import java.util.Objects;
import es.cic.tessa.model.dto.support.AbstractEntityResponse;
import es.cic.tessa.model.types.OrganizerType;


public class HistoricalOrganizerResponse extends AbstractEntityResponse
{

    private static final long serialVersionUID = 1L;

    private String type = OrganizerType.ASSET_ORGANIZER.getCode();

    private String path;

    private HistoricalAssetResponse historicalMetadata;

    private HistoricalOrganizerResponse historicalParentOrganizer;

    private HistoricalChangeResponse historicalChange;

    private Integer numElements;

    private Integer numOrganizers;

    public String getType()
    {

	return type;
    }


    public void setType(String type)
    {

	this.type = type;
    }


    public String getPath()
    {

	return path;
    }


    public void setPath(String path)
    {

	this.path = path;
    }


    public HistoricalAssetResponse getHistoricalMetadata()
    {

	return historicalMetadata;
    }


    public void setHistoricalMetadata(HistoricalAssetResponse historicalMetadata)
    {

	this.historicalMetadata = historicalMetadata;
    }


    public HistoricalOrganizerResponse getHistoricalParentOrganizer()
    {

	return historicalParentOrganizer;
    }


    public void setHistoricalParentOrganizer(HistoricalOrganizerResponse historicalParentOrganizer)
    {

	this.historicalParentOrganizer = historicalParentOrganizer;
    }


    public HistoricalChangeResponse getHistoricalChange()
    {

	return historicalChange;
    }


    public void setHistoricalChange(HistoricalChangeResponse historicalChange)
    {

	this.historicalChange = historicalChange;
    }


    public Integer getNumElements()
    {

	return numElements;
    }


    public void setNumElements(Integer numElements)
    {

	this.numElements = numElements;
    }


    public Integer getNumOrganizers()
    {

	return numOrganizers;
    }


    public void setNumOrganizers(Integer numOrganizers)
    {

	this.numOrganizers = numOrganizers;
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(historicalChange, historicalMetadata, historicalParentOrganizer, numElements, numOrganizers, type, path);
	return result;
    }


    @Override
    public boolean equals(Object obj)
    {

	if(this == obj)
	    return true;
	if(!super.equals(obj))
	    return false;
	if(getClass() != obj.getClass())
	    return false;
	HistoricalOrganizerResponse other = (HistoricalOrganizerResponse) obj;
	return Objects.equals(historicalChange, other.historicalChange) && Objects.equals(historicalMetadata, other.historicalMetadata) && Objects.equals(historicalParentOrganizer, other.historicalParentOrganizer) && Objects.equals(numElements, other.numElements) && Objects.equals(numOrganizers, other.numOrganizers) && Objects.equals(type, other.type) && Objects.equals(path, other.path);
    }
}
