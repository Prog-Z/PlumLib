package fr.progz.io.database.exception;

import fr.progz.plumlib.arch.IModuleChat;
import fr.progz.plumlib.chat.IMessageType;

public class DBException extends Exception implements IModuleChat {

    private static final long serialVersionUID = 8362340380700548231L;
    private DBExceptionType type;
    private Exception e;

    public DBException(DBExceptionType type, Exception e) {
        this.type = type;
        this.e = e;

        if (isError()) {
            sendMsg(type.getMsg(),IMessageType.ERROR,false);
            if (this.e!=null) this.e.printStackTrace();
        } 
    }
    public boolean isError() {return type.isError();}
}