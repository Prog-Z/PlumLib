package fr.plum.plumlib.arch;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.Listener;
import org.jetbrains.annotations.Nullable;

import fr.plum.plumlib.chat.sender.IMessageSender;

/**
 * Create a module for a PlumPlugin
 * 
 * @author Meltwin
 * @since 1.1.0
 */
public abstract class PlumModule implements IMessageSender {
    /**
     * Create an instance of a PlumModule
     */
    protected PlumModule() {
        listeners = new ArrayList<>();
    }

    public abstract void init();

    // EventListeners
    protected final List<Listener> listeners;

    public final List<Listener> getListerner() { return listeners; }

    // CommandHandler
    @Nullable
    public final CmdHandler getCommandHandler() { return commands; }

    protected CmdHandler commands;
    protected final void setCommandHandler(final CmdHandler handler) { commands = handler; }
}