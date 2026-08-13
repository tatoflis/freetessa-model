/*
 * Copyright [2019] "CIC Consulting"
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 *
 */
package es.cic.tessa.model;


import java.util.HashSet;
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


@Node(Labels.ASSET_VALUE)
public class AssetValue extends TessaElement
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
    private Function expressionProperties = new Function();

    @Property(name = "idBinary")
    private String idBinary;

    @Property(name = AssetValueProperties.ALIAS)
    private Integer alias = Integer.valueOf(0);

    @Relationship(type = Relations.HAS_VALUE_RELATION, direction = Direction.INCOMING)
    private Asset asset;

    // Reference value defined by template type
    @Relationship(type = Relations.ASSET_REFERENCE_RELATION, direction = Direction.OUTGOING)
    private AssetReference assetReference;

    // if value is related with attribute template
    @Relationship(type = Relations.VALUE_TEMPLATE_RELATION, direction = Direction.OUTGOING)
    private TemplateAttribute templateAttribute;

    @Property(name = "attributeNameLower")
    private String attributeNameLower;

    // if the value is related with collection mapped in template
    @Relationship(type = Relations.VALUE_TEMPLATE_MAPPED_RELATION, direction = Direction.OUTGOING)
    private TemplateAttributeCollectionMapping templateAttributeCollectionMapping;

    @Relationship(type = Relations.EXPRESSION_RELATION, direction = Direction.OUTGOING)
    private Set<ExpressionParam> expressionParams = new HashSet<>();

    public AssetValue()
    {

    }


    public AssetValue(Asset asset, TemplateAttribute templateAttribute, String value, Set<String> groups)
    {

	super(value, groups);
	this.asset = asset;
	this.templateAttribute = templateAttribute;
	this.attributeNameLower = templateAttribute.getNameLower();

	this.value = value;

	if(value != null)
	{
	    setValueLower(value.toLowerCase());
	}
    }


    public AssetValue(Asset asset, TemplateAttribute templateAttribute, TemplateAttributeCollectionMapping templateAttributeCollectionMapping, String value, Set<String> groups)
    {

	super(value, groups);
	this.asset = asset;
	this.templateAttribute = templateAttribute;
	this.templateAttributeCollectionMapping = templateAttributeCollectionMapping;
	this.value = value;
	this.attributeNameLower = templateAttribute.getNameLower();

	if(value != null)
	{
	    setValueLower(value.toLowerCase());
	}
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


    public Asset getAsset()
    {

	return asset;
    }


    public void setAsset(Asset asset)
    {

	this.asset = asset;
    }


    public String getValue()
    {

	return value;
    }


    public void setValue(String value)
    {

	this.value = value;

	if(value != null)
	{
	    setValueLower(value.toLowerCase());
	}
    }


    public String getValueLower()
    {

	return valueLower;
    }


    public void setValueLower(String valueLower)
    {

	this.valueLower = valueLower;
    }


    public TemplateAttribute getTemplateAttribute()
    {

	return templateAttribute;
    }


    public void setTemplateAttribute(TemplateAttribute templateAttribute)
    {

	this.templateAttribute = templateAttribute;
    }


    public TemplateAttributeCollectionMapping getTemplateAttributeCollectionMapping()
    {

	return templateAttributeCollectionMapping;
    }


    public void setTemplateAttributeCollectionMapping(TemplateAttributeCollectionMapping templateAttributeCollectionMapping)
    {

	this.templateAttributeCollectionMapping = templateAttributeCollectionMapping;
    }


    public Set<ExpressionParam> getExpressionParams()
    {

	return expressionParams;
    }


    public void setExpressionParams(Set<ExpressionParam> expressionParams)
    {

	this.expressionParams = expressionParams;
    }


    public AssetReference getAssetReference()
    {

	return assetReference;
    }


    public void setAssetReference(AssetReference assetReference)
    {

	this.assetReference = assetReference;
    }


    public String getIdBinary()
    {

	return idBinary;
    }


    public void setIdBinary(String idBinary)
    {

	this.idBinary = idBinary;
    }


    public Integer getAlias()
    {

	return alias;
    }


    public void setAlias(Integer alias)
    {

	this.alias = alias;
    }


    public Function getExpressionProperties()
    {

	return expressionProperties;
    }


    public void setExpressionProperties(Function function)
    {

	this.expressionProperties = function;
    }


    public Long getNodeId()
    {

	return nodeId;
    }


    public void setNodeId(Long nodeId)
    {

	this.nodeId = nodeId;
    }


    public String getAttributeNameLower()
    {

	return attributeNameLower;
    }


    public void setAttributeNameLower(String attributeNameLower)
    {

	this.attributeNameLower = attributeNameLower;
    }

}
