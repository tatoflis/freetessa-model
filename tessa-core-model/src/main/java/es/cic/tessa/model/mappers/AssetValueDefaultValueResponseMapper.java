package es.cic.tessa.model.mappers;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;
import es.cic.tessa.model.DefaultValueAssetValue;
import es.cic.tessa.model.dto.DefaultValueAssetValueResponse;


@Component
public class AssetValueDefaultValueResponseMapper
{

    public DefaultValueAssetValueResponse defaulValueAssetValueToDefaultValueAssetValueResponse(DefaultValueAssetValue defaultValueAssetValue)
    {

	DefaultValueAssetValueResponse defaultValueAssetValueResponse = new DefaultValueAssetValueResponse();
	defaultValueAssetValueResponse.setId(defaultValueAssetValue.getCustomId());
	defaultValueAssetValueResponse.setName(defaultValueAssetValue.getName());
	defaultValueAssetValueResponse.setNemonic(defaultValueAssetValue.getNemonic());
	defaultValueAssetValueResponse.setValue(defaultValueAssetValue.getValue());
	defaultValueAssetValueResponse.setVersion(defaultValueAssetValue.getVersion());
	
	return defaultValueAssetValueResponse;

    }


    public DefaultValueAssetValue defaultValueAssetValueResponseToDefaultValueAssetValue(DefaultValueAssetValueResponse defaultValueAssetValueResponse)
    {

	DefaultValueAssetValue defaultValueAssetValue = new DefaultValueAssetValue();
	defaultValueAssetValue.setCustomId(defaultValueAssetValueResponse.getId());
	defaultValueAssetValue.setName(defaultValueAssetValueResponse.getName());
	defaultValueAssetValue.setNemonic(defaultValueAssetValueResponse.getNemonic());
	defaultValueAssetValue.setValue(defaultValueAssetValueResponse.getValue());
	defaultValueAssetValueResponse.setVersion(defaultValueAssetValue.getVersion());

	return defaultValueAssetValue;

    }


    public List<DefaultValueAssetValue> defaulValueAssetValuesResponseToDefaultValueAssetValues(Collection<DefaultValueAssetValueResponse> defaultValueAssetValueResponse)
    {

	List<DefaultValueAssetValue> defaultValueAssetValues = new ArrayList<>();

	for (DefaultValueAssetValueResponse defaultValueResponse : defaultValueAssetValueResponse)
	{
	    defaultValueAssetValues.add(defaultValueAssetValueResponseToDefaultValueAssetValue(defaultValueResponse));
	}

	return defaultValueAssetValues;
    }
}
