package com.github.tckz916.blastadmin.command;

import com.github.tckz916.blastadmin.BlastAdmin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by tckz916 on 2015/09/03.
 */
public class TeleportHereCommand extends BaseCommand {

    private BlastAdmin plugin = BlastAdmin.getInstance();

    public static final String NAME = "tphere";

    public static final String PERMISSION = "blastadmin.command.tp";

    public static final String DESCRIPTION = "Teleport Command";

    public static final String USAGE = "/tphere <player>";

    public TeleportHereCommand(CommandSender sender) {
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
        if (args.length < 1 || args.length > 2) {
            sendUsage();
            return;
        }

        Player player = (Player) sender;

        try {
            Player target = Bukkit.getPlayer(args[0]);
            target.teleport(player);
        } catch (NullPointerException e) {
            sendUsage();
            System.out.print(e);
        }
    }

    private String format(boolean prefix, String key, Object... args) {
        return plugin.getMessageFormat().format(prefix, key, args);
    }
}
