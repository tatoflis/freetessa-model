package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.DefaultValueAssetValue;
import es.cic.tessa.model.dto.DefaultValueAssetValueResponse;

class AssetValueDefaultValueResponseMapperTest
{

    private AssetValueDefaultValueResponseMapper mapper;

    @BeforeEach
    void setUp()
    {

	mapper = new AssetValueDefaultValueResponseMapper();
    }

    @Test
    void toResponse_mapeaCampos()
    {

	DefaultValueAssetValue dv = new DefaultValueAssetValue();
	dv.setCustomId(1L);
	dv.setName("default1");
	dv.setNemonic("nem1");
	dv.setValue("42");

	DefaultValueAssetValueResponse result = mapper.defaulValueAssetValueToDefaultValueAssetValueResponse(dv);

	assertEquals(1L, result.getId());
	assertEquals("default1", result.getName());
	assertEquals("nem1", result.getNemonic());
	assertEquals("42", result.getValue());
    }

    @Test
    void toEntity_mapeaCampos()
    {

	DefaultValueAssetValueResponse resp = new DefaultValueAssetValueResponse();
	resp.setId(5L);
	resp.setName("default5");
	resp.setNemonic("nem5");
	resp.setValue("100");

	DefaultValueAssetValue result = mapper.defaultValueAssetValueResponseToDefaultValueAssetValue(resp);

	assertEquals(5L, result.getCustomId());
	assertEquals("default5", result.getName());
	assertEquals("nem5", result.getNemonic());
	assertEquals("100", result.getValue());
    }

    @Test
    void listResponseToListEntity_mapeaTodos()
    {

	DefaultValueAssetValueResponse r1 = new DefaultValueAssetValueResponse();
	r1.setId(1L);
	r1.setName("d1");

	DefaultValueAssetValueResponse r2 = new DefaultValueAssetValueResponse();
	r2.setId(2L);
	r2.setName("d2");

	List<DefaultValueAssetValue> result = mapper.defaulValueAssetValuesResponseToDefaultValueAssetValues(List.of(r1, r2));

	assertEquals(2, result.size());
	assertEquals(1L, result.get(0).getCustomId());
	assertEquals(2L, result.get(1).getCustomId());
    }
}
