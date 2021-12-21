package de.jomlua.util;

import de.jomlua.JomluaCore;
import de.jomlua.event.*;
import de.jomlua.event.signs.*;
import de.jomlua.util.Inventory.InvseeUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class getListener {

    public static void setUp(){
        addListener();
    }
    public static void addListener(){
        PluginManager pm = Bukkit.getPluginManager();
        JomluaCore r = JomluaCore.plugin;

        //pm.registerEvents(new SelectRegion(), r);
        pm.registerEvents(new PlayerNameTag(),r);
        pm.registerEvents(new JoinListener(), r);
        pm.registerEvents(new QuitListener(),r);
        pm.registerEvents(new ChatListener(),r);
        pm.registerEvents(new DamageListener(), r);
        pm.registerEvents(new DeathListener(), r);
        pm.registerEvents(new PlayerLoginListener(),r);
        pm.registerEvents(new ArmorStands(),r);
        pm.registerEvents(new AdminshopSign(),r);
        //pm.registerEvents(new BreakBlockListener(), r);
        pm.registerEvents(new HealSign(),r);
        pm.registerEvents(new ColoredSignEvent(),r);
        pm.registerEvents(new EnderChestSign(),r);
        pm.registerEvents(new WarpSign(),r);
        pm.registerEvents(new CloseInventory(),r);
        pm.registerEvents(new DisposalSign(),r);
        pm.registerEvents(new VoteEvent(),r);
        pm.registerEvents(new InvseeUtil(),r);
        //pm.registerEvents(new SelectRegion(), r);
        pm.registerEvents(new RegionSign(),r);
    }
}
