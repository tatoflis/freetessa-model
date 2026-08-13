package es.cic.tessa.model.historical.dto;


import java.util.Objects;
import es.cic.tessa.model.dto.support.AbstractEntityResponse;


public class HistoricalDefaultValueResponse extends AbstractEntityResponse
{

    private static final long serialVersionUID = 1L;

    private int order;
    private String value;

    public int getOrder()
    {

	return order;
    }


    public void setOrder(int order)
    {

	this.order = order;
    }


    public String getValue()
    {

	return value;
    }


    public void setValue(String value)
    {

	this.value = value;
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(order, value);
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
	HistoricalDefaultValueResponse other = (HistoricalDefaultValueResponse) obj;
	return order == other.order && Objects.equals(value, other.value);
    }


    @Override
    public String toString()
    {

	return "HistoricalDefaultValueResponse [order=" + order + ", value=" + value + "]";
    }

}
