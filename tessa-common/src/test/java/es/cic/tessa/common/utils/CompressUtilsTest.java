package es.cic.tessa.common.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class CompressUtilsTest
{

    // ── compressString ───────────────────────────────────────────────────────

    @Test
    void compressString_nulo_devuelveArrayVacio()
    {

	byte[] resultado = CompressUtils.compressString(null);

	assertArrayEquals(new byte[0], resultado);
    }

    @Test
    void compressString_vacio_devuelveArrayVacio()
    {

	byte[] resultado = CompressUtils.compressString("");

	assertArrayEquals(new byte[0], resultado);
    }

    @Test
    void compressString_texto_devuelveBytesComprimidos()
    {

	byte[] resultado = CompressUtils.compressString("Hola Mundo");

	assertNotNull(resultado);
	assertTrue(resultado.length > 0);
    }

    @Test
    void compressString_textoLargo_menorQueOriginal()
    {

	String textoLargo = "a".repeat(10_000);
	byte[] comprimido = CompressUtils.compressString(textoLargo);

	assertTrue(comprimido.length < textoLargo.getBytes().length,
		"El texto comprimido debe ser menor que el original para texto repetitivo");
    }

    // ── decompressString ─────────────────────────────────────────────────────

    @Test
    void decompressString_nulo_devuelveCadenaVacia() throws IOException
    {

	String resultado = CompressUtils.decompressString(null);

	assertEquals("", resultado);
    }

    @Test
    void decompressString_arrayVacio_devuelveCadenaVacia() throws IOException
    {

	String resultado = CompressUtils.decompressString(new byte[0]);

	assertEquals("", resultado);
    }

    // ── roundtrip ────────────────────────────────────────────────────────────

    @Test
    void roundtrip_comprimir_y_descomprimir_devuelveOriginal() throws IOException
    {

	String original = "Texto de prueba para compresión y descompresión";
	byte[] comprimido = CompressUtils.compressString(original);
	String resultado = CompressUtils.decompressString(comprimido);

	assertEquals(original, resultado);
    }

    @Test
    void roundtrip_textoConCaracteresEspeciales() throws IOException
    {

	String original = "Texto con ñ, á, é, ü, €, 中文, 日本語";
	byte[] comprimido = CompressUtils.compressString(original);
	String resultado = CompressUtils.decompressString(comprimido);

	assertEquals(original, resultado);
    }

    @Test
    void roundtrip_textoLargo() throws IOException
    {

	String original = "Línea de texto repetida. ".repeat(500);
	byte[] comprimido = CompressUtils.compressString(original);
	String resultado = CompressUtils.decompressString(comprimido);

	assertEquals(original, resultado);
    }
}
