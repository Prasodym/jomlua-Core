package de.jomlua.mysql;

import de.jomlua.JomluaCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public class ReadSQL {


    public static void getStorage(){

    }


    public static String getUUID(String player){
        try {
            PreparedStatement pdo = MySQL.getConnection().prepareStatement("SELECT * FROM users WHERE username = ?" );
            pdo.setString(1, player);
            ResultSet rs = pdo.executeQuery();
            while (rs.next()){
                return rs.getString("uuid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return player;
    }

    public static String getPlayername(String uuid){
        try {
            PreparedStatement pdo = MySQL.getConnection().prepareStatement("SELECT * FROM users WHERE uuid = ?" );
            pdo.setString(1, uuid);
            ResultSet rs = pdo.executeQuery();
            while (rs.next()){
                return rs.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return uuid;
    }

    public static Long getMoney(String uuid){
        try {
            PreparedStatement pdo = MySQL.getConnection().prepareStatement("SELECT * FROM users WHERE uuid = ?" );
            pdo.setString(1, uuid);
            ResultSet rs = pdo.executeQuery();
            while (rs.next()){
                return rs.getLong("money");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static  String getPlayerAdresse(String uuid){
        try {
            PreparedStatement pdo = MySQL.getConnection().prepareStatement("SELECT * FROM users WHERE uuid = ?" );
            pdo.setString(1, uuid);
            ResultSet rs = pdo.executeQuery();
            while (rs.next()){
                return rs.getString("adresse");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return "";
    }

    public static String getOnlineTime(String uuid){
        long amount = getTimePlayer(uuid);
        long seconds = amount/1000;
        long minute = seconds/60;
        long hour = minute/60;
        long day = hour/24;


        return day + "d " + hour + "h " + minute + "m ";
    }


    public static String getOnlineTimeReal(String uuid){
        //UUID id;
        Player player = Bukkit.getPlayer(getPlayername(uuid));

        long longMilis = 0;

        try {
             longMilis = JomluaCore.playerLogTimeMap.get(player);
        }catch (NullPointerException e){
        Bukkit.getLogger().info("This Player" + uuid + " is not avlivelet");
            //Bukkit.getLogger().log(Level.WARNING, "Fehler" + e.getMessage());
        }

        long quitMilis = System.currentTimeMillis();


        long addTime = (long) (quitMilis - longMilis) / 1000;


        long amount = getTimePlayer(uuid) + addTime;
        long seconds = amount/10000;
        long minute = seconds/60;
        long hour = minute/60;
        long day = hour/24;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(amount);

        int mMin = calendar.get(Calendar.MINUTE);
        int mHour = calendar.get(Calendar.HOUR);
        int mDay = calendar.get(Calendar.DAY_OF_WEEK);
        int mSecunbde = calendar.get(Calendar.SECOND);
/**
 * TODO: Time convert
 */

        return seconds + "s " + day + "d " + hour + "h " + minute + "m " ;
    }

    public static Long getTimePlayer(String uuid){
        try {
            PreparedStatement pdo = MySQL.getConnection().prepareStatement("SELECT * FROM users WHERE uuid = ?" );
            pdo.setString(1, uuid);
            ResultSet rs = pdo.executeQuery();
            while (rs.next()){
                return rs.getLong("playtime");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static final Integer getDeathCount(String uuid){
        try{
            Connection connection;
            PreparedStatement pdo = MySQL.getConnection().prepareStatement("SELECT * FROM users WHERE uuid = ?");
            pdo.setString(1,uuid);
            ResultSet rs = pdo.executeQuery();
            while (rs.next()){
                return rs.getInt("deaths");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public static Long getLastPlay(String uuid){
        try {
            PreparedStatement pdo = MySQL.getConnection().prepareStatement("SELECT * FROM users WHERE uuid = ?" );
            pdo.setString(1, uuid);
            ResultSet rs = pdo.executeQuery();
            while (rs.next()){
                return rs.getLong("lastplay");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getOfflineSpieler(){
        List<String> listspieler = new ArrayList<>();

        try {
            PreparedStatement pdo = MySQL.getConnection().prepareStatement("SELECT * FROM users" );
            ResultSet rs = pdo.executeQuery();
            while (rs.next()){
                listspieler.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listspieler;
    }

    public static boolean isUserExists(String uuid){
        try {
            PreparedStatement pdo = MySQL.getConnection().prepareStatement("SELECT playtime FROM users WHERE uuid = ?");
            pdo.setString(1, uuid);
            ResultSet rs = pdo.executeQuery();
            return rs.next();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return false;
    }

    public static boolean isInventoryExists(String uuid){
        try {

            PreparedStatement pdo = MySQL.getConnection().prepareStatement("SELECT user FROM inventory WHERE user = ?");
            pdo.setString(1, uuid);
            ResultSet rs = pdo.executeQuery();
            return rs.next();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return false;
    }


    public static  String getPlayerInventory(String uuid){
        try {
            PreparedStatement pdo = MySQL.getConnection().prepareStatement("SELECT * FROM inventory WHERE user = ?" );
            pdo.setString(1, uuid);
            ResultSet rs = pdo.executeQuery();
            while (rs.next()){
                return rs.getString("inventory");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

}
