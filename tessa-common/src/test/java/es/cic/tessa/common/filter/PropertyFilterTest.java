package es.cic.tessa.common.filter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import es.cic.tessa.common.model.types.ArithmeticOperatorType;
import es.cic.tessa.common.model.types.LogicalOperatorType;

class PropertyFilterTest
{

    @Test
    void constructorSimple_seteaPropiedadesYDefaults()
    {

	PropertyFilter filter = new PropertyFilter("name", "value");

	assertEquals("name", filter.getPropertyName());
	assertEquals("value", filter.getPropertyValue());
	assertEquals(LogicalOperatorType.AND, filter.getLogicalOperatorType());
	assertEquals(ArithmeticOperatorType.EQUALS, filter.getArithmeticOperatorType());
    }

    @Test
    void constructorConLogicalOperator_seteaOperador()
    {

	PropertyFilter filter = new PropertyFilter("name", "value", LogicalOperatorType.OR);

	assertEquals(LogicalOperatorType.OR, filter.getLogicalOperatorType());
    }

    @Test
    void constructorConArithmeticOperator_seteaOperador()
    {

	PropertyFilter filter = new PropertyFilter("name", "value", ArithmeticOperatorType.CONTAINS);

	assertEquals(ArithmeticOperatorType.CONTAINS, filter.getArithmeticOperatorType());
    }

    @Test
    void constructorConLogicalYArithmetic_seteaAmbos()
    {

	PropertyFilter filter = new PropertyFilter("name", "value", LogicalOperatorType.OR, ArithmeticOperatorType.NOT);

	assertEquals(LogicalOperatorType.OR, filter.getLogicalOperatorType());
	assertEquals(ArithmeticOperatorType.NOT, filter.getArithmeticOperatorType());
    }

    @Test
    void constructorConDefaultOrder_creaAttributesOrder()
    {

	PropertyFilter filter = new PropertyFilter("name", "value", true);

	assertNotNull(filter.getAttributesOrder());
	assertEquals(1, filter.getAttributesOrder().size());
    }

    @Test
    void equals_mismosValores_sonIguales()
    {

	PropertyFilter f1 = new PropertyFilter("name", "value");
	PropertyFilter f2 = new PropertyFilter("name", "value");

	assertEquals(f1, f2);
	assertEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    void equals_distintosPropNames_noSonIguales()
    {

	PropertyFilter f1 = new PropertyFilter("name1", "value");
	PropertyFilter f2 = new PropertyFilter("name2", "value");

	assertNotEquals(f1, f2);
    }
}
