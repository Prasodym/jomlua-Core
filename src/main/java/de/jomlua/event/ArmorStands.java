package de.jomlua.event;

import de.jomlua.util.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
// import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class ArmorStands implements Listener {

    @EventHandler
    public void onClickToSTand(PlayerInteractAtEntityEvent event){

        Player player = event.getPlayer();

        if (player.getInventory().getItemInMainHand().getType().equals(Material.FLINT)){
            if (event.getRightClicked().getType().equals(EntityType.ARMOR_STAND)){
                Inventory main_menue = Bukkit.createInventory(player, 54, "§cAr§fmorstand Menü");

                main_menue.setItem(45,createItem(Material.TNT, 1, "§aZurücksetzen", " §eErstelle einen Custom Armorstand"));
                main_menue.setItem(53,createItem(Material.BARRIER, 1, "§cSchlißen", " §eBearbeiten beenden."));

                main_menue.setItem(0, createItem(Material.RED_WOOL,1,"X-Achse"));
                main_menue.setItem(9, createItem(Material.RED_WOOL,1,"Y-Achse"));
                main_menue.setItem(18, createItem(Material.RED_WOOL,1,"Z-Achse"));

                main_menue.setItem(8, createItem(Material.GRAVEL,1,"Grob"));
                main_menue.setItem(17, createItem(Material.DIRT,1,"Fein"));
                main_menue.setItem(26, createItem(Material.SAND,1,"Filigran"));

                main_menue.setItem(3, createItem(Material.SPIDER_EYE,1,"Drehen"));
                main_menue.setItem(4, createItem(Material.LEGACY_SKULL_ITEM,1,"Kopf"));
                main_menue.setItem(5, createItem(Material.DIAMOND_BOOTS,1,"Bewegen"));
                main_menue.setItem(12, createItem(Material.STICK,1,"Linker Arm"));
                main_menue.setItem(13, createItem(Material.LEATHER_CHESTPLATE,1,"Körper"));
                main_menue.setItem(14, createItem(Material.STICK,1,"Rechter Arm"));

                main_menue.setItem(21, createItem(Material.LEATHER_BOOTS,1,"Linkes Bein"));
                main_menue.setItem(23, createItem(Material.LEATHER_BOOTS,1,"Rechtes Bein"));

                main_menue.setItem(37, createItem(Material.NETHER_STAR,1,"Bewegungs Menue"));
                main_menue.setItem(38, createItem(Material.STICK,1,"Arme"));
                main_menue.setItem(39, createItem(Material.GOLDEN_CARROT,1,"Größe"));
                main_menue.setItem(41, createItem(Material.STONE_SLAB,1,"Bodenplatte"));
                main_menue.setItem(42, createItem(Material.FEATHER,1,"Schwerkraft"));
                main_menue.setItem(43, createItem(Material.POTION,1,"Unsichtbar"));
                player.openInventory(main_menue);


            }
            return;
        }

    }


    private static ItemStack createItem(Material material, int amount, String displayname, String... lore) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        ArrayList<String> Lore = new ArrayList<String>();
        for (String loreString : lore)
            Lore.add(loreString);
        meta.setLore(Lore);
        item.setItemMeta(meta);
        return item;
    }
    private static ItemStack createItem(Material material, short data, int amount, String displayname, String... lore) {
        ItemStack item = new ItemStack(material, amount);
        item.setDurability(data);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        ArrayList<String> Lore = new ArrayList<String>();
        for (String loreString : lore)
            Lore.add(loreString);
        meta.setLore(Lore);
        item.setItemMeta(meta);
        return item;
    }


}
