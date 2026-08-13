package es.cic.tessa.lookup.expression.model;


public class ExpressionConstants
{

    public static final String PREFIX_DYNAMIC_VALUE = "{";
    public static final String SUBFFIX_DYNAMIC_VALUE = "}";
    public static final String PREFIX_DYNAMIC_ORGANIZER = "[*";
    public static final String SUFIX_EXPRESSION_PARAM = "]";
    public static final String PREFIX_GROUPAL_EXPRESSION = "{{";
    public static final String SUFIX_GROUPAL_EXPRESSION = "}}";
    public static final String PREFIX_EXPRESSION_PARAM = "$[";
    public static final String EXPRESSION_CONDITIONAL = "**";

    public static final String EXPRESSION_CAL = "EXPRESSIONS_CALC";
    public static final String EXPRESSION_DRIVER = "EXPRESSIONS_DRIVER";
    public static final String EXPRESSION_NOTIF = "EXPRESSIONS_NOTIF";

    public static final String ASSIGNED_DRIVER_EXPRESSIONS = "EXPRESSIONS_DRIVER_ASSIGNED";
    public static final String EXPRESSIONS_EVENT = "EXPRESSIONS_EVENT";
    public static final String EXPRESSION_RELATIONS = "EXPRESSION_RELATIONS";
    public static final String EXPRESSION_FUNCTION_RESULT = "EXPRESSIONS_RESULT";
    public static final String CRON_ATTRIBUTE = "CRON";
    public static final String EXPRESSION_FUNCTION_ID = "expressionID";

    public static final String DYNAMIC_ID = "[id]";
    public static final String DYNAMIC_ORGANIZER = "[*]";

    public static final String ATTRIBUTE_PATH = "->";
    public static final String PARENT_PATH = "../";
    public static final String PARENT_PATH_SCAPE = "\\../";
    public static final String NO_CONTENT = "-1";
    public static final String VALUE_SEPARATOR = "#";
    public static final String EXPRESSION_EQUALS = "=";
    public static final String VALUE_SEPARATOR_LOOKUP = ",";
    public static final String UNIQUE_VALUE_COUNT = "1";
    public static final String NEGATIVE_UNIQUE_VALUE_COUNT = "-1";
    public static final String N_A = "N/A";
    public static final String SUSCRIPTION_EXPRESSION_SEPARATOR = "&";
    public static final String CRON_SEPARATOR_EXPRESSION = "|||";
    public static final String CONDITIONAL_EXPRESSION = "**";
    public static final String TRUE_CONDITION_VALUE = "1.0";
    public static final String FALSE_CONDITION_VALUE = "0.0";
    public static final String RESULT_CONDITIONAL_OK = "0.0";

    public static final String ASSET_VALUE_ID_SEPARATOR = "_";
    public static final String EXPRESSION_START_DELIMITER = "_[";
    public static final String EXPRESSION_END_DELIMITER = "]";
    public static final String LOOKUP_EXPRESSION_END_DELIMITER = "]}}";
    public static final String EXPRESSION_NAN = "[NaN]";
    public static final String NAN = "NaN";
    public static final String EXPRESSION_ZERO = "0.0";
    public static final String FUNCTION_SEPARATOR = "==";
    public static final String PARENTHESIS_OPEN = "(";
    public static final String PARENTHESIS_CLOSE = ")";
    public static final Long WILDCARD_LOOKUP_ID = -1L;

    public static final String EXPRESSION_EXPRESSIONS_PATH = "/expressions";
    public static final String WORKER_EXPRESSION_PATH = "/workers";
    public static final String MASTER_EXPRESSION_PATH = "/master";
    public static final String ASSIGN_EXPRESSION_PATH = "/assign";
    public static final String UNASSIGN_EXPRESSION_PATH = "/unassign";
    public static final String REMOVE_EXPRESSION = "remove";

}
