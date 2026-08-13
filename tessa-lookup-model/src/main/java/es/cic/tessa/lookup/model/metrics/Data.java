package es.cic.tessa.lookup.model.metrics;


import java.util.List;


public class Data
{

    private String resultType;
    private List<Result> result;

    // Getters and setters
    public String getResultType()
    {

	return resultType;
    }


    public void setResultType(String resultType)
    {

	this.resultType = resultType;
    }


    public List<Result> getResult()
    {

	return result;
    }


    public void setResult(List<Result> result)
    {

	this.result = result;
    }
}
