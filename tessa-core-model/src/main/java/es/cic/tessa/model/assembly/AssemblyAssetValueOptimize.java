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
import es.cic.tessa.model.asset.exceptions.AssetValueException;
import es.cic.tessa.model.optimize.AssetOptimize;
import es.cic.tessa.model.optimize.AssetValueOptimize;
import es.cic.tessa.model.optimize.ExpressionParamOptimize;
import es.cic.tessa.model.optimize.TemplateAttributeOptimize;
import es.cic.tessa.model.optimize.TemplateOptimize;


public class AssemblyAssetValueOptimize
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AssemblyAssetValueOptimize.class);

    private static final JsonMapper OBJECT_MAPPER = JsonMapper.builder()
            .enable(JsonReadFeature.ALLOW_SINGLE_QUOTES)
            .build();

    public static ResponsePage<AssetValueOptimize> buildAssetValuesOptimize(Result result)
    {

	List<AssetValueOptimize> assetValuesOptimize = new ArrayList<>();

	try
	{

	    while (result.hasNext())
	    {
		Record row = result.next();

		AssetValueOptimize assetValueOptimize = new AssetValueOptimize();
		assetValueOptimize.setId(row.get("id").asLong());

		// TODO Revisar si es necesario el idBinary, sino borrar en la
		// siguiente iteracion tanto de aqui como del modelo como de la
		// consulta
		// if(!row.get("idBinary").equals(NullValue.NULL))
		// {
		// assetValueOptimize.setIdBinary(row.get("idBinary").asLong());
		// }

		assetValueOptimize.setName(row.get("name").asString());
		assetValueOptimize.setValue(row.get("value").asString());

		if(!row.get("expressionProperties").equals(NullValue.NULL))
		{
		    buildExpressionProperties(assetValueOptimize, row.get("expressionProperties").asString());

		}

		if(!row.get("expressionParams").equals(NullValue.NULL))
		{
		    buildExpressionParams(assetValueOptimize, row.get("expressionParams").asList(Value::asMap));
		}

		Value assetNode = row.get("assetOptimize");

		AssetOptimize assetOptimize = new AssetOptimize();
		assetOptimize.setId(assetNode.get("id").asLong());
		assetOptimize.setName(assetNode.get("name").asString());
		assetOptimize.setIdentificator(assetNode.get("identificator").asString());
		assetOptimize.setActive(assetNode.get("active").asBoolean());

		Value templateNode = assetNode.get("templateOptimize");
		TemplateOptimize templateOptimize = new TemplateOptimize();
		templateOptimize.setId(templateNode.get("id").asLong());
		templateOptimize.setName(templateNode.get("name").asString());
		templateOptimize.setType(templateNode.get("type").asString());

		assetOptimize.setTemplateOptimize(templateOptimize);

		assetValueOptimize.setAssetOptimize(assetOptimize);

		Value attributeNode = row.get("templateAttributeOptimize");

		TemplateAttributeOptimize templateAttributeOptimize = new TemplateAttributeOptimize();
		templateAttributeOptimize.setId((attributeNode.get("id").asLong()));
		templateAttributeOptimize.setName(attributeNode.get("name").asString());
		templateAttributeOptimize.setType(attributeNode.get("type").asString());
		templateAttributeOptimize.setCalculatedValue(attributeNode.get("calculatedValue").asString());

		Set<String> nodeGroups = new HashSet<>();

		for (Object label : attributeNode.get("groups").asList())
		{
		    if(!label.equals(Labels.TEMPLATE_ATTRIBUTE))
		    {
			nodeGroups.add(label.toString());
		    }

		}

		templateAttributeOptimize.setGroups(nodeGroups);

		if(!attributeNode.get("templateOptimize").equals(NullValue.NULL))
		{

		    Value taTemplateNode = attributeNode.get("templateOptimize");
		    TemplateOptimize taTemplateOptimize = new TemplateOptimize();
		    taTemplateOptimize.setId(taTemplateNode.get("id").asLong());
		    taTemplateOptimize.setName(taTemplateNode.get("name").asString());
		    taTemplateOptimize.setType(taTemplateNode.get("type").asString());

		    templateAttributeOptimize.setTemplateOptimize(taTemplateOptimize);

		}

		if(!attributeNode.get("templateReference").equals(NullValue.NULL))
		{

		    Value taTemplateRefNode = attributeNode.get("templateReference");
		    TemplateOptimize taTemplateOptimize = new TemplateOptimize();
		    taTemplateOptimize.setId(taTemplateRefNode.get("id").asLong());
		    taTemplateOptimize.setName(taTemplateRefNode.get("name").asString());
		    taTemplateOptimize.setType(taTemplateRefNode.get("type").asString());

		    templateAttributeOptimize.setTemplateReferenceOptimize(taTemplateOptimize);

		}

		if(!attributeNode.get("expressionProperties").equals(NullValue.NULL))
		{
		    AssemblyTemplateAttributeOptimize.buildExpressionProperties(templateAttributeOptimize, attributeNode.get("expressionProperties").asString());

		}

		templateAttributeOptimize.setCollection(attributeNode.get("collection").asBoolean());

		assetValueOptimize.setTemplateAttributeOptimize(templateAttributeOptimize);

		if(!row.get("assetReferenceOptimize").equals(NullValue.NULL))
		{
		    Value assetReferenceNode = row.get("assetReferenceOptimize");

		    AssetOptimize assetReferenceOptimize = new AssetOptimize();
		    assetReferenceOptimize.setId(assetReferenceNode.get("id").asLong());
		    assetReferenceOptimize.setName(assetReferenceNode.get("name").asString());
		    assetReferenceOptimize.setIdentificator(assetReferenceNode.get("identificator").asString());
		    assetReferenceOptimize.setActive(assetReferenceNode.get("active").asBoolean());

		    Value templateReferenceNode = assetReferenceNode.get("templateReferenceOptimize");

		    TemplateOptimize templateReferenceOptimize = new TemplateOptimize();
		    templateReferenceOptimize.setId(templateReferenceNode.get("id").asLong());
		    templateReferenceOptimize.setName(templateReferenceNode.get("name").asString());
		    templateReferenceOptimize.setType(templateReferenceNode.get("type").asString());

		    assetReferenceOptimize.setTemplateOptimize(templateReferenceOptimize);

		    if(!assetReferenceNode.get("referenceDepends").equals(NullValue.NULL))
		    {
			Value referenceDependsNode = assetReferenceNode.get("referenceDepends");

			AssetOptimize referenceDependsOptimize = new AssetOptimize();
			referenceDependsOptimize.setId(referenceDependsNode.get("id").asLong());
			referenceDependsOptimize.setName(referenceDependsNode.get("name").asString());
			referenceDependsOptimize.setIdentificator(referenceDependsNode.get("identificator").asString());

			assetReferenceOptimize.setAssetDependsOptimize(referenceDependsOptimize);
		    }

		    assetValueOptimize.setAssetReferenceOptimize(assetReferenceOptimize);
		}

		assetValuesOptimize.add(assetValueOptimize);
	    }
	}
	catch (Exception e)
	{
	    LOGGER.error("Error searching optimize assetValues!", e);
	    throw new AssetValueException("Error searching optimize assetValues! ", e);
	}

	return new ResponsePage<>(assetValuesOptimize);

    }


    public static void buildExpressionProperties(AssetValueOptimize assetValueOptimize, String expressionProperties) throws JacksonException
    {

	Map<String, Object> map = OBJECT_MAPPER.readValue(expressionProperties, new TypeReference<Map<String, Object>>()
	{
	});

	if(map.get("expressionFunction") != null)
	{
	    assetValueOptimize.setExpressionFunction((String) map.get("expressionFunction"));
	}

	if(map.get("expressionEvent") != null)
	{
	    assetValueOptimize.setExpressionEvent((String) map.get("expressionEvent"));
	}

	if(map.get("cronDelay") != null)
	{
	    assetValueOptimize.setCronDelay(Long.parseLong(String.valueOf(map.get("cronDelay"))));
	}
	if(map.get("cronTimeZone") != null)
	{

	    assetValueOptimize.setCronTimeZone((String) map.get("cronTimeZone"));
	}

	if(map.get("cronExpression") != null)
	{
	    assetValueOptimize.setCronExpression((String) map.get("cronExpression"));
	}

	if(map.get("refillingCalculation") != null)
	{
	    assetValueOptimize.setRefillingCalculation((Boolean) map.get("refillingCalculation"));
	}
	else
	{
	    assetValueOptimize.setRefillingCalculation(false);
	}

	if(map.get("ignoreNoData") != null)
	{
	    assetValueOptimize.setIgnoreNoData((Boolean) map.get("ignoreNoData"));
	}
	else
	{
	    assetValueOptimize.setIgnoreNoData(false);
	}
    }


    public static void buildExpressionParams(AssetValueOptimize assetValueOptimize, List<Map<String, Object>> expressionParams)
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

	    assetValueOptimize.getExpressionParams().add(expressionParam);
	}

    }
}