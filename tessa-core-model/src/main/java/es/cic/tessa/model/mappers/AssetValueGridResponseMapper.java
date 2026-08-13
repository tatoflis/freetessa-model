package es.cic.tessa.model.mappers;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.AssetValue;
import es.cic.tessa.model.TemplateAttribute;
import es.cic.tessa.model.TemplateAttributeCollectionMapping;
import es.cic.tessa.model.dto.AssetValueResponse;
import es.cic.tessa.model.dto.TemplateAttributeCollectionMappingResponse;
import es.cic.tessa.model.dto.TemplateAttributeResponse;
import es.cic.tessa.model.dto.json.ResponsePageJson;


@Component
public class AssetValueGridResponseMapper
{

    @Autowired
    private TemplateAttributeResponseMapper templateAttributeResponseMapper;

    @Autowired
    @Lazy
    private AssetReferenceGridResponseMapper assetReferenceGridResponseMapper;

    @Autowired
    @Lazy
    private AssetGridResponseMapper assetGridResponseMapper;

    @Autowired
    @Lazy
    private AssetResponseMapper assetResponseMapper;

    @Autowired
    private TemplateAttributeCollectionMappingResponseMapper templateAttributeCollectionMappingResponseMapper;

    private final FunctionResponseMapper functionResponseMapper;

    private final ExpressionParamResponseMapper expressionParamResponseMapper;

    public AssetValueGridResponseMapper(FunctionResponseMapper functionResponseMapper, ExpressionParamResponseMapper expressionParamResponseMapper)
    {

	this.functionResponseMapper = functionResponseMapper;
	this.expressionParamResponseMapper = expressionParamResponseMapper;
    }


    public AssetValueResponse assetValueToAssetValueRespose(AssetValue assetValue)
    {

	AssetValueResponse assetValueResponse = new AssetValueResponse();

	assetValueResponse.setId(assetValue.getCustomId());
	assetValueResponse.setName(assetValue.getName());
	assetValueResponse.setDescription(assetValue.getDescription());
	assetValueResponse.setIcon(assetValue.getIcon());
	assetValueResponse.setInsertDate(assetValue.getInsertDate().toInstant(ZoneOffset.UTC));

	if(assetValueResponse.getModifDate() != null)
	{
	    assetValueResponse.setModifDate(assetValue.getModifDate().toInstant(ZoneOffset.UTC));
	}

	assetValueResponse.setVersion(assetValue.getVersion());
	assetValueResponse.setNemonic(assetValue.getNemonic());
	assetValueResponse.setValue(assetValue.getValue());
	assetValueResponse.setVersion(assetValue.getVersion());
	assetValueResponse.setIdBinary(assetValue.getIdBinary());

	if(assetValue.getExpressionProperties() != null)
	{
	    assetValueResponse.setExpressionProperties(functionResponseMapper.functionToFunctionResponse(assetValue.getExpressionProperties()));

	}

	if(assetValue.getAsset() != null)
	{
	    assetValueResponse.setAsset(assetResponseMapper.assetToAssetRespose(assetValue.getAsset()));
	}

	if(assetValue.getAssetReference() != null)
	{
	    assetValueResponse.setAssetReference(assetReferenceGridResponseMapper.assetReferenceToAssetReferenceRespose(assetValue.getAssetReference()));
	}

	if(assetValue.getTemplateAttribute() != null)
	{

	    TemplateAttributeResponse templateAttributeResponse = templateAttributeResponseMapper.templateAttributeToTemplateAttributeRespose(false, assetValue.getTemplateAttribute());
	    assetValueResponse.setTemplateAttribute(templateAttributeResponse);

	}
	if(assetValue.getTemplateAttributeCollectionMapping() != null)
	{
	    TemplateAttributeCollectionMappingResponse templateAttributeCollectionMappingResponse = templateAttributeCollectionMappingResponseMapper.templateAttributeCollectionMappingToTemplateAttributeCollectionMappingResponse(assetValue.getTemplateAttributeCollectionMapping());
	    assetValueResponse.setTemplateAttributeCollectionMapping(templateAttributeCollectionMappingResponse);
	}

	if(!CollectionUtils.isEmpty(assetValue.getExpressionParams()))
	{
	    assetValueResponse.setExpressionParams(expressionParamResponseMapper.expressionParamsToExpressionParamResponseCollection(assetValue.getExpressionParams()));
	}

	return assetValueResponse;
    }


    public ResponsePage<AssetValueResponse> assetValuePageToAssetValueResposePage(ResponsePage<AssetValue> assetValues)
    {

	List<AssetValueResponse> assetValuesResponse = new ArrayList<>();

	for (AssetValue assetValue : assetValues)
	{
	    assetValuesResponse.add(assetValueToAssetValueRespose(assetValue));

	}

	return new ResponsePage<>(assetValuesResponse, assetValues.getPageable(), assetValues.getTotalElements());

    }


