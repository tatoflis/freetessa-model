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


@Node(Labels.HISTORICAL_TEMPLATE_ATTRIBUTE_COLLECTION_MAPPING)
public class HistoricalTemplateAttributeCollectionMapping extends TessaElement implements Comparable<HistoricalTemplateAttributeCollectionMapping>
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long nodeId;

    @Property(name = "hasCalculatedValue")
    private Boolean hasCalculatedValue = Boolean.FALSE;

    @Property(name = "calculatedValue")
    private String calculatedValue;

    @Relationship(type = Relations.MAPPING_RELATION, direction = Direction.INCOMING)
    private HistoricalTemplateAttribute historicalTemplateAttribute;

    @Property(name = "position")
    protected Integer position = Integer.valueOf(0);

    @Property(name = "historicalChangeOperation")
    private String historicalChangeOperation;

    @Relationship(type = Relations.HISTORICAL_CHANGE_RELATION, direction = Direction.INCOMING)
    private HistoricalTemplateAttributeCollectionMappingChange historicalChange;

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


    public Boolean getHasCalculatedValue()
    {

	return hasCalculatedValue;
    }


    public void setHasCalculatedValue(Boolean hasCalculatedValue)
    {

	this.hasCalculatedValue = hasCalculatedValue;
    }


    public String getCalculatedValue()
    {

	return calculatedValue;
    }


    public void setCalculatedValue(String calculatedValue)
    {

	this.calculatedValue = calculatedValue;
    }


    public HistoricalTemplateAttribute getHistoricalTemplateAttribute()
    {

	return historicalTemplateAttribute;
    }


    public void setHistoricalTemplateAttribute(HistoricalTemplateAttribute historicalTemplateAttribute)
    {

	this.historicalTemplateAttribute = historicalTemplateAttribute;
    }


    public Integer getPosition()
    {

	return position;
    }


    public void setPosition(Integer position)
    {

	this.position = position;
    }


    public String getHistoricalChangeOperation()
    {

	return historicalChangeOperation;
    }


    public void setHistoricalChangeOperation(String historicalChangeOperation)
    {

	this.historicalChangeOperation = historicalChangeOperation;
    }


    public HistoricalTemplateAttributeCollectionMappingChange getHistoricalChange()
    {

	return historicalChange;
    }


    public void setHistoricalChange(HistoricalTemplateAttributeCollectionMappingChange historicalChange)
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
    public int compareTo(HistoricalTemplateAttributeCollectionMapping htacm)
    {

	return getName().compareTo(htacm.getName());
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(calculatedValue, hasCalculatedValue, historicalTemplateAttribute, nodeId, position, historicalChangeOperation);
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
	HistoricalTemplateAttributeCollectionMapping other = (HistoricalTemplateAttributeCollectionMapping) obj;
	return Objects.equals(calculatedValue, other.calculatedValue) && Objects.equals(hasCalculatedValue, other.hasCalculatedValue) && Objects.equals(historicalTemplateAttribute, other.historicalTemplateAttribute) && Objects.equals(nodeId, other.nodeId) && Objects.equals(position, other.position) && Objects.equals(historicalChangeOperation, other.historicalChangeOperation);
    }

}
