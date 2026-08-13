package es.cic.tessa.excel.template.model;


public class TemplateCSVConstants
{

    public static final String TEMPLATE_HEADERS = "T_Id,T_Name,T_Description,T_Nemonic,T_Icon,T_Insert,T_Modification,T_Version,T_Final,T_Abstract,T_AssetOrganized,T_TemplateOrganized,T_Type,T_Nemonic_Template_Extends,T_Nemonic_Organizer";
    public static final String TEMPLATE_ATTRIBUTE_HEADERS = "TA_Id,TA_Name,TA_Description,TA_Nemonic,TA_Icon,TA_Insert,TA_Modification,TA_Version,TA_Position,TA_Identificator,TA_Alias,TA_Type,TA_Enum,TA_Final,TA_MinLength,TA_MaxLength,TA_Required,TA_Hidden,TA_HasDefaultValue,TA_DefaultValue,TA_HasCalculatedValue,TA_CalculatedValue,TA_Function,TA_Pattern,TA_Unique,TA_ExternalSource,TA_Collection,TA_WithCapacity,TA_Capacity,TA_Mapping,TA_Nemonic_TemplateReference,TA_ReferenceType,TA_Relation_Type,TA_Nemonic_Template";
    public static final String MAPPING_HEADERS = "CM_Id,CM_Name,CM_Description,CM_Nemonic,CM_Icon,CM_Insert,CM_Modification,CM_Version,CM_Position,CM_HasCalculatedValue,CM_CalculatedValue,CM_Nemonic_TemplateAttribute";
    public static final String HASHTAG_HEADERS = "H_Id,H_Name,H_Description,H_Nemonic,H_Icon,H_Insert,H_Modification,H_Version,H_Value,H_Nemonic_TemplateAttribute";
    public static final String EXPRESSION_PARAMS_HEADERS = "EP_Id,EP_Name,EP_Description,EP_Nemonic,EP_Icon,EP_Insert,EP_Modification,EP_Version,EP_Type,EP_Required,EP_Position,EP_Nemonic_TemplateAttribute";
    public static final String DEFAULT_VALUES_HEADERS = "DV_Id,DV_Name,DV_Description,DV_Nemonic,DV_Icon,DV_Insert,DV_Modification,DV_Version,DV_Value";
    public static final String ORGANIZER_HEADERS = "Nemonic_Organizer,Nemonic_Template";

    public static final String TEMPLATES_FILE = "Templates.csv";

    public static final String CYPHER_TEMPLATE_NODES = "Template Nodes";
    public static final String CYPHER_TEMPLATE_RELATIONS = "Template Relations";

    public static final String CYPHER_HISTORICAL_TEMPLATE_NODES = "Historical Template Nodes";
    public static final String CYPHER_HISTORICAL_TEMPLATE_RELATIONS = "Historical Template Relations";

    private TemplateCSVConstants()
    {

	super();
    }
}
