package de.jomlua.commands;

import de.jomlua.util.getSelectPos;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;



public class CMDSetBlock implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


            Player player = (Player) sender;

            if (command.getName().equalsIgnoreCase("set")){
                if (sender instanceof Player){
                    if (args.length == 1){
                        getSelectPos.setBlock(player, args[0]);
                    }
                }
                return true;
            }
            return true;
    }



    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> tabComplete = new ArrayList<String>();
        if (args.length == 1){
            for (Material all : Material.values()){
                tabComplete.add(all.name());
                // Todo Material list asyncrone
            }
        }
        return tabComplete;
    }
}
