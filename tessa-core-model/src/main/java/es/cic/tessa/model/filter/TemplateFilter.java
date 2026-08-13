package es.cic.tessa.model.filter;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import es.cic.tessa.common.filter.Filter;
import es.cic.tessa.common.filter.PropertyFilter;
import es.cic.tessa.common.model.types.HierarchyType;
import es.cic.tessa.common.model.types.SystemScopeType;


public class TemplateFilter extends Filter
{

    private List<PropertyFilter> organizerFilter = new ArrayList<>();
    private List<PropertyFilter> templateAttributeFilter = new ArrayList<>();
    private List<PropertyFilter> templateReferenceFilter = new ArrayList<>();
    private List<PropertyFilter> hashtagFilter = new ArrayList<>();
    private List<TemplateReferenceFilter> referenceFilter = new ArrayList<>();
    private Long idTemplateParent;
    private boolean countChild = false;
    private boolean withPath = false; // TODO sin desarrollar
    private boolean fullTemplate = false;
    private boolean fullHierarchy = false; // TODO sin desarrollar
    private String hierarchyType = HierarchyType.NONE.getCode();
    private String templateReferenceType = HierarchyType.NONE.getCode();
    private Instant asOf;
    private SystemScopeType systemScope;

    public void addOrganizerPropertyFilter(PropertyFilter propertyFilter)
    {

	getOrganizerFilter().add(propertyFilter);
    }


    public void addTemplateAttributePropertyFilter(PropertyFilter propertyFilter)
    {

	getTemplateAttributeFilter().add(propertyFilter);
    }


    public void addTemplateReferencePropertyFilter(PropertyFilter propertyFilter)
    {

	getTemplateReferenceFilter().add(propertyFilter);
    }


    public void addHashtagPropertyFilter(PropertyFilter propertyFilter)
    {

	getHashtagFilter().add(propertyFilter);
    }


    public void addReferenceFilter(TemplateReferenceFilter templateReferenceFilter)
    {

	getReferenceFilter().add(templateReferenceFilter);
    }


    public List<PropertyFilter> getOrganizerFilter()
    {

	return organizerFilter;
    }


    public void setOrganizerFilter(List<PropertyFilter> organizerFilter)
    {

	this.organizerFilter = organizerFilter;
    }


    public List<PropertyFilter> getTemplateAttributeFilter()
    {

	return templateAttributeFilter;
    }


    public void setTemplateAttributeFilter(List<PropertyFilter> templateAttributeFilter)
    {

	this.templateAttributeFilter = templateAttributeFilter;
    }


    public List<PropertyFilter> getTemplateReferenceFilter()
    {

	return templateReferenceFilter;
    }


    public void setTemplateReferenceFilter(List<PropertyFilter> templateReferenceFilter)
    {

	this.templateReferenceFilter = templateReferenceFilter;
    }


    public List<PropertyFilter> getHashtagFilter()
    {

	return hashtagFilter;
    }


    public void setHashtagFilter(List<PropertyFilter> hashtagFilter)
    {

	this.hashtagFilter = hashtagFilter;
    }


    public List<TemplateReferenceFilter> getReferenceFilter()
    {

	return referenceFilter;
    }


    public void setReferenceFilter(List<TemplateReferenceFilter> referenceFilter)
    {

	this.referenceFilter = referenceFilter;
    }


    public Long getIdTemplateParent()
    {

	return idTemplateParent;
    }


    public void setIdTemplateParent(Long idTemplateParent)
    {

	this.idTemplateParent = idTemplateParent;
    }


    public boolean isCountChild()
    {

	return countChild;
    }


    public void setCountChild(boolean countChild)
    {

	this.countChild = countChild;
    }


    public boolean isWithPath()
    {

	return withPath;
    }


    public void setWithPath(boolean withPath)
    {

	this.withPath = withPath;
    }


    public boolean isFullTemplate()
    {

	return fullTemplate;
    }


    public void setFullTemplate(boolean fullTemplate)
    {

	this.fullTemplate = fullTemplate;
    }


    public boolean isFullHierarchy()
    {

	return fullHierarchy;
    }


    public void setFullHierarchy(boolean fullHierarchy)
    {

	this.fullHierarchy = fullHierarchy;
    }


    public String getHierarchyType()
    {

	return hierarchyType;
    }


    public void setHierarchyType(String hierarchyType)
    {

	this.hierarchyType = hierarchyType;
    }


    public String getTemplateReferenceType()
    {

	return templateReferenceType;
    }


    public void setTemplateReferenceType(String templateReferenceType)
    {

	this.templateReferenceType = templateReferenceType;
    }


    public Instant getAsOf()
    {

	return asOf;
    }


    public void setAsOf(Instant asOf)
    {

	this.asOf = asOf;
    }


    public SystemScopeType getSystemScope()
    {

	return systemScope != null ? systemScope : SystemScopeType.EXCLUDE_SYSTEM;
    }


    public void setSystemScope(SystemScopeType systemScope)
    {

	this.systemScope = systemScope;
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(propertyFilters, countChild, hashtagFilter, hierarchyType, idTemplateParent, organizerFilter, templateAttributeFilter, templateReferenceFilter);
    }


    @Override
    public boolean equals(Object obj)
    {

	if(this == obj)
	    return true;
	if(!(obj instanceof TemplateFilter))
	    return false;
	TemplateFilter other = (TemplateFilter) obj;
	return Objects.equals(propertyFilters, other.propertyFilters) && countChild == other.countChild && Objects.equals(hashtagFilter, other.hashtagFilter) && hierarchyType == other.hierarchyType && Objects.equals(idTemplateParent, other.idTemplateParent) && Objects.equals(organizerFilter, other.organizerFilter) && Objects.equals(templateAttributeFilter, other.templateAttributeFilter) && Objects.equals(templateReferenceFilter, other.templateReferenceFilter);
    }


    @Override
    public String toString()
    {

	return "TemplateFilter [organizerFilter=" + organizerFilter + ", templateAttributeFilter=" + templateAttributeFilter + ", templateReferenceFilter=" + templateReferenceFilter + ", hashtagFilter=" + hashtagFilter + ", referenceFilter=" + referenceFilter + ", idTemplateParent=" + idTemplateParent + ", countChild=" + countChild + ", withPath=" + withPath + ", fullTemplate=" + fullTemplate + ", fullHierarchy=" + fullHierarchy + ", hierarchyType=" + hierarchyType + ", templateReferenceType=" + templateReferenceType + ", asOf=" + asOf + ", systemScope=" + systemScope + ", propertyFilters=" + propertyFilters + ", count=" + count + "]";
    }

}
