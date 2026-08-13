package es.cic.tessa.model.jsog;


import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ValueDeserializer;


public class JSOGRefDeserializer extends ValueDeserializer<JSOGRef>
{

    @Override
    public JSOGRef deserialize(JsonParser jp, DeserializationContext ctx)
    {

	JsonNode node = ctx.readTree(jp);

	if(node.isString())
	{
	    return new JSOGRef(node.asString());
	}
	else
	{
	    return new JSOGRef(node.get(JSOGRef.REF_KEY).asString());
	}
    }
}
