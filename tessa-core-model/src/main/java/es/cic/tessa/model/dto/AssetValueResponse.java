package es.cic.tessa.model.dto;


import java.util.Collection;
import java.util.Objects;
import es.cic.tessa.model.dto.support.AbstractEntityResponse;


public class AssetValueResponse extends AbstractEntityResponse
{

    private static final long serialVersionUID = 1L;

    private AssetReferenceResponse assetReference;
    private TemplateAttributeResponse templateAttribute;
    private AssetResponse asset;
    private TemplateAttributeCollectionMappingResponse templateAttributeCollectionMapping;
    private String value;
    private String idBinary;
    private FunctionResponse expressionProperties;
    private Collection<ExpressionParamResponse> expressionParams;

    public TemplateAttributeResponse getTemplateAttribute()
    {

	return templateAttribute;
    }


    public void setTemplateAttribute(TemplateAttributeResponse templateAttribute)
    {

	this.templateAttribute = templateAttribute;
    }


    public TemplateAttributeCollectionMappingResponse getTemplateAttributeCollectionMapping()
    {

	return templateAttributeCollectionMapping;
    }


    public void setTemplateAttributeCollectionMapping(TemplateAttributeCollectionMappingResponse templateAttributeCollectionMapping)
    {

	this.templateAttributeCollectionMapping = templateAttributeCollectionMapping;
    }


    public AssetReferenceResponse getAssetReference()
    {

	return assetReference;
    }


    public void setAssetReference(AssetReferenceResponse assetReference)
    {

	this.assetReference = assetReference;
    }


    public AssetResponse getAsset()
    {

	return asset;
    }


    public void setAsset(AssetResponse asset)
    {

	this.asset = asset;
    }


    public String getValue()
    {

	return value;
    }


    public void setValue(String value)
    {

	this.value = value;
    }


    public FunctionResponse getExpressionProperties()
    {

	return expressionProperties;
    }


    public void setExpressionProperties(FunctionResponse expressionProperties)
    {

	this.expressionProperties = expressionProperties;
    }


    public Collection<ExpressionParamResponse> getExpressionParams()
    {

	return expressionParams;
    }


    public void setExpressionParams(Collection<ExpressionParamResponse> expressionParams)
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


    @Override
    public int hashCode()
    {

	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(asset, assetReference, expressionParams, expressionProperties, templateAttribute, templateAttributeCollectionMapping, value);
	return result;
    }


    @Override
    public boolean equals(Object obj)
    {

	if(this == obj)
	    return true;
	if(!super.equals(obj))
	    return false;
	if(getClass() != obj.getClass())
	    return false;
	AssetValueResponse other = (AssetValueResponse) obj;
	return Objects.equals(asset, other.asset) && Objects.equals(assetReference, other.assetReference) && Objects.equals(expressionParams, other.expressionParams) && Objects.equals(expressionProperties, other.expressionProperties) && Objects.equals(templateAttribute, other.templateAttribute) && Objects.equals(templateAttributeCollectionMapping, other.templateAttributeCollectionMapping) && Objects.equals(value, other.value);
    }


    @Override
    public String toString()
    {

	return "AssetValueResponse [assetReference=" + assetReference + ", templateAttribute=" + templateAttribute + ", asset=" + asset + ", templateAttributeCollectionMapping=" + templateAttributeCollectionMapping + ", value=" + value + ", function=" + expressionProperties + ", expressionParams=" + expressionParams + "]";
    }

}
