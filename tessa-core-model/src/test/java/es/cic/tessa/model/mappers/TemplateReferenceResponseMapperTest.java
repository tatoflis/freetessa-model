package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import es.cic.tessa.model.Template;
import es.cic.tessa.model.TemplateReference;
import es.cic.tessa.model.dto.TemplateReferenceResponse;
import es.cic.tessa.model.dto.TemplateResponse;
import es.cic.tessa.model.types.ReferenceType;
import es.cic.tessa.model.types.RelationType;

@ExtendWith(MockitoExtension.class)
class TemplateReferenceResponseMapperTest
{

    @Mock
    private TemplateResponseMapper templateResponseMapper;

    @InjectMocks
    private TemplateReferenceResponseMapper mapper;

    @Test
    void toResponse_mapeaCampos()
    {

	Template tmpl = new Template();
	tmpl.setCustomId(20L);

	TemplateReference ref = new TemplateReference();
	ref.setId(10L);
	ref.setTemplateReferenceType("Complex");
	ref.setTemplateRelationType("Aggregation");
	ref.setTemplate(tmpl);

	TemplateResponse tmplResp = new TemplateResponse();
	tmplResp.setId(20L);
	when(templateResponseMapper.templateToFullTemplateRespose(tmpl)).thenReturn(tmplResp);

	TemplateReferenceResponse result = mapper.templateReferenceToTemplateReferenceRespose(ref);

	assertEquals(10L, result.getId());
	assertEquals(ReferenceType.COMPLEX, result.getReferenceType());
	assertEquals(RelationType.AGGREGATION, result.getRelationType());
	assertNotNull(result.getTemplateResponse());
    }

    @Test
    void responseToTemplateReference_mapeaCampos()
    {

	TemplateResponse tmplResp = new TemplateResponse();
	tmplResp.setId(20L);

	TemplateReferenceResponse resp = new TemplateReferenceResponse();
	resp.setId(10L);
	resp.setReferenceType(ReferenceType.SIMPLE);
	resp.setRelationType(RelationType.COMPOSITION);
	resp.setTemplateResponse(tmplResp);

	Template tmpl = new Template();
	tmpl.setCustomId(20L);
	when(templateResponseMapper.templateResponseToTemplate(tmplResp)).thenReturn(tmpl);

	TemplateReference result = mapper.templateReferenceResponseToTemplateReference(resp);

	assertEquals(10L, result.getId());
	assertEquals("Simple", result.getTemplateReferenceType());
	assertEquals("Composition", result.getTemplateRelationType());
	assertNotNull(result.getTemplate());
    }

    @Test
    void recursiveToResponse_usaTemplateToTemplateRespose()
    {

	Template tmpl = new Template();
	tmpl.setCustomId(20L);

	TemplateReference ref = new TemplateReference();
	ref.setId(10L);
	ref.setTemplateReferenceType("Complex");
	ref.setTemplateRelationType("Aggregation");
	ref.setTemplate(tmpl);

	TemplateResponse tmplResp = new TemplateResponse();
	tmplResp.setId(20L);
	when(templateResponseMapper.templateToTemplateRespose(tmpl)).thenReturn(tmplResp);

	TemplateReferenceResponse result = mapper.recursiveTemplateReferenceToTemplateReferenceRespose(ref);

	verify(templateResponseMapper).templateToTemplateRespose(tmpl);
	assertNotNull(result.getTemplateResponse());
    }
}
