package com.github.tckz916.blastadmin.command;

import com.github.tckz916.blastadmin.BlastAdmin;
import com.github.tckz916.blastadmin.api.BaseCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

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
                if (prefix.getKeys(false).contains(args[1])) {
                    plugin.getMessage().sendmessage(sender, format(false, "error.already-exists"));
                    return;
                }
                plugin.getConfig().set("prefix." + args[1], build(args, 2).replace("#s", " "));
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
                                .replace("%prefix%", coloring(name)));
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
                if (!prefix.getKeys(false).contains(args[1])) {
                    plugin.getMessage().sendmessage(sender, format(false, "error.already-prefix"));
                    return;
                }
                plugin.getConfig().set("prefix." + args[1], build(args, 2).replace("#s", " "));
                plugin.saveConfig();
                plugin.getMessage().sendmessage(sender,
                        format(true, "message.prefix.rename")
                                .replace("%prefix%", args[1]));
                break;
            case "reset":
                if (args.length < 2) {
                    plugin.getMessage().sendmessage(sender, coloring("&7- &b使い方: &7/prefix reset <player>"));
                    return;
                }
                if (getPlayer(args[1]) == null) {
                    plugin.getMessage().sendmessage(sender, format(false, "error.player-not-found"));
                    return;
                }
                getPlayer(args[1]).setDisplayName(getPlayer(args[1]).getName());
                setprefix(getPlayer(args[1]), "");
                plugin.getMessage().sendmessage(sender,
                        format(true, "message.prefix.reset")
                                .replace("%player%", getPlayer(args[1]).getName()));
                break;
            case "set":
                if (args.length < 3) {
                    plugin.getMessage().sendmessage(sender, coloring("&7- &b使い方: &7/prefix set <name> <player>"));
                    return;
                }
                if (!prefix.getKeys(false).contains(args[1])) {
                    plugin.getMessage().sendmessage(sender, format(false, "error.already-prefix"));
                    return;
                }
                if (getPlayer(args[2]) == null) {
                    plugin.getMessage().sendmessage(sender, format(false, "error.player-not-found"));
                    return;
                }
                String pn = plugin.getConfig().getString("prefix." + args[1]);
                getPlayer(args[2]).setDisplayName(coloring(pn + "&r" + getPlayer(args[2]).getName()));
                setprefix(getPlayer(args[2]), pn);

                plugin.getMessage().sendmessage(sender,
                        format(true, "message.prefix.set")
                                .replace("%prefix%", args[1])
                                .replace("%player%", getPlayer(args[2]).getName()));
                break;
            default:
                sendUsage();
                break;
        }
    }

    private void setprefix(Player player, String prefix) {
        String uuid = player.getUniqueId().toString();
        File players = new File(plugin.getDataFolder() + "\\players", uuid + ".yml");
        YamlConfiguration playersfile = YamlConfiguration.loadConfiguration(players);
        playersfile.set("prefix", prefix);
        try {
            playersfile.save(players);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String build(String[] strings, int start) {
        return plugin.getMessageFormat().build(strings, start);
    }

    private String format(boolean prefix, String key, Object... args) {
        return plugin.getMessageFormat().format(prefix, key, args);
    }

    private Player getPlayer(String name) {
        return plugin.getServer().getPlayer(name);
    }

    private String coloring(String msg) {
        return plugin.getMessageFormat().coloring(msg);
    }
}
