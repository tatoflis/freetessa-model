package es.cic.tessa.excel.asset.model;


import java.util.Locale;
import java.util.Set;
import es.cic.tessa.common.support.PageConfig;
import es.cic.tessa.excel.common.ImportExportConfig;


public class AssetImportExportConfig extends ImportExportConfig
{

    private PageConfig pageConfig;
    private Boolean extended;
    private Boolean includeSynthetic;

    public AssetImportExportConfig(Set<String> searchGroups)
    {

	this.setSearchGroups(searchGroups);

    }


    public AssetImportExportConfig(Set<String> searchGroups, Locale locale, String subscriptionId)
    {

	this.setSearchGroups(searchGroups);
	this.setLocale(locale);
	this.setSubscriptionId(subscriptionId);

    }


    public AssetImportExportConfig(PageConfig pageConfig, Set<String> groups, Locale locale, Boolean withData, Boolean extended)
    {

	this.pageConfig = pageConfig;
	this.extended = extended;
	this.setGroups(groups);
	this.setLocale(locale);
	this.setWithData(withData);

    }


    public AssetImportExportConfig(PageConfig pageConfig, Set<String> groups, Locale locale, Boolean withData, Boolean extended, Boolean includeSynthetic)
    {

	this.pageConfig = pageConfig;
	this.extended = extended;
	this.includeSynthetic = includeSynthetic;
	this.setGroups(groups);
	this.setLocale(locale);
	this.setWithData(withData);

    }


    public AssetImportExportConfig(PageConfig pageConfig, Set<String> groups, Locale locale, Boolean withData, Boolean extended, String subscriptionId)
    {

	this.pageConfig = pageConfig;
	this.extended = extended;
	this.setGroups(groups);
	this.setLocale(locale);
	this.setWithData(withData);
	this.setSubscriptionId(subscriptionId);

    }


    public AssetImportExportConfig(PageConfig pageConfig, Set<String> groups, Locale locale, Boolean withData, Boolean extended, Boolean includeSynthetic, String subscriptionId)
    {

	this.pageConfig = pageConfig;
	this.extended = extended;
	this.includeSynthetic = includeSynthetic;
	this.setGroups(groups);
	this.setLocale(locale);
	this.setWithData(withData);
	this.setSubscriptionId(subscriptionId);

    }


    public AssetImportExportConfig(Set<String> searchGroups, Set<String> groups, Locale locale)
    {

	this.setSearchGroups(searchGroups);
	this.setGroups(groups);
	this.setLocale(locale);

    }


    public AssetImportExportConfig(Set<String> searchGroups, Set<String> groups, Locale locale, String subscriptionId)
    {

	this.setSearchGroups(searchGroups);
	this.setGroups(groups);
	this.setLocale(locale);
	this.setSubscriptionId(subscriptionId);

    }


    public PageConfig getPageConfig()
    {

	return pageConfig;
    }


    public void setPageConfig(PageConfig pageConfig)
    {

	this.pageConfig = pageConfig;
    }


    public Boolean getExtended()
    {

	return extended;
    }


    public void setExtended(Boolean extended)
    {

	this.extended = extended;
    }


    public Boolean getIncludeSynthetic()
    {

	return includeSynthetic;
    }


    public void setIncludeSynthetic(Boolean includeSynthetic)
    {

	this.includeSynthetic = includeSynthetic;
    }

}
