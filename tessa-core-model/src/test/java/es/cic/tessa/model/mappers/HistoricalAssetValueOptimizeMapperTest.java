package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.Function;
import es.cic.tessa.model.HistoricalAsset;
import es.cic.tessa.model.HistoricalAssetReference;
import es.cic.tessa.model.HistoricalAssetValue;
import es.cic.tessa.model.HistoricalExpressionParam;
import es.cic.tessa.model.HistoricalTemplate;
import es.cic.tessa.model.HistoricalTemplateAttribute;
import es.cic.tessa.model.HistoricalTemplateReference;
import es.cic.tessa.model.optimize.AssetValueOptimize;

class HistoricalAssetValueOptimizeMapperTest
{

    @Test
    void toOptimize_conNull_devuelveNull()
    {

	assertNull(HistoricalAssetValueOptimizeMapper.toOptimize(null));
    }

    @Test
    void toOptimize_conCamposBasicos_mapeaCorrectamente()
    {

	HistoricalAssetValue hav = new HistoricalAssetValue();
	hav.setCustomId(1L);
	hav.setName("val-name");
	hav.setGroups(Set.of("g1"));
	hav.setValue("42");

	AssetValueOptimize result = HistoricalAssetValueOptimizeMapper.toOptimize(hav);

	assertNotNull(result);
	assertEquals(1L, result.getId());
	assertEquals("val-name", result.getName());
	assertEquals("42", result.getValue());
	assertTrue(result.getGroups().contains("g1"));
    }

    @Test
    void toOptimize_idBinarySiempreNull_sinOrigenEnHistorico()
    {

	HistoricalAssetValue hav = new HistoricalAssetValue();
	hav.setCustomId(1L);
	hav.setName("val");
	hav.setValue("42");

	AssetValueOptimize result = HistoricalAssetValueOptimizeMapper.toOptimize(hav);

	assertNull(result.getIdBinary());
    }

    @Test
    void toOptimize_conFunctionYaDeserializado_mapeaCamposExpresion()
    {

	Function fn = new Function("expr1", true, false, "0 0 * * *", "event1", "UTC", 5000L);

	HistoricalAssetValue hav = new HistoricalAssetValue();
	hav.setCustomId(1L);
	hav.setName("val");
	hav.setExpressionProperties(fn);

	AssetValueOptimize result = HistoricalAssetValueOptimizeMapper.toOptimize(hav);

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

	HistoricalAssetValue hav = new HistoricalAssetValue();
	hav.setCustomId(1L);
	hav.setName("val");

	AssetValueOptimize result = HistoricalAssetValueOptimizeMapper.toOptimize(hav);

	assertNull(result.getExpressionFunction());
	assertNull(result.getCronExpression());
    }

    @Test
    void toOptimize_conHistoricalAsset_mapeaAssetOptimize()
    {

	HistoricalAsset asset = new HistoricalAsset();
	asset.setCustomId(10L);
	asset.setName("parent-asset");
	asset.setGroups(Set.of("g"));

	HistoricalAssetValue hav = new HistoricalAssetValue();
	hav.setCustomId(1L);
	hav.setName("val");
	hav.setHistoricalAsset(asset);

	AssetValueOptimize result = HistoricalAssetValueOptimizeMapper.toOptimize(hav);

	assertNotNull(result.getAssetOptimize());
	assertEquals(10L, result.getAssetOptimize().getId());
	assertEquals("parent-asset", result.getAssetOptimize().getName());
    }

    @Test
    void toOptimize_sinHistoricalAsset_assetOptimizeEsNull()
    {

	HistoricalAssetValue hav = new HistoricalAssetValue();
	hav.setCustomId(1L);
	hav.setName("val");

	AssetValueOptimize result = HistoricalAssetValueOptimizeMapper.toOptimize(hav);

	assertNull(result.getAssetOptimize());
    }

    @Test
    void toOptimize_conAssetReference_mapeaReferenceOptimize()
    {

	HistoricalAsset refAsset = new HistoricalAsset();
	refAsset.setCustomId(20L);
	refAsset.setName("ref-asset");
	refAsset.setGroups(Set.of("rg"));

	HistoricalAssetReference ref = new HistoricalAssetReference();
	ref.setHistoricalAsset(refAsset);

	HistoricalAssetValue hav = new HistoricalAssetValue();
	hav.setCustomId(1L);
	hav.setName("val");
	hav.setHistoricalAssetReference(ref);

	AssetValueOptimize result = HistoricalAssetValueOptimizeMapper.toOptimize(hav);

	assertNotNull(result.getAssetReferenceOptimize());
	assertEquals(20L, result.getAssetReferenceOptimize().getId());
	assertEquals("ref-asset", result.getAssetReferenceOptimize().getName());
    }

