package com.github.tckz916.blastadmin.command.teleports;

import com.github.tckz916.blastadmin.BlastAdmin;
import com.github.tckz916.blastadmin.api.BaseCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by tckz916 on 2015/09/03.
 */
public class TeleportAllCommand extends BaseCommand {

    private BlastAdmin plugin = BlastAdmin.getInstance();

    public static final String NAME = "tpa";

    public static final String PERMISSION = "blastadmin.command.tp";

    public static final String DESCRIPTION = "Teleport Command";

    public static final String USAGE = "\n/tpa" +
            "\n/tpa <player>";

    public TeleportAllCommand(CommandSender sender) {
        super(sender, NAME, PERMISSION, DESCRIPTION, USAGE);
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if (!hasPermission()) {
            plugin.getMessage().sendmessage(sender, format(false, "error.no-permission"));
            return;
        }
        if (isSenderConsole()) {
            plugin.getMessage().sendmessage(sender, format(false, "error.console"));
            return;
        }

        if (isSenderRemoteConsole()) {
            plugin.getMessage().sendmessage(sender, format(false, "error.console"));
            return;
        }
        if (args.length > 2) {
            sendUsage();
            return;
        }
        Player player = (Player) sender;
        String message = null;

        switch (args.length) {
            case 0:
                for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                    players.teleport(player);
                }
                message = format(false, "message.teleport")
                        .replace("%from%", "OnlinePlayers")
                        .replace("%to%", player.getDisplayName());
                plugin.getMessage().broadcastmessage(message);
                break;
            case 1:
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    plugin.getMessage().sendmessage(sender, format(false, "error.player-not-found"));
                    return;
                }
                for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                    players.teleport(target);
                }
                message = format(false, "message.teleport")
                        .replace("%from%", "OnlinePlayers")
                        .replace("%to%", target.getDisplayName());

                plugin.getMessage().broadcastmessage(message);
                break;
        }
    }

    private String format(boolean prefix, String key, Object... args) {
        return plugin.getMessageFormat().format(prefix, key, args);
    }

}
