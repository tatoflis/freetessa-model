package es.cic.tessa.excel.common;


import java.util.Locale;
import java.util.Set;


public abstract class ImportExportConfig
{

    private Set<String> groups;
    private Set<String> searchGroups;
    private Locale locale;
    private Boolean withData;
    private String subscriptionId;

    public Set<String> getGroups()
    {

	return groups;
    }


    public void setGroups(Set<String> groups)
    {

	this.groups = groups;
    }


    public Set<String> getSearchGroups()
    {

	return searchGroups;
    }


    public void setSearchGroups(Set<String> searchGroups)
    {

	this.searchGroups = searchGroups;
    }


    public Locale getLocale()
    {

	return locale;
    }


    public void setLocale(Locale locale)
    {

	this.locale = locale;
    }


    public Boolean getWithData()
    {

	return withData;
    }


    public void setWithData(Boolean withData)
    {

	this.withData = withData;
    }


    public String getSubscriptionId()
    {

	return subscriptionId;
    }


    public void setSubscriptionId(String subscriptionId)
    {

	this.subscriptionId = subscriptionId;
    }
}
