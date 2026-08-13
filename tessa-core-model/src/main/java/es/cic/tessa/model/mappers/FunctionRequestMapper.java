package es.cic.tessa.model.mappers;


import org.springframework.stereotype.Component;
import es.cic.tessa.model.Function;
import es.cic.tessa.model.dto.FunctionRequest;


@Component
public class FunctionRequestMapper
{

    public Function functionRequestToFunction(FunctionRequest functionRequest)
    {

	Function function = new Function();

	function.setExpressionFunction(functionRequest.getExpressionFunction());

	if(functionRequest.isRefillingCalculation() != null)
	{
	    function.setRefillingCalculation(functionRequest.isRefillingCalculation());
	}

	if(functionRequest.isIgnoreNoData() != null)
	{
	    function.setIgnoreNoData(functionRequest.isIgnoreNoData());
	}

	function.setCronExpression(functionRequest.getCronExpression());
	function.setExpressionEvent(functionRequest.getExpressionEvent());
	function.setCronTimeZone(functionRequest.getCronTimeZone());
	function.setCronDelay(functionRequest.getCronDelay());

	return function;

    }


    public FunctionRequest functionToFunctionRequest(Function function)
    {

	FunctionRequest functionRequest = new FunctionRequest();
	functionRequest.setExpressionFunction(function.getExpressionFunction());
	functionRequest.setRefillingCalculation(function.getRefillingCalculation());
	functionRequest.setIgnoreNoData(function.getIgnoreNoData());
	functionRequest.setCronExpression(function.getCronExpression());
	functionRequest.setExpressionEvent(function.getExpressionEvent());
	functionRequest.setCronTimeZone(function.getCronTimeZone());
	functionRequest.setCronDelay(function.getCronDelay());

	return functionRequest;
    }
}
