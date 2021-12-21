package de.jomlua.commands;

import de.jomlua.util.ChatOutput;
import de.jomlua.util.ChatUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;



public class CMDIteminfo implements CommandExecutor {

    private Map<Enchantment,Integer> en = new HashMap<Enchantment,Integer>();
    private List<String> e = new ArrayList<String>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (sender instanceof Player){
            if (player.hasPermission("jomlua.iteminfo")){
                ItemStack itemStack = player.getInventory().getItemInMainHand();
                String item = player.getInventory().getItemInMainHand().getType().name().toLowerCase();
                int itemamount = player.getInventory().getItemInMainHand().getAmount();
                String colored = itemStack.getItemMeta().getDisplayName();



                ChatUtils.sendMessageAtHex(player, "&3Item&ainfo &7&l> Anzahl/Name: &f" +itemamount+ "&c " + item );
                ChatUtils.sendMessageAtHex(player, "&3Item&ainfo &7&l> Benennung: &f" + colored );
                ChatUtils.sendMessageAtHex(player, "&3Item&ainfo &7&l> Verzaubert: &f" + Test(itemStack));

            }
        }else {
            sender.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PLAYER.getText());
        }
               return false;
    }

//    public Map<Enchantment,Integer> getEncahntmentLevel(Player player) {
//
//        ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
//        return meta == null ? ImmutableMap.<Enchantment, Integer>of():meta.getEnchants();
//
//    }
    public String Test(ItemStack i) {
       for (Enchantment ec: en.keySet()) {
           e.add("§7" + ec.getName() + " §8 Lvl: §c" + en.get(ec));
       }
        return StringUtils.join(e, ",");
    }

   
}
