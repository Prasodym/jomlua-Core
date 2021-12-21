package de.jomlua.util.Inventory;


import de.jomlua.commands.Secure.BannUtils;
import de.jomlua.mysql.ReadSQL;
import de.jomlua.util.ItemBuilder;
import de.jomlua.util.PlayTimePlayers;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;


import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class CheckInv {

    public CheckInv(String uuid, CommandSender player) throws SQLException {

        Player pl = Bukkit.getPlayer(uuid);

        Player user = (Player) player;
        Player whoplayer = Bukkit.getPlayer(uuid);
        String isOnline; String seen; String timer;

        java.text.DecimalFormat formatMoney = new java.text.DecimalFormat( "##,###.00" );



        if (!(whoplayer == null)){
            isOnline = "§aOnline";
        } else{
            isOnline = "§cOffline";
        }

        if (!(whoplayer == null)){
            seen = "§nIst gerade Online";
        }else{
            seen = PlayTimePlayers.getLastPlay(uuid);
        }
        if (!(whoplayer ==null)){
            timer = ReadSQL.getOnlineTimeReal(uuid);
        }else{
            timer = ReadSQL.getOnlineTime(uuid);
        }




        Inventory inventory = Bukkit.createInventory(null, 54, "§6Infos von " + ReadSQL.getPlayername(uuid));
        Integer money = Math.toIntExact(ReadSQL.getMoney(uuid));

        /**
         * TODO: Slot 10, playerskull returned material.
         */
        inventory.setItem(10, PlayerSkull.itemFromName(ReadSQL.getPlayername(uuid)));


        inventory.setItem(12, new ItemBuilder(Material.COBWEB).setAmount(1).setName("§eUUID").setLore("§c" + uuid).build());
        inventory.setItem(14, new ItemBuilder(Material.ENDER_EYE).setAmount(1).setName("§eZuletzt gesehen").setLore("§c" + seen).build());
        inventory.setItem(16, new ItemBuilder(Material.CLOCK).setAmount(1).setName("§eOnlinezeit").setLore("§c" + timer).build());
        inventory.setItem(28, new ItemBuilder(Material.EMERALD).setAmount(1).setName("§eKontostand").setLore("§c" + formatMoney.format(money) + "§8 Blocks").build());

        //inventory.setItem(53, new ItemBuilder(Material.DIAMOND).setEnchant(Enchantment.DAMAGE_UNDEAD,10).setAmount(20).setLore("Exe").setName("EInfach ein Item").build());

        if (BannUtils.isBanned(uuid)){
            inventory.setItem(30, new ItemBuilder(Material.IRON_BARS).setName("§cGebannt von §7(§5" + BannUtils.getBanFrom(uuid) + "§7)").build());
            inventory.setItem(32, new ItemBuilder(Material.BOOK).setName("§6Grund").setLore("§c" + BannUtils.getReason(uuid)).build());
            inventory.setItem(34, new ItemBuilder(Material.COMPASS).setName("§6Gebannt bis").setLore("§c" + BannUtils.toEnd(uuid)).build());
            inventory.setItem(46, new ItemBuilder(Material.WITHER_SKELETON_SKULL).setName("§eTode").setLore("§c" + ReadSQL.getDeathCount(uuid)).build());

        }else{

            inventory.setItem(30, new ItemBuilder(Material.TOTEM_OF_UNDYING).setName("§cWurde nicht gebannt").setEnchant(Enchantment.SOUL_SPEED,10).build());
            inventory.setItem(32, new ItemBuilder(Material.WITHER_SKELETON_SKULL).setName("§eTode").setLore("§c" + ReadSQL.getDeathCount(uuid)).build());
        }


//        for (int i = 0; i < 54; i++){
//
//            ItemStack item = inventory.getItem(i);
//            if (item.getType() == Material.AIR){
//                inventory.setItem(i,new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).build());
//            }
//
//        }


        user.openInventory(inventory);
    }

    private ItemStack getPlayerSkull(String uuid){
        boolean isNewVersion = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");

        Material type = Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM");
        ItemStack item = new ItemStack(type,1);
        if (!isNewVersion){
            item.setDurability((short) 3);
        }
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(uuid);
        meta.setDisplayName(uuid);
        meta.setLore(Collections.singletonList("uuid"));
        return item;
    }



}
