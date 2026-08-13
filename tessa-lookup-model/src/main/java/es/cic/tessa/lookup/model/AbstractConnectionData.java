package es.cic.tessa.lookup.model;


import com.fasterxml.jackson.annotation.JsonProperty;


public abstract class AbstractConnectionData extends AbstractLookupData
{

    private static final long serialVersionUID = 1L;

    public static final String PROTOCOL = "Protocol";
    public static final String HOST = "Host";
    public static final String PORT = "Port";
    public static final String USER = "User";
    public static final String PASSWORD = "Password";

    @JsonProperty("protocol")
    protected String protocol;

    @JsonProperty("host")
    protected String host;

    @JsonProperty("port")
    protected int port;

    @JsonProperty("user")
    protected String user;

    @JsonProperty("password")
    protected String password;

    public String getProtocol()
    {

	return protocol;
    }


    public void setProtocol(String protocol)
    {

	this.protocol = protocol;
    }


    public String getHost()
    {

	return host;
    }


    public void setHost(String host)
    {

	this.host = host;
    }


    public int getPort()
    {

	return port;
    }


    public void setPort(int port)
    {

	this.port = port;
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

}
