package fr.progz.io.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;

import fr.progz.io.database.config.DBMessages;
import fr.progz.io.database.connection.DBConnection;
import fr.progz.io.database.exception.DBException;
import fr.progz.io.database.exception.DBExceptionType;
import fr.progz.plumlib.chat.IMessageType;

public abstract class PlumRequester {

    private DBConnection _co;
    private ArrayList<String> requests = new ArrayList<>();
    private ArrayList<ArrayList<String>> args = new ArrayList<>();

    protected PlumRequester(DBConnection co) {
        _co = co;
        clear();
    }

    // #####################################################
    // SECTION Preparation
    // #####################################################
    protected int addRequest(String request) {
        requests.add(request);
        args.add(new ArrayList<String>());
        return requests.size()-1;
    }
    protected void addArg(int index, String arg) { args.get(index).add(arg); }
    // !SECTION

    // #####################################################
    // SECTION Execute
    // #####################################################
    protected void execute() throws DBException {
        try {
            PreparedStatement req;
            for (int reqID = 0; reqID < this.requests.size(); reqID++) { // Execute for each request
                String r = requests.get(reqID);
                
                // Preparing req
                if (_co.getConfig().doDBLog()) _co.sendMsg(String.format(DBMessages.PREPARE_REQ.getMessage(),r),IMessageType.DEFAULT,false); // LOG IN CONSOLE
                req = _co.getConnection().prepareStatement(r);
                for (int argID = 0; argID < args.get(reqID).size(); argID++)
                    req.setString(argID+1,args.get(reqID).get(argID));

                // Executing req
                if (_co.getConfig().doDBLog()) _co.sendMsg(DBMessages.EXECUTING_REQ.getMessage(),IMessageType.SUCCESS,false);
                req.execute();

                // Transform req
                lastResult = req.getResultSet();
                transformResult(reqID);

                // Closing temp SQL Objects
                if (lastResult != null) lastResult.close();
                req.close();
            }
        }
        catch (SQLTimeoutException e) {throw new DBException(DBExceptionType.CO_TIMEOUT,e);}
        catch (SQLException e) {throw new DBException(DBExceptionType.DB_NOT_FOUND,e);}
    }

    protected ResultSet lastResult;

    protected abstract void transformResult(int reqID) throws DBException;

    // !SECTION

    // #####################################################
    // SECTION Resetting
    // #####################################################
    protected void clear() {
        this.requests.clear();
        this.args.clear();
        lastResult = null;
    }
    // !SECTION
}