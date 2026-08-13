package es.cic.tessa.common.query;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.cic.tessa.common.filter.OptimizePropertyFilter;
import es.cic.tessa.common.model.types.ArithmeticOperatorType;
import es.cic.tessa.common.model.types.LogicalOperatorType;
import es.cic.tessa.common.support.PageConfig;


class AbstractOptimizeElementQueryManagerTest
{

    private AbstractOptimizeElementQueryManager queryManager;

    @BeforeEach
    void setUp()
    {

	queryManager = new AbstractOptimizeElementQueryManager()
	{
	};
    }


    @Test
    void escapeCypherString_withNull_shouldReturnNull()
    {

	assertNull(queryManager.escapeCypherString(null));
    }


    @Test
    void escapeCypherString_withNormalString_shouldReturnUnchanged()
    {

	assertEquals("hello", queryManager.escapeCypherString("hello"));
    }


    @Test
    void escapeCypherString_withSingleQuote_shouldEscape()
    {

	assertEquals("it\\'s", queryManager.escapeCypherString("it's"));
    }


    @Test
    void escapeCypherString_withBackslash_shouldEscape()
    {

	assertEquals("path\\\\to", queryManager.escapeCypherString("path\\to"));
    }


    @Test
    void escapeCypherString_withBackslashAndSingleQuote_shouldEscapeBoth()
    {

	assertEquals("path\\\\to\\'s", queryManager.escapeCypherString("path\\to's"));
    }


    @Test
    void escapeCypherString_withMultipleSingleQuotes_shouldEscapeAll()
    {

	assertEquals("it\\'s a \\'test\\'", queryManager.escapeCypherString("it's a 'test'"));
    }


    @Test
    void buildGroupsCondition_withSingleGroup_shouldReturnCondition()
    {

	Set<String> groups = new HashSet<>(Arrays.asList("Group1"));

	String condition = queryManager.buildGroupsCondition("n", groups);

	assertNotNull(condition);
	assertTrue(condition.contains("n:Group1"));
	assertTrue(condition.startsWith("("));
	assertTrue(condition.endsWith(")"));
    }


    @Test
    void buildGroupsCondition_withMultipleGroups_shouldContainOrOperator()
    {

	Set<String> groups = new HashSet<>(Arrays.asList("Group1", "Group2"));

	String condition = queryManager.buildGroupsCondition("n", groups);

	assertNotNull(condition);
	assertTrue(condition.contains("n:Group1"));
	assertTrue(condition.contains("n:Group2"));
	assertTrue(condition.contains(" OR "));
    }


    @Test
    void buildPagination_withNull_shouldReturnDefaultPagination()
    {

	String pagination = queryManager.buildPagination(null);

	assertNotNull(pagination);
	assertTrue(pagination.contains("SKIP 0"));
	assertTrue(pagination.contains("LIMIT " + Integer.MAX_VALUE));
    }


    @Test
    void buildPagination_withPageConfig_shouldReturnCorrectSkipAndLimit()
    {

	PageConfig pageConfig = new PageConfig(2, 10);

	String pagination = queryManager.buildPagination(pageConfig);

	assertNotNull(pagination);
	assertTrue(pagination.contains("SKIP 20"));
	assertTrue(pagination.contains("LIMIT 10"));
    }


    @Test
    void buildPagination_withFirstPage_shouldReturnZeroSkip()
    {

	PageConfig pageConfig = new PageConfig(0, 25);

	String pagination = queryManager.buildPagination(pageConfig);

	assertNotNull(pagination);
	assertTrue(pagination.contains("SKIP 0"));
	assertTrue(pagination.contains("LIMIT 25"));
    }


    @Test
    void buildPropertyCondition_withEqualsCondition_shouldContainEquals()
    {

	List<OptimizePropertyFilter> filters = Arrays.asList(new OptimizePropertyFilter("type", "sensor", ArithmeticOperatorType.EQUALS));

	String condition = queryManager.buildPropertyCondition("n", filters);

	assertNotNull(condition);
	assertTrue(condition.contains("n.type"));
	assertTrue(condition.contains("="));
	assertTrue(condition.contains("'sensor'"));
    }


    @Test
    void buildPropertyCondition_withContainsCondition_shouldContainContains()
    {

	List<OptimizePropertyFilter> filters = Arrays.asList(new OptimizePropertyFilter("name", "test", ArithmeticOperatorType.CONTAINS));

	String condition = queryManager.buildPropertyCondition("n", filters);

	assertNotNull(condition);
	assertTrue(condition.contains("CONTAINS"));
    }


    @Test
    void buildPropertyCondition_withNumericValue_shouldNotHaveQuotes()
    {

	List<OptimizePropertyFilter> filters = Arrays.asList(new OptimizePropertyFilter("count", 42, ArithmeticOperatorType.EQUALS));

	String condition = queryManager.buildPropertyCondition("n", filters);

	assertNotNull(condition);
	assertTrue(condition.contains("42"));
    }


    @Test
    void buildPropertyCondition_withBooleanValue_shouldNotHaveQuotes()
    {

	List<OptimizePropertyFilter> filters = Arrays.asList(new OptimizePropertyFilter("active", true, ArithmeticOperatorType.EQUALS));

	String condition = queryManager.buildPropertyCondition("n", filters);

	assertNotNull(condition);
	assertTrue(condition.contains("true"));
    }


