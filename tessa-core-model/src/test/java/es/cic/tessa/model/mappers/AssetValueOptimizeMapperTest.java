package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.Asset;
import es.cic.tessa.model.AssetReference;
import es.cic.tessa.model.AssetValue;
import es.cic.tessa.model.ExpressionParam;
import es.cic.tessa.model.Function;
import es.cic.tessa.model.Template;
import es.cic.tessa.model.TemplateAttribute;
import es.cic.tessa.model.TemplateReference;
import es.cic.tessa.model.optimize.AssetValueOptimize;

class AssetValueOptimizeMapperTest
{

    @Test
    void toOptimize_conNull_devuelveNull()
    {

	assertNull(AssetValueOptimizeMapper.toOptimize(null));
    }

    @Test
    void toOptimize_conCamposBasicos_mapeaCorrectamente()
    {

	AssetValue av = new AssetValue();
	av.setCustomId(1L);
	av.setName("val-name");
	av.setGroups(Set.of("g1"));
	av.setValue("42");

	AssetValueOptimize result = AssetValueOptimizeMapper.toOptimize(av);

	assertNotNull(result);
	assertEquals(1L, result.getId());
	assertEquals("val-name", result.getName());
	assertEquals("42", result.getValue());
	assertTrue(result.getGroups().contains("g1"));
    }

    @Test
    void toOptimize_conIdBinaryNumerico_mapeaCorrectamente()
    {

	AssetValue av = new AssetValue();
	av.setCustomId(1L);
	av.setName("val");
	av.setIdBinary("12345");

	AssetValueOptimize result = AssetValueOptimizeMapper.toOptimize(av);

	assertEquals(12345L, result.getIdBinary());
    }

    @Test
    void toOptimize_conIdBinaryNoNumerico_omiteIdBinary()
    {

	AssetValue av = new AssetValue();
	av.setCustomId(1L);
	av.setName("val");
	av.setIdBinary("not-a-number");

	AssetValueOptimize result = AssetValueOptimizeMapper.toOptimize(av);

	assertNull(result.getIdBinary());
    }

    @Test
    void toOptimize_conIdBinaryVacio_omiteIdBinary()
    {

	AssetValue av = new AssetValue();
	av.setCustomId(1L);
	av.setName("val");
	av.setIdBinary("");

	AssetValueOptimize result = AssetValueOptimizeMapper.toOptimize(av);

	assertNull(result.getIdBinary());
    }

    @Test
    void toOptimize_conIdBinaryNull_omiteIdBinary()
    {

	AssetValue av = new AssetValue();
	av.setCustomId(1L);
	av.setName("val");
	av.setIdBinary(null);

	AssetValueOptimize result = AssetValueOptimizeMapper.toOptimize(av);

	assertNull(result.getIdBinary());
    }

    @Test
    void toOptimize_conFunction_mapeaCamposExpresion()
    {

	Function fn = new Function("expr1", true, false, "0 0 * * *", "event1", "UTC", 5000L);

	AssetValue av = new AssetValue();
	av.setCustomId(1L);
	av.setName("val");
	av.setExpressionProperties(fn);

	AssetValueOptimize result = AssetValueOptimizeMapper.toOptimize(av);

	assertEquals("expr1", result.getExpressionFunction());
	assertEquals("0 0 * * *", result.getCronExpression());
	assertEquals("event1", result.getExpressionEvent());
	assertEquals(5000L, result.getCronDelay());
	assertEquals("UTC", result.getCronTimeZone());
	assertFalse(result.getIgnoreNoData());
	assertTrue(result.getRefillingCalculation());
    }

    @Test
    void toOptimize_sinFunction_noMapeaCamposExpresion()
    {

	AssetValue av = new AssetValue();
	av.setCustomId(1L);
	av.setName("val");
	av.setExpressionProperties(null);

	AssetValueOptimize result = AssetValueOptimizeMapper.toOptimize(av);

	assertNull(result.getExpressionFunction());
	assertNull(result.getCronExpression());
    }

