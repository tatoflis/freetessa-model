package es.cic.tessa.model.dto;


import java.util.Collection;
import java.util.List;
import java.util.Objects;
import es.cic.tessa.model.dto.support.AbstractEntityResponse;


public class AssetResponse extends AbstractEntityResponse
{

    private static final long serialVersionUID = 1L;

    private String identificator;
    private Boolean active;
    private String physicalPath;
    private List<String> logicalPath;
    private TemplateResponse template;
    private AssetResponse assetDependsResponse;
    private Integer numComplexAssets;
    private Collection<AssetValueResponse> values;
    private Collection<OrganizerResponse> organizers;

    public TemplateResponse getTemplate()
    {

	return template;
    }


    public void setTemplate(TemplateResponse template)
    {

	this.template = template;
    }


    public Integer getNumComplexAssets()
    {

	return numComplexAssets;
    }


    public void setNumComplexAssets(Integer numComplexAssets)
    {

	this.numComplexAssets = numComplexAssets;
    }


    public AssetResponse getAssetDependsResponse()
    {

	return assetDependsResponse;
    }


    public void setAssetDependsResponse(AssetResponse assetDependsResponse)
    {

	this.assetDependsResponse = assetDependsResponse;
    }


    public String getIdentificator()
    {

	return identificator;
    }


    public void setIdentificator(String identificator)
    {

	this.identificator = identificator;
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


    public Boolean getActive()
    {

	return active;
    }


    public void setActive(Boolean active)
    {

	this.active = active;
    }


    public Collection<OrganizerResponse> getOrganizers()
    {

	return organizers;
    }


    public void setOrganizers(Collection<OrganizerResponse> organizers)
    {

	this.organizers = organizers;
    }


    public Collection<AssetValueResponse> getValues()
    {

	return values;
    }


    public void setValues(Collection<AssetValueResponse> values)
    {

	this.values = values;
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(active, assetDependsResponse, identificator, numComplexAssets, organizers, logicalPath, physicalPath, template, values);
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
	AssetResponse other = (AssetResponse) obj;
	return Objects.equals(active, other.active) && Objects.equals(assetDependsResponse, other.assetDependsResponse) && Objects.equals(identificator, other.identificator) && Objects.equals(numComplexAssets, other.numComplexAssets) && Objects.equals(organizers, other.organizers) && Objects.equals(logicalPath, other.logicalPath) && Objects.equals(physicalPath, other.physicalPath) && Objects.equals(template, other.template) && Objects.equals(values, other.values);
    }


    @Override
    public String toString()
    {

	return "AssetResponse [identificator=" + identificator + ", active=" + active + ", logicalPath=" + logicalPath + ", physicalPath=" + physicalPath + ", template=" + template + ", assetDependsResponse=" + assetDependsResponse + ", numComplexAssets=" + numComplexAssets + ", values=" + values + ", organizers=" + organizers + "]";
    }

}
