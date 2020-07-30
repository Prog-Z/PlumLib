package fr.progz.io.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import org.bukkit.ChatColor;

import fr.progz.io.IOConfig;
import fr.progz.io.database.config.DBMessages;
import fr.progz.io.database.config.I_DBConfig;
import fr.progz.io.database.exception.DBException;
import fr.progz.io.database.exception.DBExceptionType;
import fr.progz.plumlib.arch.IModuleChat;

public class DBConnection implements IModuleChat {

    private Connection connection;
    private I_DBConfig config;

    public DBConnection(I_DBConfig conf) throws DBException {
        config = conf;
        makeConnection();
    }

    public void makeConnection() throws DBException {
        try {
            sendMsg(DBMessages.CONNECTING_DB.getMessage());
            connection = DriverManager.getConnection(config.getJDBCUrl());
        } 
        catch (SQLTimeoutException e) {
            throw new DBException(DBExceptionType.CO_TIMEOUT, e);
        }
        catch (SQLException e) {
            throw new DBException(DBExceptionType.DB_NOT_FOUND, e);
        }
    }

    public void closeConnection() throws DBException {
        if (connection != null) {
            try {
                sendMsg(DBMessages.CLOSE_DB.getMessage());
                connection.close();
            }
            catch (SQLException e) {
                throw new DBException(DBExceptionType.DB_NOT_FOUND,e);
            }
            finally {
                connection = null;
            }
        }
        else {
            connection =null;
            throw new DBException(DBExceptionType.DB_NOT_FOUND,null);
        }
        
    }

    public Connection getConnection() {return connection;}
    public I_DBConfig getConfig() {return config;}

    // #####################################################
    // SECTION IModuleChat
    // #####################################################
    @Override
    public ChatColor getBracketColor() { return IOConfig.sgetBracketColor(); }
    @Override
    public ChatColor getColor() { return IOConfig.sgetNameColor(); }
    @Override
    public String getModuleName() { return IOConfig.sgetName(); }
    @Override
    public byte moduleType() { return IModuleChat.TYPE_MODULE; }
    // !SECTION
}