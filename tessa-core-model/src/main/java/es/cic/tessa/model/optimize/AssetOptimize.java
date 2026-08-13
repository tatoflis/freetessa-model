package es.cic.tessa.model.optimize;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class AssetOptimize extends TessaElementOptimize
{

    private static final long serialVersionUID = 1L;

    private String identificator;

    private AssetOptimize assetDependsOptimize;

    private TemplateOptimize templateOptimize;

    private Boolean active = Boolean.TRUE;

    private Collection<AssetValueOptimize> assetValueOptimize = new HashSet<>();

    private Collection<OrganizerOptimize> organizerOptimize = new HashSet<>();

    public AssetOptimize()
    {

    }


    public AssetOptimize(String name, Set<String> groups)
    {

	super(name, groups);

    }


    public String getIdentificator()
    {

	return identificator;
    }


    public void setIdentificator(String identificator)
    {

	this.identificator = identificator;
    }


    public AssetOptimize getAssetDependsOptimize()
    {

	return assetDependsOptimize;
    }


    public void setAssetDependsOptimize(AssetOptimize assetDependsOptimize)
    {

	this.assetDependsOptimize = assetDependsOptimize;
    }


    public TemplateOptimize getTemplateOptimize()
    {

	return templateOptimize;
    }


    public void setTemplateOptimize(TemplateOptimize templateOptimize)
    {

	this.templateOptimize = templateOptimize;
    }


    public Collection<AssetValueOptimize> getAssetValueOptimize()
    {

	return assetValueOptimize;
    }


    public void setAssetValueOptimize(Collection<AssetValueOptimize> assetValueOptimize)
    {

	this.assetValueOptimize = assetValueOptimize;
    }


    public Collection<OrganizerOptimize> getOrganizerOptimize()
    {

	return organizerOptimize;
    }


    public void setOrganizerOptimize(Collection<OrganizerOptimize> organizerOptimize)
    {

	this.organizerOptimize = organizerOptimize;
    }


    public Boolean isActive()
    {

	return active;
    }


    public void setActive(Boolean active)
    {

	this.active = active;
    }


    @Override
    public String toString()
    {

	return "[name=" + getName() + ", templateOptimize=" + templateOptimize.getName() + "]";
    }

}
