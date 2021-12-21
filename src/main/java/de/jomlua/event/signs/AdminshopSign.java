package de.jomlua.event.signs;

import de.jomlua.event.ArmorStands;
import de.jomlua.mysql.ReadSQL;
import de.jomlua.mysql.SaveSQL;
import de.jomlua.util.ChatOutput;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

import static de.jomlua.JomluaCore.plugin;

public class AdminshopSign implements Listener {


    @EventHandler
    private void SignChanged(SignChangeEvent e){
        Player player = e.getPlayer();
        String[] line = e.getLines();






        if (player.hasPermission("jomlua.sign.shop")){
            if (line[0].equalsIgnoreCase("shop")){
                if (!(line[1].isEmpty())){
                    if (!(line[2].isEmpty())){
                        if (!(line[3].isEmpty())){
                            e.setLine(0,"§9" + player.getName());
                            e.setLine(1, e.getLine(1));
                            e.setLine(2, "&a" + e.getLine(2));
                            e.setLine(3, e.getLine(3));
                        }else{
                            player.sendMessage(ChatOutput.PREFIX.getText() + "Du musst einen Preis angeben.");
                            e.setCancelled(true);
                        }
                    }else{
                        player.sendMessage(ChatOutput.PREFIX.getText() + "Du musst das Item mit angeben");
                        e.setCancelled(true);
                    }

                }else{
                    player.sendMessage(ChatOutput.PREFIX.getText() + "Du musst die anzahl mit angeben");
                    e.setCancelled(true);
                }

            }
        }else{
            player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            e.setCancelled(true);
        }

    }



    @EventHandler
    public void OnPlayerClickSign(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (player.hasPermission("jomlua.sign.warp")){
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
                if (event.getClickedBlock().getState() instanceof Sign){
                    Sign s = (Sign) event.getClickedBlock().getState();
                    if (s.getLine(0).equalsIgnoreCase( ChatColor.BLUE + player.getName())){
                        String a = s.getLine(1);
                        String b = s.getLine(2);
                        String bb = ChatColor.stripColor(b);

                        int money = Integer.parseInt(s.getLine(3));


                       // if (!(ReadSQL.getMoney(player.getUniqueId().toString()) < money )){
                            //player.getInventory().addItem(new ItemStack(Material.getMaterial(bb),Integer.parseInt(a)));
                            player.getInventory().removeItem(new ItemStack(Material.valueOf(bb),Integer.parseInt(a)));
                            SaveSQL.addPay(player,money);

                            player.sendMessage(ChatOutput.PREFIXECO.getText() + "§cDu hast dir " + a + " " + bb +" gekauft.");
//                        }else{
//                            player.sendMessage("§7[§cE§fco§7] §cDu hast nicht genug Geld.");
//                        }



                    }
                }
            }
        } else{
            player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
        }
    }


    public static final Pattern[][] SHOP_SIGN_PATTERN = {
            { Pattern.compile("^?[\\w \\-.:]*$") },
            { Pattern.compile("^[1-9][0-9]{0,5}$"), Pattern.compile("^Q [1-9][0-9]{0,4} : C [0-9]{0,5}$") },
            {
                    Pattern.compile("(?i)^((\\d*([.e]\\d+)?)|free)$"),
                    Pattern.compile("(?i)^([BS] *((\\d*([.e]\\d+)?)|free))( *: *([BS] *((\\d*([.e]\\d+)?)|free)))?$"),
                    Pattern.compile("(?i)^(((\\d*([.e]\\d+)?)|free) *[BS])( *: *([BS] *((\\d*([.e]\\d+)?)|free)))?$"),
                    Pattern.compile("(?i)^(((\\d*([.e]\\d+)?)|free) *[BS]) *: *(((\\d*([.e]\\d+)?)|free) *[BS])$"),
                    Pattern.compile("(?i)^([BS] *((\\d*([.e]\\d+)?)|free)) *: *(((\\d*([.e]\\d+)?)|free) *[BS])$"),
            },
            { Pattern.compile("^[\\w? #:\\-]+$") }
    };




}
