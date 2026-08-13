package es.cic.tessa.model.assembly;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Value;
import org.neo4j.driver.internal.value.NullValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.asset.exceptions.AssetException;
import es.cic.tessa.model.asset.exceptions.AssetValueException;
import es.cic.tessa.model.optimize.AssetOptimize;
import es.cic.tessa.model.optimize.AssetValueOptimize;
import es.cic.tessa.model.optimize.TemplateAttributeOptimize;
import es.cic.tessa.model.optimize.TemplateOptimize;


public class AssemblyAssetOptimize
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AssemblyAssetOptimize.class);

    public static ResponsePage<AssetOptimize> buildAssetsOptimize(Result result)
    {

	List<AssetOptimize> assetsOptimize = new ArrayList<>();

	try
	{

	    while (result.hasNext())
	    {
		Record row = result.next();

		AssetOptimize assetOptimize = new AssetOptimize();
		assetOptimize.setId(row.get("id").asLong());
		assetOptimize.setName(row.get("name").asString());
		assetOptimize.setIdentificator(row.get("identificator").asString());

		Value templateNode = row.get("templateOptimize");

		TemplateOptimize templateOptimize = new TemplateOptimize();
		templateOptimize.setId(templateNode.get("id").asLong());
		templateOptimize.setName(templateNode.get("name").asString());
		templateOptimize.setType(templateNode.get("type").asString());

		assetOptimize.setTemplateOptimize(templateOptimize);

		if(!row.get("assetDependsOptimize").equals(NullValue.NULL))
		{
		    Value assetDependsNode = row.get("assetDependsOptimize");

		    AssetOptimize assetDependsOptimize = null;

		    assetDependsOptimize = new AssetOptimize();

		    assetDependsOptimize.setId(assetDependsNode.get("id").asLong());
		    assetDependsOptimize.setName(assetDependsNode.get("name").asString());
		    assetDependsOptimize.setIdentificator(assetDependsNode.get("identificator").asString());

		    assetOptimize.setAssetDependsOptimize(assetDependsOptimize);
		}

		List<Map<String, Object>> valuesList = row.get("values").asList(Value::asMap);

		assemblyAssetValues(assetOptimize, valuesList);

		assetsOptimize.add(assetOptimize);
	    }
	}
	catch (Exception e)
	{
	    LOGGER.error("Error searching optimize assets!", e);
	    throw new AssetException("Error searching optimize assets! ", e);
	}

	return new ResponsePage<>(new ArrayList<>(assetsOptimize));

    }


    private static void assemblyAssetValues(AssetOptimize assetOptimize, List<Map<String, Object>> valuesList)
    {

	try
	{
	    for (Map<String, Object> value : valuesList)
	    {

		AssetValueOptimize assetValueOptimize = new AssetValueOptimize();

		assetValueOptimize.setId((Long) value.get("idValue"));
		assetValueOptimize.setName((String) value.get("name"));
		assetValueOptimize.setValue((String) value.get("value"));

		if(value.get("expressionProperties") != null)
		{
		    AssemblyAssetValueOptimize.buildExpressionProperties(assetValueOptimize, (String) value.get("expressionProperties"));
		}

		assemblyExpressionParams(value, assetValueOptimize);

		assetValueOptimize.setAssetOptimize(assetOptimize);

		if(!value.get("assetReferenceOptimize").equals(NullValue.NULL))
		{

		    Object assetReferenceObject = value.get("assetReferenceOptimize");

		    Map<?, ?> assetReferenceMap = (Map<?, ?>) assetReferenceObject;

		    if(assetReferenceMap.get("id") != null)
		    {
			AssetOptimize assetReferenceOptimize = new AssetOptimize();
			assetReferenceOptimize.setId((Long) assetReferenceMap.get("id"));
			assetReferenceOptimize.setName((String) assetReferenceMap.get("name"));
			assetReferenceOptimize.setIdentificator((String) assetReferenceMap.get("identificator"));

			if(!assetReferenceMap.get("templateReference").equals(NullValue.NULL))
			{
			    Object templateReferenceObject = assetReferenceMap.get("templateReference");

			    Map<?, ?> templateReferenceMap = (Map<?, ?>) templateReferenceObject;

			    TemplateOptimize templateReferenceOptimize = new TemplateOptimize();
			    templateReferenceOptimize.setId((Long) templateReferenceMap.get("id"));
			    templateReferenceOptimize.setName((String) templateReferenceMap.get("name"));

			    assetReferenceOptimize.setTemplateOptimize(templateReferenceOptimize);
			}

			if(!assetReferenceMap.get("assetDepends").equals(NullValue.NULL))
			{
			    Object assetReferenceDependsObject = assetReferenceMap.get("assetDepends");

			    Map<?, ?> assetReferenceDependsMap = (Map<?, ?>) assetReferenceDependsObject;

			    AssetOptimize assetDependsReferenceOptimize = new AssetOptimize();
			    assetDependsReferenceOptimize.setId((Long) assetReferenceDependsMap.get("id"));
			    assetDependsReferenceOptimize.setName((String) assetReferenceDependsMap.get("name"));
			    assetDependsReferenceOptimize.setIdentificator((String) assetReferenceDependsMap.get("identificator"));

			    assetReferenceOptimize.setAssetDependsOptimize(assetDependsReferenceOptimize);
			}

			assetValueOptimize.setAssetReferenceOptimize(assetReferenceOptimize);
		    }

		}

		Object templateAttributeObject = value.get("templateAttributeOptimize");

		Map<?, ?> templateAttributeMap = (Map<?, ?>) templateAttributeObject;

		TemplateAttributeOptimize templateAttributeOptimize = new TemplateAttributeOptimize();

		templateAttributeOptimize.setId((Long) templateAttributeMap.get("id"));
		templateAttributeOptimize.setName((String) templateAttributeMap.get("name"));
		templateAttributeOptimize.setType((String) templateAttributeMap.get("type"));
		templateAttributeOptimize.setCalculatedValue((String) templateAttributeMap.get("calculatedValue"));

		if(templateAttributeMap.get("expressionProperties") != null)
		{
		    AssemblyTemplateAttributeOptimize.buildExpressionProperties(templateAttributeOptimize, (String) templateAttributeMap.get("expressionProperties"));
		}

		if(templateAttributeMap.get("collection") != null)
		{
		    templateAttributeOptimize.setCollection((Boolean) templateAttributeMap.get("collection"));
		}
		else
		{

		    templateAttributeOptimize.setCollection(false);
		}

		assetValueOptimize.setTemplateAttributeOptimize(templateAttributeOptimize);

		assetOptimize.getAssetValueOptimize().add(assetValueOptimize);
	    }
	}
	catch (Exception e)
	{
	    LOGGER.error("Error searching optimize assets!", e);
	    throw new AssetValueException("Error searching optimize assets! ", e);
	}
    }


    private static void assemblyExpressionParams(Map<String, Object> value, AssetValueOptimize assetValueOptimize)
    {

	JsonMapper objectMapper = JsonMapper.builder().build();
	List<Map<String, Object>> expressionParams = objectMapper.convertValue(value.get("expressionParams"), new TypeReference<List<Map<String, Object>>>()
	{
	});

	if(!expressionParams.isEmpty())
	{
	    AssemblyAssetValueOptimize.buildExpressionParams(assetValueOptimize, expressionParams);
	}
    }
}