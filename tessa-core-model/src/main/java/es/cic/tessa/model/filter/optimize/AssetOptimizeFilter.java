package es.cic.tessa.model.filter.optimize;


import java.util.Map;
import java.util.Objects;
import es.cic.tessa.common.filter.OptimizeFilter;


public class AssetOptimizeFilter extends OptimizeFilter
{

    private Long assetValueId;
    private Long assetValueReferenceId;
    private Long organizerId;
    private Long templateId;
    private String templateName;
    private Long parentAssetId;
    private Long childAssetId;
    private String templateAttributeReferenceName;
    private String childValueName;
    private Map<String, String> templateAttributeValuePairs;
    private String hierarchyType;

    public Long getAssetValueId()
    {

	return assetValueId;
    }


    public void setAssetValueId(Long assetValueId)
    {

	this.assetValueId = assetValueId;
    }


    public Long getAssetValueReferenceId()
    {

	return assetValueReferenceId;
    }


    public void setAssetValueReferenceId(Long assetValueReferenceId)
    {

	this.assetValueReferenceId = assetValueReferenceId;
    }


    public Long getOrganizerId()
    {

	return organizerId;
    }


    public void setOrganizerId(Long organizerId)
    {

	this.organizerId = organizerId;
    }


    public Long getTemplateId()
    {

	return templateId;
    }


    public void setTemplateId(Long templateId)
    {

	this.templateId = templateId;
    }


    public String getTemplateName()
    {

	return templateName;
    }


    public void setTemplateName(String templateName)
    {

	this.templateName = templateName;
    }


    public Long getParentAssetId()
    {

	return parentAssetId;
    }


    public void setParentAssetId(Long parentAssetId)
    {

	this.parentAssetId = parentAssetId;
    }


    public Long getChildAssetId()
    {

	return childAssetId;
    }


    public void setChildAssetId(Long childAssetId)
    {

	this.childAssetId = childAssetId;
    }


    public String getTemplateAttributeReferenceName()
    {

	return templateAttributeReferenceName;
    }


    public void setTemplateAttributeReferenceName(String templateAttributeReferenceName)
    {

	this.templateAttributeReferenceName = templateAttributeReferenceName;
    }


    public String getChildValueName()
    {

	return childValueName;
    }


    public void setChildValueName(String childValueName)
    {

	this.childValueName = childValueName;
    }


    public Map<String, String> getTemplateAttributeValuePairs()
    {

	return templateAttributeValuePairs;
    }


    public void setTemplateAttributeValuePairs(Map<String, String> templateAttributeValuePairs)
    {

	this.templateAttributeValuePairs = templateAttributeValuePairs;
    }


    public String getHierarchyType()
    {

	return hierarchyType;
    }


    public void setHierarchyType(String hierarchyType)
    {

	this.hierarchyType = hierarchyType;
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(assetValueId, assetValueReferenceId, childAssetId, childValueName, hierarchyType, organizerId, parentAssetId, templateAttributeReferenceName, templateAttributeValuePairs, templateId, templateName);
	return result;
    }


    @Override
    public boolean equals(Object obj)
    {

	if(this == obj)
	    return true;
	if(!super.equals(obj))
	    return false;
	if(getClass() != obj.getClass())
	    return false;
	AssetOptimizeFilter other = (AssetOptimizeFilter) obj;
	return Objects.equals(assetValueId, other.assetValueId) && Objects.equals(assetValueReferenceId, other.assetValueReferenceId) && Objects.equals(childAssetId, other.childAssetId) && Objects.equals(childValueName, other.childValueName) && Objects.equals(hierarchyType, other.hierarchyType) && Objects.equals(organizerId, other.organizerId) && Objects.equals(parentAssetId, other.parentAssetId) && Objects.equals(templateAttributeReferenceName, other.templateAttributeReferenceName) && Objects.equals(templateAttributeValuePairs, other.templateAttributeValuePairs) && Objects.equals(templateId, other.templateId) && Objects.equals(templateName, other.templateName);
    }


    @Override
    public String toString()
    {

	return "AssetOptimizeFilter [assetValueId=" + assetValueId + ", assetValueReferenceId=" + assetValueReferenceId + ", organizerId=" + organizerId + ", templateId=" + templateId + ", templateName=" + templateName + ", parentAssetId=" + parentAssetId + ", childAssetId=" + childAssetId + ", templateAttributeReferenceName=" + templateAttributeReferenceName + ", childValueName=" + childValueName + ", templateAttributeValuePairs=" + templateAttributeValuePairs + ", hierarchyType=" + hierarchyType + ", id=" + id + ", ids=" + ids + ", name=" + name + "]";
    }

}
