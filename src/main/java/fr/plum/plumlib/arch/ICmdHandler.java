package fr.plum.plumlib.arch;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import fr.plum.plumlib.chat.sender.Sender;
/**
 * Basic interface for modules' command handler
 * @author Meltwin
 * @since 1.0
 */
public abstract class ICmdHandler implements Listener, Sender {
    public abstract boolean command(CommandSender sender, Command command, String label, String[] args);
}