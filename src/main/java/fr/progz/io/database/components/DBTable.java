package fr.progz.io.database.components;

import java.util.ArrayList;

import fr.progz.io.database.components.columns.DBColumn;

public abstract class DBTable {
    protected ArrayList<DBColumn> columns = new ArrayList<>();
    protected ArrayList<String> lines = new ArrayList<>();
    protected boolean exist = false;

    // #####################################################
    // SECTION Table Making
    // #####################################################
    protected void appendColumn(DBColumn column) {
        columns.add(column);
    }
    public abstract String getTableName();
    // !SECTION

    // #####################################################
    // SECTION Building Table
    // #####################################################
    public String makeRequest() {

        String output = String.format("CREATE TABLE %s(",getTableName());
        boolean first = true;
        for(DBColumn col : columns) {
            if (!first) output += ", ";
            else first = false;

            output += col.makeRequest();
        }
        output += ")";

        return output;
    }
    public boolean isMade() {return exist;}
    public void setMade(boolean yes) {exist = yes;}
    public ArrayList<String> getLines() {return lines;}
    // !SECTION
}