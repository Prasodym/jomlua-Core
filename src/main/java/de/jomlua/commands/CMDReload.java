package de.jomlua.commands;

import de.jomlua.util.ChatOutput;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static de.jomlua.JomluaCore.*;

public class CMDReload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("jomlua.plugin.load")){
                if (args.length == 0){
                    player.sendMessage(ChatOutput.PREFIX.getText() + "§eNeu laden ...");
                    Bukkit.reload();
                    player.sendMessage(ChatOutput.PREFIX.getText() + "§aReload komplett!");
                    return true;
                }else {
                    player.sendMessage(ChatOutput.PREFIX.getText() + "§cEtwas ist schief gelaufen.");
                }
            } else{
                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            }
        }else{
            sender.sendMessage(ChatOutput.PREFIXC.getText() + "§eNeu laden ...");
            Bukkit.reload();
            sender.sendMessage(ChatOutput.PREFIXC.getText() + "§aReload komplett!");
            return true;
        }
        return true;
    }
}
