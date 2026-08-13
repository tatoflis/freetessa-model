package es.cic.tessa.model.mappers;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;
import es.cic.tessa.model.ExpressionParam;
import es.cic.tessa.model.dto.ExpressionParamRequest;


@Component
public class ExpressionParamRequestMapper
{

    private DefaultValueAssetValueRequestMapper defaultValueAssetValueRequestMapper;

    public ExpressionParamRequestMapper(DefaultValueAssetValueRequestMapper defaultValueAssetValueRequestMapper)
    {

	this.defaultValueAssetValueRequestMapper = defaultValueAssetValueRequestMapper;
    }


    public ExpressionParam expressionParamRequestToExpressionParam(ExpressionParamRequest expressionParamRequest, Set<String> groups)
    {

	ExpressionParam expressionParam = new ExpressionParam();

	if(expressionParamRequest.getId() != null)
	{
	    expressionParam.setId(expressionParamRequest.getId());
	}

	expressionParam.setName(expressionParamRequest.getName());
	expressionParam.setType(expressionParamRequest.getType());
	expressionParam.setRequired(expressionParamRequest.isRequired());
	expressionParam.setPosition(expressionParamRequest.getPosition());
	expressionParam.setDescription(expressionParamRequest.getDescription());
	expressionParam.setIcon(expressionParamRequest.getIcon());
	expressionParam.setGroups(groups);

	if(expressionParamRequest.getVersion() != null)
	{
	    expressionParam.setVersion(expressionParamRequest.getVersion());
	}

	if(expressionParamRequest.getDefaultValueAssetValueRequest() != null)
	{
	    expressionParam.setDefaultValueAssetValue(defaultValueAssetValueRequestMapper.defaultValueRequestToDefaultValue(expressionParamRequest.getDefaultValueAssetValueRequest(), groups));
	}

	if(expressionParamRequest.getModifDate() != null)
	{
	    expressionParam.setModifDate(expressionParamRequest.getModifDate());
	}

	return expressionParam;
    }


    public Set<ExpressionParam> expressionParamsRequestToExpressionParams(Collection<ExpressionParamRequest> expressionParamRequests, Set<String> groups)
    {

	Set<ExpressionParam> expressionParams = new HashSet<>();

	for (ExpressionParamRequest expressionParamRequest : expressionParamRequests)
	{
	    expressionParams.add(expressionParamRequestToExpressionParam(expressionParamRequest, groups));
	}

	return expressionParams;
    }


    public ExpressionParamRequest expressionParamToExpressionParamRequest(ExpressionParam expressionParam)
    {

	ExpressionParamRequest expressionParamRequest = new ExpressionParamRequest();

	if(expressionParam.getCustomId() != null)
	{
	    expressionParamRequest.setId(expressionParam.getCustomId());

	}

	expressionParamRequest.setName(expressionParam.getName());
	expressionParamRequest.setType(expressionParam.getType());
	expressionParamRequest.setRequired(expressionParam.getRequired());
	expressionParamRequest.setPosition(expressionParam.getPosition());

	if(expressionParam.getVersion() != null)
	{
	    expressionParamRequest.setVersion(expressionParam.getVersion());
	}

	return expressionParamRequest;
    }


    public Set<ExpressionParamRequest> expressionParamsToExpressionParamsRequest(Collection<ExpressionParam> expressionParams)
    {

	Set<ExpressionParamRequest> expressionParamRequests = new HashSet<>();

	for (ExpressionParam expressionParam : expressionParams)
	{
	    expressionParamRequests.add(expressionParamToExpressionParamRequest(expressionParam));
	}

	return expressionParamRequests;
    }
}
