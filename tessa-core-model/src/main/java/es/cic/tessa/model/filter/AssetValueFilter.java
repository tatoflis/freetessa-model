package es.cic.tessa.model.filter;


import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import es.cic.tessa.common.filter.Filter;
import es.cic.tessa.common.filter.PropertyFilter;


public class AssetValueFilter extends Filter
{

    private Collection<PropertyFilter> assetFilter = new ArrayList<>();
    private Collection<PropertyFilter> assetReferenceFilter = new ArrayList<>();
    private Collection<PropertyFilter> templateReferenceFilter = new ArrayList<>();
    private Collection<PropertyFilter> templateAssetReferenceFilter = new ArrayList<>();
    private Collection<PropertyFilter> templateAttributeFilter = new ArrayList<>();
    private Collection<PropertyFilter> templateFilter = new ArrayList<>();
    private Collection<Long> idsAssets = new ArrayList<>();
    private Set<Long> idsAssetReference = new HashSet<>();
    private Collection<PropertyFilter> relationshipFilter = new ArrayList<>();
    private Instant asOf;

    public void addTemplatePropertyFilter(PropertyFilter propertyFilter)
    {

	getTemplateFilter().add(propertyFilter);
    }


    public void addTemplateAssetReferencePropertyFilter(PropertyFilter propertyFilter)
    {

	getTemplateAssetReferenceFilter().add(propertyFilter);
    }


    public void addAssetPropertyFilter(PropertyFilter propertyFilter)
    {

	getAssetFilter().add(propertyFilter);
    }


    public void addAssetReferencePropertyFilter(PropertyFilter propertyFilter)
    {

	getAssetReferenceFilter().add(propertyFilter);
    }


    public void addTemplateReferencePropertyFilter(PropertyFilter propertyFilter)
    {

	getTemplateReferenceFilter().add(propertyFilter);
    }


    public void addTemplateAttributePropertyFilter(PropertyFilter propertyFilter)
    {

	getTemplateAttributeFilter().add(propertyFilter);
    }


    public Collection<PropertyFilter> getAssetFilter()
    {

	return assetFilter;
    }


    public void setAssetFilter(List<PropertyFilter> assetFilter)
    {

	this.assetFilter = assetFilter;
    }


    public Collection<PropertyFilter> getTemplateReferenceFilter()
    {

	return templateReferenceFilter;
    }


    public void setTemplateReferenceFilter(List<PropertyFilter> templateReferenceFilter)
    {

	this.templateReferenceFilter = templateReferenceFilter;
    }


    public Collection<PropertyFilter> getTemplateAttributeFilter()
    {

	return templateAttributeFilter;
    }


    public void setTemplateAttributeFilter(List<PropertyFilter> templateAttributeFilter)
    {

	this.templateAttributeFilter = templateAttributeFilter;
    }


    public Collection<PropertyFilter> getTemplateFilter()
    {

	return templateFilter;
    }


    public void setTemplateFilter(List<PropertyFilter> templateFilter)
    {

	this.templateFilter = templateFilter;
    }


    public Collection<Long> getIdsAssets()
    {

	return idsAssets;
    }


    public void setIdsAssets(Collection<Long> idsAssets)
    {

	this.idsAssets = idsAssets;
    }


    public Set<Long> getIdsAssetReference()
    {

	return idsAssetReference;
    }


    public void setIdsAssetReference(Set<Long> idsAssetReference)
    {

	this.idsAssetReference = idsAssetReference;
    }


    public Collection<PropertyFilter> getAssetReferenceFilter()
    {

	return assetReferenceFilter;
    }


    public void setAssetReferenceFilter(List<PropertyFilter> assetReferenceFilter)
    {

	this.assetReferenceFilter = assetReferenceFilter;
    }


    public Collection<PropertyFilter> getTemplateAssetReferenceFilter()
    {

	return templateAssetReferenceFilter;
    }


    public void setTemplateAssetReferenceFilter(List<PropertyFilter> templateAssetReferenceFilter)
    {

	this.templateAssetReferenceFilter = templateAssetReferenceFilter;
    }


    public Collection<PropertyFilter> getRelationshipFilter()
    {

	return relationshipFilter;
    }


    public void setRelationshipFilter(List<PropertyFilter> relationshipFilter)
    {

	this.relationshipFilter = relationshipFilter;
    }


    public void addRelationshipFilter(PropertyFilter propertyFilter)
    {

	getRelationshipFilter().add(propertyFilter);
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

	return Objects.hash(propertyFilters, assetFilter, assetReferenceFilter, idsAssets, templateAssetReferenceFilter, templateAttributeFilter, templateFilter, templateReferenceFilter);
    }


    @Override
    public boolean equals(Object obj)
    {

	if(this == obj)
	    return true;
	if(!(obj instanceof AssetValueFilter))
	    return false;
	AssetValueFilter other = (AssetValueFilter) obj;
	return Objects.equals(propertyFilters, other.propertyFilters) && Objects.equals(assetFilter, other.assetFilter) && Objects.equals(assetReferenceFilter, other.assetReferenceFilter) && Objects.equals(idsAssets, other.idsAssets) && Objects.equals(templateAssetReferenceFilter, other.templateAssetReferenceFilter) && Objects.equals(templateAttributeFilter, other.templateAttributeFilter) && Objects.equals(templateFilter, other.templateFilter) && Objects.equals(templateReferenceFilter, other.templateReferenceFilter);
    }


    @Override
    public String toString()
    {

	return "AssetValueFilter [assetFilter=" + assetFilter + ", assetReferenceFilter=" + assetReferenceFilter + ", templateReferenceFilter=" + templateReferenceFilter + ", templateAssetReferenceFilter=" + templateAssetReferenceFilter + ", templateAttributeFilter=" + templateAttributeFilter + ", templateFilter=" + templateFilter + ", idsAssets=" + idsAssets + ", idsAssetReference=" + idsAssetReference + ", relationshipFilter=" + relationshipFilter + ", asOf=" + asOf + ", propertyFilters=" + propertyFilters + ", count=" + count + "]";
    }

}
