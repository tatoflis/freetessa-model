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
package es.cic.tessa.model.dto.support;


import java.time.Instant;
import java.util.Objects;
import java.util.Set;


public abstract class AbstractEntityResponse extends AbstractIdentificableEntityResponse
{

    private static final long serialVersionUID = 1L;

    protected Long version;
    protected String name;
    protected String description;
    protected String icon;
    protected Instant insertDate;
    protected Instant modifDate;
    protected String nemonic;
    protected Set<String> groups;

    public Set<String> getGroups()
    {

	return groups;
    }


    public void setGroups(Set<String> groups)
    {

	this.groups = groups;
    }


    public Instant getInsertDate()
    {

	return insertDate;
    }


    public void setInsertDate(Instant insertDate)
    {

	this.insertDate = insertDate;
    }


    public Instant getModifDate()
    {

	return modifDate;
    }


    public void setModifDate(Instant modifDate)
    {

	this.modifDate = modifDate;
    }


    public Long getVersion()
    {

	return version;
    }


    public void setVersion(Long version)
    {

	this.version = version;
    }


    public String getNemonic()
    {

	return nemonic;
    }


    public void setNemonic(String nemonic)
    {

	this.nemonic = nemonic;
    }


    public String getName()
    {

	return name;
    }


    public void setName(String name)
    {

	this.name = name;
    }


    public String getDescription()
    {

	return description;
    }


    public void setDescription(String description)
    {

	this.description = description;
    }


    public String getIcon()
    {

	return icon;
    }


    public void setIcon(String icon)
    {

	this.icon = icon;
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(id, name, nemonic);
    }


    @Override
    public boolean equals(Object obj)
    {

	if(this == obj)
	    return true;
	if(obj == null)
	    return false;
	if(!(obj instanceof AbstractEntityResponse))
	    return false;
	AbstractEntityResponse other = (AbstractEntityResponse) obj;
	return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(nemonic, other.nemonic);
    }


    @Override
    public String toString()
    {

	return "AbstractEntityResponse [id=" + id + ", name=" + name + ", nemonic=" + nemonic + ", groups=" + groups + "]";
    }

}
