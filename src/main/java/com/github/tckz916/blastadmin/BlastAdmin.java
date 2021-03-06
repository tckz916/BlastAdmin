package com.github.tckz916.blastadmin;

import com.github.tckz916.blastadmin.command.tabcomplete.GamemodeTabComplete;
import com.github.tckz916.blastadmin.command.tabcomplete.PrefixTabComple;
import com.github.tckz916.blastadmin.listener.PlayerListener;
import com.github.tckz916.blastadmin.message.Message;
import com.github.tckz916.blastadmin.message.MessageFormat;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by tckz916 on 2015/07/20.
 */
public class BlastAdmin extends JavaPlugin {

    private static BlastAdmin instance = null;

    private Message message = null;

    private MessageFormat messageFormat = null;

    @Override
    public void onEnable() {
        super.onEnable();

        instance = this;

        message = new Message();
        messageFormat = new MessageFormat();

        saveDefaultConfig();
        reloadConfig();

        registercommand("blastadmin");
        registercommand("day");
        registercommand("gamemode");
        registercommand("iteminfo");
        registercommand("night");
        registercommand("mobhead");
        registercommand("prefix");
        registercommand("serverinfo");
        registercommand("setspawn");
        registercommand("spawn");
        registercommand("tp");
        registercommand("tpa");
        registercommand("tphere");
        registercommand("whois");
        registercommand("tell");
        registercommand("reply");

        registertabcomplete("prefix", new PrefixTabComple());
        registertabcomplete("gamemode", new GamemodeTabComplete());

        registerlistener(new PlayerListener());
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }


    private void registercommand(String cmd) {
        getCommand(cmd).setExecutor(new BlastAdminCommandHandler());
    }

    private void registertabcomplete(String cmd, TabCompleter completer) {
        getCommand(cmd).setTabCompleter(completer);
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

    public static BlastAdmin getInstance() {
        return instance;
    }
}
