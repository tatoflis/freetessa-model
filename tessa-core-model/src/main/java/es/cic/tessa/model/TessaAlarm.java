package es.cic.tessa.model;


import java.time.Instant;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import tools.jackson.databind.annotation.JsonSerialize;
import es.cic.tessa.model.support.TemporalElement;


@JsonInclude(value = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
@JsonSerialize
public class TessaAlarm implements TemporalElement
{

    private static final long serialVersionUID = 1L;

    @JsonProperty("AI")
    private Long id;

    @JsonProperty("CAI")
    private Long contextAlarmId;

    @JsonProperty("S")
    private Integer severity;

    @JsonProperty("M")
    private Instant timestamp;

    @JsonProperty("D")
    private Long duration;

    public TessaAlarm()
    {

    }


    public TessaAlarm(Long id, Long contextAlarmId, Instant timestamp, Integer severity, Long duration)
    {

	this.id = id;
	this.contextAlarmId = contextAlarmId;
	this.timestamp = timestamp;
	this.severity = severity;
	this.duration = duration;
    }


    public Long getDuration()
    {

	return duration;
    }


    public void setDuration(Long duration)
    {

	this.duration = duration;
    }


    public Long getId()
    {

	return id;
    }


    public void setId(Long id)
    {

	this.id = id;
    }


    public Long getContextAlarmId()
    {

	return contextAlarmId;
    }


    public void setContextAlarmId(Long contextAlarmId)
    {

	this.contextAlarmId = contextAlarmId;
    }


    public Instant getTimestamp()
    {

	return timestamp;
    }


    public void setTimestamp(Instant timestamp)
    {

	this.timestamp = timestamp;
    }


    public Integer getSeverity()
    {

	return severity;
    }


    public void setSeverity(Integer severity)
    {

	this.severity = severity;
    }


    @Override
    public String toString()
    {

	return "AlarmDTO [id=" + id + ", contextAlarmId=" + contextAlarmId + ", timestamp=" + timestamp + ", duration=" + duration + "]";
    }

}
