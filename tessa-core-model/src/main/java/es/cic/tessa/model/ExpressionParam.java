package es.cic.tessa.model;


import java.util.Objects;
import java.util.Set;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import es.cic.tessa.common.model.Labels;
import es.cic.tessa.common.model.Relations;
import es.cic.tessa.model.support.TessaElement;


@Node(Labels.EXPRESSION_PARAM)
public class ExpressionParam extends TessaElement implements Comparable<ExpressionParam>
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long nodeId;

    @Property(name = "type")
    private String type;

    @Property(name = "required")
    private Boolean required;

    @Property(name = "position")
    protected Integer position = Integer.valueOf(0);

    @Relationship(type = Relations.EXPRESSION_RELATION, direction = Direction.INCOMING)
    private TemplateAttribute templateAttribute;

    @Relationship(type = Relations.DEFAULT_VALUE, direction = Direction.OUTGOING)
    private DefaultValueAssetValue defaultValueAssetValue;

    public ExpressionParam()
    {

    }


    public ExpressionParam(String name, String type, Boolean required, Set<String> groups)
    {

	super(name, groups);
	this.type = type;
	this.required = required;
    }


    public Long getId()
    {

	return nodeId;
    }


    public void setId(Long id)
    {

	this.nodeId = id;
    }


    public TemplateAttribute getTemplateAttribute()
    {

	return templateAttribute;
    }


    public void setTemplateAttribute(TemplateAttribute templateAttribute)
    {

	this.templateAttribute = templateAttribute;
    }


    public Integer getPosition()
    {

	return position;
    }


    public void setPosition(Integer position)
    {

	this.position = position;
    }


    public String getType()
    {

	return type;
    }


    public void setType(String type)
    {

	this.type = type;
    }


    public Boolean getRequired()
    {

	return required;
    }


    public void setRequired(Boolean required)
    {

	this.required = required;
    }


    public DefaultValueAssetValue getDefaultValueAssetValue()
    {

	return defaultValueAssetValue;
    }


    public void setDefaultValueAssetValue(DefaultValueAssetValue defaultValueAssetValue)
    {

	this.defaultValueAssetValue = defaultValueAssetValue;
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(defaultValueAssetValue, nodeId, position, required, templateAttribute, type);
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
	ExpressionParam other = (ExpressionParam) obj;
	return Objects.equals(defaultValueAssetValue, other.defaultValueAssetValue) && Objects.equals(nodeId, other.nodeId) && Objects.equals(position, other.position) && Objects.equals(required, other.required) && Objects.equals(templateAttribute, other.templateAttribute) && Objects.equals(type, other.type);
    }


    @Override
    public int compareTo(ExpressionParam o)
    {

	String thisNemonic = this.getNemonic();
	String otherNemonic = o.getNemonic();
	if(thisNemonic == null && otherNemonic == null) return 0;
	if(thisNemonic == null) return -1;
	if(otherNemonic == null) return 1;
	return thisNemonic.compareTo(otherNemonic);
    }

}
