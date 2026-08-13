package es.cic.tessa.common.filter;


import es.cic.tessa.common.model.TessaConstants;
import es.cic.tessa.common.model.types.OrderType;


public class AttributeOrder
{

    private String attributeName = TessaConstants.NAME;
    private OrderType orderType = OrderType.ASC;

    public AttributeOrder()
    {

    }


    public AttributeOrder(String attributeName, OrderType orderType)
    {

	this.attributeName = attributeName;
	this.orderType = orderType;
    }


    public String getAttributeName()
    {

	return attributeName;
    }


    public void setAttributeName(String attributeName)
    {

	this.attributeName = attributeName;
    }


    public OrderType getOrderType()
    {

	return orderType;
    }


    public void setOrderType(OrderType orderType)
    {

	this.orderType = orderType;
    }


    @Override
    public String toString()
    {

	return "AttributeOrder [attributeName=" + attributeName + ", orderType=" + orderType + "]";
    }

}
