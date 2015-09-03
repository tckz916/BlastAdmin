package com.github.tckz916.blastadmin.listener;

import com.github.tckz916.blastadmin.BlastAdmin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by tckz916 on 2015/09/03.
 */
public class PlayerListener implements Listener {

    private BlastAdmin plugin = BlastAdmin.getInstance();

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (player.hasMetadata("reply")) {
            player.removeMetadata("reply", plugin);
        }
    }
}
