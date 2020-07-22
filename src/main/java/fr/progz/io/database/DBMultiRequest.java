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
public abstract class DBMultiRequest {
	

	// SET data

	protected Connection db;
	protected String url;
	
	protected ArrayList<String> request = new ArrayList<>();
	protected ArrayList<ArrayList<String>> arg= new ArrayList<ArrayList<String>>();

	// #####################################################
	// 
	// 					   INIT Connection
	// 
	// #####################################################
	/**
	 * Set the adress of the database on wich the process will connect
	 * @param address : the db adress using jdbc (sqlite)
	 */
	public void setJDBCAdress(String address) {
		url = "jdbc:sqlite:"+address;
	}
	// !INIT



	// #####################################################
	// 
	// 				  SECTION Connection gestion
	// 
	// #####################################################
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
	 * Close the database connection
	 * @return DBException : contains any error of the connection or return null if everything is OK
	 */
	public DBException closeDB() {
		if (ConfigFiles.DB_REQ_LOG) Bukkit.getConsoleSender().sendMessage(IOMessage.closeConnect);
		if (isConnected()) {
			clearRequests();
			db = null;
			return new DBException(DBErrorType.DB_CLOSED_SUCCESS,null);
		}
		else {
			clearRequests();
			db = null;
			return new DBException(DBErrorType.DB_NOT_FOUND,null);
		}
	}
	// !SECTION



	// #####################################################
	// 
	// 			 SECTION Request & argument pass
	// 
	// #####################################################
	/**
	 * Set the SQL request to be executed
	 * @param str (String) : la commande SQL
	 */
	public void addRequest(String str) {
		request.add(str);
		arg.add(new ArrayList<String>());
	}

	/**
	 * Add the argument on the stack
	 * @param argument (String) : add the argument on the stack
	 */
	public void addArg(int reqID, String argument) {arg.get(reqID).add(argument);}
	// !SECTION



	// #####################################################
	// 
	// 			 SECTION Execution & Result
	// 
	// #####################################################
	/**
	 * Execute the set command on the opened database
	 * @return DBException : contains any error of the connection or return null if everything is OK
	 */
	public void execute() throws DBException {
		 if (isConnected()) {
			 try {
				PreparedStatement req;
				for (int reqID =0; reqID<request.size(); reqID++) {

					String thisReq = request.get(reqID);
					if (ConfigFiles.DB_REQ_LOG) Bukkit.getConsoleSender().sendMessage(IOMessage.req+thisReq);
					req = this.db.prepareStatement(thisReq);
					for (int i = 0; i<arg.get(reqID).size(); i++) {
						req.setString(i+1, arg.get(reqID).get(i)); // OOFB
					}
					req.execute();
					lastResult = req.getResultSet();
					transformResult(reqID);
					req.close();
				
				}
				throw new DBException(DBErrorType.REQ_SUCCESS,null);
			}
			catch (SQLTimeoutException err) {throw new DBException(DBErrorType.DB_TIMEOUT,err);}
			catch (SQLException err) {throw new DBException(DBErrorType.DB_NOT_FOUND,err);}
		 }
		 else { throw new DBException(DBErrorType.DB_NOT_FOUND,null);}
	}
	// SECTION TO Override
	protected ResultSet lastResult;
	/**
	 * Get the last result set & transform it into a durable thing
	 */
	protected void transformResult(int reqID) throws DBException {}
	// !SECTION

	// !SECTION


	// #####################################################
	// 
	// 			 SECTION Management function
	// 
	// #####################################################
	/**
	 * Return if the Connection instance is openned
	 * @return bool : true if the link was made, else return false if the connection can't be used 
	 */
	public boolean isConnected() {return db !=null;}

	public void clearRequests() {
		this.request.clear();
		this.arg.clear();
		lastResult = null;
	}
	// !SECTION
}