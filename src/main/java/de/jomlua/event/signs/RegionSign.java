package de.jomlua.event.signs;

import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import de.jomlua.util.ChatOutput;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import javax.swing.plaf.nimbus.State;
import java.io.File;

import static de.jomlua.JomluaCore.plugin;

public class RegionSign implements Listener {

    public RegionSign() {
    }
    public static WorldGuardPlugin getAPI() {
        return JavaPlugin.getPlugin(WorldGuardPlugin.class);
    }

    @EventHandler
    private void SignChanged(SignChangeEvent e){
        Player player = e.getPlayer();
        String[] line = e.getLines();

        File file = new File("plugins/JomluaCore", "warps.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        if (player.hasPermission("jomlua.sign.addregion")){
            if (line[0].equalsIgnoreCase("[Region]")){
                if (!line[1].isEmpty()){


                    if (!line[1].isEmpty()){
                        player.sendMessage(ChatOutput.PREFIX.getText() + "§aDas Warp Schild wurde erfolgreich erstellt.");
                        e.setLine(0, ChatColor.DARK_BLUE + "[Region]");
                        e.setLine(1, ChatColor.GREEN + line[1]);
                        e.setLine(2,ChatColor.GREEN + player.getName());
                    }else{
                        player.sendMessage(ChatOutput.PREFIX.getText() + "§cDieser Warp ist noch nicht gesetzt.");
                        e.setCancelled(true);
                    }
                } else {
                    player.sendMessage(ChatOutput.PREFIX.getText() + "Du musst in der zweiten zeile dein Warp Ziel eingeben.");
                    e.setCancelled(true);

                }
            }
        }else{
            player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            e.setCancelled(true);
        }

    }



    @EventHandler
    public void OnPlayerClickSign(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("jomlua.sign.warp")) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getClickedBlock().getState() instanceof Sign) {
                    Sign s = (Sign) event.getClickedBlock().getState();
                    if (s.getLine(0).equalsIgnoreCase(ChatColor.DARK_BLUE + "[Region]")) {



                        String a = s.getLine(1);
                        String b = ChatColor.stripColor(a);

                        //Bukkit.getServer().dispatchCommand(player, "rg flag " + b + "mobspawning allow");
                        plugin.getLogger().info(b);

                        if (getFlag("g1",Flags.MOB_SPAWNING)) {
                            player.sendMessage("true");
                        }else{
                            player.sendMessage("false");
                        }


                    }
                }
            }
        } else {
            player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
        }

    }
    public static boolean getFlag(String location, StateFlag flag) {
        StateFlag.State state = null;
        try{
            RegionManager regionManager = null;
            ProtectedRegion region;
            ProtectedRegion regions = regionManager.getRegion(location);
            ApplicableRegionSet raum = regionManager.getApplicableRegions(regions);

            state = raum.queryState(null,flag);
            
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        
        return (state != StateFlag.State.DENY);
    }
}
