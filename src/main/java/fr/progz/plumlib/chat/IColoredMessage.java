package fr.progz.plumlib.chat;

import org.bukkit.ChatColor;

public interface IColoredMessage {
    default ChatColor getMsgColor(byte type) {
        switch (type) {
            case IMessageType.SUCCESS:
                return IMessageType.COLOR_SUCCESS;
            case IMessageType.ERROR:
                return IMessageType.COLOR_ERROR;
            case IMessageType.INFO:
                return IMessageType.COLOR_INFO;
            case IMessageType.WARNING:
                return IMessageType.COLOR_WARNING;
            default:
                return IMessageType.COLOR_DEFAULT;
        }
    }
}