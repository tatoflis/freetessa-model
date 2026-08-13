package es.cic.tessa.model.historical.dto;


import java.time.Instant;
import es.cic.tessa.model.dto.AssetResponse;
import es.cic.tessa.model.dto.support.AbstractIdentificableEntityResponse;


public class HistoricalChangeResponse extends AbstractIdentificableEntityResponse
{

    private static final long serialVersionUID = 1L;

    private AssetResponse originalAsset;
    private Instant startChange;
    private Instant endChange;
    private String operation;

    public AssetResponse getOriginalAsset()
    {

	return originalAsset;
    }


    public void setOriginalAsset(AssetResponse originalAsset)
    {

	this.originalAsset = originalAsset;
    }


    public Instant getStartChange()
    {

	return startChange;
    }


    public void setStartChange(Instant startChange)
    {

	this.startChange = startChange;
    }


    public Instant getEndChange()
    {

	return endChange;
    }


    public void setEndChange(Instant endChange)
    {

	this.endChange = endChange;
    }


    public String getOperation()
    {

	return operation;
    }


    public void setOperation(String operation)
    {

	this.operation = operation;
    }

}
