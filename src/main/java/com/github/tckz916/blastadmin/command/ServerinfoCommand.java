package com.github.tckz916.blastadmin.command;

import com.github.tckz916.blastadmin.BlastAdmin;
import com.github.tckz916.blastadmin.api.BaseCommand;
import com.sun.management.OperatingSystemMXBean;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.lang.management.ManagementFactory;
import java.math.BigDecimal;

/**
 * Created by tckz916 on 2015/09/04.
 */
public class ServerInfoCommand extends BaseCommand {

    private BlastAdmin plugin = BlastAdmin.getInstance();

    public static final String NAME = "serverinfo";

    public static final String PERMISSION = "blastadmin.command.serverinfo";

    public static final String DESCRIPTION = "ServerInfo Command";

    public static final String USAGE = "/serverinfo";

    public ServerInfoCommand(CommandSender sender) {
        super(sender, NAME, PERMISSION, DESCRIPTION, USAGE);
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if (!hasPermission()) {
            plugin.getMessage().sendmessage(sender, format(false, "error.no-permission"));
            return;
        }
        if (args.length > 0) {
            sendUsage();
            return;
        }

        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        double scl = osmxb.getSystemCpuLoad() * 100;
        BigDecimal scl1 = new BigDecimal(scl);
        BigDecimal scl2 = scl1.setScale(1, BigDecimal.ROUND_HALF_UP);


        plugin.getMessage().sendmessage(sender, coloring("&7CPU使用率: &b" + scl2.doubleValue() + "&r%"));
        plugin.getMessage().sendmessage(sender, coloring("&7Online Player: &b" + plugin.getServer().getOnlinePlayers().size()));
        plugin.getMessage().sendmessage(sender, coloring("&7Max Player: &b" + plugin.getServer().getMaxPlayers()));
        plugin.getMessage().sendmessage(sender, coloring("&7Server Version: &b" + plugin.getServer().getVersion()));
        plugin.getMessage().sendmessage(sender, coloring("&7Bukkit Version: &b" + plugin.getServer().getBukkitVersion()));

    }

    private String format(boolean prefix, String key, Object... args) {
        return plugin.getMessageFormat().format(prefix, key, args);
    }

    private String coloring(String msg) {
        return plugin.getMessageFormat().coloring(msg);
    }
}
