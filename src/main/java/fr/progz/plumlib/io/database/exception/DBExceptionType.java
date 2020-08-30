package fr.progz.plumlib.io.database.exception;

public enum DBExceptionType {
    DB_FOUND("Database found successfuly.",false),
    DB_NOT_FOUND("Database not found.",true),
    CO_TIMEOUT("Database has timed out.",true),
    CO_CLOSE("Database has successfully closed.",false),
    REQ_SUCCESS("Request successful.",false),
    RS_CLOSED_OR_DB_NOT_FOUND("ResultSet closed or DB not found",true),
    RS_SUCCESS("Resultset read successfully.",false),
    RS_EMPTY("Resulset is empty",false);

    private String msg;
    private boolean error;
    private DBExceptionType(String msg, boolean error) {
        this.msg = msg;
        this.error = error;
    }
    public String getMsg() {return msg;}
    public boolean isError() {return error;}
}