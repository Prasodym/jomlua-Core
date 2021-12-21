package de.jomlua.commands;

import de.jomlua.util.ChatOutput;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.loot.Lootable;

import java.util.HashMap;

public class CMDTeamSpeak implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;


        if(sender instanceof Player) {


            if (command.getName().equalsIgnoreCase("teamspeak") || command.getName().equalsIgnoreCase("ts")){
                String[] s = ChatOutput.TEAMSPEAK.getText().split("%n");
                for (String m : s){
                    
                    player.sendMessage(ChatOutput.PREFIX.getText() + m);
                }

            }

        }else {
            sender.sendMessage(ChatOutput.PREFIXC.getText() + ChatOutput.NO_PLAYER.getText());
        }

        return false;
    }
}
