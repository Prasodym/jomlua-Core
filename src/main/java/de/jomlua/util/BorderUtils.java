package de.jomlua.util;

import de.jomlua.JomluaCore;
import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;

public class BorderUtils {

    private static WorldBorder border = Bukkit.getWorld("world").getWorldBorder();

    public static void setBorder() {
        border.setCenter(Bukkit.getWorld("world").getSpawnLocation());
        border.setSize(JomluaCore.getPlugin().getConfig().getInt("border") * 2);
        border.setWarningDistance(5);
    }
}
