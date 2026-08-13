package es.cic.tessa.model;


import java.io.Serializable;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;


@RelationshipProperties
public class TemplateReference implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @TargetNode
    private Template template;

    @Property(name = "relationType")
    private String templateRelationType;

    @Property(name = "referenceType")
    private String templateReferenceType;

    public Long getId()
    {

	return id;
    }


    public void setId(Long id)
    {

	this.id = id;
    }


    public Template getTemplate()
    {

	return template;
    }


    public void setTemplate(Template template)
    {

	this.template = template;
    }


    public String getTemplateRelationType()
    {

	return templateRelationType;
    }


    public void setTemplateRelationType(String templateRelationType)
    {

	this.templateRelationType = templateRelationType;
    }


    public String getTemplateReferenceType()
    {

	return templateReferenceType;
    }


    public void setTemplateReferenceType(String templateReferenceType)
    {

	this.templateReferenceType = templateReferenceType;
    }

}
