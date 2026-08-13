package es.cic.tessa.model.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import es.cic.tessa.model.AssetValue;
import es.cic.tessa.model.Function;
import es.cic.tessa.model.Template;
import es.cic.tessa.model.TemplateAttribute;

class FunctionUtilsTest
{

    @Test
    void retrieveCypherExpressionProperties_conTemplateAttributeConExpression_devuelveJson()
    {

	Function fn = new Function("SUM(x)", null, null, null, null, null, null);

	TemplateAttribute ta = new TemplateAttribute();
	ta.setExpressionProperties(fn);

	String result = FunctionUtils.retrieveCypherExpressionProperties(ta);

	assertNotNull(result);
	assertTrue(result.contains("expressionFunction"));
	assertTrue(result.contains("SUM(x)"));
    }

    @Test
    void retrieveCypherExpressionProperties_conTemplateAttributeConEvent_devuelveJson()
    {

	Function fn = new Function();
	fn.setExpressionEvent("ON_CHANGE");

	TemplateAttribute ta = new TemplateAttribute();
	ta.setExpressionProperties(fn);

	String result = FunctionUtils.retrieveCypherExpressionProperties(ta);

	assertNotNull(result);
	assertTrue(result.contains("ON_CHANGE"));
    }

    @Test
    void retrieveCypherExpressionProperties_conTemplateAttributeSinExpression_devuelveNull()
    {

	TemplateAttribute ta = new TemplateAttribute();
	ta.setExpressionProperties(new Function());

	String result = FunctionUtils.retrieveCypherExpressionProperties(ta);

	assertNull(result);
    }

    @Test
    void retrieveCypherExpressionProperties_conAssetValueConExpression_devuelveJson()
    {

	Function fn = new Function("AVG(y)", null, null, null, null, null, null);

	AssetValue av = new AssetValue();
	av.setExpressionProperties(fn);

	String result = FunctionUtils.retrieveCypherExpressionProperties(av);

	assertNotNull(result);
	assertTrue(result.contains("AVG(y)"));
    }

    @Test
    void retrieveCypherExpressionProperties_conAssetValueConCronExpression_devuelveJson()
    {

	Function fn = new Function();
	fn.setCronExpression("0 0 * * *");

	AssetValue av = new AssetValue();
	av.setExpressionProperties(fn);

	String result = FunctionUtils.retrieveCypherExpressionProperties(av);

	assertNotNull(result);
	assertTrue(result.contains("0 0 * * *"));
    }

    @Test
    void retrieveCypherExpressionProperties_conAssetValueSinPropiedadesRelevantes_devuelveNull()
    {

	AssetValue av = new AssetValue();
	av.setExpressionProperties(new Function());

	String result = FunctionUtils.retrieveCypherExpressionProperties(av);

	assertNull(result);
    }

    @Test
    void retrieveCypherExpressionProperties_conOtroTipoTessaElement_devuelveNull()
    {

	Template template = new Template();

	String result = FunctionUtils.retrieveCypherExpressionProperties(template);

	assertNull(result);
    }
}
