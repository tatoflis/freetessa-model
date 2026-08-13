package es.cic.tessa.lookup.expression.model;


import java.util.Collection;
import java.util.LinkedHashSet;
import com.fasterxml.jackson.annotation.JsonProperty;
import tools.jackson.databind.annotation.JsonDeserialize;


public class ExpressionEvent extends ExpressionFunction
{

    private static final long serialVersionUID = 1L;

    @JsonProperty("AEC")
    @JsonDeserialize(as = LinkedHashSet.class)
    private Collection<Long> affectedExpressionIds;

    public Collection<Long> getAffectedExpressionIds()
    {

	return affectedExpressionIds;
    }


    public void setAffectedExpressionIds(Collection<Long> affectedExpressionIds)
    {

	this.affectedExpressionIds = affectedExpressionIds;
    }


    @Override
    public int hashCode()
    {

	return super.hashCode();
    }


    @Override
    public boolean equals(Object obj)
    {

	return super.equals(obj);
    }

}
