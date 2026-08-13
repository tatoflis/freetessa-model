package es.cic.tessa.model;


import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;


@RelationshipProperties
public class AssetReference implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @TargetNode
    private Asset asset;

    @Property(name = "relationType")
    private String relationType;

    @Property(name = "referenceType")
    private String referenceType;

    public Long getId()
    {

	return id;
    }


    public void setId(Long id)
    {

	this.id = id;
    }


    public AssetReference()
    {

    }


    public AssetReference(Asset asset, String relationType, String referenceType)
    {

	this.asset = asset;
	this.relationType = relationType;
	this.referenceType = referenceType;
    }


    public Asset getAsset()
    {

	return asset;
    }


    public void setAsset(Asset asset)
    {

	this.asset = asset;
    }


    public void setRelationType(String relationType)
    {

	this.relationType = relationType;
    }


    public void setReferenceType(String referenceType)
    {

	this.referenceType = referenceType;
    }


    public String getRelationType()
    {

	return relationType;
    }


    public String getReferenceType()
    {

	return referenceType;
    }


    @Override
    public String toString()
    {

	return "AssetReference [asset=" + asset + ", relationType=" + relationType + ", referenceType=" + referenceType + "]";
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(asset, id, referenceType, relationType);
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
	AssetReference other = (AssetReference) obj;
	return Objects.equals(asset, other.asset) && Objects.equals(id, other.id) && Objects.equals(referenceType, other.referenceType) && Objects.equals(relationType, other.relationType);
    }

}
