package es.cic.tessa.model.dto;


import java.time.LocalDateTime;
import es.cic.tessa.model.dto.support.AbstractEntityRequest;


public class AssetRequest extends AbstractEntityRequest
{

    private static final long serialVersionUID = 1L;

    private String identificator;
    private Long idTemplate;
    private Long idAssetBase;
    private Boolean active;
    protected LocalDateTime insertDate;

    public Long getIdTemplate()
    {

	return idTemplate;
    }


    public void setIdTemplate(Long idTemplate)
    {

	this.idTemplate = idTemplate;
    }


    public Long getIdAssetBase()
    {

	return idAssetBase;
    }


    public void setIdAssetBase(Long idAssetBase)
    {

	this.idAssetBase = idAssetBase;
    }


    public String getIdentificator()
    {

	return identificator;
    }


    public void setIdentificator(String identificator)
    {

	this.identificator = identificator;
    }


    public Boolean getActive()
    {

	return active;
    }


    public void setActive(Boolean active)
    {

	this.active = active;
    }


    public LocalDateTime getInsertDate()
    {

	return insertDate;
    }


    public void setInsertDate(LocalDateTime insertDate)
    {

	this.insertDate = insertDate;
    }

}
