package es.cic.tessa.model.support;


import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;


public abstract class AbstractNotificationService
{

    private static final String COMMA = ",";

    protected String getIDTessaElement(TessaElement tessaElement, Set<String> groups)
    {

	Set<TessaElement> tessaElements = new LinkedHashSet<>();
	tessaElements.add(tessaElement);

	return getIDSTessaElements(tessaElements, groups);
    }


    protected String getIDSTessaElements(Collection<? extends TessaElement> tessaElements, Set<String> groups)
    {

	StringBuilder content = new StringBuilder();

	for (TessaElement tessaElement : tessaElements)
	{
	    if(tessaElement.getCustomId() != null)
	    {
		content.append("[");
		content.append(tessaElement.getCustomId());
		content.append("][");
		content.append(String.join("|", groups));
		content.append("]");
		content.append(COMMA);
	    }
	}

	if(content.length() > 0)
	{
	    content.deleteCharAt(content.length() - 1);
	}

	return content.toString();
    }

}
