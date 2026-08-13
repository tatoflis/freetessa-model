package es.cic.tessa.model;


import java.io.Serializable;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;


@RelationshipProperties
public class HistoricalTemplateReference implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @TargetNode
    private HistoricalTemplate historicalTemplate;

    @Property(name = "relationType")
    private String relationType;

    @Property(name = "referenceType")
    private String referenceType;

    public Long getId()
    {

	return id;
    }


    public HistoricalTemplateReference()
    {

	super();
    }


    public HistoricalTemplateReference(HistoricalTemplate historicalTemplate, String relationType, String referenceType)
    {

	super();
	this.historicalTemplate = historicalTemplate;
	this.relationType = relationType;
	this.referenceType = referenceType;
    }


    public void setId(Long id)
    {

	this.id = id;
    }


    public HistoricalTemplate getHistoricalTemplate()
    {

	return historicalTemplate;
    }


    public void setHistoricalTemplate(HistoricalTemplate historicalTemplate)
    {

	this.historicalTemplate = historicalTemplate;
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
}
