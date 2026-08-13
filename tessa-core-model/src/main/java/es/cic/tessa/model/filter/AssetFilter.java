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
import es.cic.tessa.common.model.types.HierarchyType;
import es.cic.tessa.common.model.types.SystemScopeType;


public class AssetFilter extends Filter
{

    private List<PropertyFilter> organizerFilter = new ArrayList<>();
    private List<PropertyFilter> templateFilter = new ArrayList<>();
    private List<PropertyFilter> assetValueFilter = new ArrayList<>();
    private List<PropertyFilter> assetValueReferencedFilter = new ArrayList<>();
    private List<PropertyFilter> templateAttributeFilter = new ArrayList<>();
    private List<PropertyFilter> hashtagFilter = new ArrayList<>();
    private List<AssetValueTemplateAttributeFilter> assetValueTemplateAttributeFilter = new ArrayList<>();
    private List<AssetReferenceFilter> referenceFilter = new ArrayList<>();
    
    private AssetFilter assetChildFilter; 
    
    // Get assets equals to idAssets
    private Collection<Long> idsAssets = new HashSet<>();
    // Count child assets?
    private Boolean countChild = Boolean.FALSE;
    // Get assets syntetic, base, all
    private String assetType;
    // Build phisical path
    private Boolean withPath = Boolean.FALSE;
    // Assets of System template
    private Boolean system = Boolean.TRUE;
    private SystemScopeType systemScope;
    // Get assets with hierarchy of template. Default none
    private String hierarchyType = HierarchyType.NONE.getCode();
    private Boolean referenced = Boolean.TRUE;
    private Boolean getDependFromAsset = Boolean.FALSE;
    // Get asset with id asset parent
    private Long idAssetParent;
    // Get asset with id sasset parent
    private Collection<Long> idsAssetParent = new HashSet<>();
    // Get asset with references to asset ids
    private Collection<Long> idsAssetChilds = new HashSet<>();
    // Get assets with idAsset parent hierarchy down
    private Long idSelectedAsset;
    // Get assets with idAsset parent hierarchy down
    private Collection<Long> idsSelectedAsset = new HashSet<>();
    // Get assets with idOrganizer parent hierarchy down
    private Long idSelectedOrganizer;
    // Get assets with idOrganizer parent hierarchy down
    private Set<Long> idsSelectedOrganizer = new HashSet<>();
    private Instant asOf;

    public void addAssetValueTemplateAttributeFilter(AssetValueTemplateAttributeFilter propertyFilter)
    {

	getAssetValueTemplateAttributeFilter().add(propertyFilter);
    }


    public void addOrganizerPropertyFilter(PropertyFilter propertyFilter)
    {

	getOrganizerFilter().add(propertyFilter);
    }


    public void addAssetValueReferencedPropertyFilter(PropertyFilter propertyFilter)
    {

	getAssetValueReferencedFilter().add(propertyFilter);
    }


