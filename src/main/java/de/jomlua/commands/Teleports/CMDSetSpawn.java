package de.jomlua.commands.Teleports;

import de.jomlua.JomluaCore;
import de.jomlua.util.ChatOutput;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static de.jomlua.JomluaCore.*;


public class CMDSetSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("jomlua.setspawn")){
                if (args.length == 0){
                    FileConfiguration config = JomluaCore.getPlugin().getConfig();
                    config.set("Spawn.World", player.getWorld().getName());
                    config.set("Spawn.X", player.getLocation().getX());
                    config.set("Spawn.Y", player.getLocation().getY());
                    config.set("Spawn.Z", player.getLocation().getZ());
                    config.set("Spawn.Yaw", player.getLocation().getYaw());
                    config.set("Spawn.Pitch", player.getLocation().getPitch());
                    JomluaCore.getPlugin().saveConfig();
                    System.out.println(ChatOutput.PREFIX.getText() + "Es wurde von" + player.getDisplayName() + "ein neuer TP Punkt gesetzt.");
                    player.sendMessage(ChatOutput.PREFIX.getText() + "§aDu hast in §3§l" + player.getWorld().getName() + "§a den Spawn gesetzt.");

                }else{

                }
            }else{
                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            }
        }
        else{
            sender.sendMessage(ChatOutput.PREFIXC.getText() + ChatOutput.NO_PLAYER.getText());
        }
        return false;
    }
}
