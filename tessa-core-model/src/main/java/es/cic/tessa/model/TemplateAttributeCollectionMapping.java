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


@Node(Labels.TEMPLATE_ATTRIBUTE_COLLECTION_MAPPING)
public class TemplateAttributeCollectionMapping extends TessaElement implements Comparable<TemplateAttributeCollectionMapping>
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    protected Long nodeId;

    @Property(name = "hasCalculatedValue")
    private Boolean hasCalculatedValue = Boolean.FALSE;

    @Property(name = "calculatedValue")
    private String calculatedValue;

    @Relationship(type = Relations.MAPPING_RELATION, direction = Direction.INCOMING)
    private TemplateAttribute templateAttribute;

    @Property(name = "position")
    protected Integer position = Integer.valueOf(0);

    public TemplateAttributeCollectionMapping()
    {

    }


    public TemplateAttributeCollectionMapping(String name, Set<String> groups)
    {

	super(name, groups);

    }


    public Long getId()
    {

	return nodeId;
    }


    public void setId(Long id)
    {

	this.nodeId = id;
    }


    public String getCalculatedValue()
    {

	return calculatedValue;
    }


    public void setCalculatedValue(String calculatedValue)
    {

	this.calculatedValue = calculatedValue;
    }


    public TemplateAttribute getTemplateAttribute()
    {

	return templateAttribute;
    }


    public void setTemplateAttribute(TemplateAttribute templateAttribute)
    {

	this.templateAttribute = templateAttribute;
    }


    @Override
    public int compareTo(TemplateAttributeCollectionMapping assetTemplateAttributeCollectionMapping)
    {

	String thisName = getName();
	String otherName = assetTemplateAttributeCollectionMapping.getName();
	if(thisName == null && otherName == null) return 0;
	if(thisName == null) return -1;
	if(otherName == null) return 1;
	return thisName.compareTo(otherName);
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

}
