package es.cic.tessa.lookup.model.metrics;


import java.util.List;


public class Result
{

    private Metric metric;
    private List<Object> value;

    // Getters and setters
    public Metric getMetric()
    {

	return metric;
    }


    public void setMetric(Metric metric)
    {

	this.metric = metric;
    }


    public List<Object> getValue()
    {

	return value;
    }


    public void setValue(List<Object> value)
    {

	this.value = value;
    }
}
