package fr.plum.plumlib.arch;

import java.util.ArrayList;

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
    public PlumModule() {
        listeners = new ArrayList<>();
    }

    public abstract void init();

    // EventListeners
    protected final ArrayList<Listener> listeners;

    public final ArrayList<Listener> getListerner() { return listeners; }

    // CommandHandler
    @Nullable
    public final ICmdHandler getCommandHandler() { return commands; }

    protected ICmdHandler commands;
    protected final void setCommandHandler(final ICmdHandler handler) { commands = handler; }
}