package es.cic.tessa.common.model;


public class OrmConstants
{

    // persistence units
    public static final String PERSISTENCE_PU = "Persistence_PU";

    // key properties
    public static final String DRIVER_KEY = "jakarta.persistence.jdbc.driver";
    public static final String URL_KEY = "jakarta.persistence.jdbc.url";
    public static final String USER_KEY = "jakarta.persistence.jdbc.user";
    public static final String PASSWORD_KEY = "jakarta.persistence.jdbc.password";

    public static final String DIALECT_KEY = "hibernate.dialect";
    public static final String BATCH_SIZE_KEY = "hibernate.jdbc.batch_size";
    public static final String SHOW_SQL_KEY = "hibernate.show_sql";

    public static final String MIN_SIZE_KEY = "hibernate.c3p0.min_size";
    public static final String MAX_SIZE_KEY = "hibernate.c3p0.max_size";
    public static final String ACQUIRE_INCREMENT_KEY = "hibernate.c3p0.acquire_increment";
    public static final String TIMEOUT_KEY = "hibernate.c3p0.timeout";
    public static final String MAX_STATEMENTS_KEY = "hibernate.c3p0.max_statements";

    public static final int BATCH_SIZE_DEFAULT = 50;

    // drivers and dialects
    public static final String SQLSERVER_DRIVER_DEFAULT = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String SQLSERVER_DIALECT_DEFAULT = "org.hibernate.dialect.SQLServerDialect";
    // jdbc:sqlserver://hostname:port;databaseName=db;

    public static final String ORACLE_DRIVER_DEFAULT = "oracle.jdbc.driver.OracleDriver";
    public static final String ORACLE_DIALECT_DEFAULT = "org.hibernate.dialect.Oracle12cDialect";
    // jdbc:oracle:thin:@hostname:port:sid
    // jdbc:oracle:thin:@hostname:port/service

    public static final String MYSQL_DRIVER_DEFAULT = "com.mysql.cj.jdbc.Driver";
    public static final String MYSQL_DIALECT_DEFAULT = "org.hibernate.dialect.MySQL8Dialect";
    // jdbc:mysql://hostname:port/databaseName

    public static final String MARIADB_DRIVER_DEFAULT = "org.mariadb.jdbc.Driver";
    public static final String MARIADB_DIALECT_DEFAULT = "org.hibernate.dialect.MariaDB53Dialect";
    // jdbc:mariadb://hostname:port/databaseName

    public static final String POSTGRES_DRIVER_DEFAULT = "org.postgresql.Driver";
    public static final String POSTGRES_DIALECT_DEFAULT = "org.hibernate.dialect.PostgreSQLDialect";
    // jdbc:postgres://hostname:5432/databaseName
}
