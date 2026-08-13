package es.cic.tessa.model.dto;


public class AssetReferenceRequest
{

    private Long id;
    private Long idAsset;
    private String referenceType;
    private String relationType;

    public Long getId()
    {

	return id;
    }


    public void setId(Long id)
    {

	this.id = id;
    }


    public Long getIdAsset()
    {

	return idAsset;
    }


    public void setIdAsset(Long idAsset)
    {

	this.idAsset = idAsset;
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
