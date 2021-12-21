package de.jomlua.event;

import de.jomlua.JomluaCore;
import de.jomlua.mysql.SaveSQL;
import de.jomlua.util.RTeleportUtil;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;


public class DeathListener implements Listener{

    public static HashMap<Player, Location> back = new HashMap<Player, Location>();
    @EventHandler
    //TODO Todes counter einbauen
    public void onDeath(PlayerDeathEvent e) throws SQLException {
        Player player = e.getEntity();


        JomluaCore.back.put(player.getName(), player.getLocation());

        SaveSQL.saveDeaths(player.getUniqueId().toString());

    }

    @EventHandler
    public void onRelive(PlayerRespawnEvent e){

        Player player = e.getPlayer();
        File file = new File("plugins/JomluaCore/Userdata", player.getUniqueId() + ".yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        Location loc = RTeleportUtil.ReadLocationRespawnConf("homes.home",file, player);

        e.setRespawnLocation(loc);
        player.setFlySpeed((float) 0.1);
        player.setWalkSpeed((float) 0.2);
        player.setAllowFlight(false);
        player.setGameMode(GameMode.SURVIVAL);
        player.playSound(loc, Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1f,2f);
    }

    @EventHandler
    public void onTeleportation(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        JomluaCore.back.put(player.getName(), player.getLocation());
    }

    @EventHandler
    public void ShulkerDrop(EntityDeathEvent event){

        Player player = event.getEntity().getKiller();
        if (JomluaCore.plugin.getConfig().getBoolean("shulker-drop-enable")){
            if (event.getEntity().getKiller() != null && event.getEntity().getType() == EntityType.SHULKER) {

                event.getDrops().clear();
                if (!(player.getInventory().getItemInMainHand().getItemMeta().addEnchant(Enchantment.LOOT_BONUS_MOBS,3,false))){
                    int random = randInt(1,JomluaCore.plugin.getConfig().getInt("shulker-drop-rate"));
                    player.sendMessage("§8Random: §7" + random);
                    event.getDrops().add(new ItemStack(Material.SHULKER_SHELL, random));

                }else{
                    player.sendMessage("§8Aus der bedingung gefallen");
                    event.getDrops().add(new ItemStack(Material.SHULKER_SHELL, 1));
                }

            }
        }
    }
    private static int randInt(int min, int max) {

        int zahl = (int) (min + (Math.random() * (max - min)));


        return zahl;
    }
}
