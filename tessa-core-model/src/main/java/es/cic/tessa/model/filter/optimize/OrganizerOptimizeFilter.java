package es.cic.tessa.model.filter.optimize;


import java.util.Objects;
import es.cic.tessa.common.filter.OptimizeFilter;


public class OrganizerOptimizeFilter extends OptimizeFilter
{

    private Long metadataId;
    private Long parentId;
    private Long assetId;

    public Long getMetadataId()
    {

	return metadataId;
    }


    public void setMetadataId(Long metadataId)
    {

	this.metadataId = metadataId;
    }


    public Long getParentId()
    {

	return parentId;
    }


    public void setParentId(Long parentId)
    {

	this.parentId = parentId;
    }


    public Long getAssetId()
    {

	return assetId;
    }


    public void setAssetId(Long assetId)
    {

	this.assetId = assetId;
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(metadataId, parentId);
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
	OrganizerOptimizeFilter other = (OrganizerOptimizeFilter) obj;
	return Objects.equals(metadataId, other.metadataId) && Objects.equals(parentId, other.parentId);
    }


    @Override
    public String toString()
    {

	return "OrganizerOptimizeFilter [metadataId=" + metadataId + ", parentId=" + parentId + ", assetId=" + assetId + ", id=" + id + ", ids=" + ids + ", name=" + name + "]";
    }

}
