package es.cic.tessa.lookup.model.metrics;

public class PrometheusModel
{

    private String status;
    private Data data;

    // Getters and setters
    public String getStatus()
    {

	return status;
    }


    public void setStatus(String status)
    {

	this.status = status;
    }


    public Data getData()
    {

	return data;
    }


    public void setData(Data data)
    {

	this.data = data;
    }
}