    @Test
    void toOptimize_conAssetReferenceSinAsset_referenceOptimizeEsNull()
    {

	HistoricalAssetReference ref = new HistoricalAssetReference();
	ref.setHistoricalAsset(null);

	HistoricalAssetValue hav = new HistoricalAssetValue();
	hav.setCustomId(1L);
	hav.setName("val");
	hav.setHistoricalAssetReference(ref);

	AssetValueOptimize result = HistoricalAssetValueOptimizeMapper.toOptimize(hav);

	assertNull(result.getAssetReferenceOptimize());
    }

    @Test
    void toOptimize_conTemplateAttribute_mapeaTemplateAttributeOptimize()
    {

	HistoricalTemplate template = new HistoricalTemplate();
	template.setCustomId(100L);
	template.setName("tmpl");
	template.setGroups(Set.of("tg"));

	Function fn = new Function("taExpr", null, true, "0 1 * * *", "evt", "CET", 1000L);

	HistoricalTemplateAttribute ta = new HistoricalTemplateAttribute();
	ta.setCustomId(50L);
	ta.setName("attr");
	ta.setGroups(Set.of("ag"));
	ta.setType("STRING");
	ta.setCollection(true);
	ta.setExpressionProperties(fn);
	ta.setCalculatedValue("calc");
	ta.setHistoricalTemplate(template);

	HistoricalAssetValue hav = new HistoricalAssetValue();
	hav.setCustomId(1L);
	hav.setName("val");
	hav.setHistoricalTemplateAttribute(ta);

	AssetValueOptimize result = HistoricalAssetValueOptimizeMapper.toOptimize(hav);

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

	HistoricalTemplate refTemplate = new HistoricalTemplate();
	refTemplate.setCustomId(300L);
	refTemplate.setName("TessaAlarmDefinition");
	refTemplate.setGroups(Set.of("SG4"));

	HistoricalTemplateReference templateReference = new HistoricalTemplateReference();
	templateReference.setHistoricalTemplate(refTemplate);

	HistoricalTemplateAttribute ta = new HistoricalTemplateAttribute();
	ta.setCustomId(50L);
	ta.setName("Alarma temperatura");
	ta.setHistoricalTemplateReference(templateReference);

	HistoricalAssetValue hav = new HistoricalAssetValue();
	hav.setCustomId(1L);
	hav.setName("val");
	hav.setHistoricalTemplateAttribute(ta);

	AssetValueOptimize result = HistoricalAssetValueOptimizeMapper.toOptimize(hav);

	assertNotNull(result.getTemplateAttributeOptimize().getTemplateReferenceOptimize());
	assertEquals(300L, result.getTemplateAttributeOptimize().getTemplateReferenceOptimize().getId());
	assertEquals("TessaAlarmDefinition", result.getTemplateAttributeOptimize().getTemplateReferenceOptimize().getName());
    }

    @Test
    void toOptimize_conExpressionParams_mapeaParamsConTypePositionRequiredYDefaultValue()
    {

	HistoricalExpressionParam p1 = new HistoricalExpressionParam();
	p1.setCustomId(101L);
	p1.setName("param1");
	p1.setGroups(Set.of("pg"));
	p1.setType("STRING");
	p1.setRequired(true);
	p1.setPosition(0);

	Set<HistoricalExpressionParam> params = new HashSet<>();
	params.add(p1);

	HistoricalAssetValue hav = new HistoricalAssetValue();
	hav.setCustomId(1L);
	hav.setName("val");
	hav.setExpressionParams(params);

	AssetValueOptimize result = HistoricalAssetValueOptimizeMapper.toOptimize(hav);

	assertNotNull(result.getExpressionParams());
	assertEquals(1, result.getExpressionParams().size());
	assertEquals("STRING", result.getExpressionParams().iterator().next().getType());
	assertTrue(result.getExpressionParams().iterator().next().isRequired());
	assertEquals(0, result.getExpressionParams().iterator().next().getPosition());
    }

    @Test
    void toOptimize_sinExpressionParams_coleccionVacia()
    {

	HistoricalAssetValue hav = new HistoricalAssetValue();
	hav.setCustomId(1L);
	hav.setName("val");

	AssetValueOptimize result = HistoricalAssetValueOptimizeMapper.toOptimize(hav);

	assertNotNull(result.getExpressionParams());
	assertTrue(result.getExpressionParams().isEmpty());
    }
}
