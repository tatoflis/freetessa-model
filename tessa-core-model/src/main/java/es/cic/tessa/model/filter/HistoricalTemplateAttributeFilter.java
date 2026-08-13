package es.cic.tessa.model.filter;


import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import es.cic.tessa.common.filter.AttributeOrder;


public class HistoricalTemplateAttributeFilter
{

    private TemplateAttributeFilter templateAttributeFilter = new TemplateAttributeFilter();
    private Instant startChange;
    private Instant endChange;
    private Instant asOf;
    private Boolean withLastChange;
    private Collection<AttributeOrder> attributesOrder = new HashSet<>();

    public Boolean getWithLastChange()
    {

	return withLastChange;
    }


    public void setWithLastChange(Boolean withLastChange)
    {

	this.withLastChange = withLastChange;
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


    public void setEndChange(Instant endCahnge)
    {

	this.endChange = endCahnge;
    }


    public Instant getAsOf()
    {

	return asOf;
    }


    public void setAsOf(Instant asOf)
    {

	this.asOf = asOf;
    }


    public Collection<AttributeOrder> getAttributesOrder()
    {

	return attributesOrder;
    }


    public void setAttributesOrder(Collection<AttributeOrder> attributesOrder)
    {

	this.attributesOrder = attributesOrder;
    }


    public TemplateAttributeFilter getTemplateAttributeFilter()
    {

	return templateAttributeFilter;
    }


    public void setTemplateAttributeFilter(TemplateAttributeFilter templateAttributeFilter)
    {

	this.templateAttributeFilter = templateAttributeFilter;
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(attributesOrder, endChange, startChange, templateAttributeFilter, withLastChange);
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
	HistoricalTemplateAttributeFilter other = (HistoricalTemplateAttributeFilter) obj;
	return Objects.equals(attributesOrder, other.attributesOrder) && Objects.equals(endChange, other.endChange) && Objects.equals(startChange, other.startChange) && Objects.equals(templateAttributeFilter, other.templateAttributeFilter) && Objects.equals(withLastChange, other.withLastChange);
    }

}
