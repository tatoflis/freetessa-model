package es.cic.tessa.model;


import java.util.Objects;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import es.cic.tessa.common.model.Labels;
import es.cic.tessa.common.model.Relations;
import es.cic.tessa.model.support.TessaElement;


@Node(Labels.DEFAULT_VALUE_ASSET_VALUE)
public class DefaultValueAssetValue extends TessaElement implements Comparable<DefaultValueAssetValue>
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long nodeId;

    @Property(name = "value")
    private String value;

    @Relationship(type = Relations.EXPRESSION_RELATION, direction = Direction.INCOMING)
    private ExpressionParam expressionParam;

    @Override
    public Long getId()
    {

	return nodeId;
    }


    @Override
    public void setId(Long nodeId)
    {

	this.nodeId = nodeId;
    }


    public String getValue()
    {

	return value;
    }


    public void setValue(String value)
    {

	this.value = value;
    }


    public ExpressionParam getExpressionParam()
    {

	return expressionParam;
    }


    public void setExpressionParam(ExpressionParam expressionParam)
    {

	this.expressionParam = expressionParam;
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(expressionParam, nodeId, value);
	return result;
    }


    @Override
    public boolean equals(Object obj)
    {

	if(this == obj)
	    return true;
	if(!super.equals(obj))
	    return false;
	if(getClass() != obj.getClass())
	    return false;
	DefaultValueAssetValue other = (DefaultValueAssetValue) obj;
	return Objects.equals(expressionParam, other.expressionParam) && Objects.equals(nodeId, other.nodeId) && Objects.equals(value, other.value);
    }


    @Override
    public int compareTo(DefaultValueAssetValue o)
    {

	String thisNemonic = this.getNemonic();
	String otherNemonic = o.getNemonic();
	if(thisNemonic == null && otherNemonic == null) return 0;
	if(thisNemonic == null) return -1;
	if(otherNemonic == null) return 1;
	return thisNemonic.compareTo(otherNemonic);
    }

}
