package es.cic.tessa.model;


import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import es.cic.tessa.common.model.Labels;
import es.cic.tessa.common.model.Relations;
import es.cic.tessa.model.support.TessaElement;


@Node(Labels.HISTORICAL_DEFAULT_VALUE_ASSET_VALUE)
public class HistoricalDefaultValueAssetValue extends TessaElement implements Comparable<HistoricalDefaultValueAssetValue>
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long nodeId;

    @Property(name = "value")
    private String value;

    @Relationship(type = Relations.EXPRESSION_RELATION, direction = Direction.INCOMING)
    private HistoricalExpressionParam historicalExpressionParam;

    @Property(name = "historicalChangeOperation")
    private String historicalChangeOperation;

    @Property(name = "startChange")
    private LocalDateTime startChange;

    @Property(name = "endChange")
    private LocalDateTime endChange;

    public HistoricalDefaultValueAssetValue()
    {

    }


    public HistoricalDefaultValueAssetValue(String name, String value, Set<String> groups)
    {

	super(name, groups);
	this.value = value;
    }


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


    public HistoricalExpressionParam getHistoricalExpressionParam()
    {

	return historicalExpressionParam;
    }


    public void setHistoricalExpressionParam(HistoricalExpressionParam historicalExpressionParam)
    {

	this.historicalExpressionParam = historicalExpressionParam;
    }


    public String getHistoricalChangeOperation()
    {

	return historicalChangeOperation;
    }


    public void setHistoricalChangeOperation(String historicalChangeOperation)
    {

	this.historicalChangeOperation = historicalChangeOperation;
    }


    public LocalDateTime getStartChange()
    {

	return startChange;
    }


    public void setStartChange(LocalDateTime startChange)
    {

	this.startChange = startChange;
    }


    public LocalDateTime getEndChange()
    {

	return endChange;
    }


    public void setEndChange(LocalDateTime endChange)
    {

	this.endChange = endChange;
    }


    @Override
    public int compareTo(HistoricalDefaultValueAssetValue o)
    {

	return this.getNemonic().compareTo(o.getNemonic());
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(historicalExpressionParam, nodeId, value);
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
	HistoricalDefaultValueAssetValue other = (HistoricalDefaultValueAssetValue) obj;
	return Objects.equals(historicalExpressionParam, other.historicalExpressionParam) && Objects.equals(nodeId, other.nodeId) && Objects.equals(value, other.value);
    }

}
