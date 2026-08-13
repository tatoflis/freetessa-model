package es.cic.tessa.excel.organizer.model;


public class OrganizerCSVConstants
{

    public static final String ORGANIZER_HEADERS = "Id,Name,Description,Nemonic,Icon,Insert,Modification,Version,Type,Nemonic_Parent,Nemonic_Metadata";

    public static final String ORGANIZER_FILE = "Organizers.csv";
    public static final String ORGANIZER_DELETE_FILE = "DeleteOrganizers.csv";

    public static final String CYPHER_ORGANIZER_NODES = "Organizer Nodes";
    public static final String CYPHER_ORGANIZER_RELATIONS = "Organizer Relations";
    public static final String CYPHER_DELETE_ORGANIZERS = "Delete Organizer";
    public static final String CYPHER_ORGANIZER_PATH = "Organizer Path";

    public static final String CYPHER_HISTORICAL_ORGANIZER_NODES = "Historical Organizer Nodes";
    public static final String CYPHER_HISTORICAL_ORGANIZER_RELATIONS = "Historical Organizer Relations";

    private OrganizerCSVConstants()
    {

	super();
    }
}
