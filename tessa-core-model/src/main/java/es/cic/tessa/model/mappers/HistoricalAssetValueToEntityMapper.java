package es.cic.tessa.model.mappers;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import es.cic.tessa.model.AssetValue;
import es.cic.tessa.model.ExpressionParam;
import es.cic.tessa.model.HistoricalAssetValue;
import es.cic.tessa.model.HistoricalExpressionParam;


@Component
public class HistoricalAssetValueToEntityMapper
{

    @Lazy
    @Autowired
    private HistoricalTemplateAttributeToEntityMapper historicalTemplateAttributeToEntityMapper;


    public AssetValue historicalAssetValueToAssetValue(HistoricalAssetValue historicalAssetValue)
    {

	if(historicalAssetValue == null)
	{
	    return null;
	}

	AssetValue assetValue = new AssetValue();

	// Campos compartidos via TessaElement/IdentificableElement/LockableElement/DateTimeElement
	assetValue.setCustomId(historicalAssetValue.getCustomId());
	assetValue.setId(historicalAssetValue.getId());
	assetValue.setName(historicalAssetValue.getName());
	assetValue.setNameLower(historicalAssetValue.getNameLower());
	assetValue.setDescription(historicalAssetValue.getDescription());
	assetValue.setNemonic(historicalAssetValue.getNemonic());
	assetValue.setGroups(historicalAssetValue.getGroups());
	assetValue.setIcon(historicalAssetValue.getIcon());
	assetValue.setVersion(historicalAssetValue.getVersion());
	assetValue.setInsertDate(historicalAssetValue.getInsertDate());
	assetValue.setModifDate(historicalAssetValue.getModifDate());

	// Campos propios de AssetValue
	assetValue.setValue(historicalAssetValue.getValue());
	assetValue.setValueLower(historicalAssetValue.getValueLower());
	assetValue.setAlias(historicalAssetValue.getAlias());
	assetValue.setExpressionProperties(historicalAssetValue.getExpressionProperties());

	// historicalAsset (relacion inversa): no se mapea para evitar ciclo AssetValue→asset→Asset
	// historicalAssetReference: sin equivalente directo en AssetReference vivo, se deja null
	// historicalTemplateAttributeCollectionMapping: no se mapea

	// Relacion con TemplateAttribute
	assetValue.setTemplateAttribute(historicalTemplateAttributeToEntityMapper.historicalTemplateAttributeToTemplateAttribute(historicalAssetValue.getHistoricalTemplateAttribute()));

	// ExpressionParams: mapeo inline Historical→vivo
	if(historicalAssetValue.getExpressionParams() != null)
	{
	    assetValue.setExpressionParams(historicalExpressionParamsToExpressionParams(historicalAssetValue.getExpressionParams()));
	}

	return assetValue;
    }


    public List<AssetValue> historicalAssetValueListToAssetValueList(List<HistoricalAssetValue> historicalAssetValues)
    {

	if(historicalAssetValues == null)
	{
	    return List.of();
	}

	return historicalAssetValues.stream()
		.map(this::historicalAssetValueToAssetValue)
		.collect(Collectors.toList());
    }


    private Set<ExpressionParam> historicalExpressionParamsToExpressionParams(Collection<HistoricalExpressionParam> historicalParams)
    {

	Set<ExpressionParam> result = new HashSet<>();

	for(HistoricalExpressionParam hp : historicalParams)
	{
	    ExpressionParam ep = new ExpressionParam();
	    ep.setCustomId(hp.getCustomId());
	    ep.setId(hp.getId());
	    ep.setName(hp.getName());
	    ep.setNameLower(hp.getNameLower());
	    ep.setDescription(hp.getDescription());
	    ep.setNemonic(hp.getNemonic());
	    ep.setGroups(hp.getGroups());
	    ep.setIcon(hp.getIcon());
	    ep.setVersion(hp.getVersion());
	    ep.setInsertDate(hp.getInsertDate());
	    ep.setModifDate(hp.getModifDate());
	    ep.setType(hp.getType());
	    ep.setRequired(hp.getRequired());
	    ep.setPosition(hp.getPosition());
	    // templateAttribute e historialDefaultValueAssetValue: no se mapean para evitar ciclos
	    result.add(ep);
	}

	return result;
    }

}
