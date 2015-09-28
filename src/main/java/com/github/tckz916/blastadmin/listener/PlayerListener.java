package com.github.tckz916.blastadmin.listener;

import com.github.tckz916.blastadmin.BlastAdmin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;

/**
 * Created by tckz916 on 2015/09/03.
 */
public class PlayerListener implements Listener {

    private BlastAdmin plugin = BlastAdmin.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();
        File players = new File(plugin.getDataFolder() + "\\players", uuid + ".yml");
        YamlConfiguration playersFile = YamlConfiguration.loadConfiguration(players);
        if (!players.exists()) {
            try {
                playersFile.set("name", player.getName());
                playersFile.set("prefix", "");
                playersFile.save(players);
            } catch (IOException ex) {
            }
        }

        if (!playersFile.getString("name").equals(player.getName())) {
            playersFile.set("name", player.getName());
        }

        //prefix関連
        String prefix = playersFile.getString("prefix");
        player.setDisplayName(coloring(prefix + "&r" + player.getName()));

        //join時にsetspawn地点にteleportするかしないか
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

        String joinmessage = plugin.getConfig().getString("joinmessage").replace("%player%", player.getDisplayName());
        event.setJoinMessage(coloring(joinmessage));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();
        if (player.hasMetadata("reply")) {
            player.removeMetadata("reply", plugin);
        }

        String quitmessage = plugin.getConfig().getString("quitmessage").replace("%player%", player.getDisplayName());
        event.setQuitMessage(coloring(quitmessage));
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        plugin.reloadConfig();

        Player player = event.getPlayer();

        String messageformat = plugin.getConfig().getString("messageformat")
                .replace("%player%", player.getDisplayName())
                .replace("%message%", "%2$s");

        event.setFormat(coloring(messageformat));

    }

    private String format(boolean prefix, String key, Object... args) {
        return plugin.getMessageFormat().format(prefix, key, args);
    }

    private String coloring(String msg) {
        return plugin.getMessageFormat().coloring(msg);
    }
}
