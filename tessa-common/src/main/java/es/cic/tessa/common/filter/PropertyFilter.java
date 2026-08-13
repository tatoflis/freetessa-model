package es.cic.tessa.common.filter;


import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import es.cic.tessa.common.model.TessaConstants;
import es.cic.tessa.common.model.types.ArithmeticOperatorType;
import es.cic.tessa.common.model.types.LogicalOperatorType;
import es.cic.tessa.common.model.types.OrderType;


public class PropertyFilter
{

    private String propertyName;
    private Object propertyValue;
    private LogicalOperatorType logicalOperatorType = LogicalOperatorType.AND;
    private ArithmeticOperatorType aritmeticalOperatorType = ArithmeticOperatorType.EQUALS;
    private Collection<AttributeOrder> attributesOrder = new LinkedHashSet<>();

    public PropertyFilter()
    {

    }


    public PropertyFilter(Collection<AttributeOrder> attributesOrder)
    {

	this.attributesOrder = attributesOrder;
    }


    public PropertyFilter(String propertyName, Object propertyValue)
    {

	this(propertyName, propertyValue, false);

    }


    public PropertyFilter(String propertyName, Object propertyValue, boolean defaultOrder)
    {

	this.propertyName = propertyName;
	this.propertyValue = propertyValue;

	if(defaultOrder)
	{
	    this.attributesOrder = new HashSet<AttributeOrder>();
	    AttributeOrder attributeOrder = new AttributeOrder(TessaConstants.NAME, OrderType.ASC);
	    this.attributesOrder.add(attributeOrder);
	}

    }


    public PropertyFilter(String propertyName, Object propertyValue, LogicalOperatorType logicalOperatorType)
    {

	this.propertyName = propertyName;
	this.propertyValue = propertyValue;
	this.logicalOperatorType = logicalOperatorType;

    }


    public PropertyFilter(String propertyName, Object propertyValue, LogicalOperatorType logicalOperatorType, ArithmeticOperatorType arithmeticOperatorType)
    {

	this.propertyName = propertyName;
	this.propertyValue = propertyValue;
	this.logicalOperatorType = logicalOperatorType;
	this.aritmeticalOperatorType = arithmeticOperatorType;

    }


    public PropertyFilter(String propertyName, Object propertyValue, LogicalOperatorType logicalOperatorType, boolean defaultOrder)
    {

	this.propertyName = propertyName;
	this.propertyValue = propertyValue;
	this.logicalOperatorType = logicalOperatorType;

	if(defaultOrder)
	{
	    Collection<AttributeOrder> attributesOrder = new HashSet<AttributeOrder>();
	    AttributeOrder attributeOrder = new AttributeOrder(TessaConstants.NAME, OrderType.ASC);
	    attributesOrder.add(attributeOrder);
	}
    }


    public PropertyFilter(String propertyName, Object propertyValue, ArithmeticOperatorType aritmeticalOperatorType)
    {

	this.propertyName = propertyName;
	this.propertyValue = propertyValue;
	this.aritmeticalOperatorType = aritmeticalOperatorType;
    }


    public PropertyFilter(String propertyName, Object propertyValue, ArithmeticOperatorType aritmeticalOperatorType, boolean defaultOrder)
    {

	this.propertyName = propertyName;
	this.propertyValue = propertyValue;
	this.aritmeticalOperatorType = aritmeticalOperatorType;

	if(defaultOrder)
	{
	    Collection<AttributeOrder> attributesOrder = new HashSet<AttributeOrder>();
	    AttributeOrder attributeOrder = new AttributeOrder(TessaConstants.NAME, OrderType.ASC);
	    attributesOrder.add(attributeOrder);
	}
    }


    public String getPropertyName()
    {

	return propertyName;
    }


    public Object getPropertyValue()
    {

	return propertyValue;
    }


    public void setPropertyValue(Object propertyValue)
    {

	this.propertyValue = propertyValue;
    }


    public LogicalOperatorType getLogicalOperatorType()
    {

	return logicalOperatorType;
    }


    public void setLogicalOperatorType(LogicalOperatorType logicalOperatorType)
    {

	this.logicalOperatorType = logicalOperatorType;
    }


    public ArithmeticOperatorType getArithmeticOperatorType()
    {

	return aritmeticalOperatorType;
    }


    public void setArithmeticOperatorType(ArithmeticOperatorType aritmeticalOperatorType)
    {

	this.aritmeticalOperatorType = aritmeticalOperatorType;
    }


    @Override
    public String toString()
    {

	return "PropertyFilter [propertyName=" + propertyName + ", propertyValue=" + propertyValue + ", logicalOperatorType=" + logicalOperatorType + ", aritmeticalOperatorType=" + aritmeticalOperatorType + "]";
    }


    public void setPropertyName(String propertyName)
    {

	this.propertyName = propertyName;
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

	return Objects.hash(aritmeticalOperatorType, attributesOrder, logicalOperatorType, propertyName, propertyValue);
    }


    @Override
    public boolean equals(Object obj)
    {

	if(this == obj)
	    return true;
	if(!(obj instanceof PropertyFilter))
	    return false;
	PropertyFilter other = (PropertyFilter) obj;
	return aritmeticalOperatorType == other.aritmeticalOperatorType && Objects.equals(attributesOrder, other.attributesOrder) && logicalOperatorType == other.logicalOperatorType && Objects.equals(propertyName, other.propertyName) && Objects.equals(propertyValue, other.propertyValue);
    }

}
