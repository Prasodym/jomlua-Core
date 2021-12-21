package de.jomlua.commands.Teleports;

import de.jomlua.JomluaCore;
import de.jomlua.util.ChatOutput;
import de.jomlua.util.TeleportTyp;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static de.jomlua.JomluaCore.*;

public class CMDTphere implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("tphere")){
            if (sender instanceof Player){
                Player player = (Player) sender;
                if(player.hasPermission("jomlua.tphere")){
                    if (args.length == 1){
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null){
                            if (target != player){
                                target.teleport(player);

                                player.sendMessage(ChatOutput.PREFIX.getText() + "§aDu hast dich zu §c" + target.getName() + "§a teleportiert.");
                                target.sendMessage(ChatOutput.PREFIX.getText() + "§c" + target.getName() + "§a hat sich zu dir teleportiert.");
                                return true;
                            }else{
                                player.sendMessage(ChatOutput.PREFIX.getText() + "§cDu kannst dich nicht zu dir teleportieren.");
                                return true;
                            }
                        }else{
                            player.sendMessage(ChatOutput.PREFIX.getText() + "§cDer Spieler ist gerade nicht da.");
                            return true;
                        }

                    }else{
                        player.sendMessage(ChatOutput.PREFIX.getText() + "§cDu musst ein Spieler mit angeben.");
                    }
                }else{
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
                }

            }else{
                sender.sendMessage(ChatOutput.PREFIXC.getText() + ChatOutput.NO_PLAYER.getText());
                return true;
            }
        }
        return false;
    }
}
