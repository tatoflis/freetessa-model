package es.cic.tessa.model.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.Value;
import org.neo4j.driver.Values;
import es.cic.tessa.model.Function;

class FunctionConverterTest
{

    private FunctionConverter converter;

    @BeforeEach
    void setUp()
    {

	converter = new FunctionConverter();
    }

    @Test
    void write_conTodosCampos_generaJsonCorrecto()
    {

	Function function = new Function("SUM(x)", true, false, "0 0 * * *", "ON_CHANGE", "UTC", 5000L);

	Value result = converter.write(function);

	String json = result.asString();
	assertTrue(json.contains("\"expressionFunction\":\"SUM(x)\""));
	assertTrue(json.contains("\"refillingCalculation\":true"));
	assertTrue(json.contains("\"ignoreNoData\":false"));
	assertTrue(json.contains("\"cronExpression\":\"0 0 * * *\""));
	assertTrue(json.contains("\"expressionEvent\":\"ON_CHANGE\""));
	assertTrue(json.contains("\"cronTimeZone\":\"UTC\""));
	assertTrue(json.contains("\"cronDelay\":5000"));
    }

    @Test
    void write_conCamposNull_omiteEnJson()
    {

	Function function = new Function();
	function.setExpressionFunction("expr");

	Value result = converter.write(function);

	String json = result.asString();
	assertTrue(json.contains("expressionFunction"));
	assertFalse(json.contains("cronExpression"));
    }

    @Test
    void read_conJsonCompleto_deserializaCorrectamente()
    {

	String json = "{\"expressionFunction\":\"AVG(x)\",\"refillingCalculation\":true,\"ignoreNoData\":false,\"cronExpression\":\"0 1 * * *\",\"expressionEvent\":\"ON_CREATE\",\"cronTimeZone\":\"CET\",\"cronDelay\":1000}";
	Value value = Values.value(json);

	Function result = converter.read(value);

	assertEquals("AVG(x)", result.getExpressionFunction());
	assertTrue(result.getRefillingCalculation());
	assertFalse(result.getIgnoreNoData());
	assertEquals("0 1 * * *", result.getCronExpression());
	assertEquals("ON_CREATE", result.getExpressionEvent());
	assertEquals("CET", result.getCronTimeZone());
	assertEquals(1000L, result.getCronDelay());
    }

    @Test
    void read_conJsonParcial_camposNoPresentes_sonNull()
    {

	String json = "{\"expressionFunction\":\"expr\"}";
	Value value = Values.value(json);

	Function result = converter.read(value);

	assertEquals("expr", result.getExpressionFunction());
	assertNull(result.getRefillingCalculation());
	assertNull(result.getCronExpression());
    }

    @Test
    void read_conJsonComillasSimples_deserializaCorrectamente()
    {

	String json = "{'expressionFunction':'max({Contadores->Consumo instantaneo->Value})'}";
	Value value = Values.value(json);

	Function result = converter.read(value);

	assertEquals("max({Contadores->Consumo instantaneo->Value})", result.getExpressionFunction());
	assertNull(result.getCronExpression());
    }

    @Test
    void writeYRead_roundTrip_conservaDatos()
    {

	Function original = new Function("CALC(a,b)", true, false, "0 0 12 * * ?", "ON_UPDATE", "Europe/Madrid", 3000L);

	Value written = converter.write(original);
	Function restored = converter.read(written);

	assertEquals(original.getExpressionFunction(), restored.getExpressionFunction());
	assertEquals(original.getRefillingCalculation(), restored.getRefillingCalculation());
	assertEquals(original.getIgnoreNoData(), restored.getIgnoreNoData());
	assertEquals(original.getCronExpression(), restored.getCronExpression());
	assertEquals(original.getExpressionEvent(), restored.getExpressionEvent());
	assertEquals(original.getCronTimeZone(), restored.getCronTimeZone());
	assertEquals(original.getCronDelay(), restored.getCronDelay());
    }
}
