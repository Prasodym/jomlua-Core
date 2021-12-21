package de.jomlua.commands;

import com.comphenix.protocol.PacketType;
import de.jomlua.JomluaCore;
import de.jomlua.util.ChatOutput;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class CMDEndersee implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        HashMap<String, String> repl = new HashMap<>();

        if (sender instanceof Player){
            if (player.hasPermission("jomlua.endsee")){
                if (args.length == 0){

                    player.openInventory(player.getEnderChest());
                }else{
                    if (player.hasPermission("jomlua.out.endsee")){
                        if (args.length == 1){
                            Player target = Bukkit.getPlayer(args[0]);
                            if (!(target == null)){

                                player.openInventory(target.getEnderChest());
                            }else{
                                repl.put("%no_player%", args[0]);
                                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PLAYER_ONLINE.getText(repl));

                            }

                        }else {
                            player.sendMessage(ChatOutput.PREFIX.getText() + "/endsee <Spieler>");
                        }
                    }else{
                        player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
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
   // Auf solche Gespräche kann ich auch pfeifen. Ich bin hier nicht irgend ein Assi mit den so umgehen kannst.