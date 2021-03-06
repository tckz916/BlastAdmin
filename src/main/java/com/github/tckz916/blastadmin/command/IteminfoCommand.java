package com.github.tckz916.blastadmin.command;

import com.github.tckz916.blastadmin.BlastAdmin;
import com.github.tckz916.blastadmin.api.BaseCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by tckz916 on 2015/09/23.
 */
public class IteminfoCommand extends BaseCommand {

    private BlastAdmin plugin = BlastAdmin.getInstance();

    public static final String NAME = "iteminfo";

    public static final String PERMISSION = "blastadmin.command.iteminfo";

    public static final String DESCRIPTION = "Item Command";

    public static final String USAGE = "/iteminfo";

    public IteminfoCommand(CommandSender sender) {
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
        ItemStack hand = player.getItemInHand();

        String id = String.valueOf(hand.getType().getId());
        String damage = String.valueOf(hand.getDurability());

        plugin.getMessage().sendmessage(sender,
                format(true, "message.iteminfo")
                        .replace("%material%", hand.getType().name())
                        .replace("%id%", id)
                        .replace("%damage%", damage));

    }

    private String format(boolean prefix, String key, Object... args) {
        return plugin.getMessageFormat().format(prefix, key, args);
    }
}
