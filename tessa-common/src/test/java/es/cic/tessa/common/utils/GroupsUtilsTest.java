package es.cic.tessa.common.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import es.cic.tessa.common.exceptions.TessaException;

class GroupsUtilsTest
{

    // ── buildGroupsAsCypherLabels ─────────────────────────────────────────────

    @Test
    void buildGroupsAsCypherLabels_unGrupo_devuelveEtiqueta()
    {

	Set<String> grupos = Set.of("GrupoA");

	String resultado = GroupsUtils.buildGroupsAsCypherLabels(grupos);

	assertEquals(":GrupoA", resultado);
    }

    @Test
    void buildGroupsAsCypherLabels_variosGrupos_devuelveEtiquetasConcatenadas()
    {

	// LinkedHashSet para mantener orden de inserción
	Set<String> grupos = new LinkedHashSet<>();
	grupos.add("GrupoA");
	grupos.add("GrupoB");
	grupos.add("GrupoC");

	String resultado = GroupsUtils.buildGroupsAsCypherLabels(grupos);

	assertEquals(":GrupoA:GrupoB:GrupoC", resultado);
    }

    @Test
    void buildGroupsAsCypherLabels_grupoSystem_noPropagaExcepcion()
    {

	// buildGroupsAsCypherLabels NO restringe el grupo System
	Set<String> grupos = Set.of("System");

	String resultado = GroupsUtils.buildGroupsAsCypherLabels(grupos);

	assertEquals(":System", resultado);
    }

    @Test
    void buildGroupsAsCypherLabels_conjuntoVacio_devuelveCadenaVacia()
    {

	String resultado = GroupsUtils.buildGroupsAsCypherLabels(Set.of());

	assertEquals("", resultado);
    }

    // ── buildGroupsNotSystemAsCypherLabels ───────────────────────────────────

    @Test
    void buildGroupsNotSystemAsCypherLabels_sinSystem_devuelveEtiquetas()
    {

	Set<String> grupos = new LinkedHashSet<>();
	grupos.add("GrupoA");
	grupos.add("GrupoB");

	String resultado = GroupsUtils.buildGroupsNotSystemAsCypherLabels(grupos);

	assertEquals(":GrupoA:GrupoB", resultado);
    }

    @Test
    void buildGroupsNotSystemAsCypherLabels_conSystem_lanzaTessaException()
    {

	Set<String> grupos = Set.of("System");

	assertThrows(TessaException.class, () -> GroupsUtils.buildGroupsNotSystemAsCypherLabels(grupos));
    }

    @Test
    void buildGroupsNotSystemAsCypherLabels_conSystemMinusculas_lanzaTessaException()
    {

	Set<String> grupos = Set.of("system");

	assertThrows(TessaException.class, () -> GroupsUtils.buildGroupsNotSystemAsCypherLabels(grupos));
    }

    @Test
    void buildGroupsNotSystemAsCypherLabels_conSystemMayusculas_lanzaTessaException()
    {

	Set<String> grupos = Set.of("SYSTEM");

	assertThrows(TessaException.class, () -> GroupsUtils.buildGroupsNotSystemAsCypherLabels(grupos));
    }

    @Test
    void buildGroupsNotSystemAsCypherLabels_conSystemEntreOtros_lanzaTessaException()
    {

	Set<String> grupos = new LinkedHashSet<>();
	grupos.add("GrupoA");
	grupos.add("System");
	grupos.add("GrupoB");

	assertThrows(TessaException.class, () -> GroupsUtils.buildGroupsNotSystemAsCypherLabels(grupos));
    }

    @Test
    void buildGroupsNotSystemAsCypherLabels_mensajeExcepcionAdecuado()
    {

	Set<String> grupos = Set.of("System");

	TessaException ex = assertThrows(TessaException.class,
		() -> GroupsUtils.buildGroupsNotSystemAsCypherLabels(grupos));

	assertTrue(ex.getMessage().contains("System") || ex.getMessage().contains("group"),
		"El mensaje de error debe hacer referencia al grupo System");
    }
}
