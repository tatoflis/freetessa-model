package es.cic.tessa.model.mappers;


import es.cic.tessa.model.Template;
import es.cic.tessa.model.optimize.TemplateOptimize;


public final class TemplateOptimizeMapper
{

    private TemplateOptimizeMapper()
    {

    }


    public static TemplateOptimize toOptimize(Template template)
    {

	if(template == null)
	{
	    return null;
	}

	TemplateOptimize optimize = new TemplateOptimize();
	optimize.setId(template.getCustomId());
	optimize.setName(template.getName());
	optimize.setGroups(template.getGroups());
	optimize.setType(template.getType());

	if(template.getExtendsTemplate() != null)
	{
	    TemplateOptimize extendsOptimize = new TemplateOptimize();
	    extendsOptimize.setId(template.getExtendsTemplate().getCustomId());
	    extendsOptimize.setName(template.getExtendsTemplate().getName());
	    extendsOptimize.setGroups(template.getExtendsTemplate().getGroups());
	    optimize.setTemplateExtendsOptimize(extendsOptimize);
	}

	return optimize;
    }
}
