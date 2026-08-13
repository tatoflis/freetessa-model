package es.cic.tessa.model.mappers;


import es.cic.tessa.model.HistoricalExpressionParam;
import es.cic.tessa.model.optimize.ExpressionParamOptimize;


/**
 * Mapea un {@link HistoricalExpressionParam} (snapshot as-of) a {@link ExpressionParamOptimize}.
 * <p>
 * A diferencia de {@code AssetValueOptimizeMapper}/{@code TemplateAttributeOptimizeMapper} (que
 * solo copian id/name/groups), aquí se pueblan también {@code type}, {@code required},
 * {@code position} y {@code defaultValue}: son los campos que
 * {@code ExpressionParameterUtils.changeExpressionFromExpressionParams} lee para sustituir los
 * tokens {@code $[...]} en la expresión — el mismo conjunto que ya mapean
 * {@code AssemblyAssetValueOptimize}/{@code AssemblyTemplateAttributeOptimize} (la vía real que
 * alimenta el cálculo desde caché/BD). Sin ellos, un {@code ExpressionParam} as-of resolvería con
 * su posición y valor por defecto vacíos aunque el snapshot los tuviera.
 * <p>
 * {@code defaultValue} viene de {@code historicalDefaultValueAssetValue.getValue()}: en
 * {@link HistoricalExpressionParam} el valor por defecto no es una propiedad propia, sino un nodo
 * relacionado ({@code DEFAULT_VALUE}), igual que en la entidad viva {@code ExpressionParam}.
 */
public final class HistoricalExpressionParamOptimizeMapper
{

    private HistoricalExpressionParamOptimizeMapper()
    {

    }


    public static ExpressionParamOptimize toOptimize(HistoricalExpressionParam historicalExpressionParam)
    {

	if(historicalExpressionParam == null)
	{
	    return null;
	}

	ExpressionParamOptimize optimize = new ExpressionParamOptimize();
	optimize.setId(historicalExpressionParam.getCustomId());
	optimize.setName(historicalExpressionParam.getName());
	optimize.setGroups(historicalExpressionParam.getGroups());
	optimize.setType(historicalExpressionParam.getType());

	if(historicalExpressionParam.getRequired() != null)
	{
	    optimize.setRequired(historicalExpressionParam.getRequired());
	}

	if(historicalExpressionParam.getPosition() != null)
	{
	    optimize.setPosition(historicalExpressionParam.getPosition());
	}

	if(historicalExpressionParam.getHistoricalDefaultValueAssetValue() != null)
	{
	    optimize.setDefaultValue(historicalExpressionParam.getHistoricalDefaultValueAssetValue().getValue());
	}

	return optimize;
    }
}
