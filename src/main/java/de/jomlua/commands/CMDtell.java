package de.jomlua.commands;

import de.jomlua.JomluaCore;
import de.jomlua.event.ChatListener;
import de.jomlua.util.ChatOutput;
import de.jomlua.util.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import static de.jomlua.JomluaCore.chat;

public class CMDtell implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (sender instanceof Player){
            if (command.getName().equalsIgnoreCase("m")){
                World world = player.getWorld();
                String group = chat.getPrimaryGroup(player);
                String prefix = chat.getGroupPrefix(world,group);
                if (args.length == 0){
                    player.sendMessage(ChatOutput.PREFIX.getText() + "Du musst ein spieler wählen.");
                }else{
                    if (args.length == 2){
                        Player target = Bukkit.getPlayer(args[0]);

                        ChatUtils.setReply(player,target);
                        args[0] = "";
                        String msg = "";
                        for (int i = 0; i < args.length; i++){
                            msg += " " + args[i];
                        }

                        target.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a" + prefix + player.getName() + "&7 -> &cDu:&b" + msg));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cDu &7-> &a" + prefix + target.getName() + "&8:&b" + msg));
                    }
                }
            }
            if (command.getName().equalsIgnoreCase("r")){
                World world = player.getWorld();
                String group = chat.getPrimaryGroup(player);
                String prefix = chat.getGroupPrefix(world,group);
                if (args.length == 0){
                    player.sendMessage("§e Du hast keine nachricht erhalten.");
                }else{
                    if (ChatUtils.getReply(player) == null){
                        player.sendMessage("Hast keine msg");
                        return true;
                    }
                    if (args.length == 1){
                        Player reciver = ChatUtils.getReply(player);
                        String mass = "";
                        for (int i = 0; i < args.length; i++){
                            mass += " " + args[i];
                        }

                        reciver.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a" + prefix + player.getName() + "&7 -> &cdu:&b" + mass));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cdu &7-> &a" + prefix + reciver.getName() + "&8:&b" + mass));
                    }
                }
            }
        }else{
            sender.sendMessage(ChatOutput.NO_PLAYER.getText());
        }

        return false;
    }
}
