package com.github.tckz916.blastadmin.command.teleports;

import com.github.tckz916.blastadmin.BlastAdmin;
import com.github.tckz916.blastadmin.api.BaseCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by tckz916 on 2015/09/13.
 */
public class SpawnCommand extends BaseCommand {

    private BlastAdmin plugin = BlastAdmin.getInstance();

    public static final String NAME = "spawn";

    public static final String PERMISSION = "blastadmin.command.spawn";

    public static final String DESCRIPTION = "Teleport Command";

    public static final String USAGE = "/spawn";

    public SpawnCommand(CommandSender sender) {
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
        if (args.length > 0) {
            sendUsage();
            return;
        }

        Player player = (Player) sender;

        String world = plugin.getConfig().getString("spawn.world");

        double x = Double.parseDouble(plugin.getConfig().getString("spawn.x"));
        double y = Double.parseDouble(plugin.getConfig().getString("spawn.y"));
        double z = Double.parseDouble(plugin.getConfig().getString("spawn.z"));
        double yaw = Double.parseDouble(plugin.getConfig().getString("spawn.yaw"));
        double pitch = Double.parseDouble(plugin.getConfig().getString("spawn.pitch"));

        Location location = new Location(Bukkit.getWorld(world), x, y, z, (float) yaw, (float) pitch);

        player.teleport(location);

        String message = format(false, "message.spawn");

        plugin.getMessage().sendmessage(sender, message);

    }

    private String format(boolean prefix, String key, Object... args) {
        return plugin.getMessageFormat().format(prefix, key, args);
    }
}
