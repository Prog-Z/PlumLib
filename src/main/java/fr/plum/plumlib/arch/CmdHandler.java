package fr.plum.plumlib.arch;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import fr.plum.plumlib.chat.sender.IMessageSender;
import fr.plum.plumlib.chat.sender.Sender;
/**
 * Basic interface for modules' command handler
 * @author Meltwin
 * @since 1.0
 */
public abstract class CmdHandler implements Listener, Sender, IMessageSender {

    protected final boolean ERROR = false;

    public abstract boolean command(CommandSender sender, Command command, String label, String[] args);

    protected final boolean hasPerm(final @NotNull CommandSender sender, final @NotNull String perm, final @NotNull String msg) {
        if (!sender.hasPermission(perm)) {
            sendMessage(sender, msg);
            return false;
        }
        return true;
    }
    protected final boolean hasPerm(final @NotNull CommandSender sender, final @NotNull IPermission perm, final @NotNull String msg) {
        return this.hasPerm(sender, perm.path(), msg);
    }

    /**
     * Quick written test for testing if there is enough arg passed in the command
     */
    protected final boolean hasArgsNumberMin(final @NotNull String[] args, int argsNumber, final CommandSender sender, final String msg) {
        if (args.length<argsNumber) {
            if (!isNull(msg) && !isNull(sender)) sendMessage(sender, msg);
            return false;
        }
        return true;
    }
    /**
     * Quick written test for testing if there is enough arg passed in the command
     */
    protected final boolean hasArgsNumberMin(final @NotNull String[] args, int argsNumber) {
        return hasArgsNumberMin(args, argsNumber, null, null);
    }

    /**
     * Quick written test for knowing if sender is a player en sending him a message
     */
    protected final boolean senderIsPlayer(final CommandSender sender, final String msg) {
        if (isNull(sender))
            return false;
        if (!_isPlayer(sender)) {
            if (!isNull(msg)) sendMessage(sender, msg);
            return false;
        }
        return true;
    }

    protected final boolean isNull(final Object obj, final CommandSender sender, final String msg) {
        if (isNull(obj)) {
            if (!isNull(msg) && !isNull(sender)) sendMessage(sender, msg);
            return true;
        }
        return false;
    }
    protected final boolean isNull(final Object obj) {return obj==null;}

}