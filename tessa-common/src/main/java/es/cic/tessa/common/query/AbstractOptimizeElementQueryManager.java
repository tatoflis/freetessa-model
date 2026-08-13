package es.cic.tessa.common.query;


import java.util.Collection;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import es.cic.tessa.common.exceptions.TessaException;
import es.cic.tessa.common.filter.OptimizePropertyFilter;
import es.cic.tessa.common.model.TessaConstants;
import es.cic.tessa.common.model.types.ArithmeticOperatorType;
import es.cic.tessa.common.model.types.LogicalOperatorType;
import es.cic.tessa.common.support.PageConfig;


public abstract class AbstractOptimizeElementQueryManager extends AbstractQueryManager
{

    private static final Pattern VALID_LABEL_PATTERN = Pattern.compile("^[a-zA-Z0-9_]+$");

    @Value("${tessa.query.groups-query-threshold:10}")
    private int groupsQueryThreshold;

    protected String escapeCypherString(String value)
    {

	if(value == null)
	{
	    return null;
	}

	return value.replace("\\", "\\\\").replace("'", "\\'");
    }


    protected int getGroupsQueryThreshold()
    {

	return groupsQueryThreshold > 0 ? groupsQueryThreshold : 10;
    }


    protected String buildGroupsCondition(String label, Set<String> groups)
    {

	if(groups.size() > getGroupsQueryThreshold())
	{
	    return buildGroupsConditionOptimized(label, groups);
	}

	return buildGroupsConditionClassic(label, groups);
    }


    private String buildGroupsConditionClassic(String label, Set<String> groups)
    {

	StringBuilder query = new StringBuilder();

	query.append("(");
	for (String group : groups)
	{
	    validateCypherLabel(group);
	    query.append(label);
	    query.append(":");
	    query.append(group);
	    query.append(" OR ");
	}

	query.replace(query.length() - 3, query.length(), ")");

	return query.toString();
    }


    private String buildGroupsConditionOptimized(String label, Set<String> groups)
    {

	StringBuilder query = new StringBuilder();

	query.append("ANY(lbl IN labels(");
	query.append(label);
	query.append(") WHERE lbl IN [");

	boolean first = true;
	for (String group : groups)
	{
	    validateCypherLabel(group);
	    if(!first)
	    {
		query.append(", ");
	    }
	    query.append("'");
	    query.append(group);
	    query.append("'");
	    first = false;
	}

	query.append("])");

	return query.toString();
    }


    private void validateCypherLabel(String label)
    {

	if(label == null || label.isBlank() || !VALID_LABEL_PATTERN.matcher(label).matches())
	{
	    throw new TessaException("Invalid Cypher label: only alphanumeric characters and underscores are allowed");
	}
    }


    protected String buildPagination(PageConfig pageConfig)
    {

	StringBuilder query = new StringBuilder();

	int skip = 0;
	int limit = Integer.MAX_VALUE;

	if(pageConfig != null)
	{
	    skip = pageConfig.getNumPage() * pageConfig.getSizePage();
	    limit = pageConfig.getSizePage();
	}

	query.append(" SKIP ");
	query.append(skip);
	query.append(" ");
	query.append("LIMIT ");
	query.append(limit);
	query.append(" ");

	return query.toString();
    }


