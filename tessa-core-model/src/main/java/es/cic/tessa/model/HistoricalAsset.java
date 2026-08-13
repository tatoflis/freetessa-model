package es.cic.tessa.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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


@Node(Labels.HISTORICAL_ASSET)
public class HistoricalAsset extends TessaElement implements Comparable<HistoricalAsset>
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    protected Long nodeId;

    @Property(name = "identificator")
    protected String identificator;

    @Property(name = "active")
    protected Boolean active = Boolean.TRUE;

    @Property(name = "physicalPath")
    protected String physicalPath;

    @Property(name = "logicalPath")
    protected List<String> logicalPath;

    @Relationship(type = Relations.DEFINED_BY_RELATION, direction = Direction.OUTGOING)
    private HistoricalTemplate historicalTemplate;

    @Relationship(type = Relations.DEPENDS_RELATION, direction = Direction.OUTGOING)
    private HistoricalAsset historicalDependsAsset;

    @Relationship(type = Relations.HAS_VALUE_RELATION, direction = Direction.OUTGOING)
    private List<HistoricalAssetValue> historicalValues = new ArrayList<>();

    @Relationship(type = Relations.CONTAINED_RELATION, direction = Direction.OUTGOING)
    private List<HistoricalOrganizer> historicalOrganizers = new ArrayList<>();

    @Relationship(type = Relations.HISTORICAL_CHANGE_RELATION, direction = Direction.INCOMING)
    private HistoricalAssetChange historicalChange;

    @Property(name = "historicalChangeOperation")
    private String historicalChangeOperation;

    @Property(name = "startChange")
    private LocalDateTime startChange;

    @Property(name = "endChange")
    private LocalDateTime endChange;

    @Transient
    private Integer numComplexAssets = Integer.valueOf(0);

    public HistoricalAsset()
    {

    }


    public HistoricalAsset(String name, String identificator, HistoricalTemplate historicalTemplate, Set<String> groups)
    {

	super(name, groups);
	this.identificator = identificator;
	nameLower = identificator.toLowerCase();
	this.historicalTemplate = historicalTemplate;
    }


    public HistoricalAsset(String name, String identificator, HistoricalTemplate historicalTemplate, HistoricalAssetChange historicalChange, Set<String> groups)
    {

	super(name, groups);
	this.identificator = identificator;
	nameLower = identificator.toLowerCase();
	this.historicalTemplate = historicalTemplate;
	this.historicalChange = historicalChange;
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


    public HistoricalTemplate getHistoricalTemplate()
    {

	return historicalTemplate;
    }


    public void setHistoricalTemplate(HistoricalTemplate historicalTemplate)
    {

	this.historicalTemplate = historicalTemplate;
    }


    public List<HistoricalOrganizer> getHistoricalOrganizers()
    {

	return historicalOrganizers;
    }


    public void setHistoricalOrganizers(List<HistoricalOrganizer> historicalOrganizers)
    {

	this.historicalOrganizers = historicalOrganizers;
    }


    public HistoricalAsset getHistoricalDependsAsset()
    {

	return historicalDependsAsset;
    }


    public void setHistoricalDependsAsset(HistoricalAsset historicalDependsAsset)
    {

	this.historicalDependsAsset = historicalDependsAsset;
    }


    public HistoricalAssetChange getHistoricalChange()
    {

	return historicalChange;
    }


    public void setHistoricalChange(HistoricalAssetChange historicalChange)
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


    public Integer getNumComplexAssets()
    {

	return numComplexAssets;
    }


    public void setNumComplexAssets(Integer numComplexAssets)
    {

	this.numComplexAssets = numComplexAssets;
    }


    public void addValue(HistoricalAssetValue value)
    {

	historicalValues.add(value);
    }


    public void addValues(List<HistoricalAssetValue> assetAttributeValues)
    {

	historicalValues.addAll(assetAttributeValues);
    }


    public List<HistoricalAssetValue> getHistoricalValues()
    {

	return historicalValues;
    }


    public void setHistoricalValues(List<HistoricalAssetValue> values)
    {

	this.historicalValues = values;
    }


    public String getIdentificator()
    {

	return identificator;
    }


    public void setIdentificator(String identificator)
    {

	this.identificator = identificator;
    }


    public String getPhysicalPath()
    {

	return physicalPath;
    }


    public void setPhysicalPath(String physicalPath)
    {

	this.physicalPath = physicalPath;
    }


    public List<String> getLogicalPath()
    {

	return logicalPath;
    }


    public void setLogicalPath(List<String> logicalPath)
    {

	this.logicalPath = logicalPath;
    }


    public Boolean getActive()
    {

	return active;
    }


    public void setActive(Boolean active)
    {

	this.active = active;
    }


    @Override
    public int compareTo(HistoricalAsset o)
    {

	return this.getNemonic().compareTo(o.getNemonic());

    }


    @Override
    // historicalValues excluido: HistoricalAssetValue.hashCode()/equals() incluye a su vez
    // historicalAsset (relacion inversa HAS_VALUE), lo que produce recursion infinita
    // (StackOverflowError) en cuanto ambos lados estan poblados -- mismo patron que el
    // corregido en HistoricalTemplate/HistoricalTemplateAttribute (ver ese comentario).
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(active, historicalDependsAsset, historicalOrganizers, historicalTemplate, nodeId, identificator, numComplexAssets, logicalPath, physicalPath, historicalChangeOperation);
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
	HistoricalAsset other = (HistoricalAsset) obj;
	return Objects.equals(active, other.active) && Objects.equals(historicalDependsAsset, other.historicalDependsAsset) && Objects.equals(historicalOrganizers, other.historicalOrganizers) && Objects.equals(historicalTemplate, other.historicalTemplate) && Objects.equals(nodeId, other.nodeId) && Objects.equals(identificator, other.identificator) && Objects.equals(numComplexAssets, other.numComplexAssets) && Objects.equals(logicalPath, other.logicalPath) && Objects.equals(physicalPath, other.physicalPath) && Objects.equals(historicalChangeOperation, other.historicalChangeOperation);
    }


    @Override
    public String toString()
    {

	return "HistoricalAsset [id=" + nodeId + ", identificator=" + identificator + ", active=" + active + ", logicalPath=" + logicalPath + ", physicalPath=" + physicalPath + ", historicalTemplate=" + historicalTemplate + ", historicalDependsAsset=" + historicalDependsAsset + ", values=" + historicalValues + ", historicalOrganizers=" + historicalOrganizers + ", numComplexAssets=" + numComplexAssets + ", historicalChangeOperation=" + historicalChangeOperation + "]";
    }

}
