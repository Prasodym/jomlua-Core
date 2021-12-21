package de.jomlua.event;



import com.vexsoftware.votifier.model.VotifierEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class VoteEvent implements Listener {

    public void onVote(VotifierEvent e){
        String user = e.getVote().getUsername();
        Player player = Bukkit.getPlayer(user);

        if (player != null){
            Bukkit.broadcastMessage("§a" + user + "Hat für uns gevotet.");
        }
    }
}
