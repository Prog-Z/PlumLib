package fr.progz.io.database.structure;
/**
 * Interface for creating a database
 * @author Meltwin
 * @version 1.0.0
 */
public interface DBCreatorI {
	
	
	/**
	 * Launch the making of a new DB
	 */
	void createDB();
	
	/**
	 * Create the DB Tables
	 */
	void createTables();
	/**
	 * Insert the lines in the DB
	 */
	void insertLines();
}
