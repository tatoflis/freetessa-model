package es.cic.tessa.model.dto;


import java.util.Collection;
import es.cic.tessa.model.dto.support.AbstractEntityRequest;


public class AssetValueRequest extends AbstractEntityRequest
{

    private static final long serialVersionUID = 1L;

    private String value;
    private FunctionRequest expressionProperties;
    private Long idAsset;
    private Long idAssetReference;
    private Long idTemplateAttribute;
    private Long idTemplateAttributeCollectionMapping;
    private String idBinary;
    private Collection<ExpressionParamRequest> expressionParams;

    public Long getIdAsset()
    {

	return idAsset;
    }


    public void setIdAsset(Long idAsset)
    {

	this.idAsset = idAsset;
    }


    public Long getIdTemplateAttribute()
    {

	return idTemplateAttribute;
    }


    public void setIdTemplateAttribute(Long idTemplateAttribute)
    {

	this.idTemplateAttribute = idTemplateAttribute;
    }


    public Long getIdTemplateAttributeCollectionMapping()
    {

	return idTemplateAttributeCollectionMapping;
    }


    public void setIdTemplateAttributeCollectionMapping(Long idTemplateAttributeCollectionMapping)
    {

	this.idTemplateAttributeCollectionMapping = idTemplateAttributeCollectionMapping;
    }


    public Long getIdAssetReference()
    {

	return idAssetReference;
    }


    public void setIdAssetReference(Long idAssetReference)
    {

	this.idAssetReference = idAssetReference;
    }


    public String getValue()
    {

	return value;
    }


    public void setValue(String value)
    {

	this.value = value;
    }


    public FunctionRequest getExpressionProperties()
    {

	return expressionProperties;
    }


    public void setExpressionProperties(FunctionRequest function)
    {

	this.expressionProperties = function;
    }


    public Collection<ExpressionParamRequest> getExpressionParams()
    {

	return expressionParams;
    }


    public void setExpressionParams(Collection<ExpressionParamRequest> expressionParams)
    {

	this.expressionParams = expressionParams;
    }


    public String getIdBinary()
    {

	return idBinary;
    }


    public void setIdBinary(String idBinary)
    {

	this.idBinary = idBinary;
    }

}
