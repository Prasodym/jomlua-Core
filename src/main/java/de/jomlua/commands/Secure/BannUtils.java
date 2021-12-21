package de.jomlua.commands.Secure;

import com.sun.org.apache.bcel.internal.generic.LDIV;
import de.jomlua.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BannUtils {

    /**
     *
     * @param uuid Spieler uniqueUUUID
     * @param player Spieler getName
     * @param reason Grund des Banns
     * @param seconds Dauer des Banns
     *                Banne einen Spieler Vom Server (SQL)
     */
    public static void ban(String law,String player, String uuid, String reason, int seconds){
        long end = 0;
        if (seconds == -1){
            end = -1;
        }else{
//            long current = System.currentTimeMillis();
//            long milliseconds = seconds*1000;
//            end = current+milliseconds;
            Date date = new Date();
            long time = date.getTime();
             Timestamp test = new Timestamp(time);
            long after = test.getTime();

            long below = seconds*1000;

             end = below+after;
        }


            //MySQL.update("INSERT INTO banned (user, uuid, end, reason) VALUES ('"+player+"', '"+uuid+"', '"+end+"', '"+reason+"')");
        System.out.println("1: " + player + ", 2:" + uuid + ", 3: " + reason + ", 4: " + end);
        try
        {
            PreparedStatement pdo = MySQL.getConnection().prepareStatement("INSERT INTO banned (user, uuid, end, reason, law) VALUES (?,?,?,?,?)");
        pdo.setString(1, player);
        pdo.setString(2, uuid);
        pdo.setString(4, reason);
        pdo.setLong(3, end);
        pdo.setString(5, law);
        pdo.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
            System.out.println("1: " + player + ", 2:" + uuid + ", 3: " + reason + ", 4: " + end);
    }


        if (Bukkit.getPlayer(player) != null){
            Bukkit.getPlayer(player).kickPlayer("§cDer Bannhammer hat gesprochen!\n" + "\n" + "§3Grund: §e" + getReason(uuid) + "\n" + "\n" + "§3Bis zum: §e" + toEnd(uuid) + "\n" + "\n" + "§cEin Entbannungsantrag ist zu ausgeschlossen!");
        }


    }
    /**
    * @param uuid Spieler uniqueUUUID
    * Lösche einen banneintrag von spieler der UUID zugehört
     **/
    public static void unban(String uuid){
//        MySQL.update("DELETE * FROM banned WEHERE uuid='"+uuid+"'");

        try {
            PreparedStatement pdo = MySQL.getConnection().prepareStatement("DELETE FROM banned WHERE uuid = ?" );
            pdo.setString(1, uuid);
            pdo.setQueryTimeout(28000);
            pdo.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prüfe ob der Spieler gebannt ist.
     * @param uuid
     * @return true = Bann / false = not bann
     */
    public static boolean isBanned(String uuid) throws SQLException {
        PreparedStatement pdo = MySQL.getConnection().prepareStatement("SELECT end FROM banned WHERE uuid = ?");
        try {
            pdo.setString(1, uuid);
            pdo.setQueryTimeout(28000);
            ResultSet rs = pdo.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * Hole den Banngrund vom Spieler
     * @param uuid Spieler uniqueUUUID
     * @return String text
     */
    public static String getReason(String uuid){

        try {
            PreparedStatement pdo = MySQL.getConnection().prepareStatement("SELECT * FROM banned WHERE uuid = ?" );
            pdo.setString(1, uuid);
            ResultSet rs = pdo.executeQuery();
            while (rs.next()){
                return rs.getString("reason");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";

    }

    public static String getBanFrom(String uuid){

        try {
            PreparedStatement pdo = MySQL.getConnection().prepareStatement("SELECT * FROM banned WHERE uuid = ?" );
            pdo.setString(1, uuid);
            ResultSet rs = pdo.executeQuery();
            while (rs.next()){
                return rs.getString("law");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";

    }


    /**
     * Hole dir die dauer des gebannten Spielers
     * @param uuid
     * @return Zeit
     */
    public static Long getEnd(String uuid){
        try {
            PreparedStatement pdo = MySQL.getConnection().prepareStatement("SELECT * FROM banned WHERE uuid = ?" );
            pdo.setString(1, uuid);
            ResultSet rs = pdo.executeQuery();
            while (rs.next()){
                return rs.getLong("end");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * Prüfe ob der Spieler gebannt ist.
     * @return Boolean
     */
    public static List<String> getBannedPlayers(){
        List<String> list = new ArrayList<>();

        try {
            PreparedStatement pdo = MySQL.getConnection().prepareStatement("SELECT * FROM banned" );
            ResultSet rs = pdo.executeQuery();
            while (rs.next()){
                //list.add(rs.getString("user"));

                list.add(rs.getString("user"));
                list.stream();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public static String getBannedDauer(String uuid){
        long current = System.currentTimeMillis();
        long end = getEnd(uuid);
        long diff = end - current;

        int sec = 0;
        int min = 0;
        int huor = 0;
        int day = 0;
        int week = 0;

        while (diff > 1000){
            diff -=1000;
            sec++;
        }
        while (sec > 60){
            sec-=60;
            min++;
        }
        while (min > 60){
            min-=60;
            huor++;
        }
        while (huor > 24){
            huor-=24;
            day++;
        }
        while (day > 7){
            day-=7;
            week++;
        }

        return "§e" + week + " §bWoche(n) §e" + day + " §bTage(n) §e" + huor + " §bStunde(n) §e" + min + " §bMinute(n) §e" + sec + " §bSekunde(n)";
    }

    public static String getReamingTime(String uid) {
        long current = System.currentTimeMillis() / 1000;
        long end = getEnd(uid) / 1000;
        if (end == -0.001) {
            return "§cPERMANENT";
        }
        long s1 = end - current;
        long m1 = s1 / 60;
        long h1 = m1 / 60;
        long d1 = h1 / 24;
        long w1 = d1 / 7;
        long y = w1 / 52;

        long s = s1 - (m1 * 60);;
        long m = m1 - (h1 * 60);;
        long h = h1 - (d1 * 24);
        long d = d1 - (w1 * 7);
        long w = w1 - (y * 52);

        return "§e" + y + " Jahr(e) " + w + " Woche(n) " + d + " Tag(e) " + h + " Stunde(n) " + m + " Minute(n) " + s
                + " Secunde(n)";

    }

    public static String toEnd(String uuid){

        long end = getEnd(uuid);
        if (end == -1){
            return "§4Permanent";
        }

        Date date1 = new Date(end);
        SimpleDateFormat jsf = new SimpleDateFormat("EEEE', 'dd. MMMM yyyy HH:mm");
        return jsf.format(date1);
    }
    public static String toEndInGame(String uuid){

        long end = getEnd(uuid);
        if (end == -1){
            return "§4Permanent";
        }

        Date date1 = new Date(end);
        SimpleDateFormat jsf = new SimpleDateFormat("dd MMMM yyyy', 'HH:mm");
        return jsf.format(date1);
    }
    public static String isPermanent(String uuid){
        String perm = "";
        if (getEnd(uuid) == -1){
            perm = "§4Permanent";
        }else{
            perm = "";
        }
        return perm;
    }
    public static String isGlobalBan(String uuid){

        return null;
    }

}
