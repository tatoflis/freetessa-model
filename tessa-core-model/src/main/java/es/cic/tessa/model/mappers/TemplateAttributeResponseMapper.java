package es.cic.tessa.model.mappers;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import es.cic.tessa.common.model.TessaConstants;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.TemplateAttribute;
import es.cic.tessa.model.dto.TemplateAttributeResponse;
import es.cic.tessa.model.dto.TemplateAttributeResponse2;
import es.cic.tessa.model.dto.json.ResponsePageJson;


@Component
public class TemplateAttributeResponseMapper
{

    @Autowired
    private TemplateResponseMapper templateResponseMapper;

    @Autowired
    @Lazy
    private TemplateReferenceResponseMapper templateReferenceResponseMapper;

    @Autowired
    @Lazy
    private ExpressionParamResponseMapper expressionParamResponseMapper;

    @Autowired
    private FunctionResponseMapper functionResponseMapper;

    public TemplateAttributeResponse templateAttributeToTemplateAttributeRespose(boolean fromTemplate, TemplateAttribute templateAttribute)
    {

	TemplateAttributeResponse templateAttributeResponse = new TemplateAttributeResponse();
	templateAttributeResponse.setId(templateAttribute.getCustomId());
	templateAttributeResponse.setName(templateAttribute.getName());
	templateAttributeResponse.setDescription(templateAttribute.getDescription());
	templateAttributeResponse.setIcon(templateAttribute.getIcon());
	templateAttributeResponse.setInsertDate(templateAttribute.getInsertDate().toInstant(ZoneOffset.UTC));

	if(templateAttributeResponse.getModifDate() != null)
	{
	    templateAttributeResponse.setModifDate(templateAttribute.getModifDate().toInstant(ZoneOffset.UTC));
	}

	templateAttributeResponse.setType(templateAttribute.getType());
	templateAttributeResponse.setMinLength(templateAttribute.getMinLength());
	templateAttributeResponse.setMaxLength(templateAttribute.getMaxLength());
	templateAttributeResponse.setRequired(templateAttribute.getRequired());
	templateAttributeResponse.setHidden(templateAttribute.getHidden());
	templateAttributeResponse.setHasDefaultValue(templateAttribute.getHasDefaultValue());
	templateAttributeResponse.setDefaultValue(templateAttribute.getDefaultValue());
	templateAttributeResponse.setHasCalculatedValue(templateAttribute.getHasCalculatedValue());
	templateAttributeResponse.setCalculatedValue(templateAttribute.getCalculatedValue());
	templateAttributeResponse.setPattern(templateAttribute.getPattern());
	templateAttributeResponse.setUnique(templateAttribute.getUnique());
	templateAttributeResponse.setExternalSource(templateAttribute.getExternalSource());
	templateAttributeResponse.setCollection(templateAttribute.getCollection());
	templateAttributeResponse.setWithcapacity(templateAttribute.getWithcapacity());
	templateAttributeResponse.setCapacity(templateAttribute.getCapacity());
	templateAttributeResponse.setMapping(templateAttribute.getMapping());
	templateAttributeResponse.setVersion(templateAttribute.getVersion());
	templateAttributeResponse.setFinalAttribute(templateAttribute.getFinalAttribute());
	templateAttributeResponse.setPassword(templateAttribute.getPassword());
	templateAttributeResponse.setNemonic(templateAttribute.getNemonic());
	templateAttributeResponse.setIdentificator(templateAttribute.getIdentificable());
	templateAttributeResponse.setAlias(templateAttribute.getAlias());
	templateAttributeResponse.setPosition(templateAttribute.getPosition());
	templateAttributeResponse.setVersion(templateAttribute.getVersion());
	templateAttributeResponse.setContentType(templateAttribute.getContentType());

	if(templateAttribute.getTemplate() != null && !fromTemplate)
	{
	    templateAttributeResponse.setTemplate(templateResponseMapper.templateToFullTemplateRespose(templateAttribute.getTemplate()));
	}

	if(templateAttribute.getTemplateReference() != null)
	{
	    if(templateAttribute.getTemplate() != null && templateAttribute.getTemplateReference().getTemplate() != templateAttribute.getTemplate())
	    {

		templateAttributeResponse.setTemplateReference(templateReferenceResponseMapper.templateReferenceToTemplateReferenceRespose(templateAttribute.getTemplateReference()));
	    }
	    else
	    {

		templateAttributeResponse.setTemplateReference(templateReferenceResponseMapper.recursiveTemplateReferenceToTemplateReferenceRespose(templateAttribute.getTemplateReference()));
	    }

	}

	if(templateAttribute.getGroups().contains(TessaConstants.SYSTEM))
	{
	    templateAttributeResponse.setGroups(templateAttribute.getGroups());
	}

	if(templateAttribute.getExpressionParams() != null)
	{

	    templateAttributeResponse.setExpressionParams(expressionParamResponseMapper.expressionParamsToExpressionParamResponseCollection(templateAttribute.getExpressionParams()));
	}

	if(templateAttribute.getExpressionProperties() != null)
	{
	    templateAttributeResponse.setExpressionProperties(functionResponseMapper.functionToFunctionResponse(templateAttribute.getExpressionProperties()));
	}

	if(!CollectionUtils.isEmpty(templateAttribute.getEnumValues()))
	{
	    templateAttributeResponse.setEnums(templateAttribute.getEnumValues());
	}

	return templateAttributeResponse;
    }


