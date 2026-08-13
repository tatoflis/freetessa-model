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
import java.util.List;
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


@Node(Labels.TEMPLATE)
public class Template extends TessaElement
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

    @Relationship(type = Relations.EXTENDS_RELATION, direction = Direction.OUTGOING)
    private Template extendsTemplate;

    @Relationship(type = Relations.ATTRIBUTE_RELATION, direction = Direction.OUTGOING)
    private List<TemplateAttribute> templateAttributes = new ArrayList<TemplateAttribute>();

    @Relationship(type = Relations.CONTAINED_RELATION, direction = Direction.OUTGOING)
    private List<Organizer> organizers = new ArrayList<>();

    @Transient
    private Integer numComplexAttributes = Integer.valueOf(0);

    public Template()
    {

    }


    public Template(String name, String type, Set<String> groups)
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


    public Template getExtendsTemplate()
    {

	return extendsTemplate;
    }


    public void setExtendsTemplate(Template extendsTemplate)
    {

	this.extendsTemplate = extendsTemplate;
    }


    public List<Organizer> getOrganizers()
    {

	return organizers;
    }


    public void setOrganizers(List<Organizer> organizers)
    {

	this.organizers = organizers;
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


    public void addOrganizer(Organizer organizer)
    {

	getOrganizers().add(organizer);

    }


    public void addOrganizers(List<Organizer> organizers)
    {

	getOrganizers().addAll(organizers);

    }


    public void addTemplateAttribute(TemplateAttribute templateAttribute)
    {

	getTemplateAttributes().add(templateAttribute);

    }


    public void addTemplateAttributes(List<TemplateAttribute> templateAttributes)
    {

	getTemplateAttributes().addAll(templateAttributes);

    }


    public List<TemplateAttribute> getTemplateAttributes()
    {

	return templateAttributes;
    }


    public void setTemplateAttributes(List<TemplateAttribute> templateAttributes)
    {

	this.templateAttributes = templateAttributes;
    }


}
