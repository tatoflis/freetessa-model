package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.Template;
import es.cic.tessa.model.dto.TemplateRequest;

class TemplateRequestMapperTest
{

    private TemplateRequestMapper mapper;

    @BeforeEach
    void setUp()
    {

	mapper = new TemplateRequestMapper();
    }

    @Test
    void templateRequestToTemplate_mapeaCamposBasicos()
    {

	TemplateRequest request = new TemplateRequest();
	request.setId(1L);
	request.setName("tmpl-name");
	request.setDescription("desc");
	request.setIcon("icon.png");
	request.setType("SIMPLE");
	request.setAbstractTemplate(true);
	request.setFinalTemplate(false);
	request.setAssetOrganized(true);
	request.setTemplateOrganized(false);
	request.setVersion(3L);
	request.setIdExtendsTemplate(10L);
	request.setModifDate(LocalDateTime.of(2025, 3, 1, 12, 0));

	Set<String> groups = Set.of("g1");
	Template result = mapper.templateRequestToTemplate(request, groups);

	assertEquals(1L, result.getCustomId());
	assertEquals("tmpl-name", result.getName());
	assertEquals("tmpl-name", result.getNameLower());
	assertEquals("desc", result.getDescription());
	assertEquals("icon.png", result.getIcon());
	assertEquals("SIMPLE", result.getType());
	assertTrue(result.getAbstractTemplate());
	assertFalse(result.getFinalTemplate());
	assertTrue(result.getAssetOrganized());
	assertFalse(result.getTemplateOrganized());
	assertEquals(3L, result.getVersion());
	assertNotNull(result.getExtendsTemplate());
	assertEquals(10L, result.getExtendsTemplate().getCustomId());
	assertEquals(LocalDateTime.of(2025, 3, 1, 12, 0), result.getModifDate());
	assertTrue(result.getGroups().contains("g1"));
    }

    @Test
    void templateRequestToTemplate_sinExtendsTemplate_extendsEsNull()
    {

	TemplateRequest request = new TemplateRequest();
	request.setName("tmpl");

	Template result = mapper.templateRequestToTemplate(request, Set.of("g"));

	assertNull(result.getExtendsTemplate());
    }

    @Test
    void templateRequestToTemplate_sinVersion_conservaDefault()
    {

	TemplateRequest request = new TemplateRequest();
	request.setName("tmpl");

	Template result = mapper.templateRequestToTemplate(request, Set.of("g"));

	assertEquals(0L, result.getVersion());
    }


    @Test
    void templateRequestToTemplate_conIdExtendsTemplate_asignaExtends()
    {

	TemplateRequest request = new TemplateRequest();
	request.setName("tmpl");
	request.setIdExtendsTemplate(10L);

	Template result = mapper.templateRequestToTemplate(request, Set.of("g"));

	assertNotNull(result.getExtendsTemplate());
	assertEquals(10L, result.getExtendsTemplate().getCustomId());
    }


    @Test
    void templateRequestToTemplate_sinIdExtendsTemplate_extendsQuedaNull()
    {

	TemplateRequest request = new TemplateRequest();
	request.setName("tmpl");

	Template result = mapper.templateRequestToTemplate(request, Set.of("g"));

	assertNull(result.getExtendsTemplate());
    }
}
