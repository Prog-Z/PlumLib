package fr.progz.plumlib;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import fr.progz.plumlib.arch.ModuleConfig;

public class PLib extends JavaPlugin implements ModuleConfig {

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
    public boolean isModule() { return false;}
    @Override
    public String getModuleName() { return "PlumLib";}
    // !SECTION

    @Override
    public void onEnable() {
        super.onEnable();
        sendInitMsg();
    }
 
}
