package es.cic.tessa.model.mappers;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import es.cic.tessa.common.support.ResponsePage;
import es.cic.tessa.model.Hashtag;
import es.cic.tessa.model.dto.HashtagRequest;


@Component
public class HashtagRequestMapper
{

    public Hashtag hashtagRequestToHashtag(HashtagRequest hashtagRequest, Set<String> groups)
    {

	Hashtag hashtag = new Hashtag();
	hashtag.setCustomId(hashtagRequest.getId());
	hashtag.setName(hashtagRequest.getHashtag());
	hashtag.setNameLower(hashtagRequest.getHashtag().toLowerCase());
	hashtag.setDescription("Hashtag created automatically by the system");
	hashtag.setValue(hashtagRequest.getHashtag());
	hashtag.setGroups(groups);

	if(hashtagRequest.getVersion() != null)
	{
	    hashtag.setVersion(hashtagRequest.getVersion());
	}

	return hashtag;

    }


    public ResponsePage<Hashtag> hashtagRequestPageToHashtagPage(ResponsePage<HashtagRequest> hashtagsRequest, Set<String> groups)
    {

	List<Hashtag> hashtags = new ArrayList<Hashtag>();

	for (HashtagRequest hashtagRequest : hashtagsRequest)
	{
	    hashtags.add(hashtagRequestToHashtag(hashtagRequest, groups));
	}

	return new ResponsePage<Hashtag>(hashtags, hashtagsRequest.getPageable(), hashtagsRequest.getTotalElements());

    }


    public Collection<Hashtag> hashtagRequestCollectionToHashtagCollection(Collection<HashtagRequest> hashtagsRequest, Set<String> groups)
    {

	Set<Hashtag> hashtags = new HashSet<Hashtag>();

	for (HashtagRequest hashtagRequest : hashtagsRequest)
	{
	    hashtags.add(hashtagRequestToHashtag(hashtagRequest, groups));
	}

	return hashtags;

    }


    public HashtagRequest hashtagToHashtagRequest(Hashtag hashtag)
    {

	HashtagRequest hashtagRequest = new HashtagRequest();
	hashtagRequest.setId(hashtag.getCustomId());
	hashtagRequest.setName(hashtag.getName());
	hashtagRequest.setDescription("Hashtag created automatically by the system");
	hashtagRequest.setIcon(hashtag.getIcon());
	hashtagRequest.setHashtag(hashtag.getValue());
	hashtagRequest.setVersion(hashtag.getVersion());

	return hashtagRequest;

    }

}
