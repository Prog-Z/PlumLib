package fr.progz.io.database.structure;

import fr.progz.io.database.DBRequest;
import fr.progz.io.database.errors.DBException;

public class R_CreateTable extends DBRequest {
	/**
	 * Create a table in the given DB 
	 * @param DBPath
	 */
	public R_CreateTable(String DBPath) {
		super();
		setJDBCAdress(DBPath);
	}
	public void createTable(DBTable table) {
		setRequest(table.getCreateRequest());
		DBException eConnect = connectDB();
		if (!eConnect.isError()) {
			DBException eExe = execute();
			if (!eExe.isError()) {
				closeDB();
			}
		}
	}
}
