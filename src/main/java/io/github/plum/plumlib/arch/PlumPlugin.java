package io.github.plum.plumlib.arch;

import io.github.plum.plumlib.config.ChatConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Launcher class for spigot plugin
 *
 * @author Meltwin
 * @version 2.0.0
 */
public abstract class PlumPlugin extends JavaPlugin {

    public PlumPlugin() {
        super();

        Bukkit.getConsoleSender().sendMessage(getChatConfig().getInitMessage());
    }

    /**
     * @return the configuration file of the plugin
     */
    abstract public ChatConfig getChatConfig();
}
