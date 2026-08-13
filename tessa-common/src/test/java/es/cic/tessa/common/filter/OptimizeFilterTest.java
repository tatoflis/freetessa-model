package es.cic.tessa.common.filter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class OptimizeFilterTest
{

    // Concrete subclass for testing abstract class
    private static class TestOptimizeFilter extends OptimizeFilter
    {
    }

    @Test
    void defaults_idsVacioYNullFields()
    {

	TestOptimizeFilter filter = new TestOptimizeFilter();

	assertNull(filter.getId());
	assertNull(filter.getName());
	assertNotNull(filter.getIds());
	assertTrue(filter.getIds().isEmpty());
	assertNotNull(filter.getPropetyFilters());
	assertTrue(filter.getPropetyFilters().isEmpty());
    }

    @Test
    void settersAndGetters_funcionanCorrectamente()
    {

	TestOptimizeFilter filter = new TestOptimizeFilter();
	filter.setId(1L);
	filter.setName("test");
	filter.setIds(Set.of(10L, 20L));

	assertEquals(1L, filter.getId());
	assertEquals("test", filter.getName());
	assertEquals(2, filter.getIds().size());
    }

    @Test
    void addPropertyFilter_aniadeAlLista()
    {

	TestOptimizeFilter filter = new TestOptimizeFilter();
	OptimizePropertyFilter pf = new OptimizePropertyFilter("prop", "val");

	filter.addPropertyFilter(pf);

	assertEquals(1, filter.getPropetyFilters().size());
	assertEquals("prop", filter.getPropetyFilters().get(0).getPropertyName());
    }

    @Test
    void equals_mismosValores_sonIguales()
    {

	TestOptimizeFilter f1 = new TestOptimizeFilter();
	f1.setId(1L);
	f1.setName("test");

	TestOptimizeFilter f2 = new TestOptimizeFilter();
	f2.setId(1L);
	f2.setName("test");

	assertEquals(f1, f2);
	assertEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    void equals_distintosIds_noSonIguales()
    {

	TestOptimizeFilter f1 = new TestOptimizeFilter();
	f1.setId(1L);

	TestOptimizeFilter f2 = new TestOptimizeFilter();
	f2.setId(2L);

	assertNotEquals(f1, f2);
    }

    @Test
    void equals_conNull_noSonIguales()
    {

	TestOptimizeFilter f1 = new TestOptimizeFilter();
	f1.setId(1L);

	assertNotEquals(f1, null);
    }

    @Test
    void setPropetyFilters_reemplazaLista()
    {

	TestOptimizeFilter filter = new TestOptimizeFilter();
	OptimizePropertyFilter pf1 = new OptimizePropertyFilter("p1", "v1");
	OptimizePropertyFilter pf2 = new OptimizePropertyFilter("p2", "v2");

	filter.setPropetyFilters(List.of(pf1, pf2));

	assertEquals(2, filter.getPropetyFilters().size());
    }
}
