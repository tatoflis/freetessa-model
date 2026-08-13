package es.cic.tessa.common.query;


import java.util.Collection;
import java.util.Set;
import org.neo4j.cypherdsl.core.Expression;
import org.neo4j.cypherdsl.core.Node;
import org.neo4j.cypherdsl.core.Statement;
import org.neo4j.cypherdsl.core.StatementBuilder.OngoingReadingWithWhere;
import org.neo4j.cypherdsl.core.StatementBuilder.OrderableOngoingReadingAndWith;
import es.cic.tessa.common.filter.Filter;
import es.cic.tessa.common.support.PageConfig;


public interface QueryManager
{

    Statement buildSearchChypherQuery(Filter filter, Set<String> groups, PageConfig pageConfig, boolean count);


    OngoingReadingWithWhere buildConditionsFromFilter(Node node, Filter filter, OrderableOngoingReadingAndWith nodeWith, Collection<Expression> expressions, Set<String> groups);
}
