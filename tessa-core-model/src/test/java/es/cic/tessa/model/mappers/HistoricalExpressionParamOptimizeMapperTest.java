package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.HistoricalDefaultValueAssetValue;
import es.cic.tessa.model.HistoricalExpressionParam;
import es.cic.tessa.model.optimize.ExpressionParamOptimize;

class HistoricalExpressionParamOptimizeMapperTest
{

    @Test
    void toOptimize_conNull_devuelveNull()
    {

	assertNull(HistoricalExpressionParamOptimizeMapper.toOptimize(null));
    }

    @Test
    void toOptimize_conCamposBasicos_mapeaCorrectamente()
    {

	HistoricalExpressionParam param = new HistoricalExpressionParam();
	param.setCustomId(101L);
	param.setName("param1");
	param.setGroups(Set.of("pg"));
	param.setType("STRING");
	param.setRequired(true);
	param.setPosition(2);

	ExpressionParamOptimize result = HistoricalExpressionParamOptimizeMapper.toOptimize(param);

	assertNotNull(result);
	assertEquals(101L, result.getId());
	assertEquals("param1", result.getName());
	assertEquals("STRING", result.getType());
	assertTrue(result.isRequired());
	assertEquals(2, result.getPosition());
    }

    @Test
    void toOptimize_conDefaultValueAssetValue_mapeaDefaultValue()
    {

	HistoricalDefaultValueAssetValue defaultValue = new HistoricalDefaultValueAssetValue();
	defaultValue.setValue("valor-por-defecto");

	HistoricalExpressionParam param = new HistoricalExpressionParam();
	param.setCustomId(101L);
	param.setName("param1");
	param.setHistoricalDefaultValueAssetValue(defaultValue);

	ExpressionParamOptimize result = HistoricalExpressionParamOptimizeMapper.toOptimize(param);

	assertEquals("valor-por-defecto", result.getDefaultValue());
    }

    @Test
    void toOptimize_sinDefaultValueAssetValue_defaultValueEsNull()
    {

	HistoricalExpressionParam param = new HistoricalExpressionParam();
	param.setCustomId(101L);
	param.setName("param1");

	ExpressionParamOptimize result = HistoricalExpressionParamOptimizeMapper.toOptimize(param);

	assertNull(result.getDefaultValue());
    }
}
