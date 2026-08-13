package es.cic.tessa.model.optimize;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import es.cic.tessa.model.support.Element;


public abstract class TessaElementOptimize implements Element, Serializable
{

    private static final long serialVersionUID = 1L;

    protected Long id;
    protected String name;
    protected Set<String> groups = new HashSet<String>();

    public TessaElementOptimize()
    {

    }


    public TessaElementOptimize(String name, Set<String> groups)
    {

	super();
	setName(name);
	getGroups().addAll(groups);

    }


    public Long getId()
    {

	return id;
    }


    public void setId(Long id)
    {

	this.id = id;
    }


    public String getName()
    {

	return name;
    }


    public void setName(String name)
    {

	this.name = name;
    }


    public Set<String> getGroups()
    {

	return groups;
    }


    public void setGroups(Set<String> groups)
    {

	this.groups = groups;
    }


    public String toString()
    {

	return "[name=" + name + ", id=" + getId() + "]";
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(id);
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
	TessaElementOptimize other = (TessaElementOptimize) obj;
	return Objects.equals(id, other.id);
    }

}
