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


@Node(Labels.HISTORICAL_HASHTAG)
public class HistoricalHashtag extends TessaElement implements Comparable<HistoricalHashtag>
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long nodeId;

    @Property(name = "value")
    private String value;

    @Property(name = "historicalChangeOperation")
    private String historicalChangeOperation;

    @Property(name = "startChange")
    private LocalDateTime startChange;

    @Property(name = "endChange")
    private LocalDateTime endChange;

    @Relationship(type = Relations.CLASSIFIES_RELATION, direction = Direction.INCOMING)
    private HistoricalTemplateAttribute historicalTemplateAttribute;

    @Relationship(type = Relations.HISTORICAL_CHANGE_RELATION, direction = Direction.INCOMING)
    private HistoricalHashtagChange historicalChange;

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


    public String getHistoricalChangeOperation()
    {

	return historicalChangeOperation;
    }


    public void setHistoricalChangeOperation(String historicalChangeOperation)
    {

	this.historicalChangeOperation = historicalChangeOperation;
    }


    public HistoricalTemplateAttribute getHistoricalTemplateAttribute()
    {

	return historicalTemplateAttribute;
    }


    public void setHistoricalTemplateAttribute(HistoricalTemplateAttribute historicalTemplateAttribute)
    {

	this.historicalTemplateAttribute = historicalTemplateAttribute;
    }


    public HistoricalHashtagChange getHistoricalChange()
    {

	return historicalChange;
    }


    public void setHistoricalChange(HistoricalHashtagChange historicalChange)
    {

	this.historicalChange = historicalChange;
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
    public int compareTo(HistoricalHashtag o)
    {

	return this.getNemonic().compareTo(o.getNemonic());
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(historicalTemplateAttribute, nodeId, value, historicalChangeOperation);
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
	HistoricalHashtag other = (HistoricalHashtag) obj;
	return Objects.equals(historicalTemplateAttribute, other.historicalTemplateAttribute) && Objects.equals(nodeId, other.nodeId) && Objects.equals(value, other.value) && Objects.equals(historicalChangeOperation, other.historicalChangeOperation);
    }

}
