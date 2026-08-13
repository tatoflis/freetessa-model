package es.cic.tessa.model.historical.mapper;


import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Component;
import es.cic.tessa.model.HistoricalHashtag;
import es.cic.tessa.model.historical.dto.HistoricalHashtagResponse;


@Component
public class HistoricalHashtagResponseMapper
{

    public HistoricalHashtagResponse hashtagToHashtagResponse(HistoricalHashtag hashtag)
    {

	HistoricalHashtagResponse hashtagResponse = new HistoricalHashtagResponse();

	hashtagResponse.setId(hashtag.getCustomId());
	hashtagResponse.setNodeId(hashtag.getId());
	hashtagResponse.setName(hashtag.getName());
	hashtagResponse.setDescription(hashtag.getDescription());
	hashtagResponse.setIcon(hashtag.getIcon());
	hashtagResponse.setInsertDate(hashtag.getInsertDate().toInstant(ZoneOffset.UTC));

	if(hashtag.getModifDate() != null)
	{
	    hashtagResponse.setModifDate(hashtag.getModifDate().toInstant(ZoneOffset.UTC));
	}

	hashtagResponse.setHashtag(hashtag.getValue());
	hashtagResponse.setVersion(hashtag.getVersion());
	hashtagResponse.setNemonic(hashtag.getNemonic());
	hashtagResponse.setHistoricalChangeOperation(hashtag.getHistoricalChangeOperation());

	return hashtagResponse;
    }


    public Collection<HistoricalHashtagResponse> hashtagsToHashtagResponseCollection(Collection<HistoricalHashtag> hashtags)
    {

	Collection<HistoricalHashtagResponse> hashtagsResponse = new ArrayList<>();

	for (HistoricalHashtag hashtag : hashtags)
	{
	    hashtagsResponse.add(hashtagToHashtagResponse(hashtag));
	}

	return hashtagsResponse;
    }

}
