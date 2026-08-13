package es.cic.tessa.common.support;


import java.util.ArrayList;
import java.util.Collection;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import tools.jackson.databind.JsonNode;


public class ResponsePage<T> extends PageImpl<T>
{

    private static final long serialVersionUID = 3248189030448292002L;

    public ResponsePage(Collection<T> content, int number, int size, Long totalElements, JsonNode pageable, boolean last, int totalPages, JsonNode sort, boolean first, int numberOfElements)
    {

	super(new ArrayList<T>(content), PageRequest.of(number, size), totalElements);
    }


    public ResponsePage(Collection<T> content, Pageable pageable, long totalElements)
    {

	super(new ArrayList<T>(content), pageable, totalElements);
    }


    public ResponsePage(Collection<T> content)
    {

	super(new ArrayList<T>(content));
    }


    public ResponsePage()
    {

	super(new ArrayList<T>());
    }

}
