package de.jomlua.commands;

import de.jomlua.mysql.ReadSQL;
import de.jomlua.util.ChatOutput;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDDiscord implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (sender instanceof Player){
            if (args.length == 0){

                String[] s = ChatOutput.DISCORD.getText().split("%n");
                for (String m : s){
                    player.sendMessage(ChatOutput.PREFIX.getText() + m);
                }
                sender.sendMessage(ReadSQL.getUUID(player.getName()));
                player.sendMessage(ReadSQL.getPlayername(player.getUniqueId().toString()));



            }
        }
        return false;
    }



//    public static String getdat(String str){
//        try {
//            PreparedStatement pdo = MySQL.getConnection().prepareStatement("SELECT * FROM users WHERE username = ?" );
//            pdo.setString(1, str);
//            ResultSet rs = pdo.executeQuery();
//            while (rs.next()){
//                return rs.getString("uuid");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return str;
//    }
}