    public Collection<AssetValueResponse> assetValueCollectionToAssetValueResposeCollection(Collection<AssetValue> assetValues)
    {

	Collection<AssetValueResponse> assetValuesResponse = new HashSet<>();

	for (AssetValue assetValue : assetValues)
	{
	    assetValuesResponse.add(assetValueToAssetValueRespose(assetValue));

	}

	return assetValuesResponse;

    }


    public AssetValue assetValueResponseToAssetValue(AssetValueResponse assetValueResponse)
    {

	AssetValue assetValue = new AssetValue();

	assetValue.setCustomId(assetValueResponse.getId());
	assetValue.setName(assetValueResponse.getName());
	assetValue.setDescription(assetValueResponse.getDescription());
	assetValue.setIcon(assetValueResponse.getIcon());
	assetValue.setInsertDate(LocalDateTime.ofInstant(assetValueResponse.getInsertDate(), ZoneOffset.UTC));

	if(assetValue.getModifDate() != null)
	{
	    assetValue.setModifDate(LocalDateTime.ofInstant(assetValueResponse.getModifDate(), ZoneOffset.UTC));
	}

	assetValue.setNemonic(assetValueResponse.getNemonic());
	assetValue.setValue(assetValueResponse.getValue());
	assetValue.setVersion(assetValueResponse.getVersion());

	if(assetValueResponse.getAsset() != null)
	{
	    assetValue.setAsset(assetResponseMapper.assetResponseToAsset(assetValueResponse.getAsset()));
	}

	if(assetValueResponse.getAssetReference() != null)
	{

	    assetValue.setAssetReference(assetReferenceGridResponseMapper.assetReferenceResponseToAssetReference(assetValueResponse.getAssetReference()));
	}

	if(assetValueResponse.getTemplateAttribute() != null)
	{

	    TemplateAttribute templateAttribute = templateAttributeResponseMapper.templateAttributeResponseToTemplateAttribute(assetValueResponse.getTemplateAttribute());
	    assetValue.setTemplateAttribute(templateAttribute);

	}
	if(assetValueResponse.getTemplateAttributeCollectionMapping() != null)
	{
	    TemplateAttributeCollectionMapping templateAttributeCollectionMapping = templateAttributeCollectionMappingResponseMapper.templateAttributeCollectionMappingResponseToTemplateAttributeCollectionMapping(assetValueResponse.getTemplateAttributeCollectionMapping());
	    assetValue.setTemplateAttributeCollectionMapping(templateAttributeCollectionMapping);
	}

	if(assetValue.getExpressionProperties() != null)
	{
	    assetValueResponse.setExpressionProperties(functionResponseMapper.functionToFunctionResponse(assetValue.getExpressionProperties()));

	}

	if(!CollectionUtils.isEmpty(assetValue.getExpressionParams()))
	{
	    assetValueResponse.setExpressionParams(expressionParamResponseMapper.expressionParamsToExpressionParamResponseCollection(assetValue.getExpressionParams()));
	}

	return assetValue;
    }


    public ResponsePage<AssetValue> assetValueResponsePageToAssetValuePage(ResponsePage<AssetValueResponse> assetValuesResponse)
    {

	Set<AssetValue> assetValues = new HashSet<>();

	for (AssetValueResponse assetValueResponse : assetValuesResponse)
	{
	    assetValues.add(assetValueResponseToAssetValue(assetValueResponse));
	}

	return new ResponsePage<>(assetValues, assetValuesResponse.getPageable(), assetValuesResponse.getTotalElements());

    }


    public ResponsePage<AssetValue> assetValueCollectionToAssetValueResposePage(ResponsePageJson<AssetValueResponse> assetValuesResponse)
    {

	List<AssetValue> assetValues = new ArrayList<>();

	PageRequest pageRequest = PageRequest.of(assetValuesResponse.getPageable().getPageNumber(), assetValuesResponse.getPageable().getPageSize());

	for (AssetValueResponse assetValueResponse : assetValuesResponse.getContent())
	{

	    AssetValue assetValue = assetValueResponseToAssetValue(assetValueResponse);

	    assetValues.add(assetValue);
	}

	return new ResponsePage<>(assetValues, pageRequest, assetValuesResponse.getTotalElements());

    }


    public List<AssetValue> assetValueResponseCollectionToAssetValueCollection(Collection<AssetValueResponse> assetValuesResponse)
    {

	List<AssetValue> assetValues = new ArrayList<>();

	for (AssetValueResponse assetValueResponse : assetValuesResponse)
	{
	    assetValues.add(assetValueResponseToAssetValue(assetValueResponse));

	}

	return assetValues;
    }

}
