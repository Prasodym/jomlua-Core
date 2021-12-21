package de.jomlua.commands;

import de.jomlua.JomluaCore;
import de.jomlua.mysql.MySQL;
import de.jomlua.mysql.MySQLConfig;
import de.jomlua.util.ChatOutput;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDPluginReload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (sender instanceof Player){
            if (player.hasPermission("jomlua.reload.plugin")){
                if (args.length == 0){


                    JomluaCore.plugin.getPluginLoader().disablePlugin(JomluaCore.plugin);
                    player.sendMessage(ChatOutput.PREFIX.getText() + "§7[§e§lDebug§7] Das Plugin wurde jetzt heruntergefahren.");

                    MySQL.disconnect();
                    JomluaCore.plugin.reloadConfig();
                    JomluaCore.plugin.getPluginLoader().enablePlugin(JomluaCore.plugin);
                    player.sendMessage(ChatOutput.PREFIX.getText() + "§7[§e§lDebug§7] Das Plugin wurde jetzt Aktiviert.");
                    MySQL.connect();
                }
            }else{
                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            }
        } else{
            //if console is available
        }
        return false;
    }
}
