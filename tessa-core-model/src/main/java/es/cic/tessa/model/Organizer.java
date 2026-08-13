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


import java.util.Objects;
import java.util.Set;
import org.springframework.data.annotation.Transient;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import es.cic.tessa.common.model.Labels;
import es.cic.tessa.common.model.Relations;
import es.cic.tessa.model.support.TessaElement;
import es.cic.tessa.model.types.OrganizerType;


@Node(Labels.ORGANIZER)
public class Organizer extends TessaElement
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long nodeId;

    @Property(name = "type")
    private String organizerType = OrganizerType.ASSET_ORGANIZER.getCode();

    @Property(name = "path")
    private String path;

    @Relationship(type = Relations.METADATA_RELATION, direction = Direction.OUTGOING)
    private Asset metadata;

    @Relationship(type = Relations.PARENT_RELATION, direction = Direction.OUTGOING)
    private Organizer parentOrganizer;

    @Transient
    private Integer numElements;

    @Transient
    private Integer numOrganizers;

    public Organizer()
    {

    }


    public Organizer(String name, Set<String> groups)
    {

	super(name, groups);
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


    public String getOrganizerType()
    {

	return organizerType;
    }


    public void setOrganizerType(String organizerType)
    {

	this.organizerType = organizerType;
    }


    public String getPath()
    {

	return path;
    }


    public void setPath(String path)
    {

	this.path = path;
    }


    @Override
    public String getNemonic()
    {

	return super.getNemonic();
    }


    @Deprecated
    @Override
    public void setNemonic(String nemonic)
    {

	super.setNemonic(nemonic);
    }


    public Asset getMetadata()
    {

	return metadata;
    }


    public void setMetadata(Asset metadata)
    {

	this.metadata = metadata;
    }


    public Integer getNumElements()
    {

	return numElements;
    }


    public void setNumElements(Integer numElements)
    {

	this.numElements = numElements;
    }


    public Integer getNumOrganizers()
    {

	return numOrganizers;
    }


    public void setNumOrganizers(Integer numOrganizers)
    {

	this.numOrganizers = numOrganizers;
    }


    public Organizer getParentOrganizer()
    {

	return parentOrganizer;
    }


    public void setParentOrganizer(Organizer parentOrganizer)
    {

	this.parentOrganizer = parentOrganizer;
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(nodeId, nemonic);
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
	Organizer other = (Organizer) obj;
	return Objects.equals(nemonic, other.nemonic) && Objects.equals(nodeId, other.nodeId);
    }

}
