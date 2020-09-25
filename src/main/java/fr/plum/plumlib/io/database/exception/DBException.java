package fr.plum.plumlib.io.database.exception;

import org.jetbrains.annotations.NotNull;

import fr.plum.plumlib.chat.config.ChatConfig;
import fr.plum.plumlib.chat.sender.IMessageSender;
import fr.plum.plumlib.chat.sender.PrefixOption;
import fr.plum.plumlib.chat.sender.Sender;
import fr.plum.plumlib.io.config.IOChatConfig;

public class DBException extends Exception implements IMessageSender, Sender {

    private static final long serialVersionUID = 8362340380700548231L;
    private DBExceptionType type;
    private Exception e;

    public DBException(DBExceptionType type, Exception e) {
        this.type = type;
        this.e = e;

        if (isError()) {
            sendMessage(CONSOLE(), type.getMsg(), PrefixOption.NO_PREFIX);
            if (this.e != null)
                this.e.printStackTrace();
        }
    }

    public boolean isError() { return type.isError(); }

    @Override
    public @NotNull ChatConfig getChatConfig() { return IOChatConfig.getInstance(); }
}