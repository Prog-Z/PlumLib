package fr.progz.io.database;

/**
 * Speed, easy requester on the db
 * @author Meltwin
 * @version 1.0.0
 */
public class R_UniversalRequester extends DBRequest {
	/**
	 * Prepare a connection on the given adress (JCDB)
	 * @param DBPath (String) : the db adress (file path)
	 */
	public R_UniversalRequester(String DBPath) {
		this.setJDBCAdress(DBPath);
	}
	/**
	 * Execute the given request
	 * Make getResult() to get the result of the request
	 * @param request (String) the SQL request to be executed
	 */
	public void executeRequest(String request) {
		this.setRequest(request);
		this.connectDB();
		this.execute();
		this.closeDB();
	}
}