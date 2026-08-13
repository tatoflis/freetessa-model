package es.cic.tessa.model.filter.optimize;


import java.util.Collection;
import java.util.Objects;
import es.cic.tessa.common.filter.OptimizeFilter;


public class TemplateAttributeOptimizeFilter extends OptimizeFilter
{

    private Long templateId;
    private String templateName;
    private String templateType;
    private String hierarchyType;
    private Boolean cronExpression = Boolean.FALSE;
    private Long templateReferenceId;
    private Collection<Long> templateReferenceIds;

    private String originAttribute;
    private String originTemplate;
    private String targetAttribute;
    private String targetTemplate;
    private String referenceAttribute;
    private int numberOfLevels;

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


    public String getTemplateType()
    {

	return templateType;
    }


    public void setTemplateType(String templateType)
    {

	this.templateType = templateType;
    }


    public String getHierarchyType()
    {

	return hierarchyType;
    }


    public void setHierarchyType(String hierarchyType)
    {

	this.hierarchyType = hierarchyType;
    }


    public Boolean getCronExpression()
    {

	return cronExpression;
    }


    public void setCronExpression(Boolean cronExpression)
    {

	this.cronExpression = cronExpression;
    }


    public Long getTemplateReferenceId()
    {

	return templateReferenceId;
    }


    public void setTemplateReferenceId(Long templateReferenceId)
    {

	this.templateReferenceId = templateReferenceId;
    }


    public Collection<Long> getTemplateReferenceIds()
    {

	return templateReferenceIds;
    }


    public void setTemplateReferenceIds(Collection<Long> templateReferenceIds)
    {

	this.templateReferenceIds = templateReferenceIds;
    }


    public String getOriginAttribute()
    {

	return originAttribute;
    }


    public void setOriginAttribute(String originAttribute)
    {

	this.originAttribute = originAttribute;
    }


    public String getOriginTemplate()
    {

	return originTemplate;
    }


    public void setOriginTemplate(String originTemplate)
    {

	this.originTemplate = originTemplate;
    }


    public String getTargetAttribute()
    {

	return targetAttribute;
    }


    public void setTargetAttribute(String targetAttribute)
    {

	this.targetAttribute = targetAttribute;
    }


    public String getTargetTemplate()
    {

	return targetTemplate;
    }


    public void setTargetTemplate(String targetTemplate)
    {

	this.targetTemplate = targetTemplate;
    }


    public String getReferenceAttribute()
    {

	return referenceAttribute;
    }


    public void setReferenceAttribute(String referenceAttribute)
    {

	this.referenceAttribute = referenceAttribute;
    }


    public int getNumberOfLevels()
    {

	return numberOfLevels;
    }


    public void setNumberOfLevels(int numberOfLevels)
    {

	this.numberOfLevels = numberOfLevels;
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(cronExpression, hierarchyType, templateId, templateName, templateReferenceId, templateType);
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
	TemplateAttributeOptimizeFilter other = (TemplateAttributeOptimizeFilter) obj;
	return Objects.equals(cronExpression, other.cronExpression) && Objects.equals(hierarchyType, other.hierarchyType) && Objects.equals(templateId, other.templateId) && Objects.equals(templateName, other.templateName) && Objects.equals(templateReferenceId, other.templateReferenceId) && Objects.equals(templateType, other.templateType);
    }


    @Override
    public String toString()
    {

	return "TemplateAttributeOptimizeFilter [templateId=" + templateId + ", templateName=" + templateName + ", templateType=" + templateType + ", hierarchyType=" + hierarchyType + ", cronExpression=" + cronExpression + ", templateReferenceId=" + templateReferenceId + ", templateReferenceIds=" + templateReferenceIds + ", originAttribute=" + originAttribute + ", originTemplate=" + originTemplate + ", targetAttribute=" + targetAttribute + ", targetTemplate=" + targetTemplate + ", referenceAttribute=" + referenceAttribute + ", numberOfLevels=" + numberOfLevels + ", id=" + id + ", ids=" + ids + ", name=" + name + "]";
    }

}
