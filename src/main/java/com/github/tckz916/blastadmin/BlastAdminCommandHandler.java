package com.github.tckz916.blastadmin;

import com.github.tckz916.blastadmin.command.BaseCommand;
import com.github.tckz916.blastadmin.command.HelpCommand;
import com.github.tckz916.blastadmin.command.TeleportCommand;
import com.github.tckz916.blastadmin.command.TeleportHereCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by tckz916 on 2015/09/03.
 */
public class BlastAdminCommandHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        BaseCommand cmd = new HelpCommand(sender);

        switch (command.getName()){
            case "blastadmin":
                cmd = new HelpCommand(sender);
                break;
            case "tp":
                cmd = new TeleportCommand(sender);
                break;
            case "tphere":
                cmd = new TeleportHereCommand(sender);
                break;
        }

        cmd.execute(sender,command,label,args);
        return true;
    }
}
