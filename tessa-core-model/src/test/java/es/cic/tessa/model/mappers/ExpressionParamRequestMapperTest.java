package es.cic.tessa.model.mappers;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import es.cic.tessa.model.ExpressionParam;
import es.cic.tessa.model.dto.ExpressionParamRequest;


@ExtendWith(MockitoExtension.class)
class ExpressionParamRequestMapperTest
{

    @Mock
    private DefaultValueAssetValueRequestMapper defaultValueMapper;

    private ExpressionParamRequestMapper mapper;

    @BeforeEach
    void setUp()
    {

	mapper = new ExpressionParamRequestMapper(defaultValueMapper);
    }


    @Test
    void expressionParamRequestToExpressionParam_mapeaCamposBasicos()
    {

	ExpressionParamRequest request = new ExpressionParamRequest();
	request.setId(1L);
	request.setName("param1");
	request.setType("STRING");
	request.setRequired(true);
	request.setPosition(0);
	request.setDescription("desc");
	request.setIcon("icon.png");
	request.setVersion(2L);

	ExpressionParam result = mapper.expressionParamRequestToExpressionParam(request, Set.of("g1"));

	assertEquals(1L, result.getId());
	assertEquals("param1", result.getName());
	assertEquals("STRING", result.getType());
	assertTrue(result.getRequired());
	assertEquals(0, result.getPosition());
	assertEquals("desc", result.getDescription());
	assertEquals("icon.png", result.getIcon());
	assertEquals(2L, result.getVersion());
	assertTrue(result.getGroups().contains("g1"));
    }


    @Test
    void expressionParamRequestToExpressionParam_sinId_noSetea()
    {

	ExpressionParamRequest request = new ExpressionParamRequest();
	request.setName("param");
	request.setType("NUMBER");

	ExpressionParam result = mapper.expressionParamRequestToExpressionParam(request, Set.of("g"));

	assertNull(result.getCustomId());
    }


    @Test
    void expressionParamToExpressionParamRequest_mapeaCampos()
    {

	ExpressionParam param = new ExpressionParam();
	param.setCustomId(1L);
	param.setName("param1");
	param.setType("INTEGER");
	param.setRequired(true);
	param.setPosition(3);
	param.setVersion(5L);

	ExpressionParamRequest result = mapper.expressionParamToExpressionParamRequest(param);

	assertEquals(1L, result.getId());
	assertEquals("param1", result.getName());
	assertEquals("INTEGER", result.getType());
	assertTrue(result.isRequired());
	assertEquals(3, result.getPosition());
	assertEquals(5L, result.getVersion());
    }


    @Test
    void expressionParamsRequestToExpressionParams_mapeaColeccion()
    {

	ExpressionParamRequest r1 = new ExpressionParamRequest();
	r1.setName("p1");
	r1.setType("STRING");

	ExpressionParamRequest r2 = new ExpressionParamRequest();
	r2.setName("p2");
	r2.setType("NUMBER");

	Set<ExpressionParam> result = mapper.expressionParamsRequestToExpressionParams(List.of(r1, r2), Set.of("g"));

	assertEquals(2, result.size());
    }


    @Test
    void expressionParamsToExpressionParamsRequest_mapeaColeccion()
    {

	ExpressionParam p1 = new ExpressionParam();
	p1.setCustomId(1L);
	p1.setName("p1");
	p1.setType("STRING");
	p1.setRequired(true);

	Set<ExpressionParamRequest> result = mapper.expressionParamsToExpressionParamsRequest(Set.of(p1));

	assertEquals(1, result.size());
    }
}
