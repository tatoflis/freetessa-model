package es.cic.tessa.common.support;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

class ServiceUtilsTest
{

    // ── getCount ─────────────────────────────────────────────────────────────

    @Test
    void getCount_conValor_devuelveValor()
    {

	int resultado = ServiceUtils.getCount(Optional.of(5));

	assertEquals(5, resultado);
    }

    @Test
    void getCount_sinValor_devuelveCero()
    {

	int resultado = ServiceUtils.getCount(Optional.empty());

	assertEquals(0, resultado);
    }

    @Test
    void getCount_valorCero_devuelveCero()
    {

	int resultado = ServiceUtils.getCount(Optional.of(0));

	assertEquals(0, resultado);
    }

    @Test
    void getCount_valorGrande_devuelveValor()
    {

	int resultado = ServiceUtils.getCount(Optional.of(Integer.MAX_VALUE));

	assertEquals(Integer.MAX_VALUE, resultado);
    }

    // ── getPagination ─────────────────────────────────────────────────────────

    @Test
    void getPagination_nulo_devuelvePaginaMaxima()
    {

	PageRequest pagination = ServiceUtils.getPagination(null);

	assertNotNull(pagination);
	assertEquals(0, pagination.getPageNumber());
	assertEquals(Integer.MAX_VALUE, pagination.getPageSize());
    }

    @Test
    void getPagination_conPageConfig_devuelveConfiguracionCorrecta()
    {

	PageConfig pageConfig = new PageConfig(2, 25);

	PageRequest pagination = ServiceUtils.getPagination(pageConfig);

	assertNotNull(pagination);
	assertEquals(2, pagination.getPageNumber());
	assertEquals(25, pagination.getPageSize());
    }

    @Test
    void getPagination_primeraPagina_devuelvePaginaCero()
    {

	PageConfig pageConfig = new PageConfig(0, 10);

	PageRequest pagination = ServiceUtils.getPagination(pageConfig);

	assertEquals(0, pagination.getPageNumber());
	assertEquals(10, pagination.getPageSize());
    }
}
