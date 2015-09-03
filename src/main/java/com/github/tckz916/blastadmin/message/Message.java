package com.github.tckz916.blastadmin.message;

import com.github.tckz916.blastadmin.BlastAdmin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by tckz916 on 2015/09/02.
 */
public class Message {

    private BlastAdmin plugin = BlastAdmin.getInstance();

    public void sendmessage(CommandSender sender, String msg) {
        sender.sendMessage(msg);
    }

    public void sendmessage(Player player, String msg) {
        player.sendMessage(msg);
    }

    public void broadcastmessage(boolean prefix, String msg) {
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            sendmessage(player, msg);
        }
    }

    private String prefix(boolean prefix, String msg) {
        return plugin.getMessageFormat().coloring(plugin.getMessageFormat().getMessageFile().get("prefix") + msg);
    }


}
