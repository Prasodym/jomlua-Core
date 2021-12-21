package de.jomlua.commands.tpa;

import de.jomlua.JomluaCore;
import de.jomlua.util.ChatOutput;
import de.jomlua.util.CountdownTimer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class tpadeny extends tpa implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (args.length==0){
            if (player.hasPermission("jomlua.tpa")){
                UUID target = JomluaCore.tpa.get(player.getUniqueId());

                Player target1 = Bukkit.getPlayer(target);

                HashMap<String, String> replacements = new HashMap<String, String>();
                replacements.put("%target%", player.getDisplayName());
                if (JomluaCore.tpa.get(player.getUniqueId()) != null){
                    JomluaCore.tpa.remove(player.getUniqueId());
                    JomluaCore.tpType.remove(player.getUniqueId());
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.TPA_DENY_A.getText());
                    target1.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.TPA_DENY_B.getText(replacements));

                } else{
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.TPA_DENY_I.getText());
                }



            }

        }else{
            player.sendMessage("§a- §e/tpadeny");
        }
        return false;
    }
}
