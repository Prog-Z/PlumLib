package fr.progz.plumlib.arch;

import java.util.ArrayList;

import org.bukkit.event.Listener;

public abstract class PluginModule {
    protected ArrayList<Listener> _listener = new ArrayList<>();

    public ArrayList<Listener> getListerner() {return _listener;}
}