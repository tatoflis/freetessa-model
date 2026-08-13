package es.cic.tessa.lookup.model;


import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import es.cic.tessa.lookup.model.db.AssetLookupDB;
import es.cic.tessa.lookup.model.metrics.AssetLookupPrometheusMetrics;
import es.cic.tessa.lookup.model.rest.AssetLookupRest;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "class")
@JsonSubTypes(
{ @Type(value = AssetLookupPrometheusMetrics.class, name = "assetLookupPrometheusMetrics"), @Type(value = AssetLookupDB.class, name = "assetLookupDB"), @Type(value = AssetLookupRest.class, name = "assetLookupRest") })

public class AbstractLookupData implements Serializable
{

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    protected Long id;

    public Long getId()
    {

	return id;
    }


    public void setId(Long id)
    {

	this.id = id;
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
	AbstractLookupData other = (AbstractLookupData) obj;
	return Objects.equals(id, other.id);
    }

}
