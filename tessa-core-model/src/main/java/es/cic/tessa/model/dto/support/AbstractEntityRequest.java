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


import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class AbstractEntityRequest implements Serializable
{

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    protected Long nodeId;

    protected Long id;

    protected Long version;

    protected String name;

    protected String description;

    protected String icon;

    protected LocalDateTime modifDate;

    public Long getId()
    {

	return id;
    }


    public void setId(Long id)
    {

	this.id = id;
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


    public LocalDateTime getModifDate()
    {

	return modifDate;
    }


    public void setModifDate(LocalDateTime modifDate)
    {

	this.modifDate = modifDate;
    }


    @Override
    public String toString()
    {

	return "AbstractEntityRequest [name=" + name + "]";
    }


    public Long getVersion()
    {

	return version;
    }


    public void setVersion(Long version)
    {

	this.version = version;
    }


    public Long getNodeId()
    {

	return nodeId;
    }


    public void setNodeId(Long nodeId)
    {

	this.nodeId = nodeId;
    }

}
