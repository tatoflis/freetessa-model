package es.cic.tessa.model;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;


public class Function implements Serializable
{

    private static final long serialVersionUID = 1L;

    private static final JsonMapper OBJECT_MAPPER = JsonMapper.builder().build();

    private String expressionFunction;
    private String expressionEvent;
    private Boolean refillingCalculation;
    private Boolean ignoreNoData;
    private String cronExpression;
    private String cronTimeZone;
    private Long cronDelay;

    public Function()
    {

    }


    public Function(String expressionFunction)
    {

	this.expressionFunction = expressionFunction;
    }


    public Function(String expressionFunction, Boolean refillingCalculation, Boolean ignoreNoData, String cronExpression, String expressionEvent, String cronTimeZone, Long cronDelay)
    {

	this.expressionFunction = expressionFunction;
	this.refillingCalculation = refillingCalculation;
	this.ignoreNoData = ignoreNoData;
	this.cronExpression = cronExpression;
	this.expressionEvent = expressionEvent;
	this.cronTimeZone = cronTimeZone;
	this.cronDelay = cronDelay;
    }


    public String getExpressionFunction()
    {

	return expressionFunction;
    }


    public void setExpressionFunction(String expressionFunction)
    {

	this.expressionFunction = expressionFunction;
    }


    public Boolean getRefillingCalculation()
    {

	return refillingCalculation;
    }


    public void setRefillingCalculation(Boolean refillingCalculation)
    {

	this.refillingCalculation = refillingCalculation;
    }


    public Boolean getIgnoreNoData()
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


    public String getCypherExpressionProperties() throws JacksonException
    {

	Map<String, Object> jsonMap = new HashMap<>();
	jsonMap.put("expressionFunction", expressionFunction);
	jsonMap.put("refillingCalculation", refillingCalculation);
	jsonMap.put("ignoreNoData", ignoreNoData);
	jsonMap.put("cronExpression", cronExpression);
	jsonMap.put("expressionEvent", expressionEvent);
	jsonMap.put("cronTimeZone", cronTimeZone);
	jsonMap.put("cronDelay", cronDelay);

	return OBJECT_MAPPER.writeValueAsString(jsonMap);
    }


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(cronDelay, cronExpression, cronTimeZone, expressionEvent, expressionFunction, ignoreNoData, refillingCalculation);
	return result;
    }


    @Override
    public boolean equals(Object obj)
    {

	if(this == obj)
	    return true;
	if(!super.equals(obj))
	    return false;
	if(getClass() != obj.getClass())
	    return false;
	Function other = (Function) obj;
	return Objects.equals(cronDelay, other.cronDelay) && Objects.equals(cronExpression, other.cronExpression) && Objects.equals(cronTimeZone, other.cronTimeZone) && Objects.equals(expressionEvent, other.expressionEvent) && Objects.equals(expressionFunction, other.expressionFunction) && Objects.equals(ignoreNoData, other.ignoreNoData) && Objects.equals(refillingCalculation, other.refillingCalculation);
    }


    @Override
    public String toString()
    {

	return "[expressionFunction=" + expressionFunction + ", refillingCalculation=" + refillingCalculation + ", ignoreNoData=" + ignoreNoData + ", cronExpression=" + cronExpression + ", expressionEvent=" + expressionEvent + ", cronTimeZone=" + cronTimeZone + ", cronDelay=" + cronDelay + "]";
    }

}
