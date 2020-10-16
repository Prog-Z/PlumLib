package fr.plum.plumlib.io.config;

import javax.ejb.Singleton;

import org.bukkit.ChatColor;

import fr.plum.plumlib.chat.config.ModuleConfig;
/**
 * Class for properties of the PlumIO Module
 * @author Meltwin
 * @since 1.1.0
 */
@Singleton
public final class IOChatConfig extends ModuleConfig {

    // Chat Properties
    public static final String NAME = "PlumIO";
    public static final ChatColor COLOR = ChatColor.AQUA;

    // Singleton Management
    static final IOChatConfig instance = new IOChatConfig();
    public static final IOChatConfig getInstance() { return instance; }
    protected IOChatConfig() { super(NAME, COLOR); }    

}