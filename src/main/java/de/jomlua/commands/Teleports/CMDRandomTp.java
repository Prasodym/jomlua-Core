package de.jomlua.commands.Teleports;

import com.comphenix.protocol.PacketType;
import de.jomlua.JomluaCore;
import de.jomlua.util.ChatOutput;
import de.jomlua.util.RTeleportUtil;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class CMDRandomTp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        World world = player.getWorld();

        if (sender instanceof Player){

            if (args.length == 0){
                //Safe Location that has been generated
                Location randomLocation = RTeleportUtil.findSafeLocation(player);

                //Prüfe ob in der Netherworld

                if (player.getWorld().getEnvironment() == World.Environment.NETHER ||player.getWorld().getEnvironment() == World.Environment.THE_END){
                    //Befindet sich in der Netherwelt
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.RANDOM_TP.getText());
                } else{
                    //Teleport player
                    player.teleport(randomLocation);

                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.RANDOM_TP_TRUE.getText());

                }
            }else if(args.length == 1){ //Specify a player to teleport
                if (player.hasPermission("jomlua.rtp.others")){
                    //Get the player to teleport
                    Player target = Bukkit.getPlayer(args[0]);

                    //Safe Location that has been generated
                    Location randomLocation = RTeleportUtil.findSafeLocation(target);

                    //Teleport player
                    target.teleport(randomLocation);

                    target.sendMessage(ChatOutput.PREFIX.getText() + ChatColor.RED + player.getDisplayName() + ChatColor.GREEN + " hat dich irgendwo hinteleportiert");


                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatColor.RED + "Player successfully teleported to: " + ChatColor.LIGHT_PURPLE + randomLocation.getX() + " " + randomLocation.getY() + " " + randomLocation.getZ());
                }
            }

        }else {
            System.out.println(ChatOutput.NO_PLAYER.getText());
        }

        if (command.getName().equalsIgnoreCase("setwb")){
            if (args.length == 2){
                String w = player.getWorld().getName();
                String  s = args[0];
                String wa = args[1];
                sender.sendMessage(w);
                setGrenze(w,s,wa, player);


            }
        }

        return true;
    }

    /**
     *
     * @param world
     * @param size
     * @param warn
     * @param player
     */
    private void setGrenze(String world, String size, String warn, Player player){
       File file = new File("plugins/JomluaCore", "config.yml");
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        try {
            cfg.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
            JomluaCore.plugin.getLogger().warning("Die datei" + file + "Konnte nicht geldaden werden.");

        }
        // -------------------------
        cfg.set("wb-world.",world);
        cfg.set("wb-size.", size);
        cfg.set("wb-warn.", warn);

        try {
            cfg.save(file);
            String world1 = cfg.getString("wb-world.");
            Double size1 = cfg.getDouble("wb-size.");
            Integer warn1 = cfg.getInt("wb-warn");

            WorldBorder border = Bukkit.getWorld(world1).getWorldBorder();
            border.setCenter(Bukkit.getWorld(world1).getSpawnLocation());
            border.setSize(size1);
            border.setWarningDistance(warn1);
        } catch (IOException e) {
            e.printStackTrace();
            player.sendMessage("Error!");
        }


    }
}
