package es.cic.tessa.common.model;


import java.io.Serializable;
import java.util.Properties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "class")
@JsonSubTypes(
{ @Type(value = OrmConfig.class, name = "ormConfig") })
public interface DbConfig extends Serializable
{

    Properties getConfigProperties();
}
