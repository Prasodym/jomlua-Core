package de.jomlua.mysql;

import de.jomlua.util.ChatOutput;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlExistTable {

    public static void sqlBlocks() {
        try {
            PreparedStatement pdo1 = MySQL.getConnection().prepareStatement("SHOW TABLES LIKE ?");
            pdo1.setString(1, "stats_blocks");
            ResultSet rs = pdo1.executeQuery();
            Boolean ob = rs.next();

            pdo1.executeQuery().next();
            if (!ob) {
                try {
                    PreparedStatement pdo = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS stats_blocks (id INT NOT NULL AUTO_INCREMENT, uuid VARCHAR(150), blockname VARCHAR(50), amount INT(11),PRIMARY KEY (id))");
                    pdo.executeUpdate();
                    Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "§aTabelle neu angelegt:" + "§e stats_blocks");
                } catch (SQLException e) {
                    Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "§4ERROR | Mysql:§c Sql config sin fehlerhaft");
                    Bukkit.broadcastMessage(ChatOutput.PREFIX.getText() + ChatOutput.DANGER.getText() + "Es besteht ein internes SQL Problem, restart Server.");
                }
            } else {
                Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "§aTabelle wurde geladen:" + "§e stats_blocks");
            }

        } catch (SQLException e) {
            Bukkit.broadcastMessage(ChatOutput.PREFIXC.getText() + ChatOutput.DANGER.getText() + "Es besteht ein internes SQL Problem, restart Server");
            //e.printStackTrace();
        }
    }
    public static void sqlInventory() {
        try {
            PreparedStatement pdo1 = MySQL.getConnection().prepareStatement("SHOW TABLES LIKE ?");
            pdo1.setString(1, "stats_blocks");
            ResultSet rs = pdo1.executeQuery();
            Boolean ob = rs.next();

            pdo1.executeQuery().next();
            if (!ob) {
                try {
                    PreparedStatement pdo = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS inventory (user CHAR(36), inventory LONGTEXT)");
                    pdo.executeUpdate();
                    Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "§aTabelle neu angelegt:" + "§e inventory");
                } catch (SQLException e) {
                    Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "§4ERROR | Mysql:§c Sql config sin fehlerhaft");
                    Bukkit.broadcastMessage(ChatOutput.PREFIX.getText() + ChatOutput.DANGER.getText() + "Es besteht ein internes SQL Problem, restart Server.");
                }
            } else {
                Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "§aTabelle wurde geladen:" + "§e inventory");
            }

        } catch (SQLException e) {
            Bukkit.broadcastMessage(ChatOutput.PREFIXC.getText() + ChatOutput.DANGER.getText() + "Es besteht ein internes SQL Problem, restart Server");
           // e.printStackTrace();
        }
    }


    public static void sqlUser() {
        try {
            PreparedStatement pdo1 = MySQL.getConnection().prepareStatement("SHOW TABLES LIKE ?");
            pdo1.setString(1, "users");
            ResultSet rs = pdo1.executeQuery();
            Boolean ob1 = rs.next();

            pdo1.executeQuery().next();

            if (!ob1) {

                try {
                    PreparedStatement pdo = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT, uuid VARCHAR(150), username VARCHAR(50), playtime int(11), firstplay VARCHAR(50),deaths int(11), money VARCHAR(50), adress VARCHAR(50), PRIMARY KEY (id))");
                    pdo.executeUpdate();
                    Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "§aTabelle neu angelegt:" + "§e users");

                } catch (SQLException e2) {
                    //e2.printStackTrace();
                    Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "§4ERROR | Mysql:§c Sql config sin fehlerhaft");
                    Bukkit.broadcastMessage(ChatOutput.PREFIX.getText() + ChatOutput.DANGER.getText() + "Es besteht ein internes SQL Problem, restart Server.");
                }

            } else {
                Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "§aTabelle wurde geladen:" + "§e users");
            }


        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "§4ERROR | Mysql:§c Sql config fehlerhaft");
            Bukkit.broadcastMessage(ChatOutput.PREFIX.getText() + ChatOutput.DANGER.getText() + "Es besteht ein internes SQL Problem, restart Server.");
            //e.printStackTrace();
        }

    }

    public static void sqlPermissions() {
        try {
            PreparedStatement pdo1 = MySQL.getConnection().prepareStatement("SHOW TABLES LIKE ?");
            pdo1.setString(1, "permissions");
            ResultSet rs = pdo1.executeQuery();
            Boolean ob1 = rs.next();

            pdo1.executeQuery().next();

            if (!ob1) {

                try {
                    PreparedStatement pdo = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS permissions (id INT NOT NULL AUTO_INCREMENT, permissions VARCHAR(50),groupid VARCHAR(50), prefix VARCHAR(50), PRIMARY KEY (id))");
                    pdo.executeUpdate();
                    Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "§aTabelle neu angelegt:" + "§e permisions");

                } catch (SQLException e2) {
                    //e2.printStackTrace();
                    Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "§4ERROR | Mysql:§c Sql config sin fehlerhaft");
                    Bukkit.broadcastMessage(ChatOutput.PREFIX.getText() + ChatOutput.DANGER.getText() + "Es besteht ein internes SQL Problem, restart Server.");
                }

            } else {
                Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "§aTabelle wurde geladen:" + "§e permissions");
            }


        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "§4ERROR | Mysql:§c Sql config sind fehlerhaft");
            Bukkit.broadcastMessage(ChatOutput.PREFIX.getText() + ChatOutput.DANGER.getText() + "Es besteht ein internes SQL Problem, restart Server.");
            //e.printStackTrace();
        }
    }

    public static void sqlGroups() {
        try {
            PreparedStatement pdo1 = MySQL.getConnection().prepareStatement("SHOW TABLES LIKE ?");
            pdo1.setString(1, "groups");
            ResultSet rs = pdo1.executeQuery();
            Boolean ob1 = rs.next();

            pdo1.executeQuery().next();

            if (!ob1) {

                try {
                    PreparedStatement pdo = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS groups (id INT NOT NULL AUTO_INCREMENT, groups VARCHAR(150), PRIMARY KEY (id))");
                    pdo.executeUpdate();
                    Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "§aTabelle neu angelegt:" + "§e groups");

                } catch (SQLException e2) {
                    //e2.printStackTrace();
                    Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "§4ERROR | Mysql:§c Sql config sind fehlerhaft");
                    Bukkit.broadcastMessage(ChatOutput.PREFIX.getText() + ChatOutput.DANGER.getText() + "Es besteht ein internes SQL Problem, restart Server.");
                }

            } else {
                Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "§aTabelle wurde geladen:" + "§e groups");
            }



        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "§4ERROR | Mysql:§c Sql config fehlerhaft");
            Bukkit.broadcastMessage(ChatOutput.PREFIX.getText() + ChatOutput.DANGER.getText() + "Es besteht ein internes SQL Problem, restart Server.");
            //e.printStackTrace();
        }
    }
    private void getgroup(){
        
    }

    public static void sqlBans() {
        try {
            PreparedStatement pdo1 = MySQL.getConnection().prepareStatement("SHOW TABLES LIKE ?");
            pdo1.setString(1, "banned");
            ResultSet rs = pdo1.executeQuery();
            Boolean ob1 = rs.next();

            pdo1.executeQuery().next();

            if (!ob1) {

                try {
                    PreparedStatement pdo = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS banned (user VARCHAR(50),uuid VARCHAR(50), end VARCHAR(50), reason VARCHAR(100), law VARCHAR(50))");
                    pdo.executeUpdate();
                    Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "§aTabelle neu angelegt:" + "§e banned");

                } catch (SQLException e2) {
                    //e2.printStackTrace();
                    Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "§4ERROR | Mysql:§c Sql config sin fehlerhaft");
                    Bukkit.broadcastMessage(ChatOutput.PREFIX.getText() + ChatOutput.DANGER.getText() + "Es besteht ein internes SQL Problem, restart Server.");
                }

            } else {
                Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "§aTabelle wurde geladen:" + "§e banned");
            }


        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "§4ERROR | Mysql:§c Sql config sind fehlerhaft");
            Bukkit.broadcastMessage(ChatOutput.PREFIX.getText() + ChatOutput.DANGER.getText() + "Es besteht ein internes SQL Problem, restart Server.");
            //e.printStackTrace();
        }
    }

}
/*
    SPIELER/USER
    ID   UUID    NAME    ZEIT  GROUP-ID(INT)

    GROUP
    ID   NAME     GROUP-PREFI<"&7[Vagina]&7">

    PERMISSION
    ID  GID(INT)   SERVER  WORLD   UNIX-TIME

    /Heal : jomlua.heal output: true, false
            If(jomlua.heal){
                führe aus
            }else{
             Oder Das..
            }

            try{

            }catch(Exception e){

            }

               HashMap<String, String>
    ADMIN:
        PERMISSION:
            PERMISSION.FLY,
            PERMISSION.HEAL,
            PERMISSION.GAMMODE
        TIME:
            PERMISSION.FLY: 02156586563
        SERVER:

        user(GID(1)) -> SERVER -> permission(sucht(permission.fly == GID(1)))

        start server -> alle infos der permissions herunter. = HashMap<String, String>
        server kontroliert ob HashMap noch gleich SQL Datenbank ist.
        server -> alle infos der permissions herunter. = HashMap<String, String>-


        id: <
            name: ADMIN,
            prefix: &7Vagina&7,
            permission:
                fly,
                heal,
                penetration
        >


        */

