package es.cic.tessa.model.filter;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import es.cic.tessa.common.filter.PropertyFilter;
import es.cic.tessa.common.model.types.RelationshipType;


public class AssetReferenceFilter
{

    private List<PropertyFilter> assetFilter = new ArrayList<>();
    private List<PropertyFilter> organizerFilter = new ArrayList<>();
    private List<PropertyFilter> templateFilter = new ArrayList<>();
    private List<PropertyFilter> assetValueFilter = new ArrayList<>();
    private List<PropertyFilter> templateAttributeFilter = new ArrayList<>();
    private List<AssetValueTemplateAttributeFilter> assetValueTemplateAttributeFilter = new ArrayList<>();
    private List<PropertyFilter> hashtagFilter = new ArrayList<>();
    private AssetValueTemplateAttributeFilter assetValueReferenceToFilter;
    private List<PropertyFilter> relationshipFilter = new ArrayList<>();
    private String relationshipDirection = RelationshipType.TO.getRelationshipType();
    private List<AssetReferenceFilter> referenceFilter = new ArrayList<>();

    public void addAssetPropertyFilter(PropertyFilter propertyFilter)
    {

	getAssetFilter().add(propertyFilter);
    }


    public void addOrganizerPropertyFilter(PropertyFilter propertyFilter)
    {

	getOrganizerFilter().add(propertyFilter);
    }


    public void addTemplatePropertyFilter(PropertyFilter propertyFilter)
    {

	getTemplateFilter().add(propertyFilter);
    }


    public void addAssetValuePropertyFilter(PropertyFilter propertyFilter)
    {

	getAssetValueFilter().add(propertyFilter);
    }


    public void addTemplateAttributePropertyFilter(PropertyFilter propertyFilter)
    {

	getTemplateAttributeFilter().add(propertyFilter);
    }


    public void addAssetValueTemplateAttributeFilter(AssetValueTemplateAttributeFilter assetValueTemplateAttributeFilter)
    {

	getAssetValueTemplateAttributeFilter().add(assetValueTemplateAttributeFilter);
    }


    public void addHashtagPropertyFilter(PropertyFilter propertyFilter)
    {

	getHashtagFilter().add(propertyFilter);
    }


    public void addRelationshipPropertyFilter(PropertyFilter propertyFilter)
    {

	getRelationshipFilter().add(propertyFilter);
    }


    public void addAssetReferenceFilter(AssetReferenceFilter assetReferenceFilter)
    {

	getReferenceFilter().add(assetReferenceFilter);
    }


    public List<PropertyFilter> getAssetFilter()
    {

	return assetFilter;
    }


    public void setAssetFilter(List<PropertyFilter> assetFilter)
    {

	this.assetFilter = assetFilter;
    }


    public List<PropertyFilter> getOrganizerFilter()
    {

	return organizerFilter;
    }


    public void setOrganizerFilter(List<PropertyFilter> organizerFilter)
    {

	this.organizerFilter = organizerFilter;
    }


    public List<PropertyFilter> getTemplateFilter()
    {

	return templateFilter;
    }


    public void setTemplateFilter(List<PropertyFilter> templateFilter)
    {

	this.templateFilter = templateFilter;
    }


    public List<PropertyFilter> getAssetValueFilter()
    {

	return assetValueFilter;
    }


    public void setAssetValueFilter(List<PropertyFilter> assetValueFilter)
    {

	this.assetValueFilter = assetValueFilter;
    }


    public List<PropertyFilter> getTemplateAttributeFilter()
    {

	return templateAttributeFilter;
    }


    public void setTemplateAttributeFilter(List<PropertyFilter> templateAttributeFilter)
    {

	this.templateAttributeFilter = templateAttributeFilter;
    }


    public List<AssetValueTemplateAttributeFilter> getAssetValueTemplateAttributeFilter()
    {

	return assetValueTemplateAttributeFilter;
    }


    public void setAssetValueTemplateAttributeFilter(List<AssetValueTemplateAttributeFilter> assetValueTemplateAttributeFilter)
    {

	this.assetValueTemplateAttributeFilter = assetValueTemplateAttributeFilter;
    }


    public List<PropertyFilter> getHashtagFilter()
    {

	return hashtagFilter;
    }


    public void setHashtagFilter(List<PropertyFilter> hashtagFilter)
    {

	this.hashtagFilter = hashtagFilter;
    }


    public AssetValueTemplateAttributeFilter getAssetValueReferenceToFilter()
    {

	return assetValueReferenceToFilter;
    }


    public void setAssetValueReferenceToFilter(AssetValueTemplateAttributeFilter assetValueReferenceToFilter)
    {

	this.assetValueReferenceToFilter = assetValueReferenceToFilter;
    }


    public List<PropertyFilter> getRelationshipFilter()
    {

	return relationshipFilter;
    }


    public void setRelationshipFilter(List<PropertyFilter> relationshipFilter)
    {

	this.relationshipFilter = relationshipFilter;
    }


    public String getRelationshipDirection()
    {

	return relationshipDirection;
    }


    public void setRelationshipDirection(String relationshipDirection)
    {

	this.relationshipDirection = relationshipDirection;
    }


    public List<AssetReferenceFilter> getReferenceFilter()
    {

	return referenceFilter;
    }


    public void setReferenceFilter(List<AssetReferenceFilter> referenceFilter)
    {

	this.referenceFilter = referenceFilter;
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(assetValueFilter, hashtagFilter, organizerFilter, relationshipFilter, relationshipDirection, templateAttributeFilter, templateFilter);
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
	AssetReferenceFilter other = (AssetReferenceFilter) obj;
	return Objects.equals(assetValueFilter, other.assetValueFilter) && Objects.equals(hashtagFilter, other.hashtagFilter) && Objects.equals(organizerFilter, other.organizerFilter) && Objects.equals(relationshipFilter, other.relationshipFilter) && Objects.equals(relationshipDirection, other.relationshipDirection) && Objects.equals(templateAttributeFilter, other.templateAttributeFilter) && Objects.equals(templateFilter, other.templateFilter);
    }

}
