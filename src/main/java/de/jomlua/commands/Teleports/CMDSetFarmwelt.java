package de.jomlua.commands.Teleports;

import de.jomlua.JomluaCore;
import de.jomlua.util.ChatOutput;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static de.jomlua.JomluaCore.*;

public class CMDSetFarmwelt implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("jomlua.farmwelt.set")){
                if (args.length == 0){
                    FileConfiguration config = JomluaCore.getPlugin().getConfig();
                    config.set("Farmwelt.World", player.getWorld().getName());
                    config.set("Farmwelt.X", player.getLocation().getX());
                    config.set("Farmwelt.Y", player.getLocation().getY());
                    config.set("Farmwelt.Z", player.getLocation().getZ());
                    config.set("Farmwelt.Sicht.Yaw", player.getLocation().getYaw());
                    config.set("Farmwelt.Sicht.Pitch", player.getLocation().getPitch());
                    JomluaCore.getPlugin().saveConfig();
                    System.out.println(ChatOutput.PREFIXC.getText() + "Es wurde von " + player.getDisplayName() + " ein neuer TP Punkt gesetzt.");
                    player.sendMessage(ChatOutput.PREFIX.getText() + "§aDu hast in §3§l" + player.getWorld().getName() + "§a den Spawn gesetzt.");

                }else{
                    player.sendMessage(ChatOutput.PREFIX.getText() + "§6Bitte benutze /setfarmwelt");
                }
            }else{
                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            }
        }else {
            sender.sendMessage(ChatOutput.PREFIXC.getText() + ChatOutput.NO_PLAYER.getText());
        }
        return false;
    }
}
