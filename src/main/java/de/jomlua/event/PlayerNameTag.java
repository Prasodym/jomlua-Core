package de.jomlua.event;



import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;


import java.nio.charset.StandardCharsets;

import static de.jomlua.JomluaCore.chat;
import static de.jomlua.JomluaCore.score;



/**
 * TODO: Add Spiel kompatiblität für 1.12.2
 * Note: This Deaktiviert
 */
public class PlayerNameTag implements Listener {




    Team team = null;
    ChatListener hex = null;
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = (Player)e.getPlayer();
        World world = player.getWorld();
        String group = chat.getPrimaryGroup(player);
        String prefix = chat.getGroupPrefix(world,group);



            if (score.getTeam(player.getName()) == null){
                team = score.registerNewTeam(player.getName());
            }else{
                team = score.getTeam(player.getName());
            }

            switch (group){
                case "staff":
                    team.setColor(ChatColor.GOLD);
                    team.addPlayer(player);
                    break;
                case "spieler":
                    //team.setDisplayName(player.getDisplayName());
                    team.addPlayer(player);
                    team.setColor(ChatColor.GREEN);
                    break;
                case "bazinga":
                    team.addPlayer(player);
                    team.setColor(ChatColor.DARK_PURPLE);
                    break;
                case "admin":
                    team.addPlayer(player);
                    team.setColor(ChatColor.DARK_RED);
                    break;
                case "mod":
                    team.addPlayer(player);
                    team.setColor(ChatColor.DARK_PURPLE);
                    break;
                case "dev":
                    team.addPlayer(player);
                    team.setColor(ChatColor.AQUA);
                    break;
                case "sponsor":
                    team.addPlayer(player);
                    team.setColor(ChatColor.LIGHT_PURPLE);
                    break;
                case "vip":
                    team.addPlayer(player);
                    team.setColor(ChatColor.YELLOW);
                    break;

                default:
                    team.setColor(ChatColor.GRAY);
                    //team.setDisplayName(player.getDisplayName());
                    team.addPlayer(player);
            }



    }

}
