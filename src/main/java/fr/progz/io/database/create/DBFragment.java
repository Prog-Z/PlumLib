package fr.progz.io.database.create;

import java.sql.SQLException;
import java.util.ArrayList;


import fr.progz.io.ConfigFiles;
import fr.progz.io.database.DBMultiRequest;
import fr.progz.io.database.errors.DBErrorType;
import fr.progz.io.database.errors.DBException;
import fr.progz.io.database.structure.DBTable;
import fr.progz.plumlib.arch.IModuleChat;
import fr.progz.plumlib.chat.IMessageType;

public abstract class DBFragment extends DBMultiRequest implements IModuleChat {
    // #####################################################
    // 
    //                  SECTION Messages
    // 
    // #####################################################
        private static String CHECK_START  = "Start checking for existing tables";
        private static String CHECK_RESULT = "Found %1$d/%2$d";
        private static String BUILD_START  = "Start making missing tables"; 
        private static String BUILD_END    = "Finished making tables";
        private static String LINE_START  = "Start making missing tables"; 
        private static String LINE_END    = "Finished making tables"; 
    // !SECTION

    private ArrayList<DBTable> _tables = new ArrayList<>();

    private byte MODE = 0;
    /*
     *  MODE : 
     *      0 => Getting tables name
     *      1 => Building Table
     *      2 => Add Lines
     */

    // #####################################################
    //
    //                          INIT
    //
    // #####################################################

    public DBFragment() {
        setJDBCAdress(ConfigFiles.getAbsoluteDatabasePath());
    }

    public void registerTable(DBTable t) {
        _tables.add(t);
    }
    // !INIT

    // #####################################################
    //
    //                      SECTION Check DB
    //
    // #####################################################

    private String[] CHECK_tablesName;
    private boolean[] CHECK_tablePresent;

    public void launchProcess() {
        // PHASE 0 : CHECKS
        try { checkDB(); } 
        catch (DBException e) {
            if (e.isError()) {
                closeDB();
                return;
            }
        }

        // PHASE 1 : BUILD Tables
        try { build(); }
        catch (DBException f) {
            if (f.isError()) {
                closeDB();
                return;
            }
        }

        // PHASE 2 : BUILD Lines
        try { buildLines(); }
        catch (DBException g) {
            if (g.isError()) {
                closeDB();
                return;
            }
        }
    }

    // FIXME: Error, it tries to recreate some existing DB
    private void checkDB() throws DBException {
        sendMsg(CHECK_START, IMessageType.INFO);
        // TODO Check DB File
        clearRequests();
        MODE = 0;

        // Prepare arrays
        CHECK_tablePresent = new boolean[_tables.size()];
        CHECK_tablesName = new String[_tables.size()];

        for (int i=0; i<_tables.size();i++) {
            CHECK_tablesName[i] = _tables.get(i).NAME;
        }
        
        // Get actual built tables
        addRequest("SELECT name FROM sqlite_master WHERE type='table'");

        connectDB();
        execute();
        closeDB();

        sendMsg(String.format(CHECK_RESULT, calcMissingDB(),_tables.size()), IMessageType.INFO);
    }

    private int calcMissingDB() {
        int counter = 0;
        for (boolean b : CHECK_tablePresent) {
            if (b) counter++;
        }
        return counter;
    }
    // !SECTION

    // #####################################################
    //
    //                  SECTION Building DB
    //
    // #####################################################

    private void build() throws DBException {
        sendMsg(BUILD_START, IMessageType.INFO);
        clearRequests();

        MODE = 1;

        addCreateTableReq();

        connectDB();
        execute();
        closeDB();

        sendMsg(BUILD_END, IMessageType.INFO);
    }

    private void addCreateTableReq() {
        for (int i=0; i<_tables.size();i++) {
            if (!CHECK_tablePresent[i]) addRequest(_tables.get(i).getCreateRequest());
        } 
    }
    // !SECTION

    // #####################################################
    //
    //                  SECTION Building Lines
    //
    // #####################################################
    private void buildLines() throws DBException {
        clearRequests();

        MODE = 2;
        sendMsg(LINE_START, IMessageType.INFO);

        // Add lines requests
        for (int i = 0; i<_tables.size(); i++) {
            if (!CHECK_tablePresent[i]) {
                for (String s : _tables.get(i).returnLineRequests()) {
                    addRequest(s);
                }
            }
        }

        // Execute
        connectDB();
        execute();
        closeDB();

        sendMsg(LINE_END, IMessageType.INFO);
    }
    // !SECTION

    @Override
    protected void transformResult(int reqID) throws DBException {
        switch (MODE) {
            case 0:
                try {
                    if (lastResult.next()) {
                        String n = lastResult.getString("name");
                        for (int i=0; i<_tables.size();i++) {
                            if (CHECK_tablesName[i].equals(n)) {
                                CHECK_tablePresent[i] = true;
                                break;
                            }
                        }
                        throw new DBException(DBErrorType.RS_SUCCESS, null);
                    }
                } catch (SQLException e) { throw new DBException(DBErrorType.RS_CLOSED_OR_DB_ACCESS_ERROR, e); }
                break;
        }
    }    
}