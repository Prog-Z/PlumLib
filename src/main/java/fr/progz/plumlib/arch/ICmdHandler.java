package fr.progz.plumlib.arch;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
/**
 * Basic interface for modules' command handler
 * @author Meltwin
 * @version 1.0
 */
public interface ICmdHandler {
    public default boolean command(CommandSender sender, Command command, String label, String[] args) {
        return true;
    };
}