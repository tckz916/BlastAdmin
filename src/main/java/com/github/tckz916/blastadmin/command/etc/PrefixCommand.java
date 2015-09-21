package com.github.tckz916.blastadmin.command.etc;

import com.github.tckz916.blastadmin.BlastAdmin;
import com.github.tckz916.blastadmin.api.BaseCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

/**
 * Created by tckz916 on 2015/09/22.
 */
public class PrefixCommand extends BaseCommand {

    private BlastAdmin plugin = BlastAdmin.getInstance();

    public static final String NAME = "prefix";

    public static final String PERMISSION = "blastadmin.command.prefix";

    public static final String DESCRIPTION = "Prefix Command";

    public static final String USAGE = "/prefix <add||delete||get||list||rename||set>";

    public PrefixCommand(CommandSender sender) {
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
        if (args.length < 1) {
            sendUsage();
            return;
        }

        plugin.reloadConfig();

        ConfigurationSection prefix = plugin.getConfig().getConfigurationSection("prefix");

        switch (args[0]) {
            case "add":
                if (args.length < 3) {
                    plugin.getMessage().sendmessage(sender, coloring("&7- &b使い方: &7/prefix add <name> <prefix>"));
                    return;
                }
                for (String s : prefix.getKeys(false)) {
                    if (s.equals(args[1])) {
                        plugin.getMessage().sendmessage(sender, format(false, "error.already-exists"));
                        return;
                    }
                }
                plugin.getConfig().set("prefix." + args[1], coloring(build(args, 2)).replace("#s", " "));
                plugin.saveConfig();
                plugin.getMessage().sendmessage(sender, format(true, "message.prefix.add").replace("%prefix%", args[1]));
                break;
            case "delete":
                if (args.length < 2) {
                    plugin.getMessage().sendmessage(sender, coloring("&7- &b使い方: &7/prefix delete <name>"));
                    return;
                }
                plugin.getConfig().set("prefix." + args[1], null);
                plugin.saveConfig();
                plugin.getMessage().sendmessage(sender, format(true, "message.prefix.delete").replace("%prefix%", args[1]));
                break;
            case "get":
                if (args.length < 2) {
                    plugin.getMessage().sendmessage(sender, coloring("&7- &b使い方: &7/prefix get <name>"));
                    return;
                }
                String name = plugin.getConfig().getString("prefix." + args[1]);
                plugin.getMessage().sendmessage(sender,
                        format(true, "message.prefix.get", args[1])
                                .replace("%prefix%", name));
                break;
            case "list":
                if (args.length > 1) {
                    plugin.getMessage().sendmessage(sender, coloring("&7- &b使い方: &7/prefix list"));
                    return;
                }
                plugin.getMessage().sendmessage(sender, coloring("&7------- [&b Prefix List &7] -------"));

                for (String s : prefix.getKeys(false)) {
                    plugin.getMessage().sendmessage(sender,
                            format(false, "message.prefix.list", s)
                                    .replace("%prefix%", coloring(plugin.getConfig().getString(prefix.getName() + "." + s))));
                }
                plugin.getMessage().sendmessage(sender, format(false, "message.prefix.help"));
                break;
            case "rename":
                if (args.length < 3) {
                    plugin.getMessage().sendmessage(sender, coloring("&7- &b使い方: &7/prefix rename <name> <prefix>"));
                    return;
                }
                plugin.getConfig().set("prefix." + args[1], coloring(build(args, 2)).replace("%space%", " "));
                plugin.saveConfig();
                plugin.getMessage().sendmessage(sender, format(true, "message.prefix.rename").replace("#s", args[1]));
                break;
            case "set":
                if (args.length < 3) {
                    plugin.getMessage().sendmessage(sender, coloring("&7- &b使い方: &7/prefix set <name> <player>"));
                    return;
                }
                Player player = (Player) sender;
                Object pn = plugin.getConfig().get("prefix." + args[1]);
                player.setDisplayName(coloring(pn + "&r" + player.getName()));
                plugin.getMessage().sendmessage(sender,
                        format(true, "message.prefix.set")
                                .replace("%prefix%", args[1])
                                .replace("%player%", player.getName()));
                break;
            default:
                sendUsage();
                break;
        }
    }

    private String build(String[] strings, int start) {
        return plugin.getMessageFormat().build(strings, start);
    }

    private String format(boolean prefix, String key, Object... args) {
        return plugin.getMessageFormat().format(prefix, key, args);
    }

    private String coloring(String msg) {
        return plugin.getMessageFormat().coloring(msg);
    }
}
