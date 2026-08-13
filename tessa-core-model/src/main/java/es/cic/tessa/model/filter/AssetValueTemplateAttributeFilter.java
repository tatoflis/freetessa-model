package es.cic.tessa.model.filter;


import java.util.Collection;
import java.util.HashSet;
import es.cic.tessa.common.filter.PropertyFilter;
import es.cic.tessa.common.model.types.LogicalOperatorType;


public class AssetValueTemplateAttributeFilter
{

    private PropertyFilter assetValuePropertyFilter;
    private PropertyFilter templateAttributePropertyFilter;
    private LogicalOperatorType logicalOperatorType = LogicalOperatorType.AND;

    public LogicalOperatorType getLogicalOperatorType()
    {

	return logicalOperatorType;
    }


    public void setLogicalOperatorType(LogicalOperatorType logicalOperatorType)
    {

	this.logicalOperatorType = logicalOperatorType;
    }

    private Collection<PropertyFilter> assetValuePropertyFilters = new HashSet<>();

    public PropertyFilter getAssetValuePropertyFilter()
    {

	return assetValuePropertyFilter;
    }


    public void setAssetValuePropertyFilter(PropertyFilter assetValuePropertyFilter)
    {

	this.assetValuePropertyFilter = assetValuePropertyFilter;
    }


    public PropertyFilter getTemplateAttributePropertyFilter()
    {

	return templateAttributePropertyFilter;
    }


    public void setTemplateAttributePropertyFilter(PropertyFilter templateAttributePropertyFilter)
    {

	this.templateAttributePropertyFilter = templateAttributePropertyFilter;
    }


    public Collection<PropertyFilter> getAssetValuePropertyFilters()
    {

	return assetValuePropertyFilters;
    }


    public void setAssetValuePropertyFilters(Collection<PropertyFilter> assetValuePropertyFilters)
    {

	this.assetValuePropertyFilters = assetValuePropertyFilters;
    }

}
