package es.cic.tessa.model.historical.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import es.cic.tessa.model.HistoricalOrganizer;
import es.cic.tessa.model.HistoricalTemplate;
import es.cic.tessa.model.historical.dto.HistoricalTemplateResponse;

class HistoricalTemplateResponseMapperTest
{

    private HistoricalTemplateResponseMapper mapper;

    @BeforeEach
    void setUp()
    {

	mapper = new HistoricalTemplateResponseMapper();
	// historicalOrganizers de HistoricalTemplate nunca es null (se inicializa a
	// ArrayList vacio), asi que el mapper siempre invoca este colaborador @Autowired.
	ReflectionTestUtils.setField(mapper, "historicalOrganizerResponseMapper", new HistoricalOrganizerResponseMapper());
    }

    @Test
    void historicalTemplateToResponse_mapeaCamposBasicos()
    {

	HistoricalTemplate ht = new HistoricalTemplate();
	ht.setCustomId(1L);
	ht.setName("tmpl");
	ht.setDescription("desc");
	ht.setIcon("icon.png");
	ht.setType("SIMPLE");
	ht.setFinalTemplate(true);
	ht.setAbstractTemplate(false);
	ht.setAssetOrganized(true);
	ht.setTemplateOrganized(false);
	ht.setVersion(3L);
	ht.setInsertDate(LocalDateTime.of(2024, 1, 15, 10, 30));

	HistoricalTemplateResponse result = mapper.historicalTemplateTohistoricalTemplateRespose(ht);

	assertEquals(1L, result.getId());
	assertEquals("tmpl", result.getName());
	assertEquals("desc", result.getDescription());
	assertEquals("icon.png", result.getIcon());
	assertEquals("SIMPLE", result.getType());
	assertTrue(result.getFinalTemplate());
	assertFalse(result.getAbstractTemplate());
	assertTrue(result.getAssetOrganized());
	assertFalse(result.getTemplateOrganized());
	assertEquals(3L, result.getVersion());
	assertNotNull(result.getInsertDate());
    }

    @Test
    void historicalTemplateToResponse_conExtendsTemplate_recursivo()
    {

	HistoricalTemplate parent = new HistoricalTemplate();
	parent.setCustomId(10L);
	parent.setName("parent");
	parent.setInsertDate(LocalDateTime.of(2024, 1, 10, 8, 0));

	HistoricalTemplate ht = new HistoricalTemplate();
	ht.setCustomId(1L);
	ht.setName("child");
	ht.setInsertDate(LocalDateTime.of(2024, 1, 15, 10, 30));
	ht.setHistoricalExtendsTemplate(parent);

	HistoricalTemplateResponse result = mapper.historicalTemplateTohistoricalTemplateRespose(ht);

	assertNotNull(result.getHistoricalExtendsTemplate());
	assertEquals(10L, result.getHistoricalExtendsTemplate().getId());
	assertEquals("parent", result.getHistoricalExtendsTemplate().getName());
    }

    @Test
    void historicalTemplateToResponse_sinExtendsTemplate_null()
    {

	HistoricalTemplate ht = new HistoricalTemplate();
	ht.setCustomId(1L);
	ht.setName("tmpl");
	ht.setInsertDate(LocalDateTime.of(2024, 1, 15, 10, 30));

	HistoricalTemplateResponse result = mapper.historicalTemplateTohistoricalTemplateRespose(ht);

	assertNull(result.getHistoricalExtendsTemplate());
    }

    @Test
    void historicalTemplateToResponse_conSystemGroup_seteaGroups()
    {

	HistoricalTemplate ht = new HistoricalTemplate();
	ht.setCustomId(1L);
	ht.setName("tmpl");
	ht.setInsertDate(LocalDateTime.of(2024, 1, 15, 10, 30));
	ht.setGroups(Set.of("System"));

	HistoricalTemplateResponse result = mapper.historicalTemplateTohistoricalTemplateRespose(ht);

	assertNotNull(result.getGroups());
    }

    @Test
    void historicalTemplateToResponse_operationLeidaDelNodo_sinRelacionHistoricalChange()
    {

	HistoricalTemplate ht = new HistoricalTemplate();
	ht.setCustomId(1L);
	ht.setName("tmpl");
	ht.setInsertDate(LocalDateTime.of(2024, 1, 15, 10, 30));
	ht.setHistoricalChangeOperation("Delete");

	HistoricalTemplateResponse result = mapper.historicalTemplateTohistoricalTemplateRespose(ht);

	assertNotNull(result.getHistoricalChange());
	assertEquals("Delete", result.getHistoricalChange().getOperation());
    }


    @Test
    void historicalTemplateToResponse_conHistoricalOrganizers_mapeaColeccion()
    {

	HistoricalOrganizer organizer = new HistoricalOrganizer();
	organizer.setCustomId(20L);
	organizer.setName("planta1");
	organizer.setInsertDate(LocalDateTime.of(2024, 1, 5, 9, 0));

	HistoricalTemplate ht = new HistoricalTemplate();
	ht.setCustomId(1L);
	ht.setName("tmpl");
	ht.setInsertDate(LocalDateTime.of(2024, 1, 15, 10, 30));
	ht.setHistoricalOrganizers(List.of(organizer));

	HistoricalTemplateResponse result = mapper.historicalTemplateTohistoricalTemplateRespose(ht);

	assertNotNull(result.getHistoricalOrganizers());
	assertEquals(1, result.getHistoricalOrganizers().size());
	assertEquals(20L, result.getHistoricalOrganizers().iterator().next().getId());
	assertEquals("planta1", result.getHistoricalOrganizers().iterator().next().getName());
    }


    @Test
    void historicalTemplateToResponse_sinHistoricalOrganizers_coleccionVacia()
    {

	HistoricalTemplate ht = new HistoricalTemplate();
	ht.setCustomId(1L);
	ht.setName("tmpl");
	ht.setInsertDate(LocalDateTime.of(2024, 1, 15, 10, 30));

	HistoricalTemplateResponse result = mapper.historicalTemplateTohistoricalTemplateRespose(ht);

	assertNotNull(result.getHistoricalOrganizers());
	assertTrue(result.getHistoricalOrganizers().isEmpty());
    }
}
