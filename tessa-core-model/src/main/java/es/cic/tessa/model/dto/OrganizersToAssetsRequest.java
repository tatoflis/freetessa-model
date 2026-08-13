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


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class OrganizersToAssetsRequest
{

    private Collection<Long> assetsIds = new ArrayList<>();

    private List<OrganizerRequest> organizers = new ArrayList<>();

    public Collection<Long> getAssetsIds()
    {

	return assetsIds;
    }


    public void setAssetsIds(List<Long> assetsIds)
    {

	this.assetsIds = assetsIds;
    }


    public List<OrganizerRequest> getOrganizers()
    {

	return organizers;
    }


    public void setOrganizers(List<OrganizerRequest> organizers)
    {

	this.organizers = organizers;
    }

}
