package es.cic.tessa.model.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class UniqueStringGeneratorTest
{

    @Test
    void generateUniqueString_noDevuelveNulo()
    {

	String resultado = UniqueStringGenerator.generateUniqueString();

	assertNotNull(resultado);
    }

    @Test
    void generateUniqueString_longitudExacta20()
    {

	String resultado = UniqueStringGenerator.generateUniqueString();

	assertEquals(20, resultado.length(), "El string generado debe tener exactamente 20 caracteres");
    }

    @Test
    void generateUniqueString_noEstaVacio()
    {

	String resultado = UniqueStringGenerator.generateUniqueString();

	assertFalse(resultado.isBlank());
    }

    @Test
    void generateUniqueString_dosLlamadasDevuelvenValoresDistintos()
    {

	String primero  = UniqueStringGenerator.generateUniqueString();
	String segundo  = UniqueStringGenerator.generateUniqueString();

	assertNotEquals(primero, segundo,
		"Dos llamadas consecutivas deben producir strings distintos");
    }

    @Test
    void generateUniqueString_milLlamadasSinColisiones()
    {

	Set<String> generados = new HashSet<>();
	int iteraciones = 1000;

	for (int i = 0; i < iteraciones; i++)
	{
	    generados.add(UniqueStringGenerator.generateUniqueString());
	}

	assertEquals(iteraciones, generados.size(),
		"No deben producirse colisiones en " + iteraciones + " llamadas");
    }

    @Test
    void generateUniqueString_soloCaracteresValidos()
    {

	// Base64 URL-safe sin padding: A-Z, a-z, 0-9, -, _
	// El prefijo es el timestamp en segundos (solo dígitos)
	String resultado = UniqueStringGenerator.generateUniqueString();

	assertTrue(resultado.matches("[A-Za-z0-9_\\-]+"),
		"El string debe contener solo caracteres Base64 URL-safe y dígitos");
    }

    private static void assertTrue(boolean condition, String message)
    {

	org.junit.jupiter.api.Assertions.assertTrue(condition, message);
    }
}
