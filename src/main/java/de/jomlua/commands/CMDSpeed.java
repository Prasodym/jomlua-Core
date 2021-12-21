package de.jomlua.commands;

import de.jomlua.util.ChatOutput;
import de.jomlua.util.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDSpeed implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player player = (Player) sender;
        if (!(player.hasPermission("jomlua.speed"))){
            player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            return true;
        }
        if (!(sender instanceof Player)){
            player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PLAYER.getText());
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("speed")){
            if (args.length == 1){
                float eingabe = Float.parseFloat(args[0]);
                if (eingabe > 0.8){
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatUtils.setHexColor("&cBitte nehme rücksicht auf andere Spieler, das schelle Fliegen ist sehr Serverlastig"));
                    player.setFlySpeed(eingabe);
                    player.setWalkSpeed(eingabe);
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatUtils.setHexColor("&fDu hast deine geschwindigkeit erhöht auf &c" + eingabe));
                    return true;
                }
                try {
                    player.setFlySpeed(Float.parseFloat(args[0]));
                    player.setWalkSpeed(Float.parseFloat(args[0]));
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatUtils.setHexColor("&fDu hast deine geschwindigkeit erhöht auf &c" + args[0]));
                }catch (IllegalArgumentException e){
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatUtils.setHexColor("&fDieser wert &c" + args[0] + ", &fist zu hoch. Erlaubte werte: &e0.1 &fbis &e1.0"));
                }


            }else{
                player.setFlySpeed((float) 0.1);
                player.setWalkSpeed((float) 0.2);
                player.sendMessage(ChatOutput.PREFIX.getText() + ChatUtils.setHexColor("&fDeine geschwindigkeit ist wieder normal."));
            }
        }


        return true;
    }
}
