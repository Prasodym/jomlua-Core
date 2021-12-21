package de.jomlua.commands;

import de.jomlua.JomluaCore;
import de.jomlua.mysql.ReadSQL;
import de.jomlua.mysql.SaveSQL;
import de.jomlua.util.ChatOutput;
import de.jomlua.util.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CMDmoney implements CommandExecutor {
    private JomluaCore plugin = JomluaCore.getPlugin();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        final java.text.DecimalFormat twoDigits = new java.text.DecimalFormat( "##,###.00" );

         BigDecimal amountq = new BigDecimal( ReadSQL.getMoney(player.getUniqueId().toString()) ).setScale( 2, BigDecimal.ROUND_CEILING);

        if (command.getName().equalsIgnoreCase("money")){

            if (args.length == 3){

                if (args[0].equalsIgnoreCase("pay")){
                    Player target = Bukkit.getPlayer(args[1]);
                    //Long balance = Long.valueOf(args[2]);
                    Double balance = Double.valueOf(args[2]);



                    SaveSQL.setPay(player,  target, balance);


                }else if (args[0].equalsIgnoreCase("add")){
                    Player target = Bukkit.getPlayer(args[1]);
                    //Long balance = Long.valueOf(args[2]);
                    Double balance = Double.valueOf(args[2]);

                    SaveSQL.addPay(player,target,balance);
                }

            }

            if (args.length == 0){

                String amout = twoDigits.format(ReadSQL.getMoney(player.getUniqueId().toString()));

                HashMap<String, String> replacements = new HashMap<String, String>();
                replacements.put("%player%", player.getDisplayName());

                HashMap<String, String> rep1 = new HashMap<>();
                rep1.put("{0}", amout + "§7 Blocks");
                ChatUtils.sendMessageAtHex(player, ChatOutput.PREFIXECO.getText() + ChatOutput.ECO_AMOUNT.getText(replacements));
                ChatUtils.sendMessageAtHex(player, ChatOutput.ECO_AMOUNT1.getText(rep1));
            }


        }
        return true;
    }
    }
