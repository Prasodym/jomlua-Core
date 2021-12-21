package de.jomlua.event;

import de.jomlua.JomluaCore;
import de.jomlua.mysql.MySQL;
import de.jomlua.util.ChatOutput;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class BreakBlockListener implements Listener {

    int i = 1;
    /**
     * @param  event Block id angeben
     */
    @EventHandler (priority = EventPriority.NORMAL)
    public void destroyBlock(BlockBreakEvent event) throws IOException {


        Block block = event.getBlock();
        Material material = block.getType();
        Player player = event.getPlayer();
        String ItemName = material.name().toString().toLowerCase();

        File file = new File(JomluaCore.plugin.getDataFolder() , "cache.yml");

        YamlConfiguration configration = YamlConfiguration.loadConfiguration(file);

        configration.set(player.getName() + "." + ItemName, i);
        configration.save(file);

        player.sendMessage("false");
        if (configration.isSet(player.getName() + "." + ItemName)){
            configration.set(player.getName() + "." + ItemName, 1);
            player.sendMessage("true");

        }else{
            player.sendMessage("false");


        }


        player.sendMessage(ChatOutput.PREFIX.getText() + "§2" + player.getDisplayName() + ": §abaut gerade §e" + ItemName + " §aab. §fAnzahl: §c" + i);

        i ++;

    }
    private void BlockCount(String material, int anzahl, Player player) throws IOException {
        File file = new File(JomluaCore.plugin.getDataFolder() , "cache.yml");

        YamlConfiguration configration = YamlConfiguration.loadConfiguration(file);

        configration.set(player.getName() + "." + material, anzahl);
        configration.save(file);

        player.sendMessage("false");
        if (configration.isSet(player.getName() + "." + material)){

            player.sendMessage("true");
        }else{
            player.sendMessage("false");
            configration.set(player.getName() + "." + material, 1);
        }
    }
}
