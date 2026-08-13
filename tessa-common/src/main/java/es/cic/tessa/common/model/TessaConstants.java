package es.cic.tessa.common.model;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class TessaConstants
{

    public static final String NEMONIC_ROOT_ORGANIZER = "_RootOrganizer_";
    public static final String SYSTEM = "System";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String NAME_LOWER = "nameLower";
    public static final String NEMONIC = "nemonic";
    public static final String TYPE = "type";
    public static final String TYPE_LOWER = "typeLower";
    public static final String ABSTRACT = "abstract";
    public static final String FINAL = "final";
    public static final String IDENTIFICATOR = "identificator";
    public static final String IDENTIFICATOR_LOWER = "identificatorLower";
    public static final String ATTRIBUTE_NAME_LOWER = "attributeNameLower";
    public static final String POSITION = "position";
    public static final String VALUE = "value";
    public static final String VALUE_LOWER = "valueLower";
    public static final String PASSWORD = "********";
    public static final String INSERT = "insert";
    public static final String MODIFICATION = "modification";
    public static final String STARTCHANGE_PROPERTY = "startChange";
    public static final String ENDCHANGE_PROPERTY = "endChange";
    public static final String HISTORICAL_OP_CREATE = "Create";
    public static final String HISTORICAL_OP_UPDATE = "Update";
    public static final String HISTORICAL_OP_DELETE = "Delete";
    public static final String EXPRESSION_FUNCTION = "expressionFunction";
    public static final String EXPRESSION_PROPERTIES = "expressionProperties";
    public static final String EXPRESSION_EVENT = "expressionEvent";
    public static final String CRON_EXPRESSION = "cronExpression";
    public static final String HIDDEN = "hidden";
    public static final String CALCULATED_VALUE = "calculatedValue";

    public static final String TESSA_EXPRESSION_PATH = "/tessa";

    public static final String INSTANCES = "/instances";
    public static final String CHANGES = "/changes";

    // public static final String INSTANCES_TESSA = "/tessa";
    public static final String INSTANCES_TESSA_CORE = "/tessa-core";
    public static final String INSTANCES_TESSA_EXCEL = "/tessa-excel";
    public static final String INSTANCES_TESSA_LOOKUP = "/tessa-lookup";

    public static final String TESSA_ASSET_PATH = "/asset";
    public static final String TESSA_ASSET_VALUE_PATH = "/assetvalue";
    public static final String TESSA_TEMPLATE_PATH = "/template";
    public static final String TESSA_TEMPLATE_ATTRIBUTE_PATH = "/templateattribute";
    public static final String TESSA_ASSET_ORGANIZER_PATH = "/assetorganizer";
    public static final String TESSA_OPERATION_CREATED = "created";
    public static final String TESSA_OPERATION_UPDATED = "updated";
    public static final String TESSA_OPERATION_DELETED = "deleted";
    public static final String TESSA_OPERATION_CONFIRM_DELETED = "confirm-deleted";
    public static final String TESSA_PATH_SEPARATOR = "/";

    public static final Set<String> SYSTEM_GROUP = new HashSet<String>(Arrays.asList(TessaConstants.SYSTEM));

    public static final String DATA_NOT_AVAILABLE = "N/A";

    public static final String PATH_SEPARATOR = "||";

    public static final String PREFIX_DYNAMIC_VALUE = "{";
    public static final String SUBFFIX_DYNAMIC_VALUE = "}";
    public static final String DYNAMIC_VALUE_PATH_SEPARATOR_DOWN = "->";
    public static final String DYNAMIC_VALUE_PATH_SEPARATOR_UP = "../";

    // Alarms
    public static final String TEMPLATE_ALARM_DEFINITION = "TessaAlarmDefinition";
    public static final String TEMPLATE_ALARM_LEVEL = "TessaAlarmLevel";
    public static final String TEMPLATE_ALARM_SEVERITY = "TessaSeverity";

    public static final String TIMER_PULSE = "Timer pulse";
    public static final String TIMER_ON_DELAY = "Timer on delay";
    public static final String TIMER_OFF_DELAY = "Timer off delay";
    public static final String INTERLOCK = "Interlock";

    public static final String SEVERITY_LEVEL_ATTRIBUTE = "Code";
    public static final String ALARM_LEVEL_SEVERITY_ATTRIBUTE = "Severity";
    public static final String ALARM_LEVEL_DEFINITION_ATTRIBUTE = "Definition";

    public static final Integer NORMAL_SEVERITY_CODE = 0;

    // TODO ponerlo en la ultima posicion
    public static final String TEMPLATE_ATTRIBUTE_CONNECTION_TYPE_DB = "system";

    private TessaConstants()
    {

    }
}