    public TemplateAttributeResponse2 templateAttributeToTemplateAttributeRespose2(TemplateAttribute templateAttribute)
    {

	TemplateAttributeResponse2 templateAttributeResponse = new TemplateAttributeResponse2();
	templateAttributeResponse.setId(templateAttribute.getCustomId());
	templateAttributeResponse.setName(templateAttribute.getName());
	templateAttributeResponse.setDescription(templateAttribute.getDescription());
	templateAttributeResponse.setIcon(templateAttribute.getIcon());
	templateAttributeResponse.setInsertDate(templateAttribute.getInsertDate().toInstant(ZoneOffset.UTC));
	if(templateAttribute.getModifDate() != null)
	{
	    templateAttributeResponse.setModifDate(templateAttribute.getModifDate().toInstant(ZoneOffset.UTC));
	}
	templateAttributeResponse.setType(templateAttribute.getType());
	templateAttributeResponse.setMinLength(templateAttribute.getMinLength());
	templateAttributeResponse.setMaxLength(templateAttribute.getMaxLength());
	templateAttributeResponse.setRequired(templateAttribute.getRequired());
	templateAttributeResponse.setHasDefaultValue(templateAttribute.getHasDefaultValue());
	templateAttributeResponse.setDefaultValue(templateAttribute.getDefaultValue());
	templateAttributeResponse.setHasCalculatedValue(templateAttribute.getHasCalculatedValue());
	templateAttributeResponse.setCalculatedValue(templateAttribute.getCalculatedValue());
	templateAttributeResponse.setPattern(templateAttribute.getPattern());
	templateAttributeResponse.setUnique(templateAttribute.getUnique());
	templateAttributeResponse.setExternalSource(templateAttribute.getExternalSource());
	templateAttributeResponse.setCollection(templateAttribute.getCollection());
	templateAttributeResponse.setWithcapacity(templateAttribute.getWithcapacity());
	templateAttributeResponse.setCapacity(templateAttribute.getCapacity());
	templateAttributeResponse.setMapping(templateAttribute.getMapping());
	templateAttributeResponse.setVersion(templateAttribute.getVersion());
	templateAttributeResponse.setFinalAttribute(templateAttribute.getFinalAttribute());
	templateAttributeResponse.setPassword(templateAttribute.getPassword());
	templateAttributeResponse.setNemonic(templateAttribute.getNemonic());
	templateAttributeResponse.setIdentificator(templateAttribute.getIdentificable());
	templateAttributeResponse.setPosition(templateAttribute.getPosition());
	templateAttributeResponse.setVersion(templateAttribute.getVersion());
	templateAttributeResponse.setContentType(templateAttribute.getContentType());

	if(templateAttribute.getTemplate() != null)
	{
	    templateAttributeResponse.setTemplate(templateResponseMapper.templateToFullTemplateRespose(templateAttribute.getTemplate()));
	}

	if(templateAttribute.getTemplateReference() != null)
	{
	    templateAttributeResponse.setTemplateReference(templateReferenceResponseMapper.templateReferenceToTemplateReferenceRespose(templateAttribute.getTemplateReference()));
	}

	return templateAttributeResponse;
    }


    public ResponsePage<TemplateAttributeResponse> templateAttributePageToTemplateAttributeResposePage(ResponsePage<TemplateAttribute> templateAttributes)
    {

	LinkedHashSet<TemplateAttributeResponse> templateAttributesResponse = new LinkedHashSet<>();

	for (TemplateAttribute templateAttribute : templateAttributes)
	{
	    templateAttributesResponse.add(templateAttributeToTemplateAttributeRespose(false, templateAttribute));

	}

	return new ResponsePage<>(templateAttributesResponse, templateAttributes.getPageable(), templateAttributes.getTotalElements());

    }


    public Collection<TemplateAttributeResponse> templateAttributeCollectionToTemplateAttributeResposeCollection(boolean fromTemplate, Collection<TemplateAttribute> templateAttributes)
    {

	Collection<TemplateAttributeResponse> templateAttributesResponse = new HashSet<>();

	for (TemplateAttribute templateAttribute : templateAttributes)
	{
	    templateAttributesResponse.add(templateAttributeToTemplateAttributeRespose(fromTemplate, templateAttribute));

	}

	return templateAttributesResponse;
    }


