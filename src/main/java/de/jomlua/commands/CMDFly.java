package de.jomlua.commands;

import de.jomlua.util.ChatOutput;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static de.jomlua.JomluaCore.*;

public class CMDFly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("jomlua.fly")){
                if (args.length == 0){

                    if (player.getAllowFlight()){
                        player.setAllowFlight(false);
                        player.sendMessage(ChatOutput.PREFIX.getText() + "§aFliegen wurde für §c"+ player.getName() +"§a deaktiviert");
                    }else{
                        player.setAllowFlight(true);
                        player.sendMessage(ChatOutput.PREFIX.getText() + "§aFliegen wurde für §c"+ player.getName() +"§a akiviert");
                    }
                }else{
                    if (player.hasPermission("jomlua.fly.out")) {
                        if (args.length == 1) {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (target != null) {

                                if (target.getAllowFlight()){
                                    target.setAllowFlight(false);
                                    target.sendMessage(ChatOutput.PREFIX.getText() + "§aDu kannst jetzt nicht mehr fliegen.");
                                    target.sendTitle("§3Flug deaktiviert", "§avon " + player.getName());
                                    player.sendMessage(ChatOutput.PREFIX.getText() + "§aDu hast für §3§l" + target.getName() + "§a fly deaktiviert.");
                                }else{
                                    target.setAllowFlight(true);
                                    target.sendMessage(ChatOutput.PREFIX.getText() + "§aDu kannst jetzt fliegen.");
                                    target.sendTitle("§cFlug aktiviert", "§avon " + player.getName());
                                    player.sendMessage(ChatOutput.PREFIX.getText() + "§aDu hast für §3§l" + target.getName() + "§a fly aktiviert.");
                                }

                            }
                        }else{
                            player.sendMessage(ChatOutput.PREFIX.getText() +"§cBitte benutze §a /fly <name>");
                        }
                    }

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
