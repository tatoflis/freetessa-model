package es.cic.tessa.model.mappers;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.Hashtag;
import es.cic.tessa.model.dto.HashtagResponse;
import es.cic.tessa.model.dto.json.ResponsePageJson;


@Component
public class HashtagResponseMapper
{

    public HashtagResponse hashtagToHashtagResponse(Hashtag hashtag)
    {

	HashtagResponse hashtagResponse = new HashtagResponse();
	hashtagResponse.setId(hashtag.getCustomId());
	hashtagResponse.setName(hashtag.getName());
	hashtagResponse.setDescription(hashtag.getDescription());
	hashtagResponse.setIcon(hashtag.getIcon());
	hashtagResponse.setInsertDate(hashtag.getInsertDate().toInstant(ZoneOffset.UTC));
	if(hashtagResponse.getModifDate() != null)
	{
	    hashtagResponse.setModifDate(hashtag.getModifDate().toInstant(ZoneOffset.UTC));
	}

	hashtagResponse.setHashtag(hashtag.getValue());
	hashtagResponse.setVersion(hashtag.getVersion());
	hashtagResponse.setNemonic(hashtag.getNemonic());

	return hashtagResponse;
    }


    public ResponsePage<HashtagResponse> hashtagPageToHashtagResposePage(ResponsePage<Hashtag> hashtags)
    {

	List<HashtagResponse> hashtagsResponse = new ArrayList<HashtagResponse>();

	for (Hashtag organizer : hashtags)
	{
	    hashtagsResponse.add(hashtagToHashtagResponse(organizer));

	}

	return new ResponsePage<HashtagResponse>(hashtagsResponse, hashtags.getPageable(), hashtags.getTotalElements());

    }


    public Collection<HashtagResponse> hashtagCollectionToHashtagResponseCollection(Collection<Hashtag> hashtags)
    {

	List<HashtagResponse> hashtagsResponse = new ArrayList<HashtagResponse>();

	for (Hashtag organizer : hashtags)
	{
	    hashtagsResponse.add(hashtagToHashtagResponse(organizer));

	}

	return hashtagsResponse;

    }


    public Hashtag hashtagResponseToHashtag(HashtagResponse hashtagResponse)
    {

	Hashtag hashtag = new Hashtag();
	hashtag.setCustomId(hashtagResponse.getId());
	hashtag.setName(hashtagResponse.getName());
	hashtag.setDescription(hashtagResponse.getDescription());
	hashtag.setIcon(hashtagResponse.getIcon());
	hashtag.setValue(hashtagResponse.getHashtag());
	hashtag.setInsertDate(LocalDateTime.ofInstant(hashtagResponse.getInsertDate(), ZoneOffset.UTC));

	if(hashtag.getModifDate() != null)
	{
	    hashtag.setModifDate(LocalDateTime.ofInstant(hashtagResponse.getModifDate(), ZoneOffset.UTC));
	}

	hashtag.setVersion(hashtagResponse.getVersion());

	return hashtag;
    }


    public ResponsePage<Hashtag> hashtagResponsePageToHashtagPage(ResponsePage<HashtagResponse> hashtagsResponse)
    {

	List<Hashtag> hashtags = new ArrayList<Hashtag>();

	for (HashtagResponse hashtagResponse : hashtagsResponse)
	{
	    hashtags.add(hashtagResponseToHashtag(hashtagResponse));

	}

	return new ResponsePage<Hashtag>(hashtags, hashtagsResponse.getPageable(), hashtagsResponse.getTotalElements());

    }


    public ResponsePage<Hashtag> hashtagCollectionToHashtagResposePage(ResponsePageJson<HashtagResponse> hashtagsResponse)
    {

	List<Hashtag> hashtags = new ArrayList<Hashtag>();

	PageRequest pageRequest = PageRequest.of(hashtagsResponse.getPageable().getPageNumber(), hashtagsResponse.getPageable().getPageSize());

	for (HashtagResponse hashtagResponse : hashtagsResponse.getContent())
	{

	    Hashtag hashtag = hashtagResponseToHashtag(hashtagResponse);

	    hashtags.add(hashtag);
	}

	return new ResponsePage<Hashtag>(hashtags, pageRequest, hashtagsResponse.getTotalElements());

    }


    public List<Hashtag> hashtagResponseListToHashtagList(List<HashtagResponse> hashtagsResponse)
    {

	List<Hashtag> hashtags = new ArrayList<Hashtag>();

	for (HashtagResponse hashtagResponse : hashtagsResponse)
	{
	    hashtags.add(hashtagResponseToHashtag(hashtagResponse));

	}

	return hashtags;
    }

}
