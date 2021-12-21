package de.jomlua.commands;

import de.jomlua.util.ChatOutput;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CMDPlugins implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 0){
                player.sendMessage(ChatOutput.PREFIX.getText() + "§cDas bleibt immer ein geheimniss.");
            }else{
                // Failed command
                player.sendMessage(ChatOutput.PREFIX.getText() + "§cDas bleibt immer ein geheimniss.");
            }
        }else {
            //Console Aktion
            sender.sendMessage(ChatOutput.PREFIX.getText() + "§cDas bleibt immer ein geheimniss.");
        }
        return false;
    }
}
