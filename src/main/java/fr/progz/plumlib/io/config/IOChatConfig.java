package fr.progz.plumlib.io.config;

import javax.ejb.Singleton;

import org.bukkit.ChatColor;

import fr.progz.plumlib.chat.config.ModuleConfig;

@Singleton
public final class IOChatConfig extends ModuleConfig {

    // Chat Properties
    public static final String NAME = "PlumIO";
    public static final ChatColor COLOR = ChatColor.AQUA;
    public static final ChatColor BRACKCOLOR = ChatColor.DARK_RED;

    // Singleton Management
    static final IOChatConfig instance = new IOChatConfig();
    public static final IOChatConfig getInstance() { return instance; }
    protected IOChatConfig() { super(NAME, COLOR, BRACKCOLOR); }    

}