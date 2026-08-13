package es.cic.tessa.model.support;


import java.util.Objects;
import java.util.Set;
import org.springframework.data.neo4j.core.schema.Property;
import es.cic.tessa.model.properties.CommonProperties;


public abstract class TessaElement extends DateTimeElement implements Element
{

    private static final long serialVersionUID = 1L;

    @Property(name = CommonProperties.ICON)
    protected String icon;

    public TessaElement()
    {

    }


    public TessaElement(String name, Set<String> groups)
    {

	super();
	setName(name);
	if(name != null)
	{
	    setNameLower(name.toLowerCase());
	}
	getGroups().addAll(groups);
	setNemonic(buildNemonic());
    }


    public String getIcon()
    {

	return icon;
    }


    public void setIcon(String icon)
    {

	this.icon = icon;
    }


    @Override
    public String toString()
    {

	return "TessaElement [id=" + getCustomId() + ", name=" + name + ", nemonic=" + nemonic + ", groups=" + groups + "]";
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
	TessaElement other = (TessaElement) obj;
	return Objects.equals(groups, other.groups) && Objects.equals(nemonic, other.nemonic);
    }

}