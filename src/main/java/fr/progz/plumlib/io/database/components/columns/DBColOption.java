package fr.progz.plumlib.io.database.components.columns;

public enum DBColOption {
    PRIMARY("PRIMARY KEY "),
    AUTO_INCREMENT("AUTOINCREMENT "),
    DEFAULT("DEFAULT ");

    private String option;
    private DBColOption(String option) {
        this.option = option;
    }

    public String get() {return option;}
}