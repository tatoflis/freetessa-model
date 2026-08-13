package es.cic.tessa.model.support;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.neo4j.core.schema.DynamicLabels;
import org.springframework.data.neo4j.core.schema.Property;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import es.cic.tessa.model.jsog.JSOGGenerator;
import es.cic.tessa.model.properties.CommonProperties;
import es.cic.tessa.model.utils.UniqueStringGenerator;


@JsonIdentityInfo(generator = JSOGGenerator.class, property = "@id")
@JsonInclude(Include.NON_NULL)
public abstract class IdentificableElement implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Property(name = CommonProperties.ID)
    protected Long customId;

    @Property(name = CommonProperties.NAME)
    protected String name;

    @Property(name = CommonProperties.NAME_LOWER)
    protected String nameLower;

    @Property(name = CommonProperties.DESCRIPTION)
    protected String description;

    @Property(name = CommonProperties.NEMONIC)
    protected String nemonic;

    @DynamicLabels
    @JsonIgnore
    protected Set<String> groups = new HashSet<>();

    public abstract Long getId();


    public IdentificableElement()
    {

	this.nemonic = buildNemonic();
    }


    public IdentificableElement(String name, Set<String> groups)
    {

	super();
	this.name = name;
	if(name != null)
	{
	    this.nameLower = name.toLowerCase();
	}
	this.groups.addAll(groups);
	this.nemonic = buildNemonic();
    }


    public Long getCustomId()
    {

	return customId;
    }


    public void setCustomId(Long customId)
    {

	this.customId = customId;
    }


    public String getName()
    {

	return name;
    }


    public void setName(String name)
    {

	this.name = name;
	if(name != null)
	{
	    this.nameLower = name.toLowerCase();
	}

    }


    public String getNameLower()
    {

	return nameLower;
    }


    public void setNameLower(String nameLower)
    {

	this.nameLower = nameLower;
    }


    public String getDescription()
    {

	return description;
    }


    public void setDescription(String description)
    {

	this.description = description;
    }


    public Set<String> getGroups()
    {

	return groups;
    }


    public void setGroups(Set<String> groups)
    {

	getGroups().addAll(groups);

    }


    public void removeGroup(String group)
    {

	groups.remove(group);
    }


    public String getNemonic()
    {

	return nemonic;
    }


    public void setNemonic(String nemonic)
    {

	this.nemonic = nemonic;
    }


    protected String buildNemonic()
    {

	return UniqueStringGenerator.generateUniqueString();
    }


    @Override
    public String toString()
    {

	return "AbstractElement [name=" + name + ", nemonic=" + nemonic + ", groups=" + groups + "]";
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(groups, nemonic);
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
	IdentificableElement other = (IdentificableElement) obj;
	return Objects.equals(groups, other.groups) && Objects.equals(nemonic, other.nemonic);
    }

}