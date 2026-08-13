package es.cic.tessa.model;


import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;


@RelationshipProperties
public class AssetValueRelation
{

    @Id
    @GeneratedValue
    private Long id;

    @TargetNode
    private AssetValue assetValue;

    @Property(name = "valueComplex")
    private Boolean valueComplex;

    public Long getId()
    {

	return id;
    }


    public void setId(Long id)
    {

	this.id = id;
    }


    public AssetValueRelation()
    {

    }


    public AssetValueRelation(AssetValue assetValue)
    {

	this.assetValue = assetValue;
    }


    public AssetValueRelation(AssetValue assetValue, Boolean valueComplex)
    {

	this.assetValue = assetValue;
	this.valueComplex = valueComplex;

    }


    public AssetValue getAssetValue()
    {

	return assetValue;
    }


    public void setAssetValue(AssetValue assetValue)
    {

	this.assetValue = assetValue;
    }


    public Boolean getValueComplex()
    {

	return valueComplex;
    }


    public void setValueComplex(Boolean valueComplex)
    {

	this.valueComplex = valueComplex;
    }

}
