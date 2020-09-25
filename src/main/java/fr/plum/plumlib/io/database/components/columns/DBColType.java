package fr.plum.plumlib.io.database.components.columns;
/**
 * The SQL Type of the column
 * @author Meltwin
 * @since 1.1.0
 */
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