package de.jomlua.util.Inventory;

import de.jomlua.mysql.MySQL;
import de.jomlua.mysql.ReadSQL;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SynInventory {

    public SynInventory() {

    }

    public static void saveInventory(Player player){

        PlayerInventory inv = player.getInventory();
        String[] content = Base64Inventory.playerInventoryToBase64(inv);

        if (!(ReadSQL.isInventoryExists(player.getUniqueId().toString()))){
            try {

                PreparedStatement pdo = MySQL.getConnection().prepareStatement("INSERT INTO inventory (user, inventory, armor) VALUES (?,?,?)");
                pdo.setString(1, player.getUniqueId().toString());
                pdo.setString(2, content[0]);
                pdo.setString(3, content[1]);
                pdo.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            try {
                PreparedStatement pdo = MySQL.getConnection().prepareStatement("UPDATE inventory SET inventory = ?, armor = ? WHERE user = ?");
                pdo.setString(1, content[0]);
                pdo.setString(2, content[1]);
                pdo.setString(3, player.getUniqueId().toString());
                pdo.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



    }
    public static void getInvetory(Player player) throws IOException {
        ItemStack[] items = Base64Inventory.itemStackArrayFromBase64(ReadSQL.getPlayerInventory(player.getUniqueId().toString()));
        player.getInventory().setContents(items);
        player.sendMessage("§aInvenory Loaded.");
    }


}
