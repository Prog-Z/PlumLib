package fr.progz.plumlib.arch;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
/**
 * Basic interface for modules' command handler
 * @author Meltwin
 * @version 1.0
 */
public interface ICmdHandler {
    public boolean command(CommandSender sender, Command command, String label, String[] args);

    public default boolean isConsole(CommandSender s) {
        return s instanceof ConsoleCommandSender;
    }

    public default boolean isPlayer(CommandSender s) {
        return s instanceof Player;
    }

    public default boolean isCmdBlock(CommandSender s) {
        return s instanceof BlockCommandSender;
    }
}