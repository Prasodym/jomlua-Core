package de.jomlua.commands;

import de.jomlua.mysql.MySQL;
import de.jomlua.mysql.ReadSQL;
import de.jomlua.util.ChatOutput;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CMDOnlinetime implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (player.hasPermission("jomlua.playtime")){

                if (args.length == 0){
                    int min;
                    int vmin;
                    int day;
                    int out;


                    vmin = ptimeQuery(player);
                    min = (vmin / 60);
                    day = (min / 24);
                    out = (min%60);

                        //player.sendMessage(ChatOutput.PREFIX.getText() + "§aDu hast insgesammt §3" + day + " Tage, " + min%24 + " Stunden §aund §3" + out + " Minuten §aSpielzeit.");
                    player.sendMessage(ChatOutput.PREFIX.getText() + "§e" + player.getName() + " §8war " + ReadSQL.getOnlineTimeReal(player.getUniqueId().toString()) + " §8Online.");

                }else if (args.length == 1){


                        if (player.hasPermission("jomlua.playtime.out")) {
                            if (!(SelectPlayer(args[0]) == null)) {
                                int min;
                                int vmin;
                                int day;


                                vmin = PofTimeQuery(args[0]);
                                min = (vmin / 60);
                                day = (min / 24);


                                //player.sendMessage(ChatOutput.PREFIX.getText() + "§c" + args[0] + "§a hat insgesammt §3"  + day + " Tage, " + min%24 + " Stunden §aund §3" + min%60 + " Minuten §aSpielzeit.");
                                player.sendMessage(ChatOutput.PREFIX.getText() + "§e" + args[0] + " §8war " + ReadSQL.getOnlineTimeReal(player.getUniqueId().toString()) + " §8Online.");
                            }else{
                                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PLAYER.getText());
                            }

                        }else{
                                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
                        }


                }
            }else {
                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS);
            }
        }else{
            if (args.length == 1){
                Player target= Bukkit.getPlayer(args[0]);

                    if (SelectPlayer(args[0]) == null) {


                        int min;
                        int vmin;
                        int day;


                        vmin = ptimeQuery(target);
                        min = (vmin / 60);
                        day = (min / 24);

                        //sender.sendMessage(ChatOutput.PREFIX.getText() + "§c" + target.getDisplayName() + "§a hat insgesammt §3"  + day + " Tage, " + min%24 + " Stunden §aund §3" + min%60 + " Minuten §aSpielzeit.");
                        sender.sendMessage(ChatOutput.PREFIX.getText() + "§e" + target.getName() + "§8war " + ReadSQL.getOnlineTimeReal(target.getUniqueId().toString()) + " §8Online.");
                    }else{

                        sender.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PLAYER.getText());
                    }
                }
        }

        return false;
    }

    public static Integer ptimeQuery(Player player){
        try {
            PreparedStatement pdo = MySQL.getConnection().prepareStatement("SELECT playtime FROM users WHERE uuid = ?");
            pdo.setString(1, player.getUniqueId().toString());
            ResultSet rs = pdo.executeQuery();
            while (rs.next()){
                return rs.getInt("playtime");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static String SelectPlayer(String str){
        try {
            PreparedStatement pdo = MySQL.getConnection().prepareStatement("SELECT uuid FROM users WHERE username = ?" );
            pdo.setString(1, str);
            ResultSet rs = pdo.executeQuery();
            while (rs.next()){
                return rs.getString("uuid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return str;
    }

    public static Integer PofTimeQuery(String args){
        try {
            PreparedStatement pdo = MySQL.getConnection().prepareStatement("SELECT playtime FROM users WHERE uuid = ?");
            pdo.setString(1, SelectPlayer(args));
            ResultSet rs = pdo.executeQuery();
            while (rs.next()){
                return rs.getInt("playtime");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

}
