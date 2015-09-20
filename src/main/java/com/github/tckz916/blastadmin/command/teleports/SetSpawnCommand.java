package com.github.tckz916.blastadmin.command.teleports;

import com.github.tckz916.blastadmin.BlastAdmin;
import com.github.tckz916.blastadmin.api.BaseCommand;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by tckz916 on 2015/09/14.
 */
public class SetSpawnCommand extends BaseCommand {

    private BlastAdmin plugin = BlastAdmin.getInstance();

    public static final String NAME = "setspawn";

    public static final String PERMISSION = "blastadmin.command.setspawn";

    public static final String DESCRIPTION = "Teleport Command";

    public static final String USAGE = "/setspawn";

    public SetSpawnCommand(CommandSender sender) {
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


        String  world = player.getWorld().getName();

        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();
        double yaw = player.getLocation().getYaw();
        double pitch = player.getLocation().getPitch();

        plugin.getConfig().set("spawn.world", world);
        plugin.getConfig().set("spawn.x", String.valueOf(x));
        plugin.getConfig().set("spawn.y", String.valueOf(y));
        plugin.getConfig().set("spawn.z", String.valueOf(z));
        plugin.getConfig().set("spawn.yaw", String.valueOf(yaw));
        plugin.getConfig().set("spawn.pitch", String.valueOf(pitch));

        plugin.saveConfig();

        String message = format(true, "message.setspawn");

        plugin.getMessage().sendmessage(sender, message);


    }

    private String format(boolean prefix, String key, Object... args) {
        return plugin.getMessageFormat().format(prefix, key, args);
    }
}
