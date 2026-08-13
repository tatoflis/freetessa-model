package es.cic.tessa.model.historical.mapper;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;
import es.cic.tessa.model.HistoricalDefaultValueAssetValue;
import es.cic.tessa.model.historical.dto.HistoricalDefaultValueResponse;


@Component
public class HistoricalDefaultValueResponseMapper
{

    public HistoricalDefaultValueResponse defaultValueToDefaultValueRespose(HistoricalDefaultValueAssetValue defaultValueAssetValue)
    {

	HistoricalDefaultValueResponse defaultValueAssetValueResponse = new HistoricalDefaultValueResponse();

	defaultValueAssetValueResponse.setId(defaultValueAssetValue.getCustomId());
	defaultValueAssetValueResponse.setName(defaultValueAssetValue.getName());
	defaultValueAssetValueResponse.setDescription(defaultValueAssetValue.getDescription());

	defaultValueAssetValueResponse.setVersion(defaultValueAssetValue.getVersion());
	defaultValueAssetValueResponse.setNemonic(defaultValueAssetValue.getNemonic());
	defaultValueAssetValueResponse.setValue(defaultValueAssetValue.getValue());
	defaultValueAssetValueResponse.setVersion(defaultValueAssetValue.getVersion());

	return defaultValueAssetValueResponse;
    }


    public HistoricalDefaultValueAssetValue defaultValueAssetValueResponseToDefaultValueAssetValue(HistoricalDefaultValueResponse defaultValueAssetValueResponse)
    {

	HistoricalDefaultValueAssetValue defaultValueAssetValue = new HistoricalDefaultValueAssetValue();
	defaultValueAssetValue.setCustomId(defaultValueAssetValueResponse.getId());
	defaultValueAssetValue.setName(defaultValueAssetValueResponse.getName());
	defaultValueAssetValue.setNemonic(defaultValueAssetValueResponse.getNemonic());
	defaultValueAssetValue.setValue(defaultValueAssetValueResponse.getValue());

	return defaultValueAssetValue;

    }


    public List<HistoricalDefaultValueAssetValue> defaulValueAssetValuesResponseToDefaultValueAssetValues(Collection<HistoricalDefaultValueResponse> defaultValueAssetValueResponse)
    {

	List<HistoricalDefaultValueAssetValue> defaultValueAssetValues = new ArrayList<>();

	for (HistoricalDefaultValueResponse defaultValueResponse : defaultValueAssetValueResponse)
	{
	    defaultValueAssetValues.add(defaultValueAssetValueResponseToDefaultValueAssetValue(defaultValueResponse));
	}

	return defaultValueAssetValues;
    }
}
