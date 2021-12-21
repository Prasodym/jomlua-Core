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



public class CMDFarmwelt implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("jomlua.farmwelt.tp")){
                if (args.length == 0){
                    FileConfiguration config = JomluaCore.getPlugin().getConfig();
                    World world = Bukkit.getWorld(config.getString("Farmwelt.World"));
                    double x = config.getDouble("Farmwelt.X");
                    double y = config.getDouble("Farmwelt.Y");
                    double z = config.getDouble("Farmwelt.Z");
                    float yaw = (float) config.getDouble("Farmwelt.Yaw");
                    float pitch = (float) config.getDouble("Farmwelt.Pitch");
                    Location location = new Location(world, x, y, z, yaw, pitch);
                    if (config.isSet("Formwelt")){
                        player.sendMessage(ChatOutput.PREFIX.getText() + "§3§lError: §cEs wurde noch keins gesetzt.");
                    } else{
                        player.teleport(location);
                        player.sendTitle("§a§lAchtung..","§cDu bist nun in der Farmwelt.",20,10,30);
                    }


                }else{
                    sender.sendMessage(ChatOutput.PREFIX.getText() + "§4Gebe hierfür /farmwelt ein.");
                }
            }else{
                sender.sendMessage(ChatOutput.PREFIX.getText() + "§cDu hast dafür keine rechte.");
            }

        }else{
            sender.sendMessage(ChatOutput.PREFIX.getText() + "§4Kann nur Ingame genutzt werden.");
        }
        return false;
    }
}
