package de.jomlua.commands;

import de.jomlua.event.ChatListener;
import de.jomlua.util.ChatUtils;
import de.jomlua.util.ChatOutput;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDWhether implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("jomlua.wheater")){
            player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            return true;
        }
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatOutput.PREFIXC.getText()+ ChatOutput.NO_PLAYER.getText());
            return true;
        }
        World world = player.getWorld();
        if (command.getName().equalsIgnoreCase("sun")){
            world.setStorm(false);
            //player.sendMessage(ChatOutput.PREFIX.getText() + Chat.setHexColor("&a Du hast das wetter umgestellt zu &Sonne"));
            ChatUtils.sendMessageAtHex(player, "&a Du hast das wetter umgestellt zu &cSonne");

        }
        if (command.getName().equalsIgnoreCase("storm")){
            world.setStorm(true);
            player.sendMessage(ChatOutput.PREFIX.getText() + ChatUtils.setHexColor("&a Du hast das wetter umgestellt zu &cSturm"));

        }



        return false;
    }
}
