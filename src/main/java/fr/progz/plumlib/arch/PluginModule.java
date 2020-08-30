package fr.progz.plumlib.arch;

import java.util.ArrayList;

import org.bukkit.event.Listener;

import fr.progz.plumlib.chat.sender.IMessageSender;

public abstract class PluginModule implements IMessageSender {

    public abstract void init();

    protected final ArrayList<Listener> _listener = new ArrayList<>();
    public final ArrayList<Listener> getListerner() { return _listener; }
}