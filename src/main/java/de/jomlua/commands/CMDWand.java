package de.jomlua.commands;

import de.jomlua.util.ChatOutput;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;



public class CMDWand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("wand")){
            //Prüft ob console oder Spieler
            if (sender instanceof Player){
                //Spieler Erkannt, und hier gehts weiter
                if (player.hasPermission("jomlua.wand")){
                    final Inventory inv = player.getInventory();
                    player.sendMessage(ChatOutput.PREFIX.getText() + "§eDu hast nun deine Axt.");

                    ItemStack test = new ItemStack(Material.WOODEN_AXE);
                    inv.addItem(test);
                }else{
                    //Spieler hat hierfür keine Permission
                    player.sendTitle(ChatOutput.NO_PERMISSIONS.getText(),"§aHallo" + player.getName(),5,5,5);
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
                }
            }else{
                //Console Erkannt, und hier gehts weiter
                sender.sendMessage(ChatOutput.PREFIXC.getText() + ChatOutput.NO_PLAYER.getText());
            }
            return true;
        }
        return true;
    }
}
