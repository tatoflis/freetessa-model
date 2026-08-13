package es.cic.tessa.model.mappers;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.Hashtag;
import es.cic.tessa.model.dto.HashtagResponse;


class HashtagResponseMapperTest
{

    private HashtagResponseMapper mapper;

    @BeforeEach
    void setUp()
    {

	mapper = new HashtagResponseMapper();
    }


    @Test
    void hashtagToResponse_mapeaCamposBasicos()
    {

	Hashtag h = new Hashtag();
	h.setCustomId(1L);
	h.setName("tag1");
	h.setDescription("desc");
	h.setIcon("icon.png");
	h.setValue("hashtag-value");
	h.setVersion(2L);
	h.setNemonic("nem");
	h.setInsertDate(LocalDateTime.of(2024, 1, 15, 10, 30));

	HashtagResponse result = mapper.hashtagToHashtagResponse(h);

	assertEquals(1L, result.getId());
	assertEquals("tag1", result.getName());
	assertEquals("desc", result.getDescription());
	assertEquals("icon.png", result.getIcon());
	assertEquals("hashtag-value", result.getHashtag());
	assertEquals(2L, result.getVersion());
	assertEquals("nem", result.getNemonic());
	assertNotNull(result.getInsertDate());
    }


    @Test
    void hashtagResponseToHashtag_mapeaCamposBasicos()
    {

	HashtagResponse resp = new HashtagResponse();
	resp.setId(5L);
	resp.setName("tag5");
	resp.setDescription("desc5");
	resp.setIcon("icon5.png");
	resp.setHashtag("hash-val");
	resp.setVersion(3L);
	resp.setInsertDate(Instant.parse("2024-06-01T12:00:00Z"));

	Hashtag result = mapper.hashtagResponseToHashtag(resp);

	assertEquals(5L, result.getCustomId());
	assertEquals("tag5", result.getName());
	assertEquals("desc5", result.getDescription());
	assertEquals("icon5.png", result.getIcon());
	assertEquals("hash-val", result.getValue());
	assertEquals(3L, result.getVersion());
	assertNotNull(result.getInsertDate());
    }


    @Test
    void hashtagCollectionToResponseCollection_mapeaTodos()
    {

	Hashtag h1 = new Hashtag();
	h1.setCustomId(1L);
	h1.setName("t1");
	h1.setInsertDate(LocalDateTime.of(2024, 1, 1, 0, 0));

	Hashtag h2 = new Hashtag();
	h2.setCustomId(2L);
	h2.setName("t2");
	h2.setInsertDate(LocalDateTime.of(2024, 1, 2, 0, 0));

	var result = mapper.hashtagCollectionToHashtagResponseCollection(List.of(h1, h2));

	assertEquals(2, result.size());
    }


    @Test
    void hashtagResponseListToHashtagList_mapeaTodos()
    {

	HashtagResponse r1 = new HashtagResponse();
	r1.setId(1L);
	r1.setName("t1");
	r1.setInsertDate(Instant.parse("2024-01-01T00:00:00Z"));

	HashtagResponse r2 = new HashtagResponse();
	r2.setId(2L);
	r2.setName("t2");
	r2.setInsertDate(Instant.parse("2024-01-02T00:00:00Z"));

	List<Hashtag> result = mapper.hashtagResponseListToHashtagList(List.of(r1, r2));

	assertEquals(2, result.size());
	assertEquals(1L, result.get(0).getCustomId());
	assertEquals(2L, result.get(1).getCustomId());
    }
}
