package es.cic.tessa.model.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.cic.tessa.model.Hashtag;
import es.cic.tessa.model.dto.HashtagRequest;

class HashtagRequestMapperTest
{

    private HashtagRequestMapper mapper;

    @BeforeEach
    void setUp()
    {

	mapper = new HashtagRequestMapper();
    }

    @Test
    void hashtagRequestToHashtag_mapeaCamposCorrectamente()
    {

	HashtagRequest request = new HashtagRequest();
	request.setId(1L);
	request.setHashtag("TestTag");
	request.setVersion(2L);

	Set<String> groups = Set.of("g1");
	Hashtag result = mapper.hashtagRequestToHashtag(request, groups);

	assertEquals(1L, result.getCustomId());
	assertEquals("TestTag", result.getName());
	assertEquals("testtag", result.getNameLower());
	assertEquals("TestTag", result.getValue());
	assertEquals("Hashtag created automatically by the system", result.getDescription());
	assertTrue(result.getGroups().contains("g1"));
	assertEquals(2L, result.getVersion());
    }

    @Test
    void hashtagRequestToHashtag_sinVersion_conservaDefault()
    {

	HashtagRequest request = new HashtagRequest();
	request.setHashtag("tag");

	Hashtag result = mapper.hashtagRequestToHashtag(request, Set.of("g"));

	assertEquals(0L, result.getVersion());
    }

    @Test
    void hashtagToHashtagRequest_mapeaCamposCorrectamente()
    {

	Hashtag hashtag = new Hashtag();
	hashtag.setCustomId(1L);
	hashtag.setName("TagName");
	hashtag.setIcon("tag.png");
	hashtag.setValue("tag-value");
	hashtag.setVersion(3L);

	HashtagRequest result = mapper.hashtagToHashtagRequest(hashtag);

	assertEquals(1L, result.getId());
	assertEquals("TagName", result.getName());
	assertEquals("tag.png", result.getIcon());
	assertEquals("tag-value", result.getHashtag());
	assertEquals(3L, result.getVersion());
    }
}
