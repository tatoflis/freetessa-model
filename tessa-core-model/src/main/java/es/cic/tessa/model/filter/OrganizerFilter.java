package es.cic.tessa.model.filter;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import es.cic.tessa.common.filter.Filter;
import es.cic.tessa.common.filter.PropertyFilter;


public class OrganizerFilter extends Filter
{

    private List<PropertyFilter> parentOrganizerFilter = new ArrayList<PropertyFilter>();
    private List<PropertyFilter> templateFilter = new ArrayList<PropertyFilter>();
    private List<PropertyFilter> assetFilter = new ArrayList<PropertyFilter>();
    private List<PropertyFilter> metadataFilter = new ArrayList<PropertyFilter>();
    private Instant asOf;

    public void addParentOrganizerPropertyFilter(PropertyFilter propertyFilter)
    {

	getParentOrganizerFilter().add(propertyFilter);
    }


    public void addTemplatePropertyFilter(PropertyFilter propertyFilter)
    {

	getTemplateFilter().add(propertyFilter);
    }


    public void addAssetPropertyFilter(PropertyFilter propertyFilter)
    {

	getAssetFilter().add(propertyFilter);
    }


    public void addMetadataPropertyFilter(PropertyFilter propertyFilter)
    {

	getMetadataFilter().add(propertyFilter);
    }


    public List<PropertyFilter> getParentOrganizerFilter()
    {

	return parentOrganizerFilter;
    }


    public void setParentOrganizerFilter(List<PropertyFilter> parentOrganizerFilter)
    {

	this.parentOrganizerFilter = parentOrganizerFilter;
    }


    public List<PropertyFilter> getTemplateFilter()
    {

	return templateFilter;
    }


    public void setTemplateFilter(List<PropertyFilter> templateFilter)
    {

	this.templateFilter = templateFilter;
    }


    public List<PropertyFilter> getAssetFilter()
    {

	return assetFilter;
    }


    public void setAssetFilter(List<PropertyFilter> assetFilter)
    {

	this.assetFilter = assetFilter;
    }


    public List<PropertyFilter> getMetadataFilter()
    {

	return metadataFilter;
    }


    public void setMetadataFilter(List<PropertyFilter> metadataFilter)
    {

	this.metadataFilter = metadataFilter;
    }


    public Instant getAsOf()
    {

	return asOf;
    }


    public void setAsOf(Instant asOf)
    {

	this.asOf = asOf;
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(propertyFilters, assetFilter, metadataFilter, parentOrganizerFilter, templateFilter);
    }


    @Override
    public boolean equals(Object obj)
    {

	if(this == obj)
	    return true;
	if(!(obj instanceof OrganizerFilter))
	    return false;
	OrganizerFilter other = (OrganizerFilter) obj;
	return Objects.equals(propertyFilters, other.propertyFilters) && Objects.equals(assetFilter, other.assetFilter) && Objects.equals(metadataFilter, other.metadataFilter) && Objects.equals(parentOrganizerFilter, other.parentOrganizerFilter) && Objects.equals(templateFilter, other.templateFilter);
    }


    @Override
    public String toString()
    {

	return "OrganizerFilter [parentOrganizerFilter=" + parentOrganizerFilter + ", templateFilter=" + templateFilter + ", assetFilter=" + assetFilter + ", metadataFilter=" + metadataFilter + ", asOf=" + asOf + ", propertyFilters=" + propertyFilters + ", count=" + count + "]";
    }

}
