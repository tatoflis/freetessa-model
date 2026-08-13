package es.cic.tessa.common.query;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.cypherdsl.core.Condition;
import org.neo4j.cypherdsl.core.Cypher;
import org.neo4j.cypherdsl.core.Node;
import org.neo4j.cypherdsl.core.renderer.Renderer;
import es.cic.tessa.common.exceptions.TessaException;


class AbstractElementQueryManagerBuildGroupsTest
{

    private static class TestableQueryManager extends AbstractElementQueryManager
    {

	private int threshold = 10;

	public void setThreshold(int threshold)
	{

	    this.threshold = threshold;
	}


	@Override
	protected int getGroupsQueryThreshold()
	{

	    return threshold > 0 ? threshold : 10;
	}


	public int currentThreshold()
	{

	    return getGroupsQueryThreshold();
	}


	public Condition buildGroupsPublic(Node node, Set<String> groups)
	{

	    return buildGroups(node, groups);
	}
    }

    private TestableQueryManager manager;
    private final Renderer renderer = Renderer.getDefaultRenderer();

    @BeforeEach
    void setUp()
    {

	manager = new TestableQueryManager();
    }

    // ── Validation tests ─────────────────────────────────────────────────────


    @Test
    void buildGroups_emptyGroups_throwsException()
    {

	Node node = Cypher.node("Asset").named("a");
	assertThrows(TessaException.class, () -> manager.buildGroupsPublic(node, Set.of()));
    }


    @Test
    void buildGroups_nullGroups_throwsException()
    {

	Node node = Cypher.node("Asset").named("a");
	assertThrows(TessaException.class, () -> manager.buildGroupsPublic(node, null));
    }


    @Test
    void buildGroups_onlyReservedLabels_throwsException()
    {

	Node node = Cypher.node("Asset").named("a");
	// "Asset", "Template", etc. are reserved labels
	assertThrows(TessaException.class, () -> manager.buildGroupsPublic(node, Set.of("Asset", "Template")));
    }

    // ── Classic mode tests (small number of groups) ──────────────────────────


    @Test
    void buildGroups_fewGroups_usesClassicOrConditions()
    {

	Node node = Cypher.node("Asset").named("a");
	Set<String> groups = new LinkedHashSet<>();
	groups.add("SG1");
	groups.add("SG2");
	groups.add("SG3");

	manager.setThreshold(10);
	Condition condition = manager.buildGroupsPublic(node, groups);
	assertNotNull(condition);

	String cypher = renderCondition(node, condition);
	// Classic mode generates: a:SG1 OR a:SG2 OR a:SG3
	assertTrue(cypher.contains(":`SG1`"), "Should contain label SG1: " + cypher);
	assertTrue(cypher.contains(":`SG2`"), "Should contain label SG2: " + cypher);
	assertTrue(cypher.contains(" OR "), "Should use OR conditions: " + cypher);
    }

    // ── Optimized mode tests (large number of groups) ────────────────────────


    @Test
    void buildGroups_manyGroups_usesAnyPredicate()
    {

	Node node = Cypher.node("Asset").named("a");
	Set<String> groups = IntStream.rangeClosed(1, 50).mapToObj(i -> "SG" + i).collect(Collectors.toCollection(LinkedHashSet::new));

	manager.setThreshold(10);
	Condition condition = manager.buildGroupsPublic(node, groups);
	assertNotNull(condition);

	String cypher = renderCondition(node, condition);
	// Optimized mode generates: ANY(lbl IN labels(a) WHERE lbl IN
	// ['SG1','SG2',...])
	assertTrue(cypher.contains("any("), "Should use ANY predicate: " + cypher);
	assertTrue(cypher.contains("labels("), "Should use labels() function: " + cypher);
    }