    protected String buildPropertyCondition(String nodeAlias, Collection<OptimizePropertyFilter> optimizeProperyFilters)
    {

	StringBuilder query = new StringBuilder();

	for (OptimizePropertyFilter propertyFilter : optimizeProperyFilters)
	{
	    if(propertyFilter.getPropertyName() == null || propertyFilter.getPropertyValue() == null)
	    {
		continue;
	    }

	    if(propertyFilter.getAritmeticalOperatorType().equals(ArithmeticOperatorType.NOT_CONTAINS))
	    {
		query.append("NOT ");
	    }

	    query.append(nodeAlias);
	    query.append(".");
	    query.append(buildPropertyName(propertyFilter));
	    query.append(StringUtils.SPACE);
	    query.append(buildArithmeticalCondition(propertyFilter.getAritmeticalOperatorType()));
	    query.append(StringUtils.SPACE);
	    query.append(buildPropertyValue(propertyFilter));
	    query.append(StringUtils.SPACE);
	    query.append(propertyFilter.getLogicalOperatorType());
	    query.append(StringUtils.SPACE);

	}

	if(query.toString().endsWith(LogicalOperatorType.AND.toString().concat(StringUtils.SPACE)))
	{

	    return query.delete(query.length() - 4, query.length()).toString();
	}

	if(query.toString().endsWith(LogicalOperatorType.OR.toString().concat(StringUtils.SPACE)))
	{

	    return query.delete(query.length() - 3, query.length()).toString();
	}

	return query.toString();
    }


    private String buildPropertyName(OptimizePropertyFilter propertyFilter)
    {

	if(propertyFilter.getPropertyName().equals(TessaConstants.NAME))
	{
	    return TessaConstants.NAME_LOWER;
	}
	else if(propertyFilter.getPropertyName().equals(TessaConstants.VALUE))
	{
	    return TessaConstants.VALUE_LOWER;
	}
	else
	{
	    return propertyFilter.getPropertyName();
	}
    }


    private String buildPropertyValue(OptimizePropertyFilter propertyFilter)
    {

	StringBuilder query = new StringBuilder();

	if(propertyFilter.getAritmeticalOperatorType().equals(ArithmeticOperatorType.EXISTS) || propertyFilter.getAritmeticalOperatorType().equals(ArithmeticOperatorType.NOT_EXISTS))
	{
	    return StringUtils.EMPTY;

	}
	else if(propertyFilter.getPropertyValue() instanceof Boolean || propertyFilter.getPropertyValue() instanceof Number)
	{
	    query.append(StringUtils.SPACE);
	    query.append(String.valueOf(propertyFilter.getPropertyValue()));
	    query.append(StringUtils.SPACE);
	}
	else if(propertyFilter.getPropertyValue() instanceof String)
	{
	    if(propertyFilter.getPropertyName().equals(TessaConstants.NAME) || propertyFilter.getPropertyName().equals(TessaConstants.VALUE))
	    {
		query.append("'");
		query.append(escapeCypherString(String.valueOf(propertyFilter.getPropertyValue()).toLowerCase()));
		query.append("'");
	    }
	    else
	    {
		query.append("'");
		query.append(escapeCypherString(String.valueOf(propertyFilter.getPropertyValue())));
		query.append("'");
	    }
	}
	else
	{
	    throw new TessaException("Type unknowed : {}", propertyFilter.getPropertyName());
	}

	return query.toString();
    }


    private String buildArithmeticalCondition(ArithmeticOperatorType arithmeticOperatorType)
    {

	switch (arithmeticOperatorType)
	{
	case EQUALS:
	{
	    return "=";
	}
	case CONTAINS:
	{
	    return "CONTAINS";
	}
	case NOT_CONTAINS:
	{
	    return "CONTAINS";
	}
	case MAYOR:
	{
	    return ">";
	}
	case MINOR:
	{
	    return "<";
	}
	case NOT:
	{
	    return "<>";
	}
	case MAYOR_EQUALS:
	{
	    return ">=";
	}
	case MINOR_EQUALS:
	{
	    return "<=";

	}
	case STARTS_WITH:
	{
	    return "STARTS WITH";

	}
	case ENDS_WITH:
	{
	    return "ENDS WITH";

	}
	case EXISTS:
	{
	    return "IS NOT NULL";

	}
	case NOT_EXISTS:
	{
	    return "IS NULL";

	}
	default:
	{
	    throw new TessaException("Aritmethic operation " + arithmeticOperatorType + " of Optimized filter not supported");
	}
	}
    }
}
