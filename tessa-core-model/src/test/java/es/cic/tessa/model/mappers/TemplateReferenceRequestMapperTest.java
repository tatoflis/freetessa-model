package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.TemplateReference;
import es.cic.tessa.model.dto.TemplateReferenceRequest;
import es.cic.tessa.model.types.ReferenceType;
import es.cic.tessa.model.types.RelationType;

class TemplateReferenceRequestMapperTest
{

    private TemplateReferenceRequestMapper mapper;

    @BeforeEach
    void setUp()
    {

	mapper = new TemplateReferenceRequestMapper();
    }

    @Test
    void requestToTemplateReference_mapeaCampos()
    {

	TemplateReferenceRequest request = new TemplateReferenceRequest();
	request.setId(10L);
	request.setIdTemplate(20L);
	request.setTemplateReferenceType(ReferenceType.COMPLEX);
	request.setTemplateRelationType(RelationType.AGGREGATION);

	TemplateReference result = mapper.templateReferenceRequestToTemplateReference(request);

	assertEquals(10L, result.getId());
	assertNotNull(result.getTemplate());
	assertEquals(20L, result.getTemplate().getCustomId());
	assertEquals("Complex", result.getTemplateReferenceType());
	assertEquals("Aggregation", result.getTemplateRelationType());
    }

    @Test
    void requestToTemplateReference_conSimpleAssociation()
    {

	TemplateReferenceRequest request = new TemplateReferenceRequest();
	request.setId(5L);
	request.setIdTemplate(15L);
	request.setTemplateReferenceType(ReferenceType.SIMPLE);
	request.setTemplateRelationType(RelationType.COMPOSITION);

	TemplateReference result = mapper.templateReferenceRequestToTemplateReference(request);

	assertEquals("Simple", result.getTemplateReferenceType());
	assertEquals("Composition", result.getTemplateRelationType());
    }
}
