package es.cic.tessa.model.historical.dto;


import java.util.Collection;
import java.util.List;
import java.util.Objects;
import es.cic.tessa.model.dto.support.AbstractEntityResponse;


public class HistoricalAssetResponse extends AbstractEntityResponse
{

    private static final long serialVersionUID = 1L;

    private String identificator;

    private Boolean active;

    private String physicalPath;

    private List<String> logicalPath;

    private HistoricalTemplateResponse historicalTemplate;

    private HistoricalAssetResponse historicalAssetDependsResponse;

    private Integer numComplexAssets;

    private Collection<HistoricalAssetValueResponse> historicalValues;

    private Collection<HistoricalOrganizerResponse> historicalOrganizers;

    private HistoricalChangeResponse historicalChange;

    public String getIdentificator()
    {

	return identificator;
    }


    public void setIdentificator(String identificator)
    {

	this.identificator = identificator;
    }


    public Boolean getActive()
    {

	return active;
    }


    public void setActive(Boolean active)
    {

	this.active = active;
    }


    public String getPhysicalPath()
    {

	return physicalPath;
    }


    public void setPhysicalPath(String physicalPath)
    {

	this.physicalPath = physicalPath;
    }


    public List<String> getLogicalPath()
    {

	return logicalPath;
    }


    public void setLogicalPath(List<String> logicalPath)
    {

	this.logicalPath = logicalPath;
    }


    public HistoricalTemplateResponse getHistoricalTemplate()
    {

	return historicalTemplate;
    }


    public void setHistoricalTemplate(HistoricalTemplateResponse historicalTemplate)
    {

	this.historicalTemplate = historicalTemplate;
    }


    public HistoricalAssetResponse getHistoricalAssetDependsResponse()
    {

	return historicalAssetDependsResponse;
    }


    public void setHistoricalAssetDependsResponse(HistoricalAssetResponse historicalAssetDependsResponse)
    {

	this.historicalAssetDependsResponse = historicalAssetDependsResponse;
    }


    public Integer getNumComplexAssets()
    {

	return numComplexAssets;
    }


    public void setNumComplexAssets(Integer numComplexAssets)
    {

	this.numComplexAssets = numComplexAssets;
    }


    public Collection<HistoricalAssetValueResponse> getHistoricalValues()
    {

	return historicalValues;
    }


    public void setHistoricalValues(Collection<HistoricalAssetValueResponse> historicalValues)
    {

	this.historicalValues = historicalValues;
    }


    public Collection<HistoricalOrganizerResponse> getHistoricalOrganizers()
    {

	return historicalOrganizers;
    }


    public void setHistoricalOrganizers(Collection<HistoricalOrganizerResponse> historicalOrganizers)
    {

	this.historicalOrganizers = historicalOrganizers;
    }


    public HistoricalChangeResponse getHistoricalChange()
    {

	return historicalChange;
    }


    public void setHistoricalChange(HistoricalChangeResponse historicalChange)
    {

	this.historicalChange = historicalChange;
    }


    // historicalValues excluido: HistoricalAssetValueResponse.hashCode()/equals() incluye a su vez
    // historicalAsset (relacion inversa), lo que produce recursion infinita (StackOverflowError)
    // en cuanto ambos lados estan poblados -- mismo patron que el corregido en las entidades de
    // dominio HistoricalAsset/HistoricalAssetValue, pero aqui en el DTO que realmente serializa
    // Jackson via HTTP.
    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(active, modifDate, historicalAssetDependsResponse, historicalChange, historicalOrganizers, historicalTemplate, identificator, numComplexAssets, logicalPath, physicalPath);
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
	HistoricalAssetResponse other = (HistoricalAssetResponse) obj;
	return Objects.equals(active, other.active) && Objects.equals(modifDate, other.modifDate) && Objects.equals(historicalAssetDependsResponse, other.historicalAssetDependsResponse) && Objects.equals(historicalChange, other.historicalChange) && Objects.equals(historicalOrganizers, other.historicalOrganizers) && Objects.equals(historicalTemplate, other.historicalTemplate) && Objects.equals(identificator, other.identificator) && Objects.equals(numComplexAssets, other.numComplexAssets) && Objects.equals(logicalPath, other.logicalPath) && Objects.equals(physicalPath, other.physicalPath);
    }

}
