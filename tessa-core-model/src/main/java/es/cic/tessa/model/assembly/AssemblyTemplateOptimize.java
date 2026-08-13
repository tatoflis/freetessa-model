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
import org.springframework.stereotype.Component;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;
import es.cic.tessa.common.model.Labels;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.asset.exceptions.AssetException;
import es.cic.tessa.model.optimize.TemplateAttributeOptimize;
import es.cic.tessa.model.optimize.TemplateOptimize;
import es.cic.tessa.model.template.exceptions.TemplateException;


@Component
public class AssemblyTemplateOptimize
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AssemblyTemplateOptimize.class);

    public static ResponsePage<TemplateOptimize> buildTemplateOptimize(Result result)
    {

	List<TemplateOptimize> templates = new ArrayList<>();

	try
	{
	    while (result.hasNext())
	    {
		Record row = result.next();

		TemplateOptimize templateOptimize = new TemplateOptimize();
		templateOptimize.setId((row.get("id").asLong()));
		templateOptimize.setName(row.get("name").asString());
		templateOptimize.setType(row.get("type").asString());

		if(!row.get("templateExtendsOptimize").equals(NullValue.NULL))
		{
		    Value templateExtendsNode = row.get("templateExtendsOptimize");

		    TemplateOptimize templateExtendsOptimize = new TemplateOptimize();
		    templateExtendsOptimize.setId(templateExtendsNode.get("id").asLong());
		    templateExtendsOptimize.setName(templateExtendsNode.get("name").asString());
		    templateExtendsOptimize.setType(templateExtendsNode.get("type").asString());

		    templateOptimize.setTemplateExtendsOptimize(templateExtendsOptimize);
		}

		if(!row.get("templateAttributesOptimize").equals(NullValue.NULL))
		{
		    assemblyTemplateAttributes(templateOptimize, row.get("templateAttributesOptimize").asList(Value::asMap));
		}

		templates.add(templateOptimize);
	    }
	}
	catch (Exception e)
	{
	    LOGGER.error("Error assembling optimize templates!", e);
	    throw new AssetException("Error assembling optimize templates! ", e);
	}

	return new ResponsePage<>(templates);
    }


    private static void assemblyTemplateAttributes(TemplateOptimize templateOptimize, List<Map<String, Object>> templateAttributes) throws JacksonException
    {

	TemplateAttributeOptimize templateAttributeOptimize;

	for (Map<String, Object> rawTemplateAttribute : templateAttributes)
	{

	    templateAttributeOptimize = new TemplateAttributeOptimize();

	    templateAttributeOptimize.setId((Long) rawTemplateAttribute.get("id"));
	    templateAttributeOptimize.setName((String) rawTemplateAttribute.get("name"));
	    templateAttributeOptimize.setType((String) rawTemplateAttribute.get("type"));

	    Set<String> nodeGroups = new HashSet<>();

	    for (Object label : List.of(rawTemplateAttribute.get("groups")))
	    {
		if(!label.equals(Labels.TEMPLATE_ATTRIBUTE))
		{
		    nodeGroups.add(label.toString());
		}

	    }

	    templateAttributeOptimize.setGroups(nodeGroups);

	    if(rawTemplateAttribute.get("calculatedValue") != null)
	    {
		templateAttributeOptimize.setCalculatedValue((String) rawTemplateAttribute.get("calculatedValue"));
	    }

	    templateAttributeOptimize.setCollection(Boolean.TRUE.equals(rawTemplateAttribute.get("collection")));

	    if(rawTemplateAttribute.get("expressionProperties") != null)
	    {
		try
		{
		    AssemblyTemplateAttributeOptimize.buildExpressionProperties(templateAttributeOptimize, rawTemplateAttribute.get("expressionProperties").toString());
		}
		catch (JacksonException e)
		{
		    LOGGER.error("Error assembling optimize expression properties for attribute: {}!", templateAttributeOptimize.getName(), e);
		    throw new TemplateException("Error assembling optimize expression properties for attribute: " + templateAttributeOptimize.getName() + "!", e);

		}

	    }

	    JsonMapper objectMapper = JsonMapper.builder().build();
	    List<Map<String, Object>> expressionParams = objectMapper.convertValue(rawTemplateAttribute.get("expressionParams"), new TypeReference<List<Map<String, Object>>>()
	    {
	    });

	    if(!expressionParams.isEmpty())
	    {
		AssemblyTemplateAttributeOptimize.buildExpressionParams(templateAttributeOptimize, expressionParams);
	    }

	    templateOptimize.getTemplateAttributesOptimize().add(templateAttributeOptimize);
	}

    }
}
