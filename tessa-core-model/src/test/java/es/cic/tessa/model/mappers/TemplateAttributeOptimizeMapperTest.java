package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.ExpressionParam;
import es.cic.tessa.model.Function;
import es.cic.tessa.model.Template;
import es.cic.tessa.model.TemplateAttribute;
import es.cic.tessa.model.TemplateReference;
import es.cic.tessa.model.optimize.TemplateAttributeOptimize;

class TemplateAttributeOptimizeMapperTest
{

    @Test
    void toOptimize_conNull_devuelveNull()
    {

	assertNull(TemplateAttributeOptimizeMapper.toOptimize(null));
    }

    @Test
    void toOptimize_conCamposBasicos_mapeaCorrectamente()
    {

	TemplateAttribute ta = new TemplateAttribute();
	ta.setCustomId(1L);
	ta.setName("attr1");
	ta.setGroups(Set.of("g1"));
	ta.setType("INTEGER");
	ta.setCollection(true);
	ta.setCalculatedValue("SUM(x)");

	TemplateAttributeOptimize result = TemplateAttributeOptimizeMapper.toOptimize(ta);

	assertNotNull(result);
	assertEquals(1L, result.getId());
	assertEquals("attr1", result.getName());
	assertTrue(result.getGroups().contains("g1"));
	assertEquals("INTEGER", result.getType());
	assertTrue(result.getCollection());
	assertEquals("SUM(x)", result.getCalculatedValue());
    }

    @Test
    void toOptimize_conExpressionProperties_mapeaCamposFunction()
    {

	Function fn = new Function("expr", true, false, "0 0 * * *", "evt", "UTC", 1000L);

	TemplateAttribute ta = new TemplateAttribute();
	ta.setCustomId(1L);
	ta.setName("attr");
	ta.setExpressionProperties(fn);

	TemplateAttributeOptimize result = TemplateAttributeOptimizeMapper.toOptimize(ta);

	assertEquals("expr", result.getExpressionFunction());
	assertEquals("0 0 * * *", result.getCronExpression());
	assertEquals("evt", result.getExpressionEvent());
	assertEquals(1000L, result.getCronDelay());
	assertEquals("UTC", result.getCronTimeZone());
	assertFalse(result.getIgnoreNoData());
	assertTrue(result.getRefillingCalculation());
    }

    @Test
    void toOptimize_sinExpressionProperties_noMapeaFunction()
    {

	TemplateAttribute ta = new TemplateAttribute();
	ta.setCustomId(1L);
	ta.setName("attr");
	ta.setExpressionProperties(null);

	TemplateAttributeOptimize result = TemplateAttributeOptimizeMapper.toOptimize(ta);

	assertNull(result.getExpressionFunction());
    }

    @Test
    void toOptimize_conTemplate_mapeaTemplateOptimize()
    {

	Template template = new Template();
	template.setCustomId(10L);
	template.setName("tmpl");
	template.setGroups(Set.of("tg"));

	TemplateAttribute ta = new TemplateAttribute();
	ta.setCustomId(1L);
	ta.setName("attr");
	ta.setTemplate(template);

	TemplateAttributeOptimize result = TemplateAttributeOptimizeMapper.toOptimize(ta);

	assertNotNull(result.getTemplateOptimize());
	assertEquals(10L, result.getTemplateOptimize().getId());
	assertEquals("tmpl", result.getTemplateOptimize().getName());
    }

    @Test
    void toOptimize_sinTemplate_templateOptimizeEsNull()
    {

	TemplateAttribute ta = new TemplateAttribute();
	ta.setCustomId(1L);
	ta.setName("attr");

	TemplateAttributeOptimize result = TemplateAttributeOptimizeMapper.toOptimize(ta);

	assertNull(result.getTemplateOptimize());
    }

    @Test
    void toOptimize_conTemplateReference_mapeaReferenceOptimize()
    {

	Template refTemplate = new Template();
	refTemplate.setCustomId(20L);
	refTemplate.setName("ref-tmpl");
	refTemplate.setGroups(Set.of("rg"));

	TemplateReference ref = new TemplateReference();
	ref.setTemplate(refTemplate);

	TemplateAttribute ta = new TemplateAttribute();
	ta.setCustomId(1L);
	ta.setName("attr");
	ta.setTemplateReference(ref);

	TemplateAttributeOptimize result = TemplateAttributeOptimizeMapper.toOptimize(ta);

	assertNotNull(result.getTemplateReferenceOptimize());
	assertEquals(20L, result.getTemplateReferenceOptimize().getId());
	assertEquals("ref-tmpl", result.getTemplateReferenceOptimize().getName());
    }

    @Test
    void toOptimize_conTemplateReferenceSinTemplate_referenceOptimizeEsNull()
    {

	TemplateReference ref = new TemplateReference();
	ref.setTemplate(null);

	TemplateAttribute ta = new TemplateAttribute();
	ta.setCustomId(1L);
	ta.setName("attr");
	ta.setTemplateReference(ref);

	TemplateAttributeOptimize result = TemplateAttributeOptimizeMapper.toOptimize(ta);

	assertNull(result.getTemplateReferenceOptimize());
    }

    @Test
    void toOptimize_conExpressionParams_mapeaParams()
    {

	ExpressionParam p1 = new ExpressionParam();
	p1.setCustomId(101L);
	p1.setName("p1");
	p1.setGroups(Set.of("pg"));

	Set<ExpressionParam> params = new HashSet<>();
	params.add(p1);

	TemplateAttribute ta = new TemplateAttribute();
	ta.setCustomId(1L);
	ta.setName("attr");
	ta.setExpressionParams(params);

	TemplateAttributeOptimize result = TemplateAttributeOptimizeMapper.toOptimize(ta);

	assertNotNull(result.getExpressionParams());
	assertEquals(1, result.getExpressionParams().size());
    }

    @Test
    void toOptimize_sinExpressionParams_coleccionVacia()
    {

	TemplateAttribute ta = new TemplateAttribute();
	ta.setCustomId(1L);
	ta.setName("attr");

	TemplateAttributeOptimize result = TemplateAttributeOptimizeMapper.toOptimize(ta);

	assertNotNull(result.getExpressionParams());
	assertTrue(result.getExpressionParams().isEmpty());
    }
}
