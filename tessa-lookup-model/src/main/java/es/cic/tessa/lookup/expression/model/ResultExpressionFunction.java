package es.cic.tessa.lookup.expression.model;


import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import tools.jackson.databind.annotation.JsonSerialize;


@JsonInclude(Include.NON_NULL)
@JsonSerialize
public class ResultExpressionFunction implements Serializable
{

    private static final long serialVersionUID = 1L;

    private List<ResultExpressionFunctionRecord> rows = new LinkedList<ResultExpressionFunctionRecord>();
    private Instant lastUpdate = Instant.now();
    private Integer state = Integer.valueOf(0);

    public List<ResultExpressionFunctionRecord> getRows()
    {

	return rows;
    }


    public void setRows(List<ResultExpressionFunctionRecord> rows)
    {

	this.rows = rows;
    }


    public Instant getLastUpdate()
    {

	return lastUpdate;
    }


    public void setLastUpdate(Instant lastUpdate)
    {

	this.lastUpdate = lastUpdate;
    }


    public Integer getState()
    {

	return state;
    }


    public void setState(Integer state)
    {

	this.state = state;
    }


    @Override
    public String toString()
    {

	return "AssetValueResult [rows=" + rows + ", lastUpdate=" + lastUpdate + ", state=" + state + "]";
    }

}
