package es.cic.tessa.lookup.model.metrics;


import es.cic.tessa.lookup.model.rest.AssetLookupRest;


public class AssetLookupPrometheusMetrics extends AssetLookupRest
{

    public static final String INSTANCE = "Instance";
    public static final String JOB = "Job";
    public static final String METRIC = "Metric";
    public static final String METHOD = "Method";

    private String method;
    private String instance;
    private String job;
    private String metric;

    private static final long serialVersionUID = 1L;

    public String getMetric()
    {

	return metric;
    }


    public void setMetric(String metric)
    {

	this.metric = metric;
    }


    public String getMethod()
    {

	return method;
    }


    public void setMethod(String method)
    {

	this.method = method;
    }


    public String getInstance()
    {

	return instance;
    }


    public void setInstance(String instance)
    {

	this.instance = instance;
    }


    public String getJob()
    {

	return job;
    }


    public void setJob(String job)
    {

	this.job = job;
    }

}
