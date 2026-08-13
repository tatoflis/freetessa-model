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
public class HistoricalHashtagChange implements Comparable<HistoricalHashtagChange>, Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @TargetNode
    private Hashtag hashtag;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Property(name = "startChange")
    private LocalDateTime startChange;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Property(name = "endChange")
    private LocalDateTime endChange;

    @Property(name = "historicalChangeOperation")
    private String historicalChangeOperation;

    public HistoricalHashtagChange(Hashtag hashtag, LocalDateTime startChange, LocalDateTime endChange, String historicalChangeOperation)
    {

	this.hashtag = hashtag;
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


    public Hashtag getHashtag()
    {

	return hashtag;
    }


    public void setHashtag(Hashtag hashtag)
    {

	this.hashtag = hashtag;
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
    public int compareTo(HistoricalHashtagChange historicalChange)
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

	return "HistoricalHashtagChange [hashtag=" + hashtag + ", startChange=" + startChange + ", endChange=" + endChange + "]";
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
	HistoricalHashtagChange other = (HistoricalHashtagChange) obj;
	return Objects.equals(endChange, other.endChange) && Objects.equals(historicalChangeOperation, other.historicalChangeOperation) && Objects.equals(id, other.id) && Objects.equals(startChange, other.startChange);
    }

}
