package es.cic.tessa.model.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import es.cic.tessa.model.optimize.TemplateAttributeOptimize;
import es.cic.tessa.model.optimize.TemplateOptimize;

class AssetUtilsTest
{

    @Test
    void isComplexAsset_sinTemplateReference_devuelveFalse()
    {

	TemplateAttributeOptimize ta = new TemplateAttributeOptimize();

	assertFalse(AssetUtils.isComplexAsset(ta));
    }

    @Test
    void isComplexAsset_conLookupDB_devuelveFalse()
    {

	TemplateOptimize refTemplate = new TemplateOptimize();
	refTemplate.setName("LookupDB");

	TemplateAttributeOptimize ta = new TemplateAttributeOptimize();
	ta.setTemplateReferenceOptimize(refTemplate);

	assertFalse(AssetUtils.isComplexAsset(ta));
    }

    @Test
    void isComplexAsset_conLookupRest_devuelveFalse()
    {

	TemplateOptimize refTemplate = new TemplateOptimize();
	refTemplate.setName("LookupRest");

	TemplateAttributeOptimize ta = new TemplateAttributeOptimize();
	ta.setTemplateReferenceOptimize(refTemplate);

	assertFalse(AssetUtils.isComplexAsset(ta));
    }

    @Test
    void isComplexAsset_conLookupPrometheusRest_devuelveFalse()
    {

	TemplateOptimize refTemplate = new TemplateOptimize();
	refTemplate.setName("LookupPrometheusRest");

	TemplateAttributeOptimize ta = new TemplateAttributeOptimize();
	ta.setTemplateReferenceOptimize(refTemplate);

	assertFalse(AssetUtils.isComplexAsset(ta));
    }

    @Test
    void isComplexAsset_conOtroTemplate_devuelveTrue()
    {

	TemplateOptimize refTemplate = new TemplateOptimize();
	refTemplate.setName("OtherTemplate");

	TemplateAttributeOptimize ta = new TemplateAttributeOptimize();
	ta.setTemplateReferenceOptimize(refTemplate);

	assertTrue(AssetUtils.isComplexAsset(ta));
    }
}
