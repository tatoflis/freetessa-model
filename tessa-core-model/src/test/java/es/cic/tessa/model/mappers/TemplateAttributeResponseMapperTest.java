package es.cic.tessa.model.mappers;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
import es.cic.tessa.model.ExpressionParam;
import es.cic.tessa.model.Function;
import es.cic.tessa.model.Template;
import es.cic.tessa.model.TemplateAttribute;
import es.cic.tessa.model.dto.ExpressionParamResponse;
import es.cic.tessa.model.dto.FunctionResponse;
import es.cic.tessa.model.dto.TemplateAttributeResponse;
import es.cic.tessa.model.dto.TemplateResponse;


@ExtendWith(MockitoExtension.class)
class TemplateAttributeResponseMapperTest
{

    @Mock
    private TemplateResponseMapper templateResponseMapper;

    @Mock
    private TemplateReferenceResponseMapper templateReferenceResponseMapper;

    @Mock
    private ExpressionParamResponseMapper expressionParamResponseMapper;

    @Mock
    private FunctionResponseMapper functionResponseMapper;

    @InjectMocks
    private TemplateAttributeResponseMapper mapper;

    @Test
    void toResponse_camposBasicos()
    {

	TemplateAttribute ta = new TemplateAttribute();
	ta.setCustomId(1L);
	ta.setName("attr1");
	ta.setDescription("desc");
	ta.setIcon("icon.png");
	ta.setType("TEXT");
	ta.setMinLength(1);
	ta.setMaxLength(100);
	ta.setRequired(true);
	ta.setVersion(2L);
	ta.setNemonic("nem");
	ta.setInsertDate(LocalDateTime.of(2024, 1, 15, 10, 30));

	TemplateAttributeResponse result = mapper.templateAttributeToTemplateAttributeRespose(true, ta);

	assertEquals(1L, result.getId());
	assertEquals("attr1", result.getName());
	assertEquals("desc", result.getDescription());
	assertEquals("TEXT", result.getType());
	assertEquals(1, result.getMinLength());
	assertEquals(100, result.getMaxLength());
	assertTrue(result.getRequired());
	assertEquals(2L, result.getVersion());
    }


    @Test
    void toResponse_conTemplate_fromTemplateFalse_invocaMapper()
    {

	Template tmpl = new Template();
	tmpl.setCustomId(10L);

	TemplateAttribute ta = new TemplateAttribute();
	ta.setCustomId(1L);
	ta.setName("a");
	ta.setInsertDate(LocalDateTime.of(2024, 1, 1, 0, 0));
	ta.setTemplate(tmpl);

	TemplateResponse tmplResp = new TemplateResponse();
	tmplResp.setId(10L);
	when(templateResponseMapper.templateToFullTemplateRespose(tmpl)).thenReturn(tmplResp);

	TemplateAttributeResponse result = mapper.templateAttributeToTemplateAttributeRespose(false, ta);

	assertNotNull(result.getTemplate());
	verify(templateResponseMapper).templateToFullTemplateRespose(tmpl);
    }


    @Test
    void toResponse_conTemplate_fromTemplateTrue_noInvocaMapper()
    {

	Template tmpl = new Template();
	tmpl.setCustomId(10L);

	TemplateAttribute ta = new TemplateAttribute();
	ta.setCustomId(1L);
	ta.setName("a");
	ta.setInsertDate(LocalDateTime.of(2024, 1, 1, 0, 0));
	ta.setTemplate(tmpl);

	TemplateAttributeResponse result = mapper.templateAttributeToTemplateAttributeRespose(true, ta);

	assertNull(result.getTemplate());
	verify(templateResponseMapper, never()).templateToFullTemplateRespose(any());
    }


    @Test
    void toResponse_conExpressionProperties_invocaFunctionMapper()
    {

	Function func = new Function();
	func.setExpressionFunction("SUM(A1)");

	TemplateAttribute ta = new TemplateAttribute();
	ta.setCustomId(1L);
	ta.setName("a");
	ta.setInsertDate(LocalDateTime.of(2024, 1, 1, 0, 0));
	ta.setExpressionProperties(func);

	FunctionResponse funcResp = new FunctionResponse();
	when(functionResponseMapper.functionToFunctionResponse(func)).thenReturn(funcResp);

	TemplateAttributeResponse result = mapper.templateAttributeToTemplateAttributeRespose(true, ta);

	assertNotNull(result.getExpressionProperties());
	verify(functionResponseMapper).functionToFunctionResponse(func);
    }


    @Test
    void toResponse_conExpressionParams_invocaParamMapper()
    {

	ExpressionParam ep = new ExpressionParam();
	ep.setCustomId(20L);

	TemplateAttribute ta = new TemplateAttribute();
	ta.setCustomId(1L);
	ta.setName("a");
	ta.setInsertDate(LocalDateTime.of(2024, 1, 1, 0, 0));
	ta.setExpressionParams(Set.of(ep));

	Collection<ExpressionParamResponse> mockResp = List.of(new ExpressionParamResponse());
	when(expressionParamResponseMapper.expressionParamsToExpressionParamResponseCollection(any())).thenReturn(mockResp);

	TemplateAttributeResponse result = mapper.templateAttributeToTemplateAttributeRespose(true, ta);

	assertNotNull(result.getExpressionParams());
    }


    @Test
    void toResponse_conSystemGroup_seteaGroups()
    {

	TemplateAttribute ta = new TemplateAttribute();
	ta.setCustomId(1L);
	ta.setName("a");
	ta.setInsertDate(LocalDateTime.of(2024, 1, 1, 0, 0));
	ta.setGroups(Set.of("System"));

	TemplateAttributeResponse result = mapper.templateAttributeToTemplateAttributeRespose(true, ta);

	assertNotNull(result.getGroups());
	assertTrue(result.getGroups().contains("System"));
    }


    @Test
    void responseToEntity_camposBasicos()
    {

	TemplateAttributeResponse resp = new TemplateAttributeResponse();
	resp.setId(1L);
	resp.setName("attr1");
	resp.setDescription("desc");
	resp.setType("TEXT");
	resp.setVersion(2L);
	resp.setInsertDate(Instant.parse("2024-06-01T12:00:00Z"));

	TemplateAttribute result = mapper.templateAttributeResponseToTemplateAttribute(resp);

	assertEquals(1L, result.getCustomId());
	assertEquals("attr1", result.getName());
	assertEquals("desc", result.getDescription());
	assertEquals("TEXT", result.getType());
	assertEquals(2L, result.getVersion());
    }


    @Test
    void responseToEntity_conTemplate_invocaReverseMapper()
    {

	TemplateResponse tmplResp = new TemplateResponse();
	tmplResp.setId(10L);

	TemplateAttributeResponse resp = new TemplateAttributeResponse();
	resp.setId(1L);
	resp.setName("a");
	resp.setInsertDate(Instant.parse("2024-01-01T00:00:00Z"));
	resp.setTemplate(tmplResp);

	Template tmpl = new Template();
	tmpl.setCustomId(10L);
	when(templateResponseMapper.templateResponseToTemplate(tmplResp)).thenReturn(tmpl);

	TemplateAttribute result = mapper.templateAttributeResponseToTemplateAttribute(resp);

	assertNotNull(result.getTemplate());
	assertEquals(10L, result.getTemplate().getCustomId());
    }
}
