package io.github.plum.plumlib.config;

import org.bukkit.ChatColor;

/**
 * Config for chat looking of messages
 *
 * @author Meltwin
 * @since 2.0.0
 */
public interface ChatConfig {

    /**
     * @return the name of the module
     */
    String getName();

    /**
     * @return the version of the module
     */
    String getVersion();

    /**
     * @return the decoration (right = true, left = false)
     */
    String getDecoration(boolean right);

    /**
     * @return the color of the name
     */
    ChatColor getNameColor();

    /**
     * @return the color of the decoration
     */
    ChatColor getDecoColor();

    /**
     * @return the type of the module
     * @see ModuleType
     */
    ModuleType getType();

    /**
     * @return the prefix for chat message with the given data on the module
     */
    default String getPrefix() {
        return getDecoColor()+getDecoration(false)+getNameColor()+getName()+getDecoColor()+getDecoration(true)+ChatColor.WHITE;
    }

    /**
     * @return a filled preset of init message
     */
    default String getInitMessage() {
        return getPrefix()+" Init "+getType().getName()+" "+getName()+" (v"+getVersion()+")";
    }
}
