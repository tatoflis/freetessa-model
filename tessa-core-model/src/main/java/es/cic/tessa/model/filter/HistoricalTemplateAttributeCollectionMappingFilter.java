package es.cic.tessa.model.filter;


import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import es.cic.tessa.common.filter.AttributeOrder;


public class HistoricalTemplateAttributeCollectionMappingFilter
{

    private TemplateAttributeCollectionMappingFilter templateAttributeCollectionMappingFilter = new TemplateAttributeCollectionMappingFilter();
    private Instant startChange;
    private Instant endChange;
    private Boolean withLastChange;
    private Collection<AttributeOrder> attributesOrder = new HashSet<>();

    public TemplateAttributeCollectionMappingFilter getTemplateAttributeCollectionMappingFilter()
    {

	return templateAttributeCollectionMappingFilter;
    }


    public void setTemplateAttributeCollectionMappingFilter(TemplateAttributeCollectionMappingFilter templateAttributeCollectionMappingFilter)
    {

	this.templateAttributeCollectionMappingFilter = templateAttributeCollectionMappingFilter;
    }


    public Instant getStartChange()
    {

	return startChange;
    }


    public void setStartChange(Instant startChange)
    {

	this.startChange = startChange;
    }


    public Instant getEndChange()
    {

	return endChange;
    }


    public void setEndChange(Instant endChange)
    {

	this.endChange = endChange;
    }


    public Boolean getWithLastChange()
    {

	return withLastChange;
    }


    public void setWithLastChange(Boolean withLastChange)
    {

	this.withLastChange = withLastChange;
    }


    public Collection<AttributeOrder> getAttributesOrder()
    {

	return attributesOrder;
    }


    public void setAttributesOrder(Collection<AttributeOrder> attributesOrder)
    {

	this.attributesOrder = attributesOrder;
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(attributesOrder, endChange, startChange, templateAttributeCollectionMappingFilter, withLastChange);
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
	HistoricalTemplateAttributeCollectionMappingFilter other = (HistoricalTemplateAttributeCollectionMappingFilter) obj;
	return Objects.equals(attributesOrder, other.attributesOrder) && Objects.equals(endChange, other.endChange) && Objects.equals(startChange, other.startChange) && Objects.equals(templateAttributeCollectionMappingFilter, other.templateAttributeCollectionMappingFilter) && Objects.equals(withLastChange, other.withLastChange);
    }

}
