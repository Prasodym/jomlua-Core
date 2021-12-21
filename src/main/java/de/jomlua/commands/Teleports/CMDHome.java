package de.jomlua.commands.Teleports;

import de.jomlua.JomluaCore;
import de.jomlua.util.ChatOutput;
import de.jomlua.util.RTeleportUtil;
// import javafx.scene.chart.BubbleChart;
import de.jomlua.util.ChatUtils;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.*;

public class    CMDHome implements CommandExecutor, TabCompleter {
    private JomluaCore plugin;



    public CMDHome(JomluaCore info){
        this.plugin = info;
    }
    @Override

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        File file = new File("plugins/JomluaCore/Userdata", player.getUniqueId() + ".yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        if (sender instanceof Player){
            if (player.hasPermission("jomlua.home")){


                if (command.getName().equalsIgnoreCase("home")) {
                    if(args.length == 0){
                        //--------------
                        if (cfg.isSet("homes.home")){
                            RTeleportUtil.ReadLocationConf("homes.home",file, player);
                            player.sendMessage(ChatOutput.PREFIX.getText() + "§aDu hast dich nachhause Teleportiert.");
                        }else{
                            player.sendMessage(ChatOutput.PREFIX.getText() + "§cDas Home ist noch nicht gesetzt.");
                        }

                    }else{
                        if (args.length == 1){
                            if (cfg.isSet("homes." + args[0])){

                                RTeleportUtil.ReadLocationConf("homes." + args[0],file, player);


                                player.sendMessage(ChatOutput.PREFIX.getText() + "§aDu hast dich nachhause Teleportiert.");
                            }else{
                                player.sendMessage(ChatOutput.PREFIX.getText() + "§cDas Home ist noch nicht gesetzt.");
                            }

                        }
                    }
                }

                if (command.getName().equalsIgnoreCase("homes")){
                    if (args.length == 0){
                        int number = JomluaCore.plugin.getConfig().getInt("homepoints");
                        int i = 0;
                        if (player.hasPermission("jomlua.sethome.multipe")){
                            player.sendMessage(ChatOutput.PREFIX.getText() + "§aAlle gesetzte Homes §7(§2" + CMDSetHome.getHome(player).size() + " §avon§c Infiniti§7)§a.");
                        } else{
                            if (CMDSetHome.getHome(player).size() == 3){
                                player.sendMessage(ChatOutput.PREFIX.getText() + "§aAlle gesetzte Homes §7(§c" + CMDSetHome.getHome(player).size() + " §avon§c "+ number +"§7)§a.");
                            }else{
                                player.sendMessage(ChatOutput.PREFIX.getText() + "§aAlle gesetzte Homes §7(§2" + CMDSetHome.getHome(player).size() + " §avon§c "+ number +"§7)§a.");
                            }
                        }
                        sender.sendMessage("§7--------------------------------");

                        Iterator var10 = cfg.getConfigurationSection("homes.").getKeys(true).iterator();
                        while (var10.hasNext()) {
                            String list = (String) var10.next();
                            if (!list.contains(".")) {
                                TextComponent warps;
                                TextComponent delwarp;
                                TextComponent worldliste;

                               warps =  ChatUtils.ChatCommand("§7[§2Teleport§7]","/home " + list,"§4Klicke um zu Warpen.");
                               delwarp = ChatUtils.ChatCommand("§7[§cLöschen§7]","/delhome " + list.toString(),"§cKlicke um §a§l" + list.toString() + " §czu löschen.");
                               worldliste = ChatUtils.ChatText("§a" + list + " §7>> ");

                                worldliste.addExtra(warps);
                                warps.addExtra(delwarp);

                                player.spigot().sendMessage(worldliste);


                            }
                        }

                        boolean var11 = false;
                    }else{
                        player.sendMessage(ChatOutput.PREFIX.getText() + "§aBefehle die dir helfen könnten.");
                        player.sendMessage("§7- §e/§bhomes");
                    }
                }
                if (command.getName().equalsIgnoreCase("delhome")){
                    if (args.length == 1){
                        if (cfg.isSet("homes." + args[0])){
                            RTeleportUtil.DeleteLocationConf("homes." + args[0],file);


                            player.sendMessage(ChatOutput.PREFIX.getText() + "§aDei Home §c" + args[0] + " §awurde gelöscht.");
                        }else {
                            player.sendMessage(ChatOutput.PREFIX.getText() + "§cDein Home §3" + args[0] + " §cexistiert nicht.");
                        }
                    }
                }


            }else{
                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            }
        }else
        {
            // Command From console
        }
        return true;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        String input = args[0].toLowerCase();
        Player player = (Player) sender;

        List<String> completion = null;

        for (String stra: getHome(player.getUniqueId().toString())){
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


    private static List<String> getHome(String uuid){
        File file = new File("plugins/JomluaCore/Userdata", uuid+".yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        List<String> warps = new ArrayList<>();


        Iterator var10 = cfg.getConfigurationSection("homes").getKeys(false).iterator();

        while (var10.hasNext()) {
            String arg = (String) var10.next();
            if (!arg.equals("")) {
                warps.add(arg);
            }
        }
        return warps;
    }
}
