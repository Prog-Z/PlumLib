package fr.progz.plumlib.io.database.components;

import java.util.ArrayList;

import fr.progz.plumlib.io.database.components.columns.DBColumn;
import fr.progz.plumlib.container.Pair;

public abstract class DBTable {
    protected ArrayList<DBColumn<?>> columns = new ArrayList<>();
    protected ArrayList<String> lines = new ArrayList<>();
    protected ArrayList<Pair<String>> uniques = new ArrayList<>();
    protected boolean exist = false;

    // #####################################################
    // SECTION Table Making
    // #####################################################
    protected void appendColumn(DBColumn<?> column) { columns.add(column); }
    protected void addUnique(String colA, String colB) { uniques.add(new Pair<String>(colA,colB)); }
    public abstract String getTableName();
    // !SECTION

    // #####################################################
    // SECTION Building Table
    // #####################################################
    public String makeRequest() {

        String output = String.format("CREATE TABLE %s(",getTableName()); // INIT

        // ADD Column
        boolean first = true;
        for(DBColumn<?> col : columns) {
            if (!first) output += ", ";
            else first = false;
            output += col.makeRequest();
        }
        // ADD Uniques
        for (Pair<String> u : uniques) output += ", UNIQUE("+u.get(false)+","+u.get(true)+") ";
        output += ")";

        return output;
    }
    public boolean isMade() {return exist;}
    public void setMade(boolean yes) {exist = yes;}
    public ArrayList<String> getLines() {return lines;}
    // !SECTION
}