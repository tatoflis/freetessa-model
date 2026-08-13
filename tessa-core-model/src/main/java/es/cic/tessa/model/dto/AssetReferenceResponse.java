package es.cic.tessa.model.dto;


import es.cic.tessa.model.dto.support.AbstractIdentificableEntityResponse;


public class AssetReferenceResponse extends AbstractIdentificableEntityResponse
{

    private static final long serialVersionUID = 1L;
    private AssetResponse assetResponse;
    private String referenceType;
    private String relationType;

    public AssetResponse getAssetResponse()
    {

	return assetResponse;
    }


    public void setAssetResponse(AssetResponse assetResponse)
    {

	this.assetResponse = assetResponse;
    }


    public String getReferenceType()
    {

	return referenceType;
    }


    public void setReferenceType(String referenceType)
    {

	this.referenceType = referenceType;
    }


    public String getRelationType()
    {

	return relationType;
    }


    public void setRelationType(String relationType)
    {

	this.relationType = relationType;
    }

}
