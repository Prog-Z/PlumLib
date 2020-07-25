package fr.progz.plumlib.chat;

import org.bukkit.ChatColor;

public interface ColoredMessage {
    default ChatColor getMsgColor(byte type) {
        switch (type) {
            case MessageType.SUCCESS:
                return MessageType.COLOR_SUCCESS;
            case MessageType.ERROR:
                return MessageType.COLOR_ERROR;
            case MessageType.INFO:
                return MessageType.COLOR_INFO;
            case MessageType.WARNING:
                return MessageType.COLOR_WARNING;
            default:
                return MessageType.COLOR_DEFAULT;
        }
    }
}