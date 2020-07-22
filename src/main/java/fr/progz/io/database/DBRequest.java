package fr.progz.io.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;

import org.bukkit.Bukkit;

import fr.progz.io.ConfigFiles;
import fr.progz.io.IOMessage;
import fr.progz.io.database.errors.DBErrorType;
import fr.progz.io.database.errors.DBException;

/**
 * Basic connection class
 * Must be extended then use : 
 * 	- setJDBCAdress()
 *  - connectDB()
 *  - setRequest()
 *  - execute()
 *  - closeDB()
 * /!\ Don't forget to close DB  
 * @author Meltwin
 * @version 1.0.0
 */
public abstract class DBRequest {
	
	protected Connection db;
	protected String url;
	
	protected String request = "";
	protected ArrayList<String> arg= new ArrayList<String>();
	protected ResultSet rs;
	
	/**
	 * Set the adress of the database on wich the process will connect
	 * @param address : the db adress using jdbc (sqlite)
	 */
	public void setJDBCAdress(String address) {
		url = "jdbc:sqlite:"+address;
	}
	/**
	 * Create a Connection object to the database
	 * @return DBException : contains any error of the connection or return null if everything is OK
	 */
	public DBException connectDB() {
		if (ConfigFiles.DB_REQ_LOG) Bukkit.getConsoleSender().sendMessage(IOMessage.setConnect+url);
		if (!url.isEmpty()) {
			boolean blocError = false;
			try {
				// Connect to DB
				db = DriverManager.getConnection(url);
				return new DBException(DBErrorType.DB_FOUND,null);
			}
			catch(SQLException err) {
				blocError = true;
				return new DBException(DBErrorType.DB_NOT_FOUND,err);
			}
			finally { if (blocError && isConnected()) {closeDB();} } // If the DB was bad initialized
		}
		else {return new DBException(DBErrorType.DB_INVALID_ADRESS,null);}
	}
	/**
	 * Return if the Connection instance is openned
	 * @return bool : true if the link was made, else return false if the connection can't be used 
	 */
	public boolean isConnected() {return db !=null;}
	/**
	 * Close the database connection
	 * @return DBException : contains any error of the connection or return null if everything is OK
	 */
	public DBException closeDB() {
		if (ConfigFiles.DB_REQ_LOG) Bukkit.getConsoleSender().sendMessage(IOMessage.closeConnect);
		if (isConnected()) {
			try {
				db.close();
				arg.clear();
				request = "";
				db = null;
				return new DBException(DBErrorType.DB_CLOSED_SUCCESS,null);
			} catch (SQLException err) {
				return new DBException(DBErrorType.DB_NOT_FOUND,err);
			}
		}
		else {
			arg.clear();
			request = "";
			db = null;
			return new DBException(DBErrorType.DB_NOT_FOUND,null);
		}
	}
	/**
	 * Set the SQL request to be executed
	 * @param str (String) : la commande SQL
	 */
	public void setRequest(String str) {request=str;}
	/**
	 * Add the argument on the stack
	 * @param argument (String) : add the argument on the stack
	 */
	public void addArg(String argument) {arg.add(argument);}
	/**
	 * Execute the set command on the opened database
	 * @return DBException : contains any error of the connection or return null if everything is OK
	 */
	public DBException execute() {
		 if (isConnected()) {
			 try {
				if (ConfigFiles.DB_REQ_LOG) Bukkit.getConsoleSender().sendMessage(IOMessage.req+request);
				PreparedStatement req = db.prepareStatement(request);
				for (int i = 0; i<arg.size(); i++) {
					req.setString(i+1, arg.get(i));
				}
				req.execute();
				rs = req.getResultSet();
				return new DBException(DBErrorType.REQ_SUCCESS,null);
			}
			catch (SQLTimeoutException err) {return new DBException(DBErrorType.DB_TIMEOUT,err);}
			catch (SQLException err) {return new DBException(DBErrorType.DB_NOT_FOUND,err);}
		 }
		 else { return new DBException(DBErrorType.DB_NOT_FOUND,null);}
	}
	/**
	 * Return the result of the request
	 * @return ResultSet the result of the request
	 */
	public ResultSet getResult() {return rs;}
}