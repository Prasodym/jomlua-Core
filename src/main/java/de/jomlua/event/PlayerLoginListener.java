package de.jomlua.event;

import de.jomlua.JomluaCore;
import de.jomlua.commands.Secure.BannUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerLoginEvent;

import java.sql.SQLException;

public class PlayerLoginListener implements Listener {
   //private Chat chat;

    @EventHandler
    public void PlayerLogin(PlayerLoginEvent e) throws SQLException {

        Player player = e.getPlayer();
        JomluaCore.playerLogTimeMap.put(player, System.currentTimeMillis());
        JomluaCore.GivePlayer.put(player.getUniqueId().toString(), player.getName());


        if (BannUtils.isBanned(player.getUniqueId().toString())){
            long currentTime = System.currentTimeMillis();
            long end = BannUtils.getEnd(player.getUniqueId().toString());
            if (currentTime < end | end == -1){
                System.out.println(player.getUniqueId().toString());
                   if (!player.isOp()){
                       e.disallow(PlayerLoginEvent.Result.KICK_BANNED,
                               "§cDu bist noch Vom Server Gebannt!\n" +
                                       "\n" + "§3Grund: §e" + BannUtils.getReason(player.getUniqueId().toString()) + "\n" +
                                       "\n" + "§3Bis zum: §e" + BannUtils.toEnd(player.getUniqueId().toString()) +
                                       "\n" + "\n" + "§cEin Entbannungsantrag ist zu ausgeschlossen!");
                   }



            }
        }else{
            return;
        }
    }



}
