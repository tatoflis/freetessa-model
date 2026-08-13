package es.cic.tessa.model;


import java.time.LocalDateTime;
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


@Node(Labels.HISTORICAL_EXPRESSION_PARAM)
public class HistoricalExpressionParam extends TessaElement implements Comparable<HistoricalExpressionParam>
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long nodeId;

    @Property(name = "type")
    private String type;

    @Property(name = "required")
    private Boolean required;

    @Property(name = "position")
    protected Integer position = Integer.valueOf(0);

    @Relationship(type = Relations.EXPRESSION_RELATION, direction = Direction.INCOMING)
    private HistoricalTemplateAttribute historicalTemplateAttribute;

    @Relationship(type = Relations.DEFAULT_VALUE, direction = Direction.OUTGOING)
    private HistoricalDefaultValueAssetValue historicalDefaultValueAssetValue;

    @Relationship(type = Relations.HISTORICAL_CHANGE_RELATION, direction = Direction.INCOMING)
    private HistoricalExpressionParamChange historicalChange;

    @Property(name = "historicalChangeOperation")
    private String historicalChangeOperation;

    @Property(name = "startChange")
    private LocalDateTime startChange;

    @Property(name = "endChange")
    private LocalDateTime endChange;

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


    public String getType()
    {

	return type;
    }


    public void setType(String type)
    {

	this.type = type;
    }


    public Boolean getRequired()
    {

	return required;
    }


    public void setRequired(Boolean required)
    {

	this.required = required;
    }


    public Integer getPosition()
    {

	return position;
    }


    public void setPosition(Integer position)
    {

	this.position = position;
    }


    public HistoricalTemplateAttribute getHistoricalTemplateAttribute()
    {

	return historicalTemplateAttribute;
    }


    public void setHistoricalTemplateAttribute(HistoricalTemplateAttribute historicalTemplateAttribute)
    {

	this.historicalTemplateAttribute = historicalTemplateAttribute;
    }


    public HistoricalDefaultValueAssetValue getHistoricalDefaultValueAssetValue()
    {

	return historicalDefaultValueAssetValue;
    }


    public void setHistoricalDefaultValueAssetValue(HistoricalDefaultValueAssetValue historicalDefaultValueAssetValue)
    {

	this.historicalDefaultValueAssetValue = historicalDefaultValueAssetValue;
    }


    public HistoricalExpressionParamChange getHistoricalChange()
    {

	return historicalChange;
    }


    public void setHistoricalChange(HistoricalExpressionParamChange historicalChange)
    {

	this.historicalChange = historicalChange;
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
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(historicalDefaultValueAssetValue, historicalTemplateAttribute, nodeId, position, required, type, historicalChangeOperation);
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
	HistoricalExpressionParam other = (HistoricalExpressionParam) obj;
	return Objects.equals(historicalDefaultValueAssetValue, other.historicalDefaultValueAssetValue) && Objects.equals(historicalTemplateAttribute, other.historicalTemplateAttribute) && Objects.equals(nodeId, other.nodeId) && Objects.equals(position, other.position) && Objects.equals(required, other.required) && Objects.equals(type, other.type) && Objects.equals(historicalChangeOperation, other.historicalChangeOperation);
    }


    @Override
    public int compareTo(HistoricalExpressionParam o)
    {

	return this.getNemonic().compareTo(o.getNemonic());
    }
}
