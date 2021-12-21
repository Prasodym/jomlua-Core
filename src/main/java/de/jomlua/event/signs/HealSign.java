package de.jomlua.event.signs;


import de.jomlua.mysql.ReadSQL;
import de.jomlua.mysql.SaveSQL;
import de.jomlua.util.ChatOutput;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;




public class HealSign implements Listener {
//    int price = 500;


    @EventHandler
    public void PlayerSetSign(SignChangeEvent event){
        Player player = event.getPlayer();
        if(event.getLine(0).equalsIgnoreCase("[heal]")){
                if (!(event.getLine(1).isEmpty())){
                    event.setLine(0,"§9[heal]");
                    event.setLine(1,"§a"+ event.getLine(1) + " §3Blocks");
                }else {
                    event.setLine(0,"§9[heal]");
                    event.setLine(1,"§aGratis");
                }






        }
    }
    @EventHandler
    public void OnPlayerClickSign(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (event.getClickedBlock().getState() instanceof Sign){
                Sign s = (Sign) event.getClickedBlock().getState();
                if (s.getLine(0).equalsIgnoreCase("§9[heal]")){
                    if (s.getLine(1).equalsIgnoreCase("§aGratis")){

                            player.setHealth(20);
                            player.sendTitle("§a§lHeilung..","§cDu bist nun geheilt.",20,10,30);


                    }else{
                        String a = s.getLine(1);
                        a = ChatColor.stripColor(a);
                        String c = a.replace(" Blocks", "");
                        int b = Integer.parseInt(c);


                        if (!(ReadSQL.getMoney(player.getUniqueId().toString()) < b )){
                            player.setHealth(20);
                            player.sendTitle("§a§lHeilung..","§cDu bist nun geheilt.",20,10,30);
                            player.sendMessage(ChatOutput.PREFIXECO.getText() + "§fDu hast für §c" + b + "§f Blocks, dich geheilt.");
                            SaveSQL.setPay(player,b);
                        }else{
                            player.sendMessage(ChatOutput.PREFIXECO.getText() + "§cDu hast nicht genug Geld.");
                        }
                    }

                }
            }
        }
    }

}
