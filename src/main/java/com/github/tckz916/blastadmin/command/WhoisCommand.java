package com.github.tckz916.blastadmin.command;

import com.github.tckz916.blastadmin.BlastAdmin;
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

        if (args.length > 2) {
            sendUsage();
            return;
        }

        Player player = (Player) sender;

        switch (args.length) {
            case 0:
                plugin.getMessage().sendmessage(sender, coloring("&7Player: &b" + player.getName()));
                plugin.getMessage().sendmessage(sender, coloring("&7Lang: &b" + player.spigot().getLocale()));
                plugin.getMessage().sendmessage(sender, coloring("&7Address: &b" + player.getAddress().getAddress().getHostAddress()));
                plugin.getMessage().sendmessage(sender, coloring("&7Host: &b" + player.getAddress().getHostName()));
                plugin.getMessage().sendmessage(sender, coloring("&7RawAddress(Proxy): &b" + player.spigot().getRawAddress()));
                break;
            case 1:
                try {
                    String name = args[0];
                    Player target = Bukkit.getPlayer(name);
                    plugin.getMessage().sendmessage(sender, coloring("&7Player: &b" + name));
                    plugin.getMessage().sendmessage(sender, coloring("&7Lang: &b" + target.spigot().getLocale()));
                    plugin.getMessage().sendmessage(sender, coloring("&7Address: &b" + target.getAddress().getAddress().getHostAddress()));
                    plugin.getMessage().sendmessage(sender, coloring("&7Host: &b" + target.getAddress().getHostName()));
                    plugin.getMessage().sendmessage(sender, coloring("&7RawAddress(Proxy): &b" + target.spigot().getRawAddress()));
                    break;
                } catch (NullPointerException e) {
                    sendUsage();
                    System.out.print(e);
                }
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
