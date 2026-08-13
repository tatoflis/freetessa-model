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
package es.cic.tessa.model.dto;


import java.util.Objects;
import es.cic.tessa.model.dto.support.AbstractEntityResponse;
import es.cic.tessa.model.types.OrganizerType;


public class OrganizerResponse extends AbstractEntityResponse
{

    private static final long serialVersionUID = 1L;

    private String type = OrganizerType.ASSET_ORGANIZER.getCode();

    private AssetResponse metadata;

    private OrganizerResponse parentOrganizer;

    private Integer numElements;

    private Integer numOrganizers;

    private String path;

    public String getPath()
    {

	return path;
    }


    public void setPath(String path)
    {

	this.path = path;
    }


    public AssetResponse getMetadata()
    {

	return metadata;
    }


    public void setMetadata(AssetResponse metadata)
    {

	this.metadata = metadata;
    }


    public OrganizerResponse getParentOrganizer()
    {

	return parentOrganizer;
    }


    public void setParentOrganizer(OrganizerResponse parentOrganizer)
    {

	this.parentOrganizer = parentOrganizer;
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


    public String getType()
    {

	return type;
    }


    public void setType(String type)
    {

	this.type = type;
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(metadata, numElements, numOrganizers, parentOrganizer, type);
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
	OrganizerResponse other = (OrganizerResponse) obj;
	return Objects.equals(metadata, other.metadata) && Objects.equals(numElements, other.numElements) && Objects.equals(numOrganizers, other.numOrganizers) && Objects.equals(parentOrganizer, other.parentOrganizer) && Objects.equals(type, other.type);
    }


    @Override
    public String toString()
    {

	return "OrganizerResponse [type=" + type + ", metadata=" + metadata + ", parentOrganizer=" + parentOrganizer + ", numElements=" + numElements + ", numOrganizers=" + numOrganizers + "]";
    }

}
