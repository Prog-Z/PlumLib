package fr.progz.io;

import org.bukkit.ChatColor;

import fr.progz.plumlib.arch.IModuleConfig;

public class IOConfig implements IModuleConfig {
    public static ChatColor W = ChatColor.WHITE;
    public static ChatColor G = ChatColor.GREEN;
    public static ChatColor Y = ChatColor.YELLOW;
    public static ChatColor R = ChatColor.RED;
    public static ChatColor Gr = ChatColor.DARK_GRAY;

    @Override
    public ChatColor getBracketColor() { return ChatColor.DARK_RED; }
    @Override
    public String getName() { return "PlumIO"; }
    @Override
    public ChatColor getNameColor() { return ChatColor.AQUA; }

    public static ChatColor sgetBracketColor() { return ChatColor.DARK_RED; }
    public static String sgetName() { return "PlumIO"; }
    public static ChatColor sgetNameColor() { return ChatColor.AQUA; }
}