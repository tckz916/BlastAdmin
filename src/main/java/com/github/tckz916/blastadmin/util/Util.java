package com.github.tckz916.blastadmin.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by tckz916 on 2015/07/20.
 */
public class Util {

    public Player getPlayer(String name) {
        Player player = Bukkit.getServer().getPlayer(name);
        if (player == null) {
            return null;
        }
        return player;
    }

}
