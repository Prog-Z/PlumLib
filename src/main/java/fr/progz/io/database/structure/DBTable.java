package fr.progz.io.database.structure;

import java.util.ArrayList;
/**
 * Base Table Creator
 * @author Meltwin
 * @version 1.0.0
 */
public class DBTable {
	
	public String NAME="";
	protected int COLUMN_NUMBER = 1;
	protected DBColumn[] COLUMNS = new DBColumn[COLUMN_NUMBER];	
	protected ArrayList<String> LINE_REQUESTS = new ArrayList<>();
	
	/**
	 * Set the name of the DB table
	 */
	public void setTableName(String name) {NAME=name;}
	/** Get the table name */
	public String getTableName() {return NAME;}
	
	/**
	 * Set the number of column of the table
	 * Erase previous registered column
	 * @param nCol (int) : the number of wanted column 
	 */
	public void setColumnNumber(int nCol) {COLUMNS = new DBColumn[nCol];}
	/** Return the number of column in this table*/
	public int getColumnNumber() {return COLUMNS.length;}
	
	/** Set a column at the provided position 
	 * @param pos (int) the position in the table
	 * @param col (DBColumn) the column parameters
	 */
	public void setColumn(int pos, DBColumn col) {COLUMNS[pos] = col;}
	
	/**
	 * Make the request to create the table in a DB
	 * @return the request
	 */
	public String getCreateRequest() {
		String output = "";
		// CREATE TABLE NAME (KEY VALUES, ...)
		output += "CREATE TABLE ";
		output += NAME+" ("; // Table Name
		boolean first = true;
		for(DBColumn col : COLUMNS) {
			if (!first) output+=", ";
			first = false;
			output += col.getFormatedColumn();
		}
		output += ")";
		return output;
	}
	/**
	 * Return an INSERT request with the given elements
	 * @return the request
	 */
	public ArrayList<String> returnLineRequests() {return LINE_REQUESTS;}
}
