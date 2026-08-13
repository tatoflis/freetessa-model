package es.cic.tessa.model;


import java.util.Objects;
import java.util.Set;
import org.springframework.data.annotation.Transient;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import es.cic.tessa.common.model.Labels;
import es.cic.tessa.common.model.Relations;
import es.cic.tessa.model.support.TessaElement;
import es.cic.tessa.model.types.OrganizerType;


@Node(Labels.HISTORICAL_ORGANIZER)
public class HistoricalOrganizer extends TessaElement implements Comparable<HistoricalOrganizer>
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long nodeId;

    @Property(name = "type")
    private String organizerType = OrganizerType.ASSET_ORGANIZER.getCode();

    @Property(name = "path")
    private String path;

    @Relationship(type = Relations.METADATA_RELATION, direction = Direction.OUTGOING)
    private HistoricalAsset historicalMetadata;

    @Relationship(type = Relations.PARENT_RELATION, direction = Direction.OUTGOING)
    private HistoricalOrganizer historicalParentOrganizer;

    @Relationship(type = Relations.HISTORICAL_CHANGE_RELATION, direction = Direction.INCOMING)
    private HistoricalOrganizerChange historicalChange;

    @Property(name = "historicalChangeOperation")
    private String historicalChangeOperation;

    @Transient
    private Integer numElements;

    @Transient
    private Integer numOrganizers;

    public HistoricalOrganizer()
    {

    }


    public HistoricalOrganizer(String name, Set<String> groups)
    {

	super(name, groups);
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


    public String getOrganizerType()
    {

	return organizerType;
    }


    public void setOrganizerType(String organizerType)
    {

	this.organizerType = organizerType;
    }


    public String getPath()
    {

	return path;
    }


    public void setPath(String path)
    {

	this.path = path;
    }


    public Integer getNumElements()
    {

	return numElements;
    }


    public void setNumElements(Integer numElements)
    {

	this.numElements = numElements;
    }


    public Integer getNumOrganizers()
    {

	return numOrganizers;
    }


    public void setNumOrganizers(Integer numOrganizers)
    {

	this.numOrganizers = numOrganizers;
    }


    public HistoricalOrganizer getHistoricalParentOrganizer()
    {

	return historicalParentOrganizer;
    }


    public void setHistoricalParentOrganizer(HistoricalOrganizer historicalParentOrganizer)
    {

	this.historicalParentOrganizer = historicalParentOrganizer;
    }


    @Override
    public int compareTo(HistoricalOrganizer o)
    {

	return this.getNemonic().compareTo(o.getNemonic());

    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(nodeId, path, historicalChangeOperation);
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
	HistoricalOrganizer other = (HistoricalOrganizer) obj;
	return Objects.equals(nodeId, other.nodeId) && Objects.equals(path, other.path) && Objects.equals(historicalChangeOperation, other.historicalChangeOperation);
    }


    public HistoricalAsset getHistoricalMetadata()
    {

	return historicalMetadata;
    }


    public void setHistoricalMetadata(HistoricalAsset historicalMetadata)
    {

	this.historicalMetadata = historicalMetadata;
    }


    public HistoricalOrganizerChange getHistoricalChange()
    {

	return historicalChange;
    }


    public void setHistoricalChange(HistoricalOrganizerChange historicalChange)
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

}
