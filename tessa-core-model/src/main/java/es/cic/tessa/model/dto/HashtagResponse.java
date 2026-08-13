package es.cic.tessa.model.dto;


import es.cic.tessa.model.dto.support.AbstractEntityResponse;


public class HashtagResponse extends AbstractEntityResponse
{

    private static final long serialVersionUID = 1L;

    private String hashtag;

    public String getHashtag()
    {

	return hashtag;
    }


    public void setHashtag(String hashtag)
    {

	this.hashtag = hashtag;
    }

}
