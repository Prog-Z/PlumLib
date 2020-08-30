package fr.progz.plumlib.io.database.exception;

import org.jetbrains.annotations.NotNull;

import fr.progz.plumlib.chat.config.ChatConfig;
import fr.progz.plumlib.chat.sender.IMessageSender;
import fr.progz.plumlib.chat.sender.PrefixOption;
import fr.progz.plumlib.io.config.IOChatConfig;

public class DBException extends Exception implements IMessageSender {

    private static final long serialVersionUID = 8362340380700548231L;
    private DBExceptionType type;
    private Exception e;

    public DBException(DBExceptionType type, Exception e) {
        this.type = type;
        this.e = e;

        if (isError()) {
            sendMessage(IMessageSender.CONSOLE, type.getMsg(), PrefixOption.NO_PREFIX);
            if (this.e != null)
                this.e.printStackTrace();
        }
    }

    public boolean isError() { return type.isError(); }

    @Override
    public @NotNull ChatConfig getChatConfig() { return IOChatConfig.getInstance(); }
}