package es.cic.tessa.model.historical.dto;


import java.util.Objects;
import es.cic.tessa.model.dto.support.AbstractEntityResponse;


public class HistoricalHashtagResponse extends AbstractEntityResponse
{

    private static final long serialVersionUID = 1L;

    private Long nodeId;

    private String hashtag;

    private String historicalChangeOperation;

    public Long getNodeId()
    {

	return nodeId;
    }


    public void setNodeId(Long nodeId)
    {

	this.nodeId = nodeId;
    }


    public String getHashtag()
    {

	return hashtag;
    }


    public void setHashtag(String hashtag)
    {

	this.hashtag = hashtag;
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
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(hashtag, historicalChangeOperation, nodeId);
	return result;
    }


    @Override
    public boolean equals(Object obj)
    {

	if(this == obj)
	    return true;
	if(!super.equals(obj))
	    return false;
	if(getClass() != obj.getClass())
	    return false;
	HistoricalHashtagResponse other = (HistoricalHashtagResponse) obj;
	return Objects.equals(hashtag, other.hashtag) && Objects.equals(historicalChangeOperation, other.historicalChangeOperation) && Objects.equals(nodeId, other.nodeId);
    }


    @Override
    public String toString()
    {

	return "HistoricalHashtagResponse [nodeId=" + nodeId + ", hashtag=" + hashtag + ", historicalChangeOperation=" + historicalChangeOperation + "]";
    }

}
