package de.jomlua.mysql;

import de.jomlua.util.ChatOutput;
import de.jomlua.util.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import javax.sql.DataSource;
import java.sql.*;


public class MySQL<connection> {

    public static String host;
    public static String port;
    public static String database;
    public static String user;
    public static String password;

    public static Connection con;
    public static int prepStmtCacheSqlLimit;


    public static void connect(){
        if (!isConnected()){
            try {
                // con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&useUnicode=yes", user, password);
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useJDBCCompliantTimezoneShift=true&&serverTimezone=UTC&&useUnicode=true&autoReconnect=true", user, password);
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIXC.getText()) + "§4ERROR | Mysql:§c the login or connection is impossible!");
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIXC.getText()) + "§4ERROR | Mysql§c Please check again your config and that the sql server is accessible!");
                e.printStackTrace();
            }
        }else{
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIXC.getText()) + "§4Mysql:§c the login or connection is impossible!");
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',ChatOutput.PREFIXC.getText()) + "§4Mysql:§c Please check again your config and that the sql server is accessible!");
        }
    }
    public static void disconnect(){
        if (isConnected()){
            try {
                con.close();
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ChatOutput.PREFIXC.getText()) + "§cMysql Disconnected!");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static boolean isConnected(){
        return con != null;
    }


    public static Connection getConnection(){
        return con;
    }

    public static void update(String query){
        if (isConnected()){
            try {
                con.createStatement().executeUpdate(query);
                System.out.println("Injected");
            }catch (SQLException e){
                e.printStackTrace();
                System.out.println("fail injected");
                disconnect();
            }
        }else{
            disconnect();
            System.out.println("Fail Connected");
        }
    }
    public static ResultSet getData(String query){
        if (!isConnected()){
            try{
                return con.createStatement().executeQuery(query);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }







}

