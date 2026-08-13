package es.cic.tessa.common.support;


import java.io.Serializable;


public class PageConfig implements Serializable
{

    private static final long serialVersionUID = 1L;

    private int numPage = 0;
    private int sizePage = 1;

    public PageConfig()
    {

    }


    public PageConfig(int numPage, int sizePage)
    {

	if(numPage < 0)
	{
	    throw new IllegalArgumentException("Page index must not be less than zero!");
	}

	if(sizePage < 1)
	{
	    throw new IllegalArgumentException("Page size must not be less than one!");
	}

	this.numPage = numPage;
	this.sizePage = sizePage;

    }


    public int getNumPage()
    {

	return numPage;
    }


    public void setNumPage(int numPage)
    {

	this.numPage = numPage;
    }


    public int getSizePage()
    {

	return sizePage;
    }


    public void setSizePage(int sizePage)
    {

	this.sizePage = sizePage;
    }


    @Override
    public String toString()
    {

	return String.format("Page config [number: %d, size %d]", getNumPage(), getSizePage());
    }

}
