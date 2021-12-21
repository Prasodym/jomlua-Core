package de.jomlua.commands.Secure;

import de.jomlua.JomluaCore;
import de.jomlua.mysql.ReadSQL;
import de.jomlua.util.ChatOutput;
import de.jomlua.util.Inventory.CheckInv;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExecuteBan implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {


        if (sender.hasPermission("Jomlua.ban")) {



            if (command.getName().equalsIgnoreCase("ban")) {
                if (args.length >= 2) {
                    String player = args[0];
                    String reason = "";
                    for (int i = 1; i < args.length; i++) {
                        reason += args[i] + "";
                    }
                    BannUtils.ban(sender.getName(),player, offuser(player), reason, -1);
                    Bukkit.broadcastMessage("§4" + sender.getName() + " §chat §f" + player + " §c(§fPermanent§c) gebannt!\n" + "§c(§f" + reason + "§c)");
                    return true;
                }
                sender.sendMessage(ChatOutput.PREFIX.getText() + "§aBefehle die dir helfen könnten.");
                sender.sendMessage(ChatOutput.PREFIX.getText() + "- §e/§bban §a<user> §c<reason>");
                return true;
            }

            if (command.getName().equalsIgnoreCase("tempban")) {

                if (args.length >= 4) {
                    String player = args[0];
                    try {
                        if (BannUtils.isBanned(offuser(player))) {
                            sender.sendMessage(ChatOutput.PREFIX.getText() + "§cDieser Spieler wurde bereits gebannt.");
                            return true;
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    int value;
                    try {
                        value = Integer.valueOf(args[1]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage(ChatOutput.PREFIX.getText() + "§c<time> muss eine ganze Zahl sein.");
                        return true;
                    }
                    String unit = args[2];
                    String reason = "";
                    for (int i = 3; i < args.length; i++) {
                        reason += args[i] + " ";
                    }

                    List<String> units = banTimeUnit.getTime();
                    if (units.contains(unit.toLowerCase())) {

                        banTimeUnit unit1 = banTimeUnit.getUnit(unit);
                        int seconds = value * unit1.getSeconds();
                        BannUtils.ban(sender.getName(), player, offuser(player),reason, seconds);
                        Bukkit.broadcastMessage("§4" + sender.getName() + " §chat §f" + player + " §cbis (§f" + BannUtils.toEndInGame(offuser(player)) + "§c) gebannt!\n" + "§c(§f" + reason + "§c)");
                        return true;
                    }
                    sender.sendMessage(ChatOutput.PREFIX.getText() + "§cDiese Value gibt es nicht.");
                    return true;
                }
                sender.sendMessage(ChatOutput.PREFIX.getText() + "§aBefehle die dir helfen könnten.");
                sender.sendMessage(ChatOutput.PREFIX.getText() + "- §e/§btempban §a<user> §c<time> §a<value> §v<reason>");
                return true;

            }

            if (command.getName().equalsIgnoreCase("check")) {

                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("list")) {
                        List banusers = BannUtils.getBannedPlayers();

                        if (banusers.size() == 0) {
                            sender.sendMessage(ChatOutput.PREFIX.getText() + "§aEs wurden noch keine Spieler gebannt");
                            return true;
                        }
                        sender.sendMessage(ChatOutput.PREFIX.getText() + "§aListe der Gesperrten Spieler. §7(§c" + banusers.size() + "§7)");
                        sender.sendMessage("§7--------------------------------------" );
                        for (String banlist : BannUtils.getBannedPlayers()) {

                            sender.sendMessage("§3" + banlist + " §5§l|§7 (§fGrund: §c" + BannUtils.getReason(offuser(banlist)) + "§7) " + BannUtils.isPermanent(offuser(banlist)) );

                        }
                        return true;
                    }


                    String player = args[0];

                    if (ReadSQL.isUserExists(offuser(player))){
                        try {
                            new CheckInv(offuser(player),sender);
                            JomluaCore.checkInv.put(sender.getName(), player.toString());
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }else{
                        sender.sendMessage(ChatOutput.PREFIX.getText() + "§fDer Spieler §c" + player + " §fist noch nicht eingetragen.");
                    }
                    //sender.sendMessage("\n");
                    return true;
                }
                sender.sendMessage(ChatOutput.PREFIX.getText() + "§aBefehle die dir helfen könnten.");
                sender.sendMessage(ChatOutput.PREFIX.getText() + "- §e/§bcheck [list] §a<user>");
                return true;
            }
            if (command.getName().equalsIgnoreCase("unban")) {

                if (args.length == 1) {
                    String player = args[0];
                    try {
                        if (!BannUtils.isBanned(offuser(player))) {
                            sender.sendMessage(ChatOutput.PREFIX.getText() + "§cDieser Spieler ist nicht gebannt.");
                            return true;
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    BannUtils.unban(offuser(player));
                    sender.sendMessage(ChatOutput.PREFIX.getText() + "§c" + player + "§a Wurde entbant.");
                    return true;
                }
                sender.sendMessage(ChatOutput.PREFIX.getText() + "- §e/§bunban §a<user>");
                return false;
            }
        }else{
            sender.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        //arr
        List<String> list = Arrays.asList("min", "hour", "day", "week");



        // Var für /command <input>
        List<String> completion = null;
        List<String> PlayerList = ReadSQL.getOfflineSpieler();
        List<String> isBannesList = BannUtils.getBannedPlayers();


        if (command.getName().equalsIgnoreCase("tempban")){
            if (args.length == 3){
//                String input = args[0].toLowerCase();
                for (String stra: list){

                        if (completion == null){
                            completion = new ArrayList<>();
                        }
                        completion.add(stra);

                }
            }else if(args.length == 1){
                for (String stra: PlayerList){

                    if (completion == null){
                        completion = new ArrayList<>();
                    }
                    completion.add(stra);

                }
            }
        }

        if (command.getName().equalsIgnoreCase("unban")){
            if (args.length == 1){
//                String input = args[0].toLowerCase();
                for (String stra: isBannesList){

                    if (completion == null){
                        completion = new ArrayList<>();
                    }
                    completion.add(stra);
                    //Bukkit.broadcastMessage("§3" + isBannesList.toString() + " | §a"+ list.toString());

                }
            }
        }
        if (command.getName().equalsIgnoreCase("check")){
            if (args.length == 1) {
                for (String str: PlayerList){
                    if (completion == null){
                        completion = new ArrayList<>();
                    }
                    completion.add(str);
                }
            }
        }


//        for (String type: listtype){
//            if (args[0].equalsIgnoreCase("create")){
//                if (completion == null){
//                    completion = new ArrayList<>();
//                }
//                completion.add(type);
//            }
//        }


        if (completion != null){
            Collections.sort(completion);
        }

        return completion;
    }


    private String offuser(String player){
        String name = Bukkit.getOfflinePlayer(player).getUniqueId().toString();
       return name;
    }
}
