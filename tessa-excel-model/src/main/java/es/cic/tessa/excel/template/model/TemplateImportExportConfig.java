package es.cic.tessa.excel.template.model;


import java.util.Locale;
import java.util.Set;
import es.cic.tessa.excel.common.ImportExportConfig;


public class TemplateImportExportConfig extends ImportExportConfig
{

    public TemplateImportExportConfig(Locale locale, Set<String> searchGroups)
    {

	this.setSearchGroups(searchGroups);
	this.setLocale(locale);

    }


    public TemplateImportExportConfig(Locale locale, Set<String> searchGroups, String subscriptionId)
    {

	this.setSearchGroups(searchGroups);
	this.setLocale(locale);
	this.setSubscriptionId(subscriptionId);

    }


    public TemplateImportExportConfig(Locale locale, Set<String> templateGroups, Set<String> searchGroups)
    {

	this.setGroups(templateGroups);
	this.setSearchGroups(searchGroups);
	this.setLocale(locale);

    }


    public TemplateImportExportConfig(Locale locale, Set<String> templateGroups, Set<String> searchGroups, String subscriptionId)
    {

	this.setGroups(templateGroups);
	this.setSearchGroups(searchGroups);
	this.setLocale(locale);
	this.setSubscriptionId(subscriptionId);

    }


    public TemplateImportExportConfig(Locale locale, Set<String> searchGroups, Boolean withData)
    {

	this.setSearchGroups(searchGroups);
	this.setLocale(locale);
	this.setWithData(withData);

    }


    public TemplateImportExportConfig(Locale locale, Set<String> searchGroups, Boolean withData, String subscriptionId)
    {

	this.setSearchGroups(searchGroups);
	this.setLocale(locale);
	this.setWithData(withData);
	this.setSubscriptionId(subscriptionId);

    }
}
