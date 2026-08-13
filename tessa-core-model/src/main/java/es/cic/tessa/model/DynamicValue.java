package es.cic.tessa.model;


public class DynamicValue
{

    private String fullExpression;
    private String expression;
    private String property;
    private String templateAttributeName;
    private String hierarchyType;

    public String getFullExpression()
    {

	return fullExpression;
    }


    public void setFullExpression(String fullExpression)
    {

	this.fullExpression = fullExpression;
    }


    public String getExpression()
    {

	return expression;
    }


    public void setExpression(String expression)
    {

	this.expression = expression;
    }


    public String getProperty()
    {

	return property;
    }


    public void setProperty(String property)
    {

	this.property = property;
    }


    public String getTemplateAttributeName()
    {

	return templateAttributeName;
    }


    public void setTemplateAttributeName(String templateAttributeName)
    {

	this.templateAttributeName = templateAttributeName;
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
    public String toString()
    {

	return "DynamicValue [expression=" + expression + " templateName=" + property + ", templateAttributeName=" + templateAttributeName + "]";
    }
}
