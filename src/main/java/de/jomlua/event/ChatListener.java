package de.jomlua.event;

import de.jomlua.util.ChatUtils;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


import java.util.IllegalFormatConversionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static de.jomlua.JomluaCore.*;


public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        World world = player.getWorld();
        String group = chat.getPrimaryGroup(player);
        String prefix = chat.getGroupPrefix(world,group);
        String x = player.getWorld().getName();

            if (player.hasPermission("jomlua.color")){
                e.setFormat(ChatUtils.setHexColor("&7[&3" + x + "&7]" + prefix + player.getDisplayName()+":&a ") + ChatUtils.setHexColor(e.getMessage()));
            } else {
                if (player.isOp()){
                    e.setFormat(ChatUtils.setHexColor("&7[&3" + x + "&7]" + prefix + player.getDisplayName()+":&a ") + e.getMessage());
                }else{
                    e.setFormat(ChatUtils.setHexColor("&7[&4Operator&7]&7[&3" + x + "&7]" + prefix + player.getDisplayName()+":&a ") + e.getMessage());
                }
            }

    }

    /**
     *
     * @param msg Output put
     * @return Rgb Chat Message
     * RGB Anwendung ab 1.16
     * @deprecated Neues über de.jomlua.ChatUtils methode setHexColor(String msg)

     */
    @Deprecated
    public String format(String msg){
        Pattern pattern = Pattern.compile("#[a-fa-f0-9]{6}");

        Matcher match = pattern.matcher(msg);
        while (match.find()){
            String color = msg.substring(match.start(), match.end());
            msg = msg.replace(color, net.md_5.bungee.api.ChatColor.of(color) + "");
            match = pattern.matcher(msg);
        }
        return ChatColor.translateAlternateColorCodes('&',msg);
    }

}
