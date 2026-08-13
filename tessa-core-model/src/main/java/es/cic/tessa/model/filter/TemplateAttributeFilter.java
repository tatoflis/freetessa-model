package es.cic.tessa.model.filter;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import es.cic.tessa.common.filter.Filter;
import es.cic.tessa.common.filter.PropertyFilter;


public class TemplateAttributeFilter extends Filter
{

    private List<PropertyFilter> hashtagFilter = new ArrayList<>();
    private List<PropertyFilter> templateFilter = new ArrayList<>();

    /**
     * @deprecated (for removal)
     */
    @Deprecated(forRemoval = true)
    private List<PropertyFilter> templateAttributeExtendsFilter = new ArrayList<>();
    private List<PropertyFilter> templateReferenceFilter = new ArrayList<>();

    private String hierarchyType;
    private Instant asOf;

    public void addHashtagPropertyFilter(PropertyFilter propertyFilter)
    {

	getHashtagFilter().add(propertyFilter);
    }


    /**
     * @deprecated (for removal)
     */
    @Deprecated(forRemoval = true)
    public void addTemplateAttributeExtendsPropertyFilter(PropertyFilter propertyFilter)
    {

	getTemplateAttributeExtendsFilter().add(propertyFilter);
    }


    public void addTemplatePropertyFilter(PropertyFilter propertyFilter)
    {

	getTemplateFilter().add(propertyFilter);
    }


    public void addTemplateReferencePropertyFilter(PropertyFilter propertyFilter)
    {

	getTemplateReferenceFilter().add(propertyFilter);
    }


    public List<PropertyFilter> getHashtagFilter()
    {

	return hashtagFilter;
    }


    public void setHashtagFilter(List<PropertyFilter> hashtagFilter)
    {

	this.hashtagFilter = hashtagFilter;
    }


    public List<PropertyFilter> getTemplateFilter()
    {

	return templateFilter;
    }


    public void setTemplateFilter(List<PropertyFilter> templateFilter)
    {

	this.templateFilter = templateFilter;
    }


    public List<PropertyFilter> getTemplateReferenceFilter()
    {

	return templateReferenceFilter;
    }


    public void setTemplateReferenceFilter(List<PropertyFilter> templateReferenceFilter)
    {

	this.templateReferenceFilter = templateReferenceFilter;
    }


    public String getHierarchyType()
    {

	return hierarchyType;
    }


    public void setHierarchyType(String hierarchyType)
    {

	this.hierarchyType = hierarchyType;
    }


    public Instant getAsOf()
    {

	return asOf;
    }


    public void setAsOf(Instant asOf)
    {

	this.asOf = asOf;
    }


    /**
     * @deprecated (for removal)
     */
    @Deprecated(forRemoval = true)
    public List<PropertyFilter> getTemplateAttributeExtendsFilter()
    {

	return templateAttributeExtendsFilter;
    }


    /**
     * @deprecated (for removal)
     */
    @Deprecated(forRemoval = true)
    public void setTemplateAttributeExtendsFilter(List<PropertyFilter> templateAttributeExtendsFilter)
    {

	this.templateAttributeExtendsFilter = templateAttributeExtendsFilter;
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(propertyFilters, hashtagFilter, hierarchyType, templateAttributeExtendsFilter, templateFilter, templateReferenceFilter);
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
	TemplateAttributeFilter other = (TemplateAttributeFilter) obj;
	return Objects.equals(propertyFilters, other.propertyFilters) && Objects.equals(hashtagFilter, other.hashtagFilter) && hierarchyType == other.hierarchyType && Objects.equals(templateAttributeExtendsFilter, other.templateAttributeExtendsFilter) && Objects.equals(templateFilter, other.templateFilter) && Objects.equals(templateReferenceFilter, other.templateReferenceFilter);
    }


    @Override
    public String toString()
    {

	return "TemplateAttributeFilter [hashtagFilter=" + hashtagFilter + ", templateFilter=" + templateFilter + ", templateAttributeExtendsFilter=" + templateAttributeExtendsFilter + ", templateReferenceFilter=" + templateReferenceFilter + ", hierarchyType=" + hierarchyType + ", asOf=" + asOf + ", propertyFilters=" + propertyFilters + ", count=" + count + "]";
    }

}
