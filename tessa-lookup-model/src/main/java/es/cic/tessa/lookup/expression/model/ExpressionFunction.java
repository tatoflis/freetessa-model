package es.cic.tessa.lookup.expression.model;


import java.time.Instant;
import java.util.Objects;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import es.cic.tessa.model.support.TemporalElement;
import tools.jackson.databind.annotation.JsonSerialize;


@JsonInclude(Include.NON_NULL)
@JsonSerialize
public class ExpressionFunction implements TemporalElement
{

    private static final long serialVersionUID = 1L;

    @JsonProperty("I")
    private Long id;

    @JsonProperty("C")
    private boolean complex;

    @JsonProperty("A")
    private Long assetId;

    @JsonProperty("SA")
    private Long synteticAssetId;

    @JsonProperty("EN")
    private String entity;

    @JsonProperty("AV")
    private Long assetValueId;

    @JsonProperty("E")
    private Expression expression;

    @JsonProperty("AP")
    private String adicionalProperties;

    @JsonProperty("M")
    private Instant moment;

    @JsonProperty("AM")
    private boolean actualMoment = Boolean.TRUE;

    @JsonProperty("G")
    private Set<String> groups;

    @JsonProperty("OE")
    private Boolean overriddenExpression;

    public ExpressionFunction()
    {

    }


    public ExpressionFunction(ExpressionFunction other)
    {

	this.id = other.id;
	this.complex = other.complex;
	this.assetId = other.assetId;
	this.synteticAssetId = other.synteticAssetId;
	this.entity = other.entity;
	this.assetValueId = other.assetValueId;
	this.expression = other.expression;
	this.adicionalProperties = other.adicionalProperties;
	this.moment = other.moment;
	this.actualMoment = other.actualMoment;
	this.groups = other.groups;
	this.overriddenExpression = other.overriddenExpression;
    }


    @Override
    public Long getId()
    {

	return id;
    }


    @Override
    public void setId(Long id)
    {

	this.id = id;

    }


    public Long getAssetValueId()
    {

	return assetValueId;
    }


    public void setAssetValueId(Long assetValueId)
    {

	this.assetValueId = assetValueId;
    }


    public Expression getExpression()
    {

	return expression;
    }


    public void setExpression(Expression expression)
    {

	this.expression = expression;
    }


    public Set<String> getGroups()
    {

	return groups;
    }


    public void setGroups(Set<String> groups)
    {

	this.groups = groups;
    }


    public Long getAssetId()
    {

	return assetId;
    }


    public void setAssetId(Long assetId)
    {

	this.assetId = assetId;
    }


    public Long getSynteticAssetId()
    {

	return synteticAssetId;
    }


    public void setSynteticAssetId(Long synteticAssetId)
    {

	this.synteticAssetId = synteticAssetId;
    }


    public String getEntity()
    {

	return entity;
    }


    public void setEntity(String entity)
    {

	this.entity = entity;
    }


    public Instant getTimestamp()
    {

	return moment;
    }


    public void setTimestamp(Instant moment)
    {

	this.moment = moment;
    }


    public boolean isComplex()
    {

	return complex;
    }


    public void setComplex(boolean complex)
    {

	this.complex = complex;
    }


    public boolean isActualMoment()
    {

	return actualMoment;
    }


    public void setActualMoment(boolean actualMoment)
    {

	this.actualMoment = actualMoment;
    }


    public Boolean isOverriddenExpression()
    {

	return overriddenExpression;
    }


    public void setOverriddenExpression(Boolean overriddenExpression)
    {

	this.overriddenExpression = overriddenExpression;
    }


    @Override
    public String toString()
    {

	return "ExpressionFunction [complex=" + complex + ", assetId=" + assetId + ", synteticAssetId=" + synteticAssetId + ", assetValueId=" + assetValueId + ", expression=" + expression + ", moment=" + moment + ", groups=" + groups + "]";
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(actualMoment, assetId, assetValueId, complex, expression, groups, moment, synteticAssetId);
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
	ExpressionFunction other = (ExpressionFunction) obj;
	return actualMoment == other.actualMoment && Objects.equals(assetId, other.assetId) && Objects.equals(assetValueId, other.assetValueId) && complex == other.complex && Objects.equals(expression, other.expression) && Objects.equals(groups, other.groups) && Objects.equals(moment, other.moment) && Objects.equals(synteticAssetId, other.synteticAssetId);
    }


    public String getAdicionalProperties()
    {

	return adicionalProperties;
    }


    public void setAdicionalProperties(String adicionalProperties)
    {

	this.adicionalProperties = adicionalProperties;
    }

}
