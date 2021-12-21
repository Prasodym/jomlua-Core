package de.jomlua.commands.Secure;

import de.jomlua.util.ChatOutput;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.logging.Level;

public class kick implements CommandExecutor {
    HashMap<String, String> replacements = new HashMap<String, String>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            //run player commands
            Player player = (Player) sender;
            if (player.hasPermission("jomlua.kick")){
                sender.sendMessage(ChatOutput.PREFIX.getText());
                return true;
            }

            if (command.getName().equalsIgnoreCase("kick")){
                // Runn kommand /kick
                    //String reason = args[1];

                List<String> list = new LinkedList<>(Arrays.asList(args));
                list.remove(0);

                String reason = ChatColor.translateAlternateColorCodes('&', String.join(" ", list));


                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null){
                        target.kickPlayer("§c" + reason);
                        Bukkit.broadcastMessage("§c" +target.getName() + " §awurde gekickt wegen: §8( §7" + reason + " §8)");
                        Bukkit.getLogger().log(Level.WARNING, ChatOutput.PREFIX.getText() + ChatColor.RED +target.getName() + ChatColor.GREEN +" wurde gekickt wegen: "+ ChatColor.DARK_GRAY +"( " + ChatColor.DARK_RED  + reason + ChatColor.DARK_GRAY +" )");
                    } else{
                        replacements.put("%np_player%",args[0]);
                        player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PLAYER_ONLINE.getText(replacements));
                    }


                return  true;
            }else if (command.getName().equalsIgnoreCase("kickall")){
                if (args.length == 0){
                    for (Player target : Bukkit.getServer().getOnlinePlayers()){
                        if (player.isOp() || target.hasPermission("jomlua.whitekickall")){
                            target.sendMessage(ChatOutput.PREFIXC.getText() + "§dDie Wartungsarbeiten sind gerade aktiv.");
                            target.setWhitelisted(true);
                        }else{
                            target.kickPlayer("§cSorry, Ab jetzt führen wir Wartungsarbeiten durch. Bitte komme Später nochmal vorbei.");
                            target.setWhitelisted(false);
                        }
                    }
                    Bukkit.setWhitelist(true);


                }
                return true;
            }


        }else{
            //Runn Console command
            Bukkit.getLogger().log(Level.FINE, " Test Bestanden!");
        }

        return false;
    }
}
