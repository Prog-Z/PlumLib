package fr.plum.plumlib.chat.sender;

import org.bukkit.Bukkit;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Interface for managing the different senders
 * @author Meltwin
 * @since 1.1.0
 */
public interface Sender {
    public default boolean _isConsole(final CommandSender sender) { return sender instanceof ConsoleCommandSender; }
    public default boolean _isPlayer(final CommandSender sender) { return sender instanceof Player; }
    public default boolean _isCmdBlock(final CommandSender sender) { return sender instanceof BlockCommandSender; }

    public static boolean isConsole(final CommandSender sender) { return sender instanceof ConsoleCommandSender; }
    public static boolean isPlayer(final CommandSender sender) { return sender instanceof Player; }
    public static boolean isCmdBlock(final CommandSender sender) { return sender instanceof BlockCommandSender; }

    public default ConsoleCommandSender CONSOLE() { return Bukkit.getConsoleSender();} 
}