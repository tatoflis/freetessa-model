package es.cic.tessa.model.support;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.Asset;

class AbstractNotificationServiceTest
{

    private AbstractNotificationService service;

    @BeforeEach
    void setUp()
    {

	service = new AbstractNotificationService()
	{
	};
    }

    @Test
    void getIDTessaElement_conElemento_generaFormatoCorrecto()
    {

	Asset asset = new Asset();
	asset.setCustomId(42L);

	Set<String> groups = new LinkedHashSet<>(Arrays.asList("g1", "g2"));
	String result = service.getIDTessaElement(asset, groups);

	assertTrue(result.startsWith("[42]["));
	assertTrue(result.contains("g1"));
	assertTrue(result.contains("g2"));
    }

    @Test
    void getIDTessaElement_sinCustomId_devuelveVacio()
    {

	Asset asset = new Asset();

	String result = service.getIDTessaElement(asset, Set.of("g"));

	assertEquals("", result);
    }

    @Test
    void getIDSTessaElements_conMultiplesElementos_separaPorComa()
    {

	Asset asset1 = new Asset();
	asset1.setCustomId(1L);

	Asset asset2 = new Asset();
	asset2.setCustomId(2L);

	LinkedHashSet<Asset> elements = new LinkedHashSet<>();
	elements.add(asset1);
	elements.add(asset2);

	Set<String> groups = Set.of("g1");
	String result = service.getIDSTessaElements(elements, groups);

	assertTrue(result.contains("[1]"));
	assertTrue(result.contains("[2]"));
	assertTrue(result.contains(","));
    }

    @Test
    void getIDSTessaElements_conColeccionVacia_devuelveVacio()
    {

	LinkedHashSet<Asset> elements = new LinkedHashSet<>();

	String result = service.getIDSTessaElements(elements, Set.of("g"));

	assertEquals("", result);
    }

    @Test
    void getIDSTessaElements_conUnElemento_sinComaFinal()
    {

	Asset asset = new Asset();
	asset.setCustomId(5L);

	LinkedHashSet<Asset> elements = new LinkedHashSet<>();
	elements.add(asset);

	String result = service.getIDSTessaElements(elements, Set.of("g"));

	assertFalse(result.endsWith(","));
	assertTrue(result.contains("[5]"));
    }
}
