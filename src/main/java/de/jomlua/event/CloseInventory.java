package de.jomlua.event;

import de.jomlua.JomluaCore;
import de.jomlua.commands.CMDInvsee;
import de.jomlua.mysql.ReadSQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.logging.Level;

public class CloseInventory implements Listener {

    @EventHandler
    public  void onInventoryClick(InventoryClickEvent event){

        Player player = (Player) event.getWhoClicked();
        String user = JomluaCore.checkInv.get(player.getName());
        if (event.getCurrentItem() == null){
            return;
        }
        if (event.getView().getTitle().equalsIgnoreCase("§6Infos von " + user)){

            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onCloseInventory(InventoryCloseEvent e){
        Player player = (Player) e.getPlayer();
        if (e.getView().getTitle().equalsIgnoreCase("§6Infos von ")){
            JomluaCore.checkInv.remove(player.getName());
            e.getView().close();

        }
    }

/*
TODO CreateInventore invsee closes
 */
//    @EventHandler
//    public void closeInv(InventoryCloseEvent e){
//
//        Player player = (Player) e.getPlayer();
//
//
//        Player target = CMDInvsee.invtarget.get(player);
//
//
//        target.getInventory().setContents(CMDInvsee.invplayeredit.get(e.getPlayer()));
//
//        player.sendMessage("true");
//    }

}