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
package es.cic.tessa.model.mappers;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.ExpressionParam;
import es.cic.tessa.model.dto.ExpressionParamResponse;
import es.cic.tessa.model.dto.json.ResponsePageJson;


@Component
public class ExpressionParamResponseMapper
{

    private AssetValueDefaultValueResponseMapper assetValueDefaultValueResponseMapper;

    public ExpressionParamResponseMapper(AssetValueDefaultValueResponseMapper assetValueDefaultValueResponseMapper)
    {

	this.assetValueDefaultValueResponseMapper = assetValueDefaultValueResponseMapper;
    }


    public ExpressionParamResponse expressionParamToExpressionParamResponse(ExpressionParam expressionParam)
    {

	ExpressionParamResponse expressionParamResponse = new ExpressionParamResponse();

	expressionParamResponse.setId(expressionParam.getCustomId());
	expressionParamResponse.setName(expressionParam.getName());
	expressionParamResponse.setType(expressionParam.getType());
	expressionParamResponse.setRequired(expressionParam.getRequired());

	if(expressionParam.getDefaultValueAssetValue() != null)
	{
	    expressionParamResponse.setDefaultValueAssetValueResponse(assetValueDefaultValueResponseMapper.defaulValueAssetValueToDefaultValueAssetValueResponse(expressionParam.getDefaultValueAssetValue()));
	}
	expressionParamResponse.setPosition(expressionParam.getPosition());
	expressionParamResponse.setVersion(expressionParam.getVersion());

	return expressionParamResponse;
    }


    public ExpressionParam expressionParamResponseToExpressionParam(ExpressionParamResponse expressionParamResponse)
    {

	ExpressionParam expressionParam = new ExpressionParam();

	expressionParam.setId(expressionParamResponse.getId());
	expressionParam.setName(expressionParamResponse.getName());
	expressionParam.setType(expressionParamResponse.getType());
	expressionParam.setRequired(expressionParamResponse.getRequired());

	if(expressionParamResponse.getDefaultValueAssetValueResponse() != null)
	{
	    expressionParam.setDefaultValueAssetValue(assetValueDefaultValueResponseMapper.defaultValueAssetValueResponseToDefaultValueAssetValue(expressionParamResponse.getDefaultValueAssetValueResponse()));
	}
	expressionParam.setPosition(expressionParamResponse.getPosition());
	expressionParam.setVersion(expressionParamResponse.getVersion());

	return expressionParam;
    }


    public Set<ExpressionParam> expressionParamCollectionResponseToExpressionParamCollection(Collection<ExpressionParamResponse> expressionParamsResponse)
    {

	Set<ExpressionParam> expressionParams = new HashSet<>();

	for (ExpressionParamResponse expressionParamResponse : expressionParamsResponse)
	{
	    expressionParams.add(expressionParamResponseToExpressionParam(expressionParamResponse));
	}

	return expressionParams;

    }


    public Collection<ExpressionParamResponse> expressionParamsToExpressionParamResponseCollection(Collection<ExpressionParam> expressionParams)
    {

	Collection<ExpressionParamResponse> expressionParamsResponse = new ArrayList<>();

	for (ExpressionParam expressionParam : expressionParams)
	{
	    expressionParamsResponse.add(expressionParamToExpressionParamResponse(expressionParam));
	}

	return expressionParamsResponse;
    }


    public ResponsePage<ExpressionParamResponse> expressionParamToExpressionParamResponsePage(ResponsePage<ExpressionParam> expressionParams)
    {

	List<ExpressionParamResponse> expressionParamResponses = new ArrayList<>();

	for (ExpressionParam expressionParam : expressionParams)
	{
	    expressionParamResponses.add(expressionParamToExpressionParamResponse(expressionParam));
	}

	return new ResponsePage<>(expressionParamResponses, expressionParams.getPageable(), expressionParams.getTotalElements());
    }


    public ResponsePage<ExpressionParamResponse> expresionParamsToExpressionParamsResponsePage(Collection<ExpressionParam> expressionParams)
    {

	List<ExpressionParamResponse> expressionParamResponseList = new ArrayList<>();

	for (ExpressionParam expressionParam : expressionParams)
	{
	    expressionParamResponseList.add(expressionParamToExpressionParamResponse(expressionParam));
	}

	return new ResponsePage<>(expressionParamResponseList);
    }


    public ResponsePage<ExpressionParam> expressionParamResponseToExpressionParamResponsePage(ResponsePageJson<ExpressionParamResponse> templateAttributeExpressionParamResponse)
    {

	List<ExpressionParam> expressionParamList = new ArrayList<>();

	PageRequest pageRequest = PageRequest.of(templateAttributeExpressionParamResponse.getPageable().getPageNumber(), templateAttributeExpressionParamResponse.getPageable().getPageSize());

	for (ExpressionParamResponse expressionParamResponse : templateAttributeExpressionParamResponse.getContent())
	{

	    ExpressionParam expressionParam = expressionParamResponseToExpressionParam(expressionParamResponse);

	    expressionParamList.add(expressionParam);
	}

	return new ResponsePage<>(expressionParamList, pageRequest, templateAttributeExpressionParamResponse.getTotalElements());

    }
}
