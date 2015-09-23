package com.github.tckz916.blastadmin.command;

import com.github.tckz916.blastadmin.BlastAdmin;
import com.github.tckz916.blastadmin.api.BaseCommand;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * Created by tckz916 on 2015/09/07.
 */
public class MobHeadCommand extends BaseCommand {

    private BlastAdmin plugin = BlastAdmin.getInstance();

    public static final String NAME = "mobhead";

    public static final String PERMISSION = "blastadmin.command.mobhead";

    public static final String DESCRIPTION = "Item Command";

    public static final String USAGE = "\n/mobhead" +
            "\n/mobhead <player>";

    public MobHeadCommand(CommandSender sender) {
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
        if (args.length > 2) {
            sendUsage();
            return;
        }


        Player player = (Player) sender;

        switch (args.length) {
            case 0:
                getSkull(player, player.getName());
                break;
            case 1:
                if (!args[0].matches("[A-Za-z0-9_]{2,16}")) {
                    plugin.getMessage().sendmessage(sender, format(false, "error.player-not-found"));
                    return;
                }
                getSkull(player, args[0]);
        }

    }

    private void getSkull(Player player, String name) {
        Inventory inventory = player.getInventory();
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullmeta = (SkullMeta) itemStack.getItemMeta();
        skullmeta.setOwner(name);
        itemStack.setItemMeta(skullmeta);
        inventory.addItem(itemStack);
        String message = format(true, "message.mobhead").replace("%player%", name);
        plugin.getMessage().sendmessage(player, message);
    }

    private String format(boolean prefix, String key, Object... args) {
        return plugin.getMessageFormat().format(prefix, key, args);
    }
}
