package es.cic.tessa.lookup.expression.model.notification;


import java.io.Serializable;
import java.time.Instant;
import java.util.Map;
import java.util.Set;


public class TessaNotificationEvent implements Serializable
{

    private static final long serialVersionUID = 2L;

    private String entityType;
    private String operation;
    private Map<Long, Set<String>> entities;
    private Map<Long, String> entityPayloads;
    private Instant timestamp;
    private String correlationId;

    public TessaNotificationEvent()
    {

    }


    public TessaNotificationEvent(String entityType, String operation, Map<Long, Set<String>> entities, Instant timestamp, String correlationId)
    {

	this.entityType = entityType;
	this.operation = operation;
	this.entities = entities;
	this.timestamp = timestamp;
	this.correlationId = correlationId;
    }


    public TessaNotificationEvent(String entityType, String operation, Map<Long, Set<String>> entities, Map<Long, String> entityPayloads, Instant timestamp, String correlationId)
    {

	this.entityType = entityType;
	this.operation = operation;
	this.entities = entities;
	this.entityPayloads = entityPayloads;
	this.timestamp = timestamp;
	this.correlationId = correlationId;
    }


    public String getEntityType()
    {

	return entityType;
    }


    public void setEntityType(String entityType)
    {

	this.entityType = entityType;
    }


    public String getOperation()
    {

	return operation;
    }


    public void setOperation(String operation)
    {

	this.operation = operation;
    }


    public Map<Long, Set<String>> getEntities()
    {

	return entities;
    }


    public void setEntities(Map<Long, Set<String>> entities)
    {

	this.entities = entities;
    }


    public Instant getTimestamp()
    {

	return timestamp;
    }


    public void setTimestamp(Instant timestamp)
    {

	this.timestamp = timestamp;
    }


    public Map<Long, String> getEntityPayloads()
    {

	return entityPayloads;
    }


    public void setEntityPayloads(Map<Long, String> entityPayloads)
    {

	this.entityPayloads = entityPayloads;
    }


    public String getCorrelationId()
    {

	return correlationId;
    }


    public void setCorrelationId(String correlationId)
    {

	this.correlationId = correlationId;
    }


    @Override
    public String toString()
    {

	return "TessaNotificationEvent{entityType='" + entityType + "', operation='" + operation + "', entities=" + (entities != null ? entities.size() : 0) + ", correlationId='" + correlationId + "'}";
    }
}
