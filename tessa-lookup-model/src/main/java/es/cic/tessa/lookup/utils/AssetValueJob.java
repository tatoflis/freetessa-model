package es.cic.tessa.lookup.utils;


import java.util.concurrent.Future;
import es.cic.tessa.lookup.expression.model.ResultExpressionFunction;


public class AssetValueJob
{

    private Future<ResultExpressionFunction> expressionFuture;
    private ResultExpressionFunction lookupResult;

    public AssetValueJob(Future<ResultExpressionFunction> expressionFuture, ResultExpressionFunction lookupResult)
    {

	this.expressionFuture = expressionFuture;
	this.lookupResult = lookupResult;
    }


    public Future<ResultExpressionFunction> getExpressionFuture()
    {

	return expressionFuture;
    }


    public void setExpressionFuture(Future<ResultExpressionFunction> expressionFuture)
    {

	this.expressionFuture = expressionFuture;
    }


    public ResultExpressionFunction getLookupResult()
    {

	return lookupResult;
    }


    public void setLookupResult(ResultExpressionFunction lookupResult)
    {

	this.lookupResult = lookupResult;
    }

}
