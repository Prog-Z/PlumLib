package fr.plum.plumlib.chat.config;

import javax.inject.Singleton;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import fr.plum.plumlib.chat.ChatType;
/**
 * ChatConfig for PlumPlugin.
 * Must be extended in a singleton pattern (for better performances)
 * @author Meltwin
 * @since 1.1.0
 */

@Singleton
public abstract class PluginConfig extends ChatConfig {

    /**
     * Option to render a [name] prefix
     */
    protected PluginConfig(final @NotNull String name, final @NotNull ChatColor colorName, final @NotNull ChatColor brackColor) {
        this.name = name;
        this.color = colorName;
        this.brackColor = brackColor;

        this.type = ChatType.PLUGIN;
    }
}