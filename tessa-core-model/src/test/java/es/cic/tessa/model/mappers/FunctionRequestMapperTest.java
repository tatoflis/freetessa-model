package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.Function;
import es.cic.tessa.model.dto.FunctionRequest;

class FunctionRequestMapperTest
{

    private FunctionRequestMapper mapper;

    @BeforeEach
    void setUp()
    {

	mapper = new FunctionRequestMapper();
    }

    @Test
    void functionRequestToFunction_mapeaTodosCampos()
    {

	FunctionRequest request = new FunctionRequest();
	request.setExpressionFunction("SUM(x,y)");
	request.setRefillingCalculation(true);
	request.setIgnoreNoData(false);
	request.setCronExpression("0 0 * * *");
	request.setExpressionEvent("ON_CHANGE");
	request.setCronTimeZone("UTC");
	request.setCronDelay(5000L);

	Function result = mapper.functionRequestToFunction(request);

	assertEquals("SUM(x,y)", result.getExpressionFunction());
	assertTrue(result.getRefillingCalculation());
	assertFalse(result.getIgnoreNoData());
	assertEquals("0 0 * * *", result.getCronExpression());
	assertEquals("ON_CHANGE", result.getExpressionEvent());
	assertEquals("UTC", result.getCronTimeZone());
	assertEquals(5000L, result.getCronDelay());
    }

    @Test
    void functionRequestToFunction_conNulls_noSetea()
    {

	FunctionRequest request = new FunctionRequest();
	request.setExpressionFunction("expr");
	request.setRefillingCalculation(null);
	request.setIgnoreNoData(null);

	Function result = mapper.functionRequestToFunction(request);

	assertEquals("expr", result.getExpressionFunction());
	assertNull(result.getRefillingCalculation());
	assertNull(result.getIgnoreNoData());
    }

    @Test
    void functionToFunctionRequest_mapeaTodosCampos()
    {

	Function function = new Function("SUM(x,y)", true, false, "0 0 * * *", "ON_CHANGE", "UTC", 5000L);

	FunctionRequest result = mapper.functionToFunctionRequest(function);

	assertEquals("SUM(x,y)", result.getExpressionFunction());
	assertTrue(result.isRefillingCalculation());
	assertFalse(result.isIgnoreNoData());
	assertEquals("0 0 * * *", result.getCronExpression());
	assertEquals("ON_CHANGE", result.getExpressionEvent());
	assertEquals("UTC", result.getCronTimeZone());
	assertEquals(5000L, result.getCronDelay());
    }
}
