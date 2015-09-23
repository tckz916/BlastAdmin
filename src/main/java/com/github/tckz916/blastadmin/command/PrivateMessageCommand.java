package com.github.tckz916.blastadmin.command;

import com.github.tckz916.blastadmin.BlastAdmin;
import com.github.tckz916.blastadmin.api.BaseCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * Created by tckz916 on 2015/09/03.
 */
public class PrivateMessageCommand extends BaseCommand {

    private BlastAdmin plugin = BlastAdmin.getInstance();

    public static final String NAME = "tell";

    public static final String PERMISSION = "blastadmin.command.tell";

    public static final String DESCRIPTION = "PrivateMessage Command";

    public static final String USAGE = "/tell <player> <message>";

    public PrivateMessageCommand(CommandSender sender) {
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
        if (args.length < 2) {
            sendUsage();
            return;
        }

        Player player = (Player) sender;
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            plugin.getMessage().sendmessage(sender, format(false, "error.player-not-found"));
            return;
        }

        String msg = build(args, 1);

        String fromformat = plugin.getConfig().getString("privatemessage.fromformat")
                .replace("%player%", target.getDisplayName())
                .replace("%message%", msg);
        String toformat = plugin.getConfig().getString("privatemessage.toformat")
                .replace("%player%", player.getDisplayName())
                .replace("%message%", msg);
        plugin.getMessage().sendmessage(player, coloring(fromformat));
        plugin.getMessage().sendmessage(target, coloring(toformat));
        target.setMetadata("reply", new FixedMetadataValue(plugin, player));

    }

    private String format(boolean prefix, String key, Object... args) {
        return plugin.getMessageFormat().format(prefix, key, args);
    }

    private String coloring(String msg){
        return plugin.getMessageFormat().coloring(msg);
    }

    private String build(String[] strings, int start) {
        return plugin.getMessageFormat().build(strings, start);
    }
}
