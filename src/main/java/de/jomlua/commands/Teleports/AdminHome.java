package de.jomlua.commands.Teleports;

import de.jomlua.util.ChatOutput;
import de.jomlua.util.RTeleportUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Iterator;

public class AdminHome implements CommandExecutor {



    //    private String loadConf(String args){
//        OfflinePlayer target = Bukkit.getOfflinePlayer(args);
//        File file1 = new File("plugins/JomluaCore/Userdata", target.getUniqueId() + ".yml");
//        FileConfiguration cfg1 = YamlConfiguration.loadConfiguration(file1);
//        return this.target.toString();
//    }
//    private loadConf target;
    //String andre = args1;



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (player.hasPermission("jomlua.admin.home") || player.isOp()){
            if (args.length == 0) {


                player.sendMessage("Test");


            } else if (args[0].equalsIgnoreCase("list")) {
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                File file1 = new File("plugins/JomluaCore/Userdata", target.getUniqueId() + ".yml");
                FileConfiguration cfg1 = YamlConfiguration.loadConfiguration(file1);

                int i = 0;
                player.sendMessage(ChatOutput.PREFIX.getText() + "§aAlle gesetzte Homes:");

                Iterator var10 = cfg1.getConfigurationSection("homes.").getKeys(true).iterator();
                while (var10.hasNext()) {
                    String arg = (String) var10.next();
                    if (!arg.contains(".")) {

                        player.sendMessage("§7- §3" + arg + "");
                    }
                }


            } else if (args[0].equalsIgnoreCase("tp")) {

                try {

                }catch (ArrayIndexOutOfBoundsException e){
                    player.sendMessage("/ahome tp <name|home> <Player>");
                }
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                File file = new File("plugins/JomluaCore/Userdata", target.getUniqueId() + ".yml");
                FileConfiguration cfg1 = YamlConfiguration.loadConfiguration(file);
                String name = cfg1.getString("Username");
                if (!(args[2] == null)){
                    if (cfg1.isSet("homes." + args[2])) {
                        if (file.exists()) {



                            RTeleportUtil.ReadLocationConf("homes." + args[2], file, player);
                            player.sendMessage(ChatOutput.PREFIX.getText() + "§aTeleportiere zum §c" + args[2] + " §avon §c" + name);


                        } else {
                            player.sendMessage(ChatOutput.PREFIX.getText() + "§a" + args[2] + " §c Existiert nicht. ");
                            return true;
                        }


                    } else {
                        player.sendMessage(ChatOutput.PREFIX.getText() + "§a" + name + " §c hat kein Home ->§4 " + args[2]);
                    }
                }else {
                    player.sendMessage("/ahome tp <Player> <name|home>");
                }

                //player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b" + args[0] +"&8:&9"+ target));
            }
        } else{
            player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
        }



        return false;
    }
}
