package es.cic.tessa.model.dto.json;


import com.fasterxml.jackson.annotation.JsonProperty;


public class PageableJson
{

    @JsonProperty("sort")
    private Sort sort;

    @JsonProperty("offset")
    private int offset;

    @JsonProperty("pageSize")
    private int pageSize;

    @JsonProperty("pageNumber")
    private int pageNumber;

    @JsonProperty("paged")
    private boolean paged;

    @JsonProperty("unpaged")
    private boolean unpaged;

    public Sort getSort()
    {

	return sort;
    }


    public void setSort(Sort sort)
    {

	this.sort = sort;
    }


    public int getOffset()
    {

	return offset;
    }


    public void setOffset(int offset)
    {

	this.offset = offset;
    }


    public int getPageSize()
    {

	return pageSize;
    }


    public void setPageSize(int pageSize)
    {

	this.pageSize = pageSize;
    }


    public int getPageNumber()
    {

	return pageNumber;
    }


    public void setPageNumber(int pageNumber)
    {

	this.pageNumber = pageNumber;
    }


    public boolean isPaged()
    {

	return paged;
    }


    public void setPaged(boolean paged)
    {

	this.paged = paged;
    }


    public boolean isUnpaged()
    {

	return unpaged;
    }


    public void setUnpaged(boolean unpaged)
    {

	this.unpaged = unpaged;
    }

}