    public void addHashtagPropertyFilter(PropertyFilter propertyFilter)
    {

	getHashtagFilter().add(propertyFilter);
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


    public Long getIdAssetParent()
    {

	return idAssetParent;
    }


    public void setIdAssetParent(Long idAssetParent)
    {

	this.idAssetParent = idAssetParent;
    }


    public List<PropertyFilter> getHashtagFilter()
    {

	return hashtagFilter;
    }


    public void setHashtagFilter(List<PropertyFilter> hashtagFilter)
    {

	this.hashtagFilter = hashtagFilter;
    }


    public List<PropertyFilter> getAssetValueReferencedFilter()
    {

	return assetValueReferencedFilter;
    }


    public void setAssetValueReferencedFilter(List<PropertyFilter> assetValueReferencedFilter)
    {

	this.assetValueReferencedFilter = assetValueReferencedFilter;
    }


    public List<AssetValueTemplateAttributeFilter> getAssetValueTemplateAttributeFilter()
    {

	return assetValueTemplateAttributeFilter;
    }


    public void setAssetValueTemplateAttributeFilter(List<AssetValueTemplateAttributeFilter> assetValueTemplateAttributeFilter)
    {

	this.assetValueTemplateAttributeFilter = assetValueTemplateAttributeFilter;
    }


    public List<AssetReferenceFilter> getReferenceFilter()
    {

	return referenceFilter;
    }


    public void setReferenceFilter(List<AssetReferenceFilter> referenceFilter)
    {

	this.referenceFilter = referenceFilter;
    }


    public void addReferenceFilter(AssetReferenceFilter referenceFilter)
    {

	getReferenceFilter().add(referenceFilter);
    }


    public Collection<Long> getIdsAssets()
    {

	return idsAssets;
    }


    public void setIdsAssets(Collection<Long> idsAssets)
    {

	this.idsAssets = idsAssets;
    }


    public Boolean isCountChild()
    {

	return countChild;
    }


    public void setCountChild(boolean countChild)
    {

	this.countChild = countChild;
    }


    public Long getIdSelectedAsset()
    {

	return idSelectedAsset;
    }


    public void setIdSelectedAsset(Long idSelectedAsset)
    {

	this.idSelectedAsset = idSelectedAsset;
    }


    public Long getIdSelectedOrganizer()
    {

	return idSelectedOrganizer;
    }


    public void setIdSelectedOrganizer(Long idSelectedOrganizer)
    {

	this.idSelectedOrganizer = idSelectedOrganizer;
    }


    public Boolean isSystem()
    {

	return system;
    }


    public void setSystem(boolean system)
    {

	this.system = system;
    }


    public SystemScopeType getSystemScope()
    {

	return systemScope != null ? systemScope : SystemScopeType.EXCLUDE_SYSTEM;
    }


    public void setSystemScope(SystemScopeType systemScope)
    {

	this.systemScope = systemScope;
    }


    public Boolean isReferenced()
    {

	return referenced;
    }


    public void setReferenced(boolean referenced)
    {

	this.referenced = referenced;
    }


    public Boolean isWithPath()
    {

	return withPath;
    }


    public void setWithPath(boolean withPath)
    {

	this.withPath = withPath;
    }


    public Boolean isGetDependFromAsset()
    {

	return getDependFromAsset;
    }


    public void setGetDependFromAsset(boolean getDependFromAsset)
    {

	this.getDependFromAsset = getDependFromAsset;
    }


    public String getAssetType()
    {

	return assetType;
    }


    public void setAssetType(String assetType)
    {

	this.assetType = assetType;
    }


    public String getHierarchyType()
    {

	return hierarchyType;
    }


    public void setHierarchyType(String hierarchyType)
    {

	this.hierarchyType = hierarchyType;
    }


    public Collection<Long> getIdsAssetParent()
    {

	return idsAssetParent;
    }


    public void setIdsAssetParent(Set<Long> idsAssetParent)
    {

	this.idsAssetParent = idsAssetParent;
    }


    public Collection<Long> getIdsAssetChilds()
    {

	return idsAssetChilds;
    }


    public void setIdsAssetChilds(Set<Long> idsAssetChilds)
    {

	this.idsAssetChilds = idsAssetChilds;
    }


    public Collection<Long> getIdsSelectedAsset()
    {

	return idsSelectedAsset;
    }


    public void setIdsSelectedAsset(Set<Long> idsSelectedAsset)
    {

	this.idsSelectedAsset = idsSelectedAsset;
    }


    public Set<Long> getIdsSelectedOrganizer()
    {

	return idsSelectedOrganizer;
    }


    public void setIdsSelectedOrganizer(Set<Long> idsSelectedOrganizer)
    {

	this.idsSelectedOrganizer = idsSelectedOrganizer;
    }


    public Instant getAsOf()
    {

	return asOf;
    }


    public void setAsOf(Instant asOf)
    {

	this.asOf = asOf;
    }


    
    public AssetFilter getAssetChildFilter()
    {
    
        return assetChildFilter;
    }


    
    public void setAssetChildFilter(AssetFilter assetChildFilter)
    {
    
        this.assetChildFilter = assetChildFilter;
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(assetValueFilter, assetValueReferencedFilter, assetValueTemplateAttributeFilter, countChild, hashtagFilter, idAssetParent, idSelectedAsset, idSelectedOrganizer, idsAssets, organizerFilter, system, templateAttributeFilter, templateFilter, hierarchyType, withPath, assetType);
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
	AssetFilter other = (AssetFilter) obj;
	return Objects.equals(assetValueFilter, other.assetValueFilter) && Objects.equals(assetValueReferencedFilter, other.assetValueReferencedFilter) && Objects.equals(assetValueTemplateAttributeFilter, other.assetValueTemplateAttributeFilter) && countChild == other.countChild && Objects.equals(hashtagFilter, other.hashtagFilter) && Objects.equals(idAssetParent, other.idAssetParent) && Objects.equals(idSelectedAsset, other.idSelectedAsset) && Objects.equals(idSelectedOrganizer, other.idSelectedOrganizer) && Objects.equals(idsAssets, other.idsAssets) && Objects.equals(organizerFilter, other.organizerFilter) && system == other.system && Objects.equals(templateAttributeFilter, other.templateAttributeFilter) && Objects.equals(templateFilter, other.templateFilter) && hierarchyType == other.hierarchyType && withPath == other.withPath && assetType == other.assetType;
    }


    @Override
    public String toString()
    {

	return "AssetFilter [organizerFilter=" + organizerFilter + ", templateFilter=" + templateFilter + ", assetValueFilter=" + assetValueFilter + ", assetValueReferencedFilter=" + assetValueReferencedFilter + ", templateAttributeFilter=" + templateAttributeFilter + ", hashtagFilter=" + hashtagFilter + ", assetValueTemplateAttributeFilter=" + assetValueTemplateAttributeFilter + ", referenceFilter=" + referenceFilter + ", assetChildFilter=" + assetChildFilter + ", idsAssets=" + idsAssets + ", countChild=" + countChild + ", assetType=" + assetType + ", withPath=" + withPath + ", system=" + system + ", hierarchyType=" + hierarchyType + ", referenced=" + referenced + ", getDependFromAsset=" + getDependFromAsset + ", idAssetParent=" + idAssetParent + ", idsAssetParent=" + idsAssetParent + ", idsAssetChilds=" + idsAssetChilds + ", idSelectedAsset=" + idSelectedAsset + ", idsSelectedAsset=" + idsSelectedAsset + ", idSelectedOrganizer=" + idSelectedOrganizer + ", idsSelectedOrganizer=" + idsSelectedOrganizer + ", asOf=" + asOf + ", propertyFilters=" + propertyFilters + ", count=" + count + "]";
    }

}
