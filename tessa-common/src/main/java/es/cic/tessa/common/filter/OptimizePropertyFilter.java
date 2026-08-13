package es.cic.tessa.common.filter;


import java.util.Objects;
import es.cic.tessa.common.model.types.ArithmeticOperatorType;
import es.cic.tessa.common.model.types.LogicalOperatorType;


public class OptimizePropertyFilter
{

    private String propertyName;
    private Object propertyValue;
    private LogicalOperatorType logicalOperatorType = LogicalOperatorType.AND;
    private ArithmeticOperatorType aritmeticalOperatorType = ArithmeticOperatorType.EQUALS;

    public OptimizePropertyFilter()
    {

	super();
    }


    public OptimizePropertyFilter(String propertyName, Object propertyValue)
    {

	super();
	this.propertyName = propertyName;
	this.propertyValue = propertyValue;
	this.logicalOperatorType = LogicalOperatorType.AND;
	this.aritmeticalOperatorType = ArithmeticOperatorType.EQUALS;
    }


    public OptimizePropertyFilter(String propertyName, Object propertyValue, ArithmeticOperatorType aritmeticalOperatorType)
    {

	super();
	this.propertyName = propertyName;
	this.propertyValue = propertyValue;
	this.aritmeticalOperatorType = aritmeticalOperatorType;
    }


    public OptimizePropertyFilter(String propertyName, Object propertyValue, LogicalOperatorType logicalOperatorType, ArithmeticOperatorType aritmeticalOperatorType)
    {

	super();
	this.propertyName = propertyName;
	this.propertyValue = propertyValue;
	this.logicalOperatorType = logicalOperatorType;
	this.aritmeticalOperatorType = aritmeticalOperatorType;
    }


    public String getPropertyName()
    {

	return propertyName;
    }


    public void setPropertyName(String propertyName)
    {

	this.propertyName = propertyName;
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


    public ArithmeticOperatorType getAritmeticalOperatorType()
    {

	return aritmeticalOperatorType;
    }


    public void setAritmeticalOperatorType(ArithmeticOperatorType aritmeticalOperatorType)
    {

	this.aritmeticalOperatorType = aritmeticalOperatorType;
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(aritmeticalOperatorType, logicalOperatorType, propertyName, propertyValue);
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
	OptimizePropertyFilter other = (OptimizePropertyFilter) obj;
	return aritmeticalOperatorType == other.aritmeticalOperatorType && logicalOperatorType == other.logicalOperatorType && Objects.equals(propertyName, other.propertyName) && Objects.equals(propertyValue, other.propertyValue);
    }


    @Override
    public String toString()
    {

	return "OptimizeProperyFilter [propertyName=" + propertyName + ", propertyValue=" + propertyValue + ", logicalOperatorType=" + logicalOperatorType + ", aritmeticalOperatorType=" + aritmeticalOperatorType + "]";
    }

}
