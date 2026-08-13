package es.cic.tessa.model;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;
import com.fasterxml.jackson.annotation.JsonFormat;


@RelationshipProperties
public class HistoricalExpressionParamChange implements Comparable<HistoricalExpressionParamChange>, Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @TargetNode
    private ExpressionParam expressionParam;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Property(name = "startChange")
    private LocalDateTime startChange;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Property(name = "endChange")
    private LocalDateTime endChange;

    @Property(name = "historicalChangeOperation")
    private String historicalChangeOperation;

    public HistoricalExpressionParamChange(ExpressionParam expressionParam, LocalDateTime startChange, LocalDateTime endChange, String historicalChangeOperation)
    {

	this.expressionParam = expressionParam;
	this.startChange = startChange;
	this.endChange = endChange;

	this.historicalChangeOperation = historicalChangeOperation;
    }


    public Long getId()
    {

	return id;
    }


    public void setId(Long id)
    {

	this.id = id;
    }


    public ExpressionParam getExpressionParam()
    {

	return expressionParam;
    }


    public void setExpressionParam(ExpressionParam expressionParam)
    {

	this.expressionParam = expressionParam;
    }


    public LocalDateTime getStartChange()
    {

	return startChange;
    }


    public void setStartChange(LocalDateTime startChange)
    {

	this.startChange = startChange;
    }


    public LocalDateTime getEndChange()
    {

	return endChange;
    }


    public void setEndChange(LocalDateTime endChange)
    {

	this.endChange = endChange;
    }


    public String getHistoricalChangeOperation()
    {

	return historicalChangeOperation;
    }


    public void setHistoricalChangeOperation(String historicalChangeOperation)
    {

	this.historicalChangeOperation = historicalChangeOperation;
    }


    @Override
    public int compareTo(HistoricalExpressionParamChange historicalChange)
    {

	if(this.getStartChange().isEqual(historicalChange.getStartChange()))
	    return 0;
	else if(this.getStartChange().isAfter(historicalChange.getStartChange()))
	    return 1;
	else
	    return -1;
    }


    @Override
    public String toString()
    {

	return "HistoricalExpressionParamChange [abstractElement=" + expressionParam + ", startChange=" + startChange + ", endChange=" + endChange + "]";
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(endChange, historicalChangeOperation, id, startChange);
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
	HistoricalExpressionParamChange other = (HistoricalExpressionParamChange) obj;
	return Objects.equals(endChange, other.endChange) && Objects.equals(historicalChangeOperation, other.historicalChangeOperation) && Objects.equals(id, other.id) && Objects.equals(startChange, other.startChange);
    }

}
