package de.jomlua.commands;

import de.jomlua.JomluaCore;
import de.jomlua.commands.Secure.BannUtils;
import de.jomlua.mysql.ReadSQL;
import de.jomlua.util.ChatOutput;
import de.jomlua.util.CountdownTimer;
import de.jomlua.util.Inventory.SynInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class CMDDebug implements CommandExecutor {
    private static File file = new File("plugins/JomluaCore","time.yml");
    private static YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
    //Date timer = new Date();


    CountdownTimer time = new CountdownTimer(JomluaCore.plugin,
            10,
            () -> Bukkit.broadcastMessage(ChatColor.YELLOW + "Timer is commencing. Get ready!"),
            () -> {
                Bukkit.broadcastMessage(ChatColor.YELLOW + "Timer is up!");

                // Heal everyone
                Bukkit.getOnlinePlayers().forEach((p) -> p.setHealth(20.0));
            },
            (t) -> Bukkit.broadcastMessage(ChatColor.YELLOW + "Time left: " + (t.getSecondsLeft()) + "/" + (t.getTotalSeconds()) + " seconds")

    );

// Start scheduling, don't use the "run" method unless you want to skip a second


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (!player.hasPermission("jomlua.debug")) {
            player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            return true;
        }
        World world = Bukkit.getWorld(player.getWorld().getName());
        if (sender instanceof Player) {
            if (args.length == 0) {
                //BannUtils.unban(player.getUniqueId().toString());
                player.sendMessage("§aDeine Daten: ");
                player.sendMessage("");
                player.sendMessage("§7Spielername: §c" + ReadSQL.getPlayername(player.getUniqueId().toString()));
                player.sendMessage("§7UUID: §c" + ReadSQL.getUUID(player.getName()));
                player.sendMessage("§7Dein Kontostand beträgt: §a" + ReadSQL.getMoney(player.getUniqueId().toString()) + " §7Blockmark.");
                player.sendMessage("§7Spielzeit: §a" + ReadSQL.getOnlineTimeReal(player.getUniqueId().toString()));
                time.scheduleTimer();

                //try {
                    //SynInventory.getInvetory(player);
               // } catch (IOException e) {
                  //  e.printStackTrace();
               // }

            } else {
                if (args.length == 1) {
                    OfflinePlayer of = Bukkit.getOfflinePlayer(args[0]);
                    player.sendMessage("§7 The uuid number -> §a" + ReadSQL.getUUID(of.getName()));


                    //Long s = JomluaCore.Online.get(args[1]);
                    sender.sendMessage("Debug Mode true..." );

                }
            }

        }
        return false;
    }
//    private Long minute(Player player) {
//        Long totalTime = null;
//
//        try {
//            for (int i = 0; i < player; 1++)
//
//        } catch (NullPointerException e){
//            e.printStackTrace();
//            Bukkit.getLogger().log(Level.WARNING, "Es liegt hier ein Dicker fehler!");
//            long min = config.getLong("Spieler.user." + player.getName() + ".time");
//           // long totalTime = min/60000;
//            return totalTime;
//        }finally {
//
//            System.out.println("true");
//        }
//        return totalTime;
//    }
}

