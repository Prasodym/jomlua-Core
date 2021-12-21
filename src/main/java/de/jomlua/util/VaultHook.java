package de.jomlua.util;

import de.jomlua.JomluaCore;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.ServicePriority;

public class VaultHook {
    private JomluaCore plugin = JomluaCore.getPlugin();
    private Economy eco;

    public void hook(){
        eco = plugin.economyImp;
        Bukkit.getServicesManager().register(Economy.class, this.eco, this.plugin, ServicePriority.Normal);
        Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + ChatColor.GREEN + "VaultAPI hooked into " + ChatColor.YELLOW + plugin.getName());
    }
    public void unHook(){
        Bukkit.getServicesManager().unregister(Economy.class, this.eco);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "VaultAPI unhooked from " + ChatColor.YELLOW + plugin.getName());
    }
}
