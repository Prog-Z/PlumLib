package io.github.plum.plumlib;

import io.github.plum.plumlib.arch.PlumPlugin;
import io.github.plum.plumlib.config.ChatConfig;
import io.github.plum.plumlib.config.ModuleType;
import org.bukkit.ChatColor;

/**
 * Plugin class for loading the lib
 *
 * @author Meltwin
 * @since 2.0.0
 */
public class PlumLib extends PlumPlugin implements ChatConfig {


    @Override
    public ChatConfig getChatConfig() {
        return this;
    }

    /*

        Chat Config

     */

    @Override
    public String getVersion() {
        return "2.0.0-SNAPSHOT";
    }

    @Override
    public String getDecoration(boolean right) {
        return (right) ? "]" : "[";
    }

    @Override
    public ChatColor getNameColor() {
        return ChatColor.LIGHT_PURPLE;
    }

    @Override
    public ChatColor getDecoColor() {
        return ChatColor.AQUA;
    }

    @Override
    public ModuleType getType() {
        return ModuleType.PLUGIN;
    }
}
