package es.cic.tessa.model.dto;


import java.util.Collection;


public class TemplateAttributeCollectionMappingCreateRequest extends TemplateAttributeCollectionMappingRequest
{

    private static final long serialVersionUID = 1L;

    private Collection<String> groups;

    public Collection<String> getGroups()
    {

	return groups;
    }


    public void setGroups(Collection<String> groups)
    {

	this.groups = groups;
    }
}
