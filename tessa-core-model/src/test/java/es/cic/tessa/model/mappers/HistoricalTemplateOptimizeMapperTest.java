package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.HistoricalTemplate;
import es.cic.tessa.model.optimize.TemplateOptimize;

class HistoricalTemplateOptimizeMapperTest
{

    @Test
    void toOptimize_conNull_devuelveNull()
    {

	assertNull(HistoricalTemplateOptimizeMapper.toOptimize(null));
    }

    @Test
    void toOptimizeThin_conNull_devuelveNull()
    {

	assertNull(HistoricalTemplateOptimizeMapper.toOptimizeThin(null));
    }

    @Test
    void toOptimize_conCamposBasicos_mapeaCorrectamente()
    {

	HistoricalTemplate template = new HistoricalTemplate();
	template.setCustomId(100L);
	template.setName("tmpl");
	template.setGroups(Set.of("tg"));
	template.setType("Complex");

	TemplateOptimize result = HistoricalTemplateOptimizeMapper.toOptimize(template);

	assertNotNull(result);
	assertEquals(100L, result.getId());
	assertEquals("tmpl", result.getName());
	assertEquals("Complex", result.getType());
    }

    @Test
    void toOptimize_conExtendsTemplate_mapeaTemplateExtendsOptimize()
    {

	HistoricalTemplate ancestro = new HistoricalTemplate();
	ancestro.setCustomId(101L);
	ancestro.setName("ancestro");
	ancestro.setGroups(Set.of("tg"));

	HistoricalTemplate template = new HistoricalTemplate();
	template.setCustomId(100L);
	template.setName("tmpl");
	template.setHistoricalExtendsTemplate(ancestro);

	TemplateOptimize result = HistoricalTemplateOptimizeMapper.toOptimize(template);

	assertNotNull(result.getTemplateExtendsOptimize());
	assertEquals(101L, result.getTemplateExtendsOptimize().getId());
	assertEquals("ancestro", result.getTemplateExtendsOptimize().getName());
    }

    @Test
    void toOptimize_sinExtendsTemplate_templateExtendsOptimizeEsNull()
    {

	HistoricalTemplate template = new HistoricalTemplate();
	template.setCustomId(100L);
	template.setName("tmpl");

	TemplateOptimize result = HistoricalTemplateOptimizeMapper.toOptimize(template);

	assertNull(result.getTemplateExtendsOptimize());
    }

    @Test
    void toOptimizeThin_noIncluyeExtendsTemplate()
    {

	HistoricalTemplate ancestro = new HistoricalTemplate();
	ancestro.setCustomId(101L);
	ancestro.setName("ancestro");

	HistoricalTemplate template = new HistoricalTemplate();
	template.setCustomId(100L);
	template.setName("tmpl");
	template.setHistoricalExtendsTemplate(ancestro);

	TemplateOptimize result = HistoricalTemplateOptimizeMapper.toOptimizeThin(template);

	assertNotNull(result);
	assertEquals(100L, result.getId());
	assertNull(result.getTemplateExtendsOptimize());
    }
}
