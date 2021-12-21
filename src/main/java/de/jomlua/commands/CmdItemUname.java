package de.jomlua.commands;

import de.jomlua.util.ChatOutput;
import de.jomlua.util.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdItemUname implements CommandExecutor {
    String Permission = "jomlua.itemuname";
    String Command = "iname";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player player = (Player) sender;

        if (!(player.hasPermission(Permission))){
            ChatUtils.sendMessageAtHex(player, ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            return true;
        }

        if (!(sender instanceof Player)){
            ChatUtils.sendMessageAtHex(player, ChatOutput.PREFIX.getText() + ChatOutput.NO_PLAYER.getText());
            return true;
        }

        if (cmd.getName().equalsIgnoreCase(Command)){
            if (args.length >= 1){
                String arguments = "";
                for (int i = 0; i < args.length; i++) {
                    arguments += args[i] + " ";
                }
                String item = player.getInventory().getItemInMainHand().getType().toString();

                ChatUtils.RenameItemInHand(player,arguments);
                ChatUtils.sendMessageAtHex(player, "&fDas Item &c" + item + " &fwurde umgenannt zu " + arguments+ "&f.");
            }else{
                ChatUtils.sendMessageAtHex(player, ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            }
        }
        return true;
    }
}
