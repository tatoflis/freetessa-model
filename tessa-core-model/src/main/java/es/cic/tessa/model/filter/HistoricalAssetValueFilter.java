package es.cic.tessa.model.filter;


import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import es.cic.tessa.common.filter.AttributeOrder;


public class HistoricalAssetValueFilter
{

    private AssetValueFilter assetValueFilter = new AssetValueFilter();
    private Instant startChange;
    private Instant endChange;
    private Instant asOf;
    private Boolean withLastChange = false;
    private Collection<AttributeOrder> attributeOrders = new HashSet<>();

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


    public Instant getAsOf()
    {

	return asOf;
    }


    public void setAsOf(Instant asOf)
    {

	this.asOf = asOf;
    }


    public Boolean getWithLastChange()
    {

	return withLastChange;
    }


    public void setWithLastChange(Boolean withLastChange)
    {

	this.withLastChange = withLastChange;
    }


    public AssetValueFilter getAssetValueFilter()
    {

	return assetValueFilter;
    }


    public void setAssetValueFilter(AssetValueFilter assetValueFilter)
    {

	this.assetValueFilter = assetValueFilter;
    }


    public Collection<AttributeOrder> getAttributeOrders()
    {

	return attributeOrders;
    }


    public void setAttributeOrders(Collection<AttributeOrder> attributeOrders)
    {

	this.attributeOrders = attributeOrders;
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(endChange, startChange, withLastChange);
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
	HistoricalAssetValueFilter other = (HistoricalAssetValueFilter) obj;
	return Objects.equals(endChange, other.endChange) && Objects.equals(startChange, other.startChange) && Objects.equals(withLastChange, other.withLastChange);
    }

}
