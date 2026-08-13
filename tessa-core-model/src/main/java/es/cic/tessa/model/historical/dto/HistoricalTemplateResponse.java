package es.cic.tessa.model.historical.dto;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonProperty;
import es.cic.tessa.model.dto.support.AbstractEntityResponse;
import es.cic.tessa.model.types.TemplateType;


@Component
public class HistoricalTemplateResponse extends AbstractEntityResponse
{

    private static final long serialVersionUID = 1L;

    @JsonProperty("final")
    private Boolean finalTemplate = Boolean.FALSE;

    @JsonProperty("abstract")
    private Boolean abstractTemplate = Boolean.FALSE;

    private Boolean assetOrganized = Boolean.FALSE;

    private Boolean templateOrganized = Boolean.FALSE;

    private String type = TemplateType.COMPLEX.getCode();

    private HistoricalTemplateResponse historicalExtendsTemplate;

    private Collection<HistoricalOrganizerResponse> historicalOrganizers;

    private HistoricalChangeResponse historicalChange;

    private Integer numComplexAttributes = Integer.valueOf(0);

    private Collection<HistoricalTemplateAttributeResponse> historicalTemplateAttributes = new ArrayList<>();

    private Long nodeId;

    public Boolean getFinalTemplate()
    {

	return finalTemplate;
    }


    public void setFinalTemplate(Boolean finalTemplate)
    {

	this.finalTemplate = finalTemplate;
    }


    public Boolean getAbstractTemplate()
    {

	return abstractTemplate;
    }


    public void setAbstractTemplate(Boolean abstractTemplate)
    {

	this.abstractTemplate = abstractTemplate;
    }


    public Boolean getAssetOrganized()
    {

	return assetOrganized;
    }


    public void setAssetOrganized(Boolean assetOrganized)
    {

	this.assetOrganized = assetOrganized;
    }


    public Boolean getTemplateOrganized()
    {

	return templateOrganized;
    }


    public void setTemplateOrganized(Boolean templateOrganized)
    {

	this.templateOrganized = templateOrganized;
    }


    public String getType()
    {

	return type;
    }


    public void setType(String type)
    {

	this.type = type;
    }


    public HistoricalTemplateResponse getHistoricalExtendsTemplate()
    {

	return historicalExtendsTemplate;
    }


    public void setHistoricalExtendsTemplate(HistoricalTemplateResponse historicalExtendsTemplate)
    {

	this.historicalExtendsTemplate = historicalExtendsTemplate;
    }


    public Collection<HistoricalOrganizerResponse> getHistoricalOrganizers()
    {

	return historicalOrganizers;
    }


    public void setHistoricalOrganizers(Collection<HistoricalOrganizerResponse> historicalOrganizers)
    {

	this.historicalOrganizers = historicalOrganizers;
    }


    public HistoricalChangeResponse getHistoricalChange()
    {

	return historicalChange;
    }


    public void setHistoricalChange(HistoricalChangeResponse historicalChange)
    {

	this.historicalChange = historicalChange;
    }


    public Integer getNumComplexAttributes()
    {

	return numComplexAttributes;
    }


    public void setNumComplexAttributes(Integer numComplexAttributes)
    {

	this.numComplexAttributes = numComplexAttributes;
    }


    public Collection<HistoricalTemplateAttributeResponse> getHistoricalTemplateAttributes()
    {

	return historicalTemplateAttributes;
    }


    public void setHistoricalTemplateAttributes(Collection<HistoricalTemplateAttributeResponse> historicalTemplateAttributes)
    {

	this.historicalTemplateAttributes = historicalTemplateAttributes;
    }


    public Long getNodeId()
    {

	return nodeId;
    }


    public void setNodeId(Long nodeId)
    {

	this.nodeId = nodeId;
    }


    // historicalTemplateAttributes excluido: HistoricalTemplateAttributeResponse.hashCode()/equals()
    // incluye a su vez historicalTemplate (relacion inversa), lo que produce recursion infinita
    // (StackOverflowError) en cuanto ambos lados estan poblados -- mismo patron que el corregido
    // en las entidades de dominio HistoricalTemplate/HistoricalTemplateAttribute, pero aqui en
    // el DTO que realmente serializa Jackson via HTTP.
    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(abstractTemplate, assetOrganized, finalTemplate, historicalChange, historicalExtendsTemplate, historicalOrganizers, nodeId, numComplexAttributes, templateOrganized, type);
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
	HistoricalTemplateResponse other = (HistoricalTemplateResponse) obj;
	return Objects.equals(abstractTemplate, other.abstractTemplate) && Objects.equals(assetOrganized, other.assetOrganized) && Objects.equals(finalTemplate, other.finalTemplate) && Objects.equals(historicalChange, other.historicalChange) && Objects.equals(historicalExtendsTemplate, other.historicalExtendsTemplate) && Objects.equals(historicalOrganizers, other.historicalOrganizers) && Objects.equals(nodeId, other.nodeId) && Objects.equals(numComplexAttributes, other.numComplexAttributes) && Objects.equals(templateOrganized, other.templateOrganized) && Objects.equals(type, other.type);
    }
}
