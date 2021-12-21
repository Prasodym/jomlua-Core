package de.jomlua.commands;

import de.jomlua.util.ChatOutput;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDTime implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        Player player = (Player) sender;
        //World world = player.getWorld();

        // Has user permissins?
        if (!(player.hasPermission("jomlua.days"))){
            player.sendMessage(ChatOutput.NO_PERMISSIONS.getText());
            return true;
        }
        // is user console or not?
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatOutput.NO_PLAYER.getText());
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("day")){
            if (args.length == 0){
                //world.setTime(0);
                for (World world : Bukkit.getServer().getWorlds()){
                    world.setTime(0);
                }
                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.DAY.getText());
            }
        }

        if (cmd.getName().equalsIgnoreCase("nigth")){
            if (args.length == 0){
                //world.setTime(1200);

                for (World world : Bukkit.getServer().getWorlds()){
                    world.setTime(12000);
                }
                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NIGHT.getText());
            }
        }



        return true;
    }
}
