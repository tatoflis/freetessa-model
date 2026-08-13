package es.cic.tessa.lookup.model;


import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import es.cic.tessa.lookup.model.db.AssetLookupDB;
import es.cic.tessa.lookup.model.metrics.AssetLookupPrometheusMetrics;
import es.cic.tessa.lookup.model.rest.AssetLookupRest;
import es.cic.tessa.model.optimize.AssetOptimize;
import es.cic.tessa.model.optimize.AssetValueOptimize;


public class AssetLookupConfigAssembly
{

    public static AssetLookupDB assemblyDatabaseConfig(AssetOptimize configLookupDatabase)
    {

	AssetLookupDB connectionDatabaseInfo = new AssetLookupDB();

	connectionDatabaseInfo.setId(configLookupDatabase.getId());

	Collection<AssetValueOptimize> values = new CopyOnWriteArrayList<>(configLookupDatabase.getAssetValueOptimize());

	for (AssetValueOptimize assetValue : values)
	{

	    String value = assetValue.getValue();

	    switch (assetValue.getTemplateAttributeOptimize().getName())
	    {
	    case AssetLookupDB.HOST:
	    {
		connectionDatabaseInfo.setHost(value);
		break;
	    }
	    case AssetLookupDB.PORT:
	    {
		connectionDatabaseInfo.setPort(Integer.parseInt(value));
		break;
	    }
	    case AssetLookupDB.SCHEMA:
	    {
		connectionDatabaseInfo.setSchema(value);
		break;
	    }
	    case AssetLookupDB.USER:
	    {
		connectionDatabaseInfo.setUser(value);
		break;
	    }
	    case AssetLookupDB.PASSWORD:
	    {
		connectionDatabaseInfo.setPassword(value);
		break;
	    }
	    case AssetLookupDB.SYSTEM:
	    {
		connectionDatabaseInfo.setSystem(value);
		break;
	    }
	    }

	}
	return connectionDatabaseInfo;
    }


    public static AssetLookupRest assemblyRestConfig(AssetOptimize configLookupRest)
    {

	AssetLookupRest connectionRestInfo = new AssetLookupRest();

	Collection<AssetValueOptimize> values = new CopyOnWriteArrayList<>(configLookupRest.getAssetValueOptimize());

	connectionRestInfo.setId(configLookupRest.getId());

	for (AssetValueOptimize assetValue : values)
	{
	    String value = assetValue.getValue();

	    switch (assetValue.getTemplateAttributeOptimize().getName())
	    {

	    case AssetLookupRest.HOST:
	    {
		connectionRestInfo.setHost(value);
		break;
	    }
	    case AssetLookupRest.PROTOCOL:
	    {
		connectionRestInfo.setProtocol(value);
		break;
	    }
	    case AssetLookupRest.PORT:
	    {
		connectionRestInfo.setPort(Integer.parseInt(value));
		break;
	    }
	    case AssetLookupRest.URI:
	    {
		connectionRestInfo.setUri(value);
		break;
	    }
	    case AssetLookupRest.URI_SERVICE:
	    {
		connectionRestInfo.setUriservice(value);
		break;
	    }
	    case AssetLookupRest.URI_LOGIN:
	    {
		connectionRestInfo.setUrilogin(value);
		break;
	    }
	    case AssetLookupRest.USER:
	    {
		connectionRestInfo.setUser(value);
		break;
	    }
	    case AssetLookupRest.PASSWORD:
	    {
		connectionRestInfo.setPassword(value);
		break;
	    }
	    case AssetLookupRest.OPERATION:
	    {
		connectionRestInfo.setOperation(value);
		break;
	    }

	    }

	}

	return connectionRestInfo;
    }


    public static AssetLookupPrometheusMetrics assemblyPrometheusConfig(AssetOptimize configPrometheusLookupRest)
    {

	AssetLookupPrometheusMetrics assetLookupPrometheusMetrics = new AssetLookupPrometheusMetrics();

	Collection<AssetValueOptimize> values = new CopyOnWriteArrayList<>(configPrometheusLookupRest.getAssetValueOptimize());

	assetLookupPrometheusMetrics.setId(configPrometheusLookupRest.getId());

	for (AssetValueOptimize assetValue : values)
	{
	    String value = assetValue.getValue();

	    switch (assetValue.getTemplateAttributeOptimize().getName())
	    {

	    case AssetLookupPrometheusMetrics.HOST:
	    {
		assetLookupPrometheusMetrics.setHost(value);
		break;
	    }
	    case AssetLookupPrometheusMetrics.PROTOCOL:
	    {
		assetLookupPrometheusMetrics.setProtocol(value);
		break;
	    }
	    case AssetLookupPrometheusMetrics.PORT:
	    {
		assetLookupPrometheusMetrics.setPort(Integer.parseInt(value));
		break;
	    }
	    case AssetLookupPrometheusMetrics.URI:
	    {
		assetLookupPrometheusMetrics.setUri(value);
		break;
	    }
	    case AssetLookupPrometheusMetrics.URI_SERVICE:
	    {
		assetLookupPrometheusMetrics.setUriservice(value);
		break;
	    }
	    case AssetLookupPrometheusMetrics.URI_LOGIN:
	    {
		assetLookupPrometheusMetrics.setUrilogin(value);
		break;
	    }
	    case AssetLookupPrometheusMetrics.USER:
	    {
		assetLookupPrometheusMetrics.setUser(value);
		break;
	    }
	    case AssetLookupPrometheusMetrics.PASSWORD:
	    {
		assetLookupPrometheusMetrics.setPassword(value);
		break;
	    }
	    case AssetLookupPrometheusMetrics.OPERATION:
	    {
		assetLookupPrometheusMetrics.setOperation(value);
		break;
	    }
	    case AssetLookupPrometheusMetrics.INSTANCE:
	    {
		assetLookupPrometheusMetrics.setInstance(value);
		break;
	    }
	    case AssetLookupPrometheusMetrics.JOB:
	    {
		assetLookupPrometheusMetrics.setJob(value);
		break;
	    }
	    case AssetLookupPrometheusMetrics.METRIC:
	    {
		assetLookupPrometheusMetrics.setMetric(value);
		break;
	    }
	    case AssetLookupPrometheusMetrics.METHOD:
	    {
		assetLookupPrometheusMetrics.setMethod(value);
		break;
	    }

	    }

	}

	return assetLookupPrometheusMetrics;
    }
}
