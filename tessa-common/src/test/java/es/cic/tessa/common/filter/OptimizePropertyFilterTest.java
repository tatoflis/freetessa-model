package es.cic.tessa.common.filter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import es.cic.tessa.common.model.types.ArithmeticOperatorType;
import es.cic.tessa.common.model.types.LogicalOperatorType;

class OptimizePropertyFilterTest
{

    @Test
    void constructorDefault_seteaDefaults()
    {

	OptimizePropertyFilter filter = new OptimizePropertyFilter();

	assertNull(filter.getPropertyName());
	assertNull(filter.getPropertyValue());
	assertEquals(LogicalOperatorType.AND, filter.getLogicalOperatorType());
	assertEquals(ArithmeticOperatorType.EQUALS, filter.getAritmeticalOperatorType());
    }

    @Test
    void constructorConNameYValue_seteaDefaultOperators()
    {

	OptimizePropertyFilter filter = new OptimizePropertyFilter("name", "value");

	assertEquals("name", filter.getPropertyName());
	assertEquals("value", filter.getPropertyValue());
	assertEquals(LogicalOperatorType.AND, filter.getLogicalOperatorType());
	assertEquals(ArithmeticOperatorType.EQUALS, filter.getAritmeticalOperatorType());
    }

    @Test
    void constructorConArithmeticOperator_seteaOperador()
    {

	OptimizePropertyFilter filter = new OptimizePropertyFilter("name", "value", ArithmeticOperatorType.CONTAINS);

	assertEquals(ArithmeticOperatorType.CONTAINS, filter.getAritmeticalOperatorType());
    }

    @Test
    void constructorConAmbosOperadores_seteaAmbos()
    {

	OptimizePropertyFilter filter = new OptimizePropertyFilter("name", "value", LogicalOperatorType.OR, ArithmeticOperatorType.NOT);

	assertEquals(LogicalOperatorType.OR, filter.getLogicalOperatorType());
	assertEquals(ArithmeticOperatorType.NOT, filter.getAritmeticalOperatorType());
    }

    @Test
    void equals_mismosValores_sonIguales()
    {

	OptimizePropertyFilter f1 = new OptimizePropertyFilter("name", "value");
	OptimizePropertyFilter f2 = new OptimizePropertyFilter("name", "value");

	assertEquals(f1, f2);
	assertEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    void equals_distintosPropNames_noSonIguales()
    {

	OptimizePropertyFilter f1 = new OptimizePropertyFilter("name1", "value");
	OptimizePropertyFilter f2 = new OptimizePropertyFilter("name2", "value");

	assertNotEquals(f1, f2);
    }

    @Test
    void equals_conNull_noSonIguales()
    {

	OptimizePropertyFilter f1 = new OptimizePropertyFilter("name", "value");

	assertNotEquals(f1, null);
    }

    @Test
    void toString_contieneNombreYValor()
    {

	OptimizePropertyFilter filter = new OptimizePropertyFilter("prop", "val");

	String str = filter.toString();
	assertTrue(str.contains("prop"));
	assertTrue(str.contains("val"));
    }
}