    public Collection<TemplateAttribute> templateAttributeResponseCollectionToTemplateAttributeCollection(Collection<TemplateAttributeResponse> templateAttributesResponse)
    {

	Collection<TemplateAttribute> templateAttributes = new HashSet<>();

	for (TemplateAttributeResponse templateAttributeResponse : templateAttributesResponse)
	{
	    templateAttributes.add(templateAttributeResponseToTemplateAttribute(templateAttributeResponse));

	}

	return templateAttributes;
    }


    public TemplateAttribute templateAttributeResponseToTemplateAttribute(TemplateAttributeResponse templateAttributeResponse)
    {

	TemplateAttribute templateAttribute = new TemplateAttribute();
	templateAttribute.setId(templateAttributeResponse.getId());
	templateAttribute.setCustomId(templateAttributeResponse.getId());
	templateAttribute.setName(templateAttributeResponse.getName());
	templateAttribute.setDescription(templateAttributeResponse.getDescription());
	templateAttribute.setIcon(templateAttributeResponse.getIcon());
	templateAttribute.setInsertDate(LocalDateTime.ofInstant(templateAttributeResponse.getInsertDate(), ZoneOffset.UTC));
	if(templateAttributeResponse.getModifDate() != null)
	{
	    templateAttribute.setModifDate(LocalDateTime.ofInstant(templateAttributeResponse.getModifDate(), ZoneOffset.UTC));
	}
	templateAttribute.setType(templateAttributeResponse.getType());
	templateAttribute.setMinLength(templateAttributeResponse.getMinLength());
	templateAttribute.setMaxLength(templateAttributeResponse.getMaxLength());
	templateAttribute.setRequired(templateAttributeResponse.getRequired());
	templateAttribute.setHidden(templateAttributeResponse.getHidden());
	templateAttribute.setHasDefaultValue(templateAttributeResponse.getHasDefaultValue());
	templateAttribute.setDefaultValue(templateAttributeResponse.getDefaultValue());
	templateAttribute.setHasCalculatedValue(templateAttributeResponse.getHasCalculatedValue());
	templateAttribute.setCalculatedValue(templateAttributeResponse.getCalculatedValue());
	templateAttribute.setPattern(templateAttributeResponse.getPattern());
	templateAttribute.setUnique(templateAttributeResponse.getUnique());
	templateAttribute.setExternalSource(templateAttributeResponse.getExternalSource());
	templateAttribute.setCollection(templateAttributeResponse.getCollection());
	templateAttribute.setWithcapacity(templateAttributeResponse.getWithcapacity());
	templateAttribute.setCapacity(templateAttributeResponse.getCapacity());
	templateAttribute.setMapping(templateAttributeResponse.getMapping());
	templateAttribute.setVersion(templateAttributeResponse.getVersion());
	templateAttribute.setFinalAttribute(templateAttributeResponse.getFinalAttribute());
	templateAttribute.setPassword(templateAttributeResponse.getPassword());
	templateAttribute.setNemonic(templateAttributeResponse.getNemonic());
	templateAttribute.setIdentificable(templateAttributeResponse.getIdentificator());
	templateAttribute.setAlias(templateAttributeResponse.getAlias());
	templateAttribute.setPosition(templateAttributeResponse.getPosition());
	templateAttribute.setVersion(templateAttributeResponse.getVersion());
	templateAttribute.setContentType(templateAttributeResponse.getContentType());
	if(templateAttributeResponse.getTemplate() != null)
	{
	    templateAttribute.setTemplate(templateResponseMapper.templateResponseToTemplate(templateAttributeResponse.getTemplate()));
	}

	if(templateAttributeResponse.getTemplateReference() != null && templateAttributeResponse.getTemplateReference().getTemplateResponse() != null)
	{
	    templateAttribute.setTemplateReference(templateReferenceResponseMapper.templateReferenceResponseToTemplateReference(templateAttributeResponse.getTemplateReference()));
	}

	if(templateAttributeResponse.getGroups() != null)
	{
	    templateAttribute.setGroups(templateAttributeResponse.getGroups());
	}

	if(templateAttributeResponse.getExpressionParams() != null)
	{
	    templateAttribute.getExpressionParams().addAll(expressionParamResponseMapper.expressionParamCollectionResponseToExpressionParamCollection(templateAttributeResponse.getExpressionParams()));
	}

	if(templateAttributeResponse.getExpressionProperties() != null)
	{
	    templateAttribute.setExpressionProperties(functionResponseMapper.functionResponseToFunction(templateAttributeResponse.getExpressionProperties()));
	}

	return templateAttribute;
    }


