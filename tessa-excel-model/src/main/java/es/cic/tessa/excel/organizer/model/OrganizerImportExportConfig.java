package es.cic.tessa.excel.organizer.model;


import java.util.Locale;
import java.util.Set;
import es.cic.tessa.excel.common.ImportExportConfig;
import es.cic.tessa.model.types.OrganizerType;


public class OrganizerImportExportConfig extends ImportExportConfig
{

    private OrganizerType organizerType;

    public OrganizerImportExportConfig(Locale locale, Set<String> organizerGroups)
    {

	this.setGroups(organizerGroups);
	this.setLocale(locale);

    }


    public OrganizerImportExportConfig(Locale locale, Set<String> organizerGroups, String subscriptionId)
    {

	this.setGroups(organizerGroups);
	this.setLocale(locale);
	this.setSubscriptionId(subscriptionId);

    }


    public OrganizerImportExportConfig(OrganizerType organizerType, Locale locale, Set<String> searchGroups, Boolean withData)
    {

	this.organizerType = organizerType;
	this.setSearchGroups(searchGroups);
	this.setLocale(locale);
	this.setWithData(withData);

    }


    public OrganizerImportExportConfig(OrganizerType organizerType, Locale locale, Set<String> searchGroups, Boolean withData, String subscriptionId)
    {

	this.organizerType = organizerType;
	this.setSearchGroups(searchGroups);
	this.setLocale(locale);
	this.setWithData(withData);
	this.setSubscriptionId(subscriptionId);

    }


    public OrganizerType getOrganizerType()
    {

	return organizerType;
    }


    public void setOrganizerType(OrganizerType organizerType)
    {

	this.organizerType = organizerType;
    }

}
