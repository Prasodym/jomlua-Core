package de.jomlua.commands;

import de.jomlua.util.ChatOutput;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.xml.validation.ValidatorHandler;
import java.util.HashMap;

import static de.jomlua.JomluaCore.*;

public class CMDHeal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            HashMap<String, String> replacements = new HashMap<String, String>();
            replacements.put("%player%", player.getDisplayName());

            if (player.hasPermission("jomlua.heal")){
                if (args.length == 0){
                    player.setHealth(20);
                    player.setFoodLevel(20);
                    player.sendMessage(ChatOutput.PREFIX.getText() + "§aDu wurdes geheilt.");
                }else
                    {
                    if (player.hasPermission("jomlua.heal.out")){

                        if (args.length == 1){
                            Player target = Bukkit.getPlayer(args[0]);

                            if (target != null) {
                                replacements.put("%target%", target.getDisplayName());
                                target.setHealth(20);
                                target.setFoodLevel(20);
                                target.sendMessage(ChatOutput.HEAL_TO.getText(replacements));
                                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.HEAL_FOR_ME.getText(replacements));
                            }else{
                                replacements.put("%no_player%",args[0]);
                                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PLAYER_ONLINE.getText(replacements));
                            }//Online end
                        }else{
                            player.sendMessage(ChatOutput.PREFIX.getText() + "§6Benutze /heal <player>");
                            //arg 2
                        }
                    }else{
                        player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
                    }
                }
            }else{
                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            }

        }else{
            sender.sendMessage(ChatOutput.NO_PLAYER.getText());
        }
        return false;
    }
}
