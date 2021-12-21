package de.jomlua.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class getSelectPos {
    public static HashMap<Player, Location> Location1 = new HashMap<Player, Location>();
    public static HashMap<Player, Location> Location2 = new HashMap<Player, Location>();

    public static void setBlock(Player player, String str){

        Material id = Material.valueOf(str);

        if (Location1.containsKey(player) && Location2.containsKey(player)){
            for (Location loc : getSelect(Location1.get(player), Location2.get(player))){
                loc.getBlock().setType(id,true);


            }
            player.sendMessage(ChatOutput.PREFIX.getText() + "§dBlöcke wurden erfolgreich gesetzt.");
        }else{
            player.sendMessage(ChatOutput.PREFIX.getText() + "§dMarkiere erst deine region.");
        }
    }

    public static List<Location> getSelect(Location pos1, Location pos2){
        int xTop = 0;
        int xBottom = 0;
        int yTop = 0;
        int yBottom = 0;
        int zTop = 0;
        int zBottom = 0;

        List<Location> pos = new ArrayList<Location>();

        if (pos1.getBlockY() >= pos2.getBlockY()){
            yTop = pos1.getBlockY();
            yBottom = pos2.getBlockY();
        }else{
            yTop = pos2.getBlockY();
            yBottom = pos1.getBlockY();
        }

        if (pos1.getBlockX() >= pos2.getBlockX()){
            xTop = pos1.getBlockX();
            xBottom = pos2.getBlockX();
        }else{
            xTop = pos2.getBlockX();
            xBottom = pos1.getBlockX();
        }

        if (pos1.getBlockZ() >= pos2.getBlockZ()){
            zTop = pos1.getBlockZ();
            zBottom = pos2.getBlockZ();
        }else{
            zTop = pos2.getBlockZ();
            zBottom = pos1.getBlockZ();
        }

        for (int x = xBottom; x < xTop; x++){
            for (int y = yBottom; y < yTop; y++){
                for (int z = zBottom; z < zTop; z++){
                    pos.add(new Location(pos1.getWorld(), x, y, z));
                }
            }
        }
        return pos;
    }
}
