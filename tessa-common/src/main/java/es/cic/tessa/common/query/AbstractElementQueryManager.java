package es.cic.tessa.common.query;


import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.neo4j.cypherdsl.core.AliasedExpression;
import org.neo4j.cypherdsl.core.Condition;
import org.neo4j.cypherdsl.core.Cypher;
import org.neo4j.cypherdsl.core.Expression;
import org.neo4j.cypherdsl.core.FunctionInvocation;
import org.neo4j.cypherdsl.core.IdentifiableElement;
import org.neo4j.cypherdsl.core.NamedPath;
import org.neo4j.cypherdsl.core.Node;
import org.neo4j.cypherdsl.core.Property;
import org.neo4j.cypherdsl.core.Relationship;
import org.neo4j.cypherdsl.core.SortItem;
import org.neo4j.cypherdsl.core.Statement;
import org.neo4j.cypherdsl.core.StatementBuilder.OngoingReading;
import org.neo4j.cypherdsl.core.StatementBuilder.OngoingReadingAndReturn;
import org.neo4j.cypherdsl.core.StatementBuilder.OngoingReadingWithWhere;
import org.neo4j.cypherdsl.core.StatementBuilder.OngoingReadingWithoutWhere;
import org.neo4j.cypherdsl.core.StatementBuilder.OrderableOngoingReadingAndWith;
import org.neo4j.cypherdsl.core.SymbolicName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import es.cic.tessa.common.exceptions.TessaException;
import es.cic.tessa.common.filter.AttributeOrder;
import es.cic.tessa.common.filter.PropertyFilter;
import es.cic.tessa.common.model.Labels;
import es.cic.tessa.common.model.RelationshipProperties;
import es.cic.tessa.common.model.TessaConstants;
import es.cic.tessa.common.model.types.ArithmeticOperatorType;
import es.cic.tessa.common.model.types.LogicalOperatorType;
import es.cic.tessa.common.model.types.OrderType;
import es.cic.tessa.common.model.types.SystemScopeType;
import es.cic.tessa.common.support.PageConfig;


