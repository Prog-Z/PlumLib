package fr.progz.plumlib.chat;

import org.bukkit.ChatColor;

public interface MessageType {
    
    byte DEFAULT = 0;
    byte SUCCESS = 1;
    byte ERROR = 2;
    byte WARNING = 3;
    byte INFO = 4;

    ChatColor COLOR_DEFAULT = ChatColor.WHITE;
    ChatColor COLOR_SUCCESS = ChatColor.GREEN;
    ChatColor COLOR_ERROR = ChatColor.DARK_RED;
    ChatColor COLOR_WARNING = ChatColor.YELLOW;
    ChatColor COLOR_INFO = ChatColor.BLUE;
}