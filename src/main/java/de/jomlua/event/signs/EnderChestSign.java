package de.jomlua.event.signs;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EnderChestSign implements Listener {
    public EnderChestSign() {
    }

    @EventHandler
    private void SignChanged(SignChangeEvent event) {
        if (event.getLine(0).equalsIgnoreCase("[inv]")) {
            event.setLine(0, "§9[inv]");
        }

    }

    @EventHandler
    public void OnPlayerClickSign(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getState() instanceof Sign) {
            Sign s = (Sign)event.getClickedBlock().getState();
            if (s.getLine(0).equalsIgnoreCase("§9[inv]")) {
                player.openInventory(player.getEnderChest());
            }
        }

    }
}