    @Test
    void buildPropertyCondition_withNameProperty_shouldUseLowerCase()
    {

	List<OptimizePropertyFilter> filters = Arrays.asList(new OptimizePropertyFilter("name", "TestName", ArithmeticOperatorType.EQUALS));

	String condition = queryManager.buildPropertyCondition("n", filters);

	assertNotNull(condition);
	assertTrue(condition.contains("nameLower"));
	assertTrue(condition.contains("testname"));
    }


    @Test
    void buildPropertyCondition_withValueProperty_shouldUseLowerCase()
    {

	List<OptimizePropertyFilter> filters = Arrays.asList(new OptimizePropertyFilter("value", "TestValue", ArithmeticOperatorType.EQUALS));

	String condition = queryManager.buildPropertyCondition("n", filters);

	assertNotNull(condition);
	assertTrue(condition.contains("valueLower"));
	assertTrue(condition.contains("testvalue"));
    }


    @Test
    void buildPropertyCondition_withStringContainingSingleQuote_shouldEscape()
    {

	List<OptimizePropertyFilter> filters = Arrays.asList(new OptimizePropertyFilter("type", "it's", ArithmeticOperatorType.EQUALS));

	String condition = queryManager.buildPropertyCondition("n", filters);

	assertNotNull(condition);
	assertTrue(condition.contains("it\\'s"));
    }


    @Test
    void buildPropertyCondition_withMultipleFiltersAndOperator_shouldCombine()
    {

	OptimizePropertyFilter filter1 = new OptimizePropertyFilter("type", "sensor", LogicalOperatorType.AND, ArithmeticOperatorType.EQUALS);
	OptimizePropertyFilter filter2 = new OptimizePropertyFilter("active", true, ArithmeticOperatorType.EQUALS);

	String condition = queryManager.buildPropertyCondition("n", Arrays.asList(filter1, filter2));

	assertNotNull(condition);
	assertTrue(condition.contains("sensor"));
	assertTrue(condition.contains("true"));
    }


    @Test
    void buildPropertyCondition_withExistsOperator_shouldContainIsNotNull()
    {

	List<OptimizePropertyFilter> filters = Arrays.asList(new OptimizePropertyFilter("field", "dummy", ArithmeticOperatorType.EXISTS));

	String condition = queryManager.buildPropertyCondition("n", filters);

	assertNotNull(condition);
	assertTrue(condition.contains("IS NOT NULL"));
    }


    @Test
    void buildPropertyCondition_withNotExistsOperator_shouldContainIsNull()
    {

	List<OptimizePropertyFilter> filters = Arrays.asList(new OptimizePropertyFilter("field", "dummy", ArithmeticOperatorType.NOT_EXISTS));

	String condition = queryManager.buildPropertyCondition("n", filters);

	assertNotNull(condition);
	assertTrue(condition.contains("IS NULL"));
    }


    @Test
    void buildPropertyCondition_withStartsWith_shouldContainStartsWith()
    {

	List<OptimizePropertyFilter> filters = Arrays.asList(new OptimizePropertyFilter("type", "sens", ArithmeticOperatorType.STARTS_WITH));

	String condition = queryManager.buildPropertyCondition("n", filters);

	assertNotNull(condition);
	assertTrue(condition.contains("STARTS WITH"));
    }


    @Test
    void buildPropertyCondition_withEndsWith_shouldContainEndsWith()
    {

	List<OptimizePropertyFilter> filters = Arrays.asList(new OptimizePropertyFilter("type", "sor", ArithmeticOperatorType.ENDS_WITH));

	String condition = queryManager.buildPropertyCondition("n", filters);

	assertNotNull(condition);
	assertTrue(condition.contains("ENDS WITH"));
    }


    @Test
    void buildPropertyCondition_withNotContains_shouldContainNotContains()
    {

	List<OptimizePropertyFilter> filters = Arrays.asList(new OptimizePropertyFilter("type", "bad", ArithmeticOperatorType.NOT_CONTAINS));

	String condition = queryManager.buildPropertyCondition("n", filters);

	assertNotNull(condition);
	assertTrue(condition.contains("NOT"));
	assertTrue(condition.contains("CONTAINS"));
    }


    @Test
    void buildPropertyCondition_withGreaterThan_shouldContainOperator()
    {

	List<OptimizePropertyFilter> filters = Arrays.asList(new OptimizePropertyFilter("count", 10, ArithmeticOperatorType.MAYOR));

	String condition = queryManager.buildPropertyCondition("n", filters);

	assertNotNull(condition);
	assertTrue(condition.contains(">"));
    }


    @Test
    void buildPropertyCondition_withLessThan_shouldContainOperator()
    {

	List<OptimizePropertyFilter> filters = Arrays.asList(new OptimizePropertyFilter("count", 10, ArithmeticOperatorType.MINOR));

	String condition = queryManager.buildPropertyCondition("n", filters);

	assertNotNull(condition);
	assertTrue(condition.contains("<"));
    }


    @Test
    void buildPropertyCondition_withNotEquals_shouldContainOperator()
    {

	List<OptimizePropertyFilter> filters = Arrays.asList(new OptimizePropertyFilter("type", "sensor", ArithmeticOperatorType.NOT));

	String condition = queryManager.buildPropertyCondition("n", filters);

	assertNotNull(condition);
	assertTrue(condition.contains("<>"));
    }
}
