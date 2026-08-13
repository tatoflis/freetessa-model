package es.cic.tessa.model.dto;


import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class FunctionResponse implements Serializable
{

    private static final long serialVersionUID = 1L;

    private String expressionFunction;
    private Boolean refillingCalculation;
    private Boolean ignoreNoData;
    private String cronExpression;
    private String expressionEvent;
    private String cronTimeZone;
    private Long cronDelay;

    public String getExpressionFunction()
    {

	return expressionFunction;
    }


    public void setExpressionFunction(String expressionFunction)
    {

	this.expressionFunction = expressionFunction;
    }


    public Boolean isRefillingCalculation()
    {

	return refillingCalculation;
    }


    public void setRefillingCalculation(Boolean refillingCalculation)
    {

	this.refillingCalculation = refillingCalculation;
    }


    public Boolean isIgnoreNoData()
    {

	return ignoreNoData;
    }


    public void setIgnoreNoData(Boolean ignoreNoData)
    {

	this.ignoreNoData = ignoreNoData;
    }


    public String getCronExpression()
    {

	return cronExpression;
    }


    public void setCronExpression(String cronExpression)
    {

	this.cronExpression = cronExpression;
    }


    public String getExpressionEvent()
    {

	return expressionEvent;
    }


    public void setExpressionEvent(String expressionEvent)
    {

	this.expressionEvent = expressionEvent;
    }


    public String getCronTimeZone()
    {

	return cronTimeZone;
    }


    public void setCronTimeZone(String cronTimeZone)
    {

	this.cronTimeZone = cronTimeZone;
    }


    public Long getCronDelay()
    {

	return cronDelay;
    }


    public void setCronDelay(Long cronDelay)
    {

	this.cronDelay = cronDelay;
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(cronDelay, cronExpression, cronTimeZone, expressionEvent, expressionFunction, ignoreNoData, refillingCalculation);
    }


    @Override
    public boolean equals(Object obj)
    {

	if(this == obj)
	    return true;
	if(obj == null)
	    return false;
	if(getClass() != obj.getClass())
	    return false;
	FunctionResponse other = (FunctionResponse) obj;
	return Objects.equals(cronDelay, other.cronDelay) && Objects.equals(cronExpression, other.cronExpression) && Objects.equals(cronTimeZone, other.cronTimeZone) && Objects.equals(expressionEvent, other.expressionEvent) && Objects.equals(expressionFunction, other.expressionFunction) && ignoreNoData == other.ignoreNoData && refillingCalculation == other.refillingCalculation;
    }


    @Override
    public String toString()
    {

	return "FunctionResponse [expressionFunction=" + expressionFunction + ", refillingCalculation=" + refillingCalculation + ", ignoreNoData=" + ignoreNoData + ", cronExpression=" + cronExpression + ", expressionEvent=" + expressionEvent + ", cronTimeZone=" + cronTimeZone + ", cronDelay=" + cronDelay + "]";
    }

}
