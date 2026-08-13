package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.Function;
import es.cic.tessa.model.dto.FunctionResponse;

class FunctionResponseMapperTest
{

    private FunctionResponseMapper mapper;

    @BeforeEach
    void setUp()
    {

	mapper = new FunctionResponseMapper();
    }

    @Test
    void functionToFunctionResponse_mapeaTodosCampos()
    {

	Function function = new Function("SUM(x,y)", true, false, "0 0 * * *", "ON_CHANGE", "UTC", 5000L);

	FunctionResponse result = mapper.functionToFunctionResponse(function);

	assertEquals("SUM(x,y)", result.getExpressionFunction());
	assertTrue(result.isRefillingCalculation());
	assertFalse(result.isIgnoreNoData());
	assertEquals("0 0 * * *", result.getCronExpression());
	assertEquals("ON_CHANGE", result.getExpressionEvent());
	assertEquals("UTC", result.getCronTimeZone());
	assertEquals(5000L, result.getCronDelay());
    }

    @Test
    void functionResponseToFunction_mapeaTodosCampos()
    {

	FunctionResponse response = new FunctionResponse();
	response.setExpressionFunction("AVG(x)");
	response.setRefillingCalculation(false);
	response.setIgnoreNoData(true);
	response.setCronExpression("0 1 * * *");
	response.setExpressionEvent("ON_CREATE");
	response.setCronTimeZone("CET");
	response.setCronDelay(1000L);

	Function result = mapper.functionResponseToFunction(response);

	assertEquals("AVG(x)", result.getExpressionFunction());
	assertFalse(result.getRefillingCalculation());
	assertTrue(result.getIgnoreNoData());
	assertEquals("0 1 * * *", result.getCronExpression());
	assertEquals("ON_CREATE", result.getExpressionEvent());
	assertEquals("CET", result.getCronTimeZone());
	assertEquals(1000L, result.getCronDelay());
    }
}
