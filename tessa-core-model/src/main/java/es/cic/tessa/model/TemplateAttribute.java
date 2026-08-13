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


import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
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


@Node(Labels.TEMPLATE_ATTRIBUTE)
public class TemplateAttribute extends TessaElement implements Comparable<TemplateAttribute>
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
    private Function expressionProperties = new Function();

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
    private List<Hashtag> hashtags = new ArrayList<>();

    @Relationship(type = Relations.MAPPING_RELATION, direction = Direction.OUTGOING)
    private SortedSet<TemplateAttributeCollectionMapping> templateAttributeCollectionMapping = new TreeSet<>();

    @Relationship(type = Relations.TEMPLATE_REFERENCE_RELATION, direction = Direction.OUTGOING)
    private TemplateReference templateReference;

    @Relationship(type = Relations.EXPRESSION_RELATION, direction = Direction.OUTGOING)
    private Set<ExpressionParam> expressionParams = new HashSet<>();

    @Relationship(type = Relations.ATTRIBUTE_RELATION, direction = Direction.INCOMING)
    private Template template;

    @Property(name = "position")
    protected Integer position = Integer.valueOf(0);

    public Template getTemplate()
    {

	return template;
    }


    public void setTemplate(Template template)
    {

	this.template = template;
    }


    public TemplateAttribute()
    {

    }


    public TemplateAttribute(String name, String type, Set<String> groups)
    {

	super(name, groups);
	this.type = type;
    }


    @Override
    public Long getId()
    {

	return nodeId;
    }


    public void setId(Long id)
    {

	this.nodeId = id;
    }


    public Boolean getIdentificable()
    {

	return identificable;
    }


    public void setIdentificable(Boolean identificable)
    {

	this.identificable = identificable;
    }


    public String getType()
    {

	return type;
    }


    public Boolean getAlias()
    {

	return alias;
    }


    public void setAlias(Boolean alias)
    {

	this.alias = alias;
    }


    public void setType(String type)
    {

	this.type = type;
    }


    public Boolean getFinalAttribute()
    {

	return finalAttribute;
    }


    public void setFinalAttribute(Boolean finalAttribute)
    {

	this.finalAttribute = finalAttribute;
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


    public Boolean getHasDefaultValue()
    {

	return hasDefaultValue;
    }


    public void setHasDefaultValue(Boolean hasDefaultValue)
    {

	this.hasDefaultValue = hasDefaultValue;
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


    public SortedSet<TemplateAttributeCollectionMapping> getTemplateAttributeCollectionMapping()
    {

	return templateAttributeCollectionMapping;
    }


    public void setTemplateAttributeCollectionMapping(SortedSet<TemplateAttributeCollectionMapping> templateAttributeCollectionMapping)
    {

	this.templateAttributeCollectionMapping = templateAttributeCollectionMapping;
    }


    public TemplateReference getTemplateReference()
    {

	return templateReference;
    }


    public void setTemplateReference(TemplateReference templateReference)
    {

	this.templateReference = templateReference;
    }


    public String getCalculatedValue()
    {

	return calculatedValue;
    }


    public void setCalculatedValue(String calculatedValue)
    {

	this.calculatedValue = calculatedValue;
    }


    public String getDefaultValue()
    {

	return defaultValue;
    }


    public void setDefaultValue(String defaultValue)
    {

	this.defaultValue = defaultValue;
    }


    public List<Hashtag> getHashtags()
    {

	return hashtags;
    }


    public void setHashtags(List<Hashtag> hashtags)
    {

	this.hashtags = hashtags;
    }


    public void addTemplateAttributeCollectionMapping(TemplateAttributeCollectionMapping templateAttributeCollectionMapping)
    {

	getTemplateAttributeCollectionMapping().add(templateAttributeCollectionMapping);

    }


    public void addTemplateAttributeCollectionMappings(Collection<TemplateAttributeCollectionMapping> templateAttributeCollectionMappings)
    {

	getTemplateAttributeCollectionMapping().addAll(templateAttributeCollectionMappings);

    }


    public void addHashtag(Hashtag hashtag)
    {

	getHashtags().add(hashtag);

    }


    public void addHashtags(List<Hashtag> hashtags)
    {

	getHashtags().addAll(hashtags);

    }


    @Override
    public int compareTo(TemplateAttribute assetTemplateAttribute)
    {

	return Comparator.comparing(TemplateAttribute::getName).compare(this, assetTemplateAttribute);

    }


    public Integer getPosition()
    {

	return position;
    }


    public void setPosition(Integer position)
    {

	this.position = position;
    }


    public Boolean getHasCalculatedValue()
    {

	return hasCalculatedValue;
    }


    public void setHasCalculatedValue(Boolean hasCalculatedValue)
    {

	this.hasCalculatedValue = hasCalculatedValue;
    }


    public Function getExpressionProperties()
    {

	return expressionProperties;
    }


    public void setExpressionProperties(Function function)
    {

	this.expressionProperties = function;
    }


    public Boolean getPassword()
    {

	return password;
    }


    public void setPassword(Boolean password)
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


    public Set<ExpressionParam> getExpressionParams()
    {

	return expressionParams;
    }


    public void setExpressionParams(Set<ExpressionParam> expressionParams)
    {

	this.expressionParams = expressionParams;
    }


    @Override
    public String toString()
    {

	return "TemplateAttribute [name=" + name + ", nemonic=" + nemonic + ", nodeId=" + nodeId + ", identificable=" + identificable + ", alias=" + alias + ", type=" + type + ", minLength=" + minLength + ", maxLength=" + maxLength + ", required=" + required + ", hidden=" + hidden + ", hasDefaultValue=" + hasDefaultValue + ", defaultValue=" + defaultValue + ", hasCalculatedValue=" + hasCalculatedValue + ", calculatedValue=" + calculatedValue + ", expressionProperties=" + expressionProperties + ", pattern=" + pattern + ", unique=" + unique + ", externalSource=" + externalSource + ", collection=" + collection + ", withcapacity=" + withcapacity + ", capacity=" + capacity + ", mapping=" + mapping + ", finalAttribute=" + finalAttribute + ", password=" + password + ", contentType=" + contentType + ", hashtags=" + hashtags + ", templateAttributeCollectionMapping=" + templateAttributeCollectionMapping + ", templateReference=" + templateReference + ", expressionParams=" + expressionParams + ", template=" + template + ", position=" + position + "]";
    }

}
