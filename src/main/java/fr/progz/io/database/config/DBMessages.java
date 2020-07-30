package fr.progz.io.database.config;

import fr.progz.plumlib.chat.I_ChatMessage;

public enum DBMessages implements I_ChatMessage {
    CONNECTING_DB("Connecting to DB"),
    CLOSE_DB("Closing Connection"),
    PREPARE_REQ("Preparing request %s"),
    EXECUTING_REQ("Excuting request"),
    
    FRAG_CHECK_START("Start checking for existing tables"),
    FRAG_CHECK_RESULT("Found %1$d/%2$d"),
    FRAG_BUILD_START("Start making missing tables"),
    FRAG_BUILD_END("Finished making tables"),
    FRAG_LINE_START("Start adding lines in missing tables"),
    FRAG_LINE_END("Finished adding lines");

    private String msg;
    private DBMessages(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
    
}