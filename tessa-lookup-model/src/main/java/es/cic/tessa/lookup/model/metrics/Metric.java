package es.cic.tessa.lookup.model.metrics;


public class Metric
{

    private String __name__;
    private String instance;
    private String job;

    // Getters and setters
    public String get__name__()
    {

	return __name__;
    }


    public void set__name__(String __name__)
    {

	this.__name__ = __name__;
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
