package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import es.cic.tessa.model.Template;
import es.cic.tessa.model.TemplateAttribute;
import es.cic.tessa.model.dto.TemplateAttributeResponse;
import es.cic.tessa.model.dto.TemplateResponse;

@ExtendWith(MockitoExtension.class)
class TemplateResponseMapperTest
{

    @Mock
    private TemplateAttributeResponseMapper templateAttributeResponseMapper;

    @InjectMocks
    private TemplateResponseMapper mapper;

    @Test
    void templateToResponse_camposBasicos()
    {

	Template tmpl = new Template();
	tmpl.setCustomId(1L);
	tmpl.setName("tmpl1");
	tmpl.setDescription("desc");
	tmpl.setIcon("icon.png");
	tmpl.setType("SIMPLE");
	tmpl.setFinalTemplate(true);
	tmpl.setAbstractTemplate(false);
	tmpl.setAssetOrganized(true);
	tmpl.setTemplateOrganized(false);
	tmpl.setVersion(3L);
	tmpl.setNemonic("nem");
	tmpl.setInsertDate(LocalDateTime.of(2024, 1, 15, 10, 30));

	TemplateResponse result = mapper.templateToTemplateRespose(tmpl);

	assertEquals(1L, result.getId());
	assertEquals("tmpl1", result.getName());
	assertEquals("desc", result.getDescription());
	assertEquals("SIMPLE", result.getType());
	assertTrue(result.getFinalTemplate());
	assertFalse(result.getAbstractTemplate());
	assertEquals(3L, result.getVersion());
    }

    @Test
    void templateToResponse_conSystemGroup_seteaGroups()
    {

	Template tmpl = new Template();
	tmpl.setCustomId(1L);
	tmpl.setName("t");
	tmpl.setInsertDate(LocalDateTime.of(2024, 1, 1, 0, 0));
	tmpl.setGroups(Set.of("System"));

	TemplateResponse result = mapper.templateToTemplateRespose(tmpl);

	assertNotNull(result.getGroups());
	assertTrue(result.getGroups().contains("System"));
    }

    @Test
    void templateToFullResponse_conExtendsTemplate_recursivo()
    {

	Template parent = new Template();
	parent.setCustomId(10L);
	parent.setName("parent");
	parent.setInsertDate(LocalDateTime.of(2024, 1, 1, 0, 0));

	Template tmpl = new Template();
	tmpl.setCustomId(1L);
	tmpl.setName("child");
	tmpl.setInsertDate(LocalDateTime.of(2024, 1, 1, 0, 0));
	tmpl.setExtendsTemplate(parent);

	TemplateResponse result = mapper.templateToFullTemplateRespose(tmpl);

	assertNotNull(result.getExtendsTemplate());
	assertEquals(10L, result.getExtendsTemplate().getId());
    }

    @Test
    void templateToFullResponse_conAttributes_invocaAttributeMapper()
    {

	TemplateAttribute ta = new TemplateAttribute();
	ta.setCustomId(20L);

	Template tmpl = new Template();
	tmpl.setCustomId(1L);
	tmpl.setName("t");
	tmpl.setInsertDate(LocalDateTime.of(2024, 1, 1, 0, 0));
	tmpl.setTemplateAttributes(List.of(ta));

	Collection<TemplateAttributeResponse> mockResp = List.of(new TemplateAttributeResponse());
	when(templateAttributeResponseMapper.templateAttributeCollectionToTemplateAttributeResposeCollection(true, tmpl.getTemplateAttributes())).thenReturn(mockResp);

	TemplateResponse result = mapper.templateToFullTemplateRespose(tmpl);

	assertNotNull(result.getTemplateAttributes());
	assertEquals(1, result.getTemplateAttributes().size());
    }

    @Test
    void templateResponseToTemplate_camposBasicos()
    {

	TemplateResponse resp = new TemplateResponse();
	resp.setId(1L);
	resp.setName("tmpl1");
	resp.setDescription("desc");
	resp.setIcon("icon.png");
	resp.setType("SIMPLE");
	resp.setFinalTemplate(true);
	resp.setAbstractTemplate(false);
	resp.setVersion(3L);
	resp.setNemonic("nem");
	resp.setInsertDate(Instant.parse("2024-06-01T12:00:00Z"));

	Template result = mapper.templateResponseToTemplate(resp);

	assertEquals(1L, result.getCustomId());
	assertEquals("tmpl1", result.getName());
	assertEquals("desc", result.getDescription());
	assertEquals("SIMPLE", result.getType());
	assertTrue(result.getFinalTemplate());
	assertFalse(result.getAbstractTemplate());
	assertEquals(3L, result.getVersion());
    }

    @Test
    void templateResponseToTemplate_conExtendsTemplate_recursivo()
    {

	TemplateResponse parent = new TemplateResponse();
	parent.setId(10L);
	parent.setName("parent");
	parent.setInsertDate(Instant.parse("2024-01-01T00:00:00Z"));

	TemplateResponse resp = new TemplateResponse();
	resp.setId(1L);
	resp.setName("child");
	resp.setInsertDate(Instant.parse("2024-01-01T00:00:00Z"));
	resp.setExtendsTemplate(parent);

	Template result = mapper.templateResponseToTemplate(resp);

	assertNotNull(result.getExtendsTemplate());
	assertEquals(10L, result.getExtendsTemplate().getCustomId());
    }
}
