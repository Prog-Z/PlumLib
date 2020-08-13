package fr.progz.plumlib;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import fr.progz.plumlib.arch.IModuleChat;

public class PLib extends JavaPlugin implements IModuleChat {

    // #####################################################
    //
    //                  SECTION Implements
    //
    // #####################################################

    @Override
    public ChatColor getBracketColor() { return ChatColor.GOLD;}
    @Override
    public ChatColor getColor() { return ChatColor.AQUA;}
    @Override
    public byte moduleType() { return IModuleChat.TYPE_PLUGIN;}
    @Override
    public String getModuleName() { return "PlumLib";}
    // !SECTION

    @Override
    public void onEnable() {
        super.onEnable();
        sendInitMsg();
    }
}
