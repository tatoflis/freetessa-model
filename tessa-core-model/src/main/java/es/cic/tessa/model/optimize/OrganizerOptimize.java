package es.cic.tessa.model.optimize;


public class OrganizerOptimize extends TessaElementOptimize
{

    private static final long serialVersionUID = 1L;

    private OrganizerOptimize parentOrganizerOptimize;

    private AssetOptimize metadataOptimize;

    public OrganizerOptimize getParentOrganizerOptimize()
    {

	return parentOrganizerOptimize;
    }


    public void setParentOrganizerOptimize(OrganizerOptimize parentOrganizerOptimize)
    {

	this.parentOrganizerOptimize = parentOrganizerOptimize;
    }


    public AssetOptimize getMetadataOptimize()
    {

	return metadataOptimize;
    }


    public void setMetadataOptimize(AssetOptimize metadataOptimize)
    {

	this.metadataOptimize = metadataOptimize;
    }

}
