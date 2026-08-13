package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.Template;
import es.cic.tessa.model.optimize.TemplateOptimize;

class TemplateOptimizeMapperTest
{

    @Test
    void toOptimize_conNull_devuelveNull()
    {

	assertNull(TemplateOptimizeMapper.toOptimize(null));
    }

    @Test
    void toOptimize_conCamposBasicos_mapeaCorrectamente()
    {

	Template template = new Template();
	template.setCustomId(1L);
	template.setName("tmpl");
	template.setGroups(Set.of("g1"));
	template.setType("SIMPLE");

	TemplateOptimize result = TemplateOptimizeMapper.toOptimize(template);

	assertNotNull(result);
	assertEquals(1L, result.getId());
	assertEquals("tmpl", result.getName());
	assertTrue(result.getGroups().contains("g1"));
	assertEquals("SIMPLE", result.getType());
    }

    @Test
    void toOptimize_conExtendsTemplate_mapeaExtendsOptimize()
    {

	Template parent = new Template();
	parent.setCustomId(10L);
	parent.setName("parent-tmpl");
	parent.setGroups(Set.of("pg"));

	Template template = new Template();
	template.setCustomId(1L);
	template.setName("child-tmpl");
	template.setGroups(Set.of("g1"));
	template.setType("COMPLEX");
	template.setExtendsTemplate(parent);

	TemplateOptimize result = TemplateOptimizeMapper.toOptimize(template);

	assertNotNull(result.getTemplateExtendsOptimize());
	assertEquals(10L, result.getTemplateExtendsOptimize().getId());
	assertEquals("parent-tmpl", result.getTemplateExtendsOptimize().getName());
	assertTrue(result.getTemplateExtendsOptimize().getGroups().contains("pg"));
    }

    @Test
    void toOptimize_sinExtendsTemplate_extendsOptimizeEsNull()
    {

	Template template = new Template();
	template.setCustomId(1L);
	template.setName("tmpl");

	TemplateOptimize result = TemplateOptimizeMapper.toOptimize(template);

	assertNull(result.getTemplateExtendsOptimize());
    }
}
