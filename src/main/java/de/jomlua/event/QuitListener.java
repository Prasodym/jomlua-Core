package de.jomlua.event;

import de.jomlua.JomluaCore;
import de.jomlua.mysql.ReadSQL;
import de.jomlua.mysql.SaveSQL;
import de.jomlua.util.ChatOutput;
import de.jomlua.util.Inventory.SynInventory;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.function.IntFunction;
import java.util.logging.Level;

import static de.jomlua.JomluaCore.score;

public class QuitListener implements Listener {
//    private static File file = new File("plugins/JomluaCore","time.yml");
//    private static YamlConfiguration config = YamlConfiguration.loadConfiguration(file);



    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent qe) throws IOException {

        Player player = qe.getPlayer();
//        try {
//            SynInventory.getInvetory(player);
//            player.getInventory().clear();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        SaveSQL.LastPlaySaved(player.getUniqueId().toString());

        HashMap<String, String> replaces = new HashMap<>();
        replaces.put("%player%",player.getDisplayName());
        qe.setQuitMessage(ChatOutput.QUIT_MSG.getText(replaces));

        score.getTeam(player.getName()).unregister();

        QuitPlayerSaveData(player);


    }

    public static void QuitPlayerSaveData(Player player){
        try{
            long longMilis = JomluaCore.playerLogTimeMap.get(player);

            long quitMilis = System.currentTimeMillis();

            long addTime = (long) (quitMilis - longMilis) / 1000;
            long onlinetime = ReadSQL.getTimePlayer(player.getUniqueId().toString()) + addTime;
            SaveSQL.savePlayTime(player.getUniqueId().toString(),onlinetime);

            JomluaCore.playerLogTimeMap.remove(player);
        }catch (NullPointerException e){
            Bukkit.getLogger().info("Failed save onlinetimes, dount use /reload or /load");
        }
    }
}
