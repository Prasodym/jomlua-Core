package de.jomlua.commands;


import de.jomlua.util.ChatOutput;
import de.jomlua.util.Inventory.InvseeUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;

import static de.jomlua.JomluaCore.*;

public class CMDInvsee implements CommandExecutor {
    private ItemStack[] inv;





    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;
        if (sender instanceof Player){
            if (player.hasPermission("jomlua.invsee")){
                if (args.length == 1){
                    Player target = Bukkit.getPlayer(args[0]);

                    if (target != null){
                        player.openInventory(InvseeUtil.openInv(target,player));

                    } else {
                        player.sendMessage(ChatOutput.PREFIX.getText() + "§cDieser Spieler ist derzeit Offline.");
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
