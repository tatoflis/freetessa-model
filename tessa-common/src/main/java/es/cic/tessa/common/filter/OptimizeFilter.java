package es.cic.tessa.common.filter;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;


public abstract class OptimizeFilter
{

    protected Long id;
    protected Collection<Long> ids = new HashSet<>();
    protected String name;
    private List<OptimizePropertyFilter> propetyFilters = new ArrayList<>();

    public Long getId()
    {

	return id;
    }


    public void setId(Long id)
    {

	this.id = id;
    }


    public void setIds(Collection<Long> ids)
    {

	this.ids = ids;
    }


    public Collection<Long> getIds()
    {

	return ids;
    }


    public String getName()
    {

	return name;
    }


    public void setName(String name)
    {

	this.name = name;
    }


    public List<OptimizePropertyFilter> getPropetyFilters()
    {

	return propetyFilters;
    }


    public void setPropetyFilters(List<OptimizePropertyFilter> propeties)
    {

	this.propetyFilters = propeties;
    }


    public void addPropertyFilter(OptimizePropertyFilter propertyFilter)
    {

	getPropetyFilters().add(propertyFilter);
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(id, name, propetyFilters);
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
	OptimizeFilter other = (OptimizeFilter) obj;
	return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(propetyFilters, other.propetyFilters);
    }

}
