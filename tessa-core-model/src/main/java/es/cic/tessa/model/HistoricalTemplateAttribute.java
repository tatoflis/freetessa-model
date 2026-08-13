package es.cic.tessa.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.springframework.data.neo4j.core.convert.ConvertWith;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import es.cic.tessa.common.model.Labels;
import es.cic.tessa.common.model.Relations;
import es.cic.tessa.model.support.TessaElement;
import es.cic.tessa.model.types.TemplateAttributeType;
import es.cic.tessa.model.utils.FunctionConverter;


@Node(Labels.HISTORICAL_TEMPLATE_ATTRIBUTE)
public class HistoricalTemplateAttribute extends TessaElement implements Comparable<HistoricalTemplateAttribute>
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    protected Long nodeId;

    @Property(name = "identificable")
    protected Boolean identificable = Boolean.FALSE;

    @Property(name = "alias")
    protected Boolean alias = Boolean.FALSE;

    @Property(name = "type")
    private String type = TemplateAttributeType.STRING.getCode();

    @Property(name = "minLength")
    private Integer minLength;

    @Property(name = "maxLength")
    private Integer maxLength;

    @Property(name = "required")
    private Boolean required = Boolean.FALSE;

    @Property(name = "hidden")
    private Boolean hidden = Boolean.FALSE;

    @Property(name = "hasDefaultValue")
    private Boolean hasDefaultValue = Boolean.FALSE;

    @Property(name = "defaultValue")
    private String defaultValue;

    @Property(name = "hasCalculatedValue")
    private Boolean hasCalculatedValue = Boolean.FALSE;

    @Property(name = "calculatedValue")
    private String calculatedValue;

    @ConvertWith(converter = FunctionConverter.class)
    private Function expressionProperties;

    @Property(name = "pattern")
    private String pattern;

    @Property(name = "unique")
    private Boolean unique = Boolean.FALSE;

    @Property(name = "external")
    private Boolean externalSource = Boolean.FALSE;

    @Property(name = "collection")
    private Boolean collection = Boolean.FALSE;

    @Property(name = "withcapacity")
    private Boolean withcapacity = Boolean.FALSE;

    @Property(name = "capacity")
    private Integer capacity;

    @Property(name = "mapping")
    private Boolean mapping = Boolean.FALSE;

    @Property(name = "final")
    private Boolean finalAttribute = Boolean.FALSE;

    @Property(name = "passwordAttribute")
    private Boolean password = Boolean.FALSE;

    @Property(name = "contentType")
    private String contentType;

    @Property(name = "enumValues")
    private List<String> enumValues;

    @Relationship(type = Relations.CLASSIFIES_RELATION, direction = Direction.OUTGOING)
    private List<HistoricalHashtag> hashtags = new ArrayList<>();

    @Relationship(type = Relations.MAPPING_RELATION, direction = Direction.OUTGOING)
    private SortedSet<HistoricalTemplateAttributeCollectionMapping> historicalTemplateAttributeCollectionMapping = new TreeSet<>();

    @Relationship(type = Relations.TEMPLATE_REFERENCE_RELATION, direction = Direction.OUTGOING)
    private HistoricalTemplateReference historicalTemplateReference;

    @Relationship(type = Relations.EXPRESSION_RELATION, direction = Direction.OUTGOING)
    private Set<HistoricalExpressionParam> historicalExpressionParams = new HashSet<>();

    @Relationship(type = Relations.ATTRIBUTE_RELATION, direction = Direction.INCOMING)
    private HistoricalTemplate historicalTemplate;

    @Relationship(type = Relations.HISTORICAL_CHANGE_RELATION, direction = Direction.INCOMING)
    private HistoricalTemplateAttributeChange historicalChange;

    @Property(name = "historicalChangeOperation")
    private String historicalChangeOperation;

    @Property(name = "position")
    protected Integer position = Integer.valueOf(0);

    @Property(name = "startChange")
    private LocalDateTime startChange;

    @Property(name = "endChange")
    private LocalDateTime endChange;

    public HistoricalTemplateAttribute()
    {

    }


    public HistoricalTemplateAttribute(String name, String type, Set<String> groups)
    {

	super(name, groups);
	this.type = type;
    }


    @Override
    public int compareTo(HistoricalTemplateAttribute o)
    {

	return this.getNemonic().compareTo(o.getNemonic());
    }


    @Override
    public Long getId()
    {

	return this.nodeId;
    }


    @Override
    public void setId(Long nodeId)
    {

	this.nodeId = nodeId;
    }


    public Boolean getIdentificable()
    {

	return identificable;
    }


    public void setIdentificable(Boolean identificable)
    {

	this.identificable = identificable;
    }


    public Boolean getAlias()
    {

	return alias;
    }


    public void setAlias(Boolean alias)
    {

	this.alias = alias;
    }


    public String getType()
    {

	return type;
    }


    public void setType(String type)
    {

	this.type = type;
    }


    public Integer getMinLength()
    {

	return minLength;
    }


    public void setMinLength(Integer minLength)
    {

	this.minLength = minLength;
    }


    public Integer getMaxLength()
    {

	return maxLength;
    }


    public void setMaxLength(Integer maxLength)
    {

	this.maxLength = maxLength;
    }


    public Boolean getRequired()
    {

	return required;
    }


    public void setRequired(Boolean required)
    {

	this.required = required;
    }


    public Boolean getHidden()
    {

	return hidden;
    }


    public void setHidden(Boolean hidden)
    {

	this.hidden = hidden;
    }


    public Boolean getHasDefaultValue()
    {

	return hasDefaultValue;
    }


    public void setHasDefaultValue(Boolean hasDefaultValue)
    {

	this.hasDefaultValue = hasDefaultValue;
    }


    public String getDefaultValue()
    {

	return defaultValue;
    }


    public void setDefaultValue(String defaultValue)
    {

	this.defaultValue = defaultValue;
    }


    public Boolean getHasCalculatedValue()
    {

	return hasCalculatedValue;
    }


    public void setHasCalculatedValue(Boolean hasCalculatedValue)
    {

	this.hasCalculatedValue = hasCalculatedValue;
    }


    public String getCalculatedValue()
    {

	return calculatedValue;
    }


    public void setCalculatedValue(String calculatedValue)
    {

	this.calculatedValue = calculatedValue;
    }


    public String getPattern()
    {

	return pattern;
    }


    public void setPattern(String pattern)
    {

	this.pattern = pattern;
    }


    public Boolean getUnique()
    {

	return unique;
    }


    public void setUnique(Boolean unique)
    {

	this.unique = unique;
    }


    public Boolean getExternalSource()
    {

	return externalSource;
    }


    public void setExternalSource(Boolean externalSource)
    {

	this.externalSource = externalSource;
    }


    public Boolean getCollection()
    {

	return collection;
    }


    public void setCollection(Boolean collection)
    {

	this.collection = collection;
    }


    public Boolean getWithcapacity()
    {

	return withcapacity;
    }


    public void setWithcapacity(Boolean withcapacity)
    {

	this.withcapacity = withcapacity;
    }


    public Integer getCapacity()
    {

	return capacity;
    }


    public void setCapacity(Integer capacity)
    {

	this.capacity = capacity;
    }


    public Boolean getMapping()
    {

	return mapping;
    }


    public void setMapping(Boolean mapping)
    {

	this.mapping = mapping;
    }


    public Boolean getFinalAttribute()
    {

	return finalAttribute;
    }


    public void setFinalAttribute(Boolean finalAttribute)
    {

	this.finalAttribute = finalAttribute;
    }


    public Boolean isPassword()
    {

	return password;
    }


    public void setHasPassword(Boolean password)
    {

	this.password = password;
    }


    public String getContentType()
    {

	return contentType;
    }


    public void setContentType(String contentType)
    {

	this.contentType = contentType;
    }


    public List<String> getEnumValues()
    {

	return enumValues;
    }


    public void setEnumValues(List<String> enumValues)
    {

	this.enumValues = enumValues;
    }


    public List<HistoricalHashtag> getHashtags()
    {

	return hashtags;
    }


    public void setHashtags(List<HistoricalHashtag> hashtags)
    {

	this.hashtags = hashtags;
    }


    public SortedSet<HistoricalTemplateAttributeCollectionMapping> getHistoricalTemplateAttributeCollectionMapping()
    {

	return historicalTemplateAttributeCollectionMapping;
    }


    public void setTemplateAttributeCollectionMapping(SortedSet<HistoricalTemplateAttributeCollectionMapping> historicalTemplateAttributeCollectionMapping)
    {

	this.historicalTemplateAttributeCollectionMapping = historicalTemplateAttributeCollectionMapping;
    }


    public HistoricalTemplateReference getHistoricalTemplateReference()
    {

	return historicalTemplateReference;
    }


    public void setHistoricalTemplateReference(HistoricalTemplateReference historicalTemplateReference)
    {

	this.historicalTemplateReference = historicalTemplateReference;
    }


    public Set<HistoricalExpressionParam> getHistoricalExpressionParams()
    {

	return historicalExpressionParams;
    }


    public void setHistoricalExpressionParams(Set<HistoricalExpressionParam> historicalExpressionParams)
    {

	this.historicalExpressionParams = historicalExpressionParams;
    }


    public HistoricalTemplate getHistoricalTemplate()
    {

	return historicalTemplate;
    }


    public void setHistoricalTemplate(HistoricalTemplate historicalTemplate)
    {

	this.historicalTemplate = historicalTemplate;
    }


    public HistoricalTemplateAttributeChange getHistoricalChange()
    {

	return historicalChange;
    }


    public void setHistoricalChange(HistoricalTemplateAttributeChange historicalChange)
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


    public Integer getPosition()
    {

	return position;
    }


    public void setPosition(Integer position)
    {

	this.position = position;
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
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(alias, calculatedValue, capacity, collection, contentType, defaultValue, enumValues, externalSource, finalAttribute, hasCalculatedValue, hasDefaultValue, hashtags, hidden, historicalTemplate, nodeId, identificable, mapping, maxLength, minLength, password, pattern, position, required, type, unique, withcapacity, historicalChangeOperation);
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
	HistoricalTemplateAttribute other = (HistoricalTemplateAttribute) obj;
	return Objects.equals(alias, other.alias) && Objects.equals(calculatedValue, other.calculatedValue) && Objects.equals(capacity, other.capacity) && Objects.equals(collection, other.collection) && Objects.equals(contentType, other.contentType) && Objects.equals(defaultValue, other.defaultValue) && Objects.equals(enumValues, other.enumValues) && Objects.equals(externalSource, other.externalSource) && Objects.equals(finalAttribute, other.finalAttribute) && Objects.equals(hasCalculatedValue, other.hasCalculatedValue) && Objects.equals(hasDefaultValue, other.hasDefaultValue) && Objects.equals(hashtags, other.hashtags) && Objects.equals(hidden, other.hidden) && Objects.equals(historicalTemplate, other.historicalTemplate) && Objects.equals(nodeId, other.nodeId) && Objects.equals(identificable, other.identificable) && Objects.equals(mapping, other.mapping) && Objects.equals(maxLength, other.maxLength) && Objects.equals(minLength, other.minLength) && Objects.equals(password, other.password) && Objects.equals(pattern, other.pattern) && Objects.equals(position, other.position) && Objects.equals(required, other.required) && Objects.equals(type, other.type) && Objects.equals(unique, other.unique) && Objects.equals(withcapacity, other.withcapacity) && Objects.equals(historicalChangeOperation, other.historicalChangeOperation);
    }


    public Function getExpressionProperties()
    {

	return expressionProperties;
    }


    public void setExpressionProperties(Function expressionProperties)
    {

	this.expressionProperties = expressionProperties;
    }


    public Boolean getPassword()
    {

	return password;
    }


    public void setPassword(Boolean password)
    {

	this.password = password;
    }
}
