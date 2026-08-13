package es.cic.tessa.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;


@Node("GlobalCounter")
public class GlobalCounter
{

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "code")
    private String code;

    @Property(name = "lastId")
    private Long lastId;

    public Long getId()
    {

	return id;
    }


    public void setId(Long id)
    {

	this.id = id;
    }


    public String getCode()
    {

	return code;
    }


    public void setCode(String code)
    {

	this.code = code;
    }


    public Long getLastId()
    {

	return lastId;
    }


    public void setLastId(Long lastId)
    {

	this.lastId = lastId;
    }

}