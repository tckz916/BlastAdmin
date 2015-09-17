package com.github.tckz916.blastadmin.command.teleports;

import com.github.tckz916.blastadmin.BlastAdmin;
import com.github.tckz916.blastadmin.api.BaseCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by tckz916 on 2015/09/03.
 */
public class TeleportCommand extends BaseCommand {

    private BlastAdmin plugin = BlastAdmin.getInstance();

    public static final String NAME = "tp";

    public static final String PERMISSION = "blastadmin.command.tp";

    public static final String DESCRIPTION = "Teleport Command";

    public static final String USAGE = "\n/tp <player>" +
            "\n/tp <player> <player>" +
            "\n/tp <x> <y> <z>" +
            "\n/tp <player> <x> <y> <z>" +
            "\n/tp <x> <y> <z> <yaw> <pitch>" +
            "\n/tp <player> <x> <y> <z> <yaw> <pitch>";

    public TeleportCommand(CommandSender sender) {
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

        if (args.length < 1 || args.length > 6) {
            sendUsage();
            return;
        }

        Player player = (Player) sender;
        Player target = null;
        String message = null;

        switch (args.length) {
            case 1:
                target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    plugin.getMessage().sendmessage(sender, format(false, "error.player-not-found"));
                    return;
                }
                player.teleport(target);
                message = format(false, "message.teleport")
                        .replace("%from%", player.getDisplayName())
                        .replace("%to%", target.getDisplayName());
                plugin.getMessage().sendmessage(player, message);
                plugin.getMessage().sendmessage(target, message);
                break;
            case 2:
                target = Bukkit.getPlayer(args[0]);
                Player to = Bukkit.getPlayer(args[1]);
                if (target == null || to == null) {
                    plugin.getMessage().sendmessage(sender, format(false, "error.player-not-found"));
                    return;
                }
                target.teleport(to);
                message = format(false, "message.teleport")
                        .replace("%from%", target.getDisplayName())
                        .replace("%to%", to.getDisplayName());
                plugin.getMessage().sendmessage(player, message);
                plugin.getMessage().sendmessage(target, message);
                plugin.getMessage().sendmessage(to, message);
                break;
            case 3:
                try {
                    World world = player.getWorld();
                    double x = Double.parseDouble(args[0]);
                    double y = Double.parseDouble(args[1]);
                    double z = Double.parseDouble(args[2]);
                    Location loc = new Location(world, x, y, z);
                    player.teleport(loc);
                    message = format(false, "message.teleport")
                            .replace("%from%", player.getDisplayName())
                            .replace("%to%", "" + x + ", " + y + ", " + z);
                    plugin.getMessage().sendmessage(player, message);
                    break;
                } catch (NumberFormatException e) {
                    sendUsage();
                    System.out.print(e);
                }
                break;
            case 4:
                try {
                    target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        plugin.getMessage().sendmessage(sender, format(false, "error.player-not-found"));
                        return;
                    }
                    World world = player.getWorld();
                    double x = Double.parseDouble(args[1]);
                    double y = Double.parseDouble(args[2]);
                    double z = Double.parseDouble(args[3]);
                    Location loc = new Location(world, x, y, z);
                    target.teleport(loc);
                    message = format(false, "message.teleport")
                            .replace("%from%", target.getDisplayName())
                            .replace("%to%", "" + x + ", " + y + ", " + z);
                    plugin.getMessage().sendmessage(player, message);
                    plugin.getMessage().sendmessage(target, message);
                    break;
                } catch (NumberFormatException e) {
                    sendUsage();
                    System.out.print(e);
                }
                break;
            case 5:
                try {
                    World world = player.getWorld();
                    double x = Double.parseDouble(args[0]);
                    double y = Double.parseDouble(args[1]);
                    double z = Double.parseDouble(args[2]);
                    double yaw = Double.parseDouble(args[3]);
                    double pitch = Double.parseDouble(args[4]);
                    Location loc = new Location(world, x, y, z, (float) yaw, (float) pitch);
                    player.teleport(loc);
                    message = format(false, "message.teleport")
                            .replace("%from%", player.getDisplayName())
                            .replace("%to%", "" + x + ", " + y + ", " + z + ", " + yaw + ", " + pitch);
                    plugin.getMessage().sendmessage(player, message);
                    break;
                } catch (NumberFormatException e) {
                    sendUsage();
                    System.out.print(e);
                }
                break;
            case 6:
                try {
                    target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        plugin.getMessage().sendmessage(sender, format(false, "error.player-not-found"));
                        return;
                    }
                    World world = player.getWorld();
                    double x = Double.parseDouble(args[1]);
                    double y = Double.parseDouble(args[2]);
                    double z = Double.parseDouble(args[3]);
                    double yaw = Double.parseDouble(args[4]);
                    double pitch = Double.parseDouble(args[5]);
                    Location loc = new Location(world, x, y, z, (float) yaw, (float) pitch);
                    target.teleport(loc);
                    message = format(false, "message.teleport")
                            .replace("%from%", target.getDisplayName())
                            .replace("%to%", "" + x + ", " + y + ", " + z + ", " + yaw + ", " + pitch);
                    plugin.getMessage().sendmessage(player, message);
                    plugin.getMessage().sendmessage(target, message);
                    break;
                } catch (NumberFormatException e) {
                    sendUsage();
                    System.out.print(e);
                }
                break;
        }
    }

    private String format(boolean prefix, String key, Object... args) {
        return plugin.getMessageFormat().format(prefix, key, args);
    }

}
