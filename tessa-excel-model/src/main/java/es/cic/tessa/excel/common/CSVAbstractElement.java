package es.cic.tessa.excel.common;


import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;


public abstract class CSVAbstractElement
{

    protected static final Logger LOGGER = LoggerFactory.getLogger(CSVAbstractElement.class);

    protected static final String SEPARATOR = ":";

    protected static final String LOAD_CSV_FROM = "LOAD CSV WITH HEADERS FROM \"";
    protected static final String AS_ROW = "\" AS row CALL { with row";
    protected static final String START_CALL = " AS line CALL { WITH line CALL { WITH line WITH line AS row ";
    protected static final String MIDDLE_CALL = " } CALL { WITH line WITH line AS row ";
    protected static final String IN_TRANSACCTIONS = "}} IN TRANSACTIONS OF ";
    protected static final String ROWS = " ROWS";

    @Value("${tessa.excel.import-csv.url:#{null}}")
    protected String hostCSV;

    @Value("${tessa.excel.import-csv.file:#{null}}")
    protected String fileCSV;

    @Value("${tessa.excel.rowsPerTransaction}")
    protected int rowsPerTransactions;

    protected String buildGroups(Set<String> groups)
    {

	StringBuilder variable = new StringBuilder();

	for (String group : groups)
	{
	    sanitizeGroup(group);
	    variable.append("`");
	    variable.append(group);
	    variable.append("`");
	    variable.append(":");

	}

	return variable.substring(0, variable.length() - 1);
    }


    protected String buildSearchGroups(Set<String> groups)
    {

	StringBuilder variable = new StringBuilder();

	for (String group : groups)
	{
	    sanitizeGroup(group);
	    variable.append("'");
	    variable.append(group);
	    variable.append("'");
	    variable.append(",");

	}

	return variable.substring(0, variable.length() - 1);
    }

    private void sanitizeGroup(String group)
    {
	if (group == null || !group.matches("[a-zA-Z0-9_\\-\\. ]+"))
	{
	    throw new IllegalArgumentException("Invalid group name: contains unsafe characters");
	}
    }


    protected String getValue(Object value)
    {

	if(value == null)
	{
	    return StringUtils.EMPTY;
	}
	else
	{
	    String valueString = String.valueOf(value);
	    valueString = valueString.replaceAll("[\u0000-\u0009\u000B\u000C\u000E-\u001F\u007F]", "");
	    valueString = valueString.replaceAll("[\n\r]", " ");
	    valueString = valueString.replace("\"", "\"\"");
	    return valueString;
	}
    }


    protected int getBoolean(Object value)
    {

	if(Boolean.TRUE.equals(value))
	{
	    return 1;
	}
	else
	{
	    return 0;
	}
    }

}
