package es.cic.tessa.lookup.service.function;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.Test;
import es.cic.tessa.lookup.expression.model.ColumnValue;
import es.cic.tessa.lookup.expression.model.ResultExpressionFunction;
import es.cic.tessa.lookup.expression.model.ResultExpressionFunctionRecord;

class FunctionAssemblyTest
{

    @Test
    void buildAssetValueResultByValue_conValorSimple_creaResultadoCorrectamente()
    {

	Instant moment = Instant.now();
	ResultExpressionFunction result = FunctionAssembly.buildAssetValueResultByValue(100L, 200L, "temperature", "42.5", moment, 1);

	assertNotNull(result);
	assertEquals(1, result.getState());
	assertEquals(1, result.getRows().size());

	ResultExpressionFunctionRecord record = result.getRows().get(0);
	assertEquals(1, record.getRow().size());

	ColumnValue cv = record.getRow().get(0);
	assertEquals("100", cv.getSource());
	assertEquals("200", cv.getColumnId());
	assertEquals("temperature", cv.getColumnName());
	assertEquals("42.5", cv.getValue());
	assertEquals(moment, cv.getMoment());
    }

    @Test
    void buildAssetValueResultByValue_sinSynteticAssetId_usaAssetValueId()
    {

	ResultExpressionFunction result = FunctionAssembly.buildAssetValueResultByValue(null, 200L, "prop", "val", Instant.now(), 0);

	ColumnValue cv = result.getRows().get(0).getRow().get(0);
	assertEquals("200", cv.getSource());
    }

    @Test
    void buildAssetValueResultByValue_conValorNull_seteaNaN()
    {

	ResultExpressionFunction result = FunctionAssembly.buildAssetValueResultByValue(100L, 200L, "prop", (String) null, Instant.now(), 0);

	ColumnValue cv = result.getRows().get(0).getRow().get(0);
	assertEquals("[NaN]", cv.getValue());
    }

    @Test
    void buildAssetValueResultByValue_sinMoment_usaInstantNow()
    {

	Instant before = Instant.now();
	ResultExpressionFunction result = FunctionAssembly.buildAssetValueResultByValue(100L, 200L, "prop", "val", null, 0);
	Instant after = Instant.now();

	ColumnValue cv = result.getRows().get(0).getRow().get(0);
	assertNotNull(cv.getMoment());
	assertTrue(!cv.getMoment().isBefore(before) && !cv.getMoment().isAfter(after));
    }

    @Test
    void buildAssetValueResultByValue_conColeccion_creaMultiplesColumnValues()
    {

	Instant moment = Instant.now();
	ResultExpressionFunction result = FunctionAssembly.buildAssetValueResultByValue(100L, 200L, "prop", List.of("v1", "v2", "v3"), moment, 2);

	assertNotNull(result);
	assertEquals(2, result.getState());
	assertEquals(1, result.getRows().size());
	assertEquals(3, result.getRows().get(0).getRow().size());
    }

    @Test
    void buildAssetValueResultByValue_conColeccion_conNullEnValores_seteaNaN()
    {

	List<String> values = new java.util.ArrayList<>();
	values.add("v1");
	values.add(null);

	ResultExpressionFunction result = FunctionAssembly.buildAssetValueResultByValue(100L, 200L, "prop", values, Instant.now(), 0);

	List<ColumnValue> row = result.getRows().get(0).getRow();
	assertEquals("v1", row.get(0).getValue());
	assertEquals("[NaN]", row.get(1).getValue());
    }
}
