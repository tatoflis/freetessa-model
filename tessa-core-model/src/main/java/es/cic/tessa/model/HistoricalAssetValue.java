package es.cic.tessa.model;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.neo4j.core.convert.ConvertWith;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import es.cic.tessa.common.model.Labels;
import es.cic.tessa.common.model.Relations;
import es.cic.tessa.model.properties.AssetValueProperties;
import es.cic.tessa.model.support.TessaElement;
import es.cic.tessa.model.utils.FunctionConverter;


@Node(Labels.HISTORICAL_ASSETVALUE)
public class HistoricalAssetValue extends TessaElement implements Comparable<HistoricalAssetValue>
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    protected Long nodeId;

    @Property(name = AssetValueProperties.VALUE)
    private String value;

    @Property(name = AssetValueProperties.VALUE_LOWER)
    private String valueLower;

    @ConvertWith(converter = FunctionConverter.class)
    private Function expressionProperties;

    @Property(name = AssetValueProperties.ALIAS)
    private Integer alias = Integer.valueOf(0);

    @Relationship(type = Relations.HAS_VALUE_RELATION, direction = Direction.INCOMING)
    private HistoricalAsset historicalAsset;

    @Relationship(type = Relations.HISTORICAL_CHANGE_RELATION, direction = Direction.INCOMING)
    private HistoricalAssetValueChange historicalChange;

    @Relationship(type = Relations.ASSET_REFERENCE_RELATION, direction = Direction.OUTGOING)
    private HistoricalAssetReference historicalAssetReference;

    @Relationship(type = Relations.VALUE_TEMPLATE_RELATION, direction = Direction.OUTGOING)
    private HistoricalTemplateAttribute historicalTemplateAttribute;

    @Relationship(type = Relations.VALUE_TEMPLATE_MAPPED_RELATION, direction = Direction.OUTGOING)
    private HistoricalTemplateAttributeCollectionMapping historicalTemplateAttributeCollectionMapping;

    @Relationship(type = Relations.EXPRESSION_RELATION, direction = Direction.OUTGOING)
    private Set<HistoricalExpressionParam> expressionParams = new HashSet<>();

    @Property(name = "historicalChangeOperation")
    private String historicalChangeOperation;

    @Property(name = "startChange")
    private LocalDateTime startChange;

    @Property(name = "endChange")
    private LocalDateTime endChange;

    public HistoricalAssetValue()
    {

    }


    public HistoricalAssetValue(HistoricalAsset historicalAsset, HistoricalTemplateAttribute historicalTemplateAttribute, String value, Set<String> groups)
    {

	super(value, groups);
	this.historicalAsset = historicalAsset;
	this.historicalTemplateAttribute = historicalTemplateAttribute;
	this.value = value;
    }


    public HistoricalAssetValue(HistoricalAsset historicalAsset, HistoricalTemplateAttribute historicalTemplateAttribute,
	    HistoricalTemplateAttributeCollectionMapping historicaltemplateAttributeCollectionMapping, String value, Set<String> groups)
    {

	super(value, groups);
	this.historicalAsset = historicalAsset;
	this.historicalTemplateAttribute = historicalTemplateAttribute;
	this.historicalTemplateAttributeCollectionMapping = historicaltemplateAttributeCollectionMapping;
	this.value = value;
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


    public String getValue()
    {

	return value;
    }


    public void setValue(String value)
    {

	this.value = value;
    }


    public String getValueLower()
    {

	return valueLower;
    }


    public void setValueLower(String valueLower)
    {

	this.valueLower = valueLower;
    }


    public Function getExpressionProperties()
    {

	return expressionProperties;
    }


    public void setExpressionProperties(Function expressionProperties)
    {

	this.expressionProperties = expressionProperties;
    }


    public Integer getAlias()
    {

	return alias;
    }


    public void setAlias(Integer alias)
    {

	this.alias = alias;
    }


    public HistoricalAsset getHistoricalAsset()
    {

	return historicalAsset;
    }


    public void setHistoricalAsset(HistoricalAsset historicalAsset)
    {

	this.historicalAsset = historicalAsset;
    }


    public HistoricalAssetValueChange getHistoricalChange()
    {

	return historicalChange;
    }


    public void setHistoricalChange(HistoricalAssetValueChange historicalChange)
    {

	this.historicalChange = historicalChange;
    }


    public HistoricalAssetReference getHistoricalAssetReference()
    {

	return historicalAssetReference;
    }


    public void setHistoricalAssetReference(HistoricalAssetReference historicalAssetReference)
    {

	this.historicalAssetReference = historicalAssetReference;
    }


    public HistoricalTemplateAttribute getHistoricalTemplateAttribute()
    {

	return historicalTemplateAttribute;
    }


    public void setHistoricalTemplateAttribute(HistoricalTemplateAttribute historicalTemplateAttribute)
    {

	this.historicalTemplateAttribute = historicalTemplateAttribute;
    }


    public HistoricalTemplateAttributeCollectionMapping getHistoricalTemplateAttributeCollectionMapping()
    {

	return historicalTemplateAttributeCollectionMapping;
    }


    public void setHistoricalTemplateAttributeCollectionMapping(HistoricalTemplateAttributeCollectionMapping historicalTemplateAttributeCollectionMapping)
    {

	this.historicalTemplateAttributeCollectionMapping = historicalTemplateAttributeCollectionMapping;
    }


    public Set<HistoricalExpressionParam> getExpressionParams()
    {

	return expressionParams;
    }


    public void setExpressionParams(Set<HistoricalExpressionParam> expressionParams)
    {

	this.expressionParams = expressionParams;
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
    public int compareTo(HistoricalAssetValue o)
    {

	return this.getNemonic().compareTo(o.getNemonic());

    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(alias, expressionParams, expressionProperties, historicalAsset, historicalAssetReference, historicalTemplateAttribute, historicalTemplateAttributeCollectionMapping, nodeId, value, valueLower, historicalChangeOperation);
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
	HistoricalAssetValue other = (HistoricalAssetValue) obj;
	return Objects.equals(alias, other.alias) && Objects.equals(expressionParams, other.expressionParams) && Objects.equals(expressionProperties, other.expressionProperties) && Objects.equals(historicalAsset, other.historicalAsset) && Objects.equals(historicalAssetReference, other.historicalAssetReference) && Objects.equals(historicalTemplateAttribute, other.historicalTemplateAttribute) && Objects.equals(historicalTemplateAttributeCollectionMapping, other.historicalTemplateAttributeCollectionMapping) && Objects.equals(nodeId, other.nodeId) && Objects.equals(value, other.value) && Objects.equals(valueLower, other.valueLower) && Objects.equals(historicalChangeOperation, other.historicalChangeOperation);
    }


    @Override
    public String toString()
    {

	return "HistoricalAssetValue [nodeId=" + nodeId + ", value=" + value + ", valueLower=" + valueLower + ", expressionProperties=" + expressionProperties + ", alias=" + alias + ", historicalAsset=" + historicalAsset + ", historicalAssetReference=" + historicalAssetReference + ", historicalTemplateAttribute=" + historicalTemplateAttribute + ", historicalTemplateAttributeCollectionMapping=" + historicalTemplateAttributeCollectionMapping + ", expressionParams=" + expressionParams + ", historicalChangeOperation=" + historicalChangeOperation + "]";
    }

}
