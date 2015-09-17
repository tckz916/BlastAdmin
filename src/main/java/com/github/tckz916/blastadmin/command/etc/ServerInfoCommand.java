package com.github.tckz916.blastadmin.command.etc;

import com.github.tckz916.blastadmin.BlastAdmin;
import com.github.tckz916.blastadmin.api.BaseCommand;
import com.sun.management.OperatingSystemMXBean;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

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

        String ip = null;
        try {
            ip = getIp();
        } catch (IOException e) {
            e.printStackTrace();
        }

        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        double scl = osmxb.getSystemCpuLoad() * 100;
        BigDecimal scl1 = new BigDecimal(scl);
        BigDecimal scl2 = scl1.setScale(1, BigDecimal.ROUND_HALF_UP);


        plugin.getMessage().sendmessage(sender, coloring("&7CPU Usage: &b" + scl2.doubleValue() + "&r%"));
        plugin.getMessage().sendmessage(sender, coloring("&7Global IP: &b" + ip));
        plugin.getMessage().sendmessage(sender, coloring("&7Online Player: &b" + plugin.getServer().getOnlinePlayers().size()));
        plugin.getMessage().sendmessage(sender, coloring("&7Max Player: &b" + plugin.getServer().getMaxPlayers()));
        plugin.getMessage().sendmessage(sender, coloring("&7Server Version: &b" + plugin.getServer().getVersion()));
        plugin.getMessage().sendmessage(sender, coloring("&7Bukkit Version: &b" + plugin.getServer().getBukkitVersion()));

    }

    private String getIp() throws IOException {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            String ip = in.readLine();
            return ip;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String format(boolean prefix, String key, Object... args) {
        return plugin.getMessageFormat().format(prefix, key, args);
    }

    private String coloring(String msg) {
        return plugin.getMessageFormat().coloring(msg);
    }
}