    @Test
    void toOptimize_conAsset_mapeaAssetOptimize()
    {

	Asset asset = new Asset();
	asset.setCustomId(10L);
	asset.setName("parent-asset");
	asset.setGroups(Set.of("g"));

	AssetValue av = new AssetValue();
	av.setCustomId(1L);
	av.setName("val");
	av.setAsset(asset);

	AssetValueOptimize result = AssetValueOptimizeMapper.toOptimize(av);

	assertNotNull(result.getAssetOptimize());
	assertEquals(10L, result.getAssetOptimize().getId());
	assertEquals("parent-asset", result.getAssetOptimize().getName());
    }

    @Test
    void toOptimize_sinAsset_assetOptimizeEsNull()
    {

	AssetValue av = new AssetValue();
	av.setCustomId(1L);
	av.setName("val");

	AssetValueOptimize result = AssetValueOptimizeMapper.toOptimize(av);

	assertNull(result.getAssetOptimize());
    }

    @Test
    void toOptimize_conAssetReference_mapeaReferenceOptimize()
    {

	Asset refAsset = new Asset();
	refAsset.setCustomId(20L);
	refAsset.setName("ref-asset");
	refAsset.setGroups(Set.of("rg"));

	AssetReference ref = new AssetReference();
	ref.setAsset(refAsset);

	AssetValue av = new AssetValue();
	av.setCustomId(1L);
	av.setName("val");
	av.setAssetReference(ref);

	AssetValueOptimize result = AssetValueOptimizeMapper.toOptimize(av);

	assertNotNull(result.getAssetReferenceOptimize());
	assertEquals(20L, result.getAssetReferenceOptimize().getId());
	assertEquals("ref-asset", result.getAssetReferenceOptimize().getName());
    }


    @Test
    void toOptimize_conAssetReferenceConTemplate_mapeaTemplateOptimize()
    {

	Template refTemplate = new Template();
	refTemplate.setCustomId(200L);
	refTemplate.setName("TessaAlarmDefinition");
	refTemplate.setGroups(Set.of("SG4"));
	refTemplate.setType("Complex");

	Asset refAsset = new Asset();
	refAsset.setCustomId(20L);
	refAsset.setName("ref-asset");
	refAsset.setGroups(Set.of("rg"));
	refAsset.setTemplate(refTemplate);

	AssetReference ref = new AssetReference();
	ref.setAsset(refAsset);

	AssetValue av = new AssetValue();
	av.setCustomId(1L);
	av.setName("val");
	av.setAssetReference(ref);

	AssetValueOptimize result = AssetValueOptimizeMapper.toOptimize(av);

	assertNotNull(result.getAssetReferenceOptimize().getTemplateOptimize());
	assertEquals(200L, result.getAssetReferenceOptimize().getTemplateOptimize().getId());
	assertEquals("TessaAlarmDefinition", result.getAssetReferenceOptimize().getTemplateOptimize().getName());
	assertEquals("Complex", result.getAssetReferenceOptimize().getTemplateOptimize().getType());
    }


    @Test
    void toOptimize_conAssetReferenceSinTemplate_templateOptimizeEsNull()
    {

	Asset refAsset = new Asset();
	refAsset.setCustomId(20L);
	refAsset.setName("ref-asset");
	refAsset.setGroups(Set.of("rg"));

	AssetReference ref = new AssetReference();
	ref.setAsset(refAsset);

	AssetValue av = new AssetValue();
	av.setCustomId(1L);
	av.setName("val");
	av.setAssetReference(ref);

	AssetValueOptimize result = AssetValueOptimizeMapper.toOptimize(av);

	assertNotNull(result.getAssetReferenceOptimize());
	assertNull(result.getAssetReferenceOptimize().getTemplateOptimize());
    }


