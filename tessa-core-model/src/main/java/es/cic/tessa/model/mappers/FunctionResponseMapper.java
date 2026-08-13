package es.cic.tessa.model.mappers;


import org.springframework.stereotype.Component;
import es.cic.tessa.model.Function;
import es.cic.tessa.model.dto.FunctionResponse;


@Component
public class FunctionResponseMapper
{

    public FunctionResponse functionToFunctionResponse(Function function)
    {

	FunctionResponse functionResponse = new FunctionResponse();

	functionResponse.setExpressionFunction(function.getExpressionFunction());
	functionResponse.setRefillingCalculation(function.getRefillingCalculation());
	functionResponse.setIgnoreNoData(function.getIgnoreNoData());
	functionResponse.setCronExpression(function.getCronExpression());
	functionResponse.setExpressionEvent(function.getExpressionEvent());
	functionResponse.setCronTimeZone(function.getCronTimeZone());
	functionResponse.setCronDelay(function.getCronDelay());

	return functionResponse;

    }


    public Function functionResponseToFunction(FunctionResponse functionResponse)
    {

	Function function = new Function();

	function.setExpressionFunction(functionResponse.getExpressionFunction());
	function.setRefillingCalculation(functionResponse.isRefillingCalculation());
	function.setIgnoreNoData(functionResponse.isIgnoreNoData());
	function.setCronExpression(functionResponse.getCronExpression());
	function.setExpressionEvent(functionResponse.getExpressionEvent());
	function.setCronTimeZone(functionResponse.getCronTimeZone());
	function.setCronDelay(functionResponse.getCronDelay());

	return function;
    }
}
