package de.jomlua.commands;

import de.jomlua.util.ChatOutput;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDKillme implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;

        if (!(player.hasPermission("jomlua.killme"))){
            player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            return true;
        }
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PLAYER.getText());
            return true;
        }

        if (args.length == 0) {
            player.setHealth(0.0D);
            player.sendMessage("Du hast dich getötet");
        }


        return true;
    }
}
