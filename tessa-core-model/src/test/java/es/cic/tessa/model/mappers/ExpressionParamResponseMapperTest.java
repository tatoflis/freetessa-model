package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import es.cic.tessa.model.DefaultValueAssetValue;
import es.cic.tessa.model.ExpressionParam;
import es.cic.tessa.model.dto.DefaultValueAssetValueResponse;
import es.cic.tessa.model.dto.ExpressionParamResponse;

@ExtendWith(MockitoExtension.class)
class ExpressionParamResponseMapperTest
{

    @Mock
    private AssetValueDefaultValueResponseMapper assetValueDefaultValueResponseMapper;

    @InjectMocks
    private ExpressionParamResponseMapper mapper;

    @Test
    void expressionParamToResponse_camposBasicos()
    {

	ExpressionParam ep = new ExpressionParam();
	ep.setCustomId(1L);
	ep.setName("param1");
	ep.setType("NUMERIC");
	ep.setRequired(true);
	ep.setPosition(0);
	ep.setVersion(1L);

	ExpressionParamResponse result = mapper.expressionParamToExpressionParamResponse(ep);

	assertEquals(1L, result.getId());
	assertEquals("param1", result.getName());
	assertEquals("NUMERIC", result.getType());
	assertTrue(result.getRequired());
	assertEquals(0, result.getPosition());
	assertEquals(1L, result.getVersion());
    }

    @Test
    void expressionParamToResponse_conDefaultValue_invocaMapper()
    {

	DefaultValueAssetValue dv = new DefaultValueAssetValue();
	dv.setCustomId(10L);

	ExpressionParam ep = new ExpressionParam();
	ep.setCustomId(1L);
	ep.setName("p");
	ep.setRequired(true);
	ep.setDefaultValueAssetValue(dv);

	DefaultValueAssetValueResponse dvResp = new DefaultValueAssetValueResponse();
	dvResp.setId(10L);
	when(assetValueDefaultValueResponseMapper.defaulValueAssetValueToDefaultValueAssetValueResponse(dv)).thenReturn(dvResp);

	ExpressionParamResponse result = mapper.expressionParamToExpressionParamResponse(ep);

	assertNotNull(result.getDefaultValueAssetValueResponse());
	assertEquals(10L, result.getDefaultValueAssetValueResponse().getId());
    }

    @Test
    void responseToExpressionParam_camposBasicos()
    {

	ExpressionParamResponse resp = new ExpressionParamResponse();
	resp.setId(1L);
	resp.setName("param1");
	resp.setType("TEXT");
	resp.setRequired(false);
	resp.setPosition(2);
	resp.setVersion(3L);

	ExpressionParam result = mapper.expressionParamResponseToExpressionParam(resp);

	assertEquals(1L, result.getId());
	assertEquals("param1", result.getName());
	assertEquals("TEXT", result.getType());
	assertFalse(result.getRequired());
	assertEquals(2, result.getPosition());
	assertEquals(3L, result.getVersion());
    }

    @Test
    void expressionParamsToResponseCollection_mapeaTodos()
    {

	ExpressionParam ep1 = new ExpressionParam();
	ep1.setCustomId(1L);
	ep1.setName("p1");
	ep1.setRequired(true);

	ExpressionParam ep2 = new ExpressionParam();
	ep2.setCustomId(2L);
	ep2.setName("p2");
	ep2.setRequired(false);

	var result = mapper.expressionParamsToExpressionParamResponseCollection(List.of(ep1, ep2));

	assertEquals(2, result.size());
    }

    @Test
    void responseCollectionToExpressionParams_mapeaTodos()
    {

	ExpressionParamResponse r1 = new ExpressionParamResponse();
	r1.setId(1L);
	r1.setName("p1");

	ExpressionParamResponse r2 = new ExpressionParamResponse();
	r2.setId(2L);
	r2.setName("p2");

	Set<ExpressionParam> result = mapper.expressionParamCollectionResponseToExpressionParamCollection(List.of(r1, r2));

	assertEquals(2, result.size());
    }
}