    @Test
    void toOptimize_conAssetReferenceSinAsset_referenceOptimizeEsNull()
    {

	AssetReference ref = new AssetReference();
	ref.setAsset(null);

	AssetValue av = new AssetValue();
	av.setCustomId(1L);
	av.setName("val");
	av.setAssetReference(ref);

	AssetValueOptimize result = AssetValueOptimizeMapper.toOptimize(av);

	assertNull(result.getAssetReferenceOptimize());
    }

    @Test
    void toOptimize_conTemplateAttribute_mapeaTemplateAttributeOptimize()
    {

	Template template = new Template();
	template.setCustomId(100L);
	template.setName("tmpl");
	template.setGroups(Set.of("tg"));

	Function fn = new Function("taExpr", null, true, "0 1 * * *", "evt", "CET", 1000L);

	TemplateAttribute ta = new TemplateAttribute();
	ta.setCustomId(50L);
	ta.setName("attr");
	ta.setGroups(Set.of("ag"));
	ta.setType("STRING");
	ta.setCollection(true);
	ta.setExpressionProperties(fn);
	ta.setCalculatedValue("calc");
	ta.setTemplate(template);

	AssetValue av = new AssetValue();
	av.setCustomId(1L);
	av.setName("val");
	av.setTemplateAttribute(ta);

	AssetValueOptimize result = AssetValueOptimizeMapper.toOptimize(av);

	assertNotNull(result.getTemplateAttributeOptimize());
	assertEquals(50L, result.getTemplateAttributeOptimize().getId());
	assertEquals("attr", result.getTemplateAttributeOptimize().getName());
	assertEquals("STRING", result.getTemplateAttributeOptimize().getType());
	assertTrue(result.getTemplateAttributeOptimize().getCollection());
	assertEquals("calc", result.getTemplateAttributeOptimize().getCalculatedValue());
	assertEquals("taExpr", result.getTemplateAttributeOptimize().getExpressionFunction());
	assertNotNull(result.getTemplateAttributeOptimize().getTemplateOptimize());
	assertEquals(100L, result.getTemplateAttributeOptimize().getTemplateOptimize().getId());
    }


    @Test
    void toOptimize_conTemplateAttributeConTemplateReference_mapeaTemplateReferenceOptimize()
    {

	Template refTemplate = new Template();
	refTemplate.setCustomId(300L);
	refTemplate.setName("TessaAlarmDefinition");
	refTemplate.setGroups(Set.of("SG4"));

	TemplateReference templateReference = new TemplateReference();
	templateReference.setTemplate(refTemplate);

	Template template = new Template();
	template.setCustomId(100L);
	template.setName("Sala");
	template.setGroups(Set.of("SG4"));

	TemplateAttribute ta = new TemplateAttribute();
	ta.setCustomId(50L);
	ta.setName("Alarma temperatura");
	ta.setType("Complex");
	ta.setTemplate(template);
	ta.setTemplateReference(templateReference);

	AssetValue av = new AssetValue();
	av.setCustomId(1L);
	av.setName("val");
	av.setTemplateAttribute(ta);

	AssetValueOptimize result = AssetValueOptimizeMapper.toOptimize(av);

	assertNotNull(result.getTemplateAttributeOptimize().getTemplateReferenceOptimize());
	assertEquals(300L, result.getTemplateAttributeOptimize().getTemplateReferenceOptimize().getId());
	assertEquals("TessaAlarmDefinition", result.getTemplateAttributeOptimize().getTemplateReferenceOptimize().getName());
    }


    @Test
    void toOptimize_conTemplateAttributeConTemplateReferenceSinTemplate_referenceOptimizeEsNull()
    {

	TemplateReference templateReference = new TemplateReference();
	templateReference.setTemplate(null);

	TemplateAttribute ta = new TemplateAttribute();
	ta.setCustomId(50L);
	ta.setName("attr");
	ta.setTemplateReference(templateReference);

	AssetValue av = new AssetValue();
	av.setCustomId(1L);
	av.setName("val");
	av.setTemplateAttribute(ta);

	AssetValueOptimize result = AssetValueOptimizeMapper.toOptimize(av);

	assertNull(result.getTemplateAttributeOptimize().getTemplateReferenceOptimize());
    }


