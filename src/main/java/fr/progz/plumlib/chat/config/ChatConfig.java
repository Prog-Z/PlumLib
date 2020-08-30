package fr.progz.plumlib.chat.config;

import javax.ejb.Singleton;

import org.bukkit.ChatColor;

import fr.progz.plumlib.chat.ChatType;

@Singleton
public abstract class ChatConfig {

    String name = "no name";
    ChatColor color = ChatColor.WHITE, brackColor = ChatColor.RED;
    ChatType type = ChatType.MODULE;

    String colorFormPrefix;
    String whiteFormPrefix;

    String coloredPrefix() { return getBrackColor()+"["+getNameColor()+getName()+getBrackColor()+"]";}
    String whitePrefix() { return "["+getName()+"]"; }

    public String getName() { return name; }
    public ChatColor getNameColor() { return color; }
    public ChatColor getBrackColor() { return brackColor; }
    public ChatType getType() { return type; }

    public String getColorFormPrefix() { 
        if (colorFormPrefix == null) colorFormPrefix = coloredPrefix();
        return colorFormPrefix; 
    }
    public String getWhiteFormPrefix() { 
        if (whiteFormPrefix == null) whiteFormPrefix = whitePrefix();
        return whiteFormPrefix; 
    }
}