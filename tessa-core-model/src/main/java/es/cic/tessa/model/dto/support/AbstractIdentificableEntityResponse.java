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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import es.cic.tessa.model.jsog.JSOGGenerator;


@JsonIdentityInfo(generator = JSOGGenerator.class, property = "@id")
@JsonInclude(Include.NON_NULL)
public abstract class AbstractIdentificableEntityResponse implements Serializable
{

    private static final long serialVersionUID = 1L;

    protected Long id;

    public Long getId()
    {

	return id;
    }


    public void setId(Long id)
    {

	this.id = id;
    }

}
