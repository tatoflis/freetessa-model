package es.cic.tessa.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.annotation.Transient;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import com.fasterxml.jackson.annotation.JsonProperty;
import es.cic.tessa.common.model.Labels;
import es.cic.tessa.common.model.Relations;
import es.cic.tessa.model.support.TessaElement;
import es.cic.tessa.model.types.TemplateType;


@Node(Labels.HISTORICAL_TEMPLATE)
public class HistoricalTemplate extends TessaElement implements Comparable<HistoricalTemplate>
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    protected Long nodeId;

    @Property(name = "final")
    private Boolean finalTemplate = Boolean.FALSE;

    @Property(name = "abstract")
    @JsonProperty("abstract")
    private Boolean abstractTemplate = Boolean.FALSE;

    @Property(name = "assetOrganized")
    private Boolean assetOrganized = Boolean.FALSE;

    @Property(name = "templateOrganized")
    private Boolean templateOrganized = Boolean.FALSE;

    @Property(name = "type")
    private String type = TemplateType.COMPLEX.getCode();

    @Property(name = "endDate")
    protected LocalDateTime endDate;

    @Property(name = "startChange")
    private LocalDateTime startChange;

    @Property(name = "endChange")
    private LocalDateTime endChange;

    @Relationship(type = Relations.EXTENDS_RELATION, direction = Direction.OUTGOING)
    private HistoricalTemplate historicalExtendsTemplate;

    @Relationship(type = Relations.ATTRIBUTE_RELATION, direction = Direction.OUTGOING)
    private List<HistoricalTemplateAttribute> historicalTemplateAttributes = new ArrayList<>();

    @Relationship(type = Relations.CONTAINED_RELATION, direction = Direction.OUTGOING)
    private List<HistoricalOrganizer> historicalOrganizers = new ArrayList<>();

    @Relationship(type = Relations.HISTORICAL_CHANGE_RELATION, direction = Direction.INCOMING)
    private HistoricalTemplateChange historicalChange;

    @Property(name = "historicalChangeOperation")
    private String historicalChangeOperation;

    @Transient
    private Integer numComplexAttributes = Integer.valueOf(0);

    public HistoricalTemplate()
    {

    }


    public HistoricalTemplate(String name, String type, Set<String> groups)
    {

	super(name, groups);
	this.type = type;
    }


    @Override
    public Long getId()
    {

	return nodeId;
    }


    @Override
    public void setId(Long nodeId)
    {

	this.nodeId = nodeId;
    }


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


    public HistoricalTemplate getHistoricalExtendsTemplate()
    {

	return historicalExtendsTemplate;
    }


    public void setHistoricalExtendsTemplate(HistoricalTemplate historicalExtendsTemplate)
    {

	this.historicalExtendsTemplate = historicalExtendsTemplate;
    }


    public String getType()
    {

	return type;
    }


    public void setType(String type)
    {

	this.type = type;
    }


    public Integer getNumComplexAttributes()
    {

	return numComplexAttributes;
    }


    public void setNumComplexAttributes(Integer numComplexAttributes)
    {

	this.numComplexAttributes = numComplexAttributes;
    }


    public void addHistoricalOrganizer(HistoricalOrganizer organizer)
    {

	getHistoricalOrganizers().add(organizer);

    }


    public void addHistoricalOrganizers(List<HistoricalOrganizer> organizers)
    {

	getHistoricalOrganizers().addAll(organizers);

    }


    public void addTemplateAttribute(HistoricalTemplateAttribute templateAttribute)
    {

	getHistoricalTemplateAttributes().add(templateAttribute);

    }


    public void addTemplateAttributes(List<HistoricalTemplateAttribute> templateAttributes)
    {

	getHistoricalTemplateAttributes().addAll(templateAttributes);

    }


    public List<HistoricalTemplateAttribute> getHistoricalTemplateAttributes()
    {

	return historicalTemplateAttributes;
    }


    public void setHistoricalTemplateAttributes(List<HistoricalTemplateAttribute> templateAttributes)
    {

	this.historicalTemplateAttributes = templateAttributes;
    }


    public List<HistoricalOrganizer> getHistoricalOrganizers()
    {

	return historicalOrganizers;
    }


    public void setHistoricalOrganizers(List<HistoricalOrganizer> historicalOrganizers)
    {

	this.historicalOrganizers = historicalOrganizers;
    }


    public LocalDateTime getEndDate()
    {

	return endDate;
    }


    public void setEndDate(LocalDateTime endDate)
    {

	this.endDate = endDate;
    }


    public HistoricalTemplateChange getHistoricalChange()
    {

	return historicalChange;
    }


    public void setHistoricalChange(HistoricalTemplateChange historicalChange)
    {

	this.historicalChange = historicalChange;
    }


    public String getHistoricalChangeOperation()
    {

	return historicalChangeOperation;
    }


    public void setHistoricalChangeOperation(String historicalChangeOperation)
    {

	this.historicalChangeOperation = historicalChangeOperation;
    }


    public LocalDateTime getStartChange()
    {

	return startChange;
    }


    public void setStartChange(LocalDateTime startChange)
    {

	this.startChange = startChange;
    }


    public LocalDateTime getEndChange()
    {

	return endChange;
    }


    public void setEndChange(LocalDateTime endChange)
    {

	this.endChange = endChange;
    }


    @Override
    public int compareTo(HistoricalTemplate o)
    {

	return this.getNemonic().compareTo(o.getNemonic());
    }


    @Override
    // historicalTemplateAttributes excluido: HistoricalTemplateAttribute.hashCode()/equals()
    // incluye a su vez historicalTemplate (relacion inversa ATTRIBUTE), lo que produce
    // recursion infinita (StackOverflowError) en cuanto ambos lados estan poblados --
    // se dispara en Jackson via USE_EQUALITY_FOR_OBJECT_ID (JsonMapperConfig), que compara
    // objetos por equals()/hashCode() para JSOG en vez de por identidad de referencia.
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(abstractTemplate, assetOrganized, endDate, finalTemplate, historicalExtendsTemplate, historicalOrganizers, nodeId, numComplexAttributes, templateOrganized, type, historicalChangeOperation);
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
	HistoricalTemplate other = (HistoricalTemplate) obj;
	return Objects.equals(abstractTemplate, other.abstractTemplate) && Objects.equals(assetOrganized, other.assetOrganized) && Objects.equals(endDate, other.endDate) && Objects.equals(finalTemplate, other.finalTemplate) && Objects.equals(historicalExtendsTemplate, other.historicalExtendsTemplate) && Objects.equals(historicalOrganizers, other.historicalOrganizers) && Objects.equals(nodeId, other.nodeId) && Objects.equals(numComplexAttributes, other.numComplexAttributes) && Objects.equals(templateOrganized, other.templateOrganized) && Objects.equals(type, other.type) && Objects.equals(historicalChangeOperation, other.historicalChangeOperation);
    }


    @Override
    public String toString()
    {

	return "HistoricalTemplate [id=" + nodeId + ", finalTemplate=" + finalTemplate + ", abstractTemplate=" + abstractTemplate + ", assetOrganized=" + assetOrganized + ", templateOrganized=" + templateOrganized + ", type=" + type + ", endDate=" + endDate + ", historicalExtendsTemplate=" + historicalExtendsTemplate + ", historicalTemplateAttributes=" + historicalTemplateAttributes + ", historicalOrganizers=" + historicalOrganizers + ", numComplexAttributes=" + numComplexAttributes + ", historicalChangeOperation=" + historicalChangeOperation + "]";
    }
}
