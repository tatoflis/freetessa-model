package es.cic.tessa.model.assembly;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Value;
import org.neo4j.driver.internal.value.NullValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.core.JacksonException;
import tools.jackson.core.json.JsonReadFeature;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;
import es.cic.tessa.common.model.Labels;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.asset.exceptions.AssetException;
import es.cic.tessa.model.optimize.ExpressionParamOptimize;
import es.cic.tessa.model.optimize.TemplateAttributeOptimize;
import es.cic.tessa.model.optimize.TemplateOptimize;


public class AssemblyTemplateAttributeOptimize
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AssemblyTemplateAttributeOptimize.class);

    private static final JsonMapper OBJECT_MAPPER = JsonMapper.builder()
            .enable(JsonReadFeature.ALLOW_SINGLE_QUOTES)
            .build();

    public static ResponsePage<TemplateAttributeOptimize> buildTemplateAttributeOptimize(Result result)
    {

	List<TemplateAttributeOptimize> attributes = new ArrayList<>();

	try
	{
	    while (result.hasNext())
	    {
		Record row = result.next();

		TemplateAttributeOptimize templateAttributeOptimize = new TemplateAttributeOptimize();
		templateAttributeOptimize.setId((row.get("id").asLong()));
		templateAttributeOptimize.setName(row.get("name").asString());
		templateAttributeOptimize.setType(row.get("type").asString());

		Set<String> nodeGroups = new HashSet<>();

		for (Object label : row.get("groups").asList())
		{
		    if(!label.equals(Labels.TEMPLATE_ATTRIBUTE))
		    {
			nodeGroups.add(label.toString());
		    }

		}

		templateAttributeOptimize.setGroups(nodeGroups);

		if(!row.get("expressionProperties").equals(NullValue.NULL))
		{
		    buildExpressionProperties(templateAttributeOptimize, row.get("expressionProperties").asString());

		}

		if(!row.get("expressionParams").equals(NullValue.NULL))
		{
		    buildExpressionParams(templateAttributeOptimize, row.get("expressionParams").asList(Value::asMap));
		}

		if(!row.get("calculatedValue").equals(NullValue.NULL))
		{
		    templateAttributeOptimize.setCalculatedValue(row.get("calculatedValue").asString());
		}

		templateAttributeOptimize.setCollection(row.get("collection").asBoolean(false));

		Value templateNode = row.get("templateOptimize");

		TemplateOptimize templateOptimize = new TemplateOptimize();
		templateOptimize.setId(templateNode.get("id").asLong());
		templateOptimize.setName(templateNode.get("name").asString());
		templateOptimize.setType(templateNode.get("type").asString());

		templateAttributeOptimize.setTemplateOptimize(templateOptimize);

		Value templateReferenceNode = row.get("templateReferenceOptimize");

		if(!templateReferenceNode.equals(NullValue.NULL))
		{
		    TemplateOptimize templateReferenceOptimize = new TemplateOptimize();
		    templateReferenceOptimize.setId(templateReferenceNode.get("id").asLong());
		    templateReferenceOptimize.setName(templateReferenceNode.get("name").asString());

		    templateAttributeOptimize.setTemplateReferenceOptimize(templateReferenceOptimize);
		}

		attributes.add(templateAttributeOptimize);
	    }
	}
	catch (Exception e)
	{
	    LOGGER.error("Error assembling optimize organizers!", e);
	    throw new AssetException("Error assembling optimize organizers! ", e);
	}

	return new ResponsePage<>(attributes);
    }


    public static void buildExpressionProperties(TemplateAttributeOptimize templateAttributeOptimize, String json) throws JacksonException
    {

	Map<String, Object> map = OBJECT_MAPPER.readValue(json, new TypeReference<Map<String, Object>>()
	{
	});

	if(map.get("expressionFunction") != null)
	{
	    templateAttributeOptimize.setExpressionFunction((String) map.get("expressionFunction"));
	}

	if(map.get("expressionEvent") != null)
	{
	    templateAttributeOptimize.setExpressionEvent((String) map.get("expressionEvent"));
	}

	if(map.get("cronDelay") != null)
	{
	    templateAttributeOptimize.setCronDelay(Long.parseLong(String.valueOf(map.get("cronDelay"))));
	}
	if(map.get("cronTimeZone") != null)
	{

	    templateAttributeOptimize.setCronTimeZone((String) map.get("cronTimeZone"));
	}

	if(map.get("cronExpression") != null)
	{
	    templateAttributeOptimize.setCronExpression((String) map.get("cronExpression"));
	}

	if(map.get("refillingCalculation") != null)
	{
	    templateAttributeOptimize.setRefillingCalculation((Boolean) map.get("refillingCalculation"));
	}
	else
	{
	    templateAttributeOptimize.setRefillingCalculation(false);
	}

	if(map.get("ignoreNoData") != null)
	{
	    templateAttributeOptimize.setIgnoreNoData((Boolean) map.get("ignoreNoData"));
	}
	else
	{
	    templateAttributeOptimize.setIgnoreNoData(false);
	}
    }


    public static void buildExpressionParams(TemplateAttributeOptimize templateAttributeOptimize, List<Map<String, Object>> expressionParams)
    {

	ExpressionParamOptimize expressionParam;

	for (Map<String, Object> rawExpressionParam : expressionParams)
	{

	    expressionParam = new ExpressionParamOptimize();

	    if(rawExpressionParam.get("id") != null)
	    {

		expressionParam.setId((Long) rawExpressionParam.get("id"));
	    }

	    if(rawExpressionParam.get("name") != null)
	    {

		expressionParam.setName((String) rawExpressionParam.get("name"));
	    }

	    if(rawExpressionParam.get("type") != null)
	    {

		expressionParam.setType((String) rawExpressionParam.get("type"));
	    }

	    if(rawExpressionParam.get("required") != null)
	    {
		expressionParam.setRequired((Boolean) rawExpressionParam.get("required"));
	    }

	    if(rawExpressionParam.get("position") != null)
	    {

		expressionParam.setPosition(((Long) rawExpressionParam.get("position")).intValue());
	    }

	    if(rawExpressionParam.get("defaultValue") != null)
	    {
		expressionParam.setDefaultValue((String) rawExpressionParam.get("defaultValue"));
	    }

	    templateAttributeOptimize.getExpressionParams().add(expressionParam);
	}

    }

}
