package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.DefaultValueAssetValue;
import es.cic.tessa.model.dto.DefaultValueAssetValueRequest;

class DefaultValueAssetValueRequestMapperTest
{

    private DefaultValueAssetValueRequestMapper mapper;

    @BeforeEach
    void setUp()
    {

	mapper = new DefaultValueAssetValueRequestMapper();
    }

    @Test
    void requestToDefaultValue_mapeaCampos()
    {

	DefaultValueAssetValueRequest req = new DefaultValueAssetValueRequest();
	req.setId(1L);
	req.setName("default1");
	req.setValue("42");
	req.setVersion(2L);

	Set<String> groups = Set.of("group1");

	DefaultValueAssetValue result = mapper.defaultValueRequestToDefaultValue(req, groups);

	assertEquals(1L, result.getCustomId());
	assertEquals("default1", result.getName());
	assertEquals("42", result.getValue());
	assertEquals(groups, result.getGroups());
	assertEquals(2L, result.getVersion());
    }

    @Test
    void requestToDefaultValue_sinId_noSeteaCustomId()
    {

	DefaultValueAssetValueRequest req = new DefaultValueAssetValueRequest();
	req.setName("default2");
	req.setValue("100");

	DefaultValueAssetValue result = mapper.defaultValueRequestToDefaultValue(req, Set.of("g"));

	assertNull(result.getCustomId());
	assertEquals("default2", result.getName());
    }

    @Test
    void requestToDefaultValue_sinVersion_mantienePorDefecto()
    {

	DefaultValueAssetValueRequest req = new DefaultValueAssetValueRequest();
	req.setName("dv");
	req.setValue("v");

	DefaultValueAssetValue result = mapper.defaultValueRequestToDefaultValue(req, Set.of("g"));

	assertEquals(0L, result.getVersion());
    }
}