    @Test
    void toOptimize_conAtributoComplexParaPayloadKafka_mapeaReferenciasCompletas()
    {

	Template parentTemplate = new Template();
	parentTemplate.setCustomId(5479L);
	parentTemplate.setName("Sala");
	parentTemplate.setGroups(Set.of("SG4"));
	parentTemplate.setType("Complex");

	Template referencedTemplate = new Template();
	referencedTemplate.setCustomId(5480L);
	referencedTemplate.setName("TessaAlarmDefinition");
	referencedTemplate.setGroups(Set.of("SG4"));
	referencedTemplate.setType("Complex");

	TemplateReference templateReference = new TemplateReference();
	templateReference.setTemplate(referencedTemplate);

	TemplateAttribute ta = new TemplateAttribute();
	ta.setCustomId(7518L);
	ta.setName("Alarma temperatura");
	ta.setGroups(Set.of("SG4"));
	ta.setType("Complex");
	ta.setTemplate(parentTemplate);
	ta.setTemplateReference(templateReference);

	Asset parentAsset = new Asset();
	parentAsset.setCustomId(5574L);
	parentAsset.setName("Sala A");
	parentAsset.setGroups(Set.of("SG4"));
	parentAsset.setTemplate(parentTemplate);

	Asset refAsset = new Asset();
	refAsset.setCustomId(7535L);
	refAsset.setName("Sala A_Alarma temperatura_1781803881947");
	refAsset.setGroups(Set.of("SG4"));
	refAsset.setTemplate(referencedTemplate);

	AssetReference assetReference = new AssetReference();
	assetReference.setAsset(refAsset);

	AssetValue av = new AssetValue();
	av.setCustomId(7529L);
	av.setName("Sala A_Alarma temperatura_1781803881947");
	av.setGroups(Set.of("SG4"));
	av.setAsset(parentAsset);
	av.setAssetReference(assetReference);
	av.setTemplateAttribute(ta);

	AssetValueOptimize result = AssetValueOptimizeMapper.toOptimize(av);

	assertNotNull(result.getAssetReferenceOptimize());
	assertNotNull(result.getAssetReferenceOptimize().getTemplateOptimize());
	assertEquals("TessaAlarmDefinition", result.getAssetReferenceOptimize().getTemplateOptimize().getName());

	assertNotNull(result.getTemplateAttributeOptimize());
	assertNotNull(result.getTemplateAttributeOptimize().getTemplateReferenceOptimize());
	assertEquals("TessaAlarmDefinition", result.getTemplateAttributeOptimize().getTemplateReferenceOptimize().getName());
    }


    @Test
    void toOptimize_conExpressionParams_mapeaParams()
    {

	ExpressionParam p1 = new ExpressionParam();
	p1.setCustomId(101L);
	p1.setName("param1");
	p1.setGroups(Set.of("pg"));

	ExpressionParam p2 = new ExpressionParam();
	p2.setCustomId(102L);
	p2.setName("param2");
	p2.setGroups(Set.of("pg"));

	Set<ExpressionParam> params = new HashSet<>();
	params.add(p1);
	params.add(p2);

	AssetValue av = new AssetValue();
	av.setCustomId(1L);
	av.setName("val");
	av.setExpressionParams(params);

	AssetValueOptimize result = AssetValueOptimizeMapper.toOptimize(av);

	assertNotNull(result.getExpressionParams());
	assertEquals(2, result.getExpressionParams().size());
    }

    @Test
    void toOptimize_sinExpressionParams_coleccionVacia()
    {

	AssetValue av = new AssetValue();
	av.setCustomId(1L);
	av.setName("val");

	AssetValueOptimize result = AssetValueOptimizeMapper.toOptimize(av);

	assertNotNull(result.getExpressionParams());
	assertTrue(result.getExpressionParams().isEmpty());
    }
}
