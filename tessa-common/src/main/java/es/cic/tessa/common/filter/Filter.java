package es.cic.tessa.common.filter;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public abstract class Filter
{

    protected List<PropertyFilter> propertyFilters = new ArrayList<>();
    protected boolean count = false;

    public void addPropertyFilter(PropertyFilter propertyFilter)
    {

	getPropertyFilters().add(propertyFilter);
    }


    public List<PropertyFilter> getPropertyFilters()
    {

	return propertyFilters;
    }


    public void setPropertyFilters(List<PropertyFilter> propertyFilters)
    {

	this.propertyFilters = propertyFilters;
    }


    public boolean isCount()
    {

	return count;
    }


    public void setCount(boolean count)
    {

	this.count = count;
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(count, propertyFilters);
    }


    @Override
    public boolean equals(Object obj)
    {

	if(this == obj)
	    return true;
	if(obj == null)
	    return false;
	if(getClass() != obj.getClass())
	    return false;
	Filter other = (Filter) obj;
	return count == other.count && Objects.equals(propertyFilters, other.propertyFilters);
    }

}
