package es.cic.tessa.common.support;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

class CacheKeyTest
{

    // ── Constructor ───────────────────────────────────────────────────────────

    @Test
    void constructor2Params_creaObjetoCorrectamente()
    {

	CacheKey cacheKey = new CacheKey("filtro1", String.class);

	assertEquals("filtro1", cacheKey.getFilter());
	assertEquals(String.class, cacheKey.getClazz());
    }

    @Test
    void constructor3Params_creaObjetoCorrectamente()
    {

	Set<String> grupos = Set.of("GrupoA", "GrupoB");
	CacheKey cacheKey = new CacheKey("filtro1", grupos, Integer.class);

	assertEquals("filtro1", cacheKey.getFilter());
	assertEquals(grupos, cacheKey.getGroups());
	assertEquals(Integer.class, cacheKey.getClazz());
    }

    // ── equals ────────────────────────────────────────────────────────────────

    @Test
    void equals_mismoObjeto_devuelveTrue()
    {

	CacheKey cacheKey = new CacheKey("filtro", Set.of("G1"), String.class);

	assertTrue(cacheKey.equals(cacheKey));
    }

    @Test
    void equals_nulo_devuelveFalse()
    {

	CacheKey cacheKey = new CacheKey("filtro", String.class);

	assertFalse(cacheKey.equals(null));
    }

    @Test
    void equals_claseDistinta_devuelveFalse()
    {

	CacheKey cacheKey = new CacheKey("filtro", String.class);

	assertFalse(cacheKey.equals("una cadena"));
    }

    @Test
    void equals_mismosValores_devuelveTrue()
    {

	Set<String> grupos = Set.of("GrupoA");
	CacheKey key1 = new CacheKey("filtro", grupos, String.class);
	CacheKey key2 = new CacheKey("filtro", grupos, String.class);

	assertTrue(key1.equals(key2));
    }

    @Test
    void equals_filtroDistinto_devuelveFalse()
    {

	Set<String> grupos = Set.of("GrupoA");
	CacheKey key1 = new CacheKey("filtro1", grupos, String.class);
	CacheKey key2 = new CacheKey("filtro2", grupos, String.class);

	assertFalse(key1.equals(key2));
    }

    @Test
    void equals_gruposDistintos_devuelveFalse()
    {

	CacheKey key1 = new CacheKey("filtro", Set.of("GrupoA"), String.class);
	CacheKey key2 = new CacheKey("filtro", Set.of("GrupoB"), String.class);

	assertFalse(key1.equals(key2));
    }

    @Test
    void equals_grupoNuloVsNoNulo_devuelveFalse()
    {

	CacheKey key1 = new CacheKey("filtro", String.class);       // grupos null
	CacheKey key2 = new CacheKey("filtro", Set.of("G1"), String.class);

	assertFalse(key1.equals(key2));
    }

    // ── hashCode ──────────────────────────────────────────────────────────────

    @Test
    void hashCode_consistente()
    {

	CacheKey key = new CacheKey("filtro", Set.of("G1"), String.class);

	assertEquals(key.hashCode(), key.hashCode());
    }

    @Test
    void hashCode_objetosIguales_mismoHashCode()
    {

	Set<String> grupos = Set.of("GrupoA");
	CacheKey key1 = new CacheKey("filtro", grupos, String.class);
	CacheKey key2 = new CacheKey("filtro", grupos, String.class);

	assertEquals(key1.hashCode(), key2.hashCode());
    }

    @Test
    void hashCode_objetosDistintos_probablementeDistinto()
    {

	CacheKey key1 = new CacheKey("filtroA", Set.of("G1"), String.class);
	CacheKey key2 = new CacheKey("filtroB", Set.of("G2"), String.class);

	assertNotEquals(key1.hashCode(), key2.hashCode());
    }

    // ── toString ──────────────────────────────────────────────────────────────

    @Test
    void toString_contieneFilterYGroups()
    {

	CacheKey key = new CacheKey("miFiltro", Set.of("GrupoA"), String.class);
	String resultado = key.toString();

	assertNotNull(resultado);
	assertTrue(resultado.contains("miFiltro"));
	assertTrue(resultado.contains("GrupoA"));
    }

    // ── Setters ───────────────────────────────────────────────────────────────

    @Test
    void setters_actualizanValores()
    {

	CacheKey key = new CacheKey("filtro", String.class);
	key.setFilter("nuevoFiltro");
	key.setGroups(Set.of("G2"));
	key.setClazz(Integer.class);

	assertEquals("nuevoFiltro", key.getFilter());
	assertEquals(Set.of("G2"), key.getGroups());
	assertEquals(Integer.class, key.getClazz());
    }
}
