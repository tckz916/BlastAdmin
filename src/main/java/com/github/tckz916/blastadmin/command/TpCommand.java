package com.github.tckz916.blastadmin.command;

import com.github.tckz916.blastadmin.BlastAdmin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by tckz916 on 2015/09/03.
 */
public class TpCommand extends BaseCommand {

    private BlastAdmin plugin = BlastAdmin.getInstance();

    public static final String NAME = "Tp";

    public static final String PERMISSION = "blastadmin.command.tp";

    public static final String DESCRIPTION = "TP Command";

    public static final String USAGE = "/tp <player> | /tp <player> <player>";

    public TpCommand(CommandSender sender) {
        super(sender, NAME, PERMISSION, DESCRIPTION, USAGE);
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if (!hasPermission()) {
            plugin.getMessage().sendmessage(false, sender, "error.no-permission");
            return;
        }

        if (isSenderConsole()) {
            plugin.getMessage().sendmessage(false, sender, "error.console");
            return;
        }

        if (args.length < 1) {
            sendUsage();
            return;
        }


        Player player = (Player) sender;

        Player target1 = null;


        switch (args.length) {
            case 1:
                target1 = plugin.getUtil().getPlayer(args[0]);
                if (target1 == null) {
                    sendUsage();
                    return;
                }
                player.teleport(target1.getLocation());
                break;
            case 2:
                Player target2 = plugin.getUtil().getPlayer(args[1]);
                if (target2 == null) {
                    sendUsage();
                    return;
                }
                target1.teleport(target2.getLocation());
                break;
        }


    }
}
