package es.cic.tessa.lookup.service.function;


import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import es.cic.tessa.lookup.expression.model.ColumnValue;
import es.cic.tessa.lookup.expression.model.ExpressionConstants;
import es.cic.tessa.lookup.expression.model.ResultExpressionFunction;
import es.cic.tessa.lookup.expression.model.ResultExpressionFunctionRecord;


public class FunctionAssembly
{

    public static ResultExpressionFunction buildAssetValueResultByValue(Long synteticAssetId, Long assetValueId, String propertyName, String value, Instant moment, int state)
    {

	List<ResultExpressionFunctionRecord> rows = new ArrayList<ResultExpressionFunctionRecord>();

	ResultExpressionFunctionRecord record = new ResultExpressionFunctionRecord();
	ColumnValue columnValue = new ColumnValue();

	if(synteticAssetId != null)
	{
	    columnValue.setSource(String.valueOf(synteticAssetId));
	}
	else
	{
	    columnValue.setSource(String.valueOf(assetValueId));
	}

	columnValue.setColumnId(String.valueOf(assetValueId));
	columnValue.setColumnName(propertyName);

	if(value == null)
	{
	    columnValue.setValue(ExpressionConstants.EXPRESSION_NAN);
	}
	else
	{
	    columnValue.setValue(value);
	}

	if(moment == null)
	{
	    moment = Instant.now();
	}

	columnValue.setMoment(moment);

	record.getRow().add(columnValue);

	rows.add(record);

	ResultExpressionFunction lookupResult = new ResultExpressionFunction();
	lookupResult.setRows(rows);
	lookupResult.setState(state);

	return lookupResult;
    }


    public static ResultExpressionFunction buildAssetValueResultByValue(Long synteticAssetId, Long assetValueId, String propertyName, Collection<String> values, Instant moment, int state)
    {

	List<ResultExpressionFunctionRecord> rows = new ArrayList<ResultExpressionFunctionRecord>();

	ResultExpressionFunctionRecord record = new ResultExpressionFunctionRecord();

	for (String value : values)
	{

	    ColumnValue columnValue = new ColumnValue();

	    if(synteticAssetId != null)
	    {
		columnValue.setSource(String.valueOf(synteticAssetId));
	    }
	    else
	    {
		columnValue.setSource(String.valueOf(assetValueId));
	    }

	    columnValue.setColumnId(String.valueOf(assetValueId));
	    columnValue.setColumnName(propertyName);

	    if(value == null)
	    {
		columnValue.setValue(ExpressionConstants.EXPRESSION_NAN);
	    }
	    else
	    {
		columnValue.setValue(value);
	    }

	    if(moment == null)
	    {
		moment = Instant.now();
	    }

	    columnValue.setMoment(moment);

	    record.getRow().add(columnValue);
	}

	rows.add(record);

	ResultExpressionFunction lookupResult = new ResultExpressionFunction();
	lookupResult.setRows(rows);
	lookupResult.setState(state);

	return lookupResult;
    }

}
