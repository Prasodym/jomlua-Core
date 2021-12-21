package de.jomlua.commands;

import com.comphenix.protocol.PacketType;
import de.jomlua.util.ChatOutput;
import de.jomlua.util.Inventory.PlayerSkull;
import de.jomlua.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDHead implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (!(player.hasPermission("jomlua.heads"))){
            player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            return true;
        }

        if (!(sender instanceof Player)){
            sender.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PLAYER.getText());
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("eheads")){
            if (args.length == 1){

                OfflinePlayer targetPlayer = (OfflinePlayer) Bukkit.getOfflinePlayer(args[0]) ;

                player.sendMessage(ChatOutput.PREFIX.getText() + "Loading Skindata...");
                player.setItemInHand(PlayerSkull.itemFromName(targetPlayer.getName()));

                player.sendMessage(ChatOutput.PREFIX.getText() + "Du hast den Kopf von §e" + targetPlayer.getName() + " §8bekommen.");
                return true;
            }
            //player.getInventory().addItem(PlayerSkull.itemFromUuid(player.getUniqueId().toString()));
            player.sendMessage(ChatOutput.PREFIX.getText() + "Du hast deinen Kopf erhalten");
            player.setItemInHand(PlayerSkull.itemFromName(player.getName()));
            return true;
        }

        return true;
    }
}
