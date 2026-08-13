package es.cic.tessa.lookup.model;


public class ResultFunctionCheck
{

    private String function;

    private Boolean valid = Boolean.FALSE;;

    public ResultFunctionCheck(String function, Boolean valid)
    {

	this.function = function;
	this.valid = valid;
    }


    public String getFunction()
    {

	return function;
    }


    public void setFunction(String function)
    {

	this.function = function;
    }


    public Boolean getValid()
    {

	return valid;
    }


    public void setValid(Boolean valid)
    {

	this.valid = valid;
    }

}
