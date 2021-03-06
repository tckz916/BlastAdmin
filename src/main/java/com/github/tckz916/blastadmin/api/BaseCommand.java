package com.github.tckz916.blastadmin.api;

import com.github.tckz916.blastadmin.BlastAdmin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Created by tckz916 on 2015/09/03.
 */
public abstract class BaseCommand {

    private BlastAdmin plugin = BlastAdmin.getInstance();

    private final CommandSender sender;

    private final String name;

    private final String permission;

    private final String description;

    private final String usage;

    public BaseCommand(CommandSender sender, String name, String permission, String description, String usage) {
        this.sender = sender;
        this.name = name;
        this.permission = permission;
        this.description = description;
        this.usage = usage;
    }

    public CommandSender getSender() {
        return sender;
    }

    public String getName() {
        return name;
    }

    public String getPermmison() {
        return permission;
    }

    public String getDescription() {
        return description;
    }

    public String getUsage() {
        return usage;
    }

    public boolean hasPermission() {
        return sender.hasPermission(permission);
    }

    public void sendUsage() {
        plugin.getMessage().sendmessage(sender, format(false, "error.usage.command", name));
        plugin.getMessage().sendmessage(sender, format(false, "error.usage.description", description));
        plugin.getMessage().sendmessage(sender, format(false, "error.usage.usage", usage));
    }

    public boolean isSenderPlayer() {
        return (sender instanceof Player);
    }

    public boolean isSenderConsole() {
        return (sender instanceof ConsoleCommandSender);
    }

    public boolean isSenderRemoteConsole() {
        return (sender instanceof RemoteConsoleCommandSender);
    }


    public abstract void execute(CommandSender sender, Command command, String label, String[] args);

    private String format(boolean prefix, String key, Object... args) {
        return plugin.getMessageFormat().format(prefix, key, args);
    }
}
