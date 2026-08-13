package es.cic.tessa.model.filter.optimize;


import java.util.Objects;
import java.util.Set;
import es.cic.tessa.common.filter.OptimizeFilter;


public class AssetValueOptimizeFilter extends OptimizeFilter
{

    private Long templateAttributeId;
    private Long assetId;
    private Set<Long> assetIds;
    private String templateAttributeName;
    private String templateName;
    private Set<Long> idsAssetReference;
    private Boolean functionValue = Boolean.FALSE;

    public Long getTemplateAttributeId()
    {

	return templateAttributeId;
    }


    public void setTemplateAttributeId(Long templateAttributeId)
    {

	this.templateAttributeId = templateAttributeId;
    }


    public Long getAssetId()
    {

	return assetId;
    }


    public void setAssetId(Long assetId)
    {

	this.assetId = assetId;
    }


    public Set<Long> getAssetIds()
    {

	return assetIds;
    }


    public void setAssetIds(Set<Long> assetIds)
    {

	this.assetIds = assetIds;
    }


    public String getTemplateAttributeName()
    {

	return templateAttributeName;
    }


    public void setTemplateAttributeName(String templateAttributeName)
    {

	this.templateAttributeName = templateAttributeName;
    }


    public String getTemplateName()
    {

	return templateName;
    }


    public void setTemplateName(String templateName)
    {

	this.templateName = templateName;
    }


    public Set<Long> getIdsAssetReference()
    {

	return idsAssetReference;
    }


    public void setIdsAssetReference(Set<Long> idsAssetReference)
    {

	this.idsAssetReference = idsAssetReference;
    }


    public Boolean getFunctionValue()
    {

	return functionValue;
    }


    public void setFunctionValue(Boolean functionValue)
    {

	this.functionValue = functionValue;
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(assetId, assetIds, idsAssetReference, templateAttributeId, templateAttributeName, templateName);
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
	AssetValueOptimizeFilter other = (AssetValueOptimizeFilter) obj;
	return Objects.equals(assetId, other.assetId) && Objects.equals(assetIds, other.assetIds) && Objects.equals(idsAssetReference, other.idsAssetReference) && Objects.equals(templateAttributeId, other.templateAttributeId) && Objects.equals(templateAttributeName, other.templateAttributeName) && Objects.equals(templateName, other.templateName);
    }


    @Override
    public String toString()
    {

	return "AssetValueOptimizeFilter [templateAttributeId=" + templateAttributeId + ", assetId=" + assetId + ", assetIds=" + assetIds + ", templateAttributeName=" + templateAttributeName + ", templateName=" + templateName + ", idsAssetReference=" + idsAssetReference + ", functionValue=" + functionValue + ", id=" + id + ", ids=" + ids + ", name=" + name + "]";
    }

}
