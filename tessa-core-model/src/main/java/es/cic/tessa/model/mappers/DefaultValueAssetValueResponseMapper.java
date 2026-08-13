package es.cic.tessa.model.mappers;


import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.DefaultValueAssetValue;
import es.cic.tessa.model.dto.DefaultValueAssetValueResponse;


@Component
public class DefaultValueAssetValueResponseMapper
{

    public DefaultValueAssetValueResponse defaultValueAssetValueToDefaultValueAssetValueRespose(DefaultValueAssetValue defaultValueAssetValue)
    {

	DefaultValueAssetValueResponse defaultValueAssetValueResponse = new DefaultValueAssetValueResponse();

	defaultValueAssetValueResponse.setId(defaultValueAssetValue.getCustomId());
	defaultValueAssetValueResponse.setName(defaultValueAssetValue.getName());
	defaultValueAssetValueResponse.setDescription(defaultValueAssetValue.getDescription());

	defaultValueAssetValueResponse.setVersion(defaultValueAssetValue.getVersion());
	defaultValueAssetValueResponse.setNemonic(defaultValueAssetValue.getNemonic());
	defaultValueAssetValueResponse.setValue(defaultValueAssetValue.getValue());
	defaultValueAssetValueResponse.setVersion(defaultValueAssetValue.getVersion());

	return defaultValueAssetValueResponse;
    }


    public ResponsePage<DefaultValueAssetValueResponse> defaultValueAssetValuePageToDefaultValueAssetValueResposePage(ResponsePage<DefaultValueAssetValue> defaultValueAssetValues)
    {

	List<DefaultValueAssetValueResponse> defaultValueAssetValueResponses = new ArrayList<>();

	for (DefaultValueAssetValue defaultValueAssetValue : defaultValueAssetValues)
	{
	    defaultValueAssetValueResponses.add(defaultValueAssetValueToDefaultValueAssetValueRespose(defaultValueAssetValue));

	}

	return new ResponsePage<>(defaultValueAssetValueResponses, defaultValueAssetValues.getPageable(), defaultValueAssetValues.getTotalElements());

    }

}
