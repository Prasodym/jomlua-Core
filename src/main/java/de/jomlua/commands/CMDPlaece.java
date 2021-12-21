package de.jomlua.commands;

import de.jomlua.util.ChatOutput;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CMDPlaece implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("jomlua.setentity")){
                if (args.length == 2){
                    String entity = args[0];
                    String count = args[1];

                    EntityType type = EntityType.valueOf(entity.toUpperCase());
                    if (type == null) return false;
                    if (!type.isSpawnable()) return false;

                    int counter;

                    try{
                        counter = Integer.parseInt(count);
                    }catch (NumberFormatException e){
                        return false;
                    }
                    for (int i = 0; i < counter; i++){
                        player.getLocation().getWorld().spawnEntity(player.getLocation(), type);
                    }
                }
            }else{
                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            }
        }else{
            sender.sendMessage(ChatOutput.PREFIXC.getText() + ChatOutput.NO_PLAYER.getText());
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0) return list;
        if (args.length == 1){
            for (EntityType values : EntityType.values()){
                if (values.isSpawnable()){
                    list.add(values.toString().toLowerCase());
                }
            }
        }else if (args.length == 2){
            for (int i = 0; i < 1; i++){
                list.add(String.valueOf(i));
            }
        }

        ArrayList<String> complete = new ArrayList<>();
        String current = args[args.length-1].toLowerCase();

        for (String s : list){
            String s1 = s.toLowerCase();
            if (s1.startsWith(current)){
                complete.add(s);
            }
        }
        return complete;
    }
}
