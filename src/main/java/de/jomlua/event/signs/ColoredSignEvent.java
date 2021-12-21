package de.jomlua.event.signs;

import de.jomlua.util.ChatOutput;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;


public class ColoredSignEvent implements Listener {

    @EventHandler
    public void ClorSign(SignChangeEvent e){
       if (e.getPlayer().hasPermission("jomlua.sign.color")){
           for (int i = 0; i < 4; i++){
               String line = e.getLine(i);
               if(line != null && !line.equals("")){
                   e.setLine(i, ChatColor.translateAlternateColorCodes('&',line));
               }
           }
       } else {
           System.out.println(ChatOutput.PREFIXC.getText() + "§7" + e.getPlayer().getDisplayName() + " §aHat gerade versucht ein Farbschild zu setzen.");
       }

    }


}
