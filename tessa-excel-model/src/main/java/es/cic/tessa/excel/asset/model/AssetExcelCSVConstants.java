package es.cic.tessa.excel.asset.model;


public class AssetExcelCSVConstants
{

    // Update
    public static final String UPDATE_ASSETS = "Update assets";
    public static final String UPDATE_ASSET_VALUES = "Update Asset values";
    public static final String UPDATE_REMOVE_ASSET_VALUES = "Update Remove Asset values";
    public static final String UPDATE_ASSET_VALUES_RELATIONS = "Update Asset values create relations";
    public static final String UPDATE_ASSET_VALUE_RELATION_TEMPLATE_ATTRIBUTE = "Update Asset value relation template attribute";
    public static final String UPDATE_REMOVE_ORGANIZERS = "Remove organizers";
    public static final String UPDATE_ASSET_ORGANIZERS = "Update Asset organizers";
    public static final String UPDATE_REMOVE_ASSET_VALUE_REFERENCES = "Remove asset value references";
    public static final String UPDATE_ASSET_VALUE_REFERENCES = "Update asset value references";
    public static final String UPDATE_REMOVE_ASSET_METADATA = "Remove asset metadata";
    public static final String UPDATE_ASSET_METADATA = "Update asset metadata";

    // Historical Update
    public static final String CREATE_HISTORICAL_ASSETS = "Create historical assets";
    public static final String FINALIZE_HISTORICAL_CHANGE = "Finalize historical asset change";
    public static final String CREATE_HISTORICAL_RELATION_ASSETS = "Create historical relation with original asset";
    public static final String CREATE_HISTORICAL_ASSET_VALUES = "Create historical asset values";
    public static final String FINALIZE_HISTORICAL_VALUE_CHANGE = "Finalize historical value change";
    public static final String CREATE_HISTORICAL_ASSET_VALUES_RELATIONS = "Create historical asset values relations";
    public static final String HISTORICAL_ASSET_HISTORICAL_VALUES_RELATIONS = "Create historical asset values relations with asset";
    public static final String HISTORICAL_VALUE_HISTORICAL_ATTRIBUTE_RELATIONS = "Create historical asset values relations with template attribute";
    public static final String HISTORICAL_VALUE_HISTORICAL_ATTRIBUTE_SYSTEM_RELATIONS = "Create historical asset values relations with system template attribute";
    public static final String HISTORICAL_ASSET_TEMPLATE_RELATIONS = "Create historical asset template relations";
    public static final String HISTORICAL_ASSET_TEMPLATE_RELATIONS_SYSTEM = "Create historical asset template system relations";
    public static final String UPDATE_HISTORICAL_ASSET_VALUE_RELATION_TEMPLATE_ATTRIBUTE = "Update historical asset value relation template attribute";
    public static final String UPDATE_HISTORICAL_ASSET_ORGANIZERS = "Update historical asset organizers";
    public static final String UPDATE_HISTORICAL_ASSET_VALUE_REFERENCES = "Update historical asset value references";
    public static final String UPDATE_HISTORICAL_ASSET_METADATA = "Update historical asset metadata";

    // Ficheros operacion update

    public static final String UPDATE_ASSET_VALUES_FILE = "UpdateAssetValues.csv";
    public static final String UPDATE_ASSET_ORGANIZERS_FILE = "UpdateAssetOrganizes.csv";
    public static final String UPDATE_ASSET_VALUE_REFERENCE_FILE = "UpdateAssetValueReferences.csv";
    public static final String UPDATE_ASSET_METADATA_FILE = "UpdateAssetMetadata.csv";
    public static final String DELETE_ASSETS = "Delete assets";

    // TODO constantes de la parte superior para borrar cuando se eliminen
    // metodos deprecados

    // nuevas constantes para la unificacion de CSV a conservar

    public static final String ASSETS_FILE = "Assets.csv";
    public static final String UPDATE_ASSETS_FILE = "UpdateAssets.csv";
    public static final String DELETE_ASSETS_FILE = "DeleteAssets.csv";

    public static final String ASSET_HEADERS = "A_Id,A_Name,A_Description,A_Nemonic,A_Icon,A_Insert,A_Modification,A_Active,A_Version,A_Identificator,A_Nemonic_Template,A_Depends_Asset_Nemonic,A_Physical_Path,A_ChangeDate";
    public static final String ASSET_VALUE_HEADERS = "AV_Id,AV_Name,AV_Description,AV_Nemonic,AV_Icon,AV_Insert,AV_Modification,AV_Version,AV_Value,AV_Alias,AV_Function,AV_Nemonic_TemplateAttribute,AV_Nemonic_Asset,AV_Mapping_Attribute,AV_Nemonic_Asset_Reference,AV_Relation_Type,AV_Reference_Type";
    public static final String ASSET_VALUE_EXPRESSION_PARAM_HEADERS = "AVEP_Id,AVEP_Name,AVEP_Nemonic,AVEP_Insert,AVEP_Modification,AVEP_Version,AVEP_Type,AVEP_Position,AVEP_Required,AVEP_Nemonic_AssetValue";
    public static final String ASSET_VALUE_DEFAULT_VALUES_HEADERS = "AVDV_Id,AVDV_Name,AVDV_Nemonic,AVDV_Insert,AVDV_Modification,AVDV_Version,AVDV_Value,AVDV_Nemonic_ExpressionParam";
    public static final String ASSET_ORGANIZER_HEADERS = "Nemonic_Organizer,Nemonic_Asset";
    public static final String ASSET_METADATA_HEADERS = "M_Nemonic_Organizer,M_Nemonic_Asset";

    public static final String CYPHER_ASSET_AND_VALUES_NODES = "Assets and AssetValues";
    public static final String CYPHER_ASSET_VALUE_NODES = "Asset";
    public static final String CYPHER_ASSET_RELATIONS = "Relations of Assets hierarchy";
    public static final String CYPHER_PHYSICAL_PATH_OF_ASSETS = "Physical path of Assets";
    public static final String CYPHER_HISTORICAL_ASSET_NODES = "Historical Asset Nodes";
    public static final String CYPHER_HISTORICAL_ASSET_RELATIONS = "Historical Asset Relations";
    public static final String CYPHER_ASSET_UPDATED_NODES = "Asset Updated";
    public static final String CYPHER_ASSET_UPDATED_RELATIONS = "Asset Relations Updated";
    public static final String CYPHER_ASSET_DELETED_NODES = "Asset Deleted";

}
