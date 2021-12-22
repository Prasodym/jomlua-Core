package de.jomlua.event;

import de.jomlua.JomluaCore;
import de.jomlua.mysql.MySQL;
import de.jomlua.mysql.ReadSQL;
import de.jomlua.util.ChatOutput;

import de.jomlua.util.ChatUtils;
import de.jomlua.util.Inventory.SynInventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;

import static de.jomlua.JomluaCore.chat;


public class JoinListener implements Listener {

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event){

        Player player = event.getPlayer();

        File file = new File("plugins/JomluaCore/Userdata", player.getUniqueId() + ".yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);


        HashMap<String, String> replaye = new HashMap<>();
        replaye.put("%player%",player.getDisplayName());
        event.setJoinMessage(ChatOutput.JOIN_MSG.getText(replaye));

        if (JomluaCore.plugin.getConfig().getBoolean("motd")){
            for (int i = 0; i < JomluaCore.plugin.getConfig().getList("motd-msg").size(); i++){
                String msg = JomluaCore.plugin.getConfig().getList("motd-msg").get(i).toString();
                String a = msg.replace("%ONLINE%", ChatUtils.getOnlinePlayer().toString());
                a =  a.replace("%PLAYER%", player.getName());
                ChatUtils.sendMessageAtHex(player,a);

            }
        }


        World world = player.getWorld();
        String group = chat.getPrimaryGroup(player);
        String prefix = chat.getGroupPrefix(world,group);


        player.setPlayerListHeader(ChatOutput.TAB_HEADER_MSG.getText());
        player.setPlayerListName(ChatUtils.setHexColor(prefix + player.getName()));
        player.setPlayerListFooter(ChatColor.translateAlternateColorCodes('&',"&AHallo " + player.getName() ));

        String ip = player.getAddress().toString();
        Bukkit.getLogger().log(Level.INFO, "Player ip: " + ip + " From player " + player.getName());

//        try {
//            SynInventory.getInvetory(player);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }





        if (!(ReadSQL.isUserExists(player.getUniqueId().toString()))) {
            Date date = new Date();
            long time = date.getTime();
            Timestamp test = new Timestamp(time);
            long after = test.getTime();

            long money = JomluaCore.plugin.getConfig().getLong("start-money");

            try {

                PreparedStatement pdo = MySQL.getConnection().prepareStatement("INSERT INTO users (uuid, username,money, adress) VALUES (?,?,?,?)");
                pdo.setString(1, player.getUniqueId().toString());
                pdo.setString(2, player.getName());
                pdo.setLong(3,money);
                pdo.setString(4, player.getAddress().toString());
                pdo.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            //Bukkit.getLogger().log(Level.WARNING, "Is exist!");
        }

    }

    @EventHandler
    public void onProxyPing(ServerListPingEvent ebent) {


        // Create a new array with the line count
        for (int i = 0; i < JomluaCore.plugin.getConfig().getList("server-motd").size(); i++){
            String msg = JomluaCore.plugin.getConfig().getList("server-motd").get(i).toString();
            String a = msg.replace("()", "\n");


            ebent.setMotd(ChatUtils.setHexColor(a));
        }

    }







}
