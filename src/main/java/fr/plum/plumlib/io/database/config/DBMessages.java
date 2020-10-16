package fr.plum.plumlib.io.database.config;


public enum DBMessages {
    CONNECTING_DB(DBMessageType.NULL,"Connecting to DB"),
    CLOSE_DB(DBMessageType.NULL,"Closing Connection"),
    PREPARE_REQ(DBMessageType.PREPARE,"%s"),
    EXECUTING_REQ(DBMessageType.EXECUTE,""),
    
    FRAG_CHECK_START(DBMessageType.INFO,"Start checking for existing tables"),
    FRAG_CHECK_RESULT(DBMessageType.NULL,"Found %1$d/%2$d"),
    FRAG_BUILD_START(DBMessageType.INFO,"Start making missing tables"),
    FRAG_BUILD_END(DBMessageType.INFO,"Finished making tables"),
    FRAG_LINE_START(DBMessageType.INFO,"Start adding lines in missing tables"),
    FRAG_LINE_END(DBMessageType.INFO,"Finished adding lines");

    private DBMessageType type;
    private String msg;
    private DBMessages(DBMessageType type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public String getMessage() {
        return type.get()+msg;
    }
    
}