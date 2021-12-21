package de.jomlua.commands.Teleports;

import de.jomlua.JomluaCore;
import de.jomlua.util.ChatOutput;
import de.jomlua.util.RTeleportUtil;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

import java.util.*;


public class CMDWarp implements CommandExecutor, TabCompleter {
    private JomluaCore plugin;

    public CMDWarp(JomluaCore info){
        this.plugin = info;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        File file = new File("plugins/JomluaCore", "warps.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        if (sender instanceof Player) {
            if (player.hasPermission("jomlua.warp")) {
                if (command.getName().equalsIgnoreCase("warp")) {
                    if (args.length == 1) {
                        if (cfg.isSet(args[0])) {

                            RTeleportUtil.ReadLocationConf(args[0],file,player);

                            player.sendMessage("§aWarpe zu §c" + args[0]);
                            //Bukkit.broadcastMessage (world.getName());
                        }else{
                            player.sendMessage(ChatOutput.PREFIX.getText() + "§cDieser Warp ist noch nicht gesetzt.");
                        }
                    }else{
                        player.sendMessage(ChatOutput.PREFIX.getText() + "§7Befehle die dir helfen könnten.");
                        player.sendMessage("§7- §e/§bwarp §c[name]");
                    }
                }
                if (command.getName().equalsIgnoreCase("setwarp")){
                    if (player.hasPermission("jomlua.setwarp")){
                        if (args.length == 1) {
                            try {
                                cfg.load(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (InvalidConfigurationException e) {
                                e.printStackTrace();
                            }
                            // -------------------------
                            String world = player.getWorld().getName();
                            double x = player.getLocation().getX();
                            double y = player.getLocation().getY();
                            double z = player.getLocation().getZ();
                            double yaw = player.getLocation().getYaw();
                            double pitch = player.getLocation().getPitch();

                            cfg.set(args[0] + ".world", world);
                            cfg.set(args[0] + ".x", x);
                            cfg.set(args[0] + ".y", y);
                            cfg.set(args[0] + ".z", z);
                            cfg.set(args[0] + ".yaw", yaw);
                            cfg.set(args[0] + ".pitch", pitch);
                            try {
                                cfg.save(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            player.sendMessage(ChatOutput.PREFIX.getText() + "§a Warp §c" + args[0] + " §awurde gesetzt.");
                        }else{
                            player.sendMessage(ChatOutput.PREFIX.getText() + "§aBefehle die dir helfen könnten.");
                            player.sendMessage("§7- §e/§bsetwarp §c[name]");
                        }
                    }else{
                        player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
                    }

                }
                if (command.getName().equalsIgnoreCase("delwarp")){
                    if (player.hasPermission("jomlua.delwarp")){
                        if (args.length == 1){
                            if (cfg.isSet(args[0])){

                                // -------------------------
                                RTeleportUtil.DeleteLocationConf(args[0],file);

                                player.sendMessage(ChatOutput.PREFIX.getText() + "§aDer Warp §c" + args[0] + " §awurde gelöscht.");
                            }else{
                                player.sendMessage(ChatOutput.PREFIX.getText() + "§cDen Warp §3" +args[0] + " §cexistiert nicht.");
                            }
                        }else{
                            player.sendMessage(ChatOutput.PREFIX.getText() + "§aBefehle die dir helfen könnten.");
                            player.sendMessage("§7- §e/§bdelwarp §c[name]");
                        }
                    }else{
                        player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
                    }
                }
                if (command.getName().equalsIgnoreCase("warps")){
                    if (args.length == 0) {
                        int i = 1;
                        player.sendMessage(ChatOutput.PREFIX.getText() + "§aAlle verfügbare Warps:" );
                        player.sendMessage("");

                        Iterator var10 = cfg.getKeys(true).iterator();
                        while (var10.hasNext()) {
                            String arg = (String) var10.next();
                            if (!arg.contains(".")) {

                                TextComponent warps = new TextComponent();
                                warps.setText("§7[§2Teleport§7]");
                                warps.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§3Klicke um zu Warpen.")));
                                warps.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/warp " + arg));

                                TextComponent delwarp = new TextComponent();
                                delwarp.setText("§7[§cLöschen§7]");
                                delwarp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§cKlicke um §a§l" + arg.toString() + " §czu löschen.")));
                                delwarp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/delwarp " + arg.toString()));


                                TextComponent warpstext = new TextComponent();
                                warpstext.setText( i++ + ": §7" + arg + " §3-> ");

                                if (player.hasPermission("jomlua.delwarp")){
                                    warpstext.addExtra(warps);
                                    warps.addExtra(delwarp);
                                }else{
                                    warpstext.addExtra(warps);
                                }


                                player.spigot().sendMessage( warpstext );
                            }
                        }

                        boolean var11 = false;
                    }else{
                            player.sendMessage(ChatOutput.PREFIX.getText() + "§aBefehle die dir helfen könnten.");
                            player.sendMessage("§7- §e/§blistwarp");
                        }
                }
                    // Startup
            } else {
                sender.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            }

            } else {
                sender.sendMessage("Dieser befehl kann nur im spiel benutzt werden.");
            }
            return false;
        }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {

        String input = args[0].toLowerCase();

        List<String> completion = null;

        for (String stra: getWarp()){
            if (stra.startsWith(input)){
                if (completion == null){
                    completion = new ArrayList<>();
                }
                completion.add(stra);
            }
        }

        if (completion != null){
            Collections.sort(completion);
        }

        return completion;
    }

    /**
     * TODO: No tab complete on next contain.
     * @return
     */
    private static List<String> getWarp(){
        File file = new File("plugins/JomluaCore", "warps.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        List<String> warps = new ArrayList<>();


        Iterator var10 = cfg.getKeys(false).iterator();
        while (var10.hasNext()) {
            String arg = (String) var10.next();
            if (!arg.contains(".")) {
                warps.add(arg);
            }
        }
        return warps;
    }





}
