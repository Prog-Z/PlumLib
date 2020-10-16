package fr.plum.plumlib.chat.sender;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import fr.plum.plumlib.chat.ChatType;
import fr.plum.plumlib.chat.config.ChatConfig;

/**
 * Interface for uniforming message sending through PlumPlugins
 * @author Meltwin
 * @since 1.1.0
 */
@FunctionalInterface
public interface IMessageSender {

    // #####################################################
    // SECTION Send Message
    // #####################################################

    /**
     * Send a message to the commandSender
     * @param sender Player, ConsoleCommandSender or BlockCommandSender
     * @param message
     */
    public default void sendMessage(final CommandSender sender, final String message, final PrefixOption addPrefix, ColorOption colored, final ChatColor msgColor) {
        // Prevent null
        if (sender == null || message == null) return;
        if (colored == null) colored = ColorOption.NO_COLOR;
        
        // Format 
        String prefix;
        if (addPrefix != null && addPrefix == PrefixOption.ADD_PREFIX) prefix = formatPrefix(colored); else prefix ="";

        // Send the message
        sender.sendMessage(prefix+formatMessage(message, colored, msgColor));
    }
    /**
     * Send a message to the commandSender
     * @param sender Player, ConsoleCommandSender or BlockCommandSender
     * @param message
     */
    public default void sendMessage(final CommandSender sender, final String message, final PrefixOption addPrefix, final ChatColor msgColor) {
        if (sender instanceof BlockCommandSender) this.sendMessage(sender, message, addPrefix, ColorOption.NO_COLOR, null);
        else this.sendMessage(sender, message, addPrefix, ColorOption.WITH_COLOR, msgColor);
    }
    /**
     * Send a message to the commandSender
     * @param sender Player, ConsoleCommandSender or BlockCommandSender
     * @param message
     */
    public default void sendMessage(final CommandSender sender, final String message, ColorOption colored, final ChatColor msgColor) {
        if (sender instanceof BlockCommandSender) this.sendMessage(sender, message, PrefixOption.NO_PREFIX, colored, msgColor);
        else this.sendMessage(sender, message, PrefixOption.ADD_PREFIX, colored, msgColor);
    }
    /**
     * Send a message to the commandSender
     * @param sender Player, ConsoleCommandSender or BlockCommandSender
     * @param message
     */
    public default void sendMessage(final CommandSender sender, final String message, final PrefixOption addPrefix) {
        if (sender instanceof BlockCommandSender) this.sendMessage(sender, message, addPrefix, ColorOption.NO_COLOR, null);
        else this.sendMessage(sender, message, addPrefix, ColorOption.WITH_COLOR, ChatColor.WHITE);
    }
    /**
     * Send a message to the commandSender
     * @param sender Player, ConsoleCommandSender or BlockCommandSender
     * @param message
     */
    public default void sendMessage(final CommandSender sender, final String message, ColorOption colored) {
        if (sender instanceof BlockCommandSender) this.sendMessage(sender, message, PrefixOption.NO_PREFIX, ColorOption.NO_COLOR, null);
        else this.sendMessage(sender, message, PrefixOption.ADD_PREFIX, colored, ChatColor.GRAY);
    }
    /**
     * Send a message to the commandSender
     * @param sender Player, ConsoleCommandSender or BlockCommandSender
     * @param message
     */
    public default void sendMessage(final CommandSender sender, final String message) {
        if (sender instanceof BlockCommandSender) this.sendMessage(sender, message, PrefixOption.NO_PREFIX, ColorOption.NO_COLOR, null);
        else this.sendMessage(sender, message, PrefixOption.ADD_PREFIX, ColorOption.WITH_COLOR, ChatColor.GRAY);
    }
    /**
     * Send an init message in the console
     */
    public default void sendInitMsg() {
        this.sendMessage(Bukkit.getConsoleSender(), ChatColor.YELLOW+"Init "+ChatColor.WHITE+getType().str()+" "+getNameColor()+getName(), ColorOption.WITH_COLOR, ChatColor.WHITE);
    }
    
    // !SECTION

    // #####################################################
    // SECTION Calc
    // #####################################################

    // ANCHOR Prefix
    default String formatPrefix(final @NotNull ColorOption colored) {
        return (colored == ColorOption.WITH_COLOR) ? getChatConfig().getColorFormPrefix() : getChatConfig().getWhiteFormPrefix();
    }

    // ANCHOR Message
    default String formatMessage(final @NotNull String message, final @NotNull ColorOption colored, final @NotNull ChatColor msgColor) {
        return (colored == ColorOption.WITH_COLOR) ? msgColor+message : message;
    }

    // !SECTION

    // #####################################################
    // SECTION Configuration
    // #####################################################

    @NotNull
    public ChatConfig getChatConfig();

    public default String getName() { return getChatConfig().getName(); }
    public default ChatColor getNameColor() { return getChatConfig().getNameColor(); }
    public default ChatColor getBrackColor() { return getChatConfig().getBrackColor(); }
    public default ChatType getType() { return getChatConfig().getType(); }
    // !SECTION
}