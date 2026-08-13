package es.cic.tessa.lookup.expression.model;


import java.io.Serializable;
import java.time.Instant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import tools.jackson.databind.annotation.JsonSerialize;


@JsonInclude(Include.NON_NULL)
@JsonSerialize
public class ColumnValue implements Serializable
{

    private static final long serialVersionUID = 1L;

    private String source;
    @JsonIgnore
    private String columnId;
    private String payload;
    private String columnName;
    private String value;
    private Instant moment;

    public Instant getMoment()
    {

	return moment;
    }


    public void setMoment(Instant moment)
    {

	this.moment = moment;
    }


    public String getColumnId()
    {

	return columnId;
    }


    public void setColumnId(String columnId)
    {

	this.columnId = columnId;
    }


    public String getColumnName()
    {

	return columnName;
    }


    public void setColumnName(String columnName)
    {

	this.columnName = columnName;
    }


    public String getValue()
    {

	return value;
    }


    public String getSource()
    {

	return source;
    }


    public void setSource(String source)
    {

	this.source = source;
    }


    public void setValue(String value)
    {

	this.value = value;
    }


    @Override
    public String toString()
    {

	return "ColumnValue [source=" + source + ", columnId=" + columnId + ", columnName=" + columnName + ", value=" + value + ", moment=" + moment + "]";
    }


    public String getPayload()
    {

	return payload;
    }


    public void setPayload(String payload)
    {

	this.payload = payload;
    }

}
