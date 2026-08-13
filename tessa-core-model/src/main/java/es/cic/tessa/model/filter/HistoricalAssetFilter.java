package es.cic.tessa.model.filter;


import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import es.cic.tessa.common.filter.AttributeOrder;


public class HistoricalAssetFilter extends AssetFilter
{

    private Instant startChange;
    private Instant endChange;
    private Instant asOf;
    private String operation;
    private Set<Long> originalAssetIds = new HashSet<>();
    private Collection<AttributeOrder> attributesOrder = new HashSet<>();

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


    public String getOperation()
    {

	return operation;
    }


    public void setOperation(String operation)
    {

	this.operation = operation;
    }


    public Set<Long> getOriginalAssetIds()
    {

	return originalAssetIds;
    }


    public void setOriginalAssetIds(Set<Long> originalAssetIds)
    {

	this.originalAssetIds = originalAssetIds;
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

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(asOf, attributesOrder, endChange, operation, originalAssetIds, startChange);
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
	HistoricalAssetFilter other = (HistoricalAssetFilter) obj;
	return Objects.equals(asOf, other.asOf) && Objects.equals(attributesOrder, other.attributesOrder) && Objects.equals(endChange, other.endChange) && Objects.equals(operation, other.operation) && Objects.equals(originalAssetIds, other.originalAssetIds) && Objects.equals(startChange, other.startChange);
    }

}
