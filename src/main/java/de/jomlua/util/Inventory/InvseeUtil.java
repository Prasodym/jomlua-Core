package de.jomlua.util.Inventory;

import de.jomlua.util.ChatOutput;
import de.jomlua.util.ChatUtils;
import de.jomlua.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class InvseeUtil implements Listener {
    public InvseeUtil() {
    }

    private static HashMap<String, ItemStack[]> invtarget = new HashMap<>();
    private static HashMap<String, String> user = new HashMap<>();
    private static HashMap<String, ItemStack[]> invbefore = new HashMap<>();

    private static String title(String player){
        return ChatUtils.setHexColor("&a" + player + "&a`s &3 Spiele Inventar");
    }

    public static Inventory openInv(Player target, Player player){

        invtarget.put(target.getName(), target.getInventory().getContents());

        Inventory inv = Bukkit.createInventory(target , 9*5, title(target.getName()));

        inv.setContents(invtarget.get(target.getName()));
        inv.setItem(44, new ItemBuilder(Material.COOKED_BEEF).setAmount(1).setName("§aHunger").setLore("§c" + target.getFoodLevel()/2 + " §eHungerkeulen").build());
        inv.setItem(43, new ItemBuilder(Material.APPLE).setName("&aLeben").setLore("§a" + Math.floor(target.getHealth())/2 + " §eLeben").build());
        user.put(player.getName(), target.getName());

        return inv;
    }

    @EventHandler
    public void onClosInv(InventoryCloseEvent event){

        Player player = (Player) event.getPlayer();
        String name = user.get(player.getName());

        try {
            Player target = Bukkit.getPlayerExact(name);
            if (event.getView().getTitle().equalsIgnoreCase(title(target.getName()))){
                user.remove(player.getName());
                invtarget.remove(name);

                invbefore.put(name, event.getInventory().getContents());

                player.sendMessage("true");
                event.getView().close();
                target.getInventory().setContents(invbefore.get(target.getName()));

            }
        }catch (IllegalArgumentException e){
            Bukkit.getLogger().warning(ChatOutput.WARNING.getText() + "§cIllegalArgumentException!");
        }


    }
}
