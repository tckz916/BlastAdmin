package com.github.tckz916.blastadmin;

import com.github.tckz916.blastadmin.message.Message;
import com.github.tckz916.blastadmin.message.MessageFormat;
import com.github.tckz916.blastadmin.util.Util;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by tckz916 on 2015/07/20.
 */
public class BlastAdmin extends JavaPlugin {

    private static BlastAdmin instance = null;

    private Message message = null;

    private MessageFormat messageFormat = null;

    private Util util = null;

    @Override
    public void onEnable() {
        super.onEnable();

        instance = this;

        message = new Message();
        messageFormat = new MessageFormat();
        util = new Util();

        registercommand("blastadmin", new BlastAdminCommandHandler());
        registercommand("tp", new BlastAdminCommandHandler());
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }


    private void registercommand(String cmd, CommandExecutor executor) {
        getCommand(cmd).setExecutor(executor);
    }

    private void registerlistener(Listener listener) {
        this.getServer().getPluginManager().registerEvents(listener, this);
    }

    public Message getMessage() {
        return message;
    }

    public MessageFormat getMessageFormat() {
        return messageFormat;
    }

    public Util getUtil() {
        return util;
    }

    public static BlastAdmin getInstance() {
        return instance;
    }
}
