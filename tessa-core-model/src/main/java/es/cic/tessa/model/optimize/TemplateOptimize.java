package es.cic.tessa.model.optimize;


import java.util.HashSet;
import java.util.Set;


public class TemplateOptimize extends TessaElementOptimize
{

    private static final long serialVersionUID = 1L;

    private TemplateOptimize templateExtendsOptimize;
    private String type;

    private Set<TemplateAttributeOptimize> templateAttributesOptimize = new HashSet<>();

    public TemplateOptimize()
    {

	super();
    }


    public TemplateOptimize(String name, Set<String> groups)
    {

	super(name, groups);

    }


    public Set<TemplateAttributeOptimize> getTemplateAttributesOptimize()
    {

	return templateAttributesOptimize;
    }


    public void setTemplateAttributesOptimize(Set<TemplateAttributeOptimize> templateAttributesOptimize)
    {

	this.templateAttributesOptimize = templateAttributesOptimize;
    }


    public TemplateOptimize getTemplateExtendsOptimize()
    {

	return templateExtendsOptimize;
    }


    public void setTemplateExtendsOptimize(TemplateOptimize templateExtendsOptimize)
    {

	this.templateExtendsOptimize = templateExtendsOptimize;
    }


    public String getType()
    {

	return type;
    }


    public void setType(String type)
    {

	this.type = type;
    }

}
