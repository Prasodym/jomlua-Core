package de.jomlua.event;

import de.jomlua.commands.CMDGodmode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onPlayerMove(EntityDamageByEntityEvent e){


        if(e.getEntity() instanceof Player){
            Player player = (Player)e.getEntity();
            if (CMDGodmode.god.contains(player.getName())){
                e.setCancelled(true);
            }else{
                e.setCancelled(false);
            }
        }
    }
}
