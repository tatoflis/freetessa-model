package es.cic.tessa.lookup.model.db;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import tools.jackson.databind.annotation.JsonSerialize;
import es.cic.tessa.lookup.model.AbstractConnectionData;


@JsonInclude(Include.NON_NULL)
@JsonSerialize
public class AssetLookupDB extends AbstractConnectionData
{

    private static final long serialVersionUID = 1L;

    public static final String SYSTEM = "System";
    public static final String SCHEMA = "Schema";

    @JsonProperty("system")
    private String system;

    @JsonProperty("schema")
    private String schema;

    public String getSystem()
    {

	return system;
    }


    public void setSystem(String system)
    {

	this.system = system;
    }


    public String getSchema()
    {

	return schema;
    }


    public void setSchema(String schema)
    {

	this.schema = schema;
    }

}
