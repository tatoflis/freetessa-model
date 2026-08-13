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
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.Asset;
import es.cic.tessa.model.AssetValue;
import es.cic.tessa.model.TemplateAttribute;
import es.cic.tessa.model.TemplateAttributeCollectionMapping;
import es.cic.tessa.model.dto.AssetValueRequest;


@Component
public class AssetValueRequestMapper
{

    private final FunctionRequestMapper functionRequestMapper;
    private final ExpressionParamRequestMapper expressionParamRequestMapper;

    public AssetValueRequestMapper(FunctionRequestMapper functionRequestMapper, ExpressionParamRequestMapper expressionParamRequestMapper)
    {

	this.functionRequestMapper = functionRequestMapper;
	this.expressionParamRequestMapper = expressionParamRequestMapper;
    }


    public AssetValue assetValueRequestToAssetValue(AssetValueRequest assetValueRequest, Set<String> groups)
    {

	AssetValue assetValue = new AssetValue();

	assetValue.setCustomId(assetValueRequest.getId());
	assetValue.setName(assetValueRequest.getName());
	assetValue.setNameLower(assetValueRequest.getName().toLowerCase());
	assetValue.setDescription(assetValueRequest.getDescription());
	assetValue.setIcon(assetValueRequest.getIcon());
	assetValue.setGroups(groups);
	if(assetValueRequest.getVersion() != null)
	{
	    assetValue.setVersion(assetValueRequest.getVersion());
	}

	if(assetValueRequest.getValue() != null)
	{
	    assetValue.setValue(assetValueRequest.getValue());
	}

	if(assetValueRequest.getExpressionProperties() != null)
	{
	    assetValue.setExpressionProperties(functionRequestMapper.functionRequestToFunction(assetValueRequest.getExpressionProperties()));
	}

	if(assetValueRequest.getIdBinary() != null)
	{
	    assetValue.setIdBinary(assetValueRequest.getIdBinary());

	}

	if(assetValueRequest.getIdAsset() != null)
	{
	    Asset asset = new Asset();
	    asset.setCustomId(assetValueRequest.getIdAsset());
	    assetValue.setAsset(asset);
	}

	if(assetValueRequest.getIdTemplateAttribute() != null)
	{
	    TemplateAttribute templateAttribute = new TemplateAttribute();
	    templateAttribute.setId(assetValueRequest.getIdTemplateAttribute());

	    assetValue.setTemplateAttribute(templateAttribute);
	}

	if(assetValueRequest.getIdTemplateAttributeCollectionMapping() != null)
	{
	    TemplateAttributeCollectionMapping templateAttributeCollectionMapping = new TemplateAttributeCollectionMapping();
	    templateAttributeCollectionMapping.setId(assetValueRequest.getIdTemplateAttributeCollectionMapping());

	    assetValue.setTemplateAttributeCollectionMapping(templateAttributeCollectionMapping);
	}

	if(assetValueRequest.getModifDate() != null)
	{
	    assetValue.setModifDate(assetValueRequest.getModifDate());
	}

	if(!CollectionUtils.isEmpty(assetValueRequest.getExpressionParams()))
	{
	    assetValue.setExpressionParams(expressionParamRequestMapper.expressionParamsRequestToExpressionParams(assetValueRequest.getExpressionParams(), groups));
	}

	return assetValue;
    }


    public AssetValueRequest assetValueToAssetValueRequest(AssetValue assetValue)
    {

	AssetValueRequest assetValueRequest = new AssetValueRequest();

	assetValueRequest.setId(assetValue.getCustomId());
	assetValueRequest.setName(assetValue.getName());
	assetValueRequest.setDescription(assetValue.getDescription());
	assetValueRequest.setIcon(assetValue.getIcon());

	if(assetValue.getVersion() != null)
	{
	    assetValueRequest.setVersion(assetValue.getVersion());
	}

	if(assetValue.getValue() != null)
	{
	    assetValueRequest.setValue(assetValue.getValue());
	}

	if(assetValue.getExpressionProperties() != null)
	{
	    assetValueRequest.setExpressionProperties(functionRequestMapper.functionToFunctionRequest(assetValue.getExpressionProperties()));
	}

	if(assetValue.getAsset() != null)
	{
	    assetValueRequest.setIdAsset(assetValue.getAsset().getCustomId());
	}

	if(assetValue.getTemplateAttribute() != null)
	{
	    assetValueRequest.setIdTemplateAttribute(assetValue.getTemplateAttribute().getCustomId());
	}

	if(assetValue.getTemplateAttributeCollectionMapping() != null)
	{
	    assetValueRequest.setIdTemplateAttributeCollectionMapping(assetValue.getTemplateAttributeCollectionMapping().getCustomId());
	}

	if(assetValue.getAssetReference() != null)
	{
	    assetValueRequest.setIdAssetReference(assetValue.getAssetReference().getAsset().getCustomId());
	}

	if(!CollectionUtils.isEmpty(assetValue.getExpressionParams()))
	{

	    assetValueRequest.setExpressionParams(expressionParamRequestMapper.expressionParamsToExpressionParamsRequest(assetValue.getExpressionParams()));
	}

	return assetValueRequest;
    }


    public ResponsePage<AssetValue> assetValueRequestPageToAssetValuePage(ResponsePage<AssetValueRequest> assetValuesRequest, Set<String> groups)
    {

	List<AssetValue> assetValues = new ArrayList<>();

	for (AssetValueRequest assetValueRequest : assetValuesRequest)
	{
	    assetValues.add(assetValueRequestToAssetValue(assetValueRequest, groups));
	}

	return new ResponsePage<>(assetValues, assetValuesRequest.getPageable(), assetValuesRequest.getTotalElements());

    }


    public Collection<AssetValueRequest> assetValueCollectionToAssetValueRequestCollection(Collection<AssetValue> assetValues)
    {

	List<AssetValueRequest> assetValuesRequest = new ArrayList<>();

	for (AssetValue assetValue : assetValues)
	{
	    assetValuesRequest.add(assetValueToAssetValueRequest(assetValue));
	}

	return assetValuesRequest;

    }

}
