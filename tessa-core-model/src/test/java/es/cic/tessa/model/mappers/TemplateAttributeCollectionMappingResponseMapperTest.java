package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import es.cic.tessa.model.TemplateAttribute;
import es.cic.tessa.model.TemplateAttributeCollectionMapping;
import es.cic.tessa.model.dto.TemplateAttributeCollectionMappingResponse;
import es.cic.tessa.model.dto.TemplateAttributeResponse2;

@ExtendWith(MockitoExtension.class)
class TemplateAttributeCollectionMappingResponseMapperTest
{

    @Mock
    private TemplateAttributeResponseMapper templateAttributeResponseMapper;

    @InjectMocks
    private TemplateAttributeCollectionMappingResponseMapper mapper;

    @Test
    void toResponse_camposBasicos()
    {

	TemplateAttributeCollectionMapping entity = new TemplateAttributeCollectionMapping();
	entity.setCustomId(1L);
	entity.setName("mapping1");
	entity.setDescription("desc");
	entity.setIcon("icon.png");
	entity.setVersion(2L);
	entity.setNemonic("nem");
	entity.setCalculatedValue("=SUM(A1)");
	entity.setPosition(0);
	entity.setInsertDate(LocalDateTime.of(2024, 1, 15, 10, 30));

	TemplateAttributeCollectionMappingResponse result = mapper.templateAttributeCollectionMappingToTemplateAttributeCollectionMappingResponse(entity);

	assertEquals(1L, result.getId());
	assertEquals("mapping1", result.getName());
	assertEquals("desc", result.getDescription());
	assertEquals("icon.png", result.getIcon());
	assertEquals(2L, result.getVersion());
	assertEquals("=SUM(A1)", result.getCalculatedValue());
	assertEquals(0, result.getPosition());
    }

    @Test
    void toResponse_conTemplateAttribute_invocaMapper()
    {

	TemplateAttribute ta = new TemplateAttribute();
	ta.setCustomId(10L);
	ta.setName("attr");
	ta.setInsertDate(LocalDateTime.of(2024, 1, 1, 0, 0));

	TemplateAttributeCollectionMapping entity = new TemplateAttributeCollectionMapping();
	entity.setCustomId(1L);
	entity.setName("m");
	entity.setInsertDate(LocalDateTime.of(2024, 1, 1, 0, 0));
	entity.setTemplateAttribute(ta);

	TemplateAttributeResponse2 taResp = new TemplateAttributeResponse2();
	taResp.setId(10L);
	when(templateAttributeResponseMapper.templateAttributeToTemplateAttributeRespose2(ta)).thenReturn(taResp);

	TemplateAttributeCollectionMappingResponse result = mapper.templateAttributeCollectionMappingToTemplateAttributeCollectionMappingResponse(entity);

	assertNotNull(result.getTemplateAttribute());
	assertEquals(10L, result.getTemplateAttribute().getId());
    }

    @Test
    void toResponse_sinTemplateAttribute_noInvocaMapper()
    {

	TemplateAttributeCollectionMapping entity = new TemplateAttributeCollectionMapping();
	entity.setCustomId(1L);
	entity.setName("m");
	entity.setInsertDate(LocalDateTime.of(2024, 1, 1, 0, 0));

	TemplateAttributeCollectionMappingResponse result = mapper.templateAttributeCollectionMappingToTemplateAttributeCollectionMappingResponse(entity);

	assertNull(result.getTemplateAttribute());
	verifyNoInteractions(templateAttributeResponseMapper);
    }

    @Test
    void responseToEntity_camposBasicos()
    {

	TemplateAttributeCollectionMappingResponse resp = new TemplateAttributeCollectionMappingResponse();
	resp.setId(1L);
	resp.setName("mapping1");
	resp.setDescription("desc");
	resp.setCalculatedValue("=AVG(B1)");
	resp.setPosition(1);
	resp.setVersion(3L);
	resp.setInsertDate(Instant.parse("2024-06-01T12:00:00Z"));

	TemplateAttributeCollectionMapping result = mapper.templateAttributeCollectionMappingResponseToTemplateAttributeCollectionMapping(resp);

	assertEquals(1L, result.getId());
	assertEquals("mapping1", result.getName());
	assertEquals("desc", result.getDescription());
	assertEquals("=AVG(B1)", result.getCalculatedValue());
	assertEquals(1, result.getPosition());
	assertEquals(3L, result.getVersion());
    }

    @Test
    void collectionToResponseCollection_mapeaTodos()
    {

	TemplateAttributeCollectionMapping m1 = new TemplateAttributeCollectionMapping();
	m1.setCustomId(1L);
	m1.setName("m1");
	m1.setInsertDate(LocalDateTime.of(2024, 1, 1, 0, 0));

	TemplateAttributeCollectionMapping m2 = new TemplateAttributeCollectionMapping();
	m2.setCustomId(2L);
	m2.setName("m2");
	m2.setInsertDate(LocalDateTime.of(2024, 1, 2, 0, 0));

	Collection<TemplateAttributeCollectionMappingResponse> result = mapper.templateAttributeCollectionMappingCollectionToTemplateAttributeCollectionMappingResponseCollection(List.of(m1, m2));

	assertEquals(2, result.size());
    }
}
