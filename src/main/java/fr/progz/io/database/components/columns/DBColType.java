package fr.progz.io.database.components.columns;

public enum DBColType {
    INTEGER("INTEGER"),
    VARCHAR("VARCHAR"),
    REAL("REAL"),
    NULL("NULL");

    private String type;

    private DBColType(String type) {
        this.type = type;
    }
    public String getType() {return type;}
}