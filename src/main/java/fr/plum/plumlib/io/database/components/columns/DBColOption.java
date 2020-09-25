package fr.plum.plumlib.io.database.components.columns;
/**
 * Options for parametize a column
 * @author Meltwin
 * @since 1.1.0
 */
public enum DBColOption {
    PRIMARY("PRIMARY KEY "),
    AUTO_INCREMENT("AUTOINCREMENT "),
    DEFAULT("DEFAULT ");

    private String option;
    private DBColOption(String option) {
        this.option = option;
    }
    /** Get the SQL Option */
    public String get() {return option;}
}