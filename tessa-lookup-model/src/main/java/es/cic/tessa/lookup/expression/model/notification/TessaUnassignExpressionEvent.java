package es.cic.tessa.lookup.expression.model.notification;


import java.io.Serializable;
import java.time.Instant;


public class TessaUnassignExpressionEvent implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Long templateAttributeId;
    private Long assetValueId;
    private Instant timestamp;

    public TessaUnassignExpressionEvent()
    {

    }


    public TessaUnassignExpressionEvent(Long templateAttributeId, Long assetValueId, Instant timestamp)
    {

	this.templateAttributeId = templateAttributeId;
	this.assetValueId = assetValueId;
	this.timestamp = timestamp;
    }


    public Long getTemplateAttributeId()
    {

	return templateAttributeId;
    }


    public void setTemplateAttributeId(Long templateAttributeId)
    {

	this.templateAttributeId = templateAttributeId;
    }


    public Long getAssetValueId()
    {

	return assetValueId;
    }


    public void setAssetValueId(Long assetValueId)
    {

	this.assetValueId = assetValueId;
    }


    public Instant getTimestamp()
    {

	return timestamp;
    }


    public void setTimestamp(Instant timestamp)
    {

	this.timestamp = timestamp;
    }


    @Override
    public String toString()
    {

	return "TessaUnassignExpressionEvent{templateAttributeId=" + templateAttributeId + ", assetValueId=" + assetValueId + "}";
    }
}
