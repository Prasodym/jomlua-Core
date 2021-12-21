package de.jomlua.util;

import de.jomlua.JomluaCore;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;

public class RTeleportUtil {
    //Get an instance of the main class so we can access config
    public static JomluaCore plugin;

    public RTeleportUtil(JomluaCore plugin) {
        this.plugin = plugin;
    }

    //List of all the unsafe blocks
    public static HashSet<Material> bad_blocks = new HashSet<>();

    static{
        bad_blocks.add(Material.LAVA);
        bad_blocks.add(Material.FIRE);
        bad_blocks.add(Material.CACTUS);
    }



    public static Location generateLocation(Player player){
        //Generate Random Location
        Random random = new Random();

        int x = 0;
        int z = 0;
        int y = 0;

        if(plugin.getConfig().getBoolean("world-border")){ //If they want to limit the distance
            x = random.nextInt(plugin.getConfig().getInt("border"));
            z = random.nextInt(plugin.getConfig().getInt("border"));
            y = 150;
        }else if(!plugin.getConfig().getBoolean("world-border")){ //If they dont
            x = random.nextInt(25000); //25000 is default
            z = random.nextInt(25000);
            y = 150;
        }

        Location randomLocation = new Location(player.getWorld(), x, y, z);
        y = randomLocation.getWorld().getHighestBlockYAt(randomLocation);
        randomLocation.setY(y);

        return randomLocation;
    }

    public static Location findSafeLocation(Player player){

        Location randomLocation = generateLocation(player);

        while (!isLocationSafe(randomLocation)){
            //Keep looking for a safe location
            randomLocation = generateLocation(player);
        }
        return randomLocation;
    }

    public static boolean isLocationSafe(Location location){

        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        //Get instances of the blocks around where the player would spawn
        Block block = location.getWorld().getBlockAt(x, y, z);
        Block below = location.getWorld().getBlockAt(x, y - 1, z);
        Block above = location.getWorld().getBlockAt(x, y + 1, z);

        //Check to see if the surroundings are safe or not
        return !(bad_blocks.contains(below.getType())) || (block.getType().isSolid()) || (above.getType().isSolid());
    }

    /**
     * Liest die daten aus der config um sich dahin zu teleportieren
     * @param path Angabe zum fahd der config
     * @param file Variable der File oder File angeben
     * @param player Selection des Players
     */
     public static void ReadLocationConf(String path, File file, Player player){
         FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

         World world = Bukkit.getWorld(cfg.getString(path +".world"));
         double x = cfg.getDouble(path + ".x");
         double y = cfg.getDouble(path + ".y");
         double z = cfg.getDouble(path + ".z");
         float yaw = (float) cfg.getDouble(path + ".yaw");
         float pitch = (float) cfg.getDouble(path + ".pitch");

         Location look = new Location(world, x, y ,z, yaw, pitch);
         player.teleport(look);

    }
    /**
     * Löscht daten aus der configuartion
     * @param path Angabe zum fahd der config
     * @param file Variable der File oder File angeben
     */
    public static void DeleteLocationConf(String path, File file){
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        try {
            cfg.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
            plugin.getLogger().warning("Die datei" + file + "Konnte nicht geldaden werden.");
        }
        // -------------------------
        cfg.set(path , null);
        cfg.set(path + ".world", null);
        cfg.set(path + ".x", null);
        cfg.set(path + ".y", null);
        cfg.set(path + ".z", null);
        cfg.set(path + ".yaw", null);
        cfg.set(path + ".pitch", null);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Liest die daten aus der config um sich dahin zu teleportieren
     * @param path Angabe zum fahd der config
     * @param file Variable der File oder File angeben
     * @param player Selection des Players
     */
    public static void ReadOfflineLocationConf(String path, File file, Player player){
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        World world = Bukkit.getWorld(cfg.getString(path +".world"));
        double x = cfg.getDouble(path + ".x");
        double y = cfg.getDouble(path + ".y");
        double z = cfg.getDouble(path + ".z");
        float yaw = (float) cfg.getDouble(path + ".yaw");
        float pitch = (float) cfg.getDouble(path + ".pitch");

        Location look = new Location(world, x, y ,z, yaw, pitch);
        player.teleport(look);

    }

    /**
     * Liest die daten aus der config um sich dahin zu teleportieren
     * @param path Angabe zum fahd der config
     * @param file Variable der File oder File angeben
     * @param player Selection des Players
     */
    public static Location ReadLocationRespawnConf(String path, File file, Player player) throws IllegalArgumentException{
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        World world = Bukkit.getWorld(cfg.getString(path +".world"));
        double x = cfg.getDouble(path + ".x");
        double y = cfg.getDouble(path + ".y");
        double z = cfg.getDouble(path + ".z");
        float yaw = (float) cfg.getDouble(path + ".yaw");
        float pitch = (float) cfg.getDouble(path + ".pitch");

        return  new Location(world, x, y ,z, yaw, pitch);
    }

}

