package es.cic.tessa.common.support;


import java.io.Serializable;
import java.util.Set;


public class CacheKey implements Serializable
{

    private static final long serialVersionUID = 1L;

    private String filter;
    private Set<String> groups;
    private Class<?> clazz;

    public CacheKey(String filter, Class<?> clazz)
    {

	this.filter = filter;
	this.clazz = clazz;
    }


    public CacheKey(String filter, Set<String> groups, Class<?> clazz)
    {

	this.filter = filter;
	this.groups = groups;
	this.clazz = clazz;
    }


    public String getFilter()
    {

	return filter;
    }


    public void setFilter(String filter)
    {

	this.filter = filter;
    }


    public Set<String> getGroups()
    {

	return groups;
    }


    public void setGroups(Set<String> groups)
    {

	this.groups = groups;
    }


    public Class<?> getClazz()
    {

	return clazz;
    }


    public void setClazz(Class<?> clazz)
    {

	this.clazz = clazz;
    }


    @Override
    public String toString()
    {

	return "CacheKey [filter=" + filter + ", groups=" + groups + "]";
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = 1;
	result = prime * result + ((groups == null) ? 0 : groups.hashCode());
	result = prime * result + ((filter == null) ? 0 : filter.hashCode());
	return result;
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
	CacheKey other = (CacheKey) obj;
	if(groups == null)
	{
	    if(other.groups != null)
		return false;
	}
	else if(!groups.equals(other.groups))
	    return false;
	if(filter == null)
	{
	    if(other.filter != null)
		return false;
	}
	else if(!filter.equals(other.filter))
	    return false;

	return true;
    }

}