public abstract class AbstractElementQueryManager extends AbstractQueryManager
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractElementQueryManager.class);

    @Value("${tessa.query.groups-query-threshold:10}")
    private int groupsQueryThreshold;

    public Node buildNode(String abstractElement, String alias)
    {

	return Cypher.node(abstractElement).named(alias);
    }

    private static final Set<String> RESERVED_LABELS = Arrays.stream(Labels.class.getDeclaredFields()).filter(f -> f.getType().equals(String.class)).map(f -> {
	try
	{
	    return (String) f.get(null);
	}
	catch (IllegalAccessException e)
	{
	    throw new RuntimeException(e);
	}
    }).collect(Collectors.toSet());

    protected Set<SortItem> buildOrder(Node node, Collection<PropertyFilter> ordersPropertyFilter)
    {

	Set<SortItem> sortItems = new HashSet<>();

	if(ordersPropertyFilter != null)
	{
	    for (PropertyFilter propertyFilter : ordersPropertyFilter)
	    {

		if(propertyFilter.getAttributesOrder() != null)
		{
		    for (AttributeOrder order : propertyFilter.getAttributesOrder())
		    {

			String attributeName = QueryManagerUtils.propertyOrderName(order.getAttributeName());

			if(order.getOrderType().equals(OrderType.ASC))
			{
			    sortItems.add(Cypher.sort(node.property(attributeName)).ascending());
			}
			else
			{
			    sortItems.add(Cypher.sort(node.property(attributeName)).descending());
			}
		    }
		}
	    }
	}

	return sortItems;

    }


    protected Collection<PropertyFilter> generateDefaultOrder()
    {

	Set<PropertyFilter> filters = new HashSet<>();

	Set<AttributeOrder> attributeOrders = new HashSet<>();

	AttributeOrder attributeOrder = new AttributeOrder(TessaConstants.NAME, OrderType.ASC);
	attributeOrders.add(attributeOrder);
	PropertyFilter propertyFilter = new PropertyFilter(attributeOrders);

	filters.add(propertyFilter);
	return filters;

    }


    protected OngoingReadingAndReturn buildPagination(OngoingReadingAndReturn returning, PageConfig pageConfig)
    {

	int skip = 0;
	int limit = Integer.MAX_VALUE;

	if(pageConfig != null)
	{
	    skip = pageConfig.getNumPage() * pageConfig.getSizePage();
	    limit = pageConfig.getSizePage();
	}

	returning.skip(skip).limit(limit);

	return returning;
    }


    protected int getGroupsQueryThreshold()
    {

	return groupsQueryThreshold > 0 ? groupsQueryThreshold : 10;
    }


    protected Condition buildGroups(Node node, Set<String> groups)
    {

	if(CollectionUtils.isEmpty(groups))
	{
	    throw new TessaException("No groups defined for node {}", node.getRequiredSymbolicName().getValue());
	}

	Set<String> filteredGroups = groups.stream().filter(Objects::nonNull).map(String::trim).filter(g -> !g.isEmpty()).filter(g -> !RESERVED_LABELS.contains(g)).collect(Collectors.toCollection(LinkedHashSet::new));

	if(filteredGroups.isEmpty())
	{
	    throw new TessaException("No valid groups defined for query {}", filteredGroups.toString());
	}

	if(filteredGroups.size() > getGroupsQueryThreshold())
	{
	    return buildGroupsOptimized(node, filteredGroups);
	}

	return buildGroupsClassic(node, filteredGroups);
    }


    /**
     * Variante de {@link #buildGroups(Node, Set)} que permite decidir como se combinan
     * los grupos del usuario con el grupo {@code System} (plantillas/atributos de producto,
     * no editables por el usuario). No cambia el comportamiento de
     * {@link #buildGroups(Node, Set)}, que sigue equivaliendo a invocar este metodo con
     * {@link SystemScopeType#ALL}.
     */
    protected Condition buildGroups(Node node, Set<String> groups, SystemScopeType systemScopeType)
    {

	SystemScopeType effectiveScope = systemScopeType != null ? systemScopeType : SystemScopeType.ALL;

	if(effectiveScope == SystemScopeType.ONLY_SYSTEM)
	{
	    return buildGroups(node, TessaConstants.SYSTEM_GROUP);
	}

	if(effectiveScope == SystemScopeType.EXCLUDE_SYSTEM)
	{
	    Condition groupsCondition = buildGroups(node, groups);
	    return groupsCondition.and(node.hasLabels(TessaConstants.SYSTEM).not());
	}

	Set<String> allGroups = new LinkedHashSet<>();
	if(groups != null)
	{
	    allGroups.addAll(groups);
	}
	allGroups.add(TessaConstants.SYSTEM);

	return buildGroups(node, allGroups);
    }


    private Condition buildGroupsClassic(Node node, Set<String> filteredGroups)
    {

	Iterator<String> iterator = filteredGroups.iterator();
	Condition groupCondition = node.hasLabels(iterator.next());

	while (iterator.hasNext())
	{
	    groupCondition = groupCondition.or(node.hasLabels(iterator.next()));
	}

	return groupCondition.asCondition();
    }


    private Condition buildGroupsOptimized(Node node, Set<String> filteredGroups)
    {

	SymbolicName lbl = Cypher.name("lbl");
	Expression labelsFunction = Cypher.call("labels").withArgs(node.asExpression()).asFunction();
	List<Expression> groupLiterals = filteredGroups.stream().map(Cypher::literalOf).collect(Collectors.toList());
	Expression groupList = Cypher.listOf(groupLiterals.toArray(new Expression[0]));

	return Cypher.any(lbl).in(labelsFunction).where(lbl.in(groupList)).asCondition();
    }


    protected Condition buildCondition(Node node, PropertyFilter propertyFilter)
    {

	if(propertyFilter.getPropertyName().equals(TessaConstants.ID))
	{
	    return buildNodeId(node, Long.parseLong(String.valueOf(propertyFilter.getPropertyValue())));
	}

	if(propertyFilter.getPropertyName().equalsIgnoreCase(TessaConstants.MODIFICATION) || propertyFilter.getPropertyName().equalsIgnoreCase(TessaConstants.INSERT))
	{

	    return buildNodeDateTime(node, propertyFilter.getPropertyValue().toString(), propertyFilter.getPropertyName(), propertyFilter.getArithmeticOperatorType());

	}

	if(propertyFilter.getPropertyName().equals(TessaConstants.IDENTIFICATOR))
	{
	    propertyFilter.setPropertyName(TessaConstants.IDENTIFICATOR);
	    propertyFilter.setPropertyValue(propertyFilter.getPropertyValue().toString());
	}
	else if(propertyFilter.getPropertyName().equalsIgnoreCase(TessaConstants.IDENTIFICATOR_LOWER))
	{
	    propertyFilter.setPropertyName(TessaConstants.IDENTIFICATOR_LOWER);
	    propertyFilter.setPropertyValue(extractValue(propertyFilter));
	}
	else if(propertyFilter.getPropertyName().equalsIgnoreCase(TessaConstants.NAME))
	{
	    propertyFilter.setPropertyName(TessaConstants.NAME_LOWER);
	    propertyFilter.setPropertyValue(extractValue(propertyFilter));
	}
	else if(propertyFilter.getPropertyName().equalsIgnoreCase(TessaConstants.VALUE))
	{
	    propertyFilter.setPropertyName(TessaConstants.VALUE_LOWER);
	    propertyFilter.setPropertyValue(extractValue(propertyFilter));

	}
	else if(propertyFilter.getPropertyName().equalsIgnoreCase(TessaConstants.ATTRIBUTE_NAME_LOWER))
	{
	    propertyFilter.setPropertyValue(extractValue(propertyFilter));
	}

	return buildArithmeticalCondition(node.property(propertyFilter.getPropertyName()), getLiteral(propertyFilter.getPropertyValue()), propertyFilter.getArithmeticOperatorType());

    }


    private String extractValue(PropertyFilter propertyFilter)
    {

	if(propertyFilter.getPropertyValue() instanceof Boolean || propertyFilter.getPropertyValue() instanceof Number)
	{
	    return String.valueOf(propertyFilter.getPropertyValue());
	}
	else if(propertyFilter.getPropertyValue() instanceof String)
	{
	    return ((String) propertyFilter.getPropertyValue()).toLowerCase();
	}
	else
	{
	    throw new TessaException("Type unknowed : {}", propertyFilter.getPropertyName());
	}
    }


    protected Condition buildNodeId(Node node, Long id)
    {

	return node.property(TessaConstants.ID).eq(Cypher.literalOf(id));

    }


    protected Condition buildNodeId(Node node, Collection<Long> ids)
    {

	return node.property(TessaConstants.ID).in(Cypher.literalOf(ids));
    }


    protected Condition buildNodeDateTime(Node node, String dateTime, String property, ArithmeticOperatorType arithmeticOperatorType)
    {

	Expression nodeDateTime = Cypher.call("datetime").withArgs(node.property(property)).asFunction();
	Expression targetDateTime = Cypher.call("dateTime").withArgs(Cypher.literalOf(dateTime)).asFunction();
	return buildArithmeticalCondition(nodeDateTime, targetDateTime, arithmeticOperatorType);
    }


    public Statement deleteNode(String node, String prefix, Set<String> groups, String... relationsFrom)
    {

	return deleteNode(node, prefix, null, groups, relationsFrom);
    }


    public Statement deleteNode(String node, String prefix, PropertyFilter propertyFilter, Set<String> groups, String... relationsFrom)
    {

	return deleteNode(node, prefix, null, propertyFilter, groups, relationsFrom);
    }


    public Statement deleteNode(String node, String prefix, Long id, PropertyFilter propertyFilter, Set<String> groups, String... relationsFrom)
    {

	Node n = Cypher.node(node).named(prefix);

	OngoingReadingWithoutWhere match = Cypher.match(n);

	Condition buildGroups = buildGroups(n, groups);

	OngoingReadingWithWhere where = match.where(buildGroups);

	if(id != null)
	{
	    Condition condition = buildNodeId(n, id);
	    where = where.and(condition);
	}

	if(propertyFilter != null)
	{
	    if(propertyFilter.getPropertyName() != null || propertyFilter.getPropertyValue() != null)
	    {
		Condition condition = buildCondition(n, propertyFilter);
		where = where.and(condition);
	    }

	}

	for (String relationFrom : relationsFrom)
	{
	    Node r = Cypher.node(relationFrom);
	    match.with(n).optionalMatch(n.relationshipFrom(r));
	}

	FunctionInvocation count = Cypher.count(n);

	OngoingReadingAndReturn returning = match.callInTransactions(Cypher.with(prefix).detachDelete(prefix).build(), 1000).returning(count);

	LOGGER.debug("Delete node {}", returning.build().getCypher());
	return returning.build();
    }


    protected Condition buildLogicalCondition(Condition condition1, Condition condition2, LogicalOperatorType logicalOperatorType)
    {

	if(condition2 == null)
	{
	    return condition1;
	}

	Condition logicalCondition = null;

	switch (logicalOperatorType)
	{
	case AND:
	{
	    logicalCondition = condition1.and(condition2);
	    break;
	}
	case OR:
	{
	    logicalCondition = condition1.or(condition2);
	    break;
	}

	}

	return logicalCondition;
    }


    protected Condition buildConditionFromPropertyFilter(Node node, Collection<PropertyFilter> propertyFilters, Set<String> groups)
    {

	Condition conditionGroups = buildGroups(node, groups);

	Condition conditions = null;

	if(propertyFilters != null)
	{

	    for (PropertyFilter propertyFilter : propertyFilters)
	    {
		if(propertyFilter.getPropertyName() == null || propertyFilter.getPropertyValue() == null)
		{
		    continue;
		}

		if(conditions == null)
		{
		    conditions = buildCondition(node, propertyFilter);
		}
		else
		{
		    conditions = buildLogicalCondition(conditions, buildCondition(node, propertyFilter), propertyFilter.getLogicalOperatorType());
		}

	    }
	}

	if(conditions == null)
	{
	    return conditionGroups;
	}
	else
	{
	    return conditions.and(conditionGroups).asCondition();
	}
    }


    protected OngoingReadingWithWhere buildNodeWhere(Node node, List<PropertyFilter> propertyFilters, boolean optional, Set<String> groups)
    {

	Condition conditionGroups = buildGroups(node, groups);

	OngoingReadingWithWhere whereNode = null;

	if(!optional)
	{
	    whereNode = Cypher.match(node).where(conditionGroups);
	}
	else
	{

	    whereNode = Cypher.optionalMatch(node).where(conditionGroups);
	}

	Condition conditions = null;

	if(propertyFilters != null)
	{
	    if(!propertyFilters.isEmpty())
	    {

		for (PropertyFilter propertyFilter : propertyFilters)
		{
		    if(propertyFilter.getPropertyName() == null || propertyFilter.getPropertyValue() == null)
		    {
			continue;
		    }

		    if(conditions == null)
		    {
			conditions = buildCondition(node, propertyFilter);
		    }
		    else
		    {
			conditions = buildLogicalCondition(conditions, buildCondition(node, propertyFilter), propertyFilter.getLogicalOperatorType());
		    }

		}

	    }
	}

	if(conditions != null)
	{
	    whereNode = whereNode.and(conditions);
	}

	return whereNode;
    }


    protected OngoingReadingWithWhere buildNodeWhere(Node node, Collection<Long> ids, boolean optional)
    {

	OngoingReadingWithWhere whereNode = null;

	Condition idsAssetCondition = buildNodeId(node, ids);

	if(!optional)
	{
	    whereNode = Cypher.match(node).where(idsAssetCondition);
	}
	else
	{

	    whereNode = Cypher.optionalMatch(node).where(idsAssetCondition);
	}

	return whereNode;
    }


    protected Condition buildConditions(Node node, Collection<PropertyFilter> propertyFilters, Set<String> groups)
    {

	Condition conditionGroups = null;

	if(groups != null)
	{
	    conditionGroups = buildGroups(node, groups);
	}

	return buildConditionsWithGroupsCondition(node, propertyFilters, conditionGroups);
    }


    /**
     * Variante de {@link #buildConditions(Node, Collection, Set)} que permite decidir como se
     * combinan los grupos del usuario con el grupo {@code System} (via
     * {@link #buildGroups(Node, Set, SystemScopeType)}). No cambia el comportamiento de
     * {@link #buildConditions(Node, Collection, Set)}, que sigue construyendo la condicion de
     * grupos con {@link #buildGroups(Node, Set)} (sin combinar con System).
     */
    protected Condition buildConditions(Node node, Collection<PropertyFilter> propertyFilters, Set<String> groups, SystemScopeType systemScopeType)
    {

	Condition conditionGroups = null;

	if(groups != null)
	{
	    conditionGroups = buildGroups(node, groups, systemScopeType);
	}

	return buildConditionsWithGroupsCondition(node, propertyFilters, conditionGroups);
    }


    private Condition buildConditionsWithGroupsCondition(Node node, Collection<PropertyFilter> propertyFilters, Condition conditionGroups)
    {

	Condition conditions = null;

	if(propertyFilters != null)
	{
	    // Performance: collapse consecutive EQUALS on the same property
	    // when each next filter is linked with OR
	    // (name='a' OR name='b') -> name IN ['a','b']. Preserve list order
	    // and real AND/OR between properties.
	    List<PropertyFilter> ordered = new ArrayList<>(propertyFilters);
	    int index = 0;
	    while (index < ordered.size())
	    {
		PropertyFilter pf = ordered.get(index);
		if(pf.getPropertyName() == null || pf.getPropertyValue() == null)
		{
		    index++;
		    continue;
		}

		boolean equalsLike = pf.getArithmeticOperatorType() == ArithmeticOperatorType.EQUALS && !pf.getPropertyName().equals(TessaConstants.ID) && !pf.getPropertyName().equalsIgnoreCase(TessaConstants.MODIFICATION) && !pf.getPropertyName().equalsIgnoreCase(TessaConstants.INSERT);

		if(equalsLike)
		{
		    List<PropertyFilter> orEqualsChain = new ArrayList<>();
		    orEqualsChain.add(pf);
		    String propKey = pf.getPropertyName().toLowerCase();
		    int j = index + 1;
		    while (j < ordered.size())
		    {
			PropertyFilter next = ordered.get(j);
			if(next.getPropertyName() == null || next.getPropertyValue() == null)
			{
			    break;
			}
			if(next.getLogicalOperatorType() != LogicalOperatorType.OR)
			{
			    break;
			}
			if(next.getArithmeticOperatorType() != ArithmeticOperatorType.EQUALS || next.getPropertyName().equals(TessaConstants.ID) || next.getPropertyName().equalsIgnoreCase(TessaConstants.MODIFICATION) || next.getPropertyName().equalsIgnoreCase(TessaConstants.INSERT))
			{
			    break;
			}
			if(!next.getPropertyName().toLowerCase().equals(propKey))
			{
			    break;
			}
			orEqualsChain.add(next);
			j++;
		    }

		    if(orEqualsChain.size() > 1)
		    {
			String propName = resolvePropertyNameForIn(orEqualsChain.get(0).getPropertyName());
			List<Object> values = orEqualsChain.stream().map(p -> resolvePropertyValueForIn(p.getPropertyName(), p.getPropertyValue())).collect(Collectors.toList());
			Condition inCondition = node.property(propName).in(Cypher.literalOf(values));
			LogicalOperatorType linkOp = orEqualsChain.get(0).getLogicalOperatorType();
			if(conditions == null)
			{
			    conditions = inCondition;
			}
			else
			{
			    if(linkOp == null)
			    {
				linkOp = LogicalOperatorType.AND;
			    }
			    conditions = buildLogicalCondition(conditions, inCondition, linkOp);
			}
			index += orEqualsChain.size();
			continue;
		    }
		}

		Condition single = buildCondition(node, pf);
		if(conditions == null)
		{
		    conditions = single;
		}
		else
		{
		    LogicalOperatorType linkOp = pf.getLogicalOperatorType();
		    if(linkOp == null)
		    {
			linkOp = LogicalOperatorType.AND;
		    }
		    conditions = buildLogicalCondition(conditions, single, linkOp);
		}
		index++;
	    }
	}
	if(conditionGroups != null)
	{
	    if(conditions == null)
	    {
		conditions = conditionGroups;
	    }
	    else
	    {
		conditions = conditions.and(conditionGroups);
	    }

	}

	return conditions;
    }


    private String resolvePropertyNameForIn(String propertyName)
    {

	if(propertyName.equalsIgnoreCase(TessaConstants.NAME))
	{
	    return TessaConstants.NAME_LOWER;
	}
	else if(propertyName.equalsIgnoreCase(TessaConstants.VALUE))
	{
	    return TessaConstants.VALUE_LOWER;
	}
	else if(propertyName.equalsIgnoreCase(TessaConstants.IDENTIFICATOR_LOWER))
	{
	    return TessaConstants.IDENTIFICATOR_LOWER;
	}

	return propertyName;
    }


    private Object resolvePropertyValueForIn(String propertyName, Object propertyValue)
    {

	if(propertyName.equalsIgnoreCase(TessaConstants.NAME) || propertyName.equalsIgnoreCase(TessaConstants.VALUE) || propertyName.equalsIgnoreCase(TessaConstants.IDENTIFICATOR_LOWER) || propertyName.equalsIgnoreCase(TessaConstants.ATTRIBUTE_NAME_LOWER))
	{
	    if(propertyValue instanceof String)
	    {
		return ((String) propertyValue).toLowerCase();
	    }
	}

	if(propertyName.equals(TessaConstants.IDENTIFICATOR))
	{
	    return String.valueOf(propertyValue);
	}

	return propertyValue;
    }


    protected OngoingReadingWithWhere buildRelationWhere(OrderableOngoingReadingAndWith match, Node node, Relationship relation, Collection<PropertyFilter> propertyFilters, boolean optional, Property propertyIndex, Set<String> groups)
    {

	Condition conditionGroups = buildGroups(node, groups);

	OngoingReadingWithWhere whereNode = null;

	if(!optional)
	{
	    if(propertyIndex != null)
	    {
		whereNode = match.match(relation).usingIndex(propertyIndex).where(conditionGroups);
	    }
	    else
	    {
		whereNode = match.match(relation).where(conditionGroups);
	    }
	}
	else
	{
	    whereNode = match.optionalMatch(relation).where(conditionGroups);

	}

	Condition conditions = null;

	if(propertyFilters != null && !propertyFilters.isEmpty())
	{

	    for (PropertyFilter propertyFilter : propertyFilters)
	    {
		if(propertyFilter.getPropertyName() == null || propertyFilter.getPropertyValue() == null)
		{
		    continue;
		}

		if(conditions == null)
		{
		    conditions = buildCondition(node, propertyFilter);
		}
		else
		{
		    conditions = buildLogicalCondition(conditions, buildCondition(node, propertyFilter), propertyFilter.getLogicalOperatorType());
		}

	    }

	}

	if(conditions != null)
	{
	    whereNode = whereNode.and(conditions);
	}

	return whereNode;
    }


    protected OngoingReadingWithoutWhere buildRelationMatch(OrderableOngoingReadingAndWith match, Node node, NamedPath relation, boolean optional)
    {

	OngoingReadingWithoutWhere matchNode = null;

	if(!optional)
	{
	    matchNode = match.match(relation);

	}
	else
	{
	    matchNode = match.optionalMatch(relation);

	}

	return matchNode;
    }


    protected OrderableOngoingReadingAndWith buildWithPageConfig(OngoingReadingWithWhere match, Node node, Collection<PropertyFilter> propertyFilters, Collection<Expression> elements, boolean distinct, PageConfig pageConfig)
    {

	if(pageConfig == null)
	{
	    pageConfig = new PageConfig(0, Integer.MAX_VALUE);
	}

	int skip = pageConfig.getNumPage() * pageConfig.getSizePage();
	int limit = pageConfig.getSizePage();

	Set<SortItem> buildOrder = buildOrder(node, propertyFilters);

	OrderableOngoingReadingAndWith with = null;

	if(!buildOrder.isEmpty())
	{
	    if(distinct)
	    {
		// TODO deactivate order by
		with = match.withDistinct(elements.toArray(new SymbolicName[0]));// .orderBy(buildOrder);
	    }
	    else
	    {
		with = match.with(elements.toArray(new SymbolicName[0]));// .orderBy(buildOrder);
	    }

	}
	else
	{
	    if(distinct)
	    {
		with = match.withDistinct(elements.toArray(new SymbolicName[0]));
	    }
	    else
	    {
		with = match.with(elements.toArray(new SymbolicName[0]));
	    }

	}

	with.skip(skip).limit(limit);

	return with;

    }


    protected OrderableOngoingReadingAndWith buildWith(OngoingReading match, Node node, Collection<PropertyFilter> propertyFilters, Collection<Expression> expressions, boolean distinct)
    {

	Set<SortItem> buildOrder = buildOrder(node, propertyFilters);

	OrderableOngoingReadingAndWith with = null;

	if(!expressions.isEmpty())
	{
	    if(!buildOrder.isEmpty())
	    {
		if(distinct)
		{
		    // TODO se desactivan las ordenaciones
		    with = match.withDistinct(expressions.toArray(new SymbolicName[0]));// .orderBy(buildOrder);
		}
		else
		{
		    with = match.with(expressions.toArray(new SymbolicName[0]));// ;orderBy(buildOrder);
		}

	    }
	    else
	    {
		if(distinct)
		{
		    with = match.withDistinct(expressions.toArray(new SymbolicName[0]));
		}
		else
		{
		    with = match.with(expressions.toArray(new SymbolicName[0]));
		}

	    }
	}

	return with;

    }


    protected OrderableOngoingReadingAndWith buildWith(OngoingReading match, Node node, Collection<PropertyFilter> propertyFilters, boolean distinct, List<Expression> expressions)
    {

	OrderableOngoingReadingAndWith with = null;

	if(distinct)
	{
	    with = match.withDistinct(expressions.toArray(new IdentifiableElement[0]));
	}
	else
	{
	    with = match.with(expressions.toArray(new IdentifiableElement[0]));
	}

	return with;

    }


    protected OrderableOngoingReadingAndWith buildWithOrderBy(OngoingReading match, Node node, Collection<PropertyFilter> propertyFilters, boolean distinct, AliasedExpression... aliasedExpression)
    {

	Set<SortItem> buildOrder = buildOrder(node, propertyFilters);

	OrderableOngoingReadingAndWith with = null;

	if(!buildOrder.isEmpty())
	{
	    if(distinct)
	    {
		with = match.withDistinct(aliasedExpression).orderBy(buildOrder);
	    }
	    else
	    {
		with = match.with(aliasedExpression).orderBy(buildOrder);
	    }

	}
	else
	{
	    if(distinct)
	    {
		with = match.withDistinct(aliasedExpression);
	    }
	    else
	    {
		with = match.with(aliasedExpression);
	    }

	}
	return with;

    }


    protected Property getIndexProperty(Node node, Collection<PropertyFilter> propertyFilters)
    {

	Property propertyIndex = null;

	if(propertyFilters != null)
	{

	    for (PropertyFilter propertiFilter : propertyFilters)
	    {
		if(propertiFilter.getArithmeticOperatorType().equals(ArithmeticOperatorType.NOT))
		{
		    continue;
		}

		if(propertiFilter.getPropertyName() != null)
		{
		    if(propertiFilter.getPropertyName().equals(TessaConstants.IDENTIFICATOR_LOWER))
		    {
			propertyIndex = node.property(TessaConstants.IDENTIFICATOR_LOWER);
		    }
		    else if(propertiFilter.getPropertyName().equals(TessaConstants.IDENTIFICATOR))
		    {
			propertyIndex = node.property(TessaConstants.IDENTIFICATOR);
		    }
		    else if(propertiFilter.getPropertyName().equals(TessaConstants.NAME_LOWER) || propertiFilter.getPropertyName().equals(TessaConstants.NAME))
		    {
			propertyIndex = node.property(TessaConstants.NAME_LOWER);
		    }
		    else if(propertiFilter.getPropertyName().equals(TessaConstants.NEMONIC))
		    {
			propertyIndex = node.property(TessaConstants.NEMONIC);
		    }
		    else if(propertiFilter.getPropertyName().equals(TessaConstants.TYPE))
		    {
			propertyIndex = node.property(TessaConstants.TYPE);
		    }
		}
	    }
	}

	return propertyIndex;
    }


    /**
     * Construye la condicion de solapamiento de intervalos entre el rango de fechas del filtro
     * [startChange, endChange] y el intervalo de vigencia de la version [rel.startChange, rel.endChange).
     * Una version solapa con el filtro si empezo antes o justo cuando termina el rango del filtro
     * (rel.startChange <= endChange) Y sigue vigente despues de que empiece el rango del filtro
     * (rel.endChange IS NULL OR rel.endChange > startChange). Si ambos limites del filtro son null,
     * se mantiene el comportamiento anterior de exigir que la version tenga startChange no nulo.
     */
    protected Condition buildConditionBetweenDates(Instant startChange, Instant endChange, Relationship relationship)
    {

	Condition upperBoundCondition = null;

	if(endChange != null)
	{
	    upperBoundCondition = relationship.property(TessaConstants.STARTCHANGE_PROPERTY).lte(Cypher.call("localdatetime").withArgs(Cypher.literalOf(endChange.atZone(ZoneOffset.UTC).toLocalDateTime().toString())).asFunction());
	}

	Condition lowerBoundCondition = null;

	if(startChange != null)
	{
	    Expression startChangeLiteral = Cypher.call("localdatetime").withArgs(Cypher.literalOf(startChange.atZone(ZoneOffset.UTC).toLocalDateTime().toString())).asFunction();

	    lowerBoundCondition = relationship.property(TessaConstants.ENDCHANGE_PROPERTY).isNull().or(relationship.property(TessaConstants.ENDCHANGE_PROPERTY).gt(startChangeLiteral));
	}

	if(upperBoundCondition != null && lowerBoundCondition != null)
	{
	    return upperBoundCondition.and(lowerBoundCondition);
	}
	else if(upperBoundCondition != null)
	{
	    return upperBoundCondition;
	}
	else if(lowerBoundCondition != null)
	{
	    return lowerBoundCondition;
	}

	return relationship.property(TessaConstants.STARTCHANGE_PROPERTY).isNotNull();

    }


    protected Condition buildConditionAsOf(java.time.LocalDateTime asOf, Relationship relationship)
    {

	Expression asOfLiteral = Cypher.call("localdatetime").withArgs(Cypher.literalOf(asOf.toString())).asFunction();

	Condition startCondition = relationship.property(TessaConstants.STARTCHANGE_PROPERTY).lte(asOfLiteral);

	Condition endIsNull = relationship.property(TessaConstants.ENDCHANGE_PROPERTY).isNull();
	Condition endAfterAsOf = relationship.property(TessaConstants.ENDCHANGE_PROPERTY).gt(asOfLiteral);

	return startCondition.and(endIsNull.or(endAfterAsOf));

    }


    protected Condition buildRelationCondition(Relationship relationship, List<PropertyFilter> relationshipFilters)
    {

	Condition relationTypeCondition = null;
	Condition referenceTypeCondition = null;

	if(relationshipFilters != null && !relationshipFilters.isEmpty())
	{
	    for (PropertyFilter relationshipFilter : relationshipFilters)
	    {

		if(relationshipFilter.getPropertyName().equals(RelationshipProperties.RELATION_TYPE))
		{

		    Property property = relationship.property(RelationshipProperties.RELATION_TYPE);

		    if(relationTypeCondition == null)
		    {

			relationTypeCondition = buildArithmeticalCondition(property, getLiteral(relationshipFilter.getPropertyValue()), relationshipFilter.getArithmeticOperatorType());

		    }
		    else
		    {

			relationTypeCondition = buildLogicalCondition(relationTypeCondition, buildArithmeticalCondition(property, getLiteral(relationshipFilter.getPropertyValue()), relationshipFilter.getArithmeticOperatorType()), relationshipFilter.getLogicalOperatorType());
		    }

		}

		if(relationshipFilter.getPropertyName().equals(RelationshipProperties.REFERENCE_TYPE))
		{

		    Property property = relationship.property(RelationshipProperties.REFERENCE_TYPE);

		    if(referenceTypeCondition == null)
		    {

			referenceTypeCondition = buildArithmeticalCondition(property, getLiteral(relationshipFilter.getPropertyValue()), relationshipFilter.getArithmeticOperatorType());

		    }
		    else
		    {

			referenceTypeCondition = buildLogicalCondition(referenceTypeCondition, buildArithmeticalCondition(property, getLiteral(relationshipFilter.getPropertyValue()), relationshipFilter.getArithmeticOperatorType()), relationshipFilter.getLogicalOperatorType());
		    }

		}
	    }
	}

	Condition relationCondition = null;

	if(relationTypeCondition != null)
	{
	    relationCondition = relationTypeCondition;

	    if(referenceTypeCondition != null)
	    {
		relationCondition = relationCondition.and(referenceTypeCondition);
	    }
	}
	else if(referenceTypeCondition != null)
	{
	    relationCondition = referenceTypeCondition;
	}

	return relationCondition;

    }


    protected Condition buildRelationCondition(SymbolicName relationship, Collection<PropertyFilter> relationshipFilters)
    {

	Condition relationTypeCondition = null;
	Condition referenceTypeCondition = null;

	if(relationshipFilters != null && !relationshipFilters.isEmpty())
	{
	    for (PropertyFilter relationshipFilter : relationshipFilters)
	    {

		if(relationshipFilter.getPropertyName().equals(RelationshipProperties.RELATION_TYPE))
		{

		    Property property = relationship.property(RelationshipProperties.RELATION_TYPE);

		    if(relationTypeCondition == null)
		    {

			relationTypeCondition = buildArithmeticalCondition(property, getLiteral(relationshipFilter.getPropertyValue()), relationshipFilter.getArithmeticOperatorType());

		    }
		    else
		    {

			relationTypeCondition = buildLogicalCondition(relationTypeCondition, buildArithmeticalCondition(property, getLiteral(relationshipFilter.getPropertyValue()), relationshipFilter.getArithmeticOperatorType()), relationshipFilter.getLogicalOperatorType());
		    }

		}

		if(relationshipFilter.getPropertyName().equals(RelationshipProperties.REFERENCE_TYPE))
		{

		    Property property = relationship.property(RelationshipProperties.REFERENCE_TYPE);

		    if(referenceTypeCondition == null)
		    {

			referenceTypeCondition = buildArithmeticalCondition(property, getLiteral(relationshipFilter.getPropertyValue()), relationshipFilter.getArithmeticOperatorType());

		    }
		    else
		    {

			referenceTypeCondition = buildLogicalCondition(referenceTypeCondition, buildArithmeticalCondition(property, getLiteral(relationshipFilter.getPropertyValue()), relationshipFilter.getArithmeticOperatorType()), relationshipFilter.getLogicalOperatorType());
		    }

		}
	    }
	}

	Condition relationCondition = null;

	if(relationTypeCondition != null)
	{
	    relationCondition = relationTypeCondition;

	    if(referenceTypeCondition != null)
	    {
		relationCondition = relationCondition.and(referenceTypeCondition);
	    }
	}
	else if(referenceTypeCondition != null)
	{
	    relationCondition = referenceTypeCondition;
	}

	return relationCondition;

    }


    private Expression getLiteral(Object propertyValue)
    {

	if(propertyValue instanceof Boolean)
	{

	    if(Boolean.TRUE.equals((propertyValue)))
	    {
		return Cypher.literalTrue();
	    }
	    else
	    {
		return Cypher.literalFalse();
	    }

	}
	else
	{
	    return Cypher.literalOf(propertyValue);

	}
    }


    private Condition buildArithmeticalCondition(Expression property, Expression expression, ArithmeticOperatorType arithmeticOperatorType)
    {

	switch (arithmeticOperatorType)
	{
	case EQUALS:
	{
	    return property.eq(expression);
	}
	case CONTAINS:
	{
	    return property.contains(expression);
	}
	case MAYOR:
	{
	    return property.gt(expression);
	}
	case MINOR:
	{
	    return property.lt(expression);
	}
	case NOT:
	{
	    return property.isNotEqualTo(expression);
	}
	case MAYOR_EQUALS:
	{
	    return property.gte(expression);
	}
	case MINOR_EQUALS:
	{
	    return property.lte(expression);

	}
	case STARTS_WITH:
	{
	    return property.startsWith(expression);

	}
	case ENDS_WITH:
	{
	    return property.endsWith(expression);

	}
	case EXISTS:
	{
	    return property.isNotNull();

	}
	case NOT_EXISTS:
	{
	    return property.isNull();

	}
	default:
	{
	    LOGGER.error("Aritmethic operation {} not supported", arithmeticOperatorType);
	    throw new TessaException("Aritmethic operation " + arithmeticOperatorType + " not supported");
	}
	}
    }

}
