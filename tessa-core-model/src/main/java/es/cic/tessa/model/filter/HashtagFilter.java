package es.cic.tessa.model.filter;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import es.cic.tessa.common.filter.Filter;
import es.cic.tessa.common.filter.PropertyFilter;


public class HashtagFilter extends Filter
{

    private List<PropertyFilter> assetFilter = new ArrayList<PropertyFilter>();
    private List<PropertyFilter> templateFilter = new ArrayList<PropertyFilter>();
    private List<PropertyFilter> templateAttributeFilter = new ArrayList<PropertyFilter>();

    public void addAssetPropertyFilter(PropertyFilter propertyFilter)
    {

	getAssetFilter().add(propertyFilter);
    }


    public void addTemplatePropertyFilter(PropertyFilter propertyFilter)
    {

	getTemplateFilter().add(propertyFilter);
    }


    public void addTemplateAttributePropertyFilter(PropertyFilter propertyFilter)
    {

	getTemplateAttributeFilter().add(propertyFilter);
    }


    public List<PropertyFilter> getAssetFilter()
    {

	return assetFilter;
    }


    public void setAssetFilter(List<PropertyFilter> assetFilter)
    {

	this.assetFilter = assetFilter;
    }


    public List<PropertyFilter> getTemplateFilter()
    {

	return templateFilter;
    }


    public void setTemplateFilter(List<PropertyFilter> templateFilter)
    {

	this.templateFilter = templateFilter;
    }


    public List<PropertyFilter> getTemplateAttributeFilter()
    {

	return templateAttributeFilter;
    }


    public void setTemplateAttributeFilter(List<PropertyFilter> templateAttributeFilter)
    {

	this.templateAttributeFilter = templateAttributeFilter;
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(propertyFilters, assetFilter, templateAttributeFilter, templateFilter);
    }


    @Override
    public boolean equals(Object obj)
    {

	if(this == obj)
	    return true;
	if(!(obj instanceof HashtagFilter))
	    return false;
	HashtagFilter other = (HashtagFilter) obj;
	return Objects.equals(propertyFilters, other.propertyFilters) && Objects.equals(assetFilter, other.assetFilter) && Objects.equals(templateAttributeFilter, other.templateAttributeFilter) && Objects.equals(templateFilter, other.templateFilter);
    }


    @Override
    public String toString()
    {

	return "HashtagFilter [assetFilter=" + assetFilter + ", templateFilter=" + templateFilter + ", templateAttributeFilter=" + templateAttributeFilter + ", propertyFilters=" + propertyFilters + ", count=" + count + "]";
    }

}