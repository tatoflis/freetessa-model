package es.cic.tessa.model.filter;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import es.cic.tessa.common.filter.Filter;
import es.cic.tessa.common.filter.PropertyFilter;


public class TemplateAttributeCollectionMappingFilter extends Filter
{

    private List<PropertyFilter> templateAttributeFilter = new ArrayList<>();

    public void addTemplateAttributePropertyFilter(PropertyFilter propertyFilter)
    {

	getTemplateAttributeFilter().add(propertyFilter);
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

	return Objects.hash(propertyFilters, templateAttributeFilter);
    }


    @Override
    public boolean equals(Object obj)
    {

	if(this == obj)
	    return true;
	if(!(obj instanceof TemplateAttributeCollectionMappingFilter))
	    return false;
	TemplateAttributeCollectionMappingFilter other = (TemplateAttributeCollectionMappingFilter) obj;
	return Objects.equals(propertyFilters, other.propertyFilters) && Objects.equals(templateAttributeFilter, other.templateAttributeFilter);
    }


    @Override
    public String toString()
    {

	return "TemplateAttributeCollectionMappingFilter [templateAttributeFilter=" + templateAttributeFilter + ", propertyFilters=" + propertyFilters + ", count=" + count + "]";
    }

}
