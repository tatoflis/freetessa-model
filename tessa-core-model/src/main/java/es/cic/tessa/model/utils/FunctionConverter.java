package es.cic.tessa.model.utils;


import java.util.HashMap;
import java.util.Map;
import org.neo4j.driver.Value;
import org.neo4j.driver.Values;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.neo4j.core.convert.Neo4jPersistentPropertyConverter;
import tools.jackson.core.json.JsonReadFeature;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;
import es.cic.tessa.common.exceptions.TessaException;
import es.cic.tessa.model.Function;
import es.cic.tessa.model.properties.FunctionProperties;


@ReadingConverter
@WritingConverter
public class FunctionConverter implements Neo4jPersistentPropertyConverter<Function>
{

    private static final JsonMapper OBJECT_MAPPER = JsonMapper.builder()
            .enable(JsonReadFeature.ALLOW_SINGLE_QUOTES)
            .build();

    @Override
    public Value write(Function source)
    {

	Map<String, Object> parameters = new HashMap<>();

	if(source.getExpressionFunction() != null)
	{
	    parameters.put(FunctionProperties.EXPRESSION_FUNCTION, source.getExpressionFunction());
	}

	if(source.getRefillingCalculation() != null)
	{
	    parameters.put(FunctionProperties.REFILLING_CALCULATION, source.getRefillingCalculation());
	}

	if(source.getIgnoreNoData() != null)
	{
	    parameters.put(FunctionProperties.IGNORE_NO_DATA, source.getIgnoreNoData());
	}

	if(source.getCronExpression() != null)
	{
	    parameters.put(FunctionProperties.CRON_EXPRESSION, source.getCronExpression());
	}

	if(source.getExpressionEvent() != null)
	{
	    parameters.put(FunctionProperties.EXPRESSION_EVENT, source.getExpressionEvent());
	}

	if(source.getCronTimeZone() != null)
	{
	    parameters.put(FunctionProperties.CRON_TIME_ZONE, source.getCronTimeZone());
	}

	if(source.getCronDelay() != null)
	{
	    parameters.put(FunctionProperties.CRON_DELAY, source.getCronDelay());
	}

	try
	{
	    String json = OBJECT_MAPPER.writeValueAsString(parameters);
	    return Values.value(json);
	}
	catch (Exception e)
	{
	    throw new TessaException("Error serializing to JSON", e);
	}

    }


    @Override
    public Function read(Value source)
    {

	Function function = new Function();

	try
	{
	    String json = source.asString();

	    Map<String, Object> parameters = OBJECT_MAPPER.readValue(json, new TypeReference<Map<String, Object>>()
	    {
	    });

	    if(parameters.get(FunctionProperties.EXPRESSION_FUNCTION) != null)
	    {

		function.setExpressionFunction((String) parameters.get(FunctionProperties.EXPRESSION_FUNCTION));
	    }

	    if(parameters.get(FunctionProperties.REFILLING_CALCULATION) != null)
	    {

		function.setRefillingCalculation((Boolean) parameters.get(FunctionProperties.REFILLING_CALCULATION));
	    }

	    if(parameters.get(FunctionProperties.IGNORE_NO_DATA) != null)
	    {

		function.setIgnoreNoData((Boolean) parameters.get(FunctionProperties.IGNORE_NO_DATA));
	    }

	    if(parameters.get(FunctionProperties.CRON_EXPRESSION) != null)
	    {

		function.setCronExpression((String) parameters.get(FunctionProperties.CRON_EXPRESSION));
	    }

	    if(parameters.get(FunctionProperties.EXPRESSION_EVENT) != null)
	    {

		function.setExpressionEvent((String) parameters.get(FunctionProperties.EXPRESSION_EVENT));

	    }

	    if(parameters.get(FunctionProperties.CRON_TIME_ZONE) != null)
	    {

		function.setCronTimeZone((String) parameters.get(FunctionProperties.CRON_TIME_ZONE));
	    }

	    if(parameters.get(FunctionProperties.CRON_DELAY) != null)
	    {
		function.setCronDelay(Long.valueOf(String.valueOf(parameters.get(FunctionProperties.CRON_DELAY))));
	    }

	}
	catch (Exception e)
	{
	    throw new TessaException("Error deserializing from JSON", e);
	}
	return function;
    }

}
