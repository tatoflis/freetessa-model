package es.cic.tessa.model.filter.optimize;


import java.util.Objects;
import es.cic.tessa.common.filter.OptimizeFilter;


public class TemplateOptimizeFilter extends OptimizeFilter
{

    private String hierachyType;
    private String parentTemplateName;
    private String childTemplateName;

    public String getHierachyType()
    {

	return hierachyType;
    }


    public void setHierachyType(String hierachyType)
    {

	this.hierachyType = hierachyType;
    }


    public String getParentTemplateName()
    {

	return parentTemplateName;
    }


    public void setParentTemplateName(String parentTemplateName)
    {

	this.parentTemplateName = parentTemplateName;
    }


    public String getChildTemplateName()
    {

	return childTemplateName;
    }


    public void setChildTemplateName(String childTemplateName)
    {

	this.childTemplateName = childTemplateName;
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(childTemplateName, hierachyType, parentTemplateName);
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
	TemplateOptimizeFilter other = (TemplateOptimizeFilter) obj;
	return Objects.equals(childTemplateName, other.childTemplateName) && Objects.equals(hierachyType, other.hierachyType) && Objects.equals(parentTemplateName, other.parentTemplateName);
    }


    @Override
    public String toString()
    {

	return "TemplateOptimizeFilter [hierachyType=" + hierachyType + ", parentTemplateName=" + parentTemplateName + ", childTemplateName=" + childTemplateName + ", id=" + id + ", ids=" + ids + ", name=" + name + "]";
    }

}
