package es.cic.tessa.common.support;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PageConfigTest
{

    // ── Constructor por defecto ───────────────────────────────────────────────

    @Test
    void constructorPorDefecto_valoresIniciales()
    {

	PageConfig pageConfig = new PageConfig();

	assertEquals(0, pageConfig.getNumPage());
	assertEquals(1, pageConfig.getSizePage());
    }

    // ── Constructor con parámetros ────────────────────────────────────────────

    @Test
    void constructorConParametros_valoresCorrectos()
    {

	PageConfig pageConfig = new PageConfig(3, 20);

	assertEquals(3, pageConfig.getNumPage());
	assertEquals(20, pageConfig.getSizePage());
    }

    @Test
    void constructor_numeroPaginaNegativo_lanzaIllegalArgumentException()
    {

	IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
		() -> new PageConfig(-1, 10));

	assertTrue(ex.getMessage().contains("less than zero"));
    }

    @Test
    void constructor_tamanioPaginaCero_lanzaIllegalArgumentException()
    {

	IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
		() -> new PageConfig(0, 0));

	assertTrue(ex.getMessage().contains("less than one"));
    }

    @Test
    void constructor_tamanioPaginaNegativo_lanzaIllegalArgumentException()
    {

	assertThrows(IllegalArgumentException.class, () -> new PageConfig(0, -5));
    }

    @Test
    void constructor_primeraPaginaTamanioUno_valido()
    {

	PageConfig pageConfig = new PageConfig(0, 1);

	assertEquals(0, pageConfig.getNumPage());
	assertEquals(1, pageConfig.getSizePage());
    }

    // ── Setters ───────────────────────────────────────────────────────────────

    @Test
    void setters_actualizanValores()
    {

	PageConfig pageConfig = new PageConfig();
	pageConfig.setNumPage(5);
	pageConfig.setSizePage(50);

	assertEquals(5, pageConfig.getNumPage());
	assertEquals(50, pageConfig.getSizePage());
    }

    // ── toString ──────────────────────────────────────────────────────────────

    @Test
    void toString_contieneNumeroYTamanio()
    {

	PageConfig pageConfig = new PageConfig(2, 15);
	String resultado = pageConfig.toString();

	assertTrue(resultado.contains("2"));
	assertTrue(resultado.contains("15"));
    }
}
