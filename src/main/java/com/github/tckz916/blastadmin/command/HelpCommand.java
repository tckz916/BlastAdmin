package com.github.tckz916.blastadmin.command;

import com.github.tckz916.blastadmin.BlastAdmin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

/**
 * Created by tckz916 on 2015/09/03.
 */
public class HelpCommand extends BaseCommand {

    private BlastAdmin plugin = BlastAdmin.getInstance();

    public static final String NAME = "help";

    public static final String DESCRIPTION = "Help Command";

    public static final String PERMISSION = "blastadmin.command.help";

    public static final String USAGE = "/blastadmin";

    public HelpCommand(CommandSender sender) {
        super(sender, NAME, PERMISSION, DESCRIPTION, USAGE);
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {

        if (!hasPermission()) {
            plugin.getMessage().sendmessage(false, sender, "error.no-permission");
            return;
        }

        ConfigurationSection help = plugin.getMessageFormat().getMessageFile().getCfg().getConfigurationSection("help");

        for (String s : help.getKeys(false)) {
            if (sender.hasPermission("blastadmin.command." + s) || s.equalsIgnoreCase("header")) {
                plugin.getMessage().sendmessage(false, sender, help.getName() + "." + s);
            }
        }

    }

}
