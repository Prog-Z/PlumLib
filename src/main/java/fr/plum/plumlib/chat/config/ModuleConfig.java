package fr.plum.plumlib.chat.config;

import javax.inject.Singleton;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import fr.plum.plumlib.chat.ChatType;
/**
 * ChatConfig for PlumModules.
 * Must be extended in a singleton pattern (for better performances)
 * @author Meltwin
 * @since 1.1.0
 */
@Singleton
public abstract class ModuleConfig extends ChatConfig {

    /**
     * Option to render a [name] prefix
     */
    protected ModuleConfig(final @NotNull String name, final @NotNull ChatColor colorName) {
        this.name = name;
        this.color = colorName;

        this.type = ChatType.MODULE;
    }
}