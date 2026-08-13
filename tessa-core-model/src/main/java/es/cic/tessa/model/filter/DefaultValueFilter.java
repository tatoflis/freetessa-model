package es.cic.tessa.model.filter;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import es.cic.tessa.common.filter.Filter;
import es.cic.tessa.common.filter.PropertyFilter;


public class DefaultValueFilter extends Filter
{

    private List<PropertyFilter> expressionParamFilter = new ArrayList<>();

    public void addExpressionParamFilter(PropertyFilter propertyFilter)
    {

	getExpressionParamFilter().add(propertyFilter);
    }


    public List<PropertyFilter> getExpressionParamFilter()
    {

	return expressionParamFilter;
    }


    public void setExpressionParamFilter(List<PropertyFilter> expressionParamFilter)
    {

	this.expressionParamFilter = expressionParamFilter;
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(expressionParamFilter);
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
	DefaultValueFilter other = (DefaultValueFilter) obj;
	return Objects.equals(expressionParamFilter, other.expressionParamFilter);
    }

}
