package es.cic.tessa.common.support;


import java.util.Optional;
import org.springframework.data.domain.PageRequest;


public class ServiceUtils
{

    public static int getCount(Optional<Integer> optionalCount)
    {

	int count = 0;
	if(!optionalCount.isPresent())
	{
	    count = 0;
	}
	else
	{
	    count = optionalCount.get();
	}

	return count;
    }


    public static PageRequest getPagination(PageConfig pageConfig)
    {

	PageRequest pagination = null;

	if(pageConfig != null)
	{
	    pagination = PageRequest.of(pageConfig.getNumPage(), pageConfig.getSizePage());
	}
	else
	{
	    pagination = PageRequest.of(0, Integer.MAX_VALUE);
	}

	return pagination;
    }
}
