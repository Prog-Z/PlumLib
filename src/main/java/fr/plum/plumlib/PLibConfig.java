package fr.plum.plumlib;

import javax.ejb.Singleton;

import org.bukkit.ChatColor;

import fr.plum.plumlib.chat.config.PluginConfig;
/**
 * Global Config for PLib
 * @author Meltwin
 * @since 1.1.0
 */
@Singleton
public final class PLibConfig extends PluginConfig {

    static final String NAME = "Plib";
    static final ChatColor COLOR = ChatColor.GOLD;
    static final ChatColor BRACK_COLOR = ChatColor.RED;

    private static final PLibConfig instance = new PLibConfig();

    protected PLibConfig() {
        super(NAME, COLOR, BRACK_COLOR);
    }

    public static PLibConfig getInstance() { return instance; }
}