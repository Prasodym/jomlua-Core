package de.jomlua.commands;

import de.jomlua.JomluaCore;
import de.jomlua.util.ChatOutput;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static de.jomlua.JomluaCore.*;

public class CMDBack implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("jomlua.back")){
                if (args.length == 0){
                    if (JomluaCore.back.containsKey(player.getName())){
                        player.teleport(JomluaCore.back.get(player.getName()));
                        JomluaCore.back.remove(player.getName());
                        player.sendMessage(ChatOutput.PREFIX.getText() + "Wooop..");
                    } else {
                        player.sendMessage(ChatOutput.PREFIX.getText() + "§cEs gibt kein Ort für dich.");
                    }

                }else{
                    sender.sendMessage(ChatOutput.PREFIX.getText() + "§eComing soon..");
                }
            }else{
                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            }

        }else{
            sender.sendMessage(ChatOutput.PREFIXC.getText() + ChatOutput.NO_PLAYER.getText());
        }

        return false;
    }

}
