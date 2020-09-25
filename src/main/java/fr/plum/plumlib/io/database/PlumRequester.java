package fr.plum.plumlib.io.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import fr.plum.plumlib.chat.config.ChatConfig;
import fr.plum.plumlib.chat.sender.IMessageSender;
import fr.plum.plumlib.chat.sender.PrefixOption;
import fr.plum.plumlib.chat.sender.Sender;
import fr.plum.plumlib.io.config.IOChatConfig;
import fr.plum.plumlib.io.database.config.DBMessages;
import fr.plum.plumlib.io.database.connection.DBConnection;
import fr.plum.plumlib.io.database.exception.DBException;
import fr.plum.plumlib.io.database.exception.DBExceptionType;

public abstract class PlumRequester implements IMessageSender, Sender {

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
        return requests.size() - 1;
    }

    protected void addArg(int index, String arg) {
        args.get(index).add(arg);
    }
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
                if (_co.getConfig().doDBLog()) sendMessage(CONSOLE(), String.format(DBMessages.PREPARE_REQ.getMessage(), r),PrefixOption.NO_PREFIX);

                req = _co.getConnection().prepareStatement(r);
                for (int argID = 0; argID < args.get(reqID).size(); argID++)
                    req.setString(argID + 1, args.get(reqID).get(argID));

                // Executing req
                if (_co.getConfig().doDBLog()) sendMessage(CONSOLE(), DBMessages.EXECUTING_REQ.getMessage(), PrefixOption.NO_PREFIX);
                req.execute();

                // Transform req
                lastResult = req.getResultSet();
                transformResult(reqID);

                // Closing temp SQL Objects
                if (lastResult != null)
                    lastResult.close();
                req.close();
            }
        } catch (SQLTimeoutException e) {
            throw new DBException(DBExceptionType.CO_TIMEOUT, e);
        } catch (SQLException e) {
            throw new DBException(DBExceptionType.DB_NOT_FOUND, e);
        }
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

    @Override
    public @NotNull ChatConfig getChatConfig() {
        return IOChatConfig.getInstance();
    }
}