package fr.progz.plumlib.arch;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

public abstract class PluginModule implements IModuleChat {
    protected ArrayList<Listener> _listener = new ArrayList<>();

    public ArrayList<Listener> getListerner() {
        return _listener;
    }

    // #####################################################
    // SECTION IModuleChat
    // #####################################################

    public ChatColor CHAT_bracketColor;
    public ChatColor CHAT_color;
    public String CHAT_name;

    public <T extends IModuleConfig> void setConfig(T c) {
        CHAT_bracketColor = c.getBracketColor();
        CHAT_color = c.getNameColor();
        CHAT_name = c.getName();
    }
    @Override
    public ChatColor getBracketColor() { return this.CHAT_bracketColor; }
    @Override
    public ChatColor getColor() { return this.CHAT_color; }
    @Override
    public String getModuleName() { return this.CHAT_name; }
    @Override
    public byte moduleType() { return IModuleChat.TYPE_MODULE; }
    // !SECTION
}