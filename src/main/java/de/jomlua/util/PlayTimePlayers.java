package de.jomlua.util;

import de.jomlua.JomluaCore;
import de.jomlua.event.JoinListener;
import de.jomlua.mysql.MySQL;
import de.jomlua.mysql.ReadSQL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;


public class PlayTimePlayers {




    public void getPlayTimer(){
        FileConfiguration p = JomluaCore.getPlugin().getConfig();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(JomluaCore.getPlugin(), new Runnable() {
            @Override
            public void run() {


                for (Player player : Bukkit.getOnlinePlayers()){



                    int minutes = p.getInt(player.getUniqueId() + ".time");


                    long unixTime = Instant.now().getEpochSecond();
                    p.set(player.getUniqueId() + ".Playtime", unixTime);

                    JomluaCore.getPlugin().saveConfig();


                    minutes++;

                }
            }
        }, 20*60, 20*60);
    }

    public static String getLastPlay(String uuid){
        Long timestamp = ReadSQL.getLastPlay(uuid);

        long end = timestamp;

        if (timestamp == null){
            return "Spieler nicht gefunden.";
        }


        if (end == -1){
            return "§4Permanent";
        }

        Date date1 = new Date(timestamp);
        SimpleDateFormat jsf = new SimpleDateFormat("dd MMMM yyyy', 'HH:mm");
        return jsf.format(date1);
    }



    public static void PlayerFirstOnline(String uuid){

//        if (getStorage.isUserExists(uuid)){
//            try {
//
//
//
//                PreparedStatement pdo = MySQL.getConnection().prepareStatement("UPDATE users SET playtime = ? WHERE uuid = ?");
//                pdo.setInt(1, minutes);
//                pdo.setString(2, player.getUniqueId().toString());
//                pdo.executeUpdate();
//            } catch (SQLException e) {
//                e.printStackTrace();
//
//            }
//        }else{
//            try {
//
//                PreparedStatement pdo = MySQL.getConnection().prepareStatement("INSERT INTO users (uuid, username, playtime) VALUES (?,?,?)");
//                pdo.setString(1, player.getUniqueId().toString());
//                pdo.setString(2, player.getDisplayName());
//                pdo.setInt(3, minutes);
//                pdo.executeUpdate();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }
    }
}
