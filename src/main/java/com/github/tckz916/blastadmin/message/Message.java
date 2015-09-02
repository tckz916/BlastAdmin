package com.github.tckz916.blastadmin.message;

import com.github.tckz916.blastadmin.BlastAdmin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by tckz916 on 2015/09/02.
 */
public class Message {

    private BlastAdmin plugin = BlastAdmin.getInstance();

    public void sendmessage(boolean prefix, CommandSender sender, String key, Object... args) {
        sender.sendMessage(plugin.getMessageFormat().format(prefix, key, args));
    }

    public void sendmessage(boolean prefix, Player player, String key, Object... args) {
        player.sendMessage(plugin.getMessageFormat().format(prefix, key, args));
    }

    public void sendmessage(CommandSender sender, String msg) {
        sender.sendMessage(plugin.getMessageFormat().coloring(msg));
    }

    public void sendmessage(Player player, String msg) {
        player.sendMessage(plugin.getMessageFormat().coloring(msg));
    }

    public void broadcastmessage(boolean prefix, String msg) {
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            sendmessage(prefix, player, msg);
        }
    }



}
