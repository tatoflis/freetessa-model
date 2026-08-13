package es.cic.tessa.common.filter;


import es.cic.tessa.common.model.types.ArithmeticOperatorType;
import es.cic.tessa.common.model.types.LogicalOperatorType;


public class RelationshipFilter
{

    private String referenceType;
    private String relationType;
    private LogicalOperatorType logicalOperatorType = LogicalOperatorType.AND;
    private ArithmeticOperatorType aritmeticalOperatorType = ArithmeticOperatorType.EQUALS;

    public RelationshipFilter()
    {

    }


    public RelationshipFilter(String relationType)
    {

	this.relationType = relationType;
    }


    public RelationshipFilter(String relationType, LogicalOperatorType logicalOperatorType)
    {

	this.relationType = relationType;
	this.logicalOperatorType = logicalOperatorType;
    }


    public RelationshipFilter(String relationType, ArithmeticOperatorType aritmeticalOperatorType)
    {

	this.relationType = relationType;
	this.aritmeticalOperatorType = aritmeticalOperatorType;
    }


    public String getReferenceType()
    {

	return referenceType;
    }


    public void setReferenceType(String referenceType)
    {

	this.referenceType = referenceType;
    }


    public String getRelationType()
    {

	return relationType;
    }


    public void setRelationType(String relationType)
    {

	this.relationType = relationType;
    }


    public LogicalOperatorType getLogicalOperatorType()
    {

	return logicalOperatorType;
    }


    public void setLogicalOperatorType(LogicalOperatorType logicalOperatorType)
    {

	this.logicalOperatorType = logicalOperatorType;
    }


    public ArithmeticOperatorType getAritmeticalOperatorType()
    {

	return aritmeticalOperatorType;
    }


    public void setAritmeticalOperatorType(ArithmeticOperatorType aritmeticalOperatorType)
    {

	this.aritmeticalOperatorType = aritmeticalOperatorType;
    }
}
