package es.cic.tessa.model.filter;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import es.cic.tessa.common.filter.Filter;
import es.cic.tessa.common.filter.PropertyFilter;


public class TemplateAttributeExpressionParamFilter extends Filter
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

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(propertyFilters, templateAttributeFilter);
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
	TemplateAttributeExpressionParamFilter other = (TemplateAttributeExpressionParamFilter) obj;
	return Objects.equals(templateAttributeFilter, other.templateAttributeFilter);
    }

}
