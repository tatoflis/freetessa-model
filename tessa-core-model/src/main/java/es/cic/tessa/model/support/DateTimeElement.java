package es.cic.tessa.model.support;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.neo4j.core.schema.Property;
import es.cic.tessa.model.properties.CommonProperties;


public abstract class DateTimeElement extends LockableElement
{

    private static final long serialVersionUID = 1L;

    @Property(name = CommonProperties.INSERT)
    @CreatedDate
    protected LocalDateTime insertDate = LocalDateTime.now(ZoneOffset.UTC);

    @Property(name = CommonProperties.MODIFICATION)
    @LastModifiedDate
    protected LocalDateTime modifDate;

    public DateTimeElement()
    {

    }


    public LocalDateTime getInsertDate()
    {

	return insertDate;
    }


    public void setInsertDate(LocalDateTime insertDate)
    {

	this.insertDate = insertDate;
    }


    public LocalDateTime getModifDate()
    {

	return modifDate;
    }


    public void setModifDate(LocalDateTime modifDate)
    {

	this.modifDate = modifDate;
    }

}