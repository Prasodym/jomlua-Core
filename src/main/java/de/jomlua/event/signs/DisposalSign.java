package de.jomlua.event.signs;

import de.jomlua.JomluaCore;
import de.jomlua.util.ChatOutput;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.File;

import static de.jomlua.JomluaCore.plugin;

public class DisposalSign implements Listener {

    @EventHandler
    private void SignChanged(SignChangeEvent e){
        Player player = e.getPlayer();
        String[] line = e.getLines();

        if (player.hasPermission("jomlua.sign.disposal")){
            if (line[0].equalsIgnoreCase("[Mülleimer]")){
                player.sendMessage(ChatOutput.PREFIX.getText() + "§aDas Disposal Schild wurde erfolgreich erstellt.");
                e.setLine(0, ChatColor.DARK_RED + "[Mülleimer]");
            }
        }else{
            player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void OnPlayerClickSign(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (player.hasPermission("jomlua.sign.disposal")){
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
                if (event.getClickedBlock().getState() instanceof Sign){
                    Sign s = (Sign) event.getClickedBlock().getState();
                    if (s.getLine(0).equalsIgnoreCase( ChatColor.DARK_RED + "[Mülleimer]")){
                        String a = s.getLine(1);
                        String b = ChatColor.stripColor(a);
                        player.openInventory(plugin.getServer().createInventory(player, 36,"Disposal"));
                        return;

                    }
                }
            }
        } else{
            player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
        }
    }
}
