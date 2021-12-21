package de.jomlua.commands.Teleports;

import de.jomlua.JomluaCore;
import de.jomlua.util.ChatOutput;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;



public class CMDSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("jomlua.spawn")){
                if (args.length == 0){
                    FileConfiguration config = JomluaCore.getPlugin().getConfig();
                    World world = Bukkit.getWorld(config.getString("Spawn.World"));
                    double x = config.getDouble("Spawn.X");
                    double y = config.getDouble("Spawn.Y");
                    double z = config.getDouble("Spawn.Z");
                    float yaw = (float) config.getDouble("Spawn.Yaw");
                    float pitch = (float) config.getDouble("Spawn.Pitch");
                    Location location = new Location(world, x, y, z, yaw, pitch);
                    player.teleport(location);
                }else{
                    sender.sendMessage(ChatOutput.PREFIX.getText() + "§4Gebe hierfür /spawn ein.");
                }
            }else{
                sender.sendMessage(ChatOutput.PREFIX.getText() + "§cDu hast dafür keine rechte.");
            }

        }else{
            sender.sendMessage(ChatOutput.PREFIXC.getText() + "§4Kann nur Ingame genutzt werden.");
        }
        return false;
    }

}
