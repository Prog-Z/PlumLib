package fr.progz.plumlib.io.database.connection;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import org.jetbrains.annotations.NotNull;

import fr.progz.plumlib.io.config.IOChatConfig;
import fr.progz.plumlib.io.database.config.DBMessages;
import fr.progz.plumlib.io.database.config.IDBConfig;
import fr.progz.plumlib.io.database.exception.DBException;
import fr.progz.plumlib.io.database.exception.DBExceptionType;
import fr.progz.plumlib.chat.config.ChatConfig;
import fr.progz.plumlib.chat.sender.IMessageSender;

public class DBConnection implements IMessageSender {

    private Connection connection;
    private IDBConfig config;

    public DBConnection(IDBConfig conf) {
        config = conf;

        File db = new File(conf.getAbsoluteDBPathString());
        if (!db.exists())
            db.getParentFile().mkdirs();
    }

    public void makeConnection() throws DBException {
        try {
            sendMessage(IMessageSender.CONSOLE,DBMessages.CONNECTING_DB.getMessage());
            connection = DriverManager.getConnection(config.getJDBCUrl());
        } catch (SQLTimeoutException e) {
            throw new DBException(DBExceptionType.CO_TIMEOUT, e);
        } catch (SQLException e) {
            throw new DBException(DBExceptionType.DB_NOT_FOUND, e);
        }
    }

    public void closeConnection() throws DBException {
        if (connection != null) {
            try {
                sendMessage(IMessageSender.CONSOLE,DBMessages.CLOSE_DB.getMessage());
                connection.close();
            } catch (SQLException e) {
                throw new DBException(DBExceptionType.DB_NOT_FOUND, e);
            } finally {
                connection = null;
            }
        } else {
            connection = null;
            throw new DBException(DBExceptionType.DB_NOT_FOUND, null);
        }

    }

    

    // #####################################################
    // GET
    // #####################################################

    public Connection getConnection() { return connection; }

    public IDBConfig getConfig() { return config; }

    @Override
    public @NotNull ChatConfig getChatConfig() { return IOChatConfig.getInstance(); }
    // !SECTION
}