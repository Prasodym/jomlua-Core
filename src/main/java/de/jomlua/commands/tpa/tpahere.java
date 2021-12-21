package de.jomlua.commands.tpa;

import de.jomlua.JomluaCore;
import de.jomlua.util.ChatOutput;
import de.jomlua.util.TeleportTyp;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class tpahere implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length==1){
            if (player.hasPermission("jomlua.tpa")){
                try {
                    Player target = Bukkit.getPlayer(args[0]);
                    JomluaCore.tpa.put(target.getUniqueId(), player.getUniqueId());
                    JomluaCore.tpType.put(target.getUniqueId(), TeleportTyp.HERE);
                    HashMap<String, String> replacements = new HashMap<String, String>();
                    replacements.put("%player%", player.getDisplayName());
                    target.sendMessage(ChatOutput.TPA_DENY_J.getText(replacements)); //r

                    TextComponent accept = new TextComponent();
                    accept.setText(" §7- §2[§aAkzeptieren§2]");

                    accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§aKlicke um die anfrage zu akzeptieren")));
                    accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/tpaccept"));

                    TextComponent deny = new TextComponent();
                    deny.setText("§4[§cAblehnen§4]");

                    deny.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§cKlicke um die anfrage abzulehnen")));
                    deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/tpadeny"));
                    TextComponent msg = new TextComponent();
                    TextComponent end = new TextComponent();
                    end.addExtra(" §7- ");
                    msg.setText(" §foder ");


                    accept.addExtra(msg);
                    accept.addExtra(deny);
                    deny.addExtra(end);

                    target.spigot().sendMessage(accept);

                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.TPA_DENY_G.getText()); //Verschickt
                }catch (Exception e){
                    HashMap<String, String> replacements = new HashMap<String, String>();
                    replacements.put("%target%", args[0]);
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.TPA_DENY_H.getText(replacements)); //r Args0
                }
            }

        }else{
            player.sendMessage("§a- §e/tpa <Player>");
        }

        return false;
    }
}
