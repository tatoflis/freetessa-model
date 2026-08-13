package es.cic.tessa.model.csv;


import java.util.Objects;
import es.cic.tessa.model.Organizer;


public class OrganizerCSV implements Comparable<OrganizerCSV>, TessaCSVElement
{

    private Organizer organizer;

    public OrganizerCSV(Organizer organizer)
    {

	this.organizer = organizer;

    }


    public Organizer getOrganizer()
    {

	return organizer;
    }


    public void setOrganizer(Organizer organizer)
    {

	this.organizer = organizer;
    }


    @Override
    public int hashCode()
    {

	return Objects.hash(organizer);
    }


    @Override
    public boolean equals(Object obj)
    {

	if(this == obj)
	    return true;
	if(obj == null)
	    return false;
	if(getClass() != obj.getClass())
	    return false;
	OrganizerCSV other = (OrganizerCSV) obj;
	return Objects.equals(organizer, other.organizer);
    }


    @Override
    public int compareTo(OrganizerCSV o)
    {

	String thisNemonic = this.organizer != null ? this.organizer.getNemonic() : null;
	String otherNemonic = o.getOrganizer() != null ? o.getOrganizer().getNemonic() : null;
	if(thisNemonic == null && otherNemonic == null) return 0;
	if(thisNemonic == null) return -1;
	if(otherNemonic == null) return 1;
	return thisNemonic.compareTo(otherNemonic);
    }

}
