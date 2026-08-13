package es.cic.tessa.model.mappers;


import java.util.Set;
import org.springframework.stereotype.Component;
import es.cic.tessa.model.DefaultValueAssetValue;
import es.cic.tessa.model.dto.DefaultValueAssetValueRequest;


@Component
public class DefaultValueAssetValueRequestMapper
{

    public DefaultValueAssetValue defaultValueRequestToDefaultValue(DefaultValueAssetValueRequest defaultValueAssetValueRequest, Set<String> groups)
    {

	DefaultValueAssetValue defaultValueAssetValue = new DefaultValueAssetValue();
	if(defaultValueAssetValueRequest.getId() != null)
	{
	    defaultValueAssetValue.setCustomId(defaultValueAssetValueRequest.getId());
	}

	defaultValueAssetValue.setName(defaultValueAssetValueRequest.getName());
	defaultValueAssetValue.setValue(defaultValueAssetValueRequest.getValue());
	defaultValueAssetValue.setGroups(groups);

	if(defaultValueAssetValueRequest.getVersion() != null)
	{
	    defaultValueAssetValue.setVersion(defaultValueAssetValueRequest.getVersion());
	}

	return defaultValueAssetValue;
    }

}
