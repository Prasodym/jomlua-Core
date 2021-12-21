package de.jomlua.commands;

import de.jomlua.util.ChatOutput;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static de.jomlua.JomluaCore.*;


public class CMDGodmode implements CommandExecutor {
    public static ArrayList<String> god = new ArrayList<String>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("jomlua.god")){
                if (args.length == 0){
                    //Funktion für den Server
                    if (god.contains(player.getName())){
                        god.remove(player.getName());
                        player.sendMessage(ChatOutput.PREFIX.getText() + "§aDu bist nicht mehr im Godmode.");
                    }else{
                        god.add(player.getName());
                        player.sendMessage(ChatOutput.PREFIX.getText() + "§aDu bist nun im Godmode.");

                    }

                }else{
                    if(player.hasPermission("jomlua.god.out")){
                        if (args.length == 1){
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target != null){
                                // funktion für den empänger
                                if (god.contains(target.getName())){
                                    god.remove(target.getName());
                                    target.sendMessage(ChatOutput.PREFIX.getText() + "§aDu bist nicht mehr im Godmode.");
                                    sender.sendMessage(ChatOutput.PREFIX.getText() + "§aDu hast für §c" + target.getName() + " §aGodmode deaktiviert.");
                                }else{
                                    god.add(target.getName());
                                    target.sendMessage(ChatOutput.PREFIX.getText() + "§aDu bist nun im Godmode.");
                                    sender.sendMessage(ChatOutput.PREFIX.getText() + "§aDu hast für §c" + target.getName() + " §aGodmode aktiviert.");
                                }
                            }
                        }
                    }else {
                        // No outher Permissions
                        player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
                    }
                }

            } else{
                // No Permissions
                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            }

        } else{
            //Consolen Befehl
            sender.sendMessage(ChatOutput.PREFIXC.getText() + "§cConsolen befehl Coming soon.");
        }

        return true;
    }


}