    @Test
    void buildGroups_exactlyAtThreshold_usesClassicMode()
    {

	Node node = Cypher.node("Asset").named("a");
	Set<String> groups = IntStream.rangeClosed(1, 10).mapToObj(i -> "SG" + i).collect(Collectors.toCollection(LinkedHashSet::new));

	manager.setThreshold(10);
	Condition condition = manager.buildGroupsPublic(node, groups);
	assertNotNull(condition);

	String cypher = renderCondition(node, condition);
	// At threshold (not exceeding), uses classic mode
	assertTrue(cypher.contains(" OR "), "At threshold should use classic OR: " + cypher);
    }


    @Test
    void buildGroups_aboveThreshold_usesOptimizedMode()
    {

	Node node = Cypher.node("Asset").named("a");
	Set<String> groups = IntStream.rangeClosed(1, 11).mapToObj(i -> "SG" + i).collect(Collectors.toCollection(LinkedHashSet::new));

	manager.setThreshold(10);
	Condition condition = manager.buildGroupsPublic(node, groups);
	assertNotNull(condition);

	String cypher = renderCondition(node, condition);
	// Above threshold uses optimized mode
	assertTrue(cypher.contains("any("), "Above threshold should use ANY predicate: " + cypher);
    }


    @Test
    void buildGroups_optimizedMode_filtersReservedLabels()
    {

	Node node = Cypher.node("Asset").named("a");
	Set<String> groups = new HashSet<>();
	groups.add("Asset"); // reserved - should be filtered
	groups.add("Template"); // reserved - should be filtered
	IntStream.rangeClosed(1, 15).forEach(i -> groups.add("SG" + i));

	manager.setThreshold(10);
	Condition condition = manager.buildGroupsPublic(node, groups);
	assertNotNull(condition);

	String cypher = renderCondition(node, condition);
	// Reserved labels should not appear in the generated condition
	assertTrue(cypher.contains("any("), "Should use optimized mode: " + cypher);
    }

    // ── Threshold configuration tests ────────────────────────────────────────


    @Test
    void getGroupsQueryThreshold_defaultValue_isTen()
    {

	assertTrue(manager.currentThreshold() == 10);
    }


    @Test
    void getGroupsQueryThreshold_customValue_isRespected()
    {

	manager.setThreshold(3);
	assertTrue(manager.currentThreshold() == 3);
    }


    @Test
    void getGroupsQueryThreshold_invalidValue_fallsBackToTen()
    {

	manager.setThreshold(0);
	assertTrue(manager.currentThreshold() == 10);

	manager.setThreshold(-5);
	assertTrue(manager.currentThreshold() == 10);
    }


    @Test
    void buildGroups_customThreshold_respectsConfiguration()
    {

	Node node = Cypher.node("Asset").named("a");
	Set<String> groups = IntStream.rangeClosed(1, 5).mapToObj(i -> "SG" + i).collect(Collectors.toCollection(LinkedHashSet::new));

	// With threshold of 3, 5 groups should use optimized mode
	manager.setThreshold(3);
	Condition condition = manager.buildGroupsPublic(node, groups);
	String cypher = renderCondition(node, condition);
	assertTrue(cypher.contains("any("), "With low threshold, should use optimized mode: " + cypher);

	// With threshold of 10, 5 groups should use classic mode
	manager.setThreshold(10);
	condition = manager.buildGroupsPublic(node, groups);
	cypher = renderCondition(node, condition);
	assertTrue(cypher.contains(" OR "), "With high threshold, should use classic mode: " + cypher);
    }


    @Test
    void buildGroups_invalidThreshold_usesFallbackTenSoSmallSetIsClassic()
    {

	Node node = Cypher.node("Asset").named("a");
	Set<String> groups = IntStream.rangeClosed(1, 5).mapToObj(i -> "SG" + i).collect(Collectors.toCollection(LinkedHashSet::new));

	manager.setThreshold(0);
	Condition condition = manager.buildGroupsPublic(node, groups);
	String cypher = renderCondition(node, condition);

	assertTrue(cypher.contains(" OR "), "With invalid threshold fallback=10, should use classic mode: " + cypher);
    }


    private String renderCondition(Node node, Condition condition)
    {

	return renderer.render(Cypher.match(node).where(condition).returning(node).build());
    }
}
