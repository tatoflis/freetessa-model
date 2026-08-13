package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.Function;
import es.cic.tessa.model.HistoricalExpressionParam;
import es.cic.tessa.model.HistoricalTemplate;
import es.cic.tessa.model.HistoricalTemplateAttribute;
import es.cic.tessa.model.HistoricalTemplateReference;
import es.cic.tessa.model.optimize.TemplateAttributeOptimize;

class HistoricalTemplateAttributeOptimizeMapperTest
{

    @Test
    void toOptimize_conNull_devuelveNull()
    {

	assertNull(HistoricalTemplateAttributeOptimizeMapper.toOptimize(null));
    }

    @Test
    void toOptimize_conCamposBasicos_mapeaCorrectamente()
    {

	HistoricalTemplateAttribute ta = new HistoricalTemplateAttribute();
	ta.setCustomId(50L);
	ta.setName("attr");
	ta.setGroups(Set.of("ag"));
	ta.setType("STRING");
	ta.setCollection(true);
	ta.setCalculatedValue("calc");

	TemplateAttributeOptimize result = HistoricalTemplateAttributeOptimizeMapper.toOptimize(ta);

	assertNotNull(result);
	assertEquals(50L, result.getId());
	assertEquals("attr", result.getName());
	assertEquals("STRING", result.getType());
	assertTrue(result.getCollection());
	assertEquals("calc", result.getCalculatedValue());
    }

    @Test
    void toOptimize_conFunctionYaDeserializado_mapeaCamposExpresion()
    {

	Function fn = new Function("expr1", true, false, "0 0 * * *", "event1", "UTC", 5000L);

	HistoricalTemplateAttribute ta = new HistoricalTemplateAttribute();
	ta.setCustomId(50L);
	ta.setName("attr");
	ta.setExpressionProperties(fn);

	TemplateAttributeOptimize result = HistoricalTemplateAttributeOptimizeMapper.toOptimize(ta);

	assertEquals("expr1", result.getExpressionFunction());
	assertEquals("0 0 * * *", result.getCronExpression());
	assertEquals("event1", result.getExpressionEvent());
	assertEquals(5000L, result.getCronDelay());
	assertEquals("UTC", result.getCronTimeZone());
	assertFalse(result.getIgnoreNoData());
	assertTrue(result.getRefillingCalculation());
    }

    @Test
    void toOptimize_conTemplate_mapeaTemplateOptimize()
    {

	HistoricalTemplate template = new HistoricalTemplate();
	template.setCustomId(100L);
	template.setName("tmpl");
	template.setGroups(Set.of("tg"));

	HistoricalTemplateAttribute ta = new HistoricalTemplateAttribute();
	ta.setCustomId(50L);
	ta.setName("attr");
	ta.setHistoricalTemplate(template);

	TemplateAttributeOptimize result = HistoricalTemplateAttributeOptimizeMapper.toOptimize(ta);

	assertNotNull(result.getTemplateOptimize());
	assertEquals(100L, result.getTemplateOptimize().getId());
	assertEquals("tmpl", result.getTemplateOptimize().getName());
    }

    @Test
    void toOptimize_conTemplateReference_mapeaTemplateReferenceOptimize()
    {

	HistoricalTemplate refTemplate = new HistoricalTemplate();
	refTemplate.setCustomId(300L);
	refTemplate.setName("TessaAlarmDefinition");
	refTemplate.setGroups(Set.of("SG4"));

	HistoricalTemplateReference templateReference = new HistoricalTemplateReference();
	templateReference.setHistoricalTemplate(refTemplate);

	HistoricalTemplateAttribute ta = new HistoricalTemplateAttribute();
	ta.setCustomId(50L);
	ta.setName("attr");
	ta.setHistoricalTemplateReference(templateReference);

	TemplateAttributeOptimize result = HistoricalTemplateAttributeOptimizeMapper.toOptimize(ta);

	assertNotNull(result.getTemplateReferenceOptimize());
	assertEquals(300L, result.getTemplateReferenceOptimize().getId());
	assertEquals("TessaAlarmDefinition", result.getTemplateReferenceOptimize().getName());
    }

    @Test
    void toOptimize_conTemplateReferenceSinTemplate_referenceOptimizeEsNull()
    {

	HistoricalTemplateReference templateReference = new HistoricalTemplateReference();
	templateReference.setHistoricalTemplate(null);

	HistoricalTemplateAttribute ta = new HistoricalTemplateAttribute();
	ta.setCustomId(50L);
	ta.setName("attr");
	ta.setHistoricalTemplateReference(templateReference);

	TemplateAttributeOptimize result = HistoricalTemplateAttributeOptimizeMapper.toOptimize(ta);

	assertNull(result.getTemplateReferenceOptimize());
    }

    @Test
    void toOptimize_conExpressionParams_mapeaParams()
    {

	HistoricalExpressionParam p1 = new HistoricalExpressionParam();
	p1.setCustomId(101L);
	p1.setName("param1");
	p1.setGroups(Set.of("pg"));

	HistoricalExpressionParam p2 = new HistoricalExpressionParam();
	p2.setCustomId(102L);
	p2.setName("param2");
	p2.setGroups(Set.of("pg"));

	Set<HistoricalExpressionParam> params = new HashSet<>();
	params.add(p1);
	params.add(p2);

	HistoricalTemplateAttribute ta = new HistoricalTemplateAttribute();
	ta.setCustomId(50L);
	ta.setName("attr");
	ta.setHistoricalExpressionParams(params);

	TemplateAttributeOptimize result = HistoricalTemplateAttributeOptimizeMapper.toOptimize(ta);

	assertNotNull(result.getExpressionParams());
	assertEquals(2, result.getExpressionParams().size());
    }

    @Test
    void toOptimize_sinExpressionParams_coleccionVacia()
    {

	HistoricalTemplateAttribute ta = new HistoricalTemplateAttribute();
	ta.setCustomId(50L);
	ta.setName("attr");

	TemplateAttributeOptimize result = HistoricalTemplateAttributeOptimizeMapper.toOptimize(ta);

	assertNotNull(result.getExpressionParams());
	assertTrue(result.getExpressionParams().isEmpty());
    }
}
