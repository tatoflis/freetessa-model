package es.cic.tessa.model.types;


public enum DatabaseSystemType
{

 ORACLE("Oracle"),
     MYSQL("Mysql"),
     SQLSERVER("SQLServer"),
     MARIADB("MariaDB"),
     POSTGRES("Postgres");

    public String code;

    private DatabaseSystemType(String code)
    {

	this.code = code;
    }


    public static DatabaseSystemType fromString(String code)
    {

	if(code != null)
	{
	    for (DatabaseSystemType databaseSystemType : DatabaseSystemType.values())
	    {
		if(code.equalsIgnoreCase(databaseSystemType.code))
		{
		    return databaseSystemType;
		}
	    }
	}
	return null;
    }


    public String getCode()
    {

	return code;
    }
}
