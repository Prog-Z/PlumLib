package fr.progz.io.database.builder;

import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.ChatColor;

import fr.progz.io.IOConfig;
import fr.progz.io.database.PlumRequester;
import fr.progz.io.database.components.DBTable;
import fr.progz.io.database.config.DBMessages;
import fr.progz.io.database.connection.DBConnection;
import fr.progz.io.database.exception.DBException;
import fr.progz.io.database.exception.DBExceptionType;
import fr.progz.plumlib.arch.IModuleChat;

public class DBFragment extends PlumRequester implements IModuleChat {

    private ArrayList<DBTable> tables = new ArrayList<>();
    private BuildSteps step;

    public DBFragment(DBConnection connection) {
        super(connection);
    }

    // #####################################################
    // SECTION Making fragment
    // #####################################################
    public void addTable(DBTable table) {
        tables.add(table);
    }
    // !SECTION

    public void launchCheckup() {

        
        try {
            // PHASE 1 : looking for already existing table
            check();

            // PHASE 2 : building missing tables
            buildTable();

            // PHASE 3 : Add lines in missing tables
            makeLines();
        } catch (DBException e) {
            if (e.isError()) return;
        }
    }

    // #####################################################
    // SECTION Check
    // #####################################################
    private void check() throws DBException {
        changeStep(DBMessages.FRAG_CHECK_START,BuildSteps.CHECK);

        addRequest("SELECT name FROM sqlite_master WHERE type='table'");
        execute();

        sendMsg(String.format(DBMessages.FRAG_CHECK_RESULT.getMessage(),calcMissingDBNumber(),tables.size()));
    }

    private int calcMissingDBNumber() {
        int counter = 0;
        for (DBTable tb : tables) if (tb.isMade()) counter++;
        return counter;
    }
    // !SECTION


    // #####################################################
    //  SECTION Build Tables
    // #####################################################
    private void buildTable() throws DBException {
        changeStep(DBMessages.FRAG_BUILD_START, BuildSteps.BUILD);

        for (DBTable tb : tables) if (!tb.isMade()) addRequest(tb.makeRequest());
        execute();

        sendMsg(DBMessages.FRAG_BUILD_END.getMessage());
    }
    // !SECTION


    // #####################################################
    // SECTION Make Lines
    // #####################################################
    private void makeLines() throws DBException {
        changeStep(DBMessages.FRAG_LINE_START, BuildSteps.LINES);

        for (DBTable tb : tables)
            if (!tb.isMade())
                for (String req : tb.getLines())
                    addRequest(req);
        execute();

        sendMsg(DBMessages.FRAG_LINE_END.getMessage());
    }
    // !SECTION


    // #####################################################
    // SECTION Calculation Core
    // #####################################################
    @Override
    protected void transformResult(int reqID) throws DBException {
        switch (step) {
            case CHECK:
                try {
                    while (lastResult.next()) {
                        String name = lastResult.getString("name");
                        for (DBTable tb : tables) {
                            if (tb.getTableName().equals(name)) {
                                tb.setMade(true);
                                break;
                            }
                        }
                    }
                } catch (SQLException e) { throw new DBException(DBExceptionType.RS_CLOSED_OR_DB_NOT_FOUND, e); }
            case BUILD:
                break;
            case LINES:
                break;
        }
    }

    private void changeStep(DBMessages msg, BuildSteps step) {
        sendMsg(msg.getMessage());
        this.step = step;
        clear();
    }
    // !SECTION


    // #####################################################
    // SECTION ModuleChat
    // #####################################################

    @Override
    public ChatColor getBracketColor() { return IOConfig.sgetBracketColor(); }
    @Override
    public ChatColor getColor() { return IOConfig.sgetNameColor(); }
    @Override
    public String getModuleName() { return IOConfig.sgetName(); }
    // !SECTION
}