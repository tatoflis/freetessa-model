package es.cic.tessa.common.query;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import es.cic.tessa.common.exceptions.TessaException;
import es.cic.tessa.common.filter.PropertyFilter;
import es.cic.tessa.common.model.TessaConstants;
import es.cic.tessa.common.model.types.ArithmeticOperatorType;


public abstract class AbstractQueryManager
{

    protected Map<String, Object> buildProperties(Collection<PropertyFilter> filters)
    {

	Map<String, Object> properties = new HashMap<>();

	for (PropertyFilter propertyFilter : filters)
	{

	    properties.put(getPropertyName(propertyFilter.getPropertyName()), getPropertyValue(propertyFilter.getPropertyValue(), propertyFilter.getPropertyName()));
	}

	return properties;

    }


    private String getPropertyName(String propertyName)
    {

	if(propertyName.equals(TessaConstants.NAME))
	{
	    return TessaConstants.NAME_LOWER;
	}
	else if(propertyName.equals(TessaConstants.VALUE))
	{
	    return TessaConstants.VALUE_LOWER;
	}

	return propertyName;
    }


    private Object getPropertyValue(Object propertyValue, String propertyName)
    {

	if(propertyValue instanceof Boolean || propertyValue instanceof Number)
	{
	    return String.valueOf(propertyValue);
	}
	else if(propertyValue instanceof String)
	{
	    return ((String) propertyValue).toLowerCase();
	}
	else
	{
	    throw new TessaException("Type unknowed : {}", propertyName);
	}

    }


    protected void addFilterIfNotNullOrEmpty(Collection<?> propertyFilters, String filterName, List<String> orderedFilters)
    {

	if(isNotNullOrEmptyFilter(propertyFilters) && !orderedFilters.contains(filterName))
	{
	    orderedFilters.add(filterName);
	}
    }


    protected void addFilterIfNotNull(Object object, String filterName, List<String> orderedFilters)
    {

	if(object != null && !orderedFilters.contains(filterName))
	{
	    orderedFilters.add(filterName);
	}
    }


    protected void addFilterIfNotEmptyAndPropertyNotNull(Collection<?> propertyFilters, String filterName, List<String> orderedFilters)
    {

	if(isNotNullOrEmptyFilter(propertyFilters) && propertyFilters.stream().anyMatch(pf -> (pf instanceof PropertyFilter && ((PropertyFilter) pf).getPropertyName() != null && ((PropertyFilter) pf).getPropertyValue() != null)))
	{
	    orderedFilters.add(filterName);
	}
    }


    protected boolean isEquals(Collection<?> propertyFilters)
    {

	return propertyFilters.stream().anyMatch(pf -> (pf instanceof PropertyFilter && ((PropertyFilter) pf).getArithmeticOperatorType() != null && ((PropertyFilter) pf).getArithmeticOperatorType().equals(ArithmeticOperatorType.EQUALS)));
    }


    protected boolean isNotNullOrEmptyFilter(Collection<?> propertyFilters)
    {

	return propertyFilters != null && !propertyFilters.isEmpty();
    }
}
