package es.cic.tessa.model.filter;


import java.util.ArrayList;
import java.util.List;
import es.cic.tessa.common.filter.PropertyFilter;
import es.cic.tessa.common.model.types.HierarchyType;
import es.cic.tessa.common.model.types.RelationshipType;


public class TemplateReferenceFilter
{

    private List<PropertyFilter> templateFilter = new ArrayList<>();
    private List<PropertyFilter> organizerFilter = new ArrayList<>();
    private List<PropertyFilter> templateAttributeFilter = new ArrayList<>();
    private List<PropertyFilter> hashtagFilter = new ArrayList<>();
    private List<PropertyFilter> relationshipFilter = new ArrayList<>();
    private String relationshipDirection = RelationshipType.TO.getRelationshipType();
    private String hierarchyType = HierarchyType.NONE.getCode();
    private List<TemplateReferenceFilter> referenceFilter = new ArrayList<>();
    
    public void addTemplatePropertyFilter(PropertyFilter propertyFilter)
    {

	getTemplateFilter().add(propertyFilter);
    }


    public void addOrganizerPropertyFilter(PropertyFilter propertyFilter)
    {

	getOrganizerFilter().add(propertyFilter);
    }


    public void addTemplateAttributePropertyFilter(PropertyFilter propertyFilter)
    {

	getTemplateAttributeFilter().add(propertyFilter);
    }


    public void addHashtagPropertyFilter(PropertyFilter propertyFilter)
    {

	getHashtagFilter().add(propertyFilter);
    }


    public void addRelationshipPropertyFilter(PropertyFilter propertyFilter)
    {

	getRelationshipFilter().add(propertyFilter);
    }

    public void addReferenceFilter(TemplateReferenceFilter templateReferenceFilter)
    {
	
	getReferenceFilter().add(templateReferenceFilter);
    }
    
    public List<PropertyFilter> getTemplateFilter()
    {

	return templateFilter;
    }


    public void setTemplateFilter(List<PropertyFilter> templateFilter)
    {

	this.templateFilter = templateFilter;
    }


    public List<PropertyFilter> getOrganizerFilter()
    {

	return organizerFilter;
    }


    public void setOrganizerFilter(List<PropertyFilter> organizerFilter)
    {

	this.organizerFilter = organizerFilter;
    }


    public List<PropertyFilter> getTemplateAttributeFilter()
    {

	return templateAttributeFilter;
    }


    public void setTemplateAttributeFilter(List<PropertyFilter> templateAttributeFilter)
    {

	this.templateAttributeFilter = templateAttributeFilter;
    }


    public List<PropertyFilter> getHashtagFilter()
    {

	return hashtagFilter;
    }


    public void setHashtagFilter(List<PropertyFilter> hashtagFilter)
    {

	this.hashtagFilter = hashtagFilter;
    }


    public List<PropertyFilter> getRelationshipFilter()
    {

	return relationshipFilter;
    }


    public void setRelationshipFilter(List<PropertyFilter> relationshipFilter)
    {

	this.relationshipFilter = relationshipFilter;
    }


    public String getRelationshipDirection()
    {

	return relationshipDirection;
    }


    public void setRelationshipDirection(String relationshipDirection)
    {

	this.relationshipDirection = relationshipDirection;
    }


    public String getHierarchyType()
    {

	return hierarchyType;
    }


    public void setHierarchyType(String hierarchyType)
    {

	this.hierarchyType = hierarchyType;
    }


    
    public List<TemplateReferenceFilter> getReferenceFilter()
    {
    
        return referenceFilter;
    }


    
    public void setReferenceFilter(List<TemplateReferenceFilter> referenceFilter)
    {
    
        this.referenceFilter = referenceFilter;
    }

}
