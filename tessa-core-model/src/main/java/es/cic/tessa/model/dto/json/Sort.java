package es.cic.tessa.model.dto.json;


import com.fasterxml.jackson.annotation.JsonProperty;


public class Sort
{

    @JsonProperty("sorted")
    private boolean sorted;

    @JsonProperty("unsorted")
    private boolean unsorted;

    @JsonProperty("empty")
    private boolean empty;

    public boolean isSorted()
    {

	return sorted;
    }


    public void setSorted(boolean sorted)
    {

	this.sorted = sorted;
    }


    public boolean isUnsorted()
    {

	return unsorted;
    }


    public void setUnsorted(boolean unsorted)
    {

	this.unsorted = unsorted;
    }


    public boolean isEmpty()
    {

	return empty;
    }


    public void setEmpty(boolean empty)
    {

	this.empty = empty;
    }

}
