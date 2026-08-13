package es.cic.tessa.lookup.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import org.junit.jupiter.api.Test;
import es.cic.tessa.lookup.expression.model.LookupParameters;

class ExpressionProcessUtilsTest
{

    @Test
    void splitExpression_conExpresionSimpleParentesis_devuelveLookupParams()
    {

	String expression = "SUM(123)";

	Collection<LookupParameters> result = ExpressionProcessUtils.splitExpression(expression);

	assertNotNull(result);
	assertEquals(1, result.size());

	LookupParameters params = result.iterator().next();
	assertEquals(-1L, params.getLookupId());
	assertEquals(123L, params.getAssetValueId());
    }

    @Test
    void splitExpression_conExpresionGrupal_devuelveLookupParams()
    {

	String expression = "{{10_[200]}}";

	Collection<LookupParameters> result = ExpressionProcessUtils.splitExpression(expression);

	assertNotNull(result);
	assertEquals(1, result.size());

	LookupParameters params = result.iterator().next();
	assertEquals(10L, params.getLookupId());
	assertEquals(200L, params.getAssetValueId());
    }

    @Test
    void splitExpression_conStringVacio_devuelveVacio()
    {

	Collection<LookupParameters> result = ExpressionProcessUtils.splitExpression("");

	assertNotNull(result);
	assertTrue(result.isEmpty());
    }

    @Test
    void splitExpression_conExpresionMalFormada_devuelveVacio()
    {

	Collection<LookupParameters> result = ExpressionProcessUtils.splitExpression("not-an-expression");

	assertNotNull(result);
	assertTrue(result.isEmpty());
    }

    @Test
    void splitExpression_conMultiplesExpresiones_devuelveMultiplesParams()
    {

	String expression = "SUM(100)+AVG(200)";

	Collection<LookupParameters> result = ExpressionProcessUtils.splitExpression(expression);

	assertNotNull(result);
	assertEquals(2, result.size());
    }
}
