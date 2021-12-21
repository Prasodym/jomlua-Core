package de.jomlua.event;



import de.jomlua.util.getSelectPos;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;



public class SelectRegion implements Listener {

    /**
     *
     * @param e EventHandler
     * TODO Lagerfeuer regionfire
     */
    @EventHandler
    public void on(BlockPlaceEvent e){
        Material bl = Material.CAULDRON;
        Player player = e.getPlayer();
        if (e.getBlock().getType() == bl){
            Block block = e.getBlock();
            Location position = block.getLocation();
            //player.sendMessage("True");
            //player.sendMessage(String.valueOf(position));

        } else{
            //player.sendMessage("False");
        }
    }
}
