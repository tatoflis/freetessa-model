package es.cic.tessa.model.support;


import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.Property;
import es.cic.tessa.model.properties.CommonProperties;


public abstract class LockableElement extends IdentificableElement
{

    private static final long serialVersionUID = 1L;

    @Version
    @Property(name = CommonProperties.VERSION)
    protected Long version = Long.valueOf(0);

    public LockableElement()
    {

    }


    public Long getVersion()
    {

	return version;
    }


    public void setVersion(Long version)
    {

	this.version = version;
    }

}