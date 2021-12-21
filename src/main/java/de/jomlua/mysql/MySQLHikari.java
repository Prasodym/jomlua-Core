package de.jomlua.mysql;

import com.zaxxer.hikari.HikariConfig;

import java.util.Properties;

public class MySQLHikari<connection> {

    public static String host;
    public static String port;
    public static String database;
    public static String user;
    public static String password;

    public static int prepStmtCacheSqlLimit;
    public static boolean cachePrepStmts;
    public static int prepStmtCacheSize;

    Properties properties;
    HikariConfig conf = new HikariConfig(properties);
}
