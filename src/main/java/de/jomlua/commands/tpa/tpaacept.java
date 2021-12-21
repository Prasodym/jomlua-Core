package de.jomlua.commands.tpa;

import de.jomlua.JomluaCore;
import de.jomlua.util.ChatOutput;
import de.jomlua.util.TeleportTyp;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class tpaacept  implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Player target = Bukkit.getPlayer(JomluaCore.tpa.get(player.getUniqueId()));


        if (args.length==0){
            if (!(sender instanceof Player)){
                sender.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PLAYER.getText());
                return true;
            }
            if (!(player.hasPermission("jomlua.tpa"))){
                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
                return true;
            }
            if (!(JomluaCore.tpa.containsKey(player.getUniqueId()))){
                // Keine anfrage msg
                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.TPA_DENY_D.getText());
                return true;
            }
            if (target == null){
                //msg Spieler bereits offline
                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.TPA_DENY_H.getText());
                JomluaCore.tpa.remove(player.getUniqueId());
                JomluaCore.tpType.remove(player.getUniqueId());
            }

            TeleportTyp tpType = JomluaCore.tpType.get(player.getUniqueId());
            if (tpType == TeleportTyp.NORMAL){
                target.teleport(player);
            }else{
                player.teleport(target);
            }
            JomluaCore.tpa.remove(player.getUniqueId());
            JomluaCore.tpType.remove(player.getUniqueId());

        }else{
            player.sendMessage("§a- §e/tpaccept");
        }
        return false;
    }
}
