package es.cic.tessa.model.dto.json;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ResponsePageJson<T>
{

    @JsonProperty("last")
    private boolean last;

    @JsonProperty("content")
    private List<T> content;

    @JsonProperty("pageable")
    private PageableJson pageable;

    @JsonProperty("totalElements")
    private long totalElements;

    @JsonProperty("totalPages")
    private long totalPages;

    @JsonProperty("size")
    private long size;

    @JsonProperty("number")
    private long number;

    @JsonProperty("sort")
    private Sort sort;

    @JsonProperty("first")
    private boolean first;

    @JsonProperty("numberOfElements")
    private long numberOfElements;

    @JsonProperty("empty")
    private boolean empty;

    public boolean isLast()
    {

	return last;
    }


    public void setLast(boolean last)
    {

	this.last = last;
    }


    public List<T> getContent()
    {

	return content;
    }


    public void setContent(List<T> content)
    {

	this.content = content;
    }


    public PageableJson getPageable()
    {

	return pageable;
    }


    public void setPageable(PageableJson pageable)
    {

	this.pageable = pageable;
    }


    public long getTotalElements()
    {

	return totalElements;
    }


    public void setTotalElements(long totalElements)
    {

	this.totalElements = totalElements;
    }


    public long getTotalPages()
    {

	return totalPages;
    }


    public void setTotalPages(long totalPages)
    {

	this.totalPages = totalPages;
    }


    public long getSize()
    {

	return size;
    }


    public void setSize(long size)
    {

	this.size = size;
    }


    public long getNumber()
    {

	return number;
    }


    public void setNumber(long number)
    {

	this.number = number;
    }


    public Sort getSort()
    {

	return sort;
    }


    public void setSort(Sort sort)
    {

	this.sort = sort;
    }


    public boolean isFirst()
    {

	return first;
    }


    public void setFirst(boolean first)
    {

	this.first = first;
    }


    public long getNumberOfElements()
    {

	return numberOfElements;
    }


    public void setNumberOfElements(long numberOfElements)
    {

	this.numberOfElements = numberOfElements;
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
