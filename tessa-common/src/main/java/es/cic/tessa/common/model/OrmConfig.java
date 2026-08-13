package es.cic.tessa.common.model;


import java.util.Properties;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class OrmConfig implements DbConfig
{

    private static final long serialVersionUID = 1L;

    private String driver;
    private String dialect;
    private String url;
    private String user;
    private String password;
    private String showSql;
    private Integer batchSize;
    private Integer minSize;
    private Integer maxSize;
    private Integer acquireIncrement;
    private Integer timeout;
    private Integer maxStatements;

    public OrmConfig()
    {

    }


    public String getDriver()
    {

	return driver;
    }


    public void setDriver(String driver)
    {

	this.driver = driver;
    }


    public String getDialect()
    {

	return dialect;
    }


    public void setDialect(String dialect)
    {

	this.dialect = dialect;
    }


    public String getUrl()
    {

	return url;
    }


    public void setUrl(String url)
    {

	this.url = url;
    }


    public String getUser()
    {

	return user;
    }


    public void setUser(String user)
    {

	this.user = user;
    }


    public String getPassword()
    {

	return password;
    }


    public void setPassword(String password)
    {

	this.password = password;
    }


    public Integer getMinSize()
    {

	return minSize;
    }


    public void setMinSize(Integer minSize)
    {

	this.minSize = minSize;
    }


    public Integer getMaxSize()
    {

	return maxSize;
    }


    public void setMaxSize(Integer maxSize)
    {

	this.maxSize = maxSize;
    }


    public Integer getAcquireIncrement()
    {

	return acquireIncrement;
    }


    public void setAcquireIncrement(Integer acquireIncrement)
    {

	this.acquireIncrement = acquireIncrement;
    }


    public Integer getTimeout()
    {

	return timeout;
    }


    public void setTimeout(Integer timeout)
    {

	this.timeout = timeout;
    }


    public Integer getMaxStatements()
    {

	return maxStatements;
    }


    public void setMaxStatements(Integer maxStatements)
    {

	this.maxStatements = maxStatements;
    }


    public String getShowSql()
    {

	return showSql;
    }


    public void setShowSql(String showSql)
    {

	this.showSql = showSql;
    }


    public Integer getBatchSize()
    {

	return batchSize;
    }


    public void setBatchSize(Integer batchSize)
    {

	this.batchSize = batchSize;
    }


    @JsonIgnore
    @Override
    public Properties getConfigProperties()
    {

	Properties properties = new Properties();

	properties.put(OrmConstants.DRIVER_KEY, this.driver);
	properties.put(OrmConstants.DIALECT_KEY, this.dialect);
	properties.put(OrmConstants.URL_KEY, this.url);
	properties.put(OrmConstants.USER_KEY, this.user);
	properties.put(OrmConstants.PASSWORD_KEY, this.password);
	if(this.batchSize != null)
	{
	    properties.put(OrmConstants.BATCH_SIZE_KEY, this.batchSize);
	}

	if(this.showSql != null)
	    properties.put(OrmConstants.SHOW_SQL_KEY, this.showSql);

	if(this.minSize != null)
	    properties.put(OrmConstants.MIN_SIZE_KEY, this.minSize);
	if(this.maxSize != null)
	    properties.put(OrmConstants.MAX_SIZE_KEY, this.maxSize);
	if(this.acquireIncrement != null)
	    properties.put(OrmConstants.ACQUIRE_INCREMENT_KEY, this.acquireIncrement);
	if(this.timeout != null)
	    properties.put(OrmConstants.TIMEOUT_KEY, this.timeout);
	if(this.maxStatements != null)
	    properties.put(OrmConstants.MAX_STATEMENTS_KEY, this.maxStatements);

	return properties;
    }

}
