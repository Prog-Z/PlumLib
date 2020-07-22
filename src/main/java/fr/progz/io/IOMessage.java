package fr.progz.io;

import net.md_5.bungee.api.ChatColor;

public class IOMessage {
    public static String moduleName = "PlumIO";
	public static ChatColor moduleColor = ChatColor.AQUA;
    public static ChatColor moduleBracket = ChatColor.DARK_RED;
    public static ChatColor W = ChatColor.WHITE;
    public static ChatColor G = ChatColor.GREEN;
    public static ChatColor Y = ChatColor.YELLOW;
    public static ChatColor R = ChatColor.RED;
    public static ChatColor Gr = ChatColor.DARK_GRAY;
    
    public static String createTable = moduleBracket+"["+moduleColor+moduleName+moduleBracket+"] "+Gr+"############################################### "+G+"CREATE TABLES"+Gr+" ###############################################";
    public static String createTableE = moduleBracket+"["+moduleColor+moduleName+moduleBracket+"] "+Gr+"########################################################################################################################";
    public static String setConnect = moduleBracket+"["+moduleColor+moduleName+moduleBracket+"] "+G+"Connect "+W;
    public static String req        = moduleBracket+"["+moduleColor+moduleName+moduleBracket+"] "+Y+"Request "+W;
    public static String closeConnect= moduleBracket+"["+moduleColor+moduleName+moduleBracket+"] "+R+"Close Connection";
}