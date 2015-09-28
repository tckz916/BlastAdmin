package com.github.tckz916.blastadmin.command;

import com.github.tckz916.blastadmin.BlastAdmin;
import com.github.tckz916.blastadmin.api.BaseCommand;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by tckz916 on 2015/09/22.
 */
public class GamemodeCommand extends BaseCommand {

    private BlastAdmin plugin = BlastAdmin.getInstance();

    public static final String NAME = "gamemode";

    public static final String PERMISSION = "blastadmin.command.gamemode";

    public static final String DESCRIPTION = "Gamemode Command";

    public static final String USAGE = "/gamemode <adventure||creative||spectator||survival>";

    public GamemodeCommand(CommandSender sender) {
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
        if (args.length < 1) {
            sendUsage();
            return;
        }

        Player player = (Player) sender;

        if (args[0].equals("survival") || args[0].equals("s") || args[0].equals("0")) {
            if (args.length == 2) {
                Player target = getPlayer(args[1]);
                if (target == null) {
                    plugin.getMessage().sendmessage(sender, format(false, "error.player-not-found"));
                    return;
                }
                target.setGameMode(GameMode.SURVIVAL);
                plugin.getMessage().sendmessage(sender,
                        format(true, "message.gamemode")
                                .replace("%gamemode%", target.getGameMode().name())
                                .replace("%player%", target.getName()));
                plugin.getMessage().sendmessage(target,
                        format(true, "message.gamemode")
                                .replace("%gamemode%", target.getGameMode().name())
                                .replace("%player%", target.getName()));
                return;
            }
            player.setGameMode(GameMode.SURVIVAL);
            plugin.getMessage().sendmessage(sender,
                    format(true, "message.gamemode")
                            .replace("%gamemode%", player.getGameMode().name())
                            .replace("%player%", player.getName()));
        } else if (args[0].equals("creative") || args[0].equals("c") || args[0].equals("1")) {
            if (args.length == 2) {
                Player target = getPlayer(args[1]);
                if (target == null) {
                    plugin.getMessage().sendmessage(sender, format(false, "error.player-not-found"));
                    return;
                }
                target.setGameMode(GameMode.CREATIVE);
                plugin.getMessage().sendmessage(sender,
                        format(true, "message.gamemode")
                                .replace("%gamemode%", target.getGameMode().name())
                                .replace("%player%", target.getName()));
                plugin.getMessage().sendmessage(target,
                        format(true, "message.gamemode")
                                .replace("%gamemode%", target.getGameMode().name())
                                .replace("%player%", target.getName()));
                return;
            }
            player.setGameMode(GameMode.CREATIVE);
            plugin.getMessage().sendmessage(sender,
                    format(true, "message.gamemode")
                            .replace("%gamemode%", player.getGameMode().name())
                            .replace("%player%", player.getName()));
        } else if (args[0].equals("spectator") || args[0].equals("sp") || args[0].equals("3")) {
            if (args.length == 2) {
                Player target = getPlayer(args[1]);
                if (target == null) {
                    plugin.getMessage().sendmessage(sender, format(false, "error.player-not-found"));
                    return;
                }
                target.setGameMode(GameMode.SPECTATOR);
                plugin.getMessage().sendmessage(sender,
                        format(true, "message.gamemode")
                                .replace("%gamemode%", target.getGameMode().name())
                                .replace("%player%", target.getName()));
                plugin.getMessage().sendmessage(target,
                        format(true, "message.gamemode")
                                .replace("%gamemode%", target.getGameMode().name())
                                .replace("%player%", target.getName()));
                return;
            }
            player.setGameMode(GameMode.SPECTATOR);
            plugin.getMessage().sendmessage(sender,
                    format(true, "message.gamemode")
                            .replace("%gamemode%", player.getGameMode().name())
                            .replace("%player%", player.getName()));
        } else if (args[0].equals("adventure") || args[0].equals("a") || args[0].equals("2")) {
            if (args.length == 2) {
                Player target = getPlayer(args[1]);
                if (target == null) {
                    plugin.getMessage().sendmessage(sender, format(false, "error.player-not-found"));
                    return;
                }
                target.setGameMode(GameMode.ADVENTURE);
                plugin.getMessage().sendmessage(sender,
                        format(true, "message.gamemode")
                                .replace("%gamemode%", target.getGameMode().name())
                                .replace("%player%", target.getName()));
                plugin.getMessage().sendmessage(target,
                        format(true, "message.gamemode")
                                .replace("%gamemode%", target.getGameMode().name())
                                .replace("%player%", target.getName()));
                return;
            }
            player.setGameMode(GameMode.ADVENTURE);
            plugin.getMessage().sendmessage(sender,
                    format(true, "message.gamemode")
                            .replace("%gamemode%", player.getGameMode().name())
                            .replace("%player%", player.getName()));
        }
    }

    private Player getPlayer(String name) {
        return plugin.getServer().getPlayer(name);
    }

    private String format(boolean prefix, String key, Object... args) {
        return plugin.getMessageFormat().format(prefix, key, args);
    }
}
