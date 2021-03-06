package com.github.tckz916.blastadmin;

import com.github.tckz916.blastadmin.api.BaseCommand;
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

        switch (command.getName()) {
            case "blastadmin":
                cmd = new HelpCommand(sender);
                break;
            case "day":
                cmd = new DayCommand(sender);
                break;
            case "gamemode":
                cmd = new GamemodeCommand(sender);
                break;
            case "iteminfo":
                cmd = new IteminfoCommand(sender);
                break;
            case "night":
                cmd = new NightCommand(sender);
                break;
            case "mobhead":
                cmd = new MobHeadCommand(sender);
                break;
            case "prefix":
                cmd = new PrefixCommand(sender);
                break;
            case "serverinfo":
                cmd = new ServerInfoCommand(sender);
                break;
            case "setspawn":
                cmd = new SetSpawnCommand(sender);
                break;
            case "spawn":
                cmd = new SpawnCommand(sender);
                break;
            case "tp":
                cmd = new TeleportCommand(sender);
                break;
            case "tpa":
                cmd = new TeleportAllCommand(sender);
                break;
            case "tphere":
                cmd = new TeleportHereCommand(sender);
                break;
            case "tell":
                cmd = new PrivateMessageCommand(sender);
                break;
            case "reply":
                cmd = new ReplyCommand(sender);
                break;
            case "whois":
                cmd = new WhoisCommand(sender);
                break;
        }

        cmd.execute(sender, command, label, args);
        return true;
    }
}
