package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.TemplateAttributeCollectionMapping;
import es.cic.tessa.model.dto.TemplateAttributeCollectionMappingRequest;

class TemplateAttributeCollectionMappingRequestMapperTest
{

    private TemplateAttributeCollectionMappingRequestMapper mapper;

    @BeforeEach
    void setUp()
    {

	mapper = new TemplateAttributeCollectionMappingRequestMapper();
    }

    @Test
    void requestToEntity_mapeaCampos()
    {

	TemplateAttributeCollectionMappingRequest req = new TemplateAttributeCollectionMappingRequest();
	req.setId(1L);
	req.setName("Mapping1");
	req.setDescription("desc");
	req.setIcon("icon.png");
	req.setCalculatedValue("=SUM(A1)");
	req.setPosition(0);
	req.setVersion(2L);

	Set<String> groups = Set.of("group1");

	TemplateAttributeCollectionMapping result = mapper.templateAttributeCollectionMappingRequestToTemplateAttributeCollectionMapping(req, groups);

	assertEquals(1L, result.getCustomId());
	assertEquals("Mapping1", result.getName());
	assertEquals("mapping1", result.getNameLower());
	assertEquals("desc", result.getDescription());
	assertEquals("icon.png", result.getIcon());
	assertEquals("=SUM(A1)", result.getCalculatedValue());
	assertEquals(0, result.getPosition());
	assertNull(result.getTemplateAttribute());
	assertEquals(groups, result.getGroups());
	assertEquals(2L, result.getVersion());
    }

    @Test
    void collectionRequestToCollection_mapeaTodos()
    {

	TemplateAttributeCollectionMappingRequest req1 = new TemplateAttributeCollectionMappingRequest();
	req1.setName("M1");
	TemplateAttributeCollectionMappingRequest req2 = new TemplateAttributeCollectionMappingRequest();
	req2.setName("M2");

	Collection<TemplateAttributeCollectionMapping> result = mapper.templateAttributeCollectionMappingRequestCollectionToTemplateAttributeCollectionMappingCollection(List.of(req1, req2), Set.of("g"));

	assertEquals(2, result.size());
    }
}
