package de.jomlua.commands.Teleports;

import de.jomlua.util.ChatOutput;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static de.jomlua.JomluaCore.*;

public class CMDTp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("tp")){
            if (sender instanceof Player){
                Player player = (Player) sender;
                if(player.hasPermission("jomlua.tp")){
                    if (args.length == 1){
                        Player target = (Player) Bukkit.getPlayer(args[0]);
                        if (target != null){
                            if (player != target){
                                player.teleport(target);
                                player.sendMessage(ChatOutput.PREFIX.getText()+ "§aDu hast dich zu §c" + target.getName() + "§a teleportiert.");
                                target.sendMessage(ChatOutput.PREFIX.getText()+ "§c" + player.getName() + "§a hat sich zu dir teleportiert.");
                                return true;
                            }else{
                                player.sendMessage(ChatOutput.PREFIX.getText()+ "§cDu kannst dich nicht zu dir teleportieren.");
                                return true;
                            }
                        }else{
                            player.sendMessage(ChatOutput.PREFIX.getText()+ "§cDer Spieler ist gerade nicht da.");
                            return true;
                        }

                    }else{
                        player.sendMessage(ChatOutput.PREFIX.getText()+ "§cDu musst ein Spieler mit angeben.");
                    }
                }else{
                    player.sendMessage(ChatOutput.PREFIX.getText()+ ChatOutput.NO_PERMISSIONS.getText());
                }

            }else{
                sender.sendMessage(ChatOutput.PREFIXC.getText() + ChatOutput.NO_PLAYER.getText());
                return true;
            }
        }

        if (command.getName().equalsIgnoreCase("tpo")){
            if (sender instanceof Player){
                Player player = (Player) sender;
                if(player.hasPermission("jomlua.tpo")){
                    if (args.length == 3){

                        World world;

                        Location location = new Location(player.getWorld(), Double.parseDouble(args[0]),Double.parseDouble(args[1]) ,Double.parseDouble(args[2]) );


                                player.teleport(location);
                                player.sendMessage(ChatOutput.PREFIX.getText()+ "§aDu hast dich zu §c" + "§a teleportiert.");

                                return true;



                    }else{
                        player.sendMessage(ChatOutput.PREFIX.getText()+ "§a/tpo§e <x> <y> <z>");
                    }
                }else{
                    player.sendMessage(ChatOutput.PREFIX.getText()+ ChatOutput.NO_PERMISSIONS.getText());
                }

            }else{
                sender.sendMessage(ChatOutput.PREFIXC.getText() + ChatOutput.NO_PLAYER.getText());
                return true;
            }
        }



        return false;
    }
}
