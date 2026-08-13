package es.cic.tessa.model;


import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;


@RelationshipProperties
public class HistoricalAssetReference implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @TargetNode
    private HistoricalAsset historicalAsset;

    @Property(name = "relationType")
    private String relationType;

    @Property(name = "referenceType")
    private String referenceType;

    public HistoricalAssetReference()
    {

    }


    public HistoricalAssetReference(HistoricalAsset historicalAsset, String relationType, String referenceType)
    {

	this.historicalAsset = historicalAsset;
	this.relationType = relationType;
	this.referenceType = referenceType;
    }


    public Long getId()
    {

	return id;
    }


    public void setId(Long id)
    {

	this.id = id;
    }


    public HistoricalAsset getHistoricalAsset()
    {

	return historicalAsset;
    }


    public void setHistoricalAsset(HistoricalAsset historicalAsset)
    {

	this.historicalAsset = historicalAsset;
    }


    public String getRelationType()
    {

	return relationType;
    }


    public void setRelationType(String relationType)
    {

	this.relationType = relationType;
    }


    public String getReferenceType()
    {

	return referenceType;
    }


    public void setReferenceType(String referenceType)
    {

	this.referenceType = referenceType;
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(historicalAsset, id, referenceType, relationType);
    }


    @Override
    public boolean equals(Object obj)
    {

	if(this == obj)
	    return true;
	if(obj == null)
	    return false;
	if(getClass() != obj.getClass())
	    return false;
	HistoricalAssetReference other = (HistoricalAssetReference) obj;
	return Objects.equals(historicalAsset, other.historicalAsset) && Objects.equals(id, other.id) && Objects.equals(referenceType, other.referenceType) && Objects.equals(relationType, other.relationType);
    }


    @Override
    public String toString()
    {

	return "HistoricalAssetReference [id=" + id + ", historicalAsset=" + historicalAsset + ", relationType=" + relationType + ", referenceType=" + referenceType + "]";
    }

}
