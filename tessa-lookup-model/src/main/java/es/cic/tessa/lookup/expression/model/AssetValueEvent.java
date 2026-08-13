package es.cic.tessa.lookup.expression.model;


import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
import es.cic.tessa.model.optimize.AssetOptimize;
import es.cic.tessa.model.optimize.AssetValueOptimize;


public class AssetValueEvent implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Collection<AssetOptimize> assetIDSReferences = new LinkedHashSet<>();
    private Collection<AssetValueOptimize> assetValueExpressions = new LinkedHashSet<>();

    public Collection<AssetOptimize> getAssetIDSReferences()
    {

	return assetIDSReferences;
    }


    public void setAssetIDSReferences(Collection<AssetOptimize> assetIDSReferences)
    {

	this.assetIDSReferences = assetIDSReferences;
    }


    public Collection<AssetValueOptimize> getAssetValueExpressions()
    {

	return assetValueExpressions;
    }


    public void setAssetValueExpressions(Collection<AssetValueOptimize> assetValueExpression)
    {

	this.assetValueExpressions = assetValueExpression;
    }

}