    public TemplateAttribute templateAttributeResponseToTemplateAttribute(TemplateAttributeResponse2 templateAttributeResponse)
    {

	TemplateAttribute templateAttribute = new TemplateAttribute();
	templateAttribute.setId(templateAttributeResponse.getId());
	templateAttribute.setCustomId(templateAttributeResponse.getId());
	templateAttribute.setName(templateAttributeResponse.getName());
	templateAttribute.setDescription(templateAttributeResponse.getDescription());
	templateAttribute.setIcon(templateAttributeResponse.getIcon());
	templateAttribute.setInsertDate(LocalDateTime.ofInstant(templateAttributeResponse.getInsertDate(), ZoneOffset.UTC));
	if(templateAttributeResponse.getModifDate() != null)
	{
	    templateAttribute.setModifDate(LocalDateTime.ofInstant(templateAttributeResponse.getModifDate(), ZoneOffset.UTC));
	}
	templateAttribute.setType(templateAttributeResponse.getType());
	templateAttribute.setMinLength(templateAttributeResponse.getMinLength());
	templateAttribute.setMaxLength(templateAttributeResponse.getMaxLength());
	templateAttribute.setRequired(templateAttributeResponse.getRequired());
	templateAttribute.setHasDefaultValue(templateAttributeResponse.getHasDefaultValue());
	templateAttribute.setDefaultValue(templateAttributeResponse.getDefaultValue());
	templateAttribute.setHasCalculatedValue(templateAttributeResponse.getHasCalculatedValue());
	templateAttribute.setCalculatedValue(templateAttributeResponse.getCalculatedValue());
	templateAttribute.setPattern(templateAttributeResponse.getPattern());
	templateAttribute.setUnique(templateAttributeResponse.getUnique());
	templateAttribute.setExternalSource(templateAttributeResponse.getExternalSource());
	templateAttribute.setCollection(templateAttributeResponse.getCollection());
	templateAttribute.setWithcapacity(templateAttributeResponse.getWithcapacity());
	templateAttribute.setCapacity(templateAttributeResponse.getCapacity());
	templateAttribute.setMapping(templateAttributeResponse.getMapping());
	templateAttribute.setVersion(templateAttributeResponse.getVersion());
	templateAttribute.setFinalAttribute(templateAttributeResponse.getFinalAttribute());
	templateAttribute.setPassword(templateAttributeResponse.getPassword());
	templateAttribute.setNemonic(templateAttributeResponse.getNemonic());
	templateAttribute.setIdentificable(templateAttributeResponse.getIdentificator());
	templateAttribute.setPosition(templateAttributeResponse.getPosition());
	templateAttribute.setVersion(templateAttributeResponse.getVersion());
	templateAttribute.setContentType(templateAttributeResponse.getContentType());
	if(templateAttributeResponse.getTemplate() != null)
	{
	    templateAttribute.setTemplate(templateResponseMapper.templateResponseToTemplate(templateAttributeResponse.getTemplate()));
	}

	if(templateAttributeResponse.getTemplateReference() != null && templateAttributeResponse.getTemplateReference().getTemplateResponse() != null)
	{
	    templateAttribute.setTemplateReference(templateReferenceResponseMapper.templateReferenceResponseToTemplateReference(templateAttributeResponse.getTemplateReference()));
	}

	return templateAttribute;
    }


    public ResponsePage<TemplateAttribute> templateAttributeResponsePageToTemplateAttributePage(ResponsePage<TemplateAttributeResponse> templateAttributesResponse)
    {

	List<TemplateAttribute> templateAttributes = new ArrayList<>();

	for (TemplateAttributeResponse templateAttributeResponse : templateAttributesResponse)
	{
	    templateAttributes.add(templateAttributeResponseToTemplateAttribute(templateAttributeResponse));
	}

	return new ResponsePage<>(templateAttributes, templateAttributesResponse.getPageable(), templateAttributesResponse.getTotalElements());

    }


    public ResponsePage<TemplateAttribute> templateAttributeCollectionToTemplateAttributeResposePage(ResponsePageJson<TemplateAttributeResponse> templateAttributesResponse)
    {

	List<TemplateAttribute> templateAttributes = new ArrayList<>();

	PageRequest pageRequest = PageRequest.of(templateAttributesResponse.getPageable().getPageNumber(), templateAttributesResponse.getPageable().getPageSize());

	for (TemplateAttributeResponse templateResponse : templateAttributesResponse.getContent())
	{

	    TemplateAttribute templateAttribute = templateAttributeResponseToTemplateAttribute(templateResponse);

	    templateAttributes.add(templateAttribute);
	}

	return new ResponsePage<>(templateAttributes, pageRequest, templateAttributesResponse.getTotalElements());

    }

}
