package com.github.tckz916.blastadmin.listener;

import com.github.tckz916.blastadmin.BlastAdmin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by tckz916 on 2015/09/03.
 */
public class PlayerListener implements Listener {

    private BlastAdmin plugin = BlastAdmin.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        boolean join = plugin.getConfig().getBoolean("spawn.join");
        if (join) {
            String world = plugin.getConfig().getString("spawn.world");

            double x = Double.parseDouble(plugin.getConfig().getString("spawn.x"));
            double y = Double.parseDouble(plugin.getConfig().getString("spawn.y"));
            double z = Double.parseDouble(plugin.getConfig().getString("spawn.z"));
            double yaw = Double.parseDouble(plugin.getConfig().getString("spawn.yaw"));
            double pitch = Double.parseDouble(plugin.getConfig().getString("spawn.pitch"));

            Location location = new Location(Bukkit.getWorld(world), x, y, z, (float) yaw, (float) pitch);

            player.teleport(location);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();
        if (player.hasMetadata("reply")) {
            player.removeMetadata("reply", plugin);
        }
    }
}
