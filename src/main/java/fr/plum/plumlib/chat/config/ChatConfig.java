package fr.plum.plumlib.chat.config;

import javax.ejb.Singleton;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import fr.plum.plumlib.chat.ChatType;

/**
 * Class for using plum chat features (through IMessageSender for example)
 * @author Meltwin
 * @since 1.1.0
 */
@Singleton
public abstract class ChatConfig {

    String name = "no name";
    ChatColor color = ChatColor.WHITE, brackColor = ChatColor.RED;
    ChatType type = ChatType.UNKNOWN;

    String colorFormPrefix;
    String whiteFormPrefix;

    final @NotNull String coloredPrefix() { return getBrackColor()+"["+getNameColor()+getName()+getBrackColor()+"]";}
    final @NotNull String whitePrefix() { return "["+getName()+"]"; }

    public @NotNull String getName() { return name; }
    public @NotNull ChatColor getNameColor() { return color; }
    /**
     * Not Needed for PlumModules
     * @return color of the brackets
     */
    public @NotNull ChatColor getBrackColor() { return brackColor; }
    public void setBrackColor(@NotNull ChatColor color) { brackColor = color; }
    /**
     * Not Needed for PlumModules
     * @return return the type of the caller
     * @see {@link ChatType}
     */
    public @NotNull ChatType getType() { return type; }

    public final @NotNull String getColorFormPrefix() { 
        if (colorFormPrefix == null) colorFormPrefix = coloredPrefix();
        return colorFormPrefix; 
    }
    public final @NotNull String getWhiteFormPrefix() { 
        if (whiteFormPrefix == null) whiteFormPrefix = whitePrefix();
        return whiteFormPrefix; 
    }
}