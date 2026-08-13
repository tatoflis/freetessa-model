package es.cic.tessa.lookup.expression.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import tools.jackson.databind.annotation.JsonSerialize;


@JsonInclude(Include.NON_NULL)
@JsonSerialize
public class ResultExpressionFunctionRecord implements Serializable
{

    private static final long serialVersionUID = 1L;
    private List<ColumnValue> row = new ArrayList<ColumnValue>();

    public List<ColumnValue> getRow()
    {

	return row;
    }


    public void setRow(List<ColumnValue> row)
    {

	this.row = row;
    }


    @Override
    public String toString()
    {

	return "Record [row=" + row + "]";
    }
}
