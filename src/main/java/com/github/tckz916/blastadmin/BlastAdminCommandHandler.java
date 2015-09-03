package com.github.tckz916.blastadmin;

import com.github.tckz916.blastadmin.command.*;
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
            case "whois":
                cmd = new WhoisCommand(sender);
                break;
            case "tell":
                cmd = new PrivateMessageCommand(sender);
                break;
            case "reply":
                cmd = new ReplyCommand(sender);
                break;
        }

        cmd.execute(sender,command,label,args);
        return true;
    }
}
