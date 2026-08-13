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


import es.cic.tessa.model.dto.support.AbstractEntityRequest;
import es.cic.tessa.model.types.OrganizerType;


public class OrganizerRequest extends AbstractEntityRequest
{

    private static final long serialVersionUID = 1L;

    private String type = OrganizerType.ASSET_ORGANIZER.getCode();

    private Long idAssetMetadata;

    private Long idParentOrganizer;

    public Long getIdAssetMetadata()
    {

	return idAssetMetadata;
    }


    public void setIdAssetMetadata(Long idAssetMetadata)
    {

	this.idAssetMetadata = idAssetMetadata;
    }


    public Long getIdParentOrganizer()
    {

	return idParentOrganizer;
    }


    public void setIdParentOrganizer(Long idParentOrganizer)
    {

	this.idParentOrganizer = idParentOrganizer;
    }


    public String getType()
    {

	return type;
    }


    public void setType(String type)
    {

	this.type = type;
    }

}
