package es.cic.tessa.common.filter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import es.cic.tessa.common.model.types.ArithmeticOperatorType;
import es.cic.tessa.common.model.types.LogicalOperatorType;

class RelationshipFilterTest
{

    @Test
    void constructorDefault_seteaDefaultsCorrectos()
    {

	RelationshipFilter filter = new RelationshipFilter();

	assertNull(filter.getRelationType());
	assertNull(filter.getReferenceType());
	assertEquals(LogicalOperatorType.AND, filter.getLogicalOperatorType());
	assertEquals(ArithmeticOperatorType.EQUALS, filter.getAritmeticalOperatorType());
    }

    @Test
    void constructorConRelationType_seteaTipo()
    {

	RelationshipFilter filter = new RelationshipFilter("HAS_VALUE");

	assertEquals("HAS_VALUE", filter.getRelationType());
    }

    @Test
    void constructorConLogicalOperator_seteaOperador()
    {

	RelationshipFilter filter = new RelationshipFilter("DEFINED_BY", LogicalOperatorType.OR);

	assertEquals("DEFINED_BY", filter.getRelationType());
	assertEquals(LogicalOperatorType.OR, filter.getLogicalOperatorType());
    }

    @Test
    void constructorConArithmeticOperator_seteaOperador()
    {

	RelationshipFilter filter = new RelationshipFilter("HAS_VALUE", ArithmeticOperatorType.NOT);

	assertEquals("HAS_VALUE", filter.getRelationType());
	assertEquals(ArithmeticOperatorType.NOT, filter.getAritmeticalOperatorType());
    }

    @Test
    void settersAndGetters_funcionanCorrectamente()
    {

	RelationshipFilter filter = new RelationshipFilter();
	filter.setRelationType("REL");
	filter.setReferenceType("REF");
	filter.setLogicalOperatorType(LogicalOperatorType.OR);
	filter.setAritmeticalOperatorType(ArithmeticOperatorType.CONTAINS);

	assertEquals("REL", filter.getRelationType());
	assertEquals("REF", filter.getReferenceType());
	assertEquals(LogicalOperatorType.OR, filter.getLogicalOperatorType());
	assertEquals(ArithmeticOperatorType.CONTAINS, filter.getAritmeticalOperatorType());
    }
}
