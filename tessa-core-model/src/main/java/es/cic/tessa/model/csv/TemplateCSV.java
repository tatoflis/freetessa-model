package es.cic.tessa.model.csv;


import java.util.List;
import java.util.Objects;
import es.cic.tessa.model.Organizer;
import es.cic.tessa.model.Template;
import es.cic.tessa.model.TemplateAttribute;


public class TemplateCSV implements Comparable<TemplateCSV>, TessaCSVElement
{

    private Template template;
    private List<Organizer> organizers;
    private List<TemplateAttribute> templateAttributes;

    public Template getTemplate()
    {

	return template;
    }


    public void setTemplate(Template template)
    {

	this.template = template;
    }


    public List<Organizer> getOrganizers()
    {

	return organizers;
    }


    public void setOrganizers(List<Organizer> organizers)
    {

	this.organizers = organizers;
    }


    public List<TemplateAttribute> getTemplateAttributes()
    {

	return templateAttributes;
    }


    public void setTemplateAttributes(List<TemplateAttribute> templateAttributes)
    {

	this.templateAttributes = templateAttributes;
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(organizers, template, templateAttributes);
    }


    @Override
    public boolean equals(Object obj)
    {

	if(this == obj)
	    return true;
	if(obj == null)
	    return false;
	if(getClass() != obj.getClass())
	    return false;
	TemplateCSV other = (TemplateCSV) obj;
	return Objects.equals(organizers, other.organizers) && Objects.equals(template, other.template) && Objects.equals(templateAttributes, other.templateAttributes);
    }


    @Override
    public int compareTo(TemplateCSV o)
    {

	String thisNemonic = this.template != null ? this.template.getNemonic() : null;
	String otherNemonic = o.getTemplate() != null ? o.getTemplate().getNemonic() : null;
	if(thisNemonic == null && otherNemonic == null) return 0;
	if(thisNemonic == null) return -1;
	if(otherNemonic == null) return 1;
	return thisNemonic.compareTo(otherNemonic);
    }


    @Override
    public String toString()
    {

	return "TemplateCSV [template=" + template + ", templateAttributes=" + templateAttributes + "]";
    }

}
