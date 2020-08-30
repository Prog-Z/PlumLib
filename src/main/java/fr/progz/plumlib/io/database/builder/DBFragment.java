package fr.progz.plumlib.io.database.builder;

import java.sql.SQLException;
import java.util.ArrayList;

import fr.progz.plumlib.chat.sender.IMessageSender;
import fr.progz.plumlib.io.database.PlumRequester;
import fr.progz.plumlib.io.database.components.DBTable;
import fr.progz.plumlib.io.database.config.DBMessages;
import fr.progz.plumlib.io.database.connection.DBConnection;
import fr.progz.plumlib.io.database.exception.DBException;
import fr.progz.plumlib.io.database.exception.DBExceptionType;

public class DBFragment extends PlumRequester {

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

        sendMessage(IMessageSender.CONSOLE, String.format(DBMessages.FRAG_CHECK_RESULT.getMessage(),calcMissingDBNumber(),tables.size()));
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

        sendMessage(IMessageSender.CONSOLE, DBMessages.FRAG_BUILD_END.getMessage());
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

        sendMessage(IMessageSender.CONSOLE, DBMessages.FRAG_LINE_END.getMessage());
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
        sendMessage(IMessageSender.CONSOLE, msg.getMessage());
        this.step = step;
        clear();
    }
    // !SECTION
}