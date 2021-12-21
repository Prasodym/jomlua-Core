package de.jomlua.commands.Teleports;

import de.jomlua.JomluaCore;

import de.jomlua.util.ChatOutput;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
// Import Java IDA
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static de.jomlua.JomluaCore.*;

public class CMDSetHome implements CommandExecutor {

    private JomluaCore plugin;


    public CMDSetHome(JomluaCore info){
        this.plugin = info;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        File file = new File("plugins/JomluaCore/Userdata", player.getUniqueId() + ".yml");

        int number = JomluaCore.plugin.getConfig().getInt("homepoints");

        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (sender instanceof Player) {
            if (player.hasPermission("jomlua.sethome")) {
                String world;
                double x;
                double y;
                double z;
                double yaw;
                double pitch;
                if (args.length == 0) {
                    try {
                        cfg.load(file);
                    } catch (IOException var24) {
                        var24.printStackTrace();
                    } catch (InvalidConfigurationException var25) {
                        var25.printStackTrace();
                    }

                    world = player.getWorld().getName();
                    x = player.getLocation().getX();
                    y = player.getLocation().getY();
                    z = player.getLocation().getZ();
                    yaw = (double)player.getLocation().getYaw();
                    pitch = (double)player.getLocation().getPitch();
                    cfg.set("homes.home.world", world);
                    cfg.set("homes.home.x", x);
                    cfg.set("homes.home.y", y);
                    cfg.set("homes.home.z", z);
                    cfg.set("homes.home.yaw", yaw);
                    cfg.set("homes.home.pitch", pitch);

                    try {
                        cfg.save(file);
                    } catch (IOException var23) {
                        var23.printStackTrace();
                    }

                    player.sendMessage("§7[§3Jo§amlua§7] §aDu hast dein Homepunkt gesetzt.");

                } else if (args.length == 1) {



                    int homes = getHome(player).size();

                    if (homes == number && !player.hasPermission("jomlua.sethome.multipe")) {
                        player.sendMessage(ChatOutput.PREFIX.getText() + "§cDu hast bereits §3" + number + " §cHomes, lösche einen Home um ein neuen zu setzen.");
                        player.sendMessage("§aGebe §e/§bhomes §a ein um deine Homes zu sehen.");
                        player.sendMessage("§aUm ein Home zu löschen gebe §e/§bdelhome §3[name]§a ein deine Homes zu löschen.");


                        return true;
                    }

                    try {
                        cfg.load(file);
                    } catch (IOException var21) {
                        var21.printStackTrace();
                    } catch (InvalidConfigurationException var22) {
                        var22.printStackTrace();
                    }

                    world = player.getWorld().getName();
                    x = player.getLocation().getX();
                    y = player.getLocation().getY();
                    z = player.getLocation().getZ();
                    yaw = (double)player.getLocation().getYaw();
                    pitch = (double)player.getLocation().getPitch();
                    cfg.set("homes." + args[0] + ".world", world);
                    cfg.set("homes." + args[0] + ".x", x);
                    cfg.set("homes." + args[0] + ".y", y);
                    cfg.set("homes." + args[0] + ".z", z);
                    cfg.set("homes." + args[0] + ".yaw", yaw);
                    cfg.set("homes." + args[0] + ".pitch", pitch);

                    try {
                        cfg.save(file);
                    } catch (IOException var20) {
                        var20.printStackTrace();
                    }

                    player.sendMessage("§7[§3Jo§amlua§7] §aDein Home §c" + args[0] + " §awurde gesetzt.");
                }
            } else {
                player.sendMessage("§7[§3Jo§amlua§7] §cDu hast dafür keine berechtigung.");
            }
        } else {
            sender.sendMessage("§3[§3Jomlua§7] §4Du kannst diesen befehl nur Ingame aus führen.");
        }

        return true;
    }
    public static Set<String> getHome(Player player){
        File file3 = new File("plugins/JomluaCore/Userdata", player.getUniqueId() + ".yml");
        if (file3.exists()){
            YamlConfiguration cfg3 = YamlConfiguration.loadConfiguration(file3);
            return cfg3.getConfigurationSection("homes.").getKeys(false);
        }else{
            return new HashSet<String>();
        }
    }
}
