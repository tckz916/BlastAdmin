package com.github.tckz916.blastadmin.command.etc;

import com.github.tckz916.blastadmin.BlastAdmin;
import com.github.tckz916.blastadmin.api.BaseCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by tckz916 on 2015/09/03.
 */
public class WhoisCommand extends BaseCommand {

    private BlastAdmin plugin = BlastAdmin.getInstance();

    public static final String NAME = "whois";

    public static final String PERMISSION = "blastadmin.command.whois";

    public static final String DESCRIPTION = "Whois Command";

    public static final String USAGE = "\n/whois" +
            "\n/whois <player>";

    public WhoisCommand(CommandSender sender) {
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

        switch (args.length) {
            case 0:
                plugin.getMessage().sendmessage(sender, coloring("&7PlayerName: &b" + player.getName()));
                plugin.getMessage().sendmessage(sender, coloring("&7Language: &b" + player.spigot().getLocale()));
                plugin.getMessage().sendmessage(sender, coloring("&7Address: &b" + player.getAddress().getAddress().getHostAddress()));
                plugin.getMessage().sendmessage(sender, coloring("&7HostName: &b" + player.getAddress().getHostName()));
                plugin.getMessage().sendmessage(sender, coloring("&7RawAddress: &b" + player.spigot().getRawAddress()));
                break;
            case 1:
                String name = args[0];
                Player target = Bukkit.getPlayer(name);
                if(target == null){
                    plugin.getMessage().sendmessage(sender, format(false, "error.player-not-found"));
                    return;
                }
                plugin.getMessage().sendmessage(sender, coloring("&7PlayerName: &b" + name));
                plugin.getMessage().sendmessage(sender, coloring("&7Language: &b" + target.spigot().getLocale()));
                plugin.getMessage().sendmessage(sender, coloring("&7Address: &b" + target.getAddress().getAddress().getHostAddress()));
                plugin.getMessage().sendmessage(sender, coloring("&7HostName: &b" + target.getAddress().getHostName()));
                plugin.getMessage().sendmessage(sender, coloring("&7RawAddress: &b" + target.spigot().getRawAddress()));
                break;
        }


    }

    private String format(boolean prefix, String key, Object... args) {
        return plugin.getMessageFormat().format(prefix, key, args);
    }

    private String coloring(String msg) {
        return plugin.getMessageFormat().coloring(msg);
    }
}
