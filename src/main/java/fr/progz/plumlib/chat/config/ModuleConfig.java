package fr.progz.plumlib.chat.config;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;
/**
 * Must be extended in a singleton pattern (for better performances)
 */
public abstract class ModuleConfig extends ChatConfig {
    protected ModuleConfig(final @NotNull String name, final @NotNull ChatColor colorName, final @NotNull ChatColor brackColor) {
        this.name = name;
        this.color = colorName;
        this.brackColor = brackColor;
    }
}